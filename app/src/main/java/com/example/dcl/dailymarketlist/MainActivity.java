package com.example.dcl.dailymarketlist;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Utils.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private String contryId;
    private String contryDialCode;
    private EditText name,password;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView calling_code_show = findViewById(R.id.tv_show);
        final EditText set_number = findViewById(R.id.set_phone_number);
        name=findViewById(R.id.register_name);
        password=findViewById(R.id.register_password);
        Button reg_btn = findViewById(R.id.registation_btn);
        firebaseAuth = FirebaseAuth.getInstance();

        TelephonyManager telephonyMngr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        contryId = telephonyMngr.getSimCountryIso().toUpperCase();

        String[] arrContryCode = this.getResources().getStringArray(R.array.DialingCountryCode);

        for (int i = 0; i < arrContryCode.length; i++) {
            String[] arrDial = arrContryCode[i].split(",");
            if (arrDial[1].trim().equals(contryId.trim())) {
                contryDialCode = arrDial[0];
                break;
            }
        }

        calling_code_show.setText(contryDialCode);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                String number;
                number = stringBuilder.append(contryDialCode).append(set_number.getText().toString()).toString();
                Toast.makeText(getApplicationContext(), "" + number, Toast.LENGTH_LONG).show();

               if (!name.getText().toString().isEmpty() || !password.getText().toString().isEmpty() || !set_number.getText().toString().isEmpty()){
                   Intent in = new Intent(MainActivity.this, CodeVerification.class);
                   in.putExtra("name",name.getText().toString());
                   in.putExtra("password",password.getText().toString());
                   in.putExtra("number", number);
                   startActivity(in);
               }else {
                   Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
               }



            }
        });

    }



    @Override
    protected void onStart()
    {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null)
        {

            Common.phone_number=firebaseAuth.getCurrentUser().getPhoneNumber();

           startActivity(new Intent(MainActivity.this,Home.class));

        }

    }
}