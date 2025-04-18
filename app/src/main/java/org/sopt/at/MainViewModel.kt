package org.sopt.at

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    data class Music(
        val ranking : Int,
        val title : String,
        val singer : String
    )

    val musics = listOf<Music>(
        Music(
            ranking = 1,
            title = "TOO BAD",
            singer = "G-DRAGON"
        ),
        Music(
            ranking = 2,
            title = "모르시나요",
            singer = "조째즈"
        ),
        Music(
            ranking = 3,
            title = "like JENNIE",
            singer = "제니 (JENNIE)"
        ),
        Music(
            ranking = 4,
            title = "Drowning",
            singer = "WOODZ"
        ),
        Music(
            ranking = 5,
            title = "HOME SWEET HOME",
            singer = "G-DRAGON"
        ),
        Music(
            ranking = 6,
            title = "나는 반딧불",
            singer = "황가람"
        ),
        Music(
            ranking = 7,
            title = "REBEL HEART",
            singer = "IVE (아이브)"
        ),
        Music(
            ranking = 8,
            title = "Whiplash",
            singer = "aespa"
        ),
        Music(
            ranking = 9,
            title = "오늘만 I LOVE YOU",
            singer = "BOYNEXTDOOR"
        ),
        Music(
            ranking = 10,
            title = "APT.",
            singer = "로제 (ROSE), Bruno Mars"
        ),
    )
}