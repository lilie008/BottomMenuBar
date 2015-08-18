package widget.charttopsetting;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bottommenubar.R;

/**
 * @ClassName: TopTabBar
 * @Description: 顶部切换view
 * @author li
 * @date 2015年7月31日
 *
 */
    
public class TopTabBar extends LinearLayout implements OnClickListener{
	
	Activity mContext;
	
	int mCurrentIndex = -1;
	
	TopTabBarSetting mTopTabBarSetting;
	
	ITopTabIndexFactory mTopTabIndexFactory;
	
	View newView;
	
	View topTabBarView;
	
	public TopTabBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = (Activity) context;
        initTab();
    }

	private void initTab() {
		//动态获取TopTabBar
		LayoutInflater inflater = LayoutInflater.from(mContext);
		topTabBarView = inflater.inflate(R.layout.view_chart_settingbar, null);
		addView(topTabBarView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        //初始化设置布局、三个选项
        mTopTabBarSetting = TopTabBarSetting.getInstance();
        //注册选择设置后设置的布局View接口
//        mTopBarPopupWindow.setTopTabViewFactory(this);
		//绑定ID
		for (int i = 0; i < mTopTabBarSetting.TopTabBarNum; i++) {
			mTopTabBarSetting.linears[i] = (TextView) topTabBarView.findViewById(mTopTabBarSetting.linears_id[i]);
			mTopTabBarSetting.linears[i].setOnClickListener(this);
        }
	}
	
	public void setTopTabIndexFactory(ITopTabIndexFactory indexFactory){
		mTopTabIndexFactory = indexFactory;
	}
	
	public TopTabBarSetting getTopTabBarSetting(){
		return mTopTabBarSetting;
	}
	
	public void setTopTabBarView(String string,int index){
		TextView txt;
		switch (index) {
		case 0:
			txt = (TextView)topTabBarView.findViewById(R.id.settings_head_time_interval);
			txt.setText(string);
			break;
		case 1:
			txt = (TextView)topTabBarView.findViewById(R.id.settings_head_ma);
			txt.setText(string);
			break;
		case 2:
			txt = (TextView)topTabBarView.findViewById(R.id.settings_head_macd);
			txt.setText(string);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		for (int i = 0; i < mTopTabBarSetting.TopTabBarNum; i++) {
            if (v.getId() == mTopTabBarSetting.linears_id[i]) {
            	mTopTabIndexFactory.getChartSettingIndex(i);//实现方法回调给Activity
            }
        }
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}   
	    
	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else if (specMode == MeasureSpec.AT_MOST) {
			result = Math.min(result, specSize);
		}
		return result;
	}

	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else if (specMode == MeasureSpec.AT_MOST) {
			result = Math.min(result, specSize);
		}
		return result;
	}
}
