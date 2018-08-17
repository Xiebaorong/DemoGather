package com.example.a7invensun.verifydemo.dialog.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;


/**
 * Created by 7invensun on 2018/8/17.
 */

public class InputAttributeDialog  extends BaseDialog {
    private static final String TAG = "InputAttributeDialog";
    private Context context;

    public InputAttributeDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getDialogStyleId() {
        return R.style.CustomDialog;
    }

    @Override
    protected View getView(Context mContext) {
        View view = View.inflate(mContext, R.layout.dialog_input_attribute_item, null);
        initFindView(view);
        return view;
    }

    private TextView dialogExit;
    private void initFindView(View view) {
        dialogExit = view.findViewById(R.id.dialog_exit);
    }

}
