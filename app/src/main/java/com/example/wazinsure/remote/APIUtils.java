package com.example.wazinsure.remote;

import com.example.wazinsure.service.CustomerApiService;

public class APIUtils {
    private APIUtils(){
    };

    public static final String API_URL = "https://demo.wazinsure.com:4443/";

    public static CustomerApiService getCustomerApiService(){
        return RetrofitClient.getClient(API_URL).create(CustomerApiService.class);
    }
}
