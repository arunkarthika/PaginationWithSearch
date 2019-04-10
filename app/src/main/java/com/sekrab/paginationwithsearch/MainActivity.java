package com.sekrab.paginationwithsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{


    ArrayList<Model> list = new ArrayList<Model>();
    ArrayList<modelpojo> noticeArrayList = new ArrayList<modelpojo>();
    static List<modelpojo> modelpojos = new ArrayList<modelpojo>();
    APIInterface apiInterface;
    static RecyclerView recycler_view, recycler_view1;
    Recycleradapter adapter, adapter1;
    AutoCompleteTextView editsearch;
    int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view = (RecyclerView) findViewById(R.id.recyclerview);

        editsearch = (AutoCompleteTextView) findViewById(R.id.editsearch);
        apiInterface = APIClient.getClient().create(APIInterface.class);

//        adapter = new Recycleradapter(noticeArrayList, MainActivity.this);
        adapter = new Recycleradapter(modelpojos, MainActivity.this);
        recycler_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setLoadMoreListener(new Recycleradapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                Log.d("page", String.valueOf(page));
                Call<Model> call = apiInterface.doGetListResources(page);
                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Log.i("autolog", "response: " + response);
                        if (response.body().getMessage().equalsIgnoreCase("No Data Found!")) {

                        } else {
                            modelpojos.addAll(response.body().getModelpojos());
                            adapter.notifyDataChanged();
                        }


                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.i("autolog", "Throwable: " + t);

                    }
                });
            }
        });

        // use this if you want the RecyclerView to look like a vertical list view
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Call<Model> call = apiInterface.doGetListResources(page);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.i("autolog", "response: " + response);

                modelpojos.addAll(response.body().getModelpojos());
                adapter.notifyDataChanged();



            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.i("autolog", "Throwable: " + t);

            }
        });

        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                adapter.getFilter().filter(s);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 4) {
                    adapter.getFilter().filter(s);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
               adapter.getFilter().filter(s);
              adapter.notifyDataSetChanged();
           }
        });

    }



}
