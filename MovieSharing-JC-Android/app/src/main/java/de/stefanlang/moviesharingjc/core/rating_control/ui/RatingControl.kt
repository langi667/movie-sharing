package de.stefanlang.moviesharingjc.core.rating_control
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import de.stefanlang.moviesharingjc.ui.theme.MovieSharingJCTheme
import kotlin.math.ceil

@Composable
fun RatingControl(
    max: Int = 5,
    initial: Int = 0,
    color: Color = Color.DarkGray,
    colorSelected: Color = Color.Red,
    testTag: String = "",
    onRatingChanged: ((Int) -> Unit)? = null
) {

    var width by remember { mutableStateOf<Int>(0) }
    var currRating by remember { mutableStateOf<Int>(initial) }

    Row(modifier = Modifier
        .testTag(testTag)
        .onGloballyPositioned { coordinates ->
            width = coordinates.size.width
        }
        .pointerInput(Unit) {
            detectHorizontalDragGestures(
                onHorizontalDrag = { pointer, _ ->
                    val newRating = ratingForDragPos(width, pointer.position.x.toInt(), max)
                    currRating = updateRating(newRating, currRating, onRatingChanged)
                }
            )
        }
    ) {

        for (value in 1..max) {
            val selected = currRating >= value

            Icon(imageVector = Icons.Outlined.Star,
                contentDescription = contentDescriptionRating(value, selected),
                tint = if (selected) colorSelected else color,
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            currRating = updateRating(value, currRating, onRatingChanged)
                        }
                    )
                }
            )
        }
    }
}

fun contentDescriptionRating(rating: Int, isRated: Boolean): String {
    var retVal = "rating $rating"

    if (isRated) {
        retVal = "selected $retVal"
    }

    return retVal
}

private fun ratingForDragPos(
    overallWidth: Int,
    currentDragPos: Int,
    maxRating: Int,
    adjust: Boolean = true
): Int {
    val percent = currentDragPos.toFloat() / overallWidth.toFloat();
    val ratingFloat = maxRating.toFloat() * percent

    val rating = ceil(ratingFloat.toDouble()).toInt()
    val retVal = if (adjust) {
        adjustRating(rating, maxRating)
    } else {
        rating
    }

    return retVal
}

private fun adjustRating(rating: Int, maxRating: Int): Int {
    val retVal: Int = if (rating < 0) {
        0
    } else if (rating > maxRating) {
        maxRating
    } else {
        rating
    }

    return retVal
}

private fun updateRating(
    newValue: Int,
    currValue: Int,
    onRatingChanged: ((Int) -> Unit)? = null
): Int {

    val retVal = if (newValue != currValue) {
        onRatingChanged?.invoke(newValue)
        newValue
    } else {
        currValue
    }

    return retVal
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieSharingJCTheme {
        RatingControl()
    }
}