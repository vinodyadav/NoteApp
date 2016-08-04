package android.pract.com.projectassignment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotesListAdapter extends BaseAdapter {

	Activity act;
	JSONArray arr;
	public NotesListAdapter(Activity act, JSONArray arr) {
		// TODO Auto-generated constructor stub
		this.act = act;
		this.arr = arr; 
	}

	@Override
	public int getCount() {
		if(null==arr)
            return 0;
        else
            return arr.length();
	}

	@Override
	public JSONObject getItem(int position) {
		if(null==arr)
            return null;
        else
            return arr.optJSONObject(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = this.act.getLayoutInflater().inflate(R.layout.item_view, null);
        
		final TextView titleTxt = (TextView)convertView.findViewById(R.id.titleTV);
		final TextView dateTxt = (TextView)convertView.findViewById(R.id.dateTV);
		final TextView descTxt = (TextView)convertView.findViewById(R.id.descTV);
		
		JSONObject obj;
		try {
			obj = arr.getJSONObject(position);
			titleTxt.setText(obj.getString("title"));
			dateTxt.setText(obj.getString("date"));
			descTxt.setText(obj.getString("desc"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				JSONObject obj = null;
				try {
					obj = arr.getJSONObject(position);
					Intent i = new Intent(act, DetailActivity.class);
					i.putExtra("id", obj.getString("id"));
					i.putExtra("title",obj.getString("title"));
					i.putExtra("desc", obj.getString("desc"));
					act.startActivity(i);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		return convertView;
	}

}
