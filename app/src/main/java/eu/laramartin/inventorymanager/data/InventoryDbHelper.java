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

package eu.laramartin.inventorymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL(StockContract.StockEntry.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertItem(StockItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_NAME, item.getProductName());
        values.put(StockContract.StockEntry.COLUMN_PRICE, item.getPrice());
        values.put(StockContract.StockEntry.COLUMN_QUANTITY, item.getQuantity());
        values.put(StockContract.StockEntry.COLUMN_SUPPLIER_NAME, item.getSupplierName());
        values.put(StockContract.StockEntry.COLUMN_SUPPLIER_PHONE, item.getSupplierPhone());
        values.put(StockContract.StockEntry.COLUMN_SUPPLIER_EMAIL, item.getSupplierEmail());
        values.put(StockContract.StockEntry.COLUMN_IMAGE, item.getImage());
        long id = db.insert(StockContract.StockEntry.TABLE_NAME, null, values);
    }

    public Cursor readStock() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                StockContract.StockEntry._ID,
                StockContract.StockEntry.COLUMN_NAME,
                StockContract.StockEntry.COLUMN_PRICE,
                StockContract.StockEntry.COLUMN_QUANTITY,
                StockContract.StockEntry.COLUMN_SUPPLIER_NAME,
                StockContract.StockEntry.COLUMN_SUPPLIER_PHONE,
                StockContract.StockEntry.COLUMN_SUPPLIER_EMAIL,
                StockContract.StockEntry.COLUMN_IMAGE
        };
        Cursor cursor = db.query(
                StockContract.StockEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                StockContract.StockEntry._ID,
                StockContract.StockEntry.COLUMN_NAME,
                StockContract.StockEntry.COLUMN_PRICE,
                StockContract.StockEntry.COLUMN_QUANTITY,
                StockContract.StockEntry.COLUMN_SUPPLIER_NAME,
                StockContract.StockEntry.COLUMN_SUPPLIER_PHONE,
                StockContract.StockEntry.COLUMN_SUPPLIER_EMAIL,
                StockContract.StockEntry.COLUMN_IMAGE
        };
        String selection = StockContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };

        Cursor cursor = db.query(
                StockContract.StockEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_QUANTITY, quantity);
        String selection = StockContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(currentItemId) };
        db.update(StockContract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public void sellOneItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity -1;
        }
        ContentValues values = new ContentValues();
        values.put(StockContract.StockEntry.COLUMN_QUANTITY, newQuantity);
        String selection = StockContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };
        db.update(StockContract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
