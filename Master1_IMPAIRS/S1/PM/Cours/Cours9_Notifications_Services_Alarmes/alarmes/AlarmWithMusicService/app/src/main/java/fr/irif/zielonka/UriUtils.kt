package fr.irif.zielonka

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.Uri

fun getUriFromRes  (context: Context, resId : Int) : Uri {
    val res: Resources = context.resources
    val b  =   StringBuilder().append( ContentResolver.SCHEME_ANDROID_RESOURCE )
        .append("://").append(res.getResourcePackageName(resId))
        .append("/").append(res.getResourceTypeName(resId))
        .append("/").append(res.getResourceEntryName(resId))
        .toString()
    return Uri.parse( b );
}