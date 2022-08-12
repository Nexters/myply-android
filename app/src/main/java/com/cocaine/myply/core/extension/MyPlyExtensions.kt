package com.cocaine.myply.core.extension

import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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


@BindingAdapter("app:set_tint")
fun setBackgroundTint(view: View, color: Int) {
    runCatching {
        val viewColor = ContextCompat.getColor(view.context, color)
        view.backgroundTintList = ColorStateList.valueOf(viewColor)
    }.getOrElse {
        view.backgroundTintList = ColorStateList.valueOf(color)
    }
}

@BindingAdapter("app:set_font_color")
fun updateTextFond(view: TextView, color: Int) {
    if(color == 0) return
    val colorInfo = ContextCompat.getColor(view.context, color)
    view.setTextColor(colorInfo)
}