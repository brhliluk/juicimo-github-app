package com.example.juicimo_github

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.juicimo_github.adapters.OnCommitItemClickListener
import com.example.juicimo_github.models.Commit

class DetailActivity : AppCompatActivity(), OnCommitItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

    }

    override fun onItemClick(item: Commit, position: Int) {
        TODO("Not yet implemented")
    }
}