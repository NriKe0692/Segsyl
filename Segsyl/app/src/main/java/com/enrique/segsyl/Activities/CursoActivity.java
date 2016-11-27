package com.enrique.segsyl.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.enrique.segsyl.R;

import org.w3c.dom.Text;

public class CursoActivity extends AppCompatActivity {

    Button btn_teoria;
    Button btn_practica;
    Button btn_labo;
    TextView tv_nombre;
    TextView tv_semana;

    String nombreCurso;
    String inicio;
    String fin;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        context = this;

        btn_teoria = (Button) findViewById(R.id.btn_teoria);
        btn_practica = (Button) findViewById(R.id.btn_practica);
        btn_labo = (Button) findViewById(R.id.btn_labo);
        tv_nombre = (TextView) findViewById(R.id.tv_nombre);
        tv_semana = (TextView) findViewById(R.id.tv_semana);


        if(getIntent().hasExtra("nombreCurso")){
            nombreCurso = getIntent().getStringExtra("nombreCurso");
            if(getIntent().hasExtra("inicio")){
                inicio = getIntent().getStringExtra("inicio");
                if(getIntent().hasExtra("fin")){
                    fin = getIntent().getStringExtra("fin");
                }else{
                    finish();
                }
            }else{
                finish();
            }
        }else{
            finish();
        }

        tv_nombre.setText(nombreCurso);
        tv_semana.setText("Semana del " + inicio + " al " + fin);

        btn_teoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,TemasPorCursoActivity.class);
                i.putExtra("titulo","TEORIA");
                i.putExtra("curso",nombreCurso);
                i.putExtra("inicio",inicio);
                i.putExtra("fin",fin);
                startActivity(i);
            }
        });

        btn_practica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,TemasPorCursoActivity.class);
                i.putExtra("titulo","PRACTICA");
                i.putExtra("curso",nombreCurso);
                i.putExtra("inicio",inicio);
                i.putExtra("fin",fin);
                startActivity(i);
            }
        });

        btn_labo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,TemasPorCursoActivity.class);
                i.putExtra("titulo","LABORATORIO");
                i.putExtra("curso",nombreCurso);
                i.putExtra("inicio",inicio);
                i.putExtra("fin",fin);
                startActivity(i);
            }
        });
    }
}
