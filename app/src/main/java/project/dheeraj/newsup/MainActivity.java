package project.dheeraj.newsup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import project.dheeraj.newsup.Adapter.OnItemClickListner;
import project.dheeraj.newsup.Api.JsonPplaceHolderApi;
import project.dheeraj.newsup.Model.Articles.articles;
import project.dheeraj.newsup.Model.Feed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnItemClickListner{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Button mButton;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Context context = this;

    private String baseUrl = "https://newsapi.org/v2/";
    private JsonPplaceHolderApi jsonPlaceHolderApi;
    private TextView text;
    private ImageView imageView;
    private LayoutItem layoutItems;

    private MenuItem saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mButton = findViewById(R.id.button);

//       saved = findViewById(R.id.saved);
//       saved.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//           @Override
//           public boolean onMenuItemClick(MenuItem menuItem) {
//               Intent intent = new Intent(MainActivity.this,saved_data.class);
//               startActivity(intent);
//               return true;
//           }
//
//       });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, news_clicked.class);
                startActivity(i);
            }
        });




        getSupportActionBar().setElevation(0);

        text  = findViewById(R.id.text);

        text.setText("");


//Using Retrofit Declaration
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//Using Json Api Call
        JsonPplaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPplaceHolderApi.class);
        Call<Feed> call = jsonPlaceHolderApi.getData();
//        Call<Feed> call = jsonPlaceHolderApi.getData();

        call.enqueue(new Callback<Feed>() {
            ArrayList<LayoutItem> layoutItems = new ArrayList<>();
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if(!response.isSuccessful()){
                    text.setText("Code: "+response.code());
                    return;
                }
                ArrayList<articles> articlesArrayList = response.body().getArticles();

                for(int i=0; i< articlesArrayList.size();i++)
                {

//                    String imageView;
                    String content = "";
                    content +="Title: "+ articlesArrayList.get(i).getTitle()+"\n";
                    content +="Description: "+articlesArrayList.get(i).getDescription()+"\n";
                    content +="Content: "+articlesArrayList.get(i).getContent()+"\n\n";


                    layoutItems.add(new LayoutItem(articlesArrayList.get(i).getUrlToImage(),articlesArrayList.get(i).getTitle(),articlesArrayList.get(i).getDescription(), articlesArrayList.get(i).getContent(),articlesArrayList.get(i).getUrlToImage()));

                    // text.append(content);


                }
//Sending Data to Recycler View
                 final int position=0;
                recyclerView = findViewById(R.id.recyclerView);

                layoutManager = new LinearLayoutManager(context);
                mAdapter = new Adapter(layoutItems,MainActivity.this);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LayoutItem layoutItem = layoutItems.get(position);
                        Intent intent = new Intent(MainActivity.this, news_clicked.class);
                        Toast.makeText(MainActivity.this,"Hello"+position,Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                });




            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                text.setText(t.getMessage());
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.side_menu,menu);
        return true;
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemClick(int position) {
    }

    @Override
    public boolean onOptionItemsSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.saved:
                Toast.makeText(context,"Saved response",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, saved_data.class);
                startActivity(intent);
                break;
            case R.id.about:
                // another startActivity, this is for item with id "menu_item2"
                break;
            default:
                Toast.makeText(context,"No response",Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }

        return true;
    }



}
