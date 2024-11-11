package de.victor.composepreferences

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import de.victor.composepreferences.preferences.PreferenceScreen
import de.victor.composepreferences.ui.theme.ComposePreferencesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposePreferencesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    PreferenceScreen()
                }
            }
        }

    }

    companion object {

        const val KEY_ONE = "key one"
        const val KEY_TWO = "key two"
        const val KEY_THREE = "key three"
        const val KEY_FOUR = "key four"
        const val KEY_FIVE = "key five"
    }
}

