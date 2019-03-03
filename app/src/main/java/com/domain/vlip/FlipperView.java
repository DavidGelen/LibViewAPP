package com.domain.vlip;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.domain.R;

import java.util.List;

/**
 * @name LibViewAPP
 * @class name：com.domain.vlip
 * @class describe
 * @anthor David
 * @time 2019/3/3 2:18 PM
 * @class describe
 */
public class FlipperView extends ViewFlipper implements View.OnClickListener {
    private Context mContext;
    private List<String> mNotices;
    private OnNoticeClickListener mOnNoticeClickListener;

    public FlipperView(Context context) {
        super(context);
    }

    public FlipperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        // 轮播间隔时间为3s
        setFlipInterval(3000);
        // 内边距5dp
        setPadding(dp2px(5f), dp2px(5f), dp2px(5f), dp2px(5f));
        // 设置enter和leave动画
        setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.notice_in));
        setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.notify_out));
    }

    public void addNoticeLayout(List<String> notices) {
        mNotices = notices;
        removeAllViews();
        for (int i = 0; i < mNotices.size(); i++) {
            RelativeLayout rv = new RelativeLayout(mContext);
            TextView leftTv = new TextView(mContext);
            leftTv.setText("重大消息");
            leftTv.setSingleLine();
            leftTv.setTextSize(13f);
            leftTv.setEllipsize(TextUtils.TruncateAt.END);
            leftTv.setTextColor(Color.RED);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.leftMargin = dp2px(15);
            rv.addView(leftTv, params);

            String notice = notices.get(i);
            TextView rightText = new TextView(mContext);
            rightText.setSingleLine();
            rightText.setText(notice);
            rightText.setTextSize(13f);
            rightText.setEllipsize(TextUtils.TruncateAt.END);
            rightText.setTextColor(Color.parseColor("#666666"));

            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params1.addRule(RelativeLayout.CENTER_VERTICAL);
            params1.rightMargin = dp2px(15);
            rv.addView(rightText, params1);
            rv.setTag(i);
            rv.setOnClickListener(this);
            addView(rv, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

    }

    public void addNoticeTextView(List<String> notices) {
        mNotices = notices;
        removeAllViews();
        for (int i = 0; i < mNotices.size(); i++) {
            // 根据公告内容构建一个TextView
            String notice = notices.get(i);
            TextView textView = new TextView(mContext);
            textView.setSingleLine();
            textView.setText(notice);
            textView.setTextSize(13f);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            // 将公告的位置设置为textView的tag方便点击是回调给用户
            textView.setTag(i);
            textView.setOnClickListener(this);
            // 添加到ViewFlipper
            addView(textView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }


    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                mContext.getResources().getDisplayMetrics());
    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        String notice = (String) mNotices.get(position);
        if (mOnNoticeClickListener != null) {
            mOnNoticeClickListener.onNotieClick(position, notice);
        }
    }

    /**
     * 通知点击监听接口
     */
    public interface OnNoticeClickListener {
        void onNotieClick(int position, String notice);
    }

    /**
     * 设置通知点击监听器
     * @param onNoticeClickListener 通知点击监听器
     */
    public void setOnNoticeClickListener(OnNoticeClickListener onNoticeClickListener) {
        mOnNoticeClickListener = onNoticeClickListener;
    }
}
