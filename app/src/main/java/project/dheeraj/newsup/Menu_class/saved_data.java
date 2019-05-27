package project.dheeraj.newsup.Menu_class;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import project.dheeraj.newsup.Adapter;
import project.dheeraj.newsup.DatabaseHelper;
import project.dheeraj.newsup.LayoutItem;
import project.dheeraj.newsup.MainActivity;
import project.dheeraj.newsup.R;

import static android.os.Build.ID;

public class saved_data extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Context context = this;

    private DatabaseHelper mDatabseHelper;
    private Cursor cursor;

    private ArrayList<LayoutItem> layoutItems = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);

        mSwipeRefreshLayout = findViewById(R.id.swipe_container_savedData);
        mRecyclerView = findViewById(R.id.recyclerview_savedData);

        mDatabseHelper = new DatabaseHelper(saved_data.this);
        
//        mDatabseHelper.showData();

//

//
//
//        for (int i=0; i<layoutItems.size(); i++) {
//        layoutItems.add(new LayoutItem(null,"title","description","content","null"));
//            mSwipeRefreshLayout.setRefreshing(false);
//        }

//        SQLiteDatabase db = DatabaseHelper.getReadableDatabse();
//        layoutItems.add(mDatabseHelper.showData());


//        layoutItems.add(new LayoutItem(null,"title","description","content","null"));

//        showData();
        layoutManager = new LinearLayoutManager(saved_data.this);
        mAdapter = new Adapter(layoutItems, saved_data.this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }

    public void showData(){

        SQLiteDatabase db = mDatabseHelper.getWritableDatabase();
        Cursor cursor = mDatabseHelper.getData(db);

        String id,item01,item02,item03;


        while(cursor.moveToFirst()){
          //  id = cursor.getString(cursor.getColumnIndex(ID));
            item01 = cursor.getString(1);
            item02 = cursor.getString(2);
            item03 = cursor.getString(3);

            layoutItems.add(new LayoutItem(null,item01,item02,item03,null));
        }
    }

}
