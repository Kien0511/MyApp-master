package com.dthang.myapp.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.widget.EditText;

import com.dthang.myapp.R;

@SuppressLint("AppCompatCustomView")
public class EditTextPassWord extends EditText {

    private boolean isHindPassWord = false;
    private boolean useStrike = false;
    private Drawable eyes, eyesStrike;
    private Drawable drawable;

    public EditTextPassWord(Context context) {
        super(context);
        init(null);
    }

    public EditTextPassWord(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EditTextPassWord(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EditTextPassWord(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.EditTextPassWord, 0, 0);
            this.useStrike = typedArray.getBoolean(R.styleable.EditTextPassWord_attr, false);
        }
        if (useStrike){
            eyes = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black).mutate();
            eyesStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black).mutate();
            handle();
        }
    }

    private void handle() {
        setInputType(InputType.TYPE_CLASS_TEXT | (isHindPassWord ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = (isHindPassWord ? eyes : eyesStrike);
        drawable.setAlpha((int) (255 * 0.5f));
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (useStrike && event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())) {
            isHindPassWord = !isHindPassWord;
            handle();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
