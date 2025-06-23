package com.example.moviesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviesapp.data.MovieData
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DetailScreen(movieId: String? , navController: NavController) {

    val movie = movieId?.toIntOrNull()?.let { id ->
        MovieData.movieList.find { it.id == id }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(movie?.title ?: "Movie Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { scaffoldPadding ->
        movie?.let{
            Column(modifier = Modifier.padding(scaffoldPadding).padding(16.dp))  {
                AsyncImage(
                    model = it.posterUrl,
                    contentDescription = it.title,
                    modifier = Modifier.fillMaxWidth().height(300.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = it.title, style = MaterialTheme.typography.headlineSmall)

                Text(text = "Rating: ${it.rating}", style = MaterialTheme.typography.bodyLarge)
            }
        }

    }
}

