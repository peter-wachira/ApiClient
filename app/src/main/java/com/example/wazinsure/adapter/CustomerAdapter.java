package com.example.wazinsure.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        CardView customersLayout;
        TextView  customer;
        TextView firstName;
        TextView email;
        TextView location;
        TextView occupation;
        TextView country;
        TextView dob;
        TextView kra;
        TextView phone;
        TextView address;
        TextView code;
        TextView town;
        TextView fullName;
        TextView mobile;
        TextView relation;
        TextView agent;
        TextView id;
        TextView photo;
        TextView userCode;
        TextView customerI;

        public CustomerViewHolder(View v){
            super(v);

            customersLayout = (CardView) v.findViewById(R.id.customers_layout);
            customer = (TextView) v.findViewById(R.id.customer);
            firstName = (TextView) v.findViewById(R.id.firstName);
            email = (TextView) v.findViewById(R.id.email);
            location = (TextView) v.findViewById(R.id.location);
            occupation = (TextView) v.findViewById(R.id.occupation);
            country = (TextView) v.findViewById(R.id.country);
            dob = (TextView) v.findViewById(R.id.dob);
            kra = (TextView) v.findViewById(R.id.kra);
            phone = (TextView) v.findViewById(R.id.phone);
            address = (TextView) v.findViewById(R.id.address);
            code = (TextView) v.findViewById(R.id.code);
            town = (TextView) v.findViewById(R.id.town);
            fullName = (TextView) v.findViewById(R.id.fullName);
            mobile = (TextView) v.findViewById(R.id.mobile);
            relation = (TextView) v.findViewById(R.id.relation);
            agent = (TextView) v.findViewById(R.id.agent);
            id = (TextView) v.findViewById(R.id.id);
            photo = (TextView) v.findViewById(R.id.photo);
            userCode = (TextView) v.findViewById(R.id.userCode);
            customerI = (TextView) v.findViewById(R.id.customerId);
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
        holder.email.setText(customers.get(position).getEmail());
        holder.occupation.setText(customers.get(position).getOccupation());
        holder.location.setText(customers.get(position).getLocation());
        holder.country.setText(customers.get(position).getLastName());
        holder.dob.setText(customers.get(position).getDob());
        holder.kra.setText(customers.get(position).getKraPin());
        holder.phone.setText(customers.get(position).getMobileNo());
        holder.address.setText(customers.get(position).getPostalAddress());
        holder.code.setText(customers.get(position).getPostalCode());
        holder.town.setText(customers.get(position).getTown());
        holder.fullName.setText(customers.get(position).getNokFullname());
        holder.mobile.setText(customers.get(position).getNokMobileno());
        holder.relation.setText(customers.get(position).getNokRelation());
        holder.id.setText(customers.get(position).getIdNo());
    }
    @Override
    public int getItemCount(){
        return customers.size();
    }
}
