package com.asuper.simplelinechart;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.asuper.simplelinechart.data.MyData;
import com.asuper.simplelinechart.view.SimpleLineChart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<MyData> myData=new ArrayList();
    private SimpleLineChart slc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * 不知道什么鬼
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slc= (SimpleLineChart) findViewById(R.id.main_simpleLineChart);
        MyData md1=new MyData(1+"",53);
        MyData md2=new MyData(2+"",57);
        MyData md3=new MyData(3+"",54);
        MyData md4=new MyData(4+"",49);
        MyData md5=new MyData(5+"",60);
        MyData md6=new MyData(6+"",57);
        MyData md7=new MyData(7+"",48);
        MyData md8=new MyData(8+"",66);
        MyData md9=new MyData(9+"",63);
        MyData md10=new MyData(10+"",61);
        MyData md11=new MyData(11+"",67);
        MyData md12=new MyData(12+"",70);
        MyData md13=new MyData(13+"",64);
        MyData md14=new MyData(14+"",63);
        MyData md15=new MyData(15+"",65);
        MyData md16=new MyData(16+"",77);
        MyData md17=new MyData(17+"",75);
        MyData md18=new MyData(18+"",75);
        MyData md19=new MyData(19+"",73);
        myData.add(md1);
        myData.add(md2);
        myData.add(md3);
        myData.add(md4);
        myData.add(md5);
        myData.add(md6);
        myData.add(md7);
        myData.add(md8);
        myData.add(md9);
        myData.add(md10);
        myData.add(md11);
        myData.add(md12);
        myData.add(md13);
        myData.add(md14);
        myData.add(md15);
        myData.add(md16);
        myData.add(md17);
        myData.add(md18);
        myData.add(md19);
        slc.setMdata(myData);
    }
}
