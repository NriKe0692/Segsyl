package com.enrique.segsyl.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.enrique.segsyl.Adapters.TemasAdapter;
import com.enrique.segsyl.Models.TemasResponse;
import com.enrique.segsyl.Models.ValidadoResponse;
import com.enrique.segsyl.R;
import com.enrique.segsyl.SegsylApp;
import com.enrique.segsyl.Utils.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemasPorCursoActivity extends AppCompatActivity {

    TextView tv_semana_tema;
    TextView tv_titulo;
    Button bt_validar;

    LinearLayout ll_container_1;
    LinearLayout ll_container_2;
    LinearLayout ll_container_3;
    LinearLayout ll_container_4;
    LinearLayout ll_container_5;
    LinearLayout ll_container_6;
    LinearLayout ll_container_7;
    LinearLayout ll_container_8;
    LinearLayout ll_container_9;
    LinearLayout ll_container_10;

    TextView tv_nombre_tema1;
    TextView tv_nombre_tema2;
    TextView tv_nombre_tema3;
    TextView tv_nombre_tema4;
    TextView tv_nombre_tema5;
    TextView tv_nombre_tema6;
    TextView tv_nombre_tema7;
    TextView tv_nombre_tema8;
    TextView tv_nombre_tema9;
    TextView tv_nombre_tema10;

    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    CheckBox checkBox7;
    CheckBox checkBox8;
    CheckBox checkBox9;
    CheckBox checkBox10;

    EditText et_comentario1;
    EditText et_comentario2;
    EditText et_comentario3;
    EditText et_comentario4;
    EditText et_comentario5;
    EditText et_comentario6;
    EditText et_comentario7;
    EditText et_comentario8;
    EditText et_comentario9;
    EditText et_comentario10;


    String nombreCurso;

    int sessionId;
    String correo;

    Context context;
    ArrayList<TemasResponse> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas_por_curso);

        context = this;

        tv_titulo = (TextView) findViewById(R.id.tv_titulo);
        tv_semana_tema = (TextView) findViewById(R.id.tv_semana_tema);
        bt_validar = (Button) findViewById(R.id.bt_validar);
        ll_container_1 = (LinearLayout) findViewById(R.id.ll_container_1);
        ll_container_2 = (LinearLayout) findViewById(R.id.ll_container_2);
        ll_container_3 = (LinearLayout) findViewById(R.id.ll_container_3);
        ll_container_4 = (LinearLayout) findViewById(R.id.ll_container_4);
        ll_container_5 = (LinearLayout) findViewById(R.id.ll_container_5);
        ll_container_6 = (LinearLayout) findViewById(R.id.ll_container_6);
        ll_container_7 = (LinearLayout) findViewById(R.id.ll_container_7);
        ll_container_8 = (LinearLayout) findViewById(R.id.ll_container_8);
        ll_container_9 = (LinearLayout) findViewById(R.id.ll_container_9);
        ll_container_10 = (LinearLayout) findViewById(R.id.ll_container_10);
        tv_nombre_tema1 = (TextView) findViewById(R.id.tv_nombre_tema1);
        tv_nombre_tema2 = (TextView) findViewById(R.id.tv_nombre_tema2);
        tv_nombre_tema3 = (TextView) findViewById(R.id.tv_nombre_tema3);
        tv_nombre_tema4 = (TextView) findViewById(R.id.tv_nombre_tema4);
        tv_nombre_tema5 = (TextView) findViewById(R.id.tv_nombre_tema5);
        tv_nombre_tema6 = (TextView) findViewById(R.id.tv_nombre_tema6);
        tv_nombre_tema7 = (TextView) findViewById(R.id.tv_nombre_tema7);
        tv_nombre_tema8 = (TextView) findViewById(R.id.tv_nombre_tema8);
        tv_nombre_tema9 = (TextView) findViewById(R.id.tv_nombre_tema9);
        tv_nombre_tema10 = (TextView) findViewById(R.id.tv_nombre_tema10);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
        checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
        checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
        checkBox9 = (CheckBox) findViewById(R.id.checkBox9);
        checkBox10 = (CheckBox) findViewById(R.id.checkBox10);
        et_comentario1 = (EditText) findViewById(R.id.et_comentario1);
        et_comentario2 = (EditText) findViewById(R.id.et_comentario2);
        et_comentario3 = (EditText) findViewById(R.id.et_comentario3);
        et_comentario4 = (EditText) findViewById(R.id.et_comentario4);
        et_comentario5 = (EditText) findViewById(R.id.et_comentario5);
        et_comentario6 = (EditText) findViewById(R.id.et_comentario6);
        et_comentario7 = (EditText) findViewById(R.id.et_comentario7);
        et_comentario8 = (EditText) findViewById(R.id.et_comentario8);
        et_comentario9 = (EditText) findViewById(R.id.et_comentario9);
        et_comentario10 = (EditText) findViewById(R.id.et_comentario10);

        correo = PreferenceManager.getInstance(this).getPreferencAlumno().getUsername();

        if(getIntent().hasExtra("nombreCurso")){
            nombreCurso = getIntent().getStringExtra("nombreCurso");
        }

        if(getIntent().hasExtra("sessionId")){
            sessionId = getIntent().getIntExtra("sessionId",0);
        }

        tv_titulo.setText("TEMAS DE " + nombreCurso);

        getTemas();

       bt_validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Map<String,String> comentarios = new HashMap<>();


                if(!et_comentario1.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(0).getId(),et_comentario1.getText().toString());
                }
                if(!et_comentario2.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(1).getId(),et_comentario2.getText().toString());
                }
                if(!et_comentario3.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(2).getId(),et_comentario3.getText().toString());
                }
                if(!et_comentario4.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(3).getId(),et_comentario4.getText().toString());
                }
                if(!et_comentario5.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(4).getId(),et_comentario5.getText().toString());
                }
                if(!et_comentario6.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(5).getId(),et_comentario6.getText().toString());
                }
                if(!et_comentario7.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(6).getId(),et_comentario7.getText().toString());
                }
                if(!et_comentario8.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(7).getId(),et_comentario8.getText().toString());
                }
                if(!et_comentario9.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(8).getId(),et_comentario9.getText().toString());
                }
                if(!et_comentario10.getText().toString().isEmpty()){
                    comentarios.put("comentario_" + data.get(9).getId(),et_comentario10.getText().toString());
                }

                if(checkBox1.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(0).getId()));
                }
                if(checkBox2.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(1).getId()));
                }
                if(checkBox3.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(2).getId()));
                }
                if(checkBox4.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(3).getId()));
                }
                if(checkBox5.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(4).getId()));
                }
                if(checkBox6.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(5).getId()));
                }
                if(checkBox7.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(6).getId()));
                }
                if(checkBox8.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(7).getId()));
                }
                if(checkBox9.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(8).getId()));
                }
                if(checkBox10.isChecked()){
                    comentarios.put("temas[]",String.valueOf(data.get(9).getId()));
                }


                Call<ValidadoResponse> call = SegsylApp.getInstance().getServices().validarTemas(correo,sessionId,comentarios);

                call.enqueue(new Callback<ValidadoResponse>() {
                    @Override
                    public void onResponse(Call<ValidadoResponse> call, Response<ValidadoResponse> response) {
                        if(response.isSuccessful()){
                            if(response.body().getResponse().equals("OK")){
                                Toast.makeText(context,"Curso validado correctamente",Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ValidadoResponse> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void getTemas() {

        Call<ArrayList<TemasResponse>> call = SegsylApp.getInstance().getServices().getTemasPorCurso(correo,sessionId);
        call.enqueue(new Callback<ArrayList<TemasResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TemasResponse>> call, Response<ArrayList<TemasResponse>> response) {
                if(response.isSuccessful()){
                    data = response.body();
                    setDataInViews();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TemasResponse>> call, Throwable t) {

            }
        });
    }

    private void setDataInViews() {
        switch (data.size()){
            case 1:
                ll_container_1.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                break;
            case 2:
                ll_container_1.setVisibility(View.VISIBLE);
                ll_container_2.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                tv_nombre_tema2.setText(data.get(1).getNombre());
                break;
            case 3:
                ll_container_1.setVisibility(View.VISIBLE);
                ll_container_2.setVisibility(View.VISIBLE);
                ll_container_3.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                tv_nombre_tema2.setText(data.get(1).getNombre());
                tv_nombre_tema3.setText(data.get(2).getNombre());
                break;
            case 4:
                ll_container_1.setVisibility(View.VISIBLE);
                ll_container_2.setVisibility(View.VISIBLE);
                ll_container_3.setVisibility(View.VISIBLE);
                ll_container_4.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                tv_nombre_tema2.setText(data.get(1).getNombre());
                tv_nombre_tema3.setText(data.get(2).getNombre());
                tv_nombre_tema4.setText(data.get(3).getNombre());
                break;
            case 5:
                ll_container_1.setVisibility(View.VISIBLE);
                ll_container_2.setVisibility(View.VISIBLE);
                ll_container_3.setVisibility(View.VISIBLE);
                ll_container_4.setVisibility(View.VISIBLE);
                ll_container_5.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                tv_nombre_tema2.setText(data.get(1).getNombre());
                tv_nombre_tema3.setText(data.get(2).getNombre());
                tv_nombre_tema4.setText(data.get(3).getNombre());
                tv_nombre_tema5.setText(data.get(4).getNombre());
                break;
            case 6:
                ll_container_1.setVisibility(View.VISIBLE);
                ll_container_2.setVisibility(View.VISIBLE);
                ll_container_3.setVisibility(View.VISIBLE);
                ll_container_4.setVisibility(View.VISIBLE);
                ll_container_5.setVisibility(View.VISIBLE);
                ll_container_6.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                tv_nombre_tema2.setText(data.get(1).getNombre());
                tv_nombre_tema3.setText(data.get(2).getNombre());
                tv_nombre_tema4.setText(data.get(3).getNombre());
                tv_nombre_tema5.setText(data.get(4).getNombre());
                tv_nombre_tema6.setText(data.get(5).getNombre());
                break;
            case 7:
                ll_container_1.setVisibility(View.VISIBLE);
                ll_container_2.setVisibility(View.VISIBLE);
                ll_container_3.setVisibility(View.VISIBLE);
                ll_container_4.setVisibility(View.VISIBLE);
                ll_container_5.setVisibility(View.VISIBLE);
                ll_container_6.setVisibility(View.VISIBLE);
                ll_container_7.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                tv_nombre_tema2.setText(data.get(1).getNombre());
                tv_nombre_tema3.setText(data.get(2).getNombre());
                tv_nombre_tema4.setText(data.get(3).getNombre());
                tv_nombre_tema5.setText(data.get(4).getNombre());
                tv_nombre_tema6.setText(data.get(5).getNombre());
                tv_nombre_tema7.setText(data.get(6).getNombre());
                break;
            case 8:
                ll_container_1.setVisibility(View.VISIBLE);
                ll_container_2.setVisibility(View.VISIBLE);
                ll_container_3.setVisibility(View.VISIBLE);
                ll_container_4.setVisibility(View.VISIBLE);
                ll_container_5.setVisibility(View.VISIBLE);
                ll_container_6.setVisibility(View.VISIBLE);
                ll_container_7.setVisibility(View.VISIBLE);
                ll_container_8.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                tv_nombre_tema2.setText(data.get(1).getNombre());
                tv_nombre_tema3.setText(data.get(2).getNombre());
                tv_nombre_tema4.setText(data.get(3).getNombre());
                tv_nombre_tema5.setText(data.get(4).getNombre());
                tv_nombre_tema6.setText(data.get(5).getNombre());
                tv_nombre_tema7.setText(data.get(6).getNombre());
                tv_nombre_tema8.setText(data.get(7).getNombre());
                break;
            case 9:
                ll_container_1.setVisibility(View.VISIBLE);
                ll_container_2.setVisibility(View.VISIBLE);
                ll_container_3.setVisibility(View.VISIBLE);
                ll_container_4.setVisibility(View.VISIBLE);
                ll_container_5.setVisibility(View.VISIBLE);
                ll_container_6.setVisibility(View.VISIBLE);
                ll_container_7.setVisibility(View.VISIBLE);
                ll_container_8.setVisibility(View.VISIBLE);
                ll_container_9.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                tv_nombre_tema2.setText(data.get(1).getNombre());
                tv_nombre_tema3.setText(data.get(2).getNombre());
                tv_nombre_tema4.setText(data.get(3).getNombre());
                tv_nombre_tema5.setText(data.get(4).getNombre());
                tv_nombre_tema6.setText(data.get(5).getNombre());
                tv_nombre_tema7.setText(data.get(6).getNombre());
                tv_nombre_tema8.setText(data.get(7).getNombre());
                tv_nombre_tema9.setText(data.get(8).getNombre());
                break;
            case 10:
                ll_container_1.setVisibility(View.VISIBLE);
                ll_container_2.setVisibility(View.VISIBLE);
                ll_container_3.setVisibility(View.VISIBLE);
                ll_container_4.setVisibility(View.VISIBLE);
                ll_container_5.setVisibility(View.VISIBLE);
                ll_container_6.setVisibility(View.VISIBLE);
                ll_container_7.setVisibility(View.VISIBLE);
                ll_container_8.setVisibility(View.VISIBLE);
                ll_container_9.setVisibility(View.VISIBLE);
                ll_container_10.setVisibility(View.VISIBLE);
                tv_nombre_tema1.setText(data.get(0).getNombre());
                tv_nombre_tema2.setText(data.get(1).getNombre());
                tv_nombre_tema3.setText(data.get(2).getNombre());
                tv_nombre_tema4.setText(data.get(3).getNombre());
                tv_nombre_tema5.setText(data.get(4).getNombre());
                tv_nombre_tema6.setText(data.get(5).getNombre());
                tv_nombre_tema7.setText(data.get(6).getNombre());
                tv_nombre_tema8.setText(data.get(7).getNombre());
                tv_nombre_tema9.setText(data.get(8).getNombre());
                tv_nombre_tema10.setText(data.get(9).getNombre());
                break;
        }
    }

}
