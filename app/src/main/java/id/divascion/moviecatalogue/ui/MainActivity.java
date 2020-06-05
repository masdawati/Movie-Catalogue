package id.divascion.moviecatalogue.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import id.divascion.moviecatalogue.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private int choose = 1;
    private boolean isList = true;
    private BottomNavigationView bottomNavigationMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationMain = findViewById(R.id.bottom_nav_main);
        bottomNavigationMain.setOnNavigationItemSelectedListener(this);
        bottomNavigationMain.setSelectedItemId(R.id.menu_airingtoday);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_airingtoday:
                choose = 1;
                if (isList) {
                    switchFragment(new TvAiringTodayFragment(), "airing");
                } else {
                    switchFragment(new TvViewGridFragment(), "airing");
                }
                return true;
            case R.id.menu_popular:
                choose = 2;
                if (isList) {
                    switchFragment(new TvPopularFragment(), "popular");
                } else {
                    switchFragment(new TvViewGridFragment(), "popular");
                }
                return true;
            case R.id.menu_top:
                choose = 3;
                if (isList) {
                    switchFragment(new TvTopRatedFragment(), "top");
                } else {
                    switchFragment(new TvViewGridFragment(), "top");
                }
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_list:
                isList = true;
                switch (choose) {
                    case 1:
                        bottomNavigationMain.setSelectedItemId(R.id.menu_airingtoday);
                        break;
                    case 2:
                        bottomNavigationMain.setSelectedItemId(R.id.menu_popular);
                        break;
                    case 3:
                        bottomNavigationMain.setSelectedItemId(R.id.menu_top);
                        break;
                }
                return true;
            case R.id.item_menu_grid:
                isList = false;
                switch (choose) {
                    case 1:
                        bottomNavigationMain.setSelectedItemId(R.id.menu_airingtoday);
                        break;
                    case 2:
                        bottomNavigationMain.setSelectedItemId(R.id.menu_popular);
                        break;
                    case 3:
                        bottomNavigationMain.setSelectedItemId(R.id.menu_top);
                        break;
                }
                return true;
            default:
                break;
        }
        return false;
    }

    private void switchFragment(Fragment fragment, String code) {
        Bundle bundle = new Bundle();
        bundle.putString("CODE", code);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }

}