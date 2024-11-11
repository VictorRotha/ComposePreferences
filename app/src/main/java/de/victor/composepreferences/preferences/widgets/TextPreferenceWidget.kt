package de.victor.composepreferences.preferences.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.victor.composepreferences.preferences.data.PreferenceData


fun Modifier.conditional(condition : Boolean, modifier: Modifier) : Modifier{

    return if (condition)
        this.then(modifier)
    else
        this
}

@Composable
fun TextPreferenceWidget(
    data : PreferenceData.TextPreference,
    modifier: Modifier = Modifier,
    trailing: @Composable (() -> Unit)? = null
) {

    val commons = data.commons

    Row(
        modifier = modifier
            .fillMaxWidth()
            .conditional(data.onClick != null, Modifier.clickable { data.onClick?.invoke() }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,

    ) {

        val iconModifier = Modifier.padding(end = 8.dp).size(24.dp)

        if (commons.icon != null)
            Icon(modifier = iconModifier,
                imageVector = commons.icon,
                contentDescription = null)
        else if (commons.iconSpaceReserved)
            Spacer(modifier = iconModifier)

        Column(
            modifier = modifier
                .padding(end = 8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = commons.title,
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleSmall
            )

            commons.summary?.let {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text =  commons.summary,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodySmall
                )
            }


        }

        if (trailing != null) {
            trailing()
        }
    }


}

@Preview (showBackground = true)
@Composable
private fun TextPreviewIconTrailing() {
    TextPreferenceWidget(
        data = PreferenceData.TextPreference(
            commons = PreferenceData.Commons(
                title = "License",
                summary = "Blah, Blah, Blah Blah, Blah, BlahBlah, Blah, Blah...   Blah, Blah, Blah ... Blah, Blah, Blah ... Blah, Blah, Blah ...",
                icon = Icons.Default.AccountCircle,
            )),
            trailing = {
                Switch(
                    modifier = Modifier.scale(.8f), checked = true, onCheckedChange = {})

        }
    )

}

@Preview (showBackground = true)
@Composable
private fun TextPreviewIconSpace() {
    TextPreferenceWidget(
        data = PreferenceData.TextPreference(
            commons = PreferenceData.Commons(
                title = "License",
                iconSpaceReserved = true,
            ),
        )
    )
}

@Preview (showBackground = true)
@Composable
private fun TextPreviewNoSum() {
    TextPreferenceWidget(
        PreferenceData.TextPreference(
            commons = PreferenceData.Commons(
                title = "Published under Creative Commons CC-0",
            ),
        )
    )
}

