package com.example.dcl.dailymarketlist;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.AddMarketList.AddMarketList;
import com.example.dcl.dailymarketlist.Database.MarketListDB;
import com.example.dcl.dailymarketlist.HomePlate.MainPage;
import com.example.dcl.dailymarketlist.Map.NearByMarket;
import com.example.dcl.dailymarketlist.Model.CompleteMarket;
import com.example.dcl.dailymarketlist.Model.CompleteMySql;
import com.example.dcl.dailymarketlist.Model.CompletedBaxarListMenuModel;
import com.example.dcl.dailymarketlist.Model.Member;
import com.example.dcl.dailymarketlist.Model.Token;
import com.example.dcl.dailymarketlist.Model.User;
import com.example.dcl.dailymarketlist.Model.check1;
import com.example.dcl.dailymarketlist.Retrofit.DailyMarketApi;
import com.example.dcl.dailymarketlist.Service.FirebaseService;
import com.example.dcl.dailymarketlist.Utils.Common;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView iv;
    TextView list_members,size_members;
    MarketListDB marketListDB;
    List<String> phone_number_list = new ArrayList<>();
    List<Member> member_list=new ArrayList<>();
    List<User> user_list=new ArrayList<>();
    List<CompleteMarket> completedList=new ArrayList<>();
    List<CompletedBaxarListMenuModel> completedBaxarListMenuModelList=new ArrayList<>();
    List<CompleteMySql> completeMySqlList=new ArrayList<>();
    Common common;

    DailyMarketApi myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iv = findViewById(R.id.imageView);

        myApi=Common.getApi();
        common=new Common(this);

        startService(new Intent(this, FirebaseService.class));

        //put all contract number list into a variable
        readAllContractList();
        Common.contract_number_list=phone_number_list;

        Common.phone_number=getIntent().getStringExtra("number");

        marketListDB = new MarketListDB(this);

        if (common.isNetworkAvailable()){
            setUp_completed_market_list();
            insertCompleteDataIntoTheMysqlDatabase();
            insertIntoMySql();


            //Read Member:=
            read_member_from_firbase_datebase();

            Log.i("MARKET_LIST_DB",completedList.toString());
            Log.i("COMPLETE_MYSQL",completeMySqlList.toString());

        }else {
            Toast.makeText(this, "INTERNET NOT AVILABLE", Toast.LENGTH_SHORT).show();
        }


        Toast.makeText(this, "" + Common.getTime(), Toast.LENGTH_SHORT).show();

        TextView change_image = findViewById(R.id.change_image);
        Button btn_one = findViewById(R.id.one);
        Button btn_two = findViewById(R.id.two);
        Button btn_three = findViewById(R.id.three);
        Button btn_four = findViewById(R.id.four);

        size_members=findViewById(R.id.size_of_my_mebers);
        list_members=findViewById(R.id.list_members);




        Log.i("Phone_numbers", phone_number_list.toString());


        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AddMarketList.class));
                Toast.makeText(getApplicationContext(), "This is own_complete_list", Toast.LENGTH_LONG).show();
            }
        });


        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this, Market.class));
            }
        });
        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marketListDB.restart();
                Toast.makeText(Home.this, "Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(Home.this, MainPage.class));

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        update_tokens();


    }

    private void update_tokens() {
        Token token=new Token(FirebaseInstanceId.getInstance().getToken().toString());
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference tokens=db.getReference("Token");

        tokens.child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()).setValue(token);
       // tokens.setValue(token);

    }

    private void read_member_from_firbase_datebase() {
        FirebaseDatabase.getInstance().getReference("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    User user=ds.getValue(User.class);
                    Log.i("USER_INFO",user.getNumber());
                    user_list.add(user);
                    Common.userList=user_list;
                }


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Home.this, "Error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });
    }

    private void insertCompleteDataIntoTheMysqlDatabase() {
           for (int i=0;i<completedList.size();i++){
               Cursor cursor=marketListDB.read_all_completed_data_drom_sqlite_DB(completedList.get(i).getId());
               if (cursor!=null){

                   while (cursor.moveToNext()){
                      /* String info_id=cursor.getString(cursor.getColumnIndex(marketListDB.INFO_ID));
                       String unic_id=cursor.getString(cursor.getColumnIndex(marketListDB.UNIC_ID));*/
                       String info_id=cursor.getString(cursor.getColumnIndex(marketListDB.INFO_ID));
                       String menuName=cursor.getString(cursor.getColumnIndex(marketListDB.MENU_NAME));
                       String quantity=cursor.getString(cursor.getColumnIndex(marketListDB.MENU_QUANTITY));
                       String price=cursor.getString(cursor.getColumnIndex(marketListDB.PRICE));
                      // String completed_status=cursor.getString(cursor.getColumnIndex(marketListDB.COMPLETE_STATUS));
                     //  String insert_info_mySql=cursor.getString(cursor.getColumnIndex(marketListDB.INSERTED_INTO_MYSQL));
                       CompleteMySql completeMySql=new CompleteMySql(info_id,completedList.get(i).getDate(),completedList.get(i).getTime(),menuName,quantity,price);
                       completeMySqlList.add(completeMySql);
                   }

               }else {
                   Toast.makeText(this, "Cursor is null", Toast.LENGTH_SHORT).show();
               }



           }


    }
    private void insertIntoMySql() {
       completedList.clear();
        for ( int i=0;i<completeMySqlList.size();i++){

            Log.i("Result993",""+getIntent().getStringExtra("number")+","+completeMySqlList.get(i).getDate()+" ,"+completeMySqlList.get(i).getTime()+","+completeMySqlList.get(i).getItemName()+""+completeMySqlList.get(i).getQuantity()+""+completeMySqlList.get(i).getPrice());

            myApi.insertIntoCompletedTable("01727123374",completeMySqlList.get(i).getDate()+" "+completeMySqlList.get(i).getTime(),completeMySqlList.get(i).getItemName(),completeMySqlList.get(i).getQuantity(),completeMySqlList.get(i).getPrice()).enqueue(new Callback<check1>() {
                @Override
                public void onResponse(Call<check1> call, Response<check1> response) {


                }

                @Override
                public void onFailure(Call<check1> call, Throwable t) {
                    Toast.makeText(Home.this, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            marketListDB.insertConfromIntoTheOwnBazar(completeMySqlList.get(i).getId());

        }

    }
    private void setUp_completed_market_list() {
        Cursor cursor=marketListDB.CompletedBazarListFORMYSQ();
        if (cursor!=null){
            while (cursor.moveToNext()){
                String id=cursor.getString(cursor.getColumnIndex(marketListDB.KEY_ID));
                String date=cursor.getString(cursor.getColumnIndex(marketListDB.DATE));
                String time=cursor.getString(cursor.getColumnIndex(marketListDB.TIME));
                String completed_status=cursor.getString(cursor.getColumnIndex(marketListDB.COMPLETE_STATUS));
                String inserted_into_mysql=cursor.getString(cursor.getColumnIndex(marketListDB.INSERTED_INTO_MYSQL));

                Log.i("id",id);

                completedList.add(new CompleteMarket(id,date,time,completed_status,inserted_into_mysql));
                Log.i("completed_list",completedList.toString());
            }
        }else {
            Toast.makeText(this, "cursor is false", Toast.LENGTH_SHORT).show();
            Log.i("error",completedList.toString());

        }


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(Home.this, NearByMarket.class));

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            AuthUI.getInstance()

                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(Home.this, MainActivity.class));
                            finish();
                        }
                    });

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 10 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(bitmap);

        }

    }




    public void readAllContractList() {
        ContentResolver contentResolver;
        contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null , null,null,null);

        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {


                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {
                    Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id ,
                            null, null
                    );

                    while (cursor2.moveToNext())
                    {
                        String phoneNumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String first_check=phoneNumber.substring(0,3);
                        if (first_check.equals("+88"))
                        {
                            String number=phoneNumber.substring(3);
                            phone_number_list.add(number);
                        }
                        else {
                            phone_number_list.add(phoneNumber);
                        }
                    }
                    cursor2.close();
                }

            }
        }
        cursor.close();
        Toast.makeText(this, "" + phone_number_list, Toast.LENGTH_SHORT).show();
    }





    //
        private void retriveMember() {
      /*      Toast.makeText(Home.this, "Ayici", Toast.LENGTH_SHORT).show();
            FirebaseDatabase.getInstance().getReference("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String number = ds.getKey().toString();
                        Log.i("NUMBERS", number);
                        registerNumbers.add(number);
                        Log.i("REGISTATION_NUMBERS", registerNumbers.toString());


                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Home.this, "Error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            Toast.makeText(Home.this, "REGUISTATION NUMBER " + registerNumbers.toString(), Toast.LENGTH_SHORT).show();
            readUserMethod();*/
        }

        private void readUserMethod() {
           /* Toast.makeText(Home.this, " I am okkk ", Toast.LENGTH_SHORT).show();
            Toast.makeText(Home.this, "Size " + registerNumbers.size(), Toast.LENGTH_SHORT).show();
           for (int i = 0; i < registerNumbers.size(); i++) {
               String registerNumber = registerNumbers.get(i);
               String subReg = registerNumber.substring(2);
                Toast.makeText(Home.this, "Sub  " + subReg, Toast.LENGTH_SHORT).show();
                for (int j = 0; i < phone_number_list.size(); j++) {
                    if (subReg == (phone_number_list.get(j))) {
                        Toast.makeText(Home.this, "sb " + subReg + " J " + phone_number_list.get(j), Toast.LENGTH_SHORT).show();
                        countract_members_list.add(phone_number_list.get(j));
                    } else {
                        return;
                    }
                }

                Log.i("MYMEMUBER_NUMBER", countract_members_list.toString());
            }

            Toast.makeText(Home.this, "MY LIST " + countract_members_list.toString(), Toast.LENGTH_SHORT).show();*/
        }
    }


