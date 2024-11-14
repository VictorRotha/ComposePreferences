package de.victor.composepreferences.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import de.victor.composepreferences.preferences.data.PreferenceData

@Composable
fun PreferenceGroup(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector? = null,
    iconSpaceReserved : Boolean = false,
    preferenceData: List<PreferenceData> = emptyList(),
    preferences: Preferences?,
    dataStoreManager : DataStoreManager
) {

    Column(modifier = modifier) {

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        )
         {

            val iconModifier = Modifier.padding(end = 8.dp).size(24.dp)
            if (icon != null)
                Icon(
                    modifier = iconModifier,
                    imageVector = icon, contentDescription = null
                )
             else if (iconSpaceReserved) {
                 Spacer(modifier = iconModifier)
            }

            Column {

                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 24.sp
                )

               HorizontalDivider(
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp),
                    thickness = 1.dp,
                    color = Color.Black
                )
            }

        }

        for (data in preferenceData) {

            Preference(
                modifier = Modifier.padding(top = 8.dp),
                data = data,
                preferences = preferences,
                dataStoreManager = dataStoreManager
            )

        }
    }

}

@Preview (showBackground = true)
@Composable
private fun GroupPreview() {

    val context = LocalContext.current
    PreferenceGroup(
        title = "Preference Group",
        icon = Icons.Default.Call,
        iconSpaceReserved = false,
        preferences = null,
        dataStoreManager = DataStoreManager(context.datastore),
        preferenceData = listOf(
            PreferenceData.SwitchPreference(
                    commons = PreferenceData.Commons(
                        title = "Pref A",
                        summary = "Pref A Summary",
                        iconSpaceReserved = true),
                    key = "",
                    default = true
                ),
            PreferenceData.TextPreference(
                    commons = PreferenceData.Commons(
                        title = "Pref B",
                        summary = "This is Preference B",
                        icon = Icons.Default.Settings),
        )

    )
    )
}