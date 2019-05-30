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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);

        mRecyclerView = findViewById(R.id.recyclerview_savedData);

        mDatabseHelper = new DatabaseHelper(this);
        

        layoutManager = new LinearLayoutManager(saved_data.this);
        layoutItems = mDatabseHelper.showData();
        mAdapter = new Adapter(layoutItems, saved_data.this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }
}
