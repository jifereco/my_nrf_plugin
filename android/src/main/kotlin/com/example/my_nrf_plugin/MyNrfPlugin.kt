package com.example.my_nrf_plugin

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/** MyNrfPlugin */
class MyNrfPlugin : FlutterPlugin, MethodChannel.MethodCallHandler {

  private lateinit var channel: MethodChannel
  private lateinit var adapter: BluetoothAdapter

  override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(binding.binaryMessenger, "my_nrf_plugin")
    channel.setMethodCallHandler(this)
    adapter = BluetoothAdapter.getDefaultAdapter()
  }

  override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
    when (call.method) {

      "isBonded" -> {
        val mac = call.argument<String>("mac") ?: run {
          result.error("ARG_ERROR", "MAC is null", null)
          return
        }

        val device: BluetoothDevice = adapter.getRemoteDevice(mac)
        result.success(device.bondState == BluetoothDevice.BOND_BONDED)
      }

      "removeBond" -> {
        val mac = call.argument<String>("mac") ?: run {
          result.error("ARG_ERROR", "MAC is null", null)
          return
        }

        try {
          val device: BluetoothDevice = adapter.getRemoteDevice(mac)
          val method = device.javaClass.getMethod("removeBond")
          result.success(method.invoke(device) as Boolean)
        } catch (e: Exception) {
          result.error("REMOVE_BOND_ERROR", e.message, null)
        }
      }

      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}