package com.example.unihire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FilterInput extends AppCompatActivity {
    public static final String EXTRA_TITLE="com.example.unihire.EXTRA_TITLE";
    public static final String EXTRA_DEPT="com.example.unihire.EXTRA_DEPT";
    public static final String EXTRA_UNIVNAME="com.example.unihire.EXTRA_UNIVname";
    public static final String EXTRA_SPECIALIZATION="com.example.unihire.EXTRA_SPECIALIZATION";
    public static final Boolean EXTRA_ISEMPTYTITLE=null;
    public static final Boolean EXTRA_ISEMPTYDEPT=null;
    public static final Boolean EXTRA_ISEMPTYSPEC=null;
    public static final Boolean EXTRA_ISEMPTYUNIVNAME=null;
    Button searchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_input);

        searchBtn = (Button) findViewById(R.id.search_next);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_multi_search();
            }
        });
    }
    public void activity_multi_search(){
        EditText search_title, search_dept, search_specialization,search_univname;
        Boolean isEmptytitle = null, isEmptydept=null, isEmptyspec=null, isEmptyunivname=null;
        search_title = (EditText) findViewById(R.id.search_title);
        String text_title=search_title.getText().toString();
        if(text_title==null)
            isEmptytitle=false;
        search_dept = (EditText) findViewById(R.id.search_dept);
        String text_dept=search_dept.getText().toString();
        if(text_dept==null)
            isEmptydept=false;
        search_univname=(EditText) findViewById(R.id.search_univname);
        String text_univname = search_univname.getText().toString();
        if(text_univname==null)
            isEmptyunivname=false;
        search_specialization = (EditText) findViewById(R.id.search_specialization);
        String text_specialization=search_specialization.getText().toString();
        if(text_specialization==null)
            isEmptyspec=false;
        Intent intent = new Intent(this, MultiSearch.class);
        intent.putExtra(EXTRA_TITLE, isEmptytitle);
        intent.putExtra(EXTRA_DEPT, isEmptydept);
        intent.putExtra(EXTRA_UNIVNAME, isEmptyunivname);
        intent.putExtra(EXTRA_SPECIALIZATION, isEmptyspec);
            intent.putExtra(EXTRA_TITLE, text_title);
            intent.putExtra(EXTRA_DEPT, text_dept);
            intent.putExtra(EXTRA_UNIVNAME, text_univname);
            intent.putExtra(EXTRA_SPECIALIZATION, text_specialization);
        startActivity(intent);
    }
}