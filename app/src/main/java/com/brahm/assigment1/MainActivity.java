package com.brahm.assigment1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    ArrayList<TodayStoryModal> arrayList = new ArrayList<>();
    ArrayList<BannerModel> bannerArrayList = new ArrayList<>();
    ArrayList<BannerModel>bannerModelsList=new ArrayList<>();
    ArrayList<BannerModel>btnGridList=new ArrayList<>();
    TodayStoryAdapter adapter;
    BannerAdapter bannerAdapter;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    RecyclerView recyclerView,recyclerViewTodayStory;
    JSONArray homeDataListarray;
    ImageView img1,img2,img3,img5;
    ImageView btn1,btn2,btn3,btn4;
    JSONArray slidemenulistarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);

        img5=findViewById(R.id.img5);
        btn1=findViewById(R.id.button1);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);

        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        nav.bringToFront();
        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setCheckedItem(R.id.menu_wallet);
        nav.setNavigationItemSelectedListener(this);
        fetchData();
    }

    private void fetchData() {
        ApiInterface retrofitClient= new RetrofitClient().getRetrofitClient().create(ApiInterface.class);
        JsonObject postparams=new JsonObject();
        postparams.addProperty("userId", "123");
        postparams.addProperty("device_id","643adab2afe1d011");
        postparams.addProperty("adId","643adab2afe1d011");
        postparams.addProperty("deviceName","SM-M315F");
        postparams.addProperty("appVersion", "1.0.3");
        postparams.addProperty("todayOpen", "3");
        postparams.addProperty("totalOpen", "1");
        postparams.addProperty("userToken", "6f123008-d0a4-4386-92c7-245704fd9514");
        Call<ResponseBody> call=retrofitClient.postRawJSON("Api/api103/HomeData",postparams);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("API Data", String.valueOf(response.body()));
                try {
                    JSONObject result1 = new JSONObject(response.body().string());
                    if (response.isSuccessful()) {
                        JSONArray homeSliderarray=result1.getJSONArray("homeSlider");
                        slidemenulistarray=result1.getJSONArray("sideMenuList");
                        homeDataListarray=result1.getJSONArray("homeDataList");


                        getHomeSlider(homeSliderarray);
                        getHomeDataList(homeDataListarray);
                        setScreenValue(homeDataListarray);
//                        JSONArray todayStoryArray=result1.getJSONArray("")

//                        Constants.alertDialog(this, "User updated successfully");
                    }
                    else{

                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        recyclerView = findViewById(R.id.event_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        bannerAdapter = new BannerAdapter(getApplicationContext(), bannerArrayList);
        recyclerView.setAdapter(bannerAdapter);
    }

    private void setScreenValue(JSONArray homeDataListarray) {
        try {
            for (int j = 0; j < homeDataListarray.length(); j++) {
                JSONObject jb = new JSONObject(homeDataListarray.get(j).toString());

                if(j==1 || j==5 || j==4)
                {
                    continue;
                }
                else if(jb.get("type").equals("earnGrid")) {
                    /*
                    * Button grid me setkrdo
                    * */
                    JSONArray jsonArray = jb.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jb2 = new JSONObject(jsonArray.get(i).toString());
                        String image = jb2.getString("image");
                        btnGridList.add(new BannerModel(image));
                    }

                    Glide.with(this).load(btnGridList.get(0).getUrl()).into(btn1);
                    Glide.with(this).load(btnGridList.get(1).getUrl()).into(btn2);
                    Glide.with(this).load(btnGridList.get(2).getUrl()).into(btn3);
                    Glide.with(this).load(btnGridList.get(3).getUrl()).into(btn4);
                }
                else {
                    if (jb.has("data")) {
                        JSONArray jsonArray = jb.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jb2 = new JSONObject(jsonArray.get(i).toString());
                            String image = jb2.getString("image");
                            Log.e("TAG", image);
                            bannerModelsList.add(new BannerModel(image));
                        }
                    }
                    else{
                        continue;
                    }
                }
            }

            Glide.with(this).load(bannerModelsList.get(0).getUrl()).into(img1);
            Glide.with(this).load(bannerModelsList.get(1).getUrl()).into(img2);
            Glide.with(this).load(bannerModelsList.get(2).getUrl()).into(img3);
            Glide.with(this).load(bannerModelsList.get(3).getUrl()).into(img5);
        }catch(JSONException e){
                Log.e("getHomeDataList", e.getMessage());
                e.printStackTrace();
            }
    }

    private void getHomeSlider(JSONArray categoryList2) {
        try{
            for (int i = 0; i<categoryList2.length(); i++) {
                JSONObject jb=new JSONObject(categoryList2.get(i).toString());
                    String image = jb.getString("image");
                    bannerArrayList.add(new BannerModel(image));
            }
            recyclerView = findViewById(R.id.event_recycle);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            bannerAdapter = new BannerAdapter(getApplicationContext(), bannerArrayList);
            recyclerView.setAdapter(bannerAdapter);
            bannerAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            Log.e("getHomeSlider", e.getMessage());
            e.printStackTrace();
        }

    }
    private void getHomeDataList(JSONArray categoryList2) {
        try{

                JSONObject jb=new JSONObject(categoryList2.get(1).toString());
                JSONArray jsonArray=jb.getJSONArray("data");

            for (int i = 0; i<jsonArray.length(); i++) {
                JSONObject jb2=new JSONObject(jsonArray.get(i).toString());
                String image = jb2.getString("image");
                String title=jb2.getString("title");
                arrayList.add(new TodayStoryModal(image,title));
            }
            adapter = new TodayStoryAdapter(this,arrayList);
            recyclerViewTodayStory= findViewById(R.id.recyclerViewTodayStory);
            recyclerViewTodayStory.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
            recyclerViewTodayStory.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            Log.e("getHomeDataList", e.getMessage());
            e.printStackTrace();
        }
    }

    //Navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_wallet:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
    //Toolbar
}