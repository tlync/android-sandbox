package me.tlync.android.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LogBenchmarkActivity extends Activity {

    private static final String TAG = LogBenchmarkActivity.class.getSimpleName();
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        for (int i=0; i<10; i++) {
            performBenchmark();
        }
    }

    protected void performBenchmark() {
        TextView empty = (TextView) findViewById(R.id.empty);
        TextView standard = (TextView) findViewById(R.id.normal);
        TextView wrapper = (TextView) findViewById(R.id.wrapper);

        // パフォーマンス測定用 基準値
        long s0 = System.currentTimeMillis();
        for (int i=0; i<10000; i++) {
            // 空のループ
        }
        long e0 = System.currentTimeMillis();
        empty.setText(empty.getText() + LINE_SEPARATOR + (e0 - s0 + " ms"));

        // Android 標準の Log クラスを使用
        long s1 = System.currentTimeMillis();
        for (int i=0; i<10000; i++) {
            Log.d(TAG, "i: " + i);
        }
        long e1 = System.currentTimeMillis();
        standard.setText(standard.getText() + LINE_SEPARATOR + (e1 - s1 + " ms"));

        // 独自の Logger クラスを利用 ※詳細は後述
        long s2 = System.currentTimeMillis();
        for (int i=0; i<10000; i++) {
            Logger.d(TAG, "i: %s", i);
        }
        long e2 = System.currentTimeMillis();
        wrapper.setText(wrapper.getText() + LINE_SEPARATOR + (e2 - s2 + " ms"));
    }

}