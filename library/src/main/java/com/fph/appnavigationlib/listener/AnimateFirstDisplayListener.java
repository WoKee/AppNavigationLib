package com.fph.appnavigationlib.listener;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wang on 2015/3/11 0011.
 */
public  class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
    static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
//    static final HashMap<Integer,Object> sT=new HashMap<Integer, Object>();
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
    	super.onLoadingComplete(imageUri, view, loadedImage);
        if (loadedImage != null) {
            ImageView imageView = (ImageView) view;

//            if (sT.containsKey(imageView.getId()))
//            imageView.setScaleType((ImageView.ScaleType) sT.get(imageView.getId()));
            boolean firstDisplay = !displayedImages.contains(imageUri);
            if (firstDisplay) {
                FadeInBitmapDisplayer.animate(imageView, 500);
                displayedImages.add(imageUri);
            }
        }
    }

//    @Override
//    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//        super.onLoadingFailed(imageUri, view, failReason);
//        ImageView imageView= (ImageView) view;
//        imageView.setImageResource(ImageLoader.getInstance());
////        imageView.setScaleType((ImageView.ScaleType) sT.get(imageView.getId()
//
//    }

//    @Override
//    public void onLoadingStarted(String imageUri, View view) {
//        super.onLoadingStarted(imageUri, view);
//        ImageView imageView= (ImageView) view;
//        sT.put(imageView.getId(),imageView.getScaleType());
//        imageView.setScaleType(ImageView.ScaleType.CENTER);
//    }
//
//    @Override
//    public void onLoadingCancelled(String imageUri, View view) {
//        super.onLoadingCancelled(imageUri, view);
//        ImageView imageView= (ImageView) view;
////        if (sT.containsKey(imageView.getId()))
////        imageView.setScaleType((ImageView.ScaleType) sT.get(imageView.getId()));
//    }
}
