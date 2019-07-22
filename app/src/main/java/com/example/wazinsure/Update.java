package com.example.wazinsure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wazinsure.model.Customer;
import com.example.wazinsure.model.CustomerResponse;
import com.example.wazinsure.remote.APIUtils;
import com.example.wazinsure.service.CustomerApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update extends AppCompatActivity {

    CustomerApiService customerApiService;

    @BindView(R.id.delete) Button mUpdate;
    @BindView(R.id.customer) EditText mCustomer;
    @BindView(R.id.name) EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        customerApiService = APIUtils.getCustomerApiService();

        final String customer = "5";
        final String  name = "mac";


        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void updateUser(int id, CustomerResponse u) {
        retrofit2.Call<CustomerResponse> call = customerApiService.update(id,u);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(retrofit2.Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Update.this, "Deleted",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Log.e("TAG", t.toString());
            }
        });
    }
}

