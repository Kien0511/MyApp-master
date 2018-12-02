package com.dthang.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dthang.myapp.view.home.fragment.TechnologyFragment;
import com.dthang.myapp.view.home.fragment.FashionFragment;
import com.dthang.myapp.view.home.fragment.HighlightsFragment;
import com.dthang.myapp.view.home.fragment.HouseAndLifeFragment;
import com.dthang.myapp.view.home.fragment.MakeUpFagment;
import com.dthang.myapp.view.home.fragment.MotherAndBabyFragment;
import com.dthang.myapp.view.home.fragment.PromotionsFragment;
import com.dthang.myapp.view.home.fragment.SportAndTravelFragment;
import com.dthang.myapp.view.home.fragment.TrademarkFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mListFragments = new ArrayList<>();
    private List<String> mListTitles = new ArrayList<>();

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initList();
    }

    private void initList() {
//        mListFragments.add(new HighlightsFragment());
//        mListFragments.add(new PromotionsFragment());
        mListFragments.add(new TechnologyFragment());
        mListFragments.add(new TechnologyFragment());
        mListFragments.add(new TechnologyFragment());
//        mListFragments.add(new HouseAndLifeFragment());
//        mListFragments.add(new FashionFragment());
//        mListFragments.add(new MakeUpFagment());
//        mListFragments.add(new MotherAndBabyFragment());
//        mListFragments.add(new SportAndTravelFragment());
//        mListFragments.add(new TrademarkFragment());

        mListTitles.add("Nổi bật");
        mListTitles.add("Chương trình khuyến mãi");
        mListTitles.add("Thiết bị điện tử");
//        mListTitles.add("Nhà cửa & đời sống");
//        mListTitles.add("Thời trang");
//        mListTitles.add("Làm đẹp");
//        mListTitles.add("Mẹ và bé");
//        mListTitles.add("Thể thao & du lịch");
//        mListTitles.add("Thương hiệu");

    }

    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitles.get(position);
    }
}
