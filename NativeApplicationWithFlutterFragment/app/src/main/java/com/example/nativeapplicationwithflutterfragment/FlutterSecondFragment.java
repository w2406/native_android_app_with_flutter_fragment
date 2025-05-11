package com.example.nativeapplicationwithflutterfragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class FlutterSecondFragment extends FlutterFragment {
    // フラグメントのタグ
    static final String TAG_FLUTTER_FRAGMENT = "flutter_fragment";
    static final String CHANNEL = "com.example.nativeapplicationwithflutterfragment/channel";

    // メソッドチャンネルをflutterEngineを使用して設定する
    public static void setMethodChannel(FlutterEngine flutterEngine) {
        new MethodChannel(
                flutterEngine.getDartExecutor().getBinaryMessenger(),
                CHANNEL
        ).setMethodCallHandler(
                (call, result) -> {
                    // Flutterから呼ばれるメソッド
                    if (call.method.equals("hello")) {
                        result.success("Hello from Android!");
                    } else {
                        result.notImplemented();
                    }
                }
        );
    }
}
