package com.enrique.segsyl.Network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.enrique.segsyl.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit class from where the RouseSalesServices are going to be retrieved
 */
public class RequestManager {

    private static SegsylServices defaultRequestManager;
    private static Retrofit retrofit;

    public RequestManager() {
        retrofit = generateRetrofit();
        defaultRequestManager = retrofit.create(SegsylServices.class);
    }

    public SegsylServices getWebServices(){
        return defaultRequestManager;
    }

    private static Retrofit generateRetrofit() {
        Gson gson = new GsonBuilder().create();

        final OkHttpClient client = getOkHttpClient();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL);
        builder = builder.addConverterFactory(GsonConverterFactory.create(gson));
        return builder.client(client).build();
    }

    /**
     * Generates OkHttpClient instance with configured timeouts and auth/logging interceptors
     *
     * @return OkHttpClient
     */
    @NonNull
    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(12, TimeUnit.SECONDS)
                .connectTimeout(12, TimeUnit.SECONDS);

        //For adding logs of APIs requests & responses
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        //General interceptor
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .method(original.method(), original.body());
                return chain.proceed(requestBuilder.build());
            }
        });

        return builder.build();
    }

}
