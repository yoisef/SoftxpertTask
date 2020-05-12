package com.linked_sys.softxperttask.CarsModule.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.linked_sys.softxperttask.CarsModule.Adapters.CarsAdapter;
import com.linked_sys.softxperttask.CarsModule.Models.CarModel;
import com.linked_sys.softxperttask.CarsModule.Models.Root;
import com.linked_sys.softxperttask.R;
import com.linked_sys.softxperttask.Utils.EndPoint;
import com.linked_sys.softxperttask.Utils.RetrofitClient;
import com.linked_sys.softxperttask.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);


       binding.searchbut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String pagenumber= binding.editNumber.getText().toString();

               if (!pagenumber.equals("")) {

                   getCarsList(Integer.parseInt(pagenumber));
               }



           }
       });





       binding.refreshCars.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {



               String pagenumber= binding.editNumber.getText().toString();

               if (!pagenumber.equals("")) {

                   getCarsList(Integer.parseInt(pagenumber));
               }


           }
       });
    }





    private void getCarsList(int pagenumber)
    {

        Call<Root<CarModel>> call;
        Retrofit retrofit = RetrofitClient.getRetrofit();
        EndPoint endpoints = retrofit.create(EndPoint.class);
        call=endpoints.getAllCars(pagenumber);
        call.enqueue(new Callback<Root<CarModel>>() {
            @Override
            public void onResponse(Call<Root<CarModel>> call, Response<Root<CarModel>> response) {

                if (response.isSuccessful())
                {

                 List<CarModel> cars= response.body().getData();
                    binding.recycleCars.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    binding.recycleCars.setAdapter(new CarsAdapter(MainActivity.this,cars));
                }else{
                    try {
                        String res=response.errorBody().string();
                        JSONObject jsonObject=new JSONObject(res);
                      JSONObject error= new JSONObject((String) jsonObject.get("error"));

                      Toast.makeText(MainActivity.this, String.valueOf(error.get("message")) ,Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<Root<CarModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, getResources().getString(R.string.connection_lost), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
