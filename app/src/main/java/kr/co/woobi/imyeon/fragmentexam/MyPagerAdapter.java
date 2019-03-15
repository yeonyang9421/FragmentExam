package kr.co.woobi.imyeon.fragmentexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mData;
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        mData=new ArrayList<>();
        mData.add(new Fragment1());
        mData.add(new Fragment2());
        mData.add(new Fragment3());
        mData.add(new Fragment4());
    }

    @Override
    public Fragment getItem(int i) {
        return mData.get(i);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
