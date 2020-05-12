package com.linked_sys.softxperttask.CarsModule.presenter;

import android.widget.Toast;

import com.linked_sys.softxperttask.CarsModule.Adapters.CarsAdapter;
import com.linked_sys.softxperttask.CarsModule.Contract;
import com.linked_sys.softxperttask.CarsModule.Models.CarModel;
import com.linked_sys.softxperttask.CarsModule.Models.Root;
import com.linked_sys.softxperttask.CarsModule.Views.MainActivity;
import com.linked_sys.softxperttask.R;
import com.linked_sys.softxperttask.Utils.EndPoint;
import com.linked_sys.softxperttask.Utils.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter {


    Contract mainContract;
    public MainPresenter(Contract contract)
    {
        this.mainContract=contract;

    }

    public void getCarsList(int pagenumber)
    {

        Call<Root<List<CarModel>>> call;
        Retrofit retrofit = RetrofitClient.getRetrofit();
        EndPoint endpoints = retrofit.create(EndPoint.class);
        call=endpoints.getAllCars(pagenumber);
        call.enqueue(new Callback<Root<List<CarModel>>>() {
            @Override
            public void onResponse(Call<Root<List<CarModel>>> call, Response<Root<List<CarModel>>> response) {


                if (response.body().getData()!=null)
                {
                    if (response.body().getData().size()!=0)
                    {
                        mainContract.getCars(response.body().getData());
                    }
                }


            }

            @Override
            public void onFailure(Call<Root<List<CarModel>>> call, Throwable t) {



            }
        });


    }
}
