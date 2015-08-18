package com.example.activity;

import java.util.HashMap;
import java.util.Map;

import widget.ITabItemClickListener;
import widget.TabContrlBase;
import widget.bottommenubar.BottomTabBar;
import widget.bottommenubar.Finals;
import widget.bottommenubar.IBottomTabIndexFactory;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.bottommenubar.R;


public class MainActivity extends BaseActivity implements IBottomTabIndexFactory{
	//BottomMenu相关参数
	@SuppressLint("UseSparseArrays")
	private Map<Integer,View> viewMap = new HashMap<Integer,View>();//缓存BottomMenu已设置监听器的view
    BottomTabBar tabbar;
    int bottomTabIndex = Finals.MARKET;
    //ChartTopBar相关参数
    private int CurrentChartIndex = -1;//ChartActivity中Chart选项类型（时间、MA、MACD）
    private int defaultTime_ID = 4;
    private int defaultMa_ID = 2;
    private int defaultMacd_ID = 2;
    private int Time_ID = defaultTime_ID,Ma_ID = defaultMa_ID,Macd_ID = defaultMacd_ID;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.activity_main);
        tabbar = new BottomTabBar(this);
        tabbar.setButtonIndexFactory(this);
        
        bindView();
        if (savedInstanceState != null) {
        	int index = savedInstanceState.getInt("TabBottomIndex");
        	tabbar.switchViewTab(index);
        }
    }
  

	private void bindView() {
		TabContrlBase market_tab_tool = (TabContrlBase) findViewById(R.id.market_tab_tool);
		TabContrlBase market_tab = (TabContrlBase) findViewById(R.id.market_tab);
//		ListView list = (ListView)findViewById(R.id.list);
		
		market_tab_tool.setOnItemClickListener(new ITabItemClickListener() {
			@Override
			public boolean ItemClickIndex(int index) {
				Toast.makeText(MainActivity.this, "AAAAAAAAAAAAA:" + index, Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		market_tab.setOnItemClickListener(new ITabItemClickListener() {
			@Override
			public boolean ItemClickIndex(int index) {
				Toast.makeText(MainActivity.this, "BBBBBBBBBBBB:" + index, Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}


	public void setListener(int index) {
        switch (index){
        	case Finals.MARKET:
        		
        		break;
        	case Finals.TRADING:
        		TabContrlBase tab_tool = (TabContrlBase) findViewById(R.id.trading_tab_tool);
        		TabContrlBase trading_tab = (TabContrlBase) findViewById(R.id.trading_tab);
        		Button btn = (Button)findViewById(R.id.list);
        		btn.setOnClickListener(new OnClickListener() {
        			@Override
        			public void onClick(View v) {
        				Intent intent = new Intent(MainActivity.this,PriceActivity.class);
        				intent.putExtra("CurrentChartIndex", CurrentChartIndex);
        				intent.putExtra("Time_ID", Time_ID);
        	        	intent.putExtra("Ma_ID", Ma_ID);
        	        	intent.putExtra("Macd_ID", Macd_ID);
        				startActivityForResult(intent,0);
        			}
        		});
        		tab_tool.setOnItemClickListener(new ITabItemClickListener() {
        			@Override
        			public boolean ItemClickIndex(int index) {
        				Toast.makeText(MainActivity.this, "CCCCCCCCC:" + index, Toast.LENGTH_SHORT).show();
        				return false;
        			}
        		});
        		trading_tab.setOnItemClickListener(new ITabItemClickListener() {
					@Override
					public boolean ItemClickIndex(int index) {
						Toast.makeText(MainActivity.this, "DDDDDDDDDDD:" + index, Toast.LENGTH_SHORT).show();
						return false;
					}
				});
        		break;
            case Finals.SETTING:
            	Button btn_secondary_confirm = (Button) viewMap.get(index).findViewById(R.id.secondary_confirm);
                btn_secondary_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!v.isSelected()){
                            v.setSelected(true);
                        }else{
                            v.setSelected(false);
                        }
                    }
                });
                break;
        }
    }

    //BottomTabIndexFactory选中index回调
    @Override
    public void getBottomTabIndex(int index) {
    	bottomTabIndex = index;
        if(!viewMap.containsKey(index)){
            viewMap.put(index,tabbar.viewContainer.getCurrentView());
            setListener(index);
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) { 
        	finish();
        	System.exit(0);
        }
        return false;
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
      super.onSaveInstanceState(savedInstanceState);
      savedInstanceState.putInt("TabBottomIndex", bottomTabIndex);
    }   
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	CurrentChartIndex = data.getIntExtra("CurrentChartIndex", -1);
    	Time_ID = data.getIntExtra("Time_ID", -1);
    	Ma_ID = data.getIntExtra("Ma_ID", -1);
    	Macd_ID = data.getIntExtra("Macd_ID", -1);
    }
}
