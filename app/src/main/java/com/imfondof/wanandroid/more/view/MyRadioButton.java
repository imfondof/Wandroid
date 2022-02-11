package com.imfondof.wanandroid.more.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatRadioButton;

import com.imfondof.wanandroid.R;
import com.imfondof.wanandroid.more.util.ViewUtil;

public class MyRadioButton extends AppCompatRadioButton {

    private boolean redTag = false;
    private Drawable dotDrawable;
    private Drawable singleDigitDrawable;
    private Drawable doubleDigitDrawable;
    private Drawable moreDigitDrawable;
    private Paint textColorPaint = new Paint();
    private int redCount;
    private int topDrawableWidth;
    private Context context;

    public MyRadioButton(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public MyRadioButton(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        try {
//            dotDrawable = ContextCompat.getDrawable(getContext(), R.drawable.red_point_dot_border);
//            singleDigitDrawable = ContextCompat.getDrawable(getContext(), R.drawable.red_point_single_digit_border);
//            doubleDigitDrawable = ContextCompat.getDrawable(getContext(), R.drawable.red_point_more_border);
//            moreDigitDrawable = ContextCompat.getDrawable(getContext(), R.drawable.red_point_more_border);
            textColorPaint.setAntiAlias(true);
            textColorPaint.setColor(getResources().getColor(R.color.white));
            textColorPaint.setTextSize(ViewUtil.dpToPx(getContext(), 10));
        } catch (Throwable e) {
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (topDrawableWidth == 0) {
            CharSequence text = getText();
            if (!TextUtils.isEmpty(text)) {
                int length = text.length();
                topDrawableWidth = (int) (ViewUtil.dpToPx(context, 15) * length);//getTextSize()*length
            }
        }
        int halfTextSize = (int) (ViewUtil.dpToPx(context, 15) / 2);//getTextSize()/2
        if (redTag && dotDrawable != null) {
            canvas.save();
            int redWidth = dotDrawable.getIntrinsicWidth();
            int redHeight = dotDrawable.getIntrinsicHeight();
            int left = (int) ((getWidth() + topDrawableWidth) / 2f - ViewUtil.dpToPx(context, 1));
            int top = (int) (getMeasuredHeight() / 2 - redHeight - halfTextSize + ViewUtil.dpToPx(context, 2));
            dotDrawable.setBounds(left, top, left + redWidth, top + redHeight);
            dotDrawable.draw(canvas);
            canvas.restore();
        } else if (redCount > 0) {
            int left = (int) ((getWidth() + topDrawableWidth) / 2f - singleDigitDrawable.getIntrinsicWidth() / 2f - ViewUtil.dpToPx(context, 1));
            canvas.save();
            if (redCount > 99) {
                int redWidth = moreDigitDrawable.getIntrinsicWidth();
                int redHeight = moreDigitDrawable.getIntrinsicHeight();
                int top = getMeasuredHeight() / 2 - redHeight / 2 - halfTextSize;
                moreDigitDrawable.setBounds(left, top, left + redWidth, top + redHeight);
                moreDigitDrawable.draw(canvas);
                float textWidth = textColorPaint.measureText("99+");
                drawText(canvas, "99+", left + (redWidth - textWidth) / 2, top + redHeight / 2);
                canvas.restore();
            } else {
                int redWidth = singleDigitDrawable.getIntrinsicWidth();
                int redHeight = singleDigitDrawable.getIntrinsicHeight();
                if (redCount < 10) {
                    int top = getMeasuredHeight() / 2 - redHeight / 2 - halfTextSize;
                    singleDigitDrawable.setBounds(left, top, left + redWidth, top + redHeight);
                    singleDigitDrawable.draw(canvas);
                } else {
                    redWidth = doubleDigitDrawable.getIntrinsicWidth();
                    redHeight = doubleDigitDrawable.getIntrinsicHeight();
                    int top = getMeasuredHeight() / 2 - redHeight / 2 - halfTextSize;
                    doubleDigitDrawable.setBounds(left, top, left + redWidth, top + redHeight);
                    doubleDigitDrawable.draw(canvas);
                }
                int top = getMeasuredHeight() / 2 - redHeight / 2 - halfTextSize;
                float textWidth = textColorPaint.measureText(redCount + "");
                drawText(canvas, redCount + "", left + (redWidth - textWidth) / 2, top + redHeight / 2);
                canvas.restore();
            }
        }
    }

    public void setRedTagVisibility(boolean isShow) {
        redTag = isShow;
        invalidate();
    }

    public void setRedTagVisibility(int redCount) {
        this.redCount = redCount;
        invalidate();
    }

    public void drawText(Canvas canvas, String text, float baseX, float baseY) {
        Paint.FontMetrics fontMetrics = textColorPaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        float newY = baseY + offY;
        canvas.drawText(text, baseX, newY, textColorPaint);
    }
}
