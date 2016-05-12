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
                //Intent intent = new Intent(this,AttractionsActivity.class);
                //startActivity(intent);
                break;
            case R.id.parking:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №2!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.fest:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №3!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.free:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №4!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.parks:
                toast = Toast.makeText(getApplicationContext(),
                        "Click №5!", Toast.LENGTH_SHORT);
                toast.show();
                break;

            default:
                break;
        }
    }
}
