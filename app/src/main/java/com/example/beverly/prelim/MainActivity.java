package com.example.beverly.prelim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView lv;
    ArrayList<Prelim> list=new ArrayList<Prelim>();
    //adapter
    CustomAdapter adapter;

    //define the properties of the UI
    EditText txtName;
    Spinner cboCourse;
    Button btnOk, btnCancel;

    //utility variables
    String selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) this.findViewById(R.id.listview);
        adapter=new CustomAdapter(this,list);

        //delegate the adapter
        lv.setAdapter(adapter);

        //instantiate the properties
        txtName=(EditText)this.findViewById(R.id.fullname);
        cboCourse=(Spinner) this.findViewById(R.id.course);

        btnOk=(Button) this.findViewById(R.id.ok);
        btnCancel=(Button) this.findViewById(R.id.cancel);

        //add listeners to the spinners
        cboCourse.setOnItemSelectedListener(this);
        lv.setAdapter(adapter);

        onBtnClick();
        onCancelClick();


    }

    public void onBtnClick(){
        btnOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //get the data from the EditText
                String name = txtName.getText().toString();


                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);

                //set trappings
                if((txtName.equals("") && cboCourse.equals("-- SELECT COURSE --") && cboCourse.getSelectedItem().equals("-- SELECT COURSE --"))){
                    Toast.makeText(MainActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //display items in the listview
                    list.add(new Prelim(name,selectedCourse));
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    public void onCancelClick(){
        btnCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //clear all the fields
                txtName.setText("");
                cboCourse.setSelection(0);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //check which spinner is touched
        int sid = parent.getId();
        switch(sid){
            case R.id.course: selectedCourse=this.cboCourse.getItemAtPosition(position).toString(); break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}