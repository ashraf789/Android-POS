package net.a6te.lazycoder.andropos.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.a6te.lazycoder.andropos.sellTabes.AddProductFrg;
import net.a6te.lazycoder.andropos.sellTabes.ListProductFrg;
import net.a6te.lazycoder.andropos.sellTabes.ReceiptFrg;

/**
 * Created by Programmer on 5/1/2017.
 */

public class SellPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public SellPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AddProductFrg tab1 = new AddProductFrg();
                return tab1;
            case 1:
                ListProductFrg tab2 = new ListProductFrg();
                return tab2;
            case 2:
                ReceiptFrg tab3 = new ReceiptFrg();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
