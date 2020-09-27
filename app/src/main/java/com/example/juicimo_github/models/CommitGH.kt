package com.example.juicimo_github.models

import com.orm.SugarRecord
import java.time.LocalDateTime

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
}