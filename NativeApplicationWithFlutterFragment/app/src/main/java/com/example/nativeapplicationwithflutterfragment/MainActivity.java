package com.example.nativeapplicationwithflutterfragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    /// フラグメントを切り替えるメソッド
    private void switchFragment(Fragment fragment) {
        // fragment_containerは、activity_main.xmlに定義されているフラグメントを表示するためのコンテナ
        // 渡したフラグメントを表示する
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
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
            switchFragment(new FirstFragment());
        }

        navView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            // 選択されたアイテムに応じてフラグメントを切り替える
            if (id == R.id.nav_first) {
                // フラグメント1を表示
                switchFragment(new FirstFragment());
            } else if (id == R.id.nav_second) {
                // フラグメント2を表示
                switchFragment(new SecondFragment());
            }

            return false;
        });

    }
}