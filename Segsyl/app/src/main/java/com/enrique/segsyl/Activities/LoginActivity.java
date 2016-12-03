package com.enrique.segsyl.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.enrique.segsyl.Models.Alumno;
import com.enrique.segsyl.Models.LoginResponse;
import com.enrique.segsyl.Network.SegsylServices;
import com.enrique.segsyl.R;
import com.enrique.segsyl.SegsylApp;
import com.enrique.segsyl.Utils.PreferenceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button bt_login;
    EditText et_codigo;
    EditText et_password;

    PreferenceManager mPreferences;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        bt_login = (Button) findViewById(R.id.bt_login);
        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_password = (EditText) findViewById(R.id.et_password);

        mPreferences = PreferenceManager.getInstance(this);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et_codigo.getText().toString().isEmpty() && !et_password.getText().toString().isEmpty()){
                    final String username = et_codigo.getText().toString();
                    final String password = et_password.getText().toString();

                    Call<LoginResponse> call = SegsylApp.getInstance().getServices().login(username,password);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if(response.isSuccessful()){
                                if(response.body().getResponse().equals("YES")){
                                    Alumno a = new Alumno(username,password);
                                    mPreferences.setPreferencesUser(a);
                                    launchHome();
                                }if(response.body().getResponse().equals("NO")){
                                    Toast.makeText(context,"Usuario no valido!",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Problemas con la conexión a Internet. Por favor intente de nuevo",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"Hay campos por llenar!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void launchHome() {
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
        finish();
    }
}
