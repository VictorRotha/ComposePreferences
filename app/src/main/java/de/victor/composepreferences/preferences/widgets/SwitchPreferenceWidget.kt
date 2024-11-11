package de.victor.composepreferences.preferences.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.victor.composepreferences.preferences.data.PreferenceData


@Composable
fun SwitchPreferenceWidget(
    data : PreferenceData.SwitchPreference,
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,

    ) {

    TextPreferenceWidget(
        modifier = modifier,
        data = PreferenceData.TextPreference(
            commons = data.commons,
        )


    ) {
        Switch(
            checked = value,
            onCheckedChange = onValueChange,
            enabled = data.commons.enabled,
            )
    }




}

@Preview (showBackground = true)
@Composable
private fun SwitchPrev() {
    SwitchPreferenceWidget(
        data = PreferenceData.SwitchPreference(
            commons = PreferenceData.Commons(
                title = "Show Hints",
                summary = "Enable Hints",
                enabled = false,
                icon = Icons.Default.AccountBox,
            ),
            key = "",
            default = false
        ),

        value = true,
        onValueChange = {},

    )
}

@Preview (showBackground = true)
@Composable
private fun SwitchPrevNoIcon() {
    SwitchPreferenceWidget(
        data = PreferenceData.SwitchPreference(
            commons = PreferenceData.Commons(
                title = "Show Hints",
                summary = "Enable Hints",
                enabled = true,
                iconSpaceReserved = true,
            ),
            key = "",
            default = false
        ),
        value = true,
        onValueChange = {},

    )
}