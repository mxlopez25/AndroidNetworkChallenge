package com.mxlopez.androidnetworkchallange

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxlopez.androidnetworkchallange.ui.theme.AndroidNetworkChallangeTheme
import com.mxlopez.androidnetworkchallange.viewmodels.CharactersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = CharactersViewModel()
        setContent {
            MaterialTheme {
                MainComponent(vm, applicationContext)
            }
        }
    }
}

@Composable
fun MainComponent(vm: CharactersViewModel, context: Context) {
            LaunchedEffect(Unit, block = {
                vm.getCharacterList(context)
            })

            Scaffold(
                topBar = {
                    TopAppBar() {
                        Text(text = "Characters")
                    }
                },
                content = {
                    Column(modifier = Modifier.padding(16.dp)) {
                        LazyColumn(modifier = Modifier.fillMaxHeight()) {
                            items(vm.characterList) { character ->
                                Column {
                                    Row (modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Box(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(0.dp, 0.dp, 16.dp, 0.dp)) {
                                            Column {
                                                Card(modifier = Modifier.size(48.dp), backgroundColor = Color.Black) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.ic_test),
                                                        contentDescription = "Character Avatar",
                                                        modifier = Modifier.fillMaxSize()
                                                    )
                                                }
                                            }
//                                            Column {
//                                                Row() {
//                                                    Text("Name: ", fontWeight = FontWeight.Bold)
//                                                    Text(character.name)
//                                                }
//                                                Row() {
//                                                    Text(text = "Species: ", fontWeight = FontWeight.Bold)
//                                                    Text(character.species)
//                                                }
//                                            }
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))

                                    }
                                }
                            }
                        }
                    }
                }
            )
}




