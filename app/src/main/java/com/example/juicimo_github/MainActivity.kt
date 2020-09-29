package com.example.juicimo_github

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juicimo_github.adapters.OnRepositoryItemClickListener
import com.example.juicimo_github.adapters.ReposRecyclerAdapter
import com.example.juicimo_github.models.Repository
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_scrolling.*


class MainActivity : AppCompatActivity(), OnRepositoryItemClickListener {

    private lateinit var repositoryAdapter: ReposRecyclerAdapter
    private val repository: Repository = Repository()
    val url = "https://api.github.com/users/Inza/repos"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = "GitHub/Inza"
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val intent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "tomas.jukin@juicymo.cz", null
                )
            )
            startActivity(intent)
        }

        initRecyclerView()

        // Load repositories into database and recyclerView
        repository.loadIntoDatabase(applicationContext, repositoryAdapter, url)

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
            repositoryAdapter =
                ReposRecyclerAdapter(this@MainActivity)
            recycler_view.adapter = repositoryAdapter
        }
    }


    /**
     * Catches clicks on recyclerview
     * Starts new DetailActivity intent
     */
    override fun onItemClick(item: Repository, position: Int) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("title", item.name)
        startActivity(intent)
    }


}