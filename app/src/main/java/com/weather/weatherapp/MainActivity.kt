package com.weather.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var forecastButton = findViewById<Button>(R.id.getForecastButton)

        forecastButton.setOnClickListener {
            var searchEditText = findViewById<EditText>(R.id.editTextTextPersonName)
            if (searchEditText.text.toString().trim().isNotEmpty()) {
                var forecastIntent = Intent(applicationContext,ForecastActivity::class.java)
                forecastIntent.putExtra("searchTerm",searchEditText.text.toString())
                startActivity(forecastIntent)
            }
        }
    }
}