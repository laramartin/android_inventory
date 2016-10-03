package eu.laramartin.inventorymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailsActivity.class.getCanonicalName();
    Spinner currencySpinner;
    private String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        currencySpinner = (Spinner) findViewById(R.id.currency_spinner);
        setupSpinner();
    }

    private void setupSpinner() {
        final ArrayAdapter currencySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_currency_options, android.R.layout.simple_spinner_item);
        currencySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        currencySpinner.setAdapter(currencySpinnerAdapter);
        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.currency_japan))) {
                        currency = getString(R.string.currency_japan);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currency = "";
            }
        });
    }
}
