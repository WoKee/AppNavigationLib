package com.fph.appnavigationlib.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fph.appnavigationlib.R;
import com.fph.appnavigationlib.utils.ResourceUtil;
import com.fph.appnavigationlib.utils.Utils;


/**
 * 
 *
 * com.fph.MyTitleBar.java
 * 
 * Created by wang on 2016上午9:47:09 
 * 
 * Tips:自定义标题栏
 */
public class MyTitleBar extends RelativeLayout {

	public final static int BACKBTN_ID = 0;
	public final static int RIGHTBTN_ID = 1;
	private ImageView backBtn;
	private TextView titleTxt;
	private ImageView rightBtn;
	private ImageView leftBtn_iii;
	private View left_red;
	private OnRightBtnClickListener mRightBtnClickListener;
	private OnLeftBtnClickListener mLeftBtnClickListener;
	private Context mContext;
    private TextView right_tv;
    private String rTitle;
    
    private View bottom_line;

	private String sTitle;
	private boolean backBtnVisibility;
	private boolean leftiiiBtnVisibility;
	private boolean leftRedVisibility;
    private boolean rightTvVisibilty;

	public boolean isLeftRedVisibility() {
		return leftRedVisibility;
	}


	public void setLeftRedVisibility(boolean leftRedVisibility) {
		this.leftRedVisibility = leftRedVisibility;
		if (leftRedVisibility){
			showRed();
		}else
			hideRed();
	}

	private void showRed(){
		Log.d("Test","show");
		if (left_red!=null)
			left_red.setVisibility(View.VISIBLE);
		else{
			Utils.showToast(mContext,"show erro");
			Log.d("Test","show error");
		}
	}
	private void hideRed(){
		Log.d("Test", "hide");
		if (left_red!=null)
			left_red.setVisibility(View.GONE);
		else
		{
			Utils.showToast(mContext,"hide erro");
			Log.d("Test", "hide error");
		}
	}

	public ImageView getLeftBtn_iii() {
		return leftBtn_iii;
	}

	public void setLeftBtn_iii(ImageView leftBtn_iii) {
		this.leftBtn_iii = leftBtn_iii;
	}

	public boolean isLeftiiiBtnVisibility() {
		return leftiiiBtnVisibility;
	}

	public void setLeftiiiBtnVisibility(boolean leftiiiBtnVisibility) {
		this.leftiiiBtnVisibility = leftiiiBtnVisibility;
		if (!leftiiiBtnVisibility)
			hideriiiBtn();
		else
			showiiiBtn();
	}

	public boolean isRightTvVisibilty() {
        return rightTvVisibilty;
    }

    public void setRightTvVisibilty(boolean rightTvVisibilty) {
        this.rightTvVisibilty = rightTvVisibilty;
        if (!rightTvVisibilty)
            hiderTv();
        else
            showrTv();
    }

    /**
	 * 标题栏点击效果
	 */
	private int animId = R.anim.scale_alpha;
	public boolean isBackBtnVisibility() {
		return backBtnVisibility;
	}

	public void setBackBtnVisibility(boolean backBtnVisibility) {
		this.backBtnVisibility = backBtnVisibility;
		if(backBtnVisibility)
		{
			showBackBtn();
		}
		else
		{
			hideBackBtn();
		}
	}
	private boolean rightBtnVisibility;
	public boolean isRightBtnVisibility() {
		return rightBtnVisibility;
	}

	public void setRightBtnVisibility(boolean rightBtnVisibility) {
		this.rightBtnVisibility = rightBtnVisibility;
		if(rightBtnVisibility)
		{
			showRightBtn();
		}
		else
		{
			hideRightBtn();
		}
	}



	private int backBtnBackground;
	private int rightBtnBackground;
	private int leftiiiBtnBackground;
    private int rightTvBackgroud;
    private int rTextColor;
    private float rTexSize;
	private int textColor;
	private float textSize=14;
	private boolean blod=false;

	/**
	 *
	 * @param mOnTitleClickListener
	 */
//	public void setOnTitleClickListener(
//			OnTitleBarClickListener mOnTitleClickListener) {
//		this.mOnTitleClickListener = mOnTitleClickListener;
//	}

	/**
	 * 左右button clicked事件接口
	 *
	 *
	 */
	public interface OnRightBtnClickListener
	{
		public void onRightBtnClicked(View view);
	}

	public interface OnLeftBtnClickListener
	{
		public void onBack(View view);
	}

	public MyTitleBar(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
		initView(context);
	}

	public MyTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		if(isInEditMode()) return;
		initView(context);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
		backBtnBackground = a.getResourceId(R.styleable.MyTitleBar_backBtnBackground, 0);

		backBtnVisibility = a.getBoolean(R.styleable.MyTitleBar_backBtnVisibility, true);

		leftiiiBtnBackground = a.getResourceId(R.styleable.MyTitleBar_leftiiiBtnBackground, 0);

		leftRedVisibility = a.getBoolean(R.styleable.MyTitleBar_leftredVisibility, false);

		leftiiiBtnVisibility = a.getBoolean(R.styleable.MyTitleBar_leftiiiBtnVisibility, false);

		rightBtnBackground = a.getResourceId(R.styleable.MyTitleBar_rightBtnBackground, 0);

		rightBtnVisibility = a.getBoolean(R.styleable.MyTitleBar_rightBtnVisibility, true);

		sTitle = a.getString(R.styleable.MyTitleBar_titleText);

        rightTvVisibilty=a.getBoolean(R.styleable.MyTitleBar_rightTvVisibility, false);
        rTitle=a.getString(R.styleable.MyTitleBar_rTitleText);
        rTexSize=a.getInt(R.styleable.MyTitleBar_rTextSize, 14);
        rTextColor=a.getColor(R.styleable.MyTitleBar_rTextColor, Color.parseColor("#ffffff"));

		textColor = a.getColor(R.styleable.MyTitleBar_textColor, Color.parseColor("#ffffff"));
		textSize = a.getInt(R.styleable.MyTitleBar_textSize, 20);

		setTitle(sTitle);//设置标题内容
		setTitleColor(textColor);//设置标题字体颜色
		setTitleSize(textSize);//设置标题字体大小
//		setTitleBlod("");//设置字体粗细
        setrTitle(rTitle);
        setrTitleColor(rTextColor);
        setrTitleSize(rTexSize);
        setRightTvVisibilty(rightTvVisibilty);
		setLeftRedVisibility(leftRedVisibility);

		if(backBtnVisibility)
		{
			setBackBtnBackground(backBtnBackground);//设置左边button背景
		}
		else
		{
			hideBackBtn();//隐藏左边button
		}
		if(leftiiiBtnVisibility)
		{
			setBackBtnBackground(backBtnBackground);//设置iii
		}
		else
		{
			hideriiiBtn();//隐藏iii
		}

		if(rightBtnVisibility)
		{
			setRightBtnBackground(rightBtnBackground);	//设置右边button背景
		}
		else
		{
			hideRightBtn();//隐藏右边button
		}
		a.recycle();
	}

	public MyTitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		// TODO Auto-generated constructor stub
		initView(context);
	}

	private void initView(final Context context)
	{
		/**
		 * 构建backBtn布局
		 *
		 */

		final View view = inflate(context, ResourceUtil.getLayoutId(context, "title_bar"), null);
		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));


		backBtn = (ImageView) view.findViewById(ResourceUtil.getId(context, "leftBtn_linear"));

		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				final Animation animation = AnimationUtils.loadAnimation(context, animId);
				animation.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						new Handler().postDelayed(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if(mLeftBtnClickListener != null)
									mLeftBtnClickListener.onBack(v);
								else
								{
									((Activity)mContext).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
									((Activity)mContext).onBackPressed();
								}
							}
						}, 10);
						// TODO Auto-generated method stub

					}
				});
				v.startAnimation(animation);

			}
		});

		/**
		 * 构建iii布局
		 */
		leftBtn_iii = (ImageView) view.findViewById(ResourceUtil.getId(context, "leftBtn_iii"));

		left_red=view.findViewById(ResourceUtil.getId(context,"left_red"));

		/**
		 * 构建rightBtn布局
		 */
		rightBtn = (ImageView) view.findViewById(ResourceUtil.getId(context, "rightbtn_linear"));
		rightBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				final Animation animation = AnimationUtils.loadAnimation(mContext, animId);
				animation.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						new Handler().postDelayed(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if(mRightBtnClickListener != null&& v.isShown())
									mRightBtnClickListener.onRightBtnClicked(v);
							}
						}, 10);
						// TODO Auto-generated method stub

					}
				});
				v.startAnimation(animation);

			}
		});
        right_tv= (TextView) view.findViewById(ResourceUtil.getId(context,"right_titlebar"));
        right_tv.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(final View v) {
            // TODO Auto-generated method stub
            final Animation animation = AnimationUtils.loadAnimation(mContext, animId);
            animation.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
							if(mRightBtnClickListener != null&&v.isShown())
								mRightBtnClickListener.onRightBtnClicked(v);
                        }
                    }, 8);
                    // TODO Auto-generated method stub

                }
            });
            v.startAnimation(animation);
//            if(mRightBtnClickListener != null)
//                mRightBtnClickListener.onRightBtnClicked(v);
        }
    });
		/**
		 * 构建标题布局
		 */
		setTitleTxt((TextView) view.findViewById(ResourceUtil.getId(context, "titlebar")));
//        /**
//         * 构建右边标题布局
//         */
//        setRight_titleBar((TextView) view.findViewById(ResourceUtil.getId(context,"right_titlebar")));
		bottom_line=view.findViewById(ResourceUtil.getId(context, "bottom_line"));
		setBottomLineVisible(false);
		addView(view);
	}


	public void setBottomLineVisible(boolean isVisible){
		if (bottom_line!=null) {
			bottom_line.setVisibility(isVisible?VISIBLE:GONE);
		}
	}

	private int indexOfViewInParent(View view, ViewGroup parent)
	{
		int index;
		for (index = 0; index < parent.getChildCount(); index++)
		{
			if (parent.getChildAt(index) == view)
				break;
		}
		return index;
	}




	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
	}

	/**
	 * 设置标题
	 * @param txt
	 */
	public void setTitle(CharSequence txt)
	{
		if(getTitleTxt() != null)
			getTitleTxt().setText(txt);
	}
    /**
     * 设置you标题
     * @param txt
     */
    public void setrTitle(CharSequence txt)
    {
        if(getRight_tv() != null)
            getRight_tv().setText(txt);
    }
	/**
	 * 返回标题内容
	 * @return
	 */
	public CharSequence getTitle()
	{
		if(getTitleTxt() == null)
			return null;
		else
			return getTitleTxt().getText();
	}
    /**
     * 返回you标题内容
     * @return
     */
    public CharSequence getfTitle()
    {
        if(getRight_tv() == null)
            return null;
        else
            return getRight_tv().getText();
    }
	/**
     * 设置标题字体大小
     * @param size
     */
    public void setTitleSize(float size)
    {
        if(getTitleTxt() != null)
            getTitleTxt().setTextSize(TypedValue.COMPLEX_UNIT_SP ,size);
    }
    /**
     * 设置you标题字体大小
     * @param size
     */
    public void setrTitleSize(float size)
    {
        if(getRight_tv() != null)
            getRight_tv().setTextSize(TypedValue.COMPLEX_UNIT_SP ,size);
    }
	/**
	 * 设置标题字体粗细
	 * @param blod
	 */
	public void setTitleBlod(String blod)
	{
		if(getTitleTxt() != null){
			Paint pn=getTitleTxt().getPaint();
			pn.setTypeface(Typeface.DEFAULT_BOLD);
		}

	}
	/**
	 * 设置标题字体颜色
	 * @param color
	 */
	public void setTitleColor(int color)
	{
		if(getTitleTxt() != null)
			getTitleTxt().setTextColor(color);
	}

	public void setTitleColor(ColorStateList color)
	{
		if(getTitleTxt() != null)
			getTitleTxt().setTextColor(color);
	}
    /**
     * 设置you标题字体颜色
     * @param color
     */
    public void setrTitleColor(int color)
    {
        if(getRight_tv() != null)
            getRight_tv().setTextColor(color);
    }

    public void setrTitleColor(ColorStateList color)
    {
        if(getRight_tv() != null)
            getRight_tv().setTextColor(color);
    }

	/**
	 * 显示返回键
	 */
	public void showBackBtn()
	{
		if(getBackBtn() != null)
			getBackBtn().setVisibility(View.VISIBLE);
	}
	/**
	 * 隐藏返回键
	 */
	public void hideBackBtn()
	{
		if(getBackBtn() != null)
		{
			getBackBtn().setVisibility(View.INVISIBLE);
		}
	}


	/**
	 * 显示iii
	 */
	public void showiiiBtn()
	{
		if(getLeftBtn_iii() != null)
			getLeftBtn_iii().setVisibility(View.VISIBLE);
	}
	/**
	 * 隐藏iii
	 */
	public void hideriiiBtn()
	{
		if(getLeftBtn_iii() != null)
		{
			getLeftBtn_iii().setVisibility(View.GONE);
		}
	}


    /**
     * 显示右边文字
     */
    public void showrTv()
    {
        if(getRight_tv() != null)
            getRight_tv().setVisibility(View.VISIBLE);
    }
    /**
     * 隐藏右边文字
     */
    public void hiderTv()
    {
        if(getRight_tv() != null)
		{
//			getRight_tv().setText("");
			getRight_tv().setVisibility(View.INVISIBLE);

		}
    }
	/**
	 * 获取返回键显示状态
	 * @return
	 */
	public boolean isBackBtnShow()
	{
		return getBackBtn() == null ? false : getBackBtn().isShown();
	}

	/**
	 * 显示右边按钮
	 */
	public void showRightBtn()
	{
		if(getRightBtn() != null)
			getRightBtn().setVisibility(VISIBLE);
	}
	/**
	 * 隐藏右边按钮
	 */
	public void hideRightBtn()
	{
		if(getRightBtn() != null)
		{
			getRightBtn().setVisibility(GONE);
		}
	}
	/**
	 * 获取右边按钮显示状态
	 * @return
	 */
	public boolean isRightBtnShow()
	{
		return getRightBtn() == null ? false : getRightBtn().isShown();
	}

	/**
	 * 设置返回按键背景
	 * @param id
	 */
	public void setBackBtnBackground(int id)
	{
		setBackBtnVisibility(true);
		if(id != 0)
			getBackBtn().setImageResource(id);
//		else
//		{
//			getBackBtn().setImageResource(R.drawable.btn_default);
//		}
	}

	/**
	 * 设置iii键背景
	 * @param id
	 */
	public void setLeftiiiBtnBackground(int id)
	{
		setLeftiiiBtnVisibility(true);
		if(id != 0)
			getLeftBtn_iii().setImageResource(id);
//		else
//		{
//			getLeftBtn_iii().setImageResource(R.drawable.btn_default);
//		}
	}

	/**
	 * 设置右边按键背景
	 * @param id
	 */
	public void setRightBtnBackground(int id)
	{
		setRightBtnVisibility(true);
		if(id != 0)
			getRightBtn().setImageResource(id);
//		else
//		{
//			getRightBtn().setImageResource(R.drawable.btn_default);
//		}
	}





	public void setTitleTxt(TextView titleTxt) {
		this.titleTxt = titleTxt;
	}

	public TextView getTitleTxt() {
		return titleTxt;
	}

    public TextView getRight_tv() {
        return right_tv;
    }

    public void setRight_tv(TextView right_titleBar) {
        this.right_tv = right_titleBar;
    }

    public void setBackBtn(ImageView backBtn) {
		setBackBtnVisibility(true);
		this.backBtn = backBtn;
	}

	public ImageView getBackBtn() {
		return backBtn;
	}

	public void setRightBtn(ImageView rightBtn) {
		setRightBtnVisibility(true);
		this.rightBtn = rightBtn;
	}

	public ImageView getRightBtn() {
		return rightBtn;
	}

	public void setAnimId(int animId) {
		this.animId = animId;
	}

	public int getAnimId() {
		return animId;
	}

	public void setRightBtnClickListener(OnRightBtnClickListener mRightBtnClickListener) {
		this.mRightBtnClickListener = mRightBtnClickListener;
	}

	public OnRightBtnClickListener getRightBtnClickListener() {
		return mRightBtnClickListener;
	}

	public void setLeftBtnClickListener(OnLeftBtnClickListener mLeftBtnClickListener) {
		this.mLeftBtnClickListener = mLeftBtnClickListener;
	}

	public OnLeftBtnClickListener getLeftBtnClickListener() {
		return mLeftBtnClickListener;
	}

//	public void setRigthBtnText(String text)
//	{
//		rightImg.setText(text);
//	}
//
//	public void setLeftBtnText(String text)
//	{
//		leftImg.setText(text);
//	}
}
