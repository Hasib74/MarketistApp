package com.example.dcl.dailymarketlist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.Model.Check;
import com.example.dcl.dailymarketlist.Model.User;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Utils.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeVerification extends AppCompatActivity {

    public String number;
    EditText editTextCode;
    private String mVerificationId;
    private FirebaseAuth mAuth;
    private DailyMarketApi mServer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verification);

        editTextCode=findViewById(R.id.verify_code);
        Button confromButton=findViewById(R.id.confrom_code);
        mServer= Common.getApi();
        mAuth = FirebaseAuth.getInstance();
        number=getIntent().getStringExtra("number");
        sendVerificationCode(number);


        confromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }

                verifyVerificationCode(code);

            }
        });

    }



    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                editTextCode.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(CodeVerification.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }

    };
    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(CodeVerification.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Common.phone_number=number;
                            User user =new User(number,getIntent().getStringExtra("name"),getIntent().getStringExtra("password"));
                           FirebaseDatabase.getInstance().getReference("User").child(number.substring(2)).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   Toast.makeText(CodeVerification.this, "Added to the Firebase database", Toast.LENGTH_SHORT).show();
                               }
                           });

                           mServer.create_table(number).enqueue(new Callback<Check>() {
                               @Override
                               public void onResponse(Call<Check> call, Response<Check> response) {
                                  Check check=response.body();
                                  if (check.getResult().equals("ok")){
                                     // startActivity(new Intent(CodeVerification.this,Home.class));
                                      Toast.makeText(CodeVerification.this,"Successfully",Toast.LENGTH_LONG).show();
                                  }else {
                                      startActivity(new Intent(CodeVerification.this,Home.class));
                                  }
                               }

                               @Override
                               public void onFailure(Call<Check> call, Throwable t) {
                                   Toast.makeText(CodeVerification.this, "Error :"+t.getMessage(), Toast.LENGTH_SHORT).show();

                               }
                           });

                            Intent intent=new Intent(CodeVerification.this,Home.class);
                            intent.putExtra("number",getIntent().getStringExtra("number"));
                            startActivity(intent);

                        } else {
                            //verification unsuccessful.. display an error message
                            String message = "Somthing is wrong, we will fix it soon...";
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }
                            Toast.makeText(CodeVerification.this,""+message,Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }





    }



