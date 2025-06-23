package com.example.moviesapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesapp.data.MovieData
import com.example.moviesapp.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movie App") }
            )
        }
    ) { scaffoldPadding ->
        LazyColumn(
            contentPadding = scaffoldPadding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(MovieData.movieList) { movie ->
                MovieItem(movie = movie){
                    navController.navigate("detail/${movie.id}")
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(
                modifier = Modifier.width(12.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = movie.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier.height(4.dp)
                )
                Text(
                    text = "Rating: ${movie.rating}"
                )
                Text(
                    text = "Year: ${movie.year}"
                )
            }

        }
    }
}
