package io.travelaid.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.travelaid.R;
import io.travelaid.data.Bus;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mCtx;
    private List<Bus> bus_list;

    public RecyclerViewAdapter(Context mCtx, List<Bus> bus_list){
        this.mCtx = mCtx;
        this.bus_list = bus_list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflating the layout to get the views from the layout resource file
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
        view = layoutInflater.inflate(R.layout.bus_cardview,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //sets the respective images and text to the objects of the views
        myViewHolder.bus_image.setImageResource(bus_list.get(i).getImage());
        myViewHolder.bus_type.setText(bus_list.get(i).getType());
        myViewHolder.bus_number.setText(bus_list.get(i).getNumber());
    }

    @Override
    public int getItemCount() {
        return bus_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //creating objects of the views
        ImageView bus_image;
        TextView bus_type;
        TextView bus_number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bus_image = itemView.findViewById(R.id.bus_image);
            bus_type = itemView.findViewById(R.id.bus_type);
            bus_number = itemView.findViewById(R.id.bus_number);

        }
    }

}
