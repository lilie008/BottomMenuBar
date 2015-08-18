package widget.bottommenubar;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 项目名称：ButtonMenu
 * 类描述：此类为一个父容器，用于存放不使用layout页面的布局View，为选择相应的item内容，简单的做了些缓存机制的逻辑。
 * 创建人：lijie
 * 创建时间：2015/7/9 0009 下午 3:44
 * 修改人：lijie
 * 修改时间：2015/7/9 0009 下午 3:44
 * 修改备注：
 */
public class ViewContainer extends FrameLayout {
    IBottomTabFactory mViewFactory;
    private View tempView = null;
    private View currentView = null;
    private BottomTabLayoutItem bottomTabItemView = null;
    private Map<Integer, View> viewMap = null;
    private int cuIndex;
    private Context mContext;
    LayoutParams newLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

    public ViewContainer(Context context) {
        super(context);
        init(context);
    }

    public ViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        viewMap = new HashMap<Integer, View>();
        cuIndex = -1;
        mContext = context;
    }

    public void setViewFactory(IBottomTabFactory viewFactory) {
        mViewFactory = viewFactory;
    }

    public View getCurrentView() {
        return currentView;
    }


    /**
     * @param index
     * 切换底部菜单
     */
    public void flipToView(int index) {
        if (index == cuIndex)
            return;
        if (viewMap.containsKey(index)) {
            tempView = viewMap.get(index);
            setViewState();
            return;
        }
        tempView = mViewFactory.createView(index);
        viewMap.put(index, tempView);
        this.addView(tempView, newLayout);
        setViewState();
    }

    /**
     * 设置当前页面状态
     */
    private void setViewState() {
        if (currentView != null) {
            bottomTabItemView = (BottomTabLayoutItem) currentView.getTag();
            currentView.setVisibility(View.GONE);
            bottomTabItemView.OnViewHide();
        }
        bottomTabItemView = (BottomTabLayoutItem) tempView.getTag();
        tempView.setVisibility(View.VISIBLE);
        bottomTabItemView.OnViewShow();

        currentView = tempView;
    }

//    private void setViewClickListener() {
//        switch (cuIndex){
//            case 3:
//
//                Button bt = (Button)findViewById(R.id.secondary_confirm);
//                bt.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(!v.isSelected()){
//                            v.setSelected(true);
//                        }else{
//                            v.setSelected(false);
//                        }
//                    }
//                });
//        }
//    }
}
