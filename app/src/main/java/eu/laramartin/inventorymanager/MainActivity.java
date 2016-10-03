package eu.laramartin.inventorymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import eu.laramartin.inventorymanager.data.InventoryDbHelper;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getCanonicalName();
    InventoryDbHelper dbHelper;
    StockCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new InventoryDbHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, NewMessageActivity.class);
//                startActivity(intent);
                Log.v(LOG_TAG, "Floating button pressed");
                dbHelper.insertItem();
            }
        });


        ListView listView = (ListView) findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readStock();
        while (cursor.moveToNext()) {
            Log.v(LOG_TAG, "Stock: " + cursor.getInt(0) + " " + cursor.getString(1) +
                    " " + cursor.getString(2) + " " + cursor.getInt(3));
        }

        adapter = new StockCursorAdapter(this, cursor);
        listView.setAdapter(adapter);


    }


}
