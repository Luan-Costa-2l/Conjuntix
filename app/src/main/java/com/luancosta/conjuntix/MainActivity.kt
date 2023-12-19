package com.luancosta.conjuntix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luancosta.conjuntix.ui.theme.ConjuntixTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SetViewModel by viewModels()
            ConjuntixTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Conjuntix(viewModel)
                }
            }
        }
    }
}

// experimental material3api
@Composable
fun Conjuntix(viewModel: SetViewModel) {
    val red = 0XFFff5252
    val green = 0XFF6fbf99
    val yellow = 0XFFf6b93b
    val lightBlue = 0XFF00bcd4

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(text = "Conjunto A", modifier = Modifier.padding(bottom = 8.dp))
        TextField(
            value = viewModel.setA.value,
            onValueChange = { viewModel.handleSetA(it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text(text = "Ex.: 1, 2, 3, 4, 5")}
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Conjunto B", modifier = Modifier.padding(bottom = 8.dp))
        TextField(
            value = viewModel.setB.value,
            onValueChange = { viewModel.handleSetB(it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text(text = "Ex.: 2, 3, 5, 7, 11") },
        )

        Button(onClick = { viewModel.getAllOperation() }, modifier = Modifier.padding(vertical = 16.dp)) {
            Text(text = "Calcular")
        }
        //-----
        Row(verticalAlignment = Alignment.Bottom) {
            Column(modifier = Modifier
                .background(color = Color(red))
                .height(60.dp)
                .width(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "A U B", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(
                    text = viewModel.union.value,
                    modifier = Modifier
                        .padding(vertical = 8.dp).fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    fontSize = 20.sp
                )
                Divider(
                    color = Color(red),
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.Bottom) {
            Column(modifier = Modifier
                .background(color = Color(green))
                .height(60.dp)
                .width(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "A ∩ B", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(
                    text = viewModel.intersection.value,
                    modifier = Modifier
                        .padding(vertical = 8.dp).fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    fontSize = 20.sp
                )
                Divider(
                    color = Color(green),
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.Bottom) {
            Column(modifier = Modifier
                .background(color = Color(yellow))
                .height(60.dp)
                .width(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "A - B", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(
                    text = viewModel.differenceAB.value,
                    modifier = Modifier
                        .padding(vertical = 8.dp).fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    fontSize = 20.sp
                )
                Divider(
                    color = Color(yellow),
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.Bottom) {
            Column(modifier = Modifier
                .background(color = Color(lightBlue))
                .height(60.dp)
                .width(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "B - A", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(
                    text = viewModel.differenceBA.value,
                    modifier = Modifier
                        .padding(vertical = 8.dp).fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    fontSize = 20.sp
                )
                Divider(
                    color = Color(lightBlue),
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.clearAll() }, modifier = Modifier.padding(vertical = 16.dp)) {
            Text(text = "Limpar")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ConjuntixPreview() {
    val viewModel: SetViewModel = viewModel()
    Conjuntix(viewModel)
}