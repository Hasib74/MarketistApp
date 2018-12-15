package com.example.dcl.dailymarketlist.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Model.Member;
import com.example.dcl.dailymarketlist.R;

import java.util.List;

public class MemberAdepter extends RecyclerView.Adapter<MemberAdepter.MemberViewHolder> {
    Context context;
    List<Member> memberList;

    public MemberAdepter(Context context, List<Member> memberList) {
        this.context = context;
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.member_calss_design,null);
        return new MemberViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder memberViewHolder, int i) {
         memberViewHolder.number.setText(memberList.get(i).getNumber());
         Toast.makeText(context, ""+memberList.get(i).getNumber(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.number);
        }
    }
}
