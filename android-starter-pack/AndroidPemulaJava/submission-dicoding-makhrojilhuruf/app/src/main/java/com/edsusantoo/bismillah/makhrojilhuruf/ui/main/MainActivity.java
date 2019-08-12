package com.edsusantoo.bismillah.makhrojilhuruf.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.edsusantoo.bismillah.makhrojilhuruf.R;
import com.edsusantoo.bismillah.makhrojilhuruf.model.DataItem;
import com.edsusantoo.bismillah.makhrojilhuruf.model.MakhrojilHurufItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvMain;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rv_main);

        setupRecycleMain();

        getDataMakhrojilHuruf();

    }

    void setupRecycleMain() {
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this);
        rvMain.setAdapter(adapter);
        rvMain.setHasFixedSize(true);
    }

    void getDataMakhrojilHuruf() {
        ArrayList<DataItem> halq = new ArrayList<>();
        halq.add(new DataItem("Hamzah", "ء", R.drawable.hamzah, "Keluar dari tenggorokan bagian bawah"));
        halq.add(new DataItem("Hâ", "هـ", R.drawable.habesar, "Keluar dari tenggorokan bagian bawah"));
        halq.add(new DataItem("`Aîn", "ع", R.drawable.ain, "Keluar dari tenggorokan bagian tengah"));
        halq.add(new DataItem("Hâ", "ح", R.drawable.hakecil, "Keluar dari tenggorokan bagian tengah"));
        halq.add(new DataItem("Ghoîn", "غ", R.drawable.gho, "Keluar dari tenggorokan bagian atas"));
        halq.add(new DataItem("Khô", "خ", R.drawable.kho, "Keluar dari tenggorokan bagian atas"));

        ArrayList<DataItem> lisan = new ArrayList<>();
        lisan.add(new DataItem("Qôf", "ق", R.drawable.qo, "Keluar dari pangkal lidah (dekat tenggorokan) dengan mengangkatnya ke atas langit-langit mulut"));
        lisan.add(new DataItem("Kâf", "ك", R.drawable.ka, "Keluar dari pangkal lidah (dekat tenggorokan) dengan menurunkannya ke bawah langit-langit mulut"));
        lisan.add(new DataItem("Lâm", "ل", R.drawable.lam, "Keluarnya dari ujung lidah bertemu dengan ujung langit-langit mulut"));
        lisan.add(new DataItem("Nûn", "ن", R.drawable.na, "Keluarnya dari ujung lidah di bawah makhraj lam"));
        lisan.add(new DataItem("Rô", "ر", R.drawable.ro, "Keluarnya dari ujung lidah, hampir sama seperti huruf nun, dengan memasukan punggung lidah"));
        lisan.add(new DataItem("Tâ", "ت", R.drawable.ta, "Keluar dari ujung lidah yang bertemu dengan gigi seri atas"));
        lisan.add(new DataItem("Dâl", "د", R.drawable.da, "Keluar dari ujung lidah yang bertemu dengan gigi seri atas"));
        lisan.add(new DataItem("Thô", "ط", R.drawable.tho, "Keluar dari ujung lidah yang bertemu dengan gigi seri atas"));
        lisan.add(new DataItem("Tsâ", "ث", R.drawable.tsa, "Keluar dari ujung lidah. Ujung lidah sedikit keluar dan bertemu dengan ujung gigi seri atas"));
        lisan.add(new DataItem("Dzâl", "ذ", R.drawable.da, "Keluar dari ujung lidah. Ujung lidah sedikit keluar dan bertemu dengan ujung gigi seri atas"));
        lisan.add(new DataItem("Zhô", "ظ", R.drawable.dzo, "Keluar dari ujung lidah. Ujung lidah sedikit keluar dan bertemu dengan ujung gigi seri atas"));
        lisan.add(new DataItem("Shôd", "ص", R.drawable.sho, "Keluar dari ujung lidah yang hampir bertemu dengan gigi seri bawah"));
        lisan.add(new DataItem("Zây, Zayy, atau Zâ", "ز", R.drawable.za, "Keluar dari ujung lidah yang hampir bertemu dengan gigi seri bawah"));
        lisan.add(new DataItem("Sîn", "س", R.drawable.sa, "Keluar dari ujung lidah yang hampir bertemu dengan gigi seri bawah"));
        lisan.add(new DataItem("Jîm", "ج", R.drawable.ja, "Keluar dari tengah lidah yang bertemu dengan langit-langit mulut"));
        lisan.add(new DataItem("Yâ", "ي", R.drawable.ya, "Keluar dari tengah lidah yang bertemu dengan langit-langit mulut"));
        lisan.add(new DataItem("Syîn", "ش", R.drawable.sya, "Keluar dari tengah lidah yang bertemu dengan langit-langit mulut"));
        lisan.add(new DataItem("Dhôd", "ض", R.drawable.dho, "Keluar dari dua sisi lidah atau salah satunya yang bertemu dengan gigi geraham di atas ataupun di bawah"));

        ArrayList<DataItem> syafatan = new ArrayList<>();
        syafatan.add(new DataItem("Fâ", "ف", R.drawable.fa, "Keluar dari bibir bawah bagian dalam yang bertemu dengan ujung gigi seri atas"));
        syafatan.add(new DataItem("Wâw", "و", R.drawable.wau, "Keluar dari dua bibir yang dimonyongkan"));
        syafatan.add(new DataItem("Bâ", "ب", R.drawable.ba, "Keluar dari dua bibir yang dirapatkan"));
        syafatan.add(new DataItem("Mîm", "م", R.drawable.ma, "Keluar dari dua bibir yang dirapatkan"));

        ArrayList<DataItem> jauf = new ArrayList<>();
        jauf.add(new DataItem("Alif Sukun Sebelumnya Fathah", "ا", R.drawable.aliffathah, "Pengucapannya dengan membuka mulut"));
        jauf.add(new DataItem("Ya Sukun Sebelumnya Kasroh", "ي", R.drawable.yakasroh, "Pengucapannya dengan menurunkan bibir bagian bawah"));
        jauf.add(new DataItem("Wau Sukun Sebelumnya Dommah", "و", R.drawable.waudommah, "Pengucapannya dengan memonyongkan dua bibir"));

        ArrayList<DataItem> khaisyum = new ArrayList<>();
        khaisyum.add(new DataItem("Gunnah Musyadadah", "", R.drawable.gunnahmusyadadah, "Setiap huruf mim dan nun yang bertasydid wajib digunnahkan dan dipanjangkan dua harakat"));
        khaisyum.add(new DataItem("Idgham Bighunnah", "", R.drawable.idghombigunnah, "Ghunah berarti dengung, Idgham bighunnah adalah meleburkan nun mati atau tanwin di sertai dengan dengung, Terjadi jika nun mati atau tanwin bertemu dengan huruf idgham bigunnah"));
        khaisyum.add(new DataItem("Idgham Mutajanisain", "", R.drawable.idghammutanajisain, "pertemuan dua huruf yang sama makhrajnya tetapi tidak sama sifatnya"));
        khaisyum.add(new DataItem("Idgham Mitslain", "", R.drawable.idghammimi, "hanya berlaku pada saat Mim Sukun ( مْ ) bertemu dengan huruf Mim yang berharakat ( مَ  مِ , مُ ).  Dan apabila huruf Mim Sukun tersebut belum bertemu dengan huruf Mim yang memiliki harakat (hidup), maka wajib dibaca Idzhar. Cara membacanya pun juga berbeda, yakni dibaca jelas tanpa ada dengung."));
        khaisyum.add(new DataItem("Iqlab", "", R.drawable.iqlab, "Jikalau Nun mati atau tanwin bertemu dengan huruf Ba (ب) berarti itu adalah hukum bacaan Iqlab, arti dari Iqlab itu sendiri dari merubah, dibalik, ditukar atau mengganti. Jadi yang disebut iqlab adalah merubah, membalik, menukar atau mengganti Nun mati atau tanwin dengan huruf mim, atau dibaca sebagai mim mati."));
        khaisyum.add(new DataItem("Ikhfa Haqiqi", "", R.drawable.ikhfahaqiqi, "Ikhfa bila dilihat berdasarkan asal hurufnya [harfiah /etimologi] mempunyai arti menyembunyikan atau bisa juga berarti menyamarkan. Di dalam ilmu tajwid. apabila ada Nun disukun ( نْ ) dan juga tanwin ( ــًــ, ــٍــ, ــٌــ ), baik itu fathah tanwin, kasrah tanwin dan juga dhomah tanwin kemudian dibelakangnya terdapat  huruf hijaiyah yang berjumlah 15 (lima belas) maka hukumnya adalah ikhfa haqiqi.  Ikhfa Haqiqi maknanya adalah menyamarkan /menyembunikan huruf Nun Sukun ( نْ ) ataupun juga tanwin (fathah tanwin ( ــٌــ), kasrah tanwin ( ــٍــ), dhomah tanwin ــًــ ) masuk ke dalam huruf hijaiyah yang berada di belakangnya (sesudahnya)."));
        khaisyum.add(new DataItem("Ikhfa Syafawi", "", R.drawable.ikhfasyafawi, "Ikhfa Syafawi yaitu suatu hukum tajwid yang terjadi ketika ada huruf hijaiyah Mim Sukun ( مْ ) ketemu dengan huruf hijaiyah Ba ( ب ) . Disebut dengan Ikhfa Syafawi sebab makhraj dari huruf hijaiyah Mim dan huruf hijaiyah Ba adalah pertemuan antara bibir bawah dan bibir atas."));


        ArrayList<MakhrojilHurufItem> tempat = new ArrayList<>();
        tempat.add(new MakhrojilHurufItem("Halq atau Tenggorokan", halq));
        tempat.add(new MakhrojilHurufItem("Lisan atau Lidah", lisan));
        tempat.add(new MakhrojilHurufItem("Syafatan atau Dua Bibir", syafatan));
        tempat.add(new MakhrojilHurufItem("Jauf atau Rongga Mulut", jauf));
        tempat.add(new MakhrojilHurufItem("Khaisyum atau Rongga Hidung", khaisyum));

        adapter.addMakhrojilHuruf(tempat);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_gambar_lengkap:

                break;

            case R.id.action_referensi_video:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
