package kr.co.woobi.imyeon.fragmentexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MoviePosterPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mData = new ArrayList<>();

    public MoviePosterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mData.get(i);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public void setItems(List<Fragment> movieFragmentList) {
        mData = movieFragmentList;
    }
}
