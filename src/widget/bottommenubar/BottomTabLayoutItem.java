package widget.bottommenubar;

import android.app.Activity;
import android.view.View;

/**
 * 项目名称：ButtonMenu
 * 类描述：用于实现不同的布局页面和相关页面操作的基类
 * 创建人：lijie
 * 创建时间：2015/7/9 0009 下午 2:53
 * 修改人：lijie
 * 修改时间：2015/7/9 0009 下午 2:53
 * 修改备注：
 */
public class BottomTabLayoutItem {
    protected Activity mContext;
    protected BottomTabBar mTab;
    protected View view;
    /**
     * @param context 当前activity
     * @param layoutId 布局文件
     * @param tab  底部菜单类
     */
    public BottomTabLayoutItem(Activity context, int layoutId, BottomTabBar tab){
        this.mContext = context;
        this.mTab = tab;
        view = mContext.getLayoutInflater().inflate(layoutId, null);
        view.setTag(this);
    }


    /**
     * @return View实例
     */
    public View getView() {
        return view;
    }

    /**
     * @return 底部菜单实例
     */
    public BottomTabBar getTab() {
        return mTab;
    }

    /**
     *  进入底部菜单Item时调用
     */
    public void OnViewShow() {

    };

    /**
     * 切换底部菜单Ite时调用
     */
    public void OnViewHide() {

    };
}
