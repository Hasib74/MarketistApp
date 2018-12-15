package com.example.dcl.dailymarketlist.Service;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Model.Token;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.logging.Handler;

public class FirebaseService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        final String refreshToken= FirebaseInstanceId.getInstance().getToken();

       updateTokenServer(refreshToken);
    }
    private void updateTokenServer(String refreshToken) {
//        Toast.makeText(this, "User found"+refreshToken, Toast.LENGTH_SHORT).show();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Token");
        Token token=new Token(refreshToken);

      /*  if (FirebaseAuth.getInstance().getCurrentUser().getUid()!=null){
            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(token);
        }else{
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }*/

    }
}
