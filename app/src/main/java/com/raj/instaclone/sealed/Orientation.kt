package com.raj.instaclone.sealed

sealed class Orientation {
    object Vertical : Orientation()
    object Horizontal : Orientation()
}