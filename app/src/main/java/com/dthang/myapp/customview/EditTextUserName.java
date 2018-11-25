package com.dthang.myapp.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.widget.EditText;

import com.dthang.myapp.R;

@SuppressLint("AppCompatCustomView")
public class EditTextUserName extends EditText {

    private Drawable drawable_clear, drawable_null;
    private boolean isHideClear = false;
    private Drawable drawable;

    public EditTextUserName(Context context) {
        super(context);
        init();
    }

    public EditTextUserName(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextUserName(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EditTextUserName(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        drawable_clear = ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp).mutate();
        drawable_clear.setAlpha((int) (255 * 0.5f));
        drawable_null = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_dark_transparent).mutate();
        handle();
    }

    private void handle() {
        Drawable[] drawables = getCompoundDrawables();
        drawable = !isHideClear ? drawable_null : drawable_clear;
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())) {
            setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (start == 0 && length() == 0) {
            isHideClear = false;
        } else {
            isHideClear = true;
        }
        handle();
    }
}
