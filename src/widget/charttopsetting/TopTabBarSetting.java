package widget.charttopsetting;

import com.example.bottommenubar.R;

import android.widget.LinearLayout;
import android.widget.TextView;

public class TopTabBarSetting {
	public static TopTabBarSetting instance;
	
	public static TopTabBarSetting getInstance(){
		if(instance == null){
			instance = new TopTabBarSetting();
		}
		return instance;
	}
	
	public int TopTabBarNum = 3;
	public int TopTabBar_Ma_Num = 3;
	public int TopTabBar_Macd_Num = 3;
	public int TopTabBar_Time_Num = 13;
	public TextView[] texts = new TextView[TopTabBarNum];
	public TextView[] linears = new TextView[TopTabBarNum];
	public TextView[] ma_stetting_tx = new TextView[TopTabBar_Ma_Num];
	public TextView[] macd_stetting_tx = new TextView[TopTabBar_Macd_Num];
	public TextView[] time_stetting_tx = new TextView[TopTabBar_Time_Num];
	public int[] linears_id = new int[] { R.id.settings_head_time_interval, R.id.settings_head_ma, R.id.settings_head_macd };
	public int[] layouts_id = new int[] { R.layout.view_chart_time_interval_settings, R.layout.view_chart_ma_settings, R.layout.view_chart_macd_settings};
	public int[] ma_setting_id = new int[] { R.id.chart_ma, R.id.chart_ema, R.id.chart_close };
	public int[] macd_setting_id = new int[] { R.id.chart_macd, R.id.chart_kdj, R.id.chart_close };
	public int[] time_setting_id = new int[] { R.id.chart_em_line, R.id.chart_minute_1, R.id.chart_minute_3, R.id.chart_minute_5, R.id.chart_minute_15, R.id.chart_minute_30,  R.id.chart_hour_1,R.id.chart_hour_2,R.id.chart_hour_4,R.id.chart_hour_6,R.id.chart_hour_12,R.id.chart_day_1,R.id.chart_day_7};
	public int[] ma_str_id = new int[] {R.string.chart_ma ,R.string.chart_ema, R.string.chart_close};
	public int[] macd_str_id = new int[] {R.string.chart_macd ,R.string.chart_kdj, R.string.chart_close};
	public int[] time_str_id = new int[] {R.string.chart_em_line ,R.string.chart_minute_1, R.string.chart_minute_3, R.string.chart_minute_5, R.string.chart_minute_15, R.string.chart_minute_30, R.string.chart_hour_1, R.string.chart_hour_2, R.string.chart_hour_4, R.string.chart_hour_6, R.string.chart_hour_12,R.string.chart_day_1,R.string.chart_day_7};
}
