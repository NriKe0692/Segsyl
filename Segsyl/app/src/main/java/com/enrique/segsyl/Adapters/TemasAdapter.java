package com.enrique.segsyl.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.enrique.segsyl.Models.TemasResponse;
import com.enrique.segsyl.R;

import java.util.ArrayList;

/**
 * Created by USUARIO on 26/11/2016.
 */

public class TemasAdapter extends RecyclerView.Adapter<TemasAdapter.MyViewHolder>{

    ArrayList<TemasResponse> data;
    Context context;


    public TemasAdapter(ArrayList<TemasResponse> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_temas,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_nombre_tema.setText(data.get(position).getNombre());

        View.OnClickListener clickEnCheckBox = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean verificado = false;
                if(holder.checkBox.isActivated()){
                    verificado = true;
                }
            }
        };

        holder.checkBox.setOnClickListener(clickEnCheckBox);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_nombre_tema;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_nombre_tema = (TextView) itemView.findViewById(R.id.tv_nombre_tema);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
