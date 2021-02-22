package com.example.dogs.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dogs.R

fun getProgressDrawable(context : Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth=10f
        centerRadius=50f
        start()
    }
}

fun ImageView.loadImage(uri:String?,progressDrawable: CircularProgressDrawable){
    val options=RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)

    if(uri!=null){

    }else{

    }

    uri?.let {
        Glide.with(context).setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
    }?: kotlin.run {  }

}


fun isNetworkAvailable(context: Context?): Boolean {
    if (context == null) return false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }

    return false
}


fun View.setBackgroundDrawableColor(parsedColor: Int) {
    (background as? GradientDrawable)?.apply {
        setColor(parsedColor)
    }
}

