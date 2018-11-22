package com.utsavrajvir.arham;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    //String BASE_URL = "http://192.168.43.6:8096/";
    String BASE_URL = "http://192.168.43.93:8096/";
    @GET("maincats")
    Call<List<Contacts>> getHeroes();

    @GET("test")
    Call<List<Test>> getTesting();

    @GET("category")
    Call<List<CategoryPojo>> getCategory();

    @GET("category")
    Call<CategoryPojo> getCategory1();

    @GET("SubCategory")
    Call<List<SubCategoryPojo>> getSubCategory();

    //Call<List<Contacts>> getHeroes();

    @GET("login")
    Call<LoginPojo> checkLogin();

    @GET("register")
    Call<List<LoginPojo>> checkRegistration();

    @POST("checkRegister")
    Call<ResponseBody> registration(@Body LoginPojo loginPojo);

    @POST("add/")
    Call<ResponseBody> getData(@Body Hero hero);


    @GET("mainquestion")
    Call<List<Questions>> getMainSection();

    @GET("categoryquestion")
    Call<List<Questions>> getCategoryQuestion();

    @GET("subcategoryquestion")
    Call<List<Questions>> getSubCategoryQuestion();

    @POST("submit/")
    Call<ResponseBody> submit(@Body List<Questions> questions);
}
