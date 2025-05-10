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

public class MainActivity extends AppCompatActivity {
    // フラグメントのタグ
    static final String TAG_FLUTTER_FRAGMENT = "flutter_fragment";

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

        // フラグメント2をFlutterFragmentに置き換える
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 追加済みのフラグメントを探す
        // フラグメントが存在していない場合は新規作成
        FlutterFragment fragment = (FlutterFragment) fragmentManager.findFragmentByTag(TAG_FLUTTER_FRAGMENT);
        FlutterFragment flutterFragment = (fragment == null) ? FlutterFragment.withNewEngine().initialRoute("/").build() : fragment;

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
                switchFragment(flutterFragment, TAG_FLUTTER_FRAGMENT);
            }

            return false;
        });

    }
}