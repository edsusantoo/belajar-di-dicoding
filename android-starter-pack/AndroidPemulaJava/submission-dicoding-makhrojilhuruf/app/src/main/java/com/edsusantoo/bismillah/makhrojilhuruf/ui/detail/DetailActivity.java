package com.edsusantoo.bismillah.makhrojilhuruf.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.edsusantoo.bismillah.makhrojilhuruf.R;
import com.edsusantoo.bismillah.makhrojilhuruf.model.DataItem;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL = "extra_detail";

    TextView tvTitle, tvDescription;
    ImageView imgDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvDescription = findViewById(R.id.tv_description);
        tvTitle = findViewById(R.id.tv_title);
        imgDetail = findViewById(R.id.img_detail);

        getDataIntent();

        setDataIntent();

    }

    private DataItem getDataIntent() {
        return getIntent().getParcelableExtra(EXTRA_DETAIL);
    }

    private void setDataIntent() {
        tvTitle.setText(getDataIntent().getHuruf());
        tvDescription.setText(getDataIntent().getPenjelasan());
        imgDetail.setImageDrawable(getResources().getDrawable(getDataIntent().getGambar()));
    }
}
