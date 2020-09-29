package com.example.juicimo_github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        Ion.with(imageView)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .animateIn(R.anim.abc_fade_in)
            .load("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/7f971445327595.582cc88e8c36d.gif");
    }
}