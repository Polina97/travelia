package admin.build1.ui.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import admin.build1.R;

/**
 * Created by User on 22.05.2016.
 */
public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.TheaterViewHolder> {
    private final Cursor mCursor;
    private final TheaterOnClickListener mListener;

    public TheaterAdapter(Cursor cursor, TheaterOnClickListener listener) {
        mCursor = cursor;
        mListener = listener;
    }


    @Override
    public TheaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_theater_kino, parent, false);
        return new TheaterViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(TheaterViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String name = mCursor.getString(mCursor.getColumnIndex("NAME"));
        String text = mCursor.getString(mCursor.getColumnIndex("TEXT"));
        int imageResId = mCursor.getInt(mCursor.getColumnIndex("IMAGE_RESOURCE_ID"));
        holder.populateView(imageResId,name, text);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }



    class TheaterViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        ImageView mMap;
        TextView mName;
        TextView mText;

        public TheaterViewHolder(View itemView, final TheaterOnClickListener listener) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.photo);
            mName = (TextView) itemView.findViewById(R.id.name);
            mText=(TextView)itemView.findViewById(R.id.text);
            mMap = (ImageView) itemView.findViewById(R.id.map3);

            View.OnClickListener oclBtnOk = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCursor.moveToPosition(getAdapterPosition());
                    listener.onMapp(mCursor.getInt(mCursor.getColumnIndex("_id")));
                }
            };
            mMap.setOnClickListener(oclBtnOk);
        }

        public void populateView(int imageResId, String name, String text) {
            mImage.setBackgroundResource(imageResId);
            mName.setText(name);
            mText.setText(text);
        }

    }
    public interface TheaterOnClickListener {
        void onMapp(int id);
    }

}
