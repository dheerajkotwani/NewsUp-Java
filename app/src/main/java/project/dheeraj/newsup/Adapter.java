package project.dheeraj.newsup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private Context context;
    private ArrayList<LayoutItem> layoutItems;

    private OnItemClickListner mListner;

    public interface OnItemClickListner{
        void onItemClick(int position);

        boolean onOptionItemsSelected(MenuItem item);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        mListner = listner;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView title;
        public TextView description;
        public TextView content;
        public TextView imageId;
        public ImageView back_color;

        OnItemClickListner onNoteClickListner;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.newsImage);
            title = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.newsDescription);
            content = itemView.findViewById(R.id.newsContent);
            imageId = itemView.findViewById(R.id.imgId);
            back_color = itemView.findViewById(R.id.back_color);

            Random rand = new Random();
            int[] random ={R.color.blue,R.color.red,R.color.colorAccent,R.color.green,R.color.yellow};



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Toast.makeText(context,"Loading",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(context, news_clicked.class);
                    intent.putExtra("title",title.getText());
                    intent.putExtra("description",description.getText());
                    intent.putExtra("content",content.getText());
                    intent.putExtra("image",imageId.getText());

                    view.getContext().startActivity(intent);

                    if (mListner != null) {

                        if (position != RecyclerView.NO_POSITION) {
                            mListner.onItemClick(position);
                        }
                    }
                }

            });
        }
    }

    public Adapter(ArrayList<LayoutItem> list,Context context){
        layoutItems = list;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_frame,viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.back_color.setBackgroundColor(color);


        LayoutItem currentItem = layoutItems.get(i);
//        holder.imageView.setImageResource(currentItem.getNewsImage());
        holder.title.setText(currentItem.getNewsTitle());
        holder.description.setText(currentItem.getNewsDescription());
        holder.content.setText(currentItem.getNewsContent());
        holder.imageId.setText(currentItem.getNewsImage());
        Picasso.with(context)
                .load(currentItem.getNewsImage())
                .into(holder.imageView);
//        holder.back_color.setBackgroundColor();

    }

    @Override
    public int getItemCount() {
        return layoutItems.size();
    }




}
