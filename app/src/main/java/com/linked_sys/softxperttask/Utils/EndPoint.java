package com.linked_sys.softxperttask.Utils;



import com.linked_sys.softxperttask.CarsModule.Models.CarModel;
import com.linked_sys.softxperttask.CarsModule.Models.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  EndPoint {


    @GET(PublicKeys.getCars)
    Call<Root<CarModel>> getAllCars(@Query("page") int pageNumber);
}
