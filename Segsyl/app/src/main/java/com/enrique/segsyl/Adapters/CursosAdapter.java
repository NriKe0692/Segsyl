package com.enrique.segsyl.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.enrique.segsyl.Activities.TemasPorCursoActivity;
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
        holder.btn_curso.setText(data.get(position).getAsignaturaNombre() + " (" + data.get(position).getTipoClase() + ")");

        if(data.get(position).isValidado()){
            holder.btn_verificar.setImageDrawable(ContextCompat.getDrawable(context,android.R.drawable.checkbox_on_background));
        }else{
            holder.btn_verificar.setImageDrawable(ContextCompat.getDrawable(context,android.R.drawable.ic_menu_help));
        }



        switch (data.get(position).getNumeroGrupo()){
            case 1:
                holder.iv_grupo.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_filter_1_black_24dp));
                break;
            case 2:
                holder.iv_grupo.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_filter_2_black_24dp));
                break;
            case 3:
                holder.iv_grupo.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_filter_3_black_24dp));
                break;
            case 4:
                holder.iv_grupo.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_filter_4_black_24dp));
                break;
        }

        View.OnClickListener openNextActivity = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!data.get(position).isValidado()){
                    Intent intent = new Intent(context, TemasPorCursoActivity.class);
                    intent.putExtra("nombreCurso",data.get(position).getAsignaturaNombre());
                    intent.putExtra("inicio",data.get(position).getFechaInicio());
                    intent.putExtra("fin",data.get(position).getFechaFin());
                    intent.putExtra("sessionId",data.get(position).getSessionId());
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context,"Este curso ya ha sido validado!",Toast.LENGTH_SHORT).show();
                }
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
        ImageView iv_grupo;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_curso = (Button) itemView.findViewById(R.id.btn_curso);
            btn_verificar = (ImageButton) itemView.findViewById(R.id.btn_verificar);
            iv_grupo = (ImageView) itemView.findViewById(R.id.iv_grupo);
        }
    }
}
