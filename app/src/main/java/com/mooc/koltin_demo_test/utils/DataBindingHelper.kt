package com.mooc.koltin_demo_test.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mooc.koltin_demo_test.R

/**
 *  @JvmStatic 必须要添加
 *
 */
object DataBindingHelper {
    @JvmStatic
    @BindingAdapter("imasrc")
    fun setImages(iv: ImageView, imasrc:String?){
        imasrc?.let {
            Glide.with(iv.context).load("http:$imasrc")
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(iv)
        }
    }
}