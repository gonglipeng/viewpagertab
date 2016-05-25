package tarena.example.com.viewpagertab;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

public class MainActivity extends Activity {

    private IndicatorViewPager indicatorViewPager;
    private ScrollIndicatorView moretabIndicator;
    private ViewPager moretabViewPager;
    private String[] names;
    LayoutInflater inflate;

    private void assignViews() {
        moretabIndicator = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);
        moretabViewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        addData();

        //设置显示小球
        /*moretabIndicator.setScrollBar(new SpringBar(getApplicationContext(), Color.GRAY));*/
        //设置tab下面线高度，宽度相对其它的tab的内容
        /*moretabIndicator.setScrollBar(new TextWidthColorBar(this, moretabIndicator, Color.RED, 10));*/
        //设置tab下面线高度，宽度相对viewpager
        moretabIndicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 10));
        Resources res = getResources();
        int selectColor = res.getColor(R.color.tab_top_text_2);
        int unSelectColor = res.getColor(R.color.tab_top_text_1);
        moretabIndicator.setOnTransitionListener(
                new OnTransitionTextListener().
                        setColor(selectColor, unSelectColor).   //设置tab上的字体颜色(分选中和没选中)
                        setSize(22, 16));    //设置tab上字体的大小(分选中和没选中)
        moretabViewPager.setOffscreenPageLimit(2);
        inflate = LayoutInflater.from(getApplicationContext());
        indicatorViewPager = new IndicatorViewPager(moretabIndicator, moretabViewPager);
        indicatorViewPager.setAdapter(new MyAdapter());
    }

    private void addData() {
        names = new String[]{"CUPCAKE", "DONUT", "FROYO", "GINGERBREAD", "HONEYCOMB", "ICE CREAM SANDWICH", "JELLY BEAN", "KITKAT"};
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

        public MyAdapter() {
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText("" + position);
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            convertView = inflate.inflate(R.layout.viewpage_item, container, false);
            TextView tv = (TextView) convertView.findViewById(R.id.textview);
            tv.setText(names[position]);
            return convertView;
        }

        @Override
        public int getCount() {
            return 2;
        }


    }

    /**
     * 根据dip值转化成px值
     *
     * @param context
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }
}



