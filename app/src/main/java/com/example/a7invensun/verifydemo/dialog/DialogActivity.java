package com.example.a7invensun.verifydemo.dialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.dialog.util.InputAttributeDialog;
import com.example.a7invensun.verifydemo.dialog.util.NewInputAttributeDialog;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        InputAttributeDialog inputAttributeDialog = new InputAttributeDialog(this);
        inputAttributeDialog.setDialogSize(1f,0.5f).setDialogLocation(0,1080).setCancelable(false).show();
//        AlertDialog.Builder builder = new NewInputAttributeDialog.Builder(this);
//        builder.show();
    }

}
