package com.example.dcl.dailymarketlist.Adepter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.AddMarketList.AddMarketList;
import com.example.dcl.dailymarketlist.Database.MarketListDB;
import com.example.dcl.dailymarketlist.Fragment.OwnMarket;
import com.example.dcl.dailymarketlist.Fragment.OwnMarketFragments.OwnCompleted;
import com.example.dcl.dailymarketlist.HomePlate.Fragments.Confirm;
import com.example.dcl.dailymarketlist.HomePlate.MainPage;
import com.example.dcl.dailymarketlist.Market;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.ShowMenuList;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class OwnBazarListAdepter extends RecyclerView.Adapter<OwnBazarListAdepter.OwnBazarListViewHolder> {
    public Context context;
    public List<com.example.dcl.dailymarketlist.Model.Menu> menuList=new ArrayList<>();
    MarketListDB marketListDB;

    public OwnBazarListAdepter(Context context, List<com.example.dcl.dailymarketlist.Model.Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public OwnBazarListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_list_design,null);
        marketListDB=new MarketListDB(context);
        return new OwnBazarListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OwnBazarListViewHolder ownBazarListViewHolder, final int i) {

        ownBazarListViewHolder.menu_name.setText(menuList.get(i).getMenu_name());
        ownBazarListViewHolder.menu_quntity.setText(menuList.get(i).getMenu_quantity());

        if (menuList.get(i).getPrice()  == null){
            ownBazarListViewHolder.save.setVisibility(View.VISIBLE);
            ownBazarListViewHolder.menu_price.setVisibility(View.VISIBLE);
        }else {
            ownBazarListViewHolder.confrom.setVisibility(View.VISIBLE);
            ownBazarListViewHolder.priceText.setVisibility(View.VISIBLE);
            ownBazarListViewHolder.priceText.setText(menuList.get(i).getPrice());

        }


        ownBazarListViewHolder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if (!ownBazarListViewHolder.menu_price.getText().toString().isEmpty()){
                      ownBazarListViewHolder.confrom.setVisibility(View.VISIBLE);
                      ownBazarListViewHolder.priceText.setVisibility(View.VISIBLE);
                      ownBazarListViewHolder.priceText.setText(ownBazarListViewHolder.menu_price.getText().toString());
                      ownBazarListViewHolder.menu_price.setVisibility(View.GONE);
                      ownBazarListViewHolder.save.setVisibility(View.GONE);
                      marketListDB.InsertPrice(ownBazarListViewHolder.menu_price.getText().toString(),menuList.get(i).getInfo_id(),menuList.get(i).getUnic_id());


                      List<String> total= marketListDB.count(menuList.get(i).getInfo_id());

                     if (Integer.parseInt(total.get(0))==Integer.parseInt(total.get(1))){

                         marketListDB.change_Complete_Status(menuList.get(i).getInfo_id());
                         AlertDialog.Builder alrt=new AlertDialog.Builder(context);
                         alrt.setTitle("Market Completed");
                         alrt.setMessage("Please Press Ok button to go to the Completed List Activity");
                         alrt.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.dismiss();
                                 Intent in=new Intent(context, MainPage.class);
                                 in.putExtra("complete","list");

                                 context.startActivity(in);
                             }
                         });
                         alrt.setPositiveButton("Go", new DialogInterface.OnClickListener() {



                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 Common.value=1;

                               // Fragment fragment=new Confirm();

                                 Intent in=new Intent(context, MainPage.class);
                                 in.putExtra("complete","confirm");

                                 context.startActivity(in);

                             }
                         });

                         alrt.show();
                     }


                  }else {
                       Toast.makeText(context, "Price Empty", Toast.LENGTH_SHORT).show();
                  }
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class OwnBazarListViewHolder extends RecyclerView.ViewHolder {
        TextView menu_name,menu_quntity,priceText;
        EditText menu_price;
        ImageView save,confrom;
        public OwnBazarListViewHolder(@NonNull View itemView) {
            super(itemView);
            menu_name=itemView.findViewById(R.id.menu_name);
            menu_quntity=itemView.findViewById(R.id.quntity);

            save=itemView.findViewById(R.id.save_button);
            confrom=itemView.findViewById(R.id.confrom);
            priceText=itemView.findViewById(R.id.priceTxt);
            menu_price=itemView.findViewById(R.id.editprice);
        }
    }
}
