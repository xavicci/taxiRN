package com.taxirn

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Callback
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import auth.AuthServiceGrpc
import auth.Auth

class MyNativeModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private val channel: ManagedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    private val stub: AuthServiceGrpc.AuthServiceBlockingStub = AuthServiceGrpc.newBlockingStub(channel)

    override fun getName(): String {
        return "MyNativeModule"
    }

    @ReactMethod
    fun login(email: String, password: String, callback: Callback) {
        try {
            val request = Auth.LoginRequest.newBuilder()
                .setEmail(email)
                .setPassword(password)
                .build()
            val response: Auth.LoginResponse = stub.login(request)
            val result = mapOf("token" to response.token, "user" to response.user)
            callback.invoke(null, result)
        } catch (e: Exception) {
            callback.invoke(e.message, null)
        }
    }
}