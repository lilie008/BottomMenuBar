package widget.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class TabContrlBase extends LinearLayout implements OnClickListener{
	protected int CurrentIndex = -1;
	protected ITabItemClickListener mItemClickListener;
	public TabContrlBase(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(this);
	}

	//设置Item状态
	public void setIndexStatu(boolean paramBoolean, int index){
	    if (index >= getChildCount())
	      return;
	    getChildAt(index).setEnabled(paramBoolean);
	  }
	
	public int getSelectedIndex(){
	    return this.CurrentIndex;
	  }
	
	public void onClick(View v)
	  {
	    if ((this.mItemClickListener != null) && (this.mItemClickListener.ItemClickIndex(indexOfChild(v))))
	      return;
	    setSelectedIndex(indexOfChild(v));
	  }

	//动态加载完布局时调用
	  protected void onFinishInflate()
	  {
	    int childCnt = getChildCount();
	    int i = 0;
	    while (i < childCnt)
	    {
	      View localView = getChildAt(i);
	      if (localView instanceof TabItem)
	      {
	        ((TabItem)localView).setOnClickListener(this);
	        if (i == 0)
	        {
	          localView.setSelected(true);
	          this.CurrentIndex = 0;
	        }
	      }else if(localView instanceof TabLayoutItem){
	    	  ((TabLayoutItem)localView).setOnClickListener(this);
		        if (i == 0)
		        {
		          localView.setSelected(true);
		          this.CurrentIndex = 0;
		        }
	      }
	      i += 1;
	    }
	  }

	  public void setOnItemClickListener(ITabItemClickListener listener)
	  {
	    this.mItemClickListener = listener;
	  }

	  public void setSelectedIndex(int paramInt)
	  {
	    if ((paramInt < 0) || (paramInt >= getChildCount()));
	    do
	    {
	      if (this.CurrentIndex == paramInt)
	    	  return;//点击相同view返回
	      if (this.CurrentIndex >= 0)
	        getChildAt(this.CurrentIndex).setSelected(false);
	      this.CurrentIndex = paramInt;
	      getChildAt(this.CurrentIndex).setSelected(true);
	    }
	    while (this.mItemClickListener == null);
	  }
}
