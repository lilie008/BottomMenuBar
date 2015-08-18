package widget.bottommenubar;

import com.example.bottommenubar.R;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 项目名称：ButtonMenu
 * 类描述：菜单设置类（底部菜单第二步，设置菜单数量、图标、文字、布局等）
 * 创建人：lijie
 * 创建时间：2015/7/9 0009 下午 4:07
 * 修改人：lijie
 * 修改时间：2015/7/9 0009 下午 4:07
 * 修改备注：
 */
public class BottomTabSetting {

    public static BottomTabSetting instance;

    public static BottomTabSetting getInstance() {
        if (instance == null) {
            instance = new BottomTabSetting();
        }
        return instance;
    }

    public int viewNum = 4;
    public ImageView[] images = new ImageView[viewNum];
    public TextView[] texts = new TextView[viewNum];
    public LinearLayout[] linears = new LinearLayout[viewNum];
    public int[] images_id = new int[] { R.id.market_image, R.id.trading_image, R.id.future_image, R.id.setting_image };
    public int[] texts_id = new int[] { R.id.market_text, R.id.trading_text, R.id.future_text, R.id.setting_text };
    public int[] linears_id = new int[] { R.id.market_layout, R.id.trading_layout, R.id.future_layout, R.id.setting_layout };
    public int[] images_selected = new int[] { R.drawable.market_selected, R.drawable.trading_selected, R.drawable.future_selected, R.drawable.setting_selected };
    public int[] images_unselected = new int[] { R.drawable.market_unselected, R.drawable.trading_unselected, R.drawable.future_unselected, R.drawable.setting_unselected };
    public int[] layouts_id = new int[] { R.layout.market_layout, R.layout.trading_layout, R.layout.future_layout, R.layout.setting_layout };

}

