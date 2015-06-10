package pl.allergyfoodadvisor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.activities.fragments.HistoryFragment;
import pl.allergyfoodadvisor.activities.fragments.SearchProductFragment;

public class MainActivity extends BaseActivity implements  NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    private static final long DRAWER_CLOSE_DELAY_MS = 250; // http://blog.xebia.com/2015/06/09/android-design-support-navigationview/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_home));

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("cheeseListFragment");
        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            fragment = new SearchProductFragment();
            ft.replace(R.id.container, fragment, "cheeseListFragment");
            ft.commit();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();

        // allow some time after closing the drawer before performing real navigation
        // so the user can see what is happening
        mDrawerLayout.closeDrawer(GravityCompat.START);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment newFragment;
                findViewById(R.id.fab).setVisibility(View.INVISIBLE);

                switch (menuItem.getItemId()) {
                    case R.id.nav_allergens:
                        newFragment = new HistoryFragment();
                        break;
                    case R.id.nav_history:
                        newFragment = new HistoryFragment();
                        break;
                    case R.id.nav_home:
                    default:
                        newFragment = new SearchProductFragment();
                        findViewById(R.id.fab).setVisibility(View.VISIBLE);
                }

                Log.d("ASdf", "ASDF");
                fragmentTransaction.replace(R.id.container, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        }, DRAWER_CLOSE_DELAY_MS);

        return true;
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    public void addNewProduct(View view) {
        Intent intent = new Intent(this, AddNewProductActivity.class);
        startActivity(intent);
    }

    public void navAllergensSelected(){
        Intent intent = new Intent(this, MyAllergensActivity.class);
        this.startActivity(intent);
    }
}
