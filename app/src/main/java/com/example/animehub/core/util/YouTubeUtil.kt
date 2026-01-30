package com.example.animehub.core.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

object YouTubeUtil {
    fun extractYoutubeId(embedUrl: String): String? {
        return embedUrl.substringAfter("/embed/", "")
            .substringBefore("?")
            .takeIf { it.isNotBlank() }
    }

    fun openYouTube(context: Context, youtubeId: String?) {
        val appIntent = Intent(
            Intent.ACTION_VIEW,
            "vnd.youtube:$youtubeId".toUri()
        )

        val webIntent = Intent(
            Intent.ACTION_VIEW,
            "https://www.youtube.com/watch?v=$youtubeId".toUri()
        )

        try {
            context.startActivity(appIntent) // Opens YouTube app
        } catch (e: ActivityNotFoundException) {
            context.startActivity(webIntent) // Fallback to browser
        }
    }
}