// Sourced: https://github.com/AniTrend/retrofit-graphql/blob/c733e613dc1e0a18b571b16d7cfb474901781fa0/library/src/main/kotlin/io/github/wax911/library/model/attribute/GraphError.kt
// Sourced: https://github.com/AniTrend/retrofit-graphql/wiki/Getting-Started
package moe.swap.aniapp.network.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import retrofit2.Response

/**
 * GraphQL error representation that is spec complaint
 *
 * @param message Description of the error.
 * @param path Path of the the response field that encountered the error, as segments that
 * represent fields should be strings, and path segments that represent list indices
 * should be 0‐indexed integers. If the error happens in an aliased field, the path to the
 * error should use the aliased name, since it represents a path in the response, not in the query.
 * @param locations List of locations within the GraphQL document at which the exception occurred.
 * @param extensions Additional information about the error.
 *
 * @see [GraphQL Error Specification](http://spec.graphql.org/June2018/#sec-Errors)
 */
data class GraphError(
    val message: String?,
    val path: List<Any>? = null,
    val locations: List<Location>? = null,
    val extensions: Map<String, Any?>? = null
) {

    /**
     * Location describing which part of GraphQL document caused an exception.
     */
    data class Location(
        val line: Int,
        val column: Int
    )

    override fun toString(): String {
        return "GraphError{" +
                "message='" + message + '\''.toString() +
                ", path=" + path?.joinToString() +
                ", locations=" + locations?.joinToString() +
                ", extensions=" + extensions +
                '}'.toString()
    }
}

/**
 * Converts the response error response into an object.
 *
 * @return The error object, or null if an exception was encountered
 *
 * @see GraphError
 */
fun Response<*>?.getError(): List<GraphError>? {
    try {
        if (this != null) {
            val responseBody = errorBody()
            val message = responseBody?.string()
            if (responseBody != null && !message.isNullOrBlank()) {
                val graphErrors= message.getGraphQLError()
                if (graphErrors != null)
                    return graphErrors
            }
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return null
}

@Throws(JsonSyntaxException::class)
private fun String.getGraphQLError(): List<GraphError>? {
    val tokenType = object : TypeToken<GraphContainer<*>>() {}.type
    val graphContainer = Gson().fromJson<GraphContainer<*>>(this, tokenType)
    return graphContainer.errors
}