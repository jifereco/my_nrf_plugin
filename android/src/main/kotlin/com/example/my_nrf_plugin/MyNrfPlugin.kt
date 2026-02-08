package com.example.my_nrf_plugin

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/** MyNrfPlugin */
class MyNrfPlugin: FlutterPlugin, MethodCallHandler {

  private lateinit var channel: MethodChannel
  private lateinit var adapter: BluetoothAdapter

  override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(binding.binaryMessenger, "my_nrf_plugin")
    channel.setMethodCallHandler(this)
    adapter = BluetoothAdapter.getDefaultAdapter()
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when (call.method) {

      "isBonded" -> {
        val mac = call.argument<String>("mac")!!
        val device = adapter.getRemoteDevice(mac)
        result.success(device.bondState == BluetoothDevice.BOND_BONDED)
      }

      "removeBond" -> {
        val mac = call.argument<String>("mac")!!
        val device = adapter.getRemoteDevice(mac)
        val method = device.javaClass.getMethod("removeBond")
        result.success(method.invoke(device) as Boolean)
      }

      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
