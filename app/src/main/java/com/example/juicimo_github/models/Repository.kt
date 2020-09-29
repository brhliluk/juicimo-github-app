package com.example.juicimo_github.models

import android.content.Context
import android.widget.Toast
import com.example.juicimo_github.MainActivity
import com.example.juicimo_github.adapters.ReposRecyclerAdapter
import com.google.gson.JsonArray
import com.koushikdutta.ion.Ion
import com.koushikdutta.ion.future.ResponseFuture
import com.orm.SugarRecord
import org.json.JSONArray
import org.json.JSONObject

class Repository : SugarRecord {
    lateinit var name: String

    constructor() {}

    constructor(name: String) {
        this.name = name
    }

    /**
     * Fills database of repositories with records
     * Online results if internet connection is available
     * Offline results - loading from database if no internet
     */
    fun loadIntoDatabase(context: Context, adapter: ReposRecyclerAdapter, url: String) {
        Ion.with(context)
            .load(url)
            .asString()
            .setCallback { e, result ->
                if (e != null) {
                    //error
                    Toast.makeText(
                        context, "Could not load data, using old records.", Toast.LENGTH_LONG
                    ).show()
                    updateRecyclerView(adapter)
                } else {
                    // success retrieving repositories list, cleanup database
                    deleteAll(Repository::class.java)
                    // parse retrieved data
                    val root = JSONArray(result)
                    // select name from every json record, save to db
                    for (i in 0 until root.length()) {
                        val tmp = root.get(i) as JSONObject
                        val newRepo = Repository(tmp.getString("name"))
                        newRepo.save()
                    }
                    updateRecyclerView(adapter)
                }
            }
    }

    /**
     * Inserts data into recyclerview through given adapter
     */
    private fun updateRecyclerView(adapter: ReposRecyclerAdapter){
        adapter.submitList(listAll(Repository::class.java))
        adapter.notifyDataSetChanged()
    }
}

