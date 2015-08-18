package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bottommenubar.R;

public class TabLayoutItem extends LinearLayout{
	String mTxt;
	Context mcontext;
	public TabLayoutItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		mcontext = context;
		View view= LayoutInflater.from(mcontext).inflate(R.layout.view_tab_item_default_layout, null);
		switch (this.getId()) {
		case R.id.buy_in:
			((TextView)view.findViewById(R.id.tablayoutitem_text)).setText(R.string.buy_in);
			setGravity(Gravity.CENTER);
			break;
		case R.id.sell_out:
			((TextView)view.findViewById(R.id.tablayoutitem_text)).setText(R.string.sell_out);
			setGravity(Gravity.CENTER);
			break;
		case R.id.not_deal:
			((TextView)view.findViewById(R.id.tablayoutitem_text)).setText(R.string.not_deal);
			setGravity(Gravity.CENTER);
			break;
		case R.id.proxy_histrory:
			((TextView)view.findViewById(R.id.tablayoutitem_text)).setText(R.string.proxy_histrory);
			setGravity(Gravity.CENTER);
			break;
		case R.id.spot:
			((TextView)view.findViewById(R.id.tablayoutitem_text)).setText(R.string.spot);
			setGravity(Gravity.CENTER);
			break;
		case R.id.agreements:
			((TextView)view.findViewById(R.id.tablayoutitem_text)).setText(R.string.agreements);
			setGravity(Gravity.CENTER);
			break;
		default:
			break;
		}
		addView(view);
	}
}
