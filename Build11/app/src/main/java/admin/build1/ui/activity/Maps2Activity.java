package admin.build1.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import admin.build1.Manifest;
import admin.build1.R;
import admin.build1.database.TraveliaDatabaseHelper;

public class Maps2Activity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b!= null) {
            String name = b.getString("name");
            int id = b.getInt("id");

            try {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
                else {
                    // Show rationale and request permission.
                }
                SQLiteOpenHelper sightsDatabaseHelper = new TraveliaDatabaseHelper(this);
                SQLiteDatabase db = sightsDatabaseHelper.getReadableDatabase();
                Cursor cursor = db.query(name,
                        new String[]{"NAME", "LATITUDE", "LONGITUDE"}, "_id = ?",
                        new String[]{Integer.toString(id)}, null, null, null, null);
                //Move to the first record in the Cursor
                if (cursor.moveToFirst()) {
                    String name1 = cursor.getString(0);
                    double lat = cursor.getDouble(1);
                    double lng = cursor.getDouble(2);
                    LatLng sydney = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(sydney).title(name1));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18f));
                    mMap.setMyLocationEnabled(true);
                }
                cursor.close();
                db.close();

            } catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
