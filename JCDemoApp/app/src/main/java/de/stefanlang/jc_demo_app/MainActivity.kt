package de.stefanlang.jc_demo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.stefanlang.jc_demo_app.ui.theme.JCDemoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainActivityViewModel>()

        setContent {
            JCDemoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AddButtonExample( viewModel)
                }
            }
        }
    }
}

@Composable
fun AddButtonExample(viewModel: MainActivityViewModel) {
    val count = viewModel.count

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Card(Modifier.size(80.dp), shape = CircleShape, elevation = 4.dp) {
            Column(modifier = Modifier
                .fillMaxSize()
                .testTag("button")
                .clickable {
                    viewModel.onButtonClicked()
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Add", style = MaterialTheme.typography.body1 )
                Text(modifier = Modifier.testTag("sum"), text = "${count}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JCDemoAppTheme {
        AddButtonExample( MainActivityViewModel())
    }
}