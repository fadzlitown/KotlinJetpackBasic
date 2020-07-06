package com.inovasiti.kucingapp

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun getProgressDrawable(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

// can extend the Android native Library with our own fun after "." by ImageView.loadImage
fun ImageView.loadImage(url : String?, progress: CircularProgressDrawable){
    //Provides type independent options to customize loads with Glide.
    val options =RequestOptions()
        .placeholder(progress)
        .error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}