package com.example.mycountry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CountryDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val baseURL = "https://restcountries.eu/rest/v2/alpha/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofit.create(CountryWSInterface::class.java)

        val callback = object : Callback<CountryObject> {
            override fun onResponse(call: Call<CountryObject>, response: Response<CountryObject>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val capital: TextView = findViewById(R.id.activity_detail_txt_capital_value)
                        capital.text = it.capital
                        val area : TextView = findViewById(R.id.activity_detail_txt_are_value)
                        area.text = it.area + " km2"
                        val population : TextView = findViewById(R.id.activity_detail_txt_population_value)
                        population.text = it.population
//                        val currency : TextView = findViewById(R.id.activity_detail_txt_currency_value)
//                        currency.text = it.currencies.code
                    }
                }
            }
            override fun onFailure(call: Call<CountryObject>, t: Throwable) {
            }
        }
        service.getCountryList().enqueue(callback)

        val onGotoOnClickListener = object : View.OnClickListener {
            override fun onClick(clickedView: View?) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://fr.wikipedia.org/wiki/Vi%C3%AAt_Nam"))
                startActivity(browserIntent)
            }
        }
        val gotoButton: ImageView = findViewById(R.id.activity_detail_img_search)
        gotoButton.setOnClickListener(onGotoOnClickListener)

        // Adding items to cool Stuff
        val arrayAdapter: ArrayAdapter<*>
        val coolStuff = arrayOf(
            "Travel", "Food", "History"
        )
        val coolStuffTextView: ListView = findViewById(R.id.activity_detail_txt_cool_stuff)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, coolStuff)
        coolStuffTextView.adapter = arrayAdapter

        // Adding items to bad Stuff
        val badStuffArrayAdapter: ArrayAdapter<*>
        val badStuff = arrayOf(
            "Weather", "Transportation", "Shopping"
        )
        val badStuffTextView: ListView = findViewById(R.id.activity_detail_txt_bad_stuff)
        badStuffArrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, badStuff)
        badStuffTextView.adapter = badStuffArrayAdapter
    }
}