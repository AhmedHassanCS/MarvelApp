package com.apps.ahfreelancing.marvelapp.presentation.utilities

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * Created by Ahmed Hassan on 7/28/2019.
 */
object WebUtility {
    fun openURL(activity: Activity, url: String){
        if(url.isEmpty())
            return
        val browserIntent =  Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(browserIntent)
    }
}