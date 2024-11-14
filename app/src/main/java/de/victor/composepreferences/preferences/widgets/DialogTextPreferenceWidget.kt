package de.victor.composepreferences.preferences.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.victor.composepreferences.preferences.data.PreferenceData

@Composable
fun DialogTextPreferenceWidget(
    data : PreferenceData.DialogTextPreference,
    modifier: Modifier = Modifier
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    TextPreferenceWidget(modifier = modifier,
       data = PreferenceData.TextPreference(
           commons = data.commons,
           onClick = { showDialog = true }
       )
    )

    if (showDialog) {
        Dialog(
            onConfirm = {
                data.onConfirm()
                showDialog = false
            },
            onDismiss = { showDialog = false },
            title = data.dialogTitle,
            text = data.dialogText,
            buttonText = data.dialogButtonText
        )
    }

}

@Composable
fun Dialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    title: String,
    text: String,
    buttonText: String
    ) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = buttonText)
            }
        },
        title = {
            Text(text = title, style = MaterialTheme.typography.headlineSmall) },
        text = {
            Text(text = text, style = MaterialTheme.typography.bodyMedium) }

    )
}

@Preview
@Composable
private fun DialogPreview() {
    Dialog({}, {}, "Reset All Progress", "This Step can not be reversed!", "Delete")
}