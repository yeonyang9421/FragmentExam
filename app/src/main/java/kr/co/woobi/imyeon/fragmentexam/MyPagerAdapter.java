package kr.co.woobi.imyeon.fragmentexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mData;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        mData = new ArrayList<>();
        mData.add(MovieListFragment.newInstance(R.drawable.image1, "1. 군도", "예매율 61.6% | 15세 관람가 | D-1", 1));
        mData.add(MovieListFragment.newInstance(R.drawable.image2, "2. 공조", "예매율 61.6% | 15세 관람가 | D-1", 2));
        mData.add(MovieListFragment.newInstance(R.drawable.image3, "3. 더킹", "예매율 61.6% | 15세 관람가 | D-1", 3));
        mData.add(MovieListFragment.newInstance(R.drawable.image4, "4. 레지던트이블", "예매율 61.6% | 15세 관람가 | D-1", 4));
        mData.add(MovieListFragment.newInstance(R.drawable.image5, "5. 럭키", "예매율 61.6% | 15세 관람가 | D-1", 5));
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
