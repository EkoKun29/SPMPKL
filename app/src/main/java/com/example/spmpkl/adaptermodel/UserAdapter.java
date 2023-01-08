package com.example.spmpkl.adaptermodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spmpkl.R;
import com.example.spmpkl.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    Context context;
    List<User> list;
    private Dialog dialog;



    public interface Dialog{
        void onClick(int pos);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }
    public UserAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.date.setText(list.get(position).getDate());
        holder.time.setText(list.get(position).getTime());
        holder.ket.setText(list.get(position).getKet());
        holder.lok.setText(list.get(position).getLoc());
        holder.zin.setText(list.get(position).getZin());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date,time,ket,lok, zin;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            ket = itemView.findViewById(R.id.ket);
            lok = itemView.findViewById(R.id.lok);
            zin = itemView.findViewById(R.id.zin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!= null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }


}
