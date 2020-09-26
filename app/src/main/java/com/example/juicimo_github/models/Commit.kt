package com.example.juicimo_github.models

import com.orm.SugarRecord

class Commit : SugarRecord {
    lateinit var name: String
    lateinit var author: String
    lateinit var date: String

    constructor() {}

    constructor(name: String, author: String, date: String) {
        this.name = name
        this.author = author
        this.date = date
    }
}