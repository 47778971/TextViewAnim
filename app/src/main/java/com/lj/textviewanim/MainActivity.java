package com.lj.textviewanim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView textView;
    private Button button;
    private ValueAnimator valueAnimator;
    private float alphaFraction = 5f / 8f;
    private int textWidth;
    private int addWidth=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button);
        textView.setVisibility(View.INVISIBLE);
        setTextViewContent(10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator = ValueAnimator.ofInt(textWidth, textWidth + addWidth);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        textView.setWidth((int) animation.getAnimatedValue());
                        if (animation.getAnimatedFraction() <= alphaFraction) {
                            textView.setAlpha(animation.getAnimatedFraction() / alphaFraction);
                        }
                    }
                });
                valueAnimator.setDuration(800);
                valueAnimator.start();
                textView.setVisibility(View.VISIBLE);
            }
        });


    }

    private void setTextViewContent(int count) {
        textView.setText(String.format(getResources().getString(R.string.content), count));
        textView.post(new Runnable() {
            @Override
            public void run() {
                textWidth = textView.getWidth();
            }
        });
    }
}
