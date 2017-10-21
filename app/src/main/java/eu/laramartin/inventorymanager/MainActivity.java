/*
 * PROJECT LICENSE
 *
 * This project was submitted by Lara Martín as part of the Nanodegree At Udacity.
 *
 * As part of Udacity Honor code, your submissions must be your own work, hence
 * submitting this project as yours will cause you to break the Udacity Honor Code
 * and the suspension of your account.
 *
 * Me, the author of the project, allow you to check the code as a reference, but if
 * you submit it, it's your own responsibility if you get expelled.
 *
 * Copyright (c) 2017 Lara Martín
 *
 * Besides the above notice, the following license applies and this license notice
 * must be included in all works derived from this project.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package eu.laramartin.inventorymanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import eu.laramartin.inventorymanager.data.InventoryDbHelper;
import eu.laramartin.inventorymanager.data.StockItem;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getCanonicalName();
    InventoryDbHelper dbHelper;
    StockCursorAdapter adapter;
    int lastVisibleItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new InventoryDbHelper(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readStock();

        adapter = new StockCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == 0) return;
                    final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                    if (currentFirstVisibleItem > lastVisibleItem) {
                        fab.show();
                    } else if (currentFirstVisibleItem < lastVisibleItem) {
                        fab.hide();
                    }
                lastVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(dbHelper.readStock());
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        dbHelper.sellOneItem(id, quantity);
        adapter.swapCursor(dbHelper.readStock());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                // add dummy data for testing
                addDummyData();
                adapter.swapCursor(dbHelper.readStock());
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Add data for demo purposes
     */
    private void addDummyData() {
        StockItem gummibears = new StockItem(
            "Gummibears",
                "10 €",
                45,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/gummibear");
        dbHelper.insertItem(gummibears);

        StockItem peaches = new StockItem(
                "Peaches",
                "10 €",
                24,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/peach");
        dbHelper.insertItem(peaches);

        StockItem cherries = new StockItem(
                "Cherries",
                "11 €",
                74,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/cherry");
        dbHelper.insertItem(cherries);

        StockItem cola = new StockItem(
                "Cola",
                "13 €",
                44,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/cola");
        dbHelper.insertItem(cola);

        StockItem fruitSalad = new StockItem(
                "Fruit salad",
                "20 €",
                34,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/fruit_salad");
        dbHelper.insertItem(fruitSalad);

        StockItem smurfs = new StockItem(
                "Smurfs",
                "12 €",
                26,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/smurfs");
        dbHelper.insertItem(smurfs);

        StockItem fresquito = new StockItem(
                "Fresquito",
                "9 €",
                54,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/fresquito");
        dbHelper.insertItem(fresquito);

        StockItem hotChillies = new StockItem(
                "Hot chillies",
                "13 €",
                12,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/hot_chillies");
        dbHelper.insertItem(hotChillies);

        StockItem lolipopStrawberry = new StockItem(
                "Lolipop strawberry",
                "12 €",
                62,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/lolipop");
        dbHelper.insertItem(lolipopStrawberry);

        StockItem heartGummy = new StockItem(
                "Heart gummy jellies",
                "13 €",
                22,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/heart_gummy");
        dbHelper.insertItem(heartGummy);
    }
}
