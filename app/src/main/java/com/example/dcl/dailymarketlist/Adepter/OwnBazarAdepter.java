package com.example.dcl.dailymarketlist.Adepter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Interface.OwnBazarClick;
import com.example.dcl.dailymarketlist.Model.Member;
import com.example.dcl.dailymarketlist.Model.MemberNumber;
import com.example.dcl.dailymarketlist.Model.OwnBazar;
import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.ShowMenuList;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.List;

public class OwnBazarAdepter extends RecyclerView.Adapter<OwnBazarAdepter.OwnBazarViewHolder>{
    public Context context;
    public List<OwnBazar> ownBazarList;




    public OwnBazarAdepter(Context context, List<OwnBazar> ownBazarList) {
        this.context = context;
        this.ownBazarList = ownBazarList;
    }

    @NonNull
    @Override
    public OwnBazarAdepter.OwnBazarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.own_bazar_list_design,null);
        return new OwnBazarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OwnBazarAdepter.OwnBazarViewHolder ownBazarViewHolder, final int i) {
        ownBazarViewHolder.date.setText(ownBazarList.get(i).getDate());
        ownBazarViewHolder.time.setText(ownBazarList.get(i).getTime());
        ownBazarViewHolder.confrom_quantity.setText(ownBazarList.get(i).getTotal());
        ownBazarViewHolder.shaare_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alrt=new AlertDialog.Builder(context);
                alrt.setMessage("Press the list item to send your bazar to another people");
                View dialog_view=LayoutInflater.from(context).inflate(R.layout.members_dialog,null);
                alrt.setView(dialog_view);
                RecyclerView recyclerView=dialog_view.findViewById(R.id.members_recycular);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

                List<MemberNumber> member_list= Common.read_all_members();
                DialogMembersAdepter dialogMembersAdepter=new DialogMembersAdepter(context,member_list,ownBazarViewHolder.date.getText().toString(),ownBazarViewHolder.time.getText().toString(),ownBazarList.get(i).getId());

                recyclerView.setAdapter(dialogMembersAdepter);

                alrt.show();
            }
        });

        ownBazarViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cliciked", Toast.LENGTH_SHORT).show();

                String id=ownBazarList.get(i).getId();

                Toast.makeText(context, "Clicked item"+i, Toast.LENGTH_SHORT).show();
                Intent in=new Intent(context, ShowMenuList.class);
                in.putExtra("id",id);
                context.startActivity(in);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ownBazarList.size();
    }

    public class OwnBazarViewHolder extends RecyclerView.ViewHolder  {
        TextView date,time,confrom_quantity;
        ImageView shaare_btn;
        public OwnBazarViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            confrom_quantity=itemView.findViewById(R.id.confrom_qantity);
            shaare_btn=itemView.findViewById(R.id.share_btn);

        }


    }
}
