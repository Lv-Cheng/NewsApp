package data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cache.BitmapUtils;
import test.newsapp.R;
import test.newsapp.activity.MainActivity;


/**
 * Created by admin on 2016/5/19.
 */
public class InfoAdapter extends BaseAdapter {

    private Context mContext;
    private final ArrayList<NewsData> mNewsArrayList;
    private LayoutInflater mLayoutInflater;

    public InfoAdapter(Context context, ArrayList<NewsData> newsArrayList){
        this.mContext = context;
        this.mNewsArrayList = newsArrayList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mNewsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.activity_adapt, null);
            viewHolder = new ViewHolder();
            viewHolder.mTitleTextView = (TextView) convertView.findViewById(R.id.news_title_text);
            viewHolder.mDescriptionTextView = (TextView) convertView.findViewById(R.id.news_description_text);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.news_image_view);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.mTitleTextView.setText(mNewsArrayList.get(position).getTitle());
        viewHolder.mDescriptionTextView.setText(mNewsArrayList.get(position).getDescription());

        Picture picture = mNewsArrayList.get(position).getPicture();
        MainActivity.bitmapUtils.display(viewHolder.mImageView, picture.getPictureUrl());

        return convertView;
    }



    private final class ViewHolder{
        ImageView mImageView;
        TextView mTitleTextView;
        TextView mDescriptionTextView;
    }
}
