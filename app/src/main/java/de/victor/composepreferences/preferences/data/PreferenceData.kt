package de.victor.composepreferences.preferences.data

import androidx.compose.ui.graphics.vector.ImageVector



sealed class PreferenceData(
    open val commons : Commons,
){

    data class Commons(
        val title: String,
        val enabled: Boolean = true,
        val summary: String? = null,
        val icon: ImageVector? = null,
        val iconSpaceReserved: Boolean = false,
    )

    data class TextPreference(
        override val commons: Commons,
        val onClick : (() -> Unit)? = null
    ) : PreferenceData(commons)

    data class SwitchPreference(
        override val commons: Commons,
        val key: String,
        val default : Boolean = false,
    ) : PreferenceData(commons = commons)

    data class DialogTextPreference(
        override val commons: Commons,
        val dialogTitle : String,
        val dialogText: String,
        val dialogButtonText: String,
        val onConfirm : () -> Unit
    ) : PreferenceData(commons = commons)

}
