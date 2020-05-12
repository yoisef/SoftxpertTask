package com.linked_sys.softxperttask.CarsModule.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
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
    private ProgressDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        dialog1 = new ProgressDialog(this);



        binding.searchbut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String pagenumber= binding.editNumber.getText().toString();

               if (!pagenumber.equals("")) {

                   getCarsList(Integer.parseInt(pagenumber));
               }
               else{
                   Toast.makeText(MainActivity.this, "Enter Page Number ", Toast.LENGTH_SHORT).show();
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
               else{
                   binding.refreshCars.setRefreshing(false);
               }


           }
       });
    }





    private void getCarsList(int pagenumber)
    {
        ShowDialog(this,"Loading...");

        Call<Root<List<CarModel>>> call;
        Retrofit retrofit = RetrofitClient.getRetrofit();
        EndPoint endpoints = retrofit.create(EndPoint.class);
        call=endpoints.getAllCars(pagenumber);
        call.enqueue(new Callback<Root<List<CarModel>>>() {
            @Override
            public void onResponse(Call<Root<List<CarModel>>> call, Response<Root<List<CarModel>>> response) {

                HideDialog();
                if (response.isSuccessful())
                {


                    binding.refreshCars.setRefreshing(false);
                    if (response.body().getData()!=null)
                    {
                        List<CarModel> cars= response.body().getData();
                        if (cars.size()!=0)
                        {
                            binding.recycleCars.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            binding.recycleCars.setAdapter(new CarsAdapter(MainActivity.this,cars));
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "No Cars withThis Page Number ", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    try {
                        String res=response.errorBody().string();
                        JSONObject jsonObject=new JSONObject(res);
                        JSONObject error= new JSONObject((String) jsonObject.get("error"));

                        Toast.makeText(MainActivity.this, String.valueOf(error.get("message")) ,Toast.LENGTH_SHORT).show();
                        binding.refreshCars.setRefreshing(false);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Root<List<CarModel>>> call, Throwable t) {

                HideDialog();
                Toast.makeText(MainActivity.this, getResources().getString(R.string.connection_lost), Toast.LENGTH_SHORT).show();
                binding.refreshCars.setRefreshing(false);

            }
        });


    }

    public void ShowDialog(final Activity ac, final String message)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog1.setMessage(message);
                dialog1.setCancelable(false);
                if(!dialog1.isShowing()) {
                    dialog1.show();
                }
            }
        });

    }

    public void HideDialog()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(dialog1.isShowing())
                {
                    dialog1.dismiss();
                }
            }
        });
    }
}
