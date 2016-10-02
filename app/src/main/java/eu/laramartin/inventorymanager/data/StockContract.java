package eu.laramartin.inventorymanager.data;

import android.provider.BaseColumns;

/**
 * Created by lara on 2/10/16.
 */
public class StockContract {

    public StockContract() {
    }

    public static final class StockEntry implements BaseColumns {

        public static final String TABLE_NAME = "stock";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplierName";
        public static final String COLUMN_SUPPLIER_PHONE = "supplierPhone";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplierEmail";

        public static final String SUPPLIER_NAME_DRINKS = "Durinku JP";
        public static final String SUPPLIER_PHONE_DRINKS = "+81 123-456-789";
        public static final String SUPPLIER_EMAIL_DRINKS = "durinkuJP@gmail.com";
        public static final String SUPPLIER_NAME_FOODS = "Gumi Candy JP";
        public static final String SUPPLIER_PHONE_FOODS = "+81 987-654-321";
        public static final String SUPPLIER_EMAIL_FOODS = "gumi_candyJP@gmail.com";
    }
}
