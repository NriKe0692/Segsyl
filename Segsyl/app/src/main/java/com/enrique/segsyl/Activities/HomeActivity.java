package com.enrique.segsyl.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.enrique.segsyl.Adapters.CursosAdapter;
import com.enrique.segsyl.Models.Alumno;
import com.enrique.segsyl.Models.CursosResponse;
import com.enrique.segsyl.R;
import com.enrique.segsyl.SegsylApp;
import com.enrique.segsyl.Utils.PreferenceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    TextView tv_bienvenido;

    LinearLayout container1;
    LinearLayout container2;
    LinearLayout container3;

    RecyclerView rv_cursos1;
    RecyclerView rv_cursos2;
    RecyclerView rv_cursos3;


    TextView tv_semana1;
    TextView tv_semana2;
    TextView tv_semana3;

    PreferenceManager mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mPreference = PreferenceManager.getInstance(this);

        container1 = (LinearLayout) findViewById(R.id.container1);
        container2 = (LinearLayout) findViewById(R.id.container2);
        container3 = (LinearLayout) findViewById(R.id.container3);

        rv_cursos1 = (RecyclerView) findViewById(R.id.rv_cursos1);
        rv_cursos2 = (RecyclerView) findViewById(R.id.rv_cursos2);
        rv_cursos3 = (RecyclerView) findViewById(R.id.rv_cursos3);

        tv_semana1 = (TextView) findViewById(R.id.tv_semana1);
        tv_semana2 = (TextView) findViewById(R.id.tv_semana2);
        tv_semana3 = (TextView) findViewById(R.id.tv_semana3);

        tv_bienvenido = (TextView) findViewById(R.id.tv_bienvenido);

        getDataForRv();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataForRv();
    }

    private void getDataForRv(){
        Alumno a = mPreference.getPreferencAlumno();
        Call<CursosResponse> call = SegsylApp.getInstance().getServices().getCursosDeAlumno(a.getUsername());

        call.enqueue(new Callback<CursosResponse>() {
            @Override
            public void onResponse(Call<CursosResponse> call, Response<CursosResponse> response) {
                if(response.isSuccessful()){
                    showContainers(response.body().getSemanas().size());
                    setupViews(response.body().getSemanas());
                }
            }

            @Override
            public void onFailure(Call<CursosResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Problemas con la conexión a Internet. Por favor intente otra vez",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showContainers(int size) {
        if(size == 1){
            container1.setVisibility(View.VISIBLE);
        }else{
            if(size == 2){
                container1.setVisibility(View.VISIBLE);
                container2.setVisibility(View.VISIBLE);
            }else{
                if(size == 3){
                    container1.setVisibility(View.VISIBLE);
                    container2.setVisibility(View.VISIBLE);
                    container3.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    private void setupViews(ArrayList<CursosResponse.Semanas> semanas) {
        Alumno a = mPreference.getPreferencAlumno();
        tv_bienvenido.setText(a.getUsername());

        if(semanas.size() == 1 && semanas.get(0).getAsignaturas().size() != 0){
            tv_semana1.setText("Semana del " + semanas.get(0).getFechaInicio() + " al " + semanas.get(0).getFechaFin());
            CursosAdapter adapter1 = new CursosAdapter(semanas.get(0).getAsignaturas(),this);
            rv_cursos1.setLayoutManager(new LinearLayoutManager(this));
            rv_cursos1.setAdapter(adapter1);
        }else{
            if(semanas.size() == 2 && semanas.get(1).getAsignaturas().size() != 0){
                if(semanas.get(1).getAsignaturas().size() == 0){
                    tv_semana1.setText("Semana del " + semanas.get(0).getFechaInicio() + " al " + semanas.get(0).getFechaFin());
                    CursosAdapter adapter1 = new CursosAdapter(semanas.get(0).getAsignaturas(),this);
                    rv_cursos1.setLayoutManager(new LinearLayoutManager(this));
                    rv_cursos1.setAdapter(adapter1);
                }else{
                    tv_semana1.setText("Semana del " + semanas.get(0).getFechaInicio() + " al " + semanas.get(0).getFechaFin());
                    CursosAdapter adapter1 = new CursosAdapter(semanas.get(0).getAsignaturas(),this);
                    rv_cursos1.setLayoutManager(new LinearLayoutManager(this));
                    rv_cursos1.setAdapter(adapter1);

                    tv_semana2.setText("Semana del " + semanas.get(1).getFechaInicio() + " al " + semanas.get(1).getFechaFin());
                    CursosAdapter adapter2 = new CursosAdapter(semanas.get(1).getAsignaturas(),this);
                    rv_cursos2.setLayoutManager(new LinearLayoutManager(this));
                    rv_cursos2.setAdapter(adapter2);
                }
            }else{
                if(semanas.size() == 3){
                    if(semanas.get(2).getAsignaturas().size() == 0){
                        tv_semana1.setText("Semana del " + semanas.get(0).getFechaInicio() + " al " + semanas.get(0).getFechaFin());
                        CursosAdapter adapter1 = new CursosAdapter(semanas.get(0).getAsignaturas(),this);
                        rv_cursos1.setLayoutManager(new LinearLayoutManager(this));
                        rv_cursos1.setAdapter(adapter1);

                        tv_semana2.setText("Semana del " + semanas.get(1).getFechaInicio() + " al " + semanas.get(1).getFechaFin());
                        CursosAdapter adapter2 = new CursosAdapter(semanas.get(1).getAsignaturas(),this);
                        rv_cursos2.setLayoutManager(new LinearLayoutManager(this));
                        rv_cursos2.setAdapter(adapter2);
                    }else{
                        tv_semana1.setText("Semana del " + semanas.get(0).getFechaInicio() + " al " + semanas.get(0).getFechaFin());
                        CursosAdapter adapter1 = new CursosAdapter(semanas.get(0).getAsignaturas(),this);
                        rv_cursos1.setLayoutManager(new LinearLayoutManager(this));
                        rv_cursos1.setAdapter(adapter1);

                        tv_semana2.setText("Semana del " + semanas.get(1).getFechaInicio() + " al " + semanas.get(1).getFechaFin());
                        CursosAdapter adapter2 = new CursosAdapter(semanas.get(1).getAsignaturas(),this);
                        rv_cursos2.setLayoutManager(new LinearLayoutManager(this));
                        rv_cursos2.setAdapter(adapter2);

                        tv_semana3.setText("Semana del " + semanas.get(2).getFechaInicio() + " al " + semanas.get(2).getFechaFin());
                        CursosAdapter adapter3 = new CursosAdapter(semanas.get(2).getAsignaturas(),this);
                        rv_cursos3.setLayoutManager(new LinearLayoutManager(this));
                        rv_cursos3.setAdapter(adapter3);
                    }
                }
            }
        }

    }
}

