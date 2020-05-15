package com.lenovo.mynews.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovo.mynews.R;
import com.lenovo.mynews.bean.Data;
import com.lenovo.mynews.bean.MyJson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class MyNewsAdapter extends BaseMultiItemQuickAdapter<Data, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyNewsAdapter(List<Data> data) {
        super(data);
        addItemType(1, R.layout.recycler_news_item_a);
        addItemType(2, R.layout.recycler_news_item_b);
    }

    Bitmap posterBitmap;

    @Override
    protected void convert(BaseViewHolder helper, Data item) {


        switch (helper.getItemViewType()) {

            case 1:
                setPosterBitmap(item.getThumbnail_pic_s());
                helper.setText(R.id.tv_news_title_a, item.getTitle());
                helper.setText(R.id.tv_news_category_a, item.getCategory());
                helper.setText(R.id.tv_news_date, item.getDate());
               Bitmap bitmap= setPosterBitmap(item.getThumbnail_pic_s());
                helper.setImageBitmap(R.id.iv_new_photo_a, bitmap);

                break;
            case 2:
                helper.setText(R.id.tv_news_title_b, item.getTitle());
                helper.setText(R.id.tv_news_category_b, item.getCategory());
                helper.setText(R.id.tv_news_date_b, item.getDate());
                break;
        }
    }

    /**
     * 使用picasso讲url转换成bitmap
     * @param url
     */
    Bitmap setPosterBitmap(String url) {
        final Bitmap[] mBitmap = new Bitmap[1];
        Picasso.get().load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                mBitmap[0] = bitmap;
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
        return mBitmap[0];
    }
}