package com.edsusantoo.bismillah.mylistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edsusantoo.bismillah.mylistview.R;
import com.edsusantoo.bismillah.mylistview.model.Hero;

import java.util.ArrayList;

public class HeroAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Hero> heroes;

    public HeroAdapter(Context context) {
        this.context = context;
        heroes = new ArrayList<>();
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public int getCount() {
        return heroes.size();
    }

    @Override
    public Object getItem(int position) {
        return heroes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hero, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(convertView);
        Hero hero = (Hero) getItem(position);
        viewHolder.bind(hero);

        return convertView;
    }

    private class ViewHolder {
        private TextView tvName, tvDescription;
        private ImageView imgPhoto;

        ViewHolder(View view) {
            tvName = view.findViewById(R.id.tv_name);
            tvDescription = view.findViewById(R.id.tv_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(Hero hero) {
            tvName.setText(hero.getName());
            tvDescription.setText(hero.getDescription());
            imgPhoto.setImageResource(hero.getPhoto());
        }
    }
}
