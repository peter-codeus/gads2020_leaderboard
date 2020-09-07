package com.codeus.gadsleaderboard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Learning Leaders"));
        tabLayout.addTab(tabLayout.newTab().setText("Skill IQ Leaders"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void dialogDisplay()
    {
        Dialog submissionDialog = new Dialog(this);
//        View dialogView = getLayoutInflater().inflate(R.layout.successful_submission_dialog_layout, null);
        View dialogView = getLayoutInflater().inflate(R.layout.confirmation_dialog_layout, null);
        submissionDialog.setContentView(dialogView);
//        ImageView dialogImage = dialogView.findViewById(R.id.dialogIcon);
//        TextView textView = dialogView.findViewById(R.id.dialogText);
//        textView.setText("Submission not Successful");
//        dialogImage.setImageResource(R.drawable.ic_warning_red);
        submissionDialog.setCanceledOnTouchOutside(true);
        submissionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        submissionDialog.show();
    }

    public void launchProjectSubmissionScreen(View view)
    {
        startActivity(new Intent(this, ProjectSubmissionActivity.class));
    }
}
