package com.example.zhongdun.verifydemo.customdialog.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a7invensun.verifydemo.R;
import com.example.zhongdun.verifydemo.customdialog.CustomDialogFragment;

public class MyDialogFragment extends CustomDialogFragment {
    private View mView;

    @Override
    public View onCreateDialogView() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.zd_fragment_my_dialog, null);
        mView.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return mView;
    }


}
