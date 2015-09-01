package widget.charts.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @ClassName: SolveConflictScrollView
 * @Description: 子view的OnTouchEven返回true,SolveConflictScrollView的
 *  onInterceptTouchEvent返回默认时,
 *  解决子View左右滑动冲突
 * @author li
 * @date 2015年7月29日
 *
 */
    
public class SolveConflictScrollView extends ScrollView {
	// 滑动距离及坐标
	private float xDistance, yDistance, xLast, yLast;

	public SolveConflictScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				xDistance = yDistance = 0f;
				xLast = ev.getX();
				yLast = ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				final float curX = ev.getX();
				final float curY = ev.getY();

				xDistance += Math.abs(curX - xLast);
				yDistance += Math.abs(curY - yLast);
				xLast = curX;
				yLast = curY;
				//判断角度
				if (xDistance > yDistance) {
					return false;
				}
		}
		return super.onInterceptTouchEvent(ev);
	}
}
