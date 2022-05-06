
package com.example.jetnews.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.jetnews.JetnewsApplication
import com.example.jetnews.utils.rememberWindowSizeClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appContainer = (application as JetnewsApplication).container
        setContent {
            val windowSizeClass = rememberWindowSizeClass()
            JetnewsApp(appContainer, windowSizeClass)
        }
    }
}
