package com.example.wazinsure;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wazinsure.model.Customer;
import com.example.wazinsure.model.CustomerResponse;
import com.example.wazinsure.remote.APIUtils;
import com.example.wazinsure.service.CustomerApiService;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public class Delete extends AppCompatActivity {


    CustomerApiService customerApiService;

    @BindView(R.id.delete) Button mDelete;
    @BindView(R.id.customer) EditText mCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        ButterKnife.bind(this);

        customerApiService = APIUtils.getCustomerApiService();

        final String customer = "34";


        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(Integer.parseInt(customer));
            }

        });

    }

    public void deleteUser(int id) {
        retrofit2.Call<CustomerResponse> call = customerApiService.delete(id);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(retrofit2.Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Delete.this, "Deleted",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Log.e("TAG", t.toString());
            }
        });
    }
}


