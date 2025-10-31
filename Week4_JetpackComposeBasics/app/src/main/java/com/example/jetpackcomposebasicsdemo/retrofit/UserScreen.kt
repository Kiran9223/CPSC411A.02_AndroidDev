package com.example.jetpackcomposebasicsdemo.retrofit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items

@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val users = emptyList<User>()
    val isLoading = false
    val errorMessage = null


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Users from API"
        )

        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red
                )
            }

            else -> {
                LazyColumn {
                    items(users) {
                            user ->
                        Card (
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column (
                                modifier = Modifier.padding(16.dp)

                            ) {
                                Text(user.name)
                                Text(user.email)
                                Text(user.id.toString())
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}