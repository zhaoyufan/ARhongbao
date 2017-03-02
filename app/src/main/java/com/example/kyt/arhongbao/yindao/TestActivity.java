package com.example.kyt.arhongbao.yindao;



        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.TextView;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("第二个页面");
        textView.setPadding(50, 50, 50, 50);
        textView.setTextSize(50);

        setContentView(textView);

    }

}
