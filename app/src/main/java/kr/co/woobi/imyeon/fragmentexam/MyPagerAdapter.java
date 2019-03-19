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
        mData.add(Fragment1.newInstance(R.drawable.image1, "1. 군도", "예매율 61.6% | 15세 관람가 | D-1"));
        mData.add(Fragment1.newInstance(R.drawable.image2, "2. 공조", "예매율 61.6% | 15세 관람가 | D-1"));
        mData.add(Fragment1.newInstance(R.drawable.image3, "3. 더킹", "예매율 61.6% | 15세 관람가 | D-1"));
        mData.add(Fragment1.newInstance(R.drawable.image4, "4. 레지던트이블", "예매율 61.6% | 15세 관람가 | D-1"));
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
