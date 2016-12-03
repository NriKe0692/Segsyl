package com.enrique.segsyl.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.enrique.segsyl.Adapters.TemasAdapter;
import com.enrique.segsyl.Models.TemasResponse;
import com.enrique.segsyl.R;
import com.enrique.segsyl.SegsylApp;
import com.enrique.segsyl.Utils.PreferenceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemasPorCursoActivity extends AppCompatActivity {

    TextView tv_semana_tema;
    TextView tv_titulo;
    RecyclerView rv_temas;

    String titulo;
    String inicio;
    String fin;
    String nombreCurso;

    int sessionId;

    ArrayList<TemasResponse> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas_por_curso);

        tv_titulo = (TextView) findViewById(R.id.tv_titulo);
        tv_semana_tema = (TextView) findViewById(R.id.tv_semana_tema);
        rv_temas = (RecyclerView) findViewById(R.id.rv_temas);

//        if(getIntent().hasExtra("titulo")){
//            titulo = getIntent().getStringExtra("titulo");
//            if(getIntent().hasExtra("curso")){
//                nombreCurso = getIntent().getStringExtra("curso");
//                if(getIntent().hasExtra("inicio")){
//                    inicio = getIntent().getStringExtra("inicio");
//                    if(getIntent().hasExtra("fin")){
//                        fin = getIntent().getStringExtra("fin");
//                    }else{
//                        finish();
//                    }
//                }else{
//                    finish();
//                }
//            }
//            else{
//                finish();
//            }
//        }else{
//            finish();
//        }

        if(getIntent().hasExtra("nombreCurso")){
            nombreCurso = getIntent().getStringExtra("nombreCurso");
        }

        if(getIntent().hasExtra("sessionId")){
            sessionId = getIntent().getIntExtra("sessionId",0);
        }

        tv_titulo.setText("TEMAS DE " + nombreCurso);

        getTemas();

        setDataIntoRV();

    }

    private void getTemas() {
        String correo = PreferenceManager.getInstance(this).getPreferencAlumno().getUsername();
        Call<ArrayList<TemasResponse>> call = SegsylApp.getInstance().getServices().getTemasPorCurso(correo,sessionId);
        call.enqueue(new Callback<ArrayList<TemasResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TemasResponse>> call, Response<ArrayList<TemasResponse>> response) {
                if(response.isSuccessful()){
                    data = response.body();
                    setDataIntoRV();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TemasResponse>> call, Throwable t) {

            }
        });
    }

    private void setDataIntoRV() {
        TemasAdapter adapter = new TemasAdapter(data,this);
        rv_temas.setLayoutManager(new LinearLayoutManager(this));
        rv_temas.setAdapter(adapter);
    }
}
