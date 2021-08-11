package com.example.mycountry
import retrofit2.Call
import retrofit2.http.GET
interface CountryWSInterface {
    @GET("VNM?fields=currencies;population;capital;area")
    fun getCountryList() : Call<CountryObject>
}

