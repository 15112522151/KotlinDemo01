package com.example.day01

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.day01.ui.theme.Day01Theme

class MainActivity : ComponentActivity() {
    private var mContext: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        enableEdgeToEdge()
        setContent {
            //  Day01Theme {
            Surface(
                Modifier
                    .padding(0.dp, 25.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
            ) {
                Conversation(getMessageList())
            }
            //       }
        }
    }
}

data class Message(val aaa: String, val bbb: String, val position: Int)

fun getMessageList(): List<Message> {
    val list: MutableList<Message> = mutableListOf()
    for (i in 0..20) {
        list.add(
            Message(
                "GXM$i",
                "name:kotlin,kotlin,kotlin,kotlin,kotlin,kotlin，kotlin,kotlin,kotlin,kotlin,kotlin,kotlin,kotlin,kotlin,kotlin，kotlin,kotlin,kotlin,kotlin,kotlin,kotlin,kotlin,kotlin,kotlin，kotlin,kotlin,kotlin,kotlin,kotlin,kotlin,kotlin,kotlin,kotlin，kotlin,kotlin,kotlin,kotlin",
                position = i
            )
        )
    }
    return list
}

@Composable
fun Conversation(list: List<Message>) {
    // CustView(null)‘
    //维护选中状态:存储当前选中的 Message 的 id，初始为空 (没有选中)
    var selectedItemId by remember { mutableStateOf<String?>(null) }
    LazyColumn {
        items(list) { item: Message ->
            //检查当前item 是否被选中
            val isSelected = item.position.toString() === selectedItemId
            val bgColor = if (isSelected) Color.Yellow else Color.White
            MessageCard(item, bgColor, onClick = {
                // 更新选中状态：如果已经是选中的，可以取消选中（可选），否则选中它
                selectedItemId = if (isSelected) null else item.position.toString()
            })
        }
    }
}

/**
 * 消息列表item布局组合
 */
@Composable
fun MessageCard(message: Message, bgColor: Color, onClick: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(all = 8f.dp)
            .background(Color.White)
            .clickable {
                //跳转界面
                val intent = Intent(context, SecondActivity::class.java)
                context.startActivity(intent)
            },
        //设置上下居中
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "描述",
            Modifier
                .size(50.dp)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            Modifier
                .clickable(onClick = onClick)
                .background(bgColor)
        ) {
            Text(
                text = message.aaa,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = message.bbb,
                modifier = Modifier.padding(horizontal = 4.dp),
                color = Color.Gray,
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 22.sp,

                )
        }
    }


}

@Preview(showBackground = true, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
fun GreetingPreview() {
    Day01Theme {
        Surface(
            Modifier
                .padding(0.dp, 25.dp, 0.dp, 10.dp)
                .width(300.dp)
                .height(500.dp), color = Color.LightGray
        ) {
            Conversation(getMessageList())
        }

    }
}