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
 * Created by User on 23.05.2016.
 */
public class CasinoAdapter extends RecyclerView.Adapter<CasinoAdapter.CasinoViewHolder> {
    private final Cursor mCursor;
    private final CasinoOnClickListener mListener;

    public CasinoAdapter(Cursor cursor, CasinoOnClickListener listener) {
        mCursor = cursor;
        mListener = listener;
    }


    @Override
    public CasinoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_theater_kino, parent, false);
        return new CasinoViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(CasinoViewHolder holder, int position) {
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



    class CasinoViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        ImageView mMap;
        TextView mName;
        TextView mText;

        public CasinoViewHolder(View itemView, final CasinoOnClickListener listener) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.photo);
            mName = (TextView) itemView.findViewById(R.id.name);
            mText=(TextView)itemView.findViewById(R.id.text);
            mMap = (ImageView) itemView.findViewById(R.id.map3);

            View.OnClickListener oclBtnOk = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCursor.moveToPosition(getAdapterPosition());
                    listener.onMap(mCursor.getInt(mCursor.getColumnIndex("_id")));
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
    public interface CasinoOnClickListener {
        void onMap(int id);
    }

}