package com.next.example.sample.uil;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.next.example.sample.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by next on 3/10/16.
 */
public class XMLParsingActivity extends ListActivity {

            // All static variables
            static final String URL = "http://api.androidhive.info/pizza/?format=xml";
            // XML node keys
            static final String KEY_ITEM = "item"; // parent node
            static final String KEY_ID = "id";
            static final String KEY_NAME = "name";
            static final String KEY_COST = "cost";
            static final String KEY_DESC = "description";

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_employee_list);

                ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

                XMLParser parser = new XMLParser();
                String xml = parser.getXmlFromUrl(URL); // getting XML
                Document doc = parser.getDomElement(xml); // getting DOM element

                NodeList nl = doc.getElementsByTagName(KEY_ITEM);
                // looping through all item nodes <item>
                for (int i = 0; i < nl.getLength(); i++) {
                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();
                    Element e = (Element) nl.item(i);
                    // adding each child node to HashMap key => value
                    map.put(KEY_ID, parser.getValue(e, KEY_ID));
                    map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
                    map.put(KEY_COST, "Rs." + parser.getValue(e, KEY_COST));
                    map.put(KEY_DESC, parser.getValue(e, KEY_DESC));

                    // adding HashList to ArrayList
                    menuItems.add(map);
                }

                // Adding menuItems to ListView
                ListAdapter adapter = new SimpleAdapter(this, menuItems,
                        R.layout.listview_item,
                        new String[] { KEY_NAME, KEY_DESC, KEY_COST }, new int[] {
                        R.id.txtV_first, R.id.txtV_last, R.id.txtV_username });

                setListAdapter(adapter);

                // selecting single ListView item
                ListView lv = getListView();
                // listening to single listitem click
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {



                    }
                });
            }
        }
