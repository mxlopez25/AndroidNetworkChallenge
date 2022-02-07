package com.mxlopez.androidnetworkchallange

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.TypedArrayUtils.getText
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
        }
    ) {
        LazyColumn (modifier = Modifier.fillMaxWidth()) {
            items(vm.characterList) { character ->
                Helper.setCharacter(character)
                Row(modifier = Modifier.fillMaxWidth().clickable { Toast.makeText(
                    context,
                    "Selected: ${character.name}",
                    Toast.LENGTH_SHORT
                ).show() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.size(100.dp, 80.dp)) {
                        Card(
                            modifier = Modifier
                                .padding(5.dp)
                                .size(75.dp, 75.dp),
                            backgroundColor = Color.Black
                        ) {
                            Image(
                                painter = rememberImagePainter(character.image),
                                contentDescription = "Character Avatar"
                            )
                        }
                    }
                    Column(modifier = Modifier.size(250.dp, 75.dp)) {
                        Text(
                            text = "Name: ${character.name}",
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Clip
                        )
                        Text(text = "Species: ${character.species}", fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp))
            }
        }
    }
}





