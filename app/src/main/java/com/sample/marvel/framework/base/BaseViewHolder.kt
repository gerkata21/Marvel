package com.sample.marvel.framework.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val requestOptions = RequestOptions().override(300, 300)

    abstract fun clear()

    abstract fun displayImage(image: String)

    abstract fun displayGif(image: String)

    open fun bindViews(position: Int) {
        clear()
    }
}
