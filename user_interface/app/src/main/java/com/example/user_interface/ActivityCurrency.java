package com.example.user_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityCurrency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        Intent intent = getIntent() ;

        if(intent != null){
            String name = intent.getStringExtra("name") ;
            String rank = intent.getStringExtra("rank");
            String symbol = intent.getStringExtra("symbol") ;

            TextView currency_symbol_id = (TextView) findViewById(R.id.currency_symbol_id) ;
            TextView currency_rank_id = (TextView) findViewById(R.id.currency_rank_id) ;
            TextView currency_name_id = (TextView) findViewById(R.id.currency_name_id) ;

            currency_name_id.setText(name);
            currency_symbol_id.setText(symbol);
            currency_rank_id.setText(rank);

         }
    }
}