package com.enrique.segsyl.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enrique.segsyl.Models.Alumno;
import com.enrique.segsyl.Models.Curso;
import com.enrique.segsyl.Models.Tema;
import com.enrique.segsyl.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Button bt_login;
    EditText et_codigo;
    EditText et_password;

    Alumno a1;
    Alumno a2;
    Alumno a3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setData();

        bt_login = (Button) findViewById(R.id.bt_login);
        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_password = (EditText) findViewById(R.id.et_password);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAlumno();
            }
        });
    }



    private void getAlumno() {
        String codigo = et_codigo.getText().toString();
        String password = et_password.getText().toString();

        if(codigo.equals(a1.getCodigo()) || codigo.equals(a2.getCodigo()) || codigo.equals(a3.getCodigo())){
            if(password.equals(a1.getPassword()) || password.equals(a2.getPassword()) || password.equals(a3.getPassword())){
                Intent i = new Intent(this,HomeActivity.class);
                if(codigo.equals(a1.getCodigo())){
                    i.putExtra("alumno",a1);
                }
                if(codigo.equals(a2.getCodigo())){
                    i.putExtra("alumno",a2);
                }
                if(codigo.equals(a3.getCodigo())) {
                    i.putExtra("alumno", a3);
                }
                startActivity(i);
                finish();
            }
        }

    }

    private void setData() {
        ArrayList<Curso> cursos1 = new ArrayList<>();
        ArrayList<Curso> cursos2 = new ArrayList<>();
        ArrayList<Curso> cursos3 = new ArrayList<>();

        ArrayList<Tema> temas1 = new ArrayList<>();
        ArrayList<Tema> temas2 = new ArrayList<>();
        ArrayList<Tema> temas3 = new ArrayList<>();
        ArrayList<Tema> temas4 = new ArrayList<>();
        ArrayList<Tema> temas5 = new ArrayList<>();
        ArrayList<Tema> temas6 = new ArrayList<>();
        ArrayList<Tema> temas7 = new ArrayList<>();
        ArrayList<Tema> temas8 = new ArrayList<>();
        ArrayList<Tema> temas9 = new ArrayList<>();
        ArrayList<Tema> temas10 = new ArrayList<>();
        ArrayList<Tema> temas11 = new ArrayList<>();

        temas1.add(new Tema("TEMA 1",false));
        temas1.add(new Tema("TEMA 2",false));
        temas1.add(new Tema("TEMA 3",false));
        temas1.add(new Tema("TEMA 4",false));
        temas1.add(new Tema("TEMA 5",false));
        temas1.add(new Tema("TEMA 6",false));
        temas1.add(new Tema("TEMA 7",false));
        temas1.add(new Tema("TEMA 8",false));
        temas1.add(new Tema("TEMA 9",false));
        temas1.add(new Tema("TEMA 10",false));

        temas2.addAll(temas1);
        temas3.addAll(temas1);
        temas4.addAll(temas1);
        temas5.addAll(temas1);
        temas6.addAll(temas1);
        temas7.addAll(temas1);
        temas8.addAll(temas1);
        temas9.addAll(temas1);
        temas10.addAll(temas1);
        temas11.addAll(temas1);



        Curso c1 = new Curso("Taller de construccion de Software",12,7,temas1);
        Curso c2 = new Curso("Idioma Extranjero VI",12,6,temas2);
        Curso c3 = new Curso("Datawarehouse",12,0,temas3);
        Curso c4 = new Curso("Redes y Transmision de Datos",12,7,temas4);
        Curso c5 = new Curso("Contabilidad para la Gestion",12,6,temas5);
        Curso c6 = new Curso("Sistemas Inteligentes",12,9,temas6);
        Curso c7 = new Curso("Arquitectura de Software",12,6,temas7);
        Curso c8 = new Curso("Seguridad de Redes",12,8,temas8);
        Curso c9 = new Curso("Gestion de Tecnologias de la Informacion",12,8,temas9);
        Curso c10 = new Curso("Inteligencia Artificial",12,8,temas10);
        Curso c11 = new Curso("Gerencia de Proyectos de Software",12,7,temas11);


        cursos1.add(c1);
        cursos1.add(c2);
        cursos1.add(c3);
        cursos1.add(c4);
        cursos1.add(c5);

        cursos2.add(c1);
        cursos2.add(c7);
        cursos2.add(c8);
        cursos2.add(c9);
        cursos2.add(c10);

        cursos3.add(c1);
        cursos3.add(c7);
        cursos3.add(c6);
        cursos3.add(c11);
        cursos3.add(c4);

        a1 = new Alumno("12200070","12200070","Enrique","Villarreal Carrillo",cursos1);
        a2 = new Alumno("12200187","12200187","Giancarlos Junior","Claudio Zavaleta", cursos2);
        a3 = new Alumno("12200055","12200055","Takeshi Santiago","Farro Hinoshita",cursos3);
    }
}
