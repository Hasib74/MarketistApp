package com.example.dcl.dailymarketlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Adepter.MemberAdepter;
import com.example.dcl.dailymarketlist.Model.Member;
import com.example.dcl.dailymarketlist.Utils.Common;

import java.util.ArrayList;
import java.util.List;

public class Members extends AppCompatActivity {

    RecyclerView recyclerView;
    MemberAdepter memberAdepter;
    List<Member> memberList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        recyclerView=(RecyclerView)findViewById(R.id.member_recycular);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        read_all_members();
        Log.i("MEMBER_LIST",memberList.toString());
        memberAdepter=new MemberAdepter(this, memberList);

        recyclerView.setAdapter(memberAdepter);


    }

    private void read_all_members() {
        memberList.clear();
        for (int i = 0; i < Common.userList.size(); i++) {
            for (int j = 0; j <Common.contract_number_list.size(); j++) {
                String registerNumber = Common.userList.get(i).getNumber();
                if (registerNumber.equals(Common.contract_number_list.get(j))) {
                    Member member=new Member(Common.contract_number_list.get(j));
                    memberList.add(member);
                }
            }
        }
    }
}
