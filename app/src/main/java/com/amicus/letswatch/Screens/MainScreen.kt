package com.amicus.letswatch.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.amicus.letswatch.MainViewModel
import com.amicus.letswatch.data.models.MoviesItem
import com.amicus.letswatch.navigation.Screens

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {

    val allmovies = viewModel.allmovies.observeAsState(listOf()).value

    Surface (modifier = Modifier
        .fillMaxSize())
    {
    LazyColumn (modifier = Modifier
        .padding(20.dp)){
        items(allmovies.take(allmovies.size)){ item ->
           MoviesItem(item = item,navController = navController)
        }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesItem(item: MoviesItem,navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp),
        onClick = {
                  navController.navigate(Screens.Details.route + "/${item.id}")
        },
        elevation = cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Image(
                painter = rememberImagePainter(item.image.original),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )

            Column {
                Text(text = item.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Row {
                    Text(text = "Rating: ", fontWeight = FontWeight.Bold)
                    Text(text = item.rating.average.toString())
                }
                Row {
                    Text(text = "Жанр: ", fontWeight = FontWeight.Bold)
                    item.genres.take(2).forEach { Text(text = "$it") }
                }
                Row {
                    Text(text = "Premired: ", fontWeight = FontWeight.Bold)
                    Text(text = item.premiered)
                }
            }
        }
    }
}