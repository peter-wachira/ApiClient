package com.example.wazinsure.service;

import com.example.wazinsure.model.CustomerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CustomerApiService {
    @GET("api/customers")
    Call<CustomerResponse> getCustomerRecords();

    @DELETE("api/customers/{id}")
    Call<CustomerResponse> delete(@Path("id") int id);

    @PUT("api/customers/{id")
    Call<CustomerResponse> update(@Path("id") int id, @Body CustomerResponse customerResponse);
}
