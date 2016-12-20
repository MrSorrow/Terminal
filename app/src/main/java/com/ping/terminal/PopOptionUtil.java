package com.ping.terminal;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * 仿qq弹出式菜单实现类。
 * Created by Mr.sorrow on 2016/10/26.
 */
public class PopOptionUtil {

    private Context mContext;
    private int popupWidth;
    private int popupHeight;
    private PopupWindow popupWindow;
    private PopClickEvent mEvent;
    private Button preBt;
    private Button nextBt;

    public PopOptionUtil(Context context) {
        mContext = context;
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.pop_menu, null);
        preBt = (Button) popupView.findViewById(R.id.bt_1);
        nextBt = (Button) popupView.findViewById(R.id.bt_2);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupWidth = popupView.getMeasuredWidth();
        popupHeight = popupView.getMeasuredHeight();
    }

    public void show(View view) {
        initEvent();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, (location[0] + view.getWidth() / 2) - popupWidth / 2,
                location[1] - popupHeight);
    }

    public void close(){
        popupWindow.dismiss();
    }

    public void setOnPopClickEvent(PopClickEvent mEvent) {
        this.mEvent = mEvent;
    }

    private void initEvent() {
        if (mEvent != null) {
            preBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEvent.onPreClick();
                }
            });
            nextBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEvent.onNextClick();
                }
            });

        }
    }
}
