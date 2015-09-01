package com.example.activity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import widget.bottommenubar.BottomTabBar;
import widget.bottommenubar.Finals;
import widget.bottommenubar.IBottomTabIndexFactory;
import widget.tab.ITabItemClickListener;
import widget.tab.TabContrlBase;
import adapter.ListAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bottommenubar.R;


public class MainActivity extends BaseActivity implements IBottomTabIndexFactory, OnItemClickListener{
	//BottomMenu相关参数
	@SuppressLint("UseSparseArrays")
	private Map<Integer,View> mViewMap = new HashMap<Integer,View>();//缓存BottomMenu已设置监听器的view
    BottomTabBar mTabbar;
    int mBottomTabIndex = Finals.MARKET;
    //ChartTopBar相关参数
    private int mCurrentChartIndex = -1;//ChartActivity中Chart选项类型（时间、MA、MACD）
    private int defaultTime_ID = 4;
    private int defaultMa_ID = 2;
    private int defaultMacd_ID = 2;
    private int mTime_ID = defaultTime_ID,mMa_ID = defaultMa_ID,mMacd_ID = defaultMacd_ID;
    //list参数
    LinkedList <Map<String, Object>> mInfo_List = new LinkedList <Map<String,Object>>();
    //
    private int CoinType_BTC = 0;
    private int CoinType_LTC = 1;
    private int TradingType_Spot = 0;
    private int TradingType_Agreements = 1;
    private int TradingType_BuyIn = 0;
    private int TradingType_SellOut = 1;
    private int TradingType_NotDeal = 2;
    private int TradingType_Proxy_histrory = 3;
    private int mCurrentCoinType = -1; //当前币种BTC/LTC
    private int mCurrentTradingType = -1;//当前交易类型
    //适配
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.activity_main);
        mTabbar = new BottomTabBar(this);
        mTabbar.setButtonIndexFactory(this);
        
        bindView();
        if (savedInstanceState != null) {
        	int index = savedInstanceState.getInt("TabBottomIndex");
        	mTabbar.switchViewTab(index);
        }
    }
  

	private void bindView() {
		TabContrlBase market_tab_tool = (TabContrlBase) findViewById(R.id.market_tab_tool);
		TabContrlBase market_tab = (TabContrlBase) findViewById(R.id.market_tab);
		ListView list = (ListView)findViewById(R.id.list);
		
		setMarketBTC_SpotData();
		adapter = new ListAdapter(this, mInfo_List ,mCurrentCoinType , mCurrentTradingType);
		list.setAdapter(adapter);
		market_tab_tool.setOnItemClickListener(new ITabItemClickListener() {
			@Override
			public boolean ItemClickIndex(int index) {
				Toast.makeText(MainActivity.this, "AAAAAAAAAAAAA:" + index, Toast.LENGTH_SHORT).show();
				if(mCurrentCoinType != CoinType_BTC){
					return false;
				}
				
				if(mCurrentCoinType != CoinType_LTC){
					return false;
				}
				return false;
			}
		});
		market_tab.setOnItemClickListener(new ITabItemClickListener() {
			@Override 
			public boolean ItemClickIndex(int index) {
				Toast.makeText(MainActivity.this, "BBBBBBBBBBBB:" + index, Toast.LENGTH_SHORT).show();
				if(mCurrentTradingType != TradingType_Spot){
					setMarketBTC_SpotData();
					mCurrentTradingType = TradingType_Spot;
					return false;//防止选择改变后，执行又执行下面的方法
				}
				if(mCurrentTradingType != TradingType_Agreements){
					setMarketBTC_AgreementsData();
					mCurrentTradingType = TradingType_Agreements;
					return false;
				}
				return false;
			}
		});
		
		list.setOnItemClickListener(this);
	}


	private void setListener(int index) {
        switch (index){
        	case Finals.MARKET:
        		
        		break;
        	case Finals.TRADING:
        		TabContrlBase tab_tool = (TabContrlBase) findViewById(R.id.trading_tab_tool);
        		TabContrlBase trading_tab = (TabContrlBase) findViewById(R.id.trading_tab);
//        		Button btn = (Button)findViewById(R.id.list);
//        		btn.setOnClickListener(new OnClickListener() {
//        			@Override
//        			public void onClick(View v) {
//        				Intent intent = new Intent(MainActivity.this,PriceActivity.class);
//        				intent.putExtra("CurrentChartIndex", CurrentChartIndex);
//        				intent.putExtra("Time_ID", Time_ID);
//        	        	intent.putExtra("Ma_ID", Ma_ID);
//        	        	intent.putExtra("Macd_ID", Macd_ID);
//        				startActivityForResult(intent,0);
//        			}
//        		});
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
            	Button btn_secondary_confirm = (Button) mViewMap.get(index).findViewById(R.id.secondary_confirm);
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
	
	//行情BTC现货数据设置
	private void setMarketBTC_SpotData(){
		if(mCurrentTradingType == TradingType_Spot || mInfo_List == null )
			return;
		mInfo_List.clear();
		Map<String, Object> map = new HashMap<String, Object>();
		map = new HashMap<String, Object>();
		map.put("", "");
		mInfo_List.add(map);
		map = new HashMap<String, Object>();
		map.put("", "");
		mInfo_List.add(map);
		map = new HashMap<String, Object>();
		map.put("", "");
		mInfo_List.add(map);
		map = new HashMap<String, Object>();
		map.put("", "");
		mInfo_List.add(map);
		map = new HashMap<String, Object>();
		map.put("", "");
		mInfo_List.add(map);
		map = new HashMap<String, Object>();
		map.put("", "");
		mInfo_List.add(map);
		if(mCurrentTradingType == -1 && mCurrentCoinType == -1){
			mCurrentTradingType = TradingType_Spot;
			mCurrentCoinType = CoinType_BTC;
			return;
		}
		adapter.notifyDataSetChanged();
	}
	
	//行情BTC合约数据设置
	private void setMarketBTC_AgreementsData(){
		if(mCurrentTradingType == TradingType_Agreements || mInfo_List == null )
			return;
		mInfo_List.clear();
		Map<String, Object> map = new HashMap<String, Object>();
		map = new HashMap<String, Object>();
		map.put("", "");
		mInfo_List.add(map);
		map = new HashMap<String, Object>();
		map.put("", "");
		mInfo_List.add(map);
		map = new HashMap<String, Object>();
		map.put("", "");
		mInfo_List.add(map);
		
		adapter.notifyDataSetChanged();
	}

    //BottomTabIndexFactory选中index回调
    @Override
    public void getBottomTabIndex(int index) {
    	mBottomTabIndex = index;
        if(!mViewMap.containsKey(index)){
        	mViewMap.put(index,mTabbar.viewContainer.getCurrentView());
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
      savedInstanceState.putInt("TabBottomIndex", mBottomTabIndex);
    }   
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	mCurrentChartIndex = data.getIntExtra("CurrentChartIndex", -1);
    	mTime_ID = data.getIntExtra("Time_ID", -1);
    	mMa_ID = data.getIntExtra("Ma_ID", -1);
    	mMacd_ID = data.getIntExtra("Macd_ID", -1);
    }


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(MainActivity.this,PriceActivity.class);
		startActivity(intent);
	}
}
