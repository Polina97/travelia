package admin.build1.database;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

/**
 * Created by User on 24.05.2016.
 */
public class TraveliaCursorLoaderFun extends CursorLoader {
    private TraveliaDatabaseHelper mDatabase;

    public TraveliaCursorLoaderFun(Context context, TraveliaDatabaseHelper database) {
        super(context);
        mDatabase = database;
    }
    @Override
    public Cursor loadInBackground() {
        return mDatabase.getClubsCursor();
    }
}

