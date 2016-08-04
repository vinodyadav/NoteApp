package android.pract.com.projectassignment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by clean on 7/4/16.
 */

public class CustomAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimForActivityOnCreate(this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setAnimForActivityOnFinish(this);
    }

    public static void setAnimForActivityOnCreate(Activity activity) {
        activity.overridePendingTransition(R.anim.activityentering_oncreate, R.anim.activityleaveing_oncreate);
    }

    public static void setAnimForActivityOnFinish(Activity activity) {
        activity.overridePendingTransition(R.anim.activityentering_onfinish, R.anim.activityleaving_onfinish);
    }



}
