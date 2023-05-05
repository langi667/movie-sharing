package de.stefanlang.moviesharingjc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.stefanlang.moviesharingjc.detail.MoviesDetailView
import de.stefanlang.moviesharingjc.detail.MoviesDetailViewModel
import de.stefanlang.moviesharingjc.ui.theme.MovieSharingJCTheme
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moviesDetailViewModel: MoviesDetailViewModel by viewModels()

        lifecycleScope.launch(Dispatchers.IO + CoroutineName("MyCoroutine")) {
            delay(1000L)
            val name = this.coroutineContext.get(CoroutineName.Key) // MyCoroutine
        }

        setContent {
            MovieSharingJCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainView(moviesDetailViewModel)
                }
            }
        }
    }
}

@Composable
fun MainView(viewModel: MoviesDetailViewModel) {
    Box(contentAlignment = Alignment.Center) {
        MoviesDetailView(viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieSharingJCTheme {
        MainView(MoviesDetailViewModel())
    }
}
