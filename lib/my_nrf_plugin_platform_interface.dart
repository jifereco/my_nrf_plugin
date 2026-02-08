import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'my_nrf_plugin_method_channel.dart';

abstract class MyNrfPluginPlatform extends PlatformInterface {
  /// Constructs a MyNrfPluginPlatform.
  MyNrfPluginPlatform() : super(token: _token);

  static final Object _token = Object();

  static MyNrfPluginPlatform _instance = MethodChannelMyNrfPlugin();

  /// The default instance of [MyNrfPluginPlatform] to use.
  ///
  /// Defaults to [MethodChannelMyNrfPlugin].
  static MyNrfPluginPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [MyNrfPluginPlatform] when
  /// they register themselves.
  static set instance(MyNrfPluginPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
