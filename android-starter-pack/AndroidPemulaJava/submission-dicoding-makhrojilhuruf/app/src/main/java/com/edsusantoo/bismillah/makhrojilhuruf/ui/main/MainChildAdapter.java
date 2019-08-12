package com.edsusantoo.bismillah.makhrojilhuruf.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edsusantoo.bismillah.makhrojilhuruf.R;
import com.edsusantoo.bismillah.makhrojilhuruf.model.DataItem;
import com.edsusantoo.bismillah.makhrojilhuruf.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MainChildAdapter extends RecyclerView.Adapter<MainChildAdapter.MainChildViewHolder> {

    private Context context;
    private ArrayList<DataItem> mData = new ArrayList<>();

    MainChildAdapter(Context context) {
        this.context = context;
    }

    void addHuruf(List<DataItem> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainChildAdapter.MainChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_main_child, viewGroup, false);

        return new MainChildViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainChildAdapter.MainChildViewHolder holder, int position) {
        final DataItem dataItem = mData.get(position);
        holder.tvTitle.setText(dataItem.getHuruf());
        holder.imgHuruf.setImageDrawable(context.getResources().getDrawable(dataItem.getGambar()));
        holder.cvMainChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                DataItem data = new DataItem();
                data.setHuruf(dataItem.getHuruf());
                data.setArab(dataItem.getArab());
                data.setGambar(dataItem.getGambar());
                data.setPenjelasan(dataItem.getPenjelasan());
                i.putExtra(DetailActivity.EXTRA_DETAIL, data);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    class MainChildViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imgHuruf;
        CardView cvMainChild;

        MainChildViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_tv_title_child);
            imgHuruf = itemView.findViewById(R.id.item_img_child);
            cvMainChild = itemView.findViewById(R.id.item_cv_child);
        }
    }
}
