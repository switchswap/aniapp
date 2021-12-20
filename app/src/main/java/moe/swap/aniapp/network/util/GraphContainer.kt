//Sourced: https://github.com/AniTrend/retrofit-graphql/wiki/Getting-Started

package moe.swap.aniapp.network.util

data class GraphContainer<T>(
    val data: T? = null,
    val errors: List<GraphError>? = null,
    val extensions: Map<Any, Any>? = null
)