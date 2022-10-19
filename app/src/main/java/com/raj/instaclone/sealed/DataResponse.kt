package com.raj.instaclone.sealed

sealed class DataResponse<T>(
    var data: T? = null,
    var error: com.raj.instaclone.sealed.Error? = null,
) {
    class Success<T>(data: T) : DataResponse<T>(data = data)
    class Error<T>(error: com.raj.instaclone.sealed.Error) : DataResponse<T>(error = error)
}