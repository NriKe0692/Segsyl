package com.enrique.segsyl.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.enrique.segsyl.Activities.CursoActivity;
import com.enrique.segsyl.Models.CursosResponse;
import com.enrique.segsyl.R;

import java.util.ArrayList;

/**
 * Created by USUARIO on 12/11/2016.
 */

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.MyViewHolder> {

    ArrayList<CursosResponse.Semanas.Asignaturas> data = new ArrayList<>();
    Context context;

    public CursosAdapter(ArrayList<CursosResponse.Semanas.Asignaturas> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cursos,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.btn_curso.setText(data.get(position).getAsignaturaNombre());

        View.OnClickListener openNextActivity = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CursoActivity.class);
                intent.putExtra("nombreCurso",data.get(position).getAsignaturaNombre());
                intent.putExtra("inicio",data.get(position).getFechaInicio());
                intent.putExtra("fin",data.get(position).getFechaFin());
                context.startActivity(intent);
            }
        };

        holder.btn_verificar.setOnClickListener(openNextActivity);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button btn_curso;
        ImageButton btn_verificar;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_curso = (Button) itemView.findViewById(R.id.btn_curso);
            btn_verificar = (ImageButton) itemView.findViewById(R.id.btn_verificar);
        }
    }
}
