package pl.allergyfoodadvisor.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import pl.allergyfoodadvisor.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

}
