package com.example.dcl.dailymarketlist.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dcl.dailymarketlist.Model.CompletedBaxarListMenuModel;
import com.example.dcl.dailymarketlist.R;

import java.util.ArrayList;
import java.util.List;

public class CompletedBazarListMenuAdepter extends RecyclerView.Adapter<CompletedBazarListMenuAdepter.CompletedBazarListMenusViewHolder> {
    Context context;
    List<CompletedBaxarListMenuModel> listMenuModelList=new ArrayList<>();

    public CompletedBazarListMenuAdepter(Context context, List<CompletedBaxarListMenuModel> listMenuModelList) {
        this.context = context;
        this.listMenuModelList = listMenuModelList;
    }

    @NonNull
    @Override
    public CompletedBazarListMenusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.completed_list_menus_design,null);

        return new CompletedBazarListMenusViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedBazarListMenusViewHolder completedBazarListMenusViewHolder, int i) {

        completedBazarListMenusViewHolder.menu_name.setText(listMenuModelList.get(i).getMenuName());
        completedBazarListMenusViewHolder.menu_quantity.setText(listMenuModelList.get(i).getQuantity());
        completedBazarListMenusViewHolder.menu_price.setText(listMenuModelList.get(i).getPrice());

    }

    @Override
    public int getItemCount() {
        return listMenuModelList.size();
    }

    public class CompletedBazarListMenusViewHolder extends RecyclerView.ViewHolder {
        TextView menu_name,menu_quantity,menu_price;
        public CompletedBazarListMenusViewHolder(@NonNull View itemView) {
            super(itemView);

            menu_name=itemView.findViewById(R.id.menu_name);
            menu_quantity=itemView.findViewById(R.id.menu_quantity);
            menu_price=itemView.findViewById(R.id.menu_price);
        }
    }
}
