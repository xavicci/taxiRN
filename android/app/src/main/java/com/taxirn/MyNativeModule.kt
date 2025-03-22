package com.taxirn

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Callback

class MyNativeModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "MyNativeModule"
    }

    @ReactMethod
    fun sampleMethod(message: String, callback: Callback) {
        // Aquí puedes implementar la lógica que desees
        val response = "Mensaje recibido: $message"
        callback.invoke(response)
    }
}