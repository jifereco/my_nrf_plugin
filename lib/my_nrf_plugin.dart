
import 'my_nrf_plugin_platform_interface.dart';

import 'package:flutter/services.dart';

class MyNrfPlugin {
  static const MethodChannel _channel =
      MethodChannel('my_nrf_plugin');

  static Future<bool> isBonded(String mac) async {
    return await _channel.invokeMethod<bool>(
      'isBonded',
      {'mac': mac},
    ) ?? false;
  }

  static Future<bool> removeBond(String mac) async {
    return await _channel.invokeMethod<bool>(
      'removeBond',
      {'mac': mac},
    ) ?? false;
  }
}
