package admin.build1.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import admin.build1.R;
import admin.build1.ui.adapter.HotelsAdapter;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

    }

    public void onClick(View view) {
        Toast toast;
        switch (view.getId()) {
            case R.id.fsights:
                Bundle b = new Bundle();
                boolean f = true;
                b.putBoolean("favorite", f);
                Intent intent = new Intent(this,AttractionsActivity.class);
                intent.putExtras(b);
                startActivity(intent);
                break;
            case R.id.fhotels:
                Intent intent1 = new Intent(this,HotelsActivity.class);
                intent1.putExtra("name","HOTELS");
                startActivity(intent1);
                break;
            case R.id.fcafe:
                Intent intent2 = new Intent(this,CafeActivity.class);
                intent2.putExtra("name","CAFE");
                startActivity(intent2);
                break;
            case R.id.fparks:
                Intent intent3 = new Intent(this,ParkActivity.class);
                intent3.putExtra("name","PARKS");
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
