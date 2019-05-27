package project.dheeraj.newsup;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import project.dheeraj.newsup.R;

public class news_clicked extends AppCompatActivity {

    private ImageButton buttonShare;
    private ImageButton buttonFav;
    private ImageButton buttonFavBorder;
    private ImageButton buttonHome;
    private TextView mNewsName;

    private TextView mtitle, mdescription, mcontent;
    private ImageView mimage;

    private Context context;

    private DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_clicked);

        getSupportActionBar().setElevation(0);

        buttonHome = findViewById(R.id.home_buuton);
        buttonShare = findViewById(R.id.button_share);
        buttonFav = findViewById(R.id.button_fav);
        buttonFavBorder = findViewById(R.id.button_fav_border);
        mNewsName = findViewById(R.id.news_name);

        mtitle = findViewById(R.id.news_title);
        mdescription = findViewById(R.id.news_description);
        mcontent = findViewById(R.id.news_content);
        mimage = findViewById(R.id.newsImage);

        Intent intent = getIntent();
        mtitle.setText(intent.getStringExtra("title"));
        mdescription.setText(intent.getStringExtra("description"));
        mcontent.setText(intent.getStringExtra("content"));

        String imgRes= intent.getStringExtra("image");

        mDatabaseHelper = new DatabaseHelper(news_clicked.this);

//        this.context = context;

//        mNewsName.setText(intent.getStringExtra("image"));
        Picasso.with(this)
                .load(imgRes)
                .into(mimage);



        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        mNewsName.setBackgroundColor(color);


        buttonFavBorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleData =mtitle.getText().toString();
                String descriptionData = mdescription.getText().toString();
                String contentData = mcontent.getText().toString();

                boolean insertData = mDatabaseHelper.addData(titleData,descriptionData, contentData);

                Toast.makeText(news_clicked.this,"Marked Favourite",Toast.LENGTH_SHORT).show();
                view.setVisibility(View.GONE);
                buttonFav.setVisibility(View.VISIBLE);

                if(insertData == true){
                    Toast.makeText(news_clicked.this,"Data Succesfully Added",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(news_clicked.this,"Something went Wrong",Toast.LENGTH_SHORT).show();





            }
        });
        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
                buttonFavBorder.setVisibility(View.VISIBLE);
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(news_clicked.this,"Home",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(news_clicked.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(news_clicked.this,"Share",Toast.LENGTH_SHORT).show();
            }
        });

    //    AddData();
    }
//    public void addData(String entry1, String entry2, String entry3){
//        boolean insertData = mDatabaseHelper.addData(entry1, entry2, entry3);
//
//        if(insertData){
//            Toast.makeText(context,"Data Succesfully Added",Toast.LENGTH_SHORT).show();
//        }
//        else Toast.makeText(news_clicked.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
//    }
    public void AddData(){
        buttonFavBorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleData =mtitle.getText().toString();
                String descriptionData = mdescription.getText().toString();
                String contentData = mcontent.getText().toString();

                boolean insertData = mDatabaseHelper.addData(titleData,descriptionData, contentData);

                Toast.makeText(news_clicked.this,"Marked Favourite",Toast.LENGTH_SHORT).show();
                view.setVisibility(View.GONE);
                buttonFav.setVisibility(View.VISIBLE);

                if(insertData == true){
            Toast.makeText(news_clicked.this,"Data Succesfully Added",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(news_clicked.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
