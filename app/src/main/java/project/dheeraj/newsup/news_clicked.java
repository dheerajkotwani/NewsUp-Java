package project.dheeraj.newsup;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class news_clicked extends AppCompatActivity {

    private ImageButton buttonFav;
    private ImageButton buttonFavBorder;
    private TextView mNewsName;

    private TextView mtitle, mdescription, mcontent;
    private ImageView mimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_clicked);

        getSupportActionBar().setElevation(0);

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
                view.setVisibility(View.GONE);
                buttonFav.setVisibility(View.VISIBLE);
            }
        });
        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
                buttonFavBorder.setVisibility(View.VISIBLE);
            }
        });

    }
}
