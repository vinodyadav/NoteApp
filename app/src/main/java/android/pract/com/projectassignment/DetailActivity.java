package android.pract.com.projectassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends CustomAppCompatActivity {

    String id = null;
    String title = null;
    String desc = null;

    TextView edit,remove;
    EditText titleTxt, descTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edit = (TextView) findViewById(R.id.edit);
        remove = (TextView) findViewById(R.id.remove);

        titleTxt = (EditText)findViewById(R.id.titleTxt);
        descTxt = (EditText)findViewById(R.id.descTxt);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            id = b.getString("id");
            title = b.getString("title");
            desc = b.getString("desc");

            titleTxt.setText(title);
            descTxt.setText(desc);

        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.editTitleTable(DetailActivity.this, titleTxt.getText().toString(), id);
                Constant.editDescTable(DetailActivity.this, descTxt.getText().toString(), id);
                Constant.showMsg(DetailActivity.this, "Note Updated");
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.deleteRowFromTable(DetailActivity.this,id);
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                finish();
            }
        });

    }

}
