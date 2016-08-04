package android.pract.com.projectassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends CustomAppCompatActivity {

    EditText titleTxt,descTxt;
    TextView submitBtn;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Constant.createTable(MainActivity.this);

        /*if(Constant.getRowLength(MainActivity.this)>=1){
            startActivity(new Intent(MainActivity.this, NotesListActivity.class));
        }*/

        fab = (FloatingActionButton) findViewById(R.id.fab);

        titleTxt = (EditText)findViewById(R.id.titleTxt);
        descTxt = (EditText)findViewById(R.id.descTxt);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotesListActivity.class));
                finish();
            }
        });

        submitBtn = (TextView)findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String title = titleTxt.getText().toString();
                String desc = descTxt.getText().toString();

                if(title.equals("")) {
                    Constant.showMsg(MainActivity.this, "Title can't be blank");
                } else if(desc.equals("")){
                    Constant.showMsg(MainActivity.this, "Description can't be blank");
                } else {
                    String date = getDate();
                    Constant.insertIntoTable(MainActivity.this,title, desc.trim(), date);
                    startActivity(new Intent(MainActivity.this, NotesListActivity.class));
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //startActivity(new Intent(MainActivity.this,NotesListActivity.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getDate() {
        String dateStr;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateStr = df.format(c.getTime());
        return dateStr;
    }
}
