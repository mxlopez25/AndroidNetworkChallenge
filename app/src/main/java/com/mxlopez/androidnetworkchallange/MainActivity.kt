package com.mxlopez.androidnetworkchallange

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.mxlopez.androidnetworkchallange.models.CharacterModel
import com.mxlopez.androidnetworkchallange.ui.theme.AndroidNetworkChallangeTheme
import com.mxlopez.androidnetworkchallange.viewmodels.CharactersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mainActivityViewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)
        setContent {
            MaterialTheme {
                MainComponent(mainActivityViewModel, applicationContext)
            }
        }
    }
}

@Composable
fun MainComponent(vm: CharactersViewModel, context: Context) {
            LaunchedEffect(Unit, block = {
                EspressoIdlingResource.increment()
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
                                Helper.setCharacter(character)
                                Column {
//                                    if(character.status == "alive") {
                                        Row (modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                            Box(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(0.dp, 0.dp, 16.dp, 0.dp)) {
                                                Row {
                                                    Column {
                                                        Card(modifier = Modifier
                                                            .size(70.dp)
                                                            .padding(5.dp), backgroundColor = Color.Black) {
                                                            Image(
                                                                painter = rememberImagePainter(character.image),
                                                                contentDescription = "Character Avatar",
                                                                modifier = Modifier.fillMaxSize()
                                                            )
                                                        }
                                                    }
                                                    Column {
                                                        Row() {
                                                            Text("Name: ", fontWeight = FontWeight.Bold)
                                                            Text(character.name)
                                                        }
                                                        Row() {
                                                            Text(text = "Species: ", fontWeight = FontWeight.Bold)
                                                            Text(character.species)
                                                        }
                                                    }
                                                    Column {
                                                        Button(
                                                            onClick = {
                                                                Log.d(
                                                                    "ButtonTest",
                                                                    character.name
                                                                )
                                                            },
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .fillMaxHeight()
                                                        ) {
                                                            Text(text = "Click here for test")
                                                        }
                                                    }
                                                }
                                            }
                                            Spacer(modifier = Modifier.width(16.dp))

                                        }
//                                    }
                                }
                            }
                        }
                    }
                }
            )
}




