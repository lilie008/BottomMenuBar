package com.example.activity;

import java.util.HashMap;
import java.util.Map;

import widget.bottommenubar.Finals;
import widget.charttopsetting.ITopTabIndexFactory;
import widget.charttopsetting.TopSettingPopupWindow;
import widget.charttopsetting.TopTabBar;
import widget.charttopsetting.TopTabBarSetting;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.bottommenubar.R;

public class PriceActivity extends Activity implements ITopTabIndexFactory,OnClickListener{
	
    @SuppressLint("UseSparseArrays")
	private Map<Integer,View> charSettingViewMap = new HashMap<Integer,View>();//缓存ChartSettingView
    @SuppressLint("UseSparseArrays")
    private Map<Integer,PopupWindow> popupWindowMap = new HashMap<Integer,PopupWindow>();//缓存ChartSetting
    private TopTabBar charSettingView;//ChartSetting的对象
    private TopSettingPopupWindow mTopBarPopupWindow;
    private TopTabBarSetting mTopTabBarSetting;
    private int CurrentChartIndex = -1;//当前Chart选项类型（时间、MA、MACD）
    private int CurrentItemId = -1;//当前类型的ID
    private int defaultTime_ID = 4;
    private int defaultMa_ID = 2;
    private int defaultMacd_ID = 2;
    private int Time_ID,Ma_ID,Macd_ID;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.ready_stock_layout);
    	charSettingView = (TopTabBar)findViewById(R.id.TopTabBar);
    	charSettingView.setTopTabIndexFactory(this);
    	mTopTabBarSetting = charSettingView.getTopTabBarSetting();
    	back = (ImageView)findViewById(R.id.back);
    	back.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {

			}
		});
    	DefaultSetting();
    }
    
    private void DefaultSetting() {
    	Intent intent = getIntent();
    	CurrentChartIndex = intent.getIntExtra("CurrentChartIndex", -1);
    	Time_ID = intent.getIntExtra("Time_ID", defaultTime_ID);
    	Ma_ID = intent.getIntExtra("Ma_ID", defaultMa_ID);
    	Macd_ID = intent.getIntExtra("Macd_ID", defaultMacd_ID);
    	getSelected(0);
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) { 
        	Intent intent = new Intent(PriceActivity.this,MainActivity.class);
        	intent.putExtra("CurrentChartIndex", CurrentChartIndex);
			intent.putExtra("Time_ID", Time_ID);
        	intent.putExtra("Ma_ID", Ma_ID);
        	intent.putExtra("Macd_ID", Macd_ID);
        	setResult(0,intent);
        	finish();
        }
        return false;
    }


    //Chart设置选中回调
	@Override
	public void getChartSettingIndex(int index) {
		CurrentChartIndex = index;
		View newView; 
		if (!charSettingViewMap.containsKey(index)) {
			newView = this.getLayoutInflater().inflate(mTopTabBarSetting.layouts_id[index], 
				 (ViewGroup)findViewById(R.id.TopTabBar),false);
			charSettingViewMap.put(index,newView);
		 }else{
			newView = charSettingViewMap.get(index);
		 }
		if (!popupWindowMap.containsKey(index)) {
			mTopBarPopupWindow = new TopSettingPopupWindow(newView, newView.getWidth(), newView.getHeight());
			mTopBarPopupWindow.setHeight(LayoutParams.WRAP_CONTENT);
			mTopBarPopupWindow.setWidth(LayoutParams.WRAP_CONTENT);
			mTopBarPopupWindow.setFocusable(true);
			mTopBarPopupWindow.setOutsideTouchable(true);
			mTopBarPopupWindow.setBackgroundDrawable(new BitmapDrawable());
			mTopBarPopupWindow.showAsDropDown(mTopTabBarSetting.linears[index]);
			setTopBarPopupWindowLinstener(index,newView);
		}else{
			mTopBarPopupWindow = (TopSettingPopupWindow) popupWindowMap.get(index);
			mTopBarPopupWindow.showAsDropDown(mTopTabBarSetting.linears[index]);
		}
		setTopBarPopupWindowItemSelected(index,newView);
	}
	
	private void getSelected(int index) {
		switch (index) {
			case Finals.TIME:
				charSettingView.setTopTabBarView(this.getResources().getString(mTopTabBarSetting.time_str_id[Time_ID]), 0);
			case Finals.MA:
				charSettingView.setTopTabBarView(this.getResources().getString(mTopTabBarSetting.ma_str_id[Ma_ID]), 1);
			case Finals.MACD:
				charSettingView.setTopTabBarView(this.getResources().getString(mTopTabBarSetting.macd_str_id[Macd_ID]), 2);
			break;
			default:
				break;
		}
	}

	private void setTopBarPopupWindowLinstener(int index, View newView) {
		switch (index) {
			case Finals.TIME:
			for(int i = 0; i < mTopTabBarSetting.TopTabBar_Time_Num; i++){
				mTopTabBarSetting.time_stetting_tx[i] = (TextView) newView.findViewById(mTopTabBarSetting.time_setting_id[i]);
				mTopTabBarSetting.time_stetting_tx[i].setOnClickListener(this);
			}
			break;
			case Finals.MA:
			for(int i = 0; i < mTopTabBarSetting.TopTabBar_Ma_Num; i++){
				mTopTabBarSetting.ma_stetting_tx[i] = (TextView) newView.findViewById(mTopTabBarSetting.ma_setting_id[i]);
				mTopTabBarSetting.ma_stetting_tx[i].setOnClickListener(this);
			}
			break;
			case Finals.MACD:
			for(int i = 0; i < mTopTabBarSetting.TopTabBar_Macd_Num; i++){
				mTopTabBarSetting.macd_stetting_tx[i] = (TextView) newView.findViewById(mTopTabBarSetting.macd_setting_id[i]);
				mTopTabBarSetting.macd_stetting_tx[i].setOnClickListener(this);
			}
			break;
		default:
			break;
		}
		popupWindowMap.put(index, mTopBarPopupWindow);
	}

	
	private void setTopBarPopupWindowItemSelected(int index, View newView) {
		switch (index) {
			case Finals.TIME:
			for(int i = 0; i < mTopTabBarSetting.TopTabBar_Time_Num; i++){
				mTopTabBarSetting.time_stetting_tx[i] = (TextView) newView.findViewById(mTopTabBarSetting.time_setting_id[i]);
				if(i == Time_ID)
					mTopTabBarSetting.time_stetting_tx[i].setSelected(true);
				else
					mTopTabBarSetting.time_stetting_tx[i].setSelected(false);
			}
			break;
			case Finals.MA:
			for(int i = 0; i < mTopTabBarSetting.TopTabBar_Ma_Num; i++){
				mTopTabBarSetting.ma_stetting_tx[i] = (TextView) newView.findViewById(mTopTabBarSetting.ma_setting_id[i]);
				if(i == Ma_ID)
					mTopTabBarSetting.ma_stetting_tx[i].setSelected(true);
				else
					mTopTabBarSetting.ma_stetting_tx[i].setSelected(false);
			}
			break;
			case Finals.MACD:
			for(int i = 0; i < mTopTabBarSetting.TopTabBar_Macd_Num; i++){
				mTopTabBarSetting.macd_stetting_tx[i] = (TextView) newView.findViewById(mTopTabBarSetting.macd_setting_id[i]);
				if(i == Macd_ID)
					mTopTabBarSetting.macd_stetting_tx[i].setSelected(true);
				else
					mTopTabBarSetting.macd_stetting_tx[i].setSelected(false);
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void onClick(View v) {
		CurrentItemId = v.getId();
		setSelectedChart();
		v.setSelected(true);
		mTopBarPopupWindow.dismiss();
	}
	
	private void setSelectedChart() {
		switch (CurrentChartIndex) {
		case Finals.TIME:
			for(int i = 0; i < mTopTabBarSetting.TopTabBar_Time_Num; i++){
				if(CurrentItemId == mTopTabBarSetting.time_setting_id[i]){
					mTopTabBarSetting.time_stetting_tx[i].setSelected(true);
					charSettingView.setTopTabBarView(this.getResources().getString(mTopTabBarSetting.time_str_id[i]), CurrentChartIndex);
					Time_ID = i;
				}else{
					mTopTabBarSetting.time_stetting_tx[i].setSelected(false);
				}
			}
			break;
		case Finals.MA:
			for(int i = 0; i < mTopTabBarSetting.TopTabBar_Ma_Num; i++){
				if(CurrentItemId == mTopTabBarSetting.ma_setting_id[i]){
					mTopTabBarSetting.ma_stetting_tx[i].setSelected(true);
					charSettingView.setTopTabBarView(this.getResources().getString(mTopTabBarSetting.ma_str_id[i]), CurrentChartIndex);
					Ma_ID = i;
				}else{
					mTopTabBarSetting.ma_stetting_tx[i].setSelected(false);
				}
			}
			break;
			
		case Finals.MACD:
			for(int i = 0; i < mTopTabBarSetting.TopTabBar_Macd_Num; i++){
				if(CurrentItemId == mTopTabBarSetting.macd_setting_id[i]){
					mTopTabBarSetting.macd_stetting_tx[i].setSelected(true);
					charSettingView.setTopTabBarView(this.getResources().getString(mTopTabBarSetting.macd_str_id[i]), CurrentChartIndex);
					Macd_ID = i;
				}else{
					mTopTabBarSetting.macd_stetting_tx[i].setSelected(false);
				}
			}
			break;
		default:
			break;
		}
	}

	@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
      super.onSaveInstanceState(savedInstanceState);
      savedInstanceState.putInt("CurrentChartIndex", CurrentChartIndex);
      savedInstanceState.putInt("Time_ID", Time_ID);
      savedInstanceState.putInt("Ma_ID", Ma_ID);
      savedInstanceState.putInt("Macd_ID", Macd_ID);
    }  
}
