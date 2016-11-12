package com.enrique.segsyl.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.enrique.segsyl.Adapters.TemasAdapter;
import com.enrique.segsyl.Models.Alumno;
import com.enrique.segsyl.Models.Curso;
import com.enrique.segsyl.Models.Tema;
import com.enrique.segsyl.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView tv_bienvenido;
    Spinner spinner;
    RecyclerView rv_temas;
    Button bt_validar;

    private Alumno alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle b = getIntent().getExtras();
        alumno = b.getParcelable("alumno");

        tv_bienvenido = (TextView) findViewById(R.id.tv_bienvenido);
        spinner = (Spinner) findViewById(R.id.spinner);
        rv_temas = (RecyclerView) findViewById(R.id.rv_temas);
        bt_validar = (Button) findViewById(R.id.bt_validar);

        setDataIntoSpinner();

        tv_bienvenido.setText("Bienvenido " + alumno.getApellidos() + ", " + alumno.getNombre());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position != 0){
                    setDataIntoRecyclerView(alumno.getCursos().get(position-1).getTemas());
                }
                if(position == 0){
                    clearRecyclerView();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bt_validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"TEMAS VALIDADOS!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void clearRecyclerView() {
        TemasAdapter adapter = new TemasAdapter(new ArrayList<Tema>(),this);
        rv_temas.setAdapter(adapter);
        rv_temas.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setDataIntoRecyclerView(ArrayList<Tema> temas) {
        TemasAdapter adapter = new TemasAdapter(temas,this);
        rv_temas.setAdapter(adapter);
        rv_temas.setLayoutManager(new LinearLayoutManager(this));
    }


    private void setDataIntoSpinner() {
        List<String> list = new ArrayList();

        list.add("Seleccione un Curso");

        for (Curso c: alumno.getCursos()) {
            list.add(c.getNombre());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

}
