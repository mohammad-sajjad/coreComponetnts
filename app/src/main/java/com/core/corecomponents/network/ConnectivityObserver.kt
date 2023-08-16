package com.core.corecomponents.network

import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {

    fun observer() : Flow<ConnectivityStatus>
}

enum class ConnectivityStatus {
    AVAILABLE,
    UNAVAILABLE,
    LOSING,
    LOST

}
