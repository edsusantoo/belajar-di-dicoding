package com.edsusantoo.bismillah.makhrojilhuruf.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edsusantoo.bismillah.makhrojilhuruf.R;
import com.edsusantoo.bismillah.makhrojilhuruf.model.DataItem;
import com.edsusantoo.bismillah.makhrojilhuruf.model.MakhrojilHurufItem;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private ArrayList<MakhrojilHurufItem> mData = new ArrayList<>();
    private Context context;

    MainAdapter(Context context) {
        this.context = context;
    }

    void addMakhrojilHuruf(List<MakhrojilHurufItem> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_main, viewGroup, false);

        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        holder.tvTempatHuruf.setText(mData.get(position).getTempat());
        initRecyclerHuruf(holder.rvHuruf, mData.get(position).getData());
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    private void initRecyclerHuruf(RecyclerView rvHuruf, List<DataItem> data) {
        rvHuruf.setLayoutManager(new GridLayoutManager(context, 2));
        MainChildAdapter adapter = new MainChildAdapter(context);
        rvHuruf.setAdapter(adapter);
        rvHuruf.setHasFixedSize(true);

        //adddata
        adapter.addHuruf(data);
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView tvTempatHuruf;
        RecyclerView rvHuruf;

        MainViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTempatHuruf = itemView.findViewById(R.id.item_tv_title);
            rvHuruf = itemView.findViewById(R.id.item_rv_child);
        }
    }
}
