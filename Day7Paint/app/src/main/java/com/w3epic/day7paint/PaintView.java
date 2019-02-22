package com.w3epic.day7paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PaintView extends View {
    private Paint brush = new Paint();
    private Path path = new Path();
    public Button button;
    public ViewGroup.LayoutParams layoutParams;

    public PaintView(Context context) {
        super(context);

        brush.setAntiAlias(true);
        brush.setColor(Color.BLUE);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(15f);

        button = new Button(context);
        button.setText("Erase");
        //button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //button.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        button.setLayoutParams(layoutParams);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                path.reset();
                postInvalidate();
                /* ^^^^^^^^^^^^^
                * Each class which is derived from the View class has the invalidate and the
                * postInvalidate method. If invalidate gets called it tells the system that the
                * current view has changed and it should be redrawn as soon as possible. As this
                * method can only be called from your UIThread another method is needed for when
                * you are not in the UIThread and still want to notify the system that your View
                * has been changed. The postInvalidate method notifies the system from a
                * non-UIThread and the View gets redrawn in the next eventloop on the UIThread
                * as soon as possible. It is also briefly explained in the SDK documentation.
                * https://stackoverflow.com/questions/5521596/what-does-postinvalidate-do
                * */
            }

        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            // A pressed gesture has started, the motion contains the initial starting location.
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y); // set cursor to touched point
                return true;

            // A change has happened during a press gesture (between ACTION_DOWN and ACTION_UP)
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break; // why without 'break' is it still working?

            // A pressed gesture has finished, the motion contains the final release location as
            // well as any intermediate points since the last down or move event.
            case MotionEvent.ACTION_UP:
                break;
        }

        postInvalidate(); // refresh the canvas
        return false;

        //return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        canvas.drawPath(path, brush);
    }
}
