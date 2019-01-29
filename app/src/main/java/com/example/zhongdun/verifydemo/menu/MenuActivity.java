package com.example.zhongdun.verifydemo.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.a7invensun.verifydemo.R;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_enter:
                Toast.makeText(this, "点击了登陆", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_setting:
                Toast.makeText(this, "点击了设置", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_out:
                Toast.makeText(this, "点击了退出", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
