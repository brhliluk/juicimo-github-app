package com.example.juicimo_github

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juicimo_github.adapters.CommitsRecyclerAdapter
import com.example.juicimo_github.models.CommitGH
import com.koushikdutta.ion.Ion
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.detail_activity.recycler_view
import org.json.JSONArray
import org.json.JSONObject

class DetailActivity : AppCompatActivity(){

    private lateinit var commitAdapter: CommitsRecyclerAdapter
    val url = "https://api.github.com/repos/Inza/"
    private lateinit var reponame: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        detail_repo_name.text = intent.getStringExtra("title")
        reponame = detail_repo_name.text as String

        initRecyclerView()

        loadCommitsDatabase()

        commitAdapter.submitList(SugarRecord.find(CommitGH::class.java, "parent = ?", reponame))
    }

    /**
     * Init list of repositories
     */
    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            commitAdapter =
                CommitsRecyclerAdapter(this@DetailActivity)
            recycler_view.adapter = commitAdapter
        }
    }


    /**
     * Fills database of repositories with records
     * Online results if internet connection is available
     * Offline results - loading from database if no internet
     */
    private fun loadCommitsDatabase() {
        Ion.with(applicationContext)
            .load("$url$reponame/commits")
            .asString()
            .setCallback { e, result ->
                if (e != null) {
                    //error
                    Toast.makeText(
                        applicationContext,
                        "Could not load data, using old records.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    // success retrieving repositories list, cleanup database
                    SugarRecord.deleteAll(CommitGH::class.java, "parent = ?", reponame)
                    // parse retrieved data
                    val root = JSONArray(result)
                    // select name from last max 10 json records, save to db
                    for (i in 0 until minOf(root.length(), 10)) {
                        val tmp = root.get(i) as JSONObject
                        val commit = parseJSONCommit(tmp)
                        commit.save()
                    }
                }
            }
    }

    /**
     * Parse recieved commit in json format
     * into local Commit model
     */
    private fun parseJSONCommit(jsonCom: JSONObject): CommitGH{
        val commitMessage = jsonCom.getJSONObject("commit").getString("message")
        val commitAuthor =
            jsonCom.getJSONObject("commit").getJSONObject("author").getString("name")
        val commitDate = jsonCom.getJSONObject("commit").getJSONObject("author").getString("date")
        return CommitGH(reponame, commitMessage, commitAuthor, commitDate)
    }

}