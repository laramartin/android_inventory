package eu.laramartin.inventorymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import eu.laramartin.inventorymanager.data.InventoryDbHelper;

public class DetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailsActivity.class.getCanonicalName();
    private InventoryDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dbHelper = new InventoryDbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_details options from the res/menu_details/menu_editor.xml file.
        // This adds menu_details items to the app bar.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // save item in DB
                Toast.makeText(DetailsActivity.this, "Item saved! ", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
