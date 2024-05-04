package com.amicus.letswatch.Screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amicus.letswatch.MainViewModel
import com.amicus.letswatch.data.models.MoviesItem

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {

    val allmovies = viewModel.allmovies.observeAsState(listOf()).value
    allmovies.forEach { Log.d("check data","ID:${it.id} \"name ${it.name}"  ) }

    Surface (modifier = Modifier
        .fillMaxSize())
    {
    LazyColumn (modifier = Modifier.padding(top = 15.dp)){
        items(allmovies.take(allmovies.size)){ item ->
           MoviesItem(item = item)
        }
    }
    }
}

@Composable
fun MoviesItem(item: MoviesItem) {
    Row (modifier = Modifier.fillMaxWidth())
    {
    Text(text = item.id.toString())
    Text(text = item.name)
    }
}