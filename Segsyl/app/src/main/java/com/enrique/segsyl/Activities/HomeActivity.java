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

//    private void clearRecyclerView() {
//        CursosAdapter adapter = new CursosAdapter(new ArrayList<Tema>(),this);
//        rv_temas.setAdapter(adapter);
//        rv_temas.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//    private void setDataIntoRecyclerView(ArrayList<Tema> temas) {
//        CursosAdapter adapter = new CursosAdapter(temas,this);
//        rv_temas.setAdapter(adapter);
//        rv_temas.setLayoutManager(new LinearLayoutManager(this));
//    }

    private void getDataForRv(){
        String alumno_json = mPreference.getPrefernceAlumnoString();
        Alumno a = mPreference.getPreferencAlumno(alumno_json);
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
        String alumno_json = mPreference.getPrefernceAlumnoString();
        System.out.println(alumno_json + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,");
        Alumno a = mPreference.getPreferencAlumno(alumno_json);
        tv_bienvenido.setText(a.getUsername());

        if(semanas.size() == 1){
            tv_semana1.setText("Semana del " + semanas.get(0).getFechaInicio() + " al " + semanas.get(0).getFechaFin());
            CursosAdapter adapter1 = new CursosAdapter(semanas.get(0).getAsignaturas(),this);
            rv_cursos1.setLayoutManager(new LinearLayoutManager(this));
            rv_cursos1.setAdapter(adapter1);
        }else{
            if(semanas.size() == 2){
                tv_semana1.setText("Semana del " + semanas.get(0).getFechaInicio() + " al " + semanas.get(0).getFechaFin());
                CursosAdapter adapter1 = new CursosAdapter(semanas.get(0).getAsignaturas(),this);
                rv_cursos1.setLayoutManager(new LinearLayoutManager(this));
                rv_cursos1.setAdapter(adapter1);

                tv_semana2.setText("Semana del " + semanas.get(1).getFechaInicio() + " al " + semanas.get(1).getFechaFin());
                CursosAdapter adapter2 = new CursosAdapter(semanas.get(1).getAsignaturas(),this);
                rv_cursos2.setLayoutManager(new LinearLayoutManager(this));
                rv_cursos2.setAdapter(adapter2);
            }else{
                if(semanas.size() == 3){
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
