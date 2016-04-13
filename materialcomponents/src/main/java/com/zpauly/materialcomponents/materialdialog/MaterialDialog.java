package com.zpauly.materialcomponents.materialdialog;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by root on 16-4-10.
 */
public class MaterialDialog extends Dialog {
    public MaterialDialog(Context context) {
        super(context);
    }

    public MaterialDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MaterialDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
