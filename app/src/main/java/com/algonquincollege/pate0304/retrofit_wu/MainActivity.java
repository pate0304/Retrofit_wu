package com.algonquincollege.pate0304.retrofit_wu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.algonquincollege.pate0304.retrofit_wu.data.model.Weather;
import com.algonquincollege.pate0304.retrofit_wu.data.remote.weatherAPI;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


public class MainActivity extends AppCompatActivity {

    private static String city;
    private static double temp;
//   @BindView(R.id.textView_city) TextView textView_city;

//    @BindView(R.id.textView_temp) TextView textView_temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
        weatherAPI.Factory.getInstance().getweather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                //set output
                String city = response.body().getCurrentObservation().getObservationLocation().getCity();
               Double temp = response.body().getCurrentObservation().getTempC();
                Log.d("result",city+temp);
                        TextView tvtemp = (TextView) findViewById(R.id.textView_temp);
        TextView tv = (TextView) findViewById(R.id.textView_city);
        tv.setText(city);
        String numasstring = new Double(temp).toString();
        tvtemp.setText(numasstring);

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e("FAILED!!!",t.getMessage());
            }
        });

    }




}
