package project.dheeraj.newsup.Menu_class;

import android.content.Context;
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

public class saved_data extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Context context = this;

//    private DatabaseHelper mDatabseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);

        mSwipeRefreshLayout = findViewById(R.id.swipe_container_savedData);
        mRecyclerView = findViewById(R.id.recyclerview_savedData);

//        mDatabseHelper = new DatabaseHelper(context);

        ArrayList<LayoutItem> layoutItems = new ArrayList<>();


        for (int i=0; i<layoutItems.size(); i++) {
//        layoutItems.add(new LayoutItem(articlesArrayList.get(i).getUrlToImage(), articlesArrayList.get(i).getTitle(), articlesArrayList.get(i).getDescription(), articlesArrayList.get(i).getContent(), articlesArrayList.get(i).getUrlToImage()));
            mSwipeRefreshLayout.setRefreshing(false);
        }


        layoutManager = new LinearLayoutManager(context);
        mAdapter = new Adapter(layoutItems, saved_data.this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }
}
