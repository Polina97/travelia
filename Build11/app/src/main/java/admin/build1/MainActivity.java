package admin.build1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageSwitcher;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageSwitcher mImageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        new Thread(new Runnable() {
                public void run() {
                    do{

                        long endTime = System.currentTimeMillis()
                            + 10 * 1000;

                        while (System.currentTimeMillis() < endTime) {
                        synchronized (this) {
                            try {
                                wait(endTime -
                                        System.currentTimeMillis());
                            } catch (Exception e) {
                            }
                        }
                            mImageSwitcher.post(new Runnable() {
                                public void run() {
                                    mImageSwitcher.showNext();
                                }
                            });
                    }

                    }while (true);
                }
        }).start();

    }

    public void onClickPrev(View view) {
        mImageSwitcher.showPrevious();
    }
    public void onClickNext(View view) {
        mImageSwitcher.showNext();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void onClick(View view) {
        Toast toast;
        switch (view.getId()) {
            case R.id.id1:
                Intent intent = new Intent(this,AttractionsActivity.class);
                startActivity(intent);
                break;
            case R.id.id2:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №2!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            /*case R.id.id3:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №3!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.id4:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №4!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.id5:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №5!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.id6:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №6!", Toast.LENGTH_SHORT);
                toast.show();
                break;*/

            default:
                break;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
