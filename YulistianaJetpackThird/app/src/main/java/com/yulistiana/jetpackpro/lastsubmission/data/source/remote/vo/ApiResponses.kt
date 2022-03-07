package com.yulistiana.jetpackpro.lastsubmission.data.source.remote.vo

class ApiResponses<T>(val status: StatusResponses, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponses<T> = ApiResponses(StatusResponses.SUCCESS, body, null)

        fun <T> error(msg: String, body: T): ApiResponses<T> = ApiResponses(StatusResponses.ERROR, body, msg)
    }
}