package com.codeus.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    int numberOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int nbTabs) {
        super(fm);
        this.numberOfTabs = nbTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new LearnerFragment(FragmentType.LEARNER);
            case 1:
                return new LearnerFragment(FragmentType.SKILL_IQ);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
