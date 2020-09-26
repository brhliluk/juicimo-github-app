package com.example.juicimo_github.models

import com.orm.SugarRecord

class Repository : SugarRecord {
    lateinit var name: String

    constructor() {}

    constructor(name:String){
        this.name = name
    }
}