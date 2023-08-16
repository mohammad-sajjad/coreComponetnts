package com.core.corecomponents.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NetworkConnectivityManager(private val context: Context): ConnectivityObserver {

    val networkManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observer(): Flow<ConnectivityStatus> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(ConnectivityStatus.AVAILABLE) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(ConnectivityStatus.LOSING) }

                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(ConnectivityStatus.LOST) }

                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(ConnectivityStatus.UNAVAILABLE) }

                }
            }
            networkManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                networkManager.unregisterNetworkCallback(callback)
            }

        }.distinctUntilChanged()


    }
}