import 'package:flutter_test/flutter_test.dart';
import 'package:my_nrf_plugin/my_nrf_plugin.dart';
import 'package:my_nrf_plugin/my_nrf_plugin_platform_interface.dart';
import 'package:my_nrf_plugin/my_nrf_plugin_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockMyNrfPluginPlatform
    with MockPlatformInterfaceMixin
    implements MyNrfPluginPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final MyNrfPluginPlatform initialPlatform = MyNrfPluginPlatform.instance;

  test('$MethodChannelMyNrfPlugin is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelMyNrfPlugin>());
  });

  test('getPlatformVersion', () async {
    MyNrfPlugin myNrfPlugin = MyNrfPlugin();
    MockMyNrfPluginPlatform fakePlatform = MockMyNrfPluginPlatform();
    MyNrfPluginPlatform.instance = fakePlatform;

    expect(await myNrfPlugin.getPlatformVersion(), '42');
  });
}
