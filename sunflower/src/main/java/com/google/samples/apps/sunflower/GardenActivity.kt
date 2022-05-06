
package com.google.samples.apps.sunflower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.google.samples.apps.sunflower.databinding.ActivityGardenBinding

class GardenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView<ActivityGardenBinding>(this, R.layout.activity_garden)
    }
}
