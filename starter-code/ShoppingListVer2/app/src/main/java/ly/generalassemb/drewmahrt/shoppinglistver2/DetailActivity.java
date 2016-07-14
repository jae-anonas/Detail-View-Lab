package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_detail_layout);

        TextView name = (TextView) findViewById(R.id.text_itemName);
        TextView type = (TextView) findViewById(R.id.text_itemType);
        TextView price = (TextView) findViewById(R.id.text_price);
        TextView desc = (TextView) findViewById(R.id.text_desc);

        int query = getIntent().getIntExtra(ShoppingSQLiteOpenHelper.COL_ID, 0);

        Cursor cursor = ShoppingSQLiteOpenHelper.getInstance(this).searchItemByID(query);

        if (cursor.moveToFirst()){
            name.setText(cursor.getString(cursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_NAME)));
            type.setText(cursor.getString(cursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_TYPE)));
            String strPrice = String.format(Locale.ENGLISH, "$ %s", cursor.getString(cursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_PRICE)));
            price.setText(strPrice);
            desc.setText(cursor.getString(cursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_DESCRIPTION)));
        }

        cursor.close();
    }
}
