package com.amicus.letswatch.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.amicus.letswatch.MainViewModel
import com.amicus.letswatch.R
import com.amicus.letswatch.utils.htmlText

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(viewModel: MainViewModel, itemId: String) {
    val cuItems = viewModel.allmovies
        .observeAsState(listOf()).value.firstOrNull {
            it.id == itemId.toInt()
        }

    Scaffold {
        TopAppBar(title = {
            Text(text = stringResource(R.string.app_name))},
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onSecondary )
            )
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 93.dp, horizontal = 8.dp)
    ) {
        LazyColumn {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(cuItems?.image?.medium),
                        contentDescription = null,
                        modifier = Modifier.size(512.dp)
                    )
                    Text(
                        text = cuItems?.name ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Row(modifier = Modifier.padding(top = 8.dp))
                    {
                        Text(
                            text = "Rating :",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = cuItems?.rating?.average.toString(),
                            fontSize = 18.sp
                        )
                    }
                    Row(modifier = Modifier.padding(top = 8.dp))
                    {
                        Text(
                            text = "Жанр :",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        cuItems?.genres?.take(2)?.forEach {
                            Text(
                                text = "[$it]",
                                fontSize = 18.sp
                            )
                        }
                    }
                    htmlText(string = cuItems?.summary ?: "", modifier = Modifier.padding(top = 10.dp))
                }
            }
        }
    }

}
