package com.example.memorycardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //buttons to choose activity:

        val button: Button = findViewById(R.id.button3)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
        val button1: Button = findViewById(R.id.button7)
        button1.setOnClickListener {
            val intent1 = Intent(this@MainActivity, MainActivity3::class.java)
            startActivity(intent1)
        }
        val button2: Button = findViewById(R.id.button6)
        button2.setOnClickListener {
            val intent2 = Intent(this@MainActivity, MainActivity4::class.java)
            startActivity(intent2)
        }

    }


}