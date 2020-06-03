package id.divascion.moviecatalogue.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import id.divascion.moviecatalogue.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

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
        switch(menuItem.getItemId()) {
            case R.id.menu_airingtoday:
                if(isList) {
                    switchFragment(new TvAiringTodayFragment());
                } else {

                }
                return true;
            case R.id.menu_popular:
                if(isList) {
                    switchFragment(new TvPopularFragment());
                } else {

                }
                return true;
            case R.id.menu_top:
                if(isList) {
                    switchFragment(new TvTopRatedFragment());
                } else {

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
        switch(item.getItemId()) {
            case R.id.item_menu_list:
                isList = true;
                bottomNavigationMain.setOnNavigationItemSelectedListener(this);
                return true;
            case R.id.item_menu_grid:
                isList = false;
                bottomNavigationMain.setOnNavigationItemSelectedListener(this);
                return true;
            default:
                break;
        }
        return false;
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }

}