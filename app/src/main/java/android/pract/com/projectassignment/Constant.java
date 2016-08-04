package android.pract.com.projectassignment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Constant {

	public static SQLiteDatabase myDb;
	public static String dBName = "NOTES.db";
	public static String tblName = "NOTE_TABLE";

	public static void createTable(Context ctx) {
		try {
			myDb = ctx.openOrCreateDatabase(dBName, Context.MODE_PRIVATE, null);
			myDb.execSQL("CREATE TABLE IF NOT EXISTS "+tblName+" (ID INTEGER PRIMARY KEY,TITLE TEXT,DESCRIPTION TEXT,DATE TEXT);");
			myDb.close();
			//showMsg(ctx,"table created");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			showMsg(ctx,"creating table error");
		}
	} 

	public static void insertIntoTable(Context ctx, String title,String desc,String date) {
		try {
			myDb = ctx.openOrCreateDatabase(dBName, Context.MODE_PRIVATE, null);
			myDb.execSQL("INSERT INTO "+tblName+" (TITLE,DESCRIPTION,DATE) VALUES('"+title+"','"+desc+"','"+date+"')");
			myDb.close();
			showMsg(ctx, "Note Created");
			getRowCount(ctx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			showMsg(ctx,"inserting into table error");
		}
	}

	public static void editTable(Context ctx, String attrToBeUpdate,String newData,String id) {
		try {
			myDb = ctx.openOrCreateDatabase(dBName, Context.MODE_PRIVATE, null);
			String updateStr = "UPDATE " + tblName + " SET " + attrToBeUpdate + " = '" + newData + "' WHERE ID =" + id;
			//myDb.execSQL(updateStr);
			myDb.rawQuery(updateStr, null);
			myDb.close();
			//showMsg(ctx, "Note Updated");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			showMsg(ctx,"updating table error");
		}
	}

	public static void editTitleTable(Context ctx, String newData,String id) {
		try {
			myDb = ctx.openOrCreateDatabase(dBName, Context.MODE_PRIVATE, null);
			Log.e("ID",""+id);
			Log.e("newdata",""+newData);
			String update = "UPDATE NOTE_TABLE SET TITLE = '"+ newData +"' WHERE ID = '" + id+"'";
			myDb.execSQL(update);
			//showMsg(ctx, "Note Updated");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			showMsg(ctx,"updating table error");
		}
	}

	public static void editDescTable(Context ctx,String newData,String id) {
		try {
			myDb = ctx.openOrCreateDatabase(dBName, Context.MODE_PRIVATE, null);
			String update = "UPDATE NOTE_TABLE SET DESCRIPTION = '"+ newData +"' WHERE ID = " + id;
			myDb.execSQL(update);
			showMsg(ctx, "Note Updated");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			showMsg(ctx,"updating table error");
		}
	}

	public static void deleteRowFromTable(Context ctx, String id) {
		try {
			myDb = ctx.openOrCreateDatabase(dBName, Context.MODE_PRIVATE, null);
			myDb.execSQL("DELETE FROM "+tblName+" WHERE ID = '"+id+"' ");
			myDb.close();
			showMsg(ctx, "Note deleted");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			showMsg(ctx,"deleting table row error");
		}
	}

	public static void getRowCount(Context ctx) {
		try {
			myDb = ctx.openOrCreateDatabase(dBName, Context.MODE_PRIVATE, null);
			Cursor mCursor = myDb.rawQuery("SELECT * FROM "+tblName, null);
			//showMsg(ctx,"row count is "+mCursor.getCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			showMsg(ctx,"counting error");
		}
	}

	public static int getRowLength(Context ctx) {
		int length = 0;
		try {
			myDb = ctx.openOrCreateDatabase(dBName, Context.MODE_PRIVATE, null);
			Cursor mCursor = myDb.rawQuery("SELECT * FROM "+tblName, null);
			length = mCursor.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			showMsg(ctx,"counting error");
		}
		return length;
	}

	public static JSONArray retriveData(Context ctx) {
		JSONArray arr = new JSONArray();
		try {
			myDb = ctx.openOrCreateDatabase(dBName, Context.MODE_PRIVATE, null);
			Cursor mCurser = myDb.rawQuery("SELECT * FROM "+ tblName, null);

			if(mCurser.moveToFirst()){
				do{
					String id = mCurser.getString(0);
					String title = mCurser.getString(1);
					String desc = mCurser.getString(2);
					String date = mCurser.getString(3);

					try {
						JSONObject obj = new JSONObject();
						obj.put("id", id);
						obj.put("title", title);
						obj.put("desc", desc);
						obj.put("date", date);
						arr.put(obj);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while(mCurser.moveToNext());
			}
			myDb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arr;
	}

	public static void showMsg(Context ctx, String msg) {
		if(msg!=null || !msg.equals("") || !msg.equals("null")) {
			Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
		}
	}


	/*
	public static void showMsg(Context ctx, String msg) {
		if(msg!=null || !msg.equals("") || !msg.equals("null")) {
			Snackbar.make(((Activity)ctx).findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
		}
	}
	*/
}
