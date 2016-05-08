package admin.build1.database;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

/**
 * Created by User on 08.05.2016.
 */
public class TraveliaCursorLoader extends CursorLoader {
    private TraveliaDatabaseHelper mDatabase;
    public TraveliaCursorLoader(Context context, TraveliaDatabaseHelper database) {
        super(context);
        mDatabase = database;
    }

    @Override
    public Cursor loadInBackground() {
        return mDatabase.getSightsCursor();
    }
}
