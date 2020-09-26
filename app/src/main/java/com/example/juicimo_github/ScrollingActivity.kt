package com.example.juicimo_github

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juicimo_github.adapters.OnRepositoryItemClickListener
import com.example.juicimo_github.adapters.ReposRecyclerAdapter
import com.example.juicimo_github.models.Repository
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.koushikdutta.ion.Ion
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.content_scrolling.*
import org.json.JSONArray
import org.json.JSONObject


class ScrollingActivity : AppCompatActivity(), OnRepositoryItemClickListener {

    private lateinit var repository: ReposRecyclerAdapter
    //private val repositoriesDataSet: MutableList<Repository> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        loadReposDatabaseOnline()

        initRecyclerView()

        repository.submitList(SugarRecord.listAll(Repository::class.java))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Init list of repositories
     */
    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            repository =
                ReposRecyclerAdapter(this@ScrollingActivity)
            recycler_view.adapter = repository
        }
    }

    override fun onItemClick(item: Repository, position: Int) {
        TODO("Not yet implemented")
    }

    private fun loadReposDatabaseOnline() {
        Ion.with(applicationContext)
            .load("https://api.github.com/users/Inza/repos")
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
                    SugarRecord.deleteAll(Repository::class.java)
                    // parse retrieved data
                    val root = JSONArray(result)
                    // select name from every json record, save to db
                    for (i in 0 until root.length()) {
                        val tmp = root.get(i) as JSONObject
                        val newRepo = Repository(tmp.getString("name"))
                        newRepo.save()
                    }
                }
            }
    }


}