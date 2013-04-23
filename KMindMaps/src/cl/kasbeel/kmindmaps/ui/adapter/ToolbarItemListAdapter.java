package cl.kasbeel.kmindmaps.ui.adapter;

import cl.kasbeel.kmindmaps.R;
import cl.kasbeel.kmindmaps.entities.ToolbarItem;
import java.util.ArrayList;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ToolbarItemListAdapter extends ArrayAdapter<ToolbarItem> {
	private final LayoutInflater mInflater;

	public ToolbarItemListAdapter(Context mContext, ArrayList<ToolbarItem>items) {
		super(mContext, 0, items);
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	final public View getView(final int position, View convertView, ViewGroup parent) {
		ItemStorage storage;
		View row = convertView;
		
		if (row == null) {
			row = mInflater.inflate(R.layout.item_toolbar, parent, false);
			row.setOnTouchListener(new ToolTouchListener());
			storage = new ItemStorage();
			storage.tvTool = (TextView) row.findViewById(R.id.tvTool);
			storage.imgTool = (ImageView) row.findViewById(R.id.imgTool);
			
			row.setTag(storage);
		} else {
			storage = (ItemStorage) row.getTag();
		}
		
		ToolbarItem entity = getItem(position);
		storage.tvTool.setText(entity.getName());
		storage.imgTool.setImageResource(entity.getResource());
		
		return row;
	}
	  private final class ToolTouchListener implements OnTouchListener {
		    public boolean onTouch(View view, MotionEvent motionEvent) {
		      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		        ClipData data = ClipData.newPlainText("", "");
		        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
		        view.startDrag(data, shadowBuilder, view, 0);
		        return true;
		      } else {
		        return false;
		      }
		    }
		  }
	private class ItemStorage {
		TextView tvTool;
		ImageView imgTool;
	}

}
