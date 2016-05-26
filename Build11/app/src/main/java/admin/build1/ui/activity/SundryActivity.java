package admin.build1.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import admin.build1.R;

public class SundryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sundry);
    }
    public void onClick(View view) {
        Toast toast;
        switch (view.getId()) {
            case R.id.taxi:
                Intent intent = new Intent(this,TaxiActivity.class);
                startActivity(intent);
                break;
            case R.id.parking:
                Bundle b = new Bundle();
                String fullname = "PARKING";
                b.putString("name", fullname);
                Intent intent1 = new Intent(this,MapsActivity.class );
                intent1.putExtras(b);
                startActivity(intent1);
                break;
            case R.id.fest:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №3!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.theater:
                Intent intent3 = new Intent(this,TheaterActivity.class);
                startActivity(intent3);
                break;
            case R.id.cinema:
                Intent intent4 = new Intent(this,CinemaActivity.class);
                startActivity(intent4);
                break;
            case R.id.parks:
                Intent intent5 = new Intent(this,ParkActivity.class);
                startActivity(intent5);
                break;
            case R.id.clubs:
                Intent intent6 = new Intent(this,FunActivity.class);
                startActivity(intent6);
                break;
            case R.id.cazino:
                Intent intent7 = new Intent(this,CazinoActivity.class);
                startActivity(intent7);
                break;

            default:
                break;
        }
    }
}
