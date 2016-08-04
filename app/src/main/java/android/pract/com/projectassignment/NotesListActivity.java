package android.pract.com.projectassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

public class NotesListActivity extends CustomAppCompatActivity {
    public TextView noDataTv;
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noDataTv = (TextView)findViewById(R.id.noDataTv);
        listView = (ListView)findViewById(R.id.notesList);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotesListActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(NotesListActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        JSONArray arr = Constant.retriveData(NotesListActivity.this);
        Log.e("arr", arr.toString());
        if(arr.length()<=0){
            listView.setVisibility(View.GONE);
            noDataTv.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            noDataTv.setVisibility(View.GONE);
            NotesListAdapter adapter = new NotesListAdapter(NotesListActivity.this,arr);
            listView.setAdapter(adapter);
        }
    }
}
