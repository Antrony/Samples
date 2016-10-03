package com.next.example.sample.uil;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.next.example.sample.R;
import com.next.example.sample.dal.DataAccessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by next on 29/9/16.
 */
public class EmployeeExpandableActivity extends AppCompatActivity {
    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_expandable);
        getviewcasting();

    prepareListData();

    listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

    // setting list adapter
    expListView.setAdapter(listAdapter);

    // Listview Group click listener
    expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v,
        int groupPosition, long id) {
             Toast.makeText(getApplicationContext(),
             "Group Clicked " + listDataHeader.get(groupPosition),
             Toast.LENGTH_SHORT).show();
            return false;
        }
    });

    // Listview Group expanded listener
    expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

        @Override
        public void onGroupExpand(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
        }
    });

    // Listview Group collasped listener
    expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

        @Override
        public void onGroupCollapse(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Collapsed",
                            Toast.LENGTH_SHORT).show();

        }
    });

    // Listview on child click listener
    expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition),
                            Toast.LENGTH_SHORT).show();
            return false;
        }
    });
}

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        // Adding child data
        listDataHeader.add("Moveo");
        listDataHeader.add("Neo");
        listDataHeader.add("Geo");
        listDataHeader.add("Exos");
        listDataHeader.add("Transform");
        listDataHeader.add("Greens");
        // Adding child data
        List<String> moveo = new ArrayList<String>();
        getDatabaseValueBasedOnId(moveo,"Moveo");

        List<String> neo = new ArrayList<String>();
        getDatabaseValueBasedOnId(neo,"Neo");

        List<String> geo = new ArrayList<String>();
        getDatabaseValueBasedOnId(geo,"Geo");

        List<String> exos = new ArrayList<String>();
        getDatabaseValueBasedOnId(exos,"Exos");

        List<String> transform = new ArrayList<String>();
        getDatabaseValueBasedOnId(transform,"Transform");

        List<String> greens = new ArrayList<String>();
        getDatabaseValueBasedOnId(greens,"Greens");


        listDataChild.put(listDataHeader.get(0), moveo); // Header, Child data
        listDataChild.put(listDataHeader.get(1), neo);
        listDataChild.put(listDataHeader.get(2), geo);
        listDataChild.put(listDataHeader.get(3), exos); // Header, Child data
        listDataChild.put(listDataHeader.get(4), transform);
        listDataChild.put(listDataHeader.get(5), greens);
        // Header, Child data
    }
    private void getDatabaseValueBasedOnId(List<String>mList,String mparent) {
        DataAccessLayer dal = new DataAccessLayer(this);
        dal.open();
        Cursor cursor = dal.columnIdFetch(dal.TRN_TABLE_EMPLOYEE,dal.TRN_EMPLOYEE_TEAM,mparent);
        System.out.println("Cursor exp:"+cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String fname = cursor.getString(cursor.getColumnIndex("" + DataAccessLayer.TRN_EMPLOYEE_FIRST_NAME + ""));
            mList.add(fname);
        }
    }
    public void getviewcasting(){
        expListView = (ExpandableListView) findViewById(R.id.eListV_employee);
    }
}
