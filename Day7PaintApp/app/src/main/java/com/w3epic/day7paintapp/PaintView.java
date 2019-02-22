package com.w3epic.day7paintapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PaintView extends View {
    class Stroke {
        private Paint brush;
        private Path path;
        private int color;
        private float strokeWidth;

        public Stroke () {
            brush = new Paint();
            path = new Path();
            color = Color.BLACK;
            strokeWidth = 15f;

            initBrush(color, strokeWidth);
        }

        public Stroke (int newColor, float newStrokeWidth) {
            brush = new Paint();
            path = new Path();
            color = newColor;
            strokeWidth = newStrokeWidth;

            initBrush(color, newStrokeWidth);
        }

        private void initBrush(int newColor, float newStrokeWidth) {
            brush.setAntiAlias(true);
            brush.setColor(newColor);
            brush.setStyle(Paint.Style.STROKE);
            brush.setStrokeJoin(Paint.Join.ROUND);
            brush.setStrokeWidth(newStrokeWidth);
        }

        public Paint getBrush () {
            return brush;
        }

        public Path getPath () {
            return path;
        }
    }

    private Paint brush = new Paint();
    private Path path = new Path();

    public LinearLayout toolsPanel;
    public LinearLayout firstPanel;
    public LinearLayout secondPanel;
    public Button btnColorRed;
    public Button btnColorGreen;
    public Button btnColorBlue;
    public Button btnColorBlack;
    public Button btnColorWhite;
    public Button btnColorOrange;
    public Button btnColorYellow;
    public Button btnEraser;
    public ViewGroup.LayoutParams layoutParams;
    public SeekBar seekBar;
    public Spinner spnBgColor;

    public ArrayList<Stroke> Strokes = new ArrayList<Stroke>();
    public int currectColor = Color.BLACK;
    public float strokeWidth = 15f;

    public Path getPath () {
        return path;
    }

    public void eraseAll() {
        try {
            for (Stroke stroke:Strokes) {
                stroke.getPath().reset();
            }
            this.postInvalidate();
        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private int dpToPixel(int dps) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }

    public PaintView(final Context context) {
        super(context);
        this.setBackgroundColor(Color.WHITE);

        // toolsPanel contains all controll button like colors, brush, eraser, etc.
        toolsPanel = new LinearLayout(context);
        toolsPanel.setOrientation(LinearLayout.VERTICAL);

        firstPanel = new LinearLayout(context);
        firstPanel.setOrientation(LinearLayout.HORIZONTAL);
        secondPanel = new LinearLayout(context);
        secondPanel.setOrientation(LinearLayout.HORIZONTAL);
        secondPanel.setBackgroundColor(Color.LTGRAY);
        secondPanel.setPadding(dpToPixel(16), dpToPixel(4), dpToPixel(16), dpToPixel(4));
        /*secondPanel.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));*/

        TextView tvBrushSizeLabel = new TextView(context);
        tvBrushSizeLabel.setText("Brush size: ");
        tvBrushSizeLabel.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                dpToPixel(48),
                0.5f
        ));
        secondPanel.addView(tvBrushSizeLabel);

        seekBar = new SeekBar(context);
        seekBar.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                dpToPixel(48),
                7.0f
        ));
        seekBar.setMax(0);
        seekBar.setMax(100);
        seekBar.setProgress(15);
        secondPanel.addView(seekBar);

        LinearLayout.LayoutParams btnLayoutParams = new LinearLayout.LayoutParams(dpToPixel(64), dpToPixel(64), 1.0f);

        String[] bgColors = {"White", "Black", "Red", "Green", "Blue", "Orange", "Yellow"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                bgColors
        );
        spnBgColor = new Spinner(context);
        spnBgColor.setAdapter(arrayAdapter);
        secondPanel.addView(spnBgColor);

        btnEraser = new Button(context);
        btnEraser.setLayoutParams(new LinearLayout.LayoutParams(dpToPixel(48), dpToPixel(48), 0.5f));
        btnEraser.setBackgroundResource(R.drawable.eraser_icon);
        secondPanel.addView(btnEraser);

        btnColorRed = new Button(context);
        btnColorRed.setLayoutParams(new LinearLayout.LayoutParams(dpToPixel(64), dpToPixel(64), 1.0f));
        btnColorRed.setBackgroundColor(Color.RED);
        firstPanel.addView(btnColorRed);

        btnColorGreen = new Button(context);
        btnColorGreen.setLayoutParams(btnLayoutParams);
        btnColorGreen.setBackgroundColor(Color.GREEN);
        firstPanel.addView(btnColorGreen);

        btnColorBlue = new Button(context);
        btnColorBlue.setLayoutParams(btnLayoutParams);
        btnColorBlue.setBackgroundColor(Color.BLUE);
        firstPanel.addView(btnColorBlue);

        btnColorBlack = new Button(context);
        btnColorBlack.setLayoutParams(btnLayoutParams);
        btnColorBlack.setBackgroundColor(Color.BLACK);
        firstPanel.addView(btnColorBlack);

        btnColorWhite = new Button(context);
        btnColorWhite.setLayoutParams(btnLayoutParams);
        btnColorWhite.setBackgroundColor(Color.WHITE);
        firstPanel.addView(btnColorWhite);

        btnColorOrange = new Button(context);
        btnColorOrange.setLayoutParams(btnLayoutParams);
        btnColorOrange.setBackgroundColor(Color.rgb(255, 69, 0));
        firstPanel.addView(btnColorOrange);

        btnColorYellow = new Button(context);
        btnColorYellow.setLayoutParams(btnLayoutParams);
        btnColorYellow.setBackgroundColor(Color.YELLOW);
        firstPanel.addView(btnColorYellow);

        toolsPanel.addView(firstPanel);
        toolsPanel.addView(secondPanel);

        //btnColorRed.setText("Erase");
        //button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //button.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );



        btnColorRed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currectColor = Color.RED;
            }
        });
        btnColorGreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currectColor = Color.GREEN;
            }
        });
        btnColorBlue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currectColor = Color.BLUE;
            }
        });
        btnColorBlack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currectColor = Color.BLACK;
            }
        });
        btnColorWhite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currectColor = Color.WHITE;
            }
        });
        btnColorOrange.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currectColor = Color.rgb(255, 69, 0);
            }
        });
        btnColorYellow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currectColor = Color.YELLOW;
            }
        });
        btnEraser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currectColor = ((ColorDrawable)PaintView.this.getBackground()).getColor();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                strokeWidth = (float) progress;
                Log.d("MainActivity", Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spnBgColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int bgColor;
                switch (position) {
                    case 0:
                        bgColor = Color.WHITE;
                        break;
                    case 1:
                        bgColor = Color.BLACK;
                        break;
                    case 2:
                        bgColor = Color.RED;
                        break;
                    case 3:
                        bgColor = Color.GREEN;
                        break;
                    case 4:
                        bgColor = Color.BLUE;
                        break;
                    case 5:
                        bgColor = Color.rgb(255, 69, 0); // orange
                        break;
                    case 6:
                        bgColor = Color.YELLOW;
                        break;
                    default:
                        bgColor = Color.WHITE;
                        break;
                }

                PaintView.this.setBackgroundColor(bgColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            float x = event.getX();
            float y = event.getY();
            //Stroke stroke = new Stroke();

            switch (event.getAction()) {
                // A pressed gesture has started, the motion contains the initial starting location.
                case MotionEvent.ACTION_DOWN:
                    //path.moveTo(x, y); // set cursor to touched point
                    Strokes.add(new Stroke(currectColor, strokeWidth));
                    Strokes.get(Strokes.size() - 1).getPath().moveTo(x, y);
                    return true;
                    //break;

                // A change has happened during a press gesture (between ACTION_DOWN and ACTION_UP)
                case MotionEvent.ACTION_MOVE:
                    //path.lineTo(x, y);
                    Strokes.get(Strokes.size() - 1).getPath().lineTo(x, y);
                    break; // why without 'break' is it still working?

                // A pressed gesture has finished, the motion contains the final release location as
                // well as any intermediate points since the last down or move event.
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    return false;
            }

        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        postInvalidate(); // refresh the canvas
        return false;

        //return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // https://developer.android.com/training/custom-views/custom-drawing#overrideondraw

        try {
            for (Stroke stroke:Strokes) {
                canvas.drawPath(stroke.getPath(), stroke.getBrush());
            }
        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        super.onDraw(canvas);
    }
}