package com.example.user_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class BidMarketActivity extends AppCompatActivity {

    ListView listView ;

    private List<CurrencyObject> currencyArray = new ArrayList<CurrencyObject>() ;

    private void readCsv(){
        InputStream is = getResources().openRawResource(R.raw.cryptocurrency) ;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,Charset.forName("UTF-8"))) ;

        String line = "" ;

        try {
            line= reader.readLine() ;

            while ((line= reader.readLine()) != null){
                String[] tokens =line.split(",");

                CurrencyObject currencyObject = new CurrencyObject();
                currencyObject.setRank(Integer.parseInt(tokens[2]));
                currencyObject.setSymbol(tokens[0]);
                currencyObject.setName(tokens[1]);

                currencyArray.add(currencyObject) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_market);

        readCsv();

        listView =(ListView) findViewById(R.id.window_list_id) ;
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext() ,currencyArray);
        listView.setAdapter(customAdapter);

        //call when the user select the relevent currency
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BidMarketActivity.this , ActivityCurrency.class);
                intent.putExtra("name", currencyArray.get(i).getName() ) ;
                intent.putExtra("rank" , Integer.toString(currencyArray.get(i).getRank())) ;
                intent.putExtra("symbol", currencyArray.get(i).getSymbol() ) ;

                startActivity(intent);
            }
        });


    }








}