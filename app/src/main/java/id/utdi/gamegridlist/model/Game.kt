package id.utdi.gamegridlist.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Game(
    @StringRes val title: Int,
    @StringRes val genre: Int,
    @DrawableRes val image: Int
)
