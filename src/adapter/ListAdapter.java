package adapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.bottommenubar.R;

import android.content.Context;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter{
	private LayoutInflater mInflater;
	private List<Map<String, Object>> mData;
	private int currentCoinType,currentTradingType;
	Context context;
	String view;
		public ListAdapter(Context context,
				LinkedList<Map<String, Object>> info_List, int currentCoinType, int currentTradingType) {
			this.mData = info_List;
			this.context = context;
			this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.currentCoinType= currentCoinType;
			this.currentTradingType = currentTradingType;
//			this.view = context.getResources().getString(R.string.invest_view);
		}
		@Override
		public int getCount() {
			
			if(mData != null){
				return mData.size();
			}
			else{
				return 0;
			}
		}
		
		@Override
		public Object getItem(int position) {
			return null;
		}
		
		@Override
		public long getItemId(int position) {
			return 0;
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Lits_ViewHolder holder = null;
	        if (convertView == null) {
	            holder = new Lits_ViewHolder();  
	            convertView = mInflater.inflate(R.layout.market_list_item, null);
	            holder.big_lastTxt = (TextView)convertView.findViewById(R.id.big_last);
	            holder.small_lastTxt = (TextView)convertView.findViewById(R.id.small_last);
	            holder.volumeTxt = (TextView)convertView.findViewById(R.id.volume);
	            convertView.setTag(holder);
	        }else{
	        	holder = (Lits_ViewHolder)convertView.getTag();
	        }
//	        holder.big_lastTxt.setText(Html.fromHtml(mData.get(position).get("big_last").toString()));
	        return convertView;
		}
		
		public final class Lits_ViewHolder{
			public TextView big_lastTxt,small_lastTxt,volumeTxt;
        }
}
