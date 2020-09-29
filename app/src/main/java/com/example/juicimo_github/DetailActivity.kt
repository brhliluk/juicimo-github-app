package com.example.juicimo_github

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juicimo_github.adapters.CommitsRecyclerAdapter
import com.example.juicimo_github.models.CommitGH
import kotlinx.android.synthetic.main.detail_activity.recycler_view

class DetailActivity : AppCompatActivity() {

    private lateinit var commitAdapter: CommitsRecyclerAdapter
    private val url = "https://api.github.com/repos/Inza/"
    private lateinit var reponame: String
    private val commit: CommitGH = CommitGH()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        setupActionBar()

        initRecyclerView()

        commit.loadIntoDatabase(applicationContext, commitAdapter, "$url$reponame/commits", reponame)

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
     * Set name to repository name
     * Add back button
     */
    private fun setupActionBar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("title")
        // allow reponame to be set with String?
        if (supportActionBar?.title == null) {
            println("error")
        } else
            reponame = supportActionBar?.title as String
    }

}