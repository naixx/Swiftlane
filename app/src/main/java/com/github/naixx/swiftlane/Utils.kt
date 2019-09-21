package com.github.naixx.swiftlane

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:src", "placeholder")
fun ImageView.loadImage(url: String, placeholder: Drawable?) {
    Glide.with(this).load(url).placeholder(placeholder).into(this)
}
