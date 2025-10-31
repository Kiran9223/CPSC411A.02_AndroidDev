package com.example.jetpackcomposebasicsdemo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasicsdemo.ui.theme.JetpackComposeBasicsDemoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.example.jetpackcomposebasicsdemo.assignment1solution.ToDo
import com.example.jetpackcomposebasicsdemo.navigation.ShoppingApp
import com.example.jetpackcomposebasicsdemo.navigation2.CourseApp
import com.example.jetpackcomposebasicsdemo.retrofit.UserScreen
import com.example.jetpackcomposebasicsdemo.viewmodel.CountScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("OnCreate is called")
        enableEdgeToEdge()
        setContent {
            JetpackComposeBasicsDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Screen(modifier = Modifier.padding(innerPadding))
//                     ModDemo(modifier = Modifier.padding(innerPadding))
//                        MyCourseApp(modifier = Modifier.padding(innerPadding))
//                    MyShop(modifier = Modifier.padding(innerPadding))
//                    CounterScreen(modifier = Modifier.padding(innerPadding))
//                    CourseDBApp(modifier = Modifier.padding(innerPadding))
                        UserApp(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        println("OnStart is called")
    }

    override fun onResume() {
        super.onResume()
        println("OnResume is called")
    }

    override fun onPause() {
        super.onPause()
        println("OnPause is called")
    }

    override fun onStop() {
        super.onStop()
        println("OnStop is called")
    }

    override fun onRestart() {
        super.onRestart()
        println("OnRestart is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("OnDestroy is called")
    }
}

// States Demo
@Composable
fun Screen(modifier: Modifier) {
//    StatefulComp(modifier)
    StatelessComp(modifier)
}

@Composable
fun StatelessComp(modifier: Modifier) {
        var count by rememberSaveable { mutableStateOf(0) }
        var juice by rememberSaveable { mutableStateOf(5) }

    Column(modifier = modifier) {
        WaterCounter(count, {count++}, modifier)
        JuiceCounter(juice, {juice++}, {juice = 0}, modifier)
    }

}

@Composable
fun WaterCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier) {
    Column(modifier = modifier) {
        Text("You had ${count} glasses of water")
        Button(onClick = onIncrement) {
            Text("Add one")
        }
    }
}

@Composable
fun JuiceCounter(
    count: Int,
    onIncrement: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier) {
    Column(modifier = modifier) {
        Text("You had ${count} glasses of juice")
        Button(onClick = onIncrement) {
            Text("Add one")
        }

        if(count > 10) {
            Button(onClick= onReset) {
                Text("Reset")
            }
        }
    }
}

@Composable
fun StatefulComp(modifier: Modifier) {
    var count  by rememberSaveable { mutableStateOf(0) }
    Column(modifier = modifier) {
        Text("You had ${count} glasses of water")
        Button(onClick = {count++}) {
            Text("Add one")
        }
    }
}



// Modifier demo

@Composable
fun ModDemo(modifier: Modifier) {
    Column(modifier = modifier) {
        Text("Hello")

        Text("With padding",
            modifier = Modifier.padding(16.dp)
        )

        Text("With bg and border",
            modifier = Modifier.background(Color.Cyan)
                .border(2.dp, Color.Green)
            )

        Text("With clickable",
            modifier = Modifier.clickable {
                println("Clicked")
            }
        )
    }

}

@Preview
@Composable
fun ModDemoPreview() {
    JetpackComposeBasicsDemoTheme {
        ModDemo(modifier = Modifier)
    }
}

// View Model demo
@Composable
fun CounterScreen(modifier: Modifier) {
    Column(modifier = modifier) {
        CountScreen()
    }

}

//***************************************************
// navigation demo

@Composable
fun MyShop(modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        ShoppingApp()
    }

}


// ******************************************************
// navigation part 2 demo
@Composable
fun MyCourseApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CourseApp()
    }
}


// ******************************************************
// data persistence - room db
@Composable
fun CourseDBApp(modifier: Modifier) {
    Column(modifier ) {
        com.example.jetpackcomposebasicsdemo.roomdb.CourseApp()
    }
}

// ******************************************************
// Retrofit demo
@Composable
fun UserApp(modifier: Modifier) {
    Column(modifier) {
        UserScreen()
    }
}





