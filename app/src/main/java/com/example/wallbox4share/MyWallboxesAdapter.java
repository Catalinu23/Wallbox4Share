package com.example.wallbox4share;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyWallboxesAdapter extends RecyclerView.Adapter<MyWallboxesAdapter.MyWallboxesViewHolder> {

    String data1[], data2[];
    Context context;

    public MyWallboxesAdapter(Context ct, String s1[], String s2[]){
        context = ct;
        data1 = s1;
        data2 = s2;
    }

    @NonNull
    //@org.jetbrains.annotations.NotNull
    @Override
    public MyWallboxesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_wallboxes_row, parent, false);
        return new MyWallboxesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyWallboxesAdapter.MyWallboxesViewHolder holder, int position) {
        holder.wallbox_name_txt.setText(data1[position]);
        holder.wallbox_description_txt.setText(data2[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyWallboxesViewHolder extends RecyclerView.ViewHolder{

        TextView wallbox_name_txt, wallbox_description_txt;

        public MyWallboxesViewHolder(@NonNull View itemView) {
            super(itemView);
            wallbox_name_txt = itemView.findViewById(R.id.wallbox_name_txt);
            wallbox_description_txt = itemView.findViewById(R.id.wallbox_description_txt);
        }
    }
}
