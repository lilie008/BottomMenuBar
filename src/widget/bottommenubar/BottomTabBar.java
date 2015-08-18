package widget.bottommenubar;

import com.example.bottommenubar.R;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 项目名称：ButtonMenu
 * 类描述：底部菜单类(实现底部菜单的第一步，先定义底部菜单类，然后实现点击接口和点击后内容view接口)
 * 创建人：lijie
 * 创建时间：2015/7/9 0009 下午 3:14
 * 修改人：lijie
 * 修改时间：2015/7/9 0009 下午 3:14
 * 修改备注：
 */
public class BottomTabBar implements IBottomTabFactory,View.OnClickListener {
    /**
     * 用于保存当前activity
     */
    Activity mContext;
    /**
     * 当前页面焦点,即显示的页面索引
     */
    int mCurrentFocus = -1;
    /**
     * 切换页面时上一个view的索引
     */
    int lastIndex = -1;

    /**
     * 底部菜单栏初始化所有控件类的一个实例
     */
    BottomTabSetting item;

    /**
     * 存放内容的布局容器
     */
    public ViewContainer viewContainer;

    /**
     * 首次创建页面的临时view
     */
    public BottomTabLayoutItem newView;


    IBottomTabIndexFactory mbuttonIndexFactory;

    /**
     * @param context
     * 构造并且初始化底部菜单
     */
    public BottomTabBar(Activity context) {
        mContext = context;
        item = BottomTabSetting.getInstance();
        initTab();
    }
    
    public int getBottomIndex(){
    	return mCurrentFocus;
    }

    /**
     * 初始化
     */
    private void initTab() {
        //绑定id
        for (int i = 0; i < item.viewNum; i++) {
            item.linears[i] = (LinearLayout) mContext.findViewById(item.linears_id[i]);
            item.linears[i].setOnClickListener(this);
            item.images[i] = (ImageView) mContext.findViewById(item.images_id[i]);
            item.texts[i] = (TextView) mContext.findViewById(item.texts_id[i]);
        }
        //初始化ViewContainer
        viewContainer = (ViewContainer) mContext.findViewById(R.id.main_view_container);
        viewContainer.setViewFactory(this);
        switchViewTab(0);
    }

    public void setButtonIndexFactory(IBottomTabIndexFactory bottomIndexFactory) {
        mbuttonIndexFactory = bottomIndexFactory;
    }

    public void switchViewTab(int index) {
        if (index == mCurrentFocus)
            return;
        setViewTab(index);
        viewContainer.flipToView(index);
    }

    private void setViewTab(int index) {
        if (index == mCurrentFocus)
            return;
        lastIndex = mCurrentFocus;
        mCurrentFocus = index;
        for (int i = 0; i < item.viewNum; i++) {
            item.images[i].setBackgroundResource(i == index ? item.images_selected[i] : item.images_unselected[i]);
            item.texts[i].setTextColor(i == index ? mContext.getResources().getColor(R.color.bottom_text_selected) : mContext.getResources().getColor(R.color.bottom_text_unselected));
        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < item.viewNum; i++) {
            if (v.getId() == item.linears_id[i]) {
                switchViewTab(i);
                mbuttonIndexFactory.getBottomTabIndex(i);//实现方法回调给Activity
            }
        }
    }
    //新的内容选中后View的回调，生成新的内容
    @Override
    public View createView(int index) {
        newView = new BottomTabLayoutItem(mContext, item.layouts_id[index], this);
        return newView.getView();
    }
}
