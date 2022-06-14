package com.weather.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        var retriver = WeatherRetriever()

        val callback = object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                title = response?.body()?.query?.results?.channel?.title

                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast

                var forecastStrings = mutableListOf<String>()

                if (forecasts != null) {
                    for (forecast in forecasts) {
                        var lineText =
                            "${forecast.date} - High: ${forecast.high} Low: ${forecast.low} - ${forecast.text}"
                        forecastStrings.add(lineText)
                    }
                }

                val forecastList = findViewById<ListView>(R.id.forecastListView)

                val adapter = ArrayAdapter(
                    this@ForecastActivity,
                    android.R.layout.simple_list_item_1,
                    forecastStrings
                )

                forecastList.adapter = adapter
            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("It's not working :(")
            }

        }

        val searchTerm = intent.getStringExtra("searchTerm")

        if (searchTerm != null) {
            retriver.getForecast(callback, searchTerm)
        }
    }
}