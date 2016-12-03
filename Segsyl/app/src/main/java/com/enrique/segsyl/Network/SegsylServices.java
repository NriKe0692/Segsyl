package com.enrique.segsyl.Network;

import com.enrique.segsyl.Models.CursosResponse;
import com.enrique.segsyl.Models.LoginResponse;
import com.enrique.segsyl.Models.TemasResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by USUARIO on 26/11/2016.
 */

public interface SegsylServices {

    @FormUrlEncoded
    @POST(Urls.LOGIN)
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @GET(Urls.CURSOS)
    Call<CursosResponse> getCursosDeAlumno(@Path("correo") String correo);

    @GET(Urls.TEMAS)
    Call<ArrayList<TemasResponse>> getTemasPorCurso(@Path("correo") String correo, @Path("session_id") int session_id);
}
