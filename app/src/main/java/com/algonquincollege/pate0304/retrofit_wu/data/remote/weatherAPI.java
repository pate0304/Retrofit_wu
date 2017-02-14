package com.algonquincollege.pate0304.retrofit_wu.data.remote;

import com.algonquincollege.pate0304.retrofit_wu.data.model.Weather;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shiva on 2017-02-14.
 */

public interface weatherAPI {

    String BASE_URL ="http://api.wunderground.com/api/";

    @GET("7169c140cf26dfcb/conditions/q/Canada/{city}.json")
    Call<Weather> getweather(@Path("city") String city);

    class Factory{
        //if instance is made use it or create new if doesnt !!
        private static weatherAPI service;
        public static weatherAPI getInstance(){

            if(service ==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

             service = retrofit.create(weatherAPI.class);
                return service;
        }else{
            return service;
            }
        }


    }

}
