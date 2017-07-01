package ai.tyas;

import android.app.*;
import android.os.*;
import android.widget.LinearLayout;
import android.widget.Button;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.graphics.Rect;
import android.view.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	LinearLayout toolbar;
	View rootv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		rootv=findViewById(R.id.rootview);
		toolbar=(LinearLayout) findViewById(R.id.mainLinearLayout1);
		generateSymbol();
		rootv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
				@Override
				public void onGlobalLayout() {
					Rect r = new Rect();
					rootv.getWindowVisibleDisplayFrame(r);
					int sh = rootv.getRootView().getHeight();
					int kh = sh - r.bottom;
					toolbar.setVisibility(kh > sh * 0.15 ?View.VISIBLE: View.GONE);
				}
			});
    }
	private void generateSymbol(){
		String[] symb=new String[]{"1","2","3", "4","5","6","7","8","9",".","-"};
		for(String s:symb){
			Button b=new Button(this);
			b.setOnClickListener(this);
			TypedValue tv= new TypedValue();
			getTheme().resolveAttribute(android.R.attr.selectableItemBackground, tv, true);
			b.setBackgroundResource(tv.resourceId);
			toolbar.addView(b);
			b.setText(s);
		}
	}
	@Override
	public void onClick(View p1) {
		if(p1 instanceof Button){
			KeyCharacterMap h=KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);
			KeyEvent[] e=h.getEvents(new char[]{((Button)p1).getText().charAt(0)});
			dispatchKeyEvent(e[0]);
		}
	}
}
