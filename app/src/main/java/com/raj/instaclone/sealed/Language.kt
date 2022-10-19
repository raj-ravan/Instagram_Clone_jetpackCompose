package com.raj.instaclone.sealed

import androidx.annotation.StringRes
import com.raj.instaclone.R

sealed class Language(
    val code: String,
    @StringRes val title: Int,
){
    object Arabic: Language(code = "ar",title = R.string.arabic)
    object English: Language(code = "en",title = R.string.english)
}