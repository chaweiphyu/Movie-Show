package zero.com.kyiimal;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import zero.com.kyiimal.adapters.ViewpagerAdapter;
import zero.com.kyiimal.fragments.OnCinemaFragment;
import zero.com.kyiimal.fragments.PopularFragment;
import zero.com.kyiimal.fragments.RecommendFragment;
import zero.com.kyiimal.fragments.Top100Fragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        setupViewpager(viewPager);

        tabLayout = findViewById(R.id.tabs);

        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewpager(ViewPager viewPager) {
        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PopularFragment(),"Popular");
        adapter.addFragment(new Top100Fragment(),"Top 100");
        adapter.addFragment(new OnCinemaFragment(),"On Cinema");
        adapter.addFragment(new RecommendFragment(),"Recommend");
        viewPager.setAdapter(adapter);

    }
}
