package com.inovasiti.kucingapp

import android.content.Context
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
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
fun ImageView.loadImage(url : String?, @Nullable progress: CircularProgressDrawable){
    //Provides type independent options to customize loads with Glide.
    val options =RequestOptions()
        .placeholder(progress)
        .error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

//BindingAdapter will make a function available in our xml layout,
// android:imageUrl will is a name of element to be used in xml tag. go to item_cat.xml
@BindingAdapter("android:imageUrl")
fun loadImage(view : ImageView, url : String?){
    //why url: String? ... ? means accepting null parameter. By default all variables in kotlin are non null

    view.loadImage(url, getProgressDrawable(view.context))

}