package com.enrique.segsyl.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.enrique.segsyl.Models.Alumno;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by USUARIO on 26/11/2016.
 */

public class PreferenceManager {
    private static final String PREFERENCES_USER = "usuario";

    private Context context;
    private static PreferenceManager self;
    private final SharedPreferences mPreferences;

    private PreferenceManager(Context context) {
        this.context = context;
        mPreferences = context.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
    }

    public static PreferenceManager getInstance(Context context) {
        if (self == null) {
            self = new PreferenceManager(context);
        }

        return self;
    }

    public void setPreferencesUser(Alumno alumno){
        Gson gson = new Gson();
        String json = gson.toJson(alumno);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(PREFERENCES_USER,json);
        editor.apply();
    }

    public String getPrefernceAlumnoString(){
        return mPreferences.getString(PREFERENCES_USER,"");
    }

    public Alumno getPreferencAlumno(){
        String alumno_json = getPrefernceAlumnoString();
        JsonParser json_parser = new JsonParser();
        if (!json_parser.parse(alumno_json).isJsonNull()){
            JsonObject json = (JsonObject) json_parser.parse(alumno_json);
            return parseUser(json,context);
        }else{
            return null;
        }
    }

    private static Alumno parseUser(JsonObject data, Context context){
        String username = data.get("username").getAsString();
        String pass = data.get("pass").getAsString();
        return new Alumno(username,pass);
    }

}
