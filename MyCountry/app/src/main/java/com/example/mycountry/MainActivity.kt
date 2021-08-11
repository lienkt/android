package com.example.mycountry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onGotoOnClickListener = object : View.OnClickListener {
            override fun onClick(clickedView: View?) {
                val gotoDetailsIntent = Intent( this@MainActivity, CountryDetailActivity::class.java)
                startActivity(gotoDetailsIntent)
            }
        }
        val gotoButton: Button = findViewById(R.id.activity_main_btn_goto)
        gotoButton.setOnClickListener(onGotoOnClickListener)
    }
}