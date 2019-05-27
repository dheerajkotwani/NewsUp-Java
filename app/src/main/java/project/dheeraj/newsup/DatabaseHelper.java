package project.dheeraj.newsup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "SavedData.db";
    private static final String TABLE_NAME = "SavedData";
    private static final String column1 = "ID";
    private static final String column2 = "TITLE";
    private static final String column3 = "DESCRIPTION";
    private static final String column4 = "CONTENT";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DESCRIPTION TEXT,CONTENT TEXT)";
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String item1,String item2,String item3){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column2,item1);
        contentValues.put(column3,item2);
//        contentValues.put(column4,"hello");
        contentValues.put(column4,item3);

        Log.d(TAG, "addDATA : ADDING"+ item1+"\n"+item2+"\n"+item3+"\nto "+TABLE_NAME);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }
        else
            return true;
    }
}
