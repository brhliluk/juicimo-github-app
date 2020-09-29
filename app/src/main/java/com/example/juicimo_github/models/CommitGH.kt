package com.example.juicimo_github.models

import android.content.Context
import android.widget.Toast
import com.example.juicimo_github.adapters.CommitsRecyclerAdapter
import com.koushikdutta.ion.Ion
import com.orm.SugarRecord
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class CommitGH : SugarRecord {
    lateinit var parent: String
    lateinit var name: String
    lateinit var author: String
    lateinit var date: String

    constructor() {}

    constructor(parent: String, name: String, author: String, date: String) {
        this.parent = parent
        this.name = name
        this.author = author
        this.date = date
    }

    /**
     * Constructs Commit from downloaded JSON
     */
    constructor(parent: String, jsonCom: JSONObject) {
        //get commit message
        this.name = jsonCom.getJSONObject("commit").getString("message")
        //get commit author
        this.author =
            jsonCom.getJSONObject("commit").getJSONObject("author").getString("name")
        //get commit date, parse and format as LocalDateTime, convert to string
        val commitDate = jsonCom.getJSONObject("commit").getJSONObject("author").getString("date")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        this.date = LocalDateTime.parse(commitDate, formatter).format(
            DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM
            )
        )
        this.parent = parent
    }

    /**
     * Fills database of commits with records
     * Online results if internet connection is available
     * Offline results - loading from database if no internet
     */
    fun loadIntoDatabase(context: Context, adapter: CommitsRecyclerAdapter, url: String, parent: String) {
        Ion.with(context)
            .load(url)
            .asString()
            .setCallback { e, result ->
                if (e != null) {
                    //error
                    Toast.makeText(
                        context, "Could not load data, using old records.", Toast.LENGTH_LONG
                    ).show()
                    updateRecyclerView(adapter, parent)
                } else {
                    // success retrieving repositories list, cleanup database
                    deleteAll(CommitGH::class.java, "parent = ?", parent)
                    // parse retrieved data
                    val root = JSONArray(result)
                    // select name from last max 10 json records, save to db
                    for (i in 0 until minOf(root.length(), 10)) {
                        val tmp = root.get(i) as JSONObject
                        val commit = CommitGH(parent, tmp)
                        commit.save()
                    }
                    updateRecyclerView(adapter, parent)
                }
            }
    }

    /**
     * Inserts data into recyclerview through given adapter
     */
    private fun updateRecyclerView(adapter: CommitsRecyclerAdapter, parent: String) {
        adapter.submitList(find(CommitGH::class.java, "parent = ?", parent))
        adapter.notifyDataSetChanged()
    }

}