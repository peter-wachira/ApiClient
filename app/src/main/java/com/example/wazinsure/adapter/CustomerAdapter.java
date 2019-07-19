package com.example.wazinsure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wazinsure.R;
import com.example.wazinsure.model.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private List<Customer> customers;
    private int rowLayout;
    private Context context;

    public CustomerAdapter(List<Customer> customers, int rowLayout, Context context){
        this.customers = customers;
        this.rowLayout = rowLayout;
        this.context = context;
    }
    public static class  CustomerViewHolder extends RecyclerView.ViewHolder{
        LinearLayout customersLayout;
        TextView  customer;
        TextView firstName;

        public CustomerViewHolder(View v){
            super(v);

            customersLayout = (LinearLayout) v.findViewById(R.id.customers_layout);
            customer = (TextView) v.findViewById(R.id.customer);
            firstName = (TextView) v.findViewById(R.id.firstName);
        }
    }
    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new CustomerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CustomerViewHolder holder, final int position){
        holder.customer.setText(customers.get(position).getLastName());
        holder.firstName.setText(customers.get(position).getFirstName());
    }
    @Override
    public int getItemCount(){
        return customers.size();
    }
}
