package com.google.samples.apps.sunflower

import android.os.Bundle
import androidx.navigation.NavDirections

class HomeViewPagerFragmentDirections private constructor() {
    private data class ActionViewPagerFragmentToPlantDetailFragment(
        val plantId: String
    ) : NavDirections {
        override val actionId: Int
            get() = R.id.action_view_pager_fragment_to_plant_detail_fragment
        override val arguments: Bundle
            get() = Bundle().apply { putString("plantId", plantId) }
    }

    companion object {
        fun actionViewPagerFragmentToPlantDetailFragment(plantId: String): NavDirections =
            ActionViewPagerFragmentToPlantDetailFragment(plantId)
    }
}