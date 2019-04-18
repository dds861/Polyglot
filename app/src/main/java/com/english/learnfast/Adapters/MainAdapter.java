package com.english.learnfast.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.english.learnfast.Models.LessonMain;
import com.english.learnfast.Presenter.PresenterDb;
import com.english.learnfast.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context context;
    private List<LessonMain> list = new ArrayList<>();
    private PresenterDb presenterDb;

    public MainAdapter(Context context, List<LessonMain> productList) {
        this.context = context;
        this.list = productList;
        this.presenterDb = new PresenterDb();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LessonMain lesson = list.get(position);

        holder.mNumberTv.setText(String.valueOf(lesson.getNumber()));
        holder.mDescriptionTv.setText(String.valueOf(lesson.getDescription()));


        if (position == 0) {
            holder.mItemsRecyclerLl.setBackgroundResource(R.drawable.borders_green);
            holder.mRateIv.setImageResource(R.drawable.item_check);
        } else if (presenterDb.getPassRatingByLessonId(position)) {
            holder.mItemsRecyclerLl.setBackgroundResource(R.drawable.borders_green);
            holder.mRateIv.setImageResource(R.drawable.item_check);
        } else {
            holder.mItemsRecyclerLl.setBackgroundResource(R.drawable.borders_dark_grey);
            holder.mRateIv.setImageResource(R.drawable.item_lock);

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNumberTv;
        private TextView mDescriptionTv;
        private ImageView mRateIv;
        private LinearLayout mItemsRecyclerLl;

        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);

        }

        private void initView(@NonNull final View itemView) {
            mNumberTv = (TextView) itemView.findViewById(R.id.tv_number);
            mDescriptionTv = (TextView) itemView.findViewById(R.id.tv_about);
            mRateIv = (ImageView) itemView.findViewById(R.id.iv_rate);
            mItemsRecyclerLl = (LinearLayout) itemView.findViewById(R.id.ll_items_recycler);
        }
    }
}