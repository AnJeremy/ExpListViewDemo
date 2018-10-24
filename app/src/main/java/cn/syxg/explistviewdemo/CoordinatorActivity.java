package cn.syxg.explistviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<String> tabNames;
    private List<Fragment> fragments;

    private TextView tvname,tvstatus,tvdownload,tvsize,tvcompany;
    private ImageView iv_icon;
    public static String packgName = null;

    private TestFragment detailsFragment;
    private TestF2 commentFragment;

    private String gamesId;

    private ImageButton downloadManger,search,back,share;
    private Button download;
    private ImageButton like;

    //private DownloadManager downloadManager;
    //private GamesDetailsBean gamesDetailsBean;
   // private SharedPreferences sharedPreferences;

    //private View loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        viewPager = (ViewPager) findViewById(R.id.details_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.details_tab);

        fragments = new ArrayList<>();
        tabNames = new ArrayList<>();

        detailsFragment = new TestFragment();
        commentFragment = new TestF2();

        fragments.add(detailsFragment);
        fragments.add(commentFragment);

        tabNames.add("详情");
        tabNames.add("评论");


        tabLayout.setTabMode(TabLayout.MODE_FIXED);//tab不能滚动，平分屏幕宽度

        DetailsAdapter adapter = new DetailsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    private class DetailsAdapter extends FragmentPagerAdapter{
        public DetailsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultAACoo",requestCode+">>"+resultCode);
        //startActivityForResult
    }
}
