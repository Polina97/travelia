package admin.build1.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import admin.build1.R;
import admin.build1.database.TraveliaDatabaseHelper;

/**
 * Created by User on 08.05.2016.
 */
public class SightsAdapter extends RecyclerView.Adapter<SightsAdapter.SightsViewHolder> {

    private final Cursor mCursor;
    private final SightsOnClickListener mListener;
    private final Context mContext;
    private final boolean mIsFavourite;

    //в этом массиве будут индексы favourite, чтобы в onBindViewHolder мы знали на какую позицию курсора идти
    private List<Integer> mFavouriteIndexes;

    public SightsAdapter(Cursor cursor, Context context, boolean isFavourite, SightsOnClickListener listener) {
        mCursor = cursor;
        mContext = context;
        mListener = listener;

        //присваеиваем переменную
        mIsFavourite = isFavourite;
    }

    @Override
    public SightsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sights, parent, false);
        return new SightsViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(SightsViewHolder holder, int position) {
        //проверяем: если нам нужны только favourite, то берём индекс из заранее подготовленного списка mFavouriteIndexes
        //иначе просто двигаемся на следующую позицию курсора
        mCursor.moveToPosition(mIsFavourite ? mFavouriteIndexes.get(position) : position);

        //тут без изменений
        String text = mCursor.getString(mCursor.getColumnIndex("NAME"));
        int imageResId = mCursor.getInt(mCursor.getColumnIndex("IMAGE_RESOURCE_ID"));
        boolean isFavourite = mCursor.getInt(mCursor.getColumnIndex("FAVORITE")) == 1;
        holder.populateView(imageResId, text, isFavourite);
    }

    @Override
    public int getItemCount() {
        //magic happens here

        if (!mIsFavourite) {
            return mCursor.getCount();
        } else {
            mFavouriteIndexes = new ArrayList<>();

            //посомтри в отлаке как идёт обход курсора и всё поймёшь
            for (int i = 0; i < mCursor.getCount(); i++) {

                mCursor.moveToPosition(i);
                //TODO CHANGE IS HERE
                //TODO CHANGE IS HERE

                if (mCursor.getInt(mCursor.getColumnIndex("FAVORITE")) == 1) {
                    mFavouriteIndexes.add(i);
                }
            }

            return mFavouriteIndexes.size();
        }
    }

    class SightsViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mText;
        CheckBox Favourite;

        public SightsViewHolder(View itemView, final SightsOnClickListener listener) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.image);
            mText = (TextView) itemView.findViewById(R.id.text);

            Favourite = (CheckBox)itemView.findViewById(R.id.favourite);
            Favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    TraveliaDatabaseHelper.getInstance(mContext).changeFavourite(getId(), isChecked);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSightClick(getId());
                }
            });
        }

        private int getId() {

            if (!mIsFavourite) {
                mCursor.moveToPosition(getAdapterPosition());
                return  mCursor.getInt(mCursor.getColumnIndex("_id"));
            }
            else {
                mCursor.moveToPosition(mFavouriteIndexes.get(getAdapterPosition()));
                return  mCursor.getInt(mCursor.getColumnIndex("_id"));

            }
        }

        public void populateView(int imageResId, String text, boolean isFavourite) {
            mImage.setBackgroundResource(imageResId);
            mText.setText(text);
            Favourite.setChecked(isFavourite);
        }
    }

    public interface SightsOnClickListener {
        void onSightClick(int id);
    }
}
