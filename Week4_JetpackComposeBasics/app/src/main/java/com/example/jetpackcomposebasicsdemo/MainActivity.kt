package com.example.jetpackcomposebasicsdemo

import android.os.Bundle
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasicsdemo.ui.theme.JetpackComposeBasicsDemoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("OnCreate is called")
        enableEdgeToEdge()
        setContent {
            JetpackComposeBasicsDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Screen(modifier = Modifier.padding(innerPadding))
//                     ModDemo(modifier = Modifier.padding(innerPadding))

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







