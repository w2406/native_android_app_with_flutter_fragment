import 'package:flutter/services.dart';

class MethodChannelHandler {
  static const MethodChannel _channel = MethodChannel("com.example.nativeapplicationwithflutterfragment/channel");

  Future<String> getHello() async {
    try {
      final String result = await _channel.invokeMethod("hello");
      return result;
    } on PlatformException catch (e) {
      throw Exception("Failed to get hello from native: ${e.message}");
    }
  }
}
