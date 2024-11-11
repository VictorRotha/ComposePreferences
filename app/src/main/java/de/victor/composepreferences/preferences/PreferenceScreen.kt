package de.victor.composepreferences.preferences

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import de.victor.composepreferences.MainActivity.Companion.KEY_ONE
import de.victor.composepreferences.MainActivity.Companion.KEY_TWO
import de.victor.composepreferences.playground.datastore
import de.victor.composepreferences.preferences.data.PreferenceData
import kotlinx.coroutines.launch

val Context.datastore: DataStore<Preferences> by preferencesDataStore("settings")

@Composable
fun PreferenceScreen(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context.datastore) }

    val prefs by dataStoreManager.preferenceFlow.collectAsState(null)

    Column(modifier = modifier.fillMaxSize()) {

        Preference(
            data = PreferenceData.SwitchPreference(
                commons = PreferenceData.Commons(
                    title = "Darkmode enabled",
                    summary = "Enable Darkmode",

                ),
                key = KEY_ONE,
                default = true,
            ),
            preferences = prefs,
            dataStoreManager = dataStoreManager
        )

        Preference(
            data = PreferenceData.SwitchPreference(
                commons = PreferenceData.Commons(
                    title = "Another Switch",
                    summary = "Some text to describe the Preference. Could be multiple lines.",
                    icon = Icons.Default.Home,
                ),

                key = KEY_TWO,
                default = true,
            ),
            preferences = prefs,
            dataStoreManager = dataStoreManager
        )

        Preference(
            data = PreferenceData.TextPreference(
                commons = PreferenceData.Commons(
                    title = "Clickable Preference",
                    summary = "Some Text ...",
                    iconSpaceReserved = true,
                ),

                onClick = { Log.d("PreferenceScreen2",  "onClick Text Preference!")}
            ),
            preferences = prefs,
            dataStoreManager = dataStoreManager

        )

        Spacer(modifier = Modifier.height(24.dp))

        val scope = rememberCoroutineScope()
        Button(onClick = {
            scope.launch {
                val key = booleanPreferencesKey("key2A")
                val v = dataStoreManager.getPreference(key, true)
                Log.d("PreferenceScreen2", "Set Preference to ${!v}")
                dataStoreManager.editPreference(key, !v)
            }
        }) {
            Text(text = "Toggle Preference")


        }
    }

}

@Preview (showBackground = true)
@Composable
private fun PreferenceScreenPreview() {
    PreferenceScreen()
}


