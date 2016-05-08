package admin.build1.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import admin.build1.R;
import admin.build1.database.TraveliaCursorLoader;
import admin.build1.database.TraveliaDatabaseHelper;
import admin.build1.ui.adapter.SightsAdapter;

public class AttractionsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<Cursor>,
        SightsAdapter.SightsOnClickListener{

    private static final int SIGTHS_LOADER_ID = 1;

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        initDrawer();

        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        getSupportLoaderManager().initLoader(SIGTHS_LOADER_ID, null, this);
    }

/*
    public void onClick(View view) {
        Toast toast;
        switch (view.getId()) {
            case R.id.id1:
            case R.id.id2:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №2!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.id3:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №3!", Toast.LENGTH_SHORT);
                toast.show();
                break;

            default:
                break;
        }
    }*/

    public void onStarClick(View view) {
        ImageView imageStar;
        /*switch (view.getId()) {
            case R.id.idStar1:
                imageStar = (ImageView)findViewById(view.getId());
                imageStar.setImageResource(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
                break;
            case R.id.idStar2:
                imageStar = (ImageView)findViewById(view.getId());
                imageStar.setImageResource(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
                break;
            case R.id.idStar3:
                imageStar = (ImageView)findViewById(view.getId());
                imageStar.setImageResource(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
                break;
            default:
                break;
        }*/
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            event.startTracking();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new TraveliaCursorLoader(this, TraveliaDatabaseHelper.getInstance(this));
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mRecycler.setAdapter(new SightsAdapter(data, this));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void initDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onSightClick(int id) {
        Intent intent = new Intent(this, AttractionsDetailActivity.class);
        // передать id
        startActivity(intent);
    }
}
