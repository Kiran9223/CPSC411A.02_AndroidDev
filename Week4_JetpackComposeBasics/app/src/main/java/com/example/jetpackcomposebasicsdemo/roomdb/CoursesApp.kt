package com.example.jetpackcomposebasicsdemo.roomdb

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun CourseApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val viewModel: CourseViewModel = viewModel { CourseViewModel(context) }

    NavHost(
        navController = navController,
        Routes.HOME
    ) {
        //home
        composable(Routes.HOME) {
            HomeScreen(navController, viewModel)
        }

        //course details
        composable(Routes.COURSE,
            listOf(navArgument(
                "courseId"
            ) {type = NavType.StringType}) )
        {

            backStackEntry ->
            val id = backStackEntry.arguments?.getString("courseId") ?: ""

            CourseScreen(id, navController, viewModel)


        }


    }
}

// Home Screen
@Composable
fun HomeScreen(navController: NavController, viewModel: CourseViewModel) {

    val courses by viewModel.courses.collectAsState()

    Column (modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Home Screen")
        Spacer(modifier = Modifier.height(8.dp))

        courses.forEach {
            course ->
            Card (modifier = Modifier.fillMaxWidth().clickable {
                navController.navigate((Routes.course(course.id)))
            }) {
                Text(course.name)
                Spacer(Modifier.height(8.dp))

                IconButton(onClick = {
                    viewModel.toggleFavorite(course)
                }) {
                    Icon(imageVector = if(course.isFavorite)
                        Icons.Filled.Favorite
                        else
                        Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }

            }
        }

    }

}

// Course Details Screen
@Composable
fun CourseScreen(courseId: String, navController: NavController, viewModel: CourseViewModel) {
    // fetch the data
//    val course = getCourse(courseId)
    val courses by viewModel.courses.collectAsState()
    val course = courses.find { it.id == courseId }


    if(course == null) {
        Text("Course is not found")
        return
    }

    Column (modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(course.name)
        Spacer(Modifier.height(8.dp))

        IconButton(onClick = {
            viewModel.toggleFavorite(course)
        }) {
            Icon(imageVector = if(course.isFavorite)
                Icons.Filled.Favorite
            else
                Icons.Filled.FavoriteBorder,
                contentDescription = "Favorite"
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = if(course.isFavorite)
            "This course if a Favorite course"
            else
            "Tap- the button to mark this course as Favoprite"
        )
    }

}


















