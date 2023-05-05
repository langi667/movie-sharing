package de.stefanlang.moviesharingjc.detail

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.stefanlang.moviesharingjc.R
import de.stefanlang.moviesharingjc.core.rating_control.RatingControl
import de.stefanlang.moviesharingjc.ui.theme.Dimen
import de.stefanlang.moviesharingjc.ui.theme.MovieSharingJCTheme
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesDetailView(viewModel: MoviesDetailViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Scaffold(topBar = { TopBar() }) {
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(ScrollState(0))) {
                Header(viewModel.title)
                Text(
                    modifier = Modifier.padding(start = Dimen.s, end = Dimen.s),
                    text = viewModel.description
                )
            }
        }
    }
}

@Composable
private fun TopBar() {
    return TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun Header(title: String) {
    val imageHeight = 160.dp
    val thumbnailHeight = imageHeight

    val thumbnailOffset = (imageHeight.value * -0.3).dp
    val thumbnailWidth = (thumbnailHeight.value * 0.64).dp

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight), painter = painterResource(id = R.drawable.movie),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(thumbnailHeight)
                .padding(start = Dimen.s, end = Dimen.s)
                .offset(y = thumbnailOffset)
        ) {

            ImageLoadingView(
                R.drawable.movie,
                modifier = Modifier
                    .size(width = thumbnailWidth, height = thumbnailHeight),
            )
            Column(modifier = Modifier.padding(start = Dimen.xs)) {
                Column(
                    modifier = Modifier
                        .weight(0.3f, true),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(0.3f, true),
                    verticalArrangement = Arrangement.Center,
                ) {
                    RatingControl()
                }

                Spacer(modifier = Modifier.weight(0.4f, true))
            }
        }
    }
}

@Composable
fun ImageLoadingView(@DrawableRes imgIdRes: Int, modifier: Modifier = Modifier) {
    Card(shape = MaterialTheme.shapes.small, modifier = modifier, elevation = 4.dp) {
        Image(
            painter = painterResource(id = imgIdRes),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesDetailViewPreview() {
    MovieSharingJCTheme {
        MoviesDetailView(MoviesDetailViewModel())
    }
}