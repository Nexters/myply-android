package com.cocaine.myply.core.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("app:update_image")
fun updateImage(imageView: ImageView, url: String?) {
    url?.let {
        //TODO : Set Error Image PlaceHolder
        Glide.with(imageView)
            .load(it)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageView)
    }
}