package com.example.wazinsure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wazinsure.adapter.CustomerAdapter;
import com.example.wazinsure.model.Customer;
import com.example.wazinsure.model.CustomerResponse;
import com.example.wazinsure.service.CustomerApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerRecords extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = RecordsActivity.class.getSimpleName();
    public static final String BASE_URL = "https://demo.wazinsure.com:4443/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    @BindView(R.id.button) Button  mButton;
    @BindView(R.id.button4) Button  mButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_records);
        ButterKnife.bind(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectAndGetData();

        mButton.setOnClickListener(this);
        mButton4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v == mButton) {
            Intent intent = new Intent(CustomerRecords.this, Delete.class);
            startActivity(intent);
        }
        if(v == mButton4){
            Intent intent = new Intent(CustomerRecords.this, Update.class);
            startActivity(intent);
        }
    }
    public void connectAndGetData(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        CustomerApiService customerApiService = retrofit.create(CustomerApiService.class);
        Call<CustomerResponse> call = customerApiService.getCustomerRecords();
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                List<Customer> customers = response.body().getData();
                recyclerView.setAdapter(new CustomerAdapter(customers, R.layout.list_item,getApplicationContext()));
                Log.d(TAG,"Number of customer records:" + customers.size());
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
