package com.edsusantoo.bismillah.myflexiblefragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnToCategory = view.findViewById(R.id.btn_category);
        btnToCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_category:
                FragmentManager mFragmentManager = getFragmentManager();
                if(mFragmentManager!=null){
                    CategoryFragment mCategoryFragment = new CategoryFragment();
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.frame_container,mCategoryFragment,CategoryFragment.class.getSimpleName());
                    mFragmentTransaction.commit();
                    //ini jika diback akan muncul fragment sebelumnya
                    mFragmentTransaction.addToBackStack(null);
                }
                break;
        }
    }
}
