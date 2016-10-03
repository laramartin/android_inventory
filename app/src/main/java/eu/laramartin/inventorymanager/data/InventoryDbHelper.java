package eu.laramartin.inventorymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lara on 2/10/16.
 */
public class InventoryDbHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "inventory.db";
    public final static int DB_VERSION = 1;
    public final static String LOG_TAG = InventoryDbHelper.class.getCanonicalName();

    public InventoryDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(LOG_TAG, "create table: " + StockContract.StockEntry.CREATE_TABLE_STOCK);
        db.execSQL(StockContract.StockEntry.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertItem() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_NAME, "Ramune caramel");
        values.put(StockContract.StockEntry.COLUMN_PRICE, "10");
        values.put(StockContract.StockEntry.COLUMN_QUANTITY, 25);
        values.put(StockContract.StockEntry.COLUMN_SUPPLIER_NAME, "Gumi Candy JP");
        values.put(StockContract.StockEntry.COLUMN_SUPPLIER_PHONE, "+81-123-456-789");
        values.put(StockContract.StockEntry.COLUMN_SUPPLIER_EMAIL, "gumi_candyJP@gmail.com");
        long id = db.insert(StockContract.StockEntry.TABLE_NAME, null, values);
        Log.v(LOG_TAG, "ID row inserted: " + String.valueOf(id));
    }

}
