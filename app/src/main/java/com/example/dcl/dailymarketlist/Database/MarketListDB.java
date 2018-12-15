package com.example.dcl.dailymarketlist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MarketListDB extends SQLiteOpenHelper {

    public static   String DATABASE_NAME="MarketList";
    public   static  int   VERSION=5;
    public static String TABLE_NAME="marketListTable";
    public static String INFO_TABLE="marketInfoTable";

    public static String PHONE_NUMBER="userNumber";
    public static String INSERTED_INTO_MYSQL="inserted_into_mysql";
    public static String MENU_NAME="menuName";
    public static String MENU_QUANTITY="quantity";
    public static String PRICE="prie";
    public static  String TIME="time";
    public static String DATE="date";

    public static  String COMPLETE_STATUS="completeStatus";

    public static String KEY_ID="keyId";
    public static String UNIC_ID="unicId";
    public static String INFO_ID="infoId";
    String id;
    String count1,count2;

   private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement," + PHONE_NUMBER + " TEXT,"
            + DATE +" TEXT,"+ TIME+" TEXT,"+COMPLETE_STATUS +" TEXT,"+INSERTED_INTO_MYSQL+" TEXT)";

    private static String LIST_TABLE = "CREATE TABLE " + INFO_TABLE + "("
            + INFO_ID + " TEXT,"+ UNIC_ID + " integer primary key autoincrement," + MENU_NAME + " TEXT,"
            + MENU_QUANTITY +" TEXT,"+PRICE+" TEXT)";

    public MarketListDB(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(LIST_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String InsertIntoCreateTable(String number,String date,String time){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(PHONE_NUMBER,number);
        cv.put(DATE,date);
        cv.put(TIME,time);
        cv.put(INSERTED_INTO_MYSQL,"0");
        cv.put(COMPLETE_STATUS,"0");
      long value=  db.insert(TABLE_NAME,null,cv);

      if (value!=-1){
          return  "Saved Into the database";
      }else {
          return "Sorry could not saved into the databse";
      }


    }

    public  String  selectLastIdOfCreateTable(){

        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT "+KEY_ID+" FROM "+TABLE_NAME+" WHERE "+KEY_ID+" = (SELECT MAX("+KEY_ID+") FROM "+TABLE_NAME+")";
        Cursor c = db.rawQuery(sql,null);

        while ( c.moveToNext())
        {
             id = c.getString(c.getColumnIndex(KEY_ID));
        }
        return  id;
    }

    public void addListIntoTheListTable(String id,String menu_name,String quantity){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(INFO_ID,id);
        cv.put(MENU_NAME,menu_name);
        cv.put(MENU_QUANTITY,quantity);
        db.insert(INFO_TABLE,null,cv);
        db.close();
    }

    public void restart(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.delete(INFO_TABLE,null,null);
        db.close();
    }

    public Cursor retriveOwnBazar(){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME+" WHERE "+COMPLETE_STATUS+"=0 ";
        Cursor cursor=db.rawQuery(sql,null,null);
        return  cursor;
    }

    public void change_Complete_Status(String key_id){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="UPDATE "+TABLE_NAME+" SET "+COMPLETE_STATUS+"= 1  WHERE "+KEY_ID+"="+key_id+" ";
        db.execSQL(sql);
    }

    public Cursor retriveFullList(String key_id){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT * FROM "+INFO_TABLE+" WHERE "+INFO_ID+" = "+key_id+" ";
        Cursor cursor=db.rawQuery(sql,null,null);

        return  cursor;
    }
    public void InsertPrice(String price,String info_id,String unic_Id){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="UPDATE "+INFO_TABLE+" SET "+PRICE+" = "+price+" WHERE infoId="+info_id+" AND unicId="+unic_Id+"";
        db.execSQL(sql);
    }

    public Cursor retrivePrice(String info_id,String unic_Id){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT "+PRICE+" FROM "+INFO_TABLE+" WHERE infoId="+info_id+" AND unicId="+unic_Id+"";
        Cursor price=db.rawQuery(sql,null,null);

        return  price;
    }


    public List<String>  count(String info_id){

        List<String> total_list=new ArrayList<>();

        SQLiteDatabase  db=this.getReadableDatabase();
        String sql="SELECT COUNT("+INFO_ID+") FROM "+INFO_TABLE+" WHERE "+INFO_ID+" ="+info_id+" ";
        Cursor cursor=db.rawQuery(sql,null,null);
        String sql1="SELECT COUNT("+INFO_ID+") FROM "+INFO_TABLE+" WHERE prie IS NOT NULL AND "+INFO_ID+" ="+info_id+" ";
        Cursor cursor1=db.rawQuery(sql1,null,null);

        cursor.moveToFirst();
        count1= cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));

        cursor1.moveToFirst();
        count2= cursor1.getString(cursor.getColumnIndex(cursor.getColumnName(0)));


       total_list.add(count1);

       total_list.add(count2);

        return  total_list;



    }



    public Cursor CompletedBazarList(){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME+" WHERE "+COMPLETE_STATUS+"=1";
        Cursor cursor=db.rawQuery(sql,null,null);

        return cursor;
    }
    public Cursor CompletedBazarListFORMYSQ(){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME+" WHERE "+COMPLETE_STATUS+"=1 AND "+INSERTED_INTO_MYSQL+"=0";
        Cursor cursor=db.rawQuery(sql,null,null);

        return cursor;
    }

    public  Cursor getAllCompletedMenus(String info_id){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT * FROM "+INFO_TABLE+" WHERE infoId="+info_id+"";
        Cursor cursor=db.rawQuery(sql,null,null);

        return  cursor;
    }

    public void  insertConfromIntoTheOwnBazar(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql=" UPDATE "+TABLE_NAME+" SET "+INSERTED_INTO_MYSQL+"=1 WHERE "+KEY_ID+" = "+id+"";
        db.execSQL(sql);
    }

    public Cursor read_all_completed_data_drom_sqlite_DB(String id){
        SQLiteDatabase db=this.getReadableDatabase();

        String sql="SELECT * FROM "+INFO_TABLE+" WHERE "+INFO_ID+"="+id+" ";

        Cursor cursor=db.rawQuery(sql,null,null);
        return cursor;
    }

    public  void delete_market_list (String key_id){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="DELETE  FROM "+TABLE_NAME+" WHERE "+KEY_ID+"="+key_id+"";
        String sql1="DELETE  FROM "+INFO_TABLE+" WHERE "+INFO_ID+"="+key_id+"";

        db.execSQL(sql);
        db.execSQL(sql1);
    }


   /* public  Cursor getAllCompletedStatusKeyId(){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT keyId FROM "+TABLE_NAME+" WHERE "+COMPLETE_STATUS+"=1";

        Cursor  cursor=db.rawQuery(sql,null,null);

        return  cursor;
    }*/

}
