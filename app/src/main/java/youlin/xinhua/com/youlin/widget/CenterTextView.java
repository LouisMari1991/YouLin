
package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @description 换行居中显示TextView
 * @author
 * @date
 */

public class CenterTextView extends TextView
{
    private StaticLayout myStaticLayout;
    private TextPaint tp;

    public CenterTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
    }

    private void initView()
    {
        tp = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        tp.setTextSize(getTextSize());
        tp.setColor(getCurrentTextColor());
        myStaticLayout = new StaticLayout(getText(), tp, getWidth(), Alignment.ALIGN_CENTER, 1.0f,
                0.0f, false);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        myStaticLayout.draw(canvas);
    }
}
