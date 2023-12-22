package com.example.ontapandroid2_5.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ontapandroid2_5.fragment.fragment_dv;
import com.example.ontapandroid2_5.fragment.fragment_tt;

public class CollectionAdapter extends FragmentStateAdapter {

    fragment_dv frag_dv;
    fragment_tt frag_tt;
    int tabCount = 2;

    public CollectionAdapter(@NonNull Fragment fragment) {
        super(fragment);
        frag_dv = new fragment_dv();
        frag_tt = new fragment_tt();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return frag_dv;
            case 1:
                return frag_tt;
            default:
                return frag_dv;
        }

    }

    @Override
    public int getItemCount() {
        return tabCount;
    }
}
