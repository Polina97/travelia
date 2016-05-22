package admin.build1.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import admin.build1.R;
import admin.build1.database.TraveliaCursorLoader1;
import admin.build1.database.TraveliaDatabaseHelper;
import admin.build1.ui.adapter.HotelsAdapter;

public class HotelsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<Cursor>,
        HotelsAdapter.HotelsOnClickListener {

    private static final int HOTELS_LOADER_ID = 1;

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        initDrawer();

        mRecycler = (RecyclerView) findViewById(R.id.recycler_view1);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        getSupportLoaderManager().initLoader(HOTELS_LOADER_ID, null, this);

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

        if (id == R.id.nav_attractions) {
            Intent intent = new Intent(this, AttractionsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_hotels) {

        } else if (id == R.id.nav_cafe) {

        } else if (id == R.id.nav_sundry) {

        } else if (id == R.id.nav_map) {

        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_festival) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new TraveliaCursorLoader1(this, TraveliaDatabaseHelper.getInstance(this));
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mRecycler.setAdapter(new HotelsAdapter(data, this));
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
    public void onHotelsClick(int id) {

        try {
            SQLiteOpenHelper sightsDatabaseHelper = new TraveliaDatabaseHelper(this);
            SQLiteDatabase db = sightsDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("HOTELS",
                    new String[]{"NAME", "CONTACTS", "IMAGE_RESOURCE_ID2"}, "_id = ?",
                    new String[]{Integer.toString(id)}, null, null, null);
            if (cursor.moveToFirst()) {
                String name = cursor.getString(0);
                String text = cursor.getString(1);
                int photoId = cursor.getInt(2);
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.card_hotels,
                        (ViewGroup)findViewById(R.id.layout));
                TextView namehotels = (TextView)layout.findViewById(R.id.text_card1);
                namehotels.setText(name);
                TextView texthotels = (TextView)layout.findViewById(R.id.text_card);
                texthotels.setText(text);
                ImageView imagehotels = (ImageView)layout.findViewById(R.id.image_card);
                imagehotels.setImageResource(photoId);

                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder. setView(layout);;
                builder.show();
            }
            cursor.close();
            db.close();

        }
        catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
