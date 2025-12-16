package co.id.hn0001.app_config.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import co.id.hn0001.app_config.ui.theme.DeepOrange200
import co.id.hn0001.app_config.ui.theme.DeepOrange400
import co.id.hn0001.app_config.ui.theme.Dimens
import co.id.hn0001.app_config.ui.theme.ConfigAppTheme

@Composable
fun Note(
    modifier: Modifier = Modifier,
    note: String = "Lorem ipsum",
    noteAnnotated: AnnotatedString? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(Dimens.x2))
            .background(DeepOrange200)
    ) {
        Box(
            modifier = Modifier
                .width(Dimens.x2)
                .fillMaxHeight()
                .background(DeepOrange400)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = Dimens.x3,
                    end = Dimens.x5,
                    top = Dimens.x4,
                    bottom = Dimens.x4
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Icons Info"
                )
                Spacer(modifier = Modifier.width(Dimens.x3))
                Text(
                    text = "NOTE",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(Dimens.x2))

            if (noteAnnotated != null) {
                Text(text = noteAnnotated)
            } else {
                Text(text = note)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotePreview() {
    ConfigAppTheme {
        Note()
    }
}

@Preview(showBackground = true)
@Composable
fun NoteWithAnnotatedPreview() {
    ConfigAppTheme {
        Note(
            noteAnnotated = AnnotatedString("This is an annotated string with more details about the note content.")
        )
    }
}
