package com.enrique.segsyl.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.enrique.segsyl.Models.Tema;
import com.enrique.segsyl.R;

import java.util.ArrayList;

/**
 * Created by USUARIO on 12/11/2016.
 */

public class TemasAdapter extends RecyclerView.Adapter<TemasAdapter.MyViewHolder> {

    ArrayList<Tema> data = new ArrayList<>();
    Context context;

    public TemasAdapter(ArrayList<Tema> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tema,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_temas.setText(data.get(position).getNombreTema());
        if(data.get(position).isRealizado()){
            holder.tv_temas.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.checkbox_on_background,0);
        }else{
            holder.tv_temas.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.checkbox_off_background,0);
        }
        holder.tv_temas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get(position).isRealizado()){
                    holder.tv_temas.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.checkbox_off_background,0);
                    data.get(position).setRealizado(false);
                }else{
                    holder.tv_temas.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.checkbox_on_background,0);
                    data.get(position).setRealizado(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_temas;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_temas = (TextView) itemView.findViewById(R.id.tv_temas);
        }
    }
}
