package com.example.nativeapplicationwithflutterfragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class MainActivity extends AppCompatActivity {
    static final String FLUTTER_ENGINE_ID   = "flutter_engine_id";

    // FlutterEngine
    private FlutterEngine flutterEngine;

    /// フラグメントを切り替えるメソッド
    private void switchFragment(Fragment fragment, String tag) {
        // fragment_containerは、activity_main.xmlに定義されているフラグメントを表示するためのコンテナ
        // 渡したフラグメントを表示する
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .addToBackStack(null) // 戻るボタン対応
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ナビゲーションの初期化
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        // 初回はフラグメント1を表示
        if (savedInstanceState == null) {
            switchFragment(new FirstFragment(), null);
        }

        // FlutterEngineの初期化してFlutterEngineをキャッシュに保存
        flutterEngine = new FlutterEngine(this);
        flutterEngine.getNavigationChannel().setInitialRoute("/");
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
        // メソッドチャンネルを追加
        FlutterSecondFragment.setMethodChannel(flutterEngine);
        FlutterEngineCache
                .getInstance()
                .put(FLUTTER_ENGINE_ID, flutterEngine);

        // Flutterのフラグメントを作成する
        // 追加済みのフラグメントを探す
        // フラグメントが存在していない場合は新規作成
        FragmentManager fragmentManager = getSupportFragmentManager();
        FlutterFragment fragment = (FlutterFragment) fragmentManager.findFragmentByTag(FlutterSecondFragment.TAG_FLUTTER_FRAGMENT);
        FlutterFragment flutterFragment = (fragment == null) ? FlutterSecondFragment.withCachedEngine(FLUTTER_ENGINE_ID).build() : fragment;

        navView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            // 選択されたアイテムに応じてフラグメントを切り替える
            if (id == R.id.nav_first) {
                // フラグメント1を表示
                switchFragment(new FirstFragment(), null);
            } else if (id == R.id.nav_second) {
                // フラグメント2を表示
                // switchFragment(new SecondFragment());

                // FlutterFragmentを表示
                switchFragment(flutterFragment, FlutterSecondFragment.TAG_FLUTTER_FRAGMENT);
            }

            return false;
        });

    }
}