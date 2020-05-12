package com.linked_sys.softxperttask.CarsModule.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.linked_sys.softxperttask.CarsModule.Models.CarModel;
import com.linked_sys.softxperttask.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.viewholder> {

    Context context;
    List<CarModel> cars;
    //Utils utils;
    private int selectedPos =-1;

    public CarsAdapter(Context context, List<CarModel> cars)
    {
        this.context=context;
        this.cars=cars;

    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.raw_car,parent,false);


        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, int position) {


        Picasso.get()
                .load(cars.get(position).getImageUrl())
                .into(holder.imagecar);

        if (cars.get(position).getIsUsed())
        {
            holder.isused.setText("used");

        }else {
            holder.isused.setText("New");
        }

        holder.construction.setText(String.valueOf(cars.get(position).getConstructionYear()));

        holder.brand.setText(cars.get(position).getBrand());





    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        ImageView imagecar;
        TextView construction,brand,isused;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imagecar=itemView.findViewById(R.id.car_image);
            construction=itemView.findViewById(R.id.construction_year);
            brand=itemView.findViewById(R.id.car_brand);
            isused=itemView.findViewById(R.id.car_status);




    }
    }
}
