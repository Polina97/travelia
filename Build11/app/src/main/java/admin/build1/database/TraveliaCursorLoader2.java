package admin.build1.database;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

/**
 * Created by User on 16.05.2016.
 */
public class TraveliaCursorLoader2  extends CursorLoader {
    private TraveliaDatabaseHelper mDatabase;

    public TraveliaCursorLoader2(Context context, TraveliaDatabaseHelper database) {
        super(context);
        mDatabase = database;
    }

    @Override
    public Cursor loadInBackground() {
        return mDatabase.getTaxiCursor();
    }
}
