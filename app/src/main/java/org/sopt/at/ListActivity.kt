package org.sopt.at

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class ListActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel by viewModels<MainViewModel>()

        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting2(
                       music = viewModel.musics
                    )
                }
            }
        }
    }
}


//data class Music(
//    val ranking : Int,
//    val title : String,
//    val singer : String
//)
//
//val musics = listOf<Music>(
//    Music(
//        ranking = 1,
//        title = "TOO BAD",
//        singer = "G-DRAGON"
//    ),
//    Music(
//        ranking = 2,
//        title = "모르시나요",
//        singer = "조째즈"
//    ),
//    Music(
//        ranking = 3,
//        title = "like JENNIE",
//        singer = "제니 (JENNIE)"
//    ),
//    Music(
//        ranking = 4,
//        title = "Drowning",
//        singer = "WOODZ"
//    ),
//    Music(
//        ranking = 5,
//        title = "HOME SWEET HOME",
//        singer = "G-DRAGON"
//    ),
//    Music(
//        ranking = 6,
//        title = "나는 반딧불",
//        singer = "황가람"
//    ),
//    Music(
//        ranking = 7,
//        title = "REBEL HEART",
//        singer = "IVE (아이브)"
//    ),
//    Music(
//        ranking = 8,
//        title = "Whiplash",
//        singer = "aespa"
//    ),
//    Music(
//        ranking = 9,
//        title = "오늘만 I LOVE YOU",
//        singer = "BOYNEXTDOOR"
//    ),
//    Music(
//        ranking = 10,
//        title = "APT.",
//        singer = "로제 (ROSE), Bruno Mars"
//    ),
//)



@Composable
fun Greeting2(
    music: List<MainViewModel.Music>
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(music) { music ->

            Text(
                modifier = Modifier.padding(16.dp),
                text = music.ranking.toString()
            )
            Column {
                Text(
                    text = music.title
                )
                Text(
                    text = music.singer
                )
            }

        }

    }
}
