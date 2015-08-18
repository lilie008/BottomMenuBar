package widget.bottommenubar;
import android.view.View;

/**
 * 项目名称：ButtonMenu
 * 类描述：切换布局后的布局转为View的回调接口
 * 创建人：lijie
 * 创建时间：2015/7/9 0009 下午 3:57
 * 修改人：lijie
 * 修改时间：2015/7/9 0009 下午 3:57
 * 修改备注：
 */
public interface IBottomTabFactory {
        View createView(int index);
}
