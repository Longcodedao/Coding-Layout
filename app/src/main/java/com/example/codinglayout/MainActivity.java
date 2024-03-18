package com.example.codinglayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // The TextViews of the numbers
    private EditText aNumber;
    private EditText bNumber;

    // Saving the Results
    private ListView resultCal;
    private ArrayList<Float> arrayList;
    private CustomAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the numbers
        aNumber = findViewById(R.id.aNumber);
        bNumber = findViewById(R.id.bNumber);

        // Initialize the buttons
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);

        resultCal = findViewById(R.id.resultCal);
        arrayList = new ArrayList<Float>();
        arrayAdapter = new CustomAdapter(this,
                android.R.layout.simple_list_item_1,
                arrayList);
//        arrayAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                arrayList);


        resultCal.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addResultToList("+");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addResultToList("-");
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                addResultToList("*");
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                addResultToList("/");
            }
        });

        resultCal.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

    }
    private void addResultToList(String operation) {
        Float result = performOperation(operation);
        if (result != null) {
            arrayList.add(result);
            arrayAdapter.notifyDataSetChanged(); // Update the ListView
        }
    }

    private Float performOperation(String operation) {
        String aValueString = aNumber.getText().toString();
        String bValueString = bNumber.getText().toString();

        if (!aValueString.isEmpty() && !bValueString.isEmpty()){
            float aValue = Float.parseFloat(aValueString);
            float bValue = Float.parseFloat(bValueString);

            // Perform Addition
            float result = 0;

            switch (operation) {
                case "+":
                    result = aValue + bValue;
                    break;
                case "-":
                    result = aValue - bValue;
                    break;
                case "*":
                    result = aValue * bValue;
                    break;

                case "/":
                    if (bValue != 0){
                        result = aValue / bValue;
                    }else{
                        Toast.makeText(MainActivity.this, "Cannot divide by zero",
                                        Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    break;
            }
            return result;
        }else {
            Toast.makeText(MainActivity.this, "Please enter both numbers",
                            Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}