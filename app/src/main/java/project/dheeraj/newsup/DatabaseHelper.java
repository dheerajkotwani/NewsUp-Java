package project.dheeraj.newsup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;

import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "SavedData.db";
    private static final String TABLE_NAME = "SavedData";
    public static final String column1 = "ID";
    public static final String column2 = "TITLE";
    public static final String column3 = "DESCRIPTION";
    public static final String column4 = "CONTENT";
    public static final String column5 = "IMAGE";



    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DESCRIPTION TEXT,CONTENT TEXT,IMAGE TEXT)";
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String item1,String item2,String item3,String item4) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column2, item1);
        contentValues.put(column3, item2);
//        contentValues.put(column4,"hello");
        contentValues.put(column4, item3);
        contentValues.put(column5, item4);

        Log.d(TAG, "addDATA : ADDING" + item1 + "\n" + item2 + "\n" + item3 + "\nto " + TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }
        public Cursor getData(SQLiteDatabase db){
           String[] projection = {column1,column2,column3,column4,column5};
           Cursor cursor = db.query(TABLE_NAME,projection,null,null,null,null,null);
           return cursor;
    }

    public ArrayList<LayoutItem> showData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = this.getData(db);

        String item01,item02,item03,item04;

        ArrayList<LayoutItem> layoutItems = new ArrayList<>();


        while(cursor.moveToNext()){
            item01 = cursor.getString(cursor.getColumnIndex(column2));
            item02 = cursor.getString(cursor.getColumnIndex(column3));
            item03 = cursor.getString(cursor.getColumnIndex(column4));
            item04 = cursor.getString(cursor.getColumnIndex(column5));
            layoutItems.add(new LayoutItem(null,item01,item02,item03,item04));
        }

        cursor.close();
        return layoutItems;
    }

}
