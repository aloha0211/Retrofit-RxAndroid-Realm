package ominext.com.kotlinretrofitrxandroidrealm.adapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * Created by LuongHH on 6/14/2017.
 */
class ViewBindingAdapter {

    companion object {
        @JvmStatic @BindingAdapter("url")
        fun loadImage(view: ImageView, url: String) {
            Glide.with(view.context).load(url).into(view)
        }
    }
}