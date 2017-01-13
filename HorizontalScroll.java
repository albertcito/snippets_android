package cl.lcc.asoidj_sodkja.probandolayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


public class HorizontalScroll extends Activity
{
    private FrameLayout scene;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        scene = new FrameLayout(this);
        scene.setLayoutParams(
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                )
        );
        setContentView(this.scene);

    }


    boolean flagPrint = false;
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus)
        {
            if( this.flagPrint ) return;
            this.flagPrint = true;

            int width  = scene.getWidth();
            int height = scene.getHeight();

            int widthCol = (int)(width*0.2); //Tamaño de columna
            int numCol = 15; //Número de columnas
            int spaceCol = 20; //Espacio entre columnas

            android.widget.HorizontalScrollView scrollView = getScroll();
            scrollView.setBackgroundColor(Color.YELLOW);
            scrollView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            scene.addView(scrollView);


            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setBackgroundColor(Color.BLUE);
            linearLayout.setLayoutParams(
                    new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            );
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            scrollView.addView(linearLayout);


            FrameLayout frameLayout = new FrameLayout(this);
            frameLayout.setBackgroundColor(Color.YELLOW);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(
                    (widthCol + spaceCol)*numCol + spaceCol,
                    height
            ));
            linearLayout.addView(frameLayout);

            for (int i = 0; i < 15; i++)
            {
                final FrameLayout fl2 = new FrameLayout(this);
                fl2.setBackgroundColor(Color.GREEN);
                fl2.setTag(i);
                fl2.setLayoutParams(
                        new FrameLayout.LayoutParams(
                                widthCol,
                                height
                        )
                );
                int posX = (widthCol + spaceCol )*i + spaceCol;
                fl2.setX(posX);
                frameLayout.addView(fl2);
                fl2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Log.e("Tag",""+fl2.getTag());
                    }
                });
            }
        }
    }

    private android.widget.HorizontalScrollView getScroll()
    {
        android.widget.HorizontalScrollView scrollView = new android.widget.HorizontalScrollView(this);
        scrollView.setBackgroundColor(Color.TRANSPARENT);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.setVerticalScrollBarEnabled(false);
        return scrollView;
    }

}
