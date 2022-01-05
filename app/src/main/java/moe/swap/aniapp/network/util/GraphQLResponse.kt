package moe.swap.aniapp.network.util

import android.util.Log
import retrofit2.Response

class GraphQLResponse<T>(private val response: Response<GraphContainer<T>>) {
    init {
        if (!response.isSuccessful) {
            throw RequestException("${response.code()} - request unsuccessful")
        }
    }

    fun getBodyOrThrow(): T {
        return response.body()?.data ?: throw RequestException("Request body is null")
    }

    fun getBody(): T? {
        return response.body()?.data
    }
}