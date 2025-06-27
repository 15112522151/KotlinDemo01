package com.example.day01

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  enableEdgeToEdge()
        setContent {
            Box(
                Modifier.fillMaxSize(), Alignment.Center
            ) {
                //居中文本
                Surface(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp), color = Color.LightGray
                ) {
                    Box(Modifier
                        .fillMaxSize()
                        .clickable {
                            Log.i("gxmaaa", "---点击了Box---")
                        }, Alignment.Center) {
                        Text(
                            text = "第二个界面",
                            color = Color.Red,
                            fontSize = 24.sp
                        )
                    }

                }
            }


        }
    }

}