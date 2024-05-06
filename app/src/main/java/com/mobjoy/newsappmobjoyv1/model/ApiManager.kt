package com.mobjoy.newsappmobjoyv1.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    // creation session -> url / server / api calls
    //retrofit -> url ->

    // 1 time -> Design patterns -> single-ton pattern
    companion object{

        private var retrofit : Retrofit ?= null // api -> data -> no records

        private fun getInstance():Retrofit{
            if(retrofit == null){
                // create instance of retrofit
                // documentation -> ret

                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }
        fun getApis():WebServices{
            //class -> interfaces ->
            return getInstance().create(WebServices::class.java) //-> get instances -> webserv
        }
    }


}