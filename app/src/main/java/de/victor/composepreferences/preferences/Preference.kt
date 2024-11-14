package de.victor.composepreferences.preferences

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import de.victor.composepreferences.preferences.data.PreferenceData
import de.victor.composepreferences.preferences.widgets.DialogTextPreferenceWidget
import de.victor.composepreferences.preferences.widgets.SwitchPreferenceWidget
import de.victor.composepreferences.preferences.widgets.TextPreferenceWidget
import kotlinx.coroutines.launch

@Composable
fun Preference(
    modifier: Modifier = Modifier,
    data : PreferenceData,
    preferences: Preferences?,
    dataStoreManager: DataStoreManager,

    ) {
    val scope = rememberCoroutineScope()

    when (data) {
        is PreferenceData.TextPreference -> {
            TextPreferenceWidget(
                modifier = modifier.fillMaxWidth(),
                data = data
            )
        }

        is PreferenceData.DialogTextPreference -> {
            DialogTextPreferenceWidget(
                modifier = modifier,
                data = data
            )

        }

        is PreferenceData.SwitchPreference -> {
            val key = booleanPreferencesKey(data.key)
            SwitchPreferenceWidget(
                modifier = modifier,
                data = data,
                value = preferences?.get(key) ?: data.default,
                onValueChange = { value -> scope.launch { dataStoreManager.editPreference(key, value) } }
            )

        }


    }

}
