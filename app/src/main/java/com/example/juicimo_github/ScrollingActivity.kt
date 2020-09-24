package com.example.juicimo_github

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juicimo_github.adapters.OnRepositoryItemClickListener
import com.example.juicimo_github.adapters.ReposRecyclerAdapter
import com.example.juicimo_github.classes.Repository
import kotlinx.android.synthetic.main.content_scrolling.*

class ScrollingActivity : AppCompatActivity(), OnRepositoryItemClickListener {

    private lateinit var repository: ReposRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        initRecyclerView()

        val repositoriesList = createRepositoriesDataSet()
        repository.submitList(repositoriesList)
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

    private fun createRepositoriesDataSet(): ArrayList<Repository> {
        val repositories = ArrayList<Repository>()
        val namesList = arrayOf(
            "Hradec Králové", "Chrudim", "Vysoké Mýto", "Polička", "Jaroměř",
            "Dvůr Králové", "Trutnov", "Nový Bydžov", "Mělník"
        )

        for (repo in namesList) {
            repositories.add(
                Repository(
                    repo
                )
            )
        }
        return repositories
    }

}