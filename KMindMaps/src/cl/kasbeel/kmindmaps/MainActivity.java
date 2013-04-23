package cl.kasbeel.kmindmaps;

import java.util.ArrayList;

import cl.kasbeel.kmindmaps.entities.ToolbarItem;
import cl.kasbeel.kmindmaps.ui.adapter.ToolbarItemListAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView lstToolbar;
	private ImageView imgExpandCollaps;
	private TextView tvToolbar;
	private RelativeLayout rlDrawingScreen;
	private ArrayList<ToolbarItem> alToolbarItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get using findViewById
        tvToolbar = (TextView) findViewById(R.id.tvToolbar);
        imgExpandCollaps = (ImageView) findViewById(R.id.imgExpandCollaps);
    	lstToolbar = (ListView) findViewById(R.id.lstToolbar);
    	rlDrawingScreen = (RelativeLayout) findViewById(R.id.rlDrawingScreen);
    	// Initialize components
    	// lstToolbar
    	alToolbarItems = setupToolbar();
    	ToolbarItemListAdapter laToolbarAdapter = new ToolbarItemListAdapter(this, alToolbarItems);
    	lstToolbar.setAdapter(laToolbarAdapter);
    	// imgExpandCollaps
    	imgExpandCollaps.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imgExpandCollaps_onClick(v);
			}
		});
    	// rlDrawingScreen
    	rlDrawingScreen.setOnDragListener(new MyDragListener());
    	
    }
    private ArrayList<ToolbarItem> setupToolbar(){
    	ArrayList<ToolbarItem> arList = new ArrayList<ToolbarItem>();
    	arList.add(newItem(R.string.new_item,R.drawable.burst_icon));
    	return arList;
    }
    private ToolbarItem newItem(int name, int image){
    	ToolbarItem item = new ToolbarItem(this.getResources().getString(name),image);
    	return item;
    }
    private void imgExpandCollaps_onClick(View v) {
		// TODO Auto-generated method stub
    	if(tvToolbar.getVisibility() == View.VISIBLE){
    		imgExpandCollaps.setImageResource(R.drawable.br_prev_icon);
    		tvToolbar.setVisibility(View.GONE);
    		lstToolbar.setVisibility(View.GONE);
    	}else{
    		imgExpandCollaps.setImageResource(R.drawable.br_next_icon);
    		tvToolbar.setVisibility(View.VISIBLE);
    		lstToolbar.setVisibility(View.VISIBLE);
    	}
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	private  class MyDragListener implements OnDragListener {
	    @Override
	    public boolean onDrag(View v, DragEvent event) {
	      int action = event.getAction();
	      switch (action) {
	      case DragEvent.ACTION_DRAG_STARTED:
	        // Do nothing
	        break;
	      case DragEvent.ACTION_DRAG_ENTERED:
	        
	        break;
	      case DragEvent.ACTION_DRAG_EXITED:
	        
	        break;
	      case DragEvent.ACTION_DROP:
	        // Dropped, reassign View to ViewGroup
	        RelativeLayout container = (RelativeLayout) v;
	        TextView tx = new TextView(MainActivity.this);
	        RelativeLayout.LayoutParams lpPosicion = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	        int left = ((int)(event.getX()/150))*150;
	        int top = ((int)(event.getY()/50))*50;
	        lpPosicion.setMargins(left, top, 0, 0);
	        tx.setLayoutParams(lpPosicion);
	        tx.setBackgroundColor(Color.BLUE);
	        tx.setText("Test");
	        container.addView(tx);
	        break;
	      case DragEvent.ACTION_DRAG_ENDED:
	      default:
	        break;
	      }
	      return true;
	    }
	  }
    
}
