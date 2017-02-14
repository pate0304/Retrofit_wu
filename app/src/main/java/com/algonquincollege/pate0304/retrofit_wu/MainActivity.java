package com.algonquincollege.pate0304.retrofit_wu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.algonquincollege.pate0304.retrofit_wu.data.model.Image;
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
//        cityname = "ottawa";





        Button btn =(Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cityname;
                EditText input = (EditText) findViewById(R.id.editText);
                cityname = input.getText().toString();
                weatherAPI.Factory.getInstance().getweather(cityname).enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        //set output
                        if(response.body().getCurrentObservation() != null) {
                            String city = response.body().getCurrentObservation().getObservationLocation().getCity();
                            Double temp = response.body().getCurrentObservation().getTempC();
                            Log.d("result", city + temp);
                            TextView tvtemp = (TextView) findViewById(R.id.textView_temp);
                            TextView tv = (TextView) findViewById(R.id.textView_city);
                            tv.setText(city);
                            String numasstring = new Double(temp).toString();
                            tvtemp.setText(numasstring);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Please Try different city!",Toast.LENGTH_LONG).show();
                        }



                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        Log.e("FAILED!!!",t.getMessage());
                    }
                });            }

        });
    }

    private void search() {
//        Log.d("city value on start",cityname);


    }


}
