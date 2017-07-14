package com.example.android.boostcampWeek02Miniproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by samsung on 2017-07-11.
 */


class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<CardItem> item;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Context context;

    public CustomAdapter(Context context, ArrayList<CardItem> item) {
        this.context = context;
        this.item = item;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.card_item_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new CustomViewHolder(view);
    }

    //Glide를 사용해서 image불러오기
    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.title.setText(item.get(position).getTitle());
        Glide.with(context).load(item.get(position).getImage()).into(holder.iv);
        holder.content.setText(item.get(position).getContent());
        holder.cb.setChecked(item.get(position).getIsClicked());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_imageView)        ImageView iv;
        @BindView(R.id.cv_title)        TextView title;
        @BindView(R.id.cv_content)        TextView content;
        @BindView(R.id.cv_checkBox)        CheckBox cb;

        public CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //checkbox 클릭시 firebase data 갱신
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.get(getAdapterPosition()).setIsClicked(isChecked);
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("cv_items");
                    myRef.setValue(item);
                }
            });
        }
    }

    //tablayout 클릭시 호출되는 sorting함수
    public void sort(int sortType) {
        Collections.sort(item, new CustomComparator(sortType));
        notifyDataSetChanged();
    }

    //fireabase 갱신시 호출되는 함수
    public void updateItem(ArrayList<CardItem> item) {
        this.item = item;
        notifyDataSetChanged();
    }
}
