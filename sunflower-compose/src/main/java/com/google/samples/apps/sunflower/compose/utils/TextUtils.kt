
package com.google.samples.apps.sunflower.compose.utils

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Load a string with grammatically correct pluralization for the given quantity,
 * using the given arguments.
 *
 * TODO: Remove when https://issuetracker.google.com/issues/158065051 is fixed
 *
 * @param id the resource identifier
 * @param quantity The number used to get the correct string for the current language's
 *           plural rules.
 *
 * @return the string data associated with the resource
 */
@Composable
fun getQuantityString(@PluralsRes id: Int, quantity: Int): String {
    val context = LocalContext.current
    return context.resources.getQuantityString(id, quantity, quantity)
}
