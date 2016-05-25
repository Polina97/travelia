package admin.build1.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import admin.build1.R;
import admin.build1.database.TraveliaDatabaseHelper;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        createMapView();
    }

    private void createMapView(){

        try {
            if(null == mMap){
                mMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.map)).getMap();
                if(null == mMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            for (int id=1; id<31;id++) {
                SQLiteOpenHelper sightsDatabaseHelper = new TraveliaDatabaseHelper(this);
                SQLiteDatabase db = sightsDatabaseHelper.getReadableDatabase();
                Cursor cursor = db.query("SIGHTS",
                        new String[]{"NAME", "LATITUDE", "LONGITUDE"}, "_id = ?",
                        new String[]{Integer.toString(id)}, null, null, null, null);
                if (cursor.moveToFirst()) {
                    String name = cursor.getString(0);
                    double lat = cursor.getDouble(1);
                    double lng = cursor.getDouble(2);
                    LatLng sydney = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(sydney).title(name));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f));
                }
                cursor.close();
                db.close();
            }
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
