package com.next.example.sample.uil;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.next.example.sample.R;
import com.next.example.sample.bal.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RecyclerActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;
    //URL to get JSON Array
    private static String url = "http://www.json-generator.com/api/json/get/cbaBvPPZfm?indent=2";

    //JSON Node Names
    private static final String TAG_EMPLOYEE= "employee";

    private static final String TAG_NAME = "name";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_AGE = "age";
    private static final String TAG_EYECOLOR = "eyeColor";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_BALANCE = "balance";
    private static final String TAG_IS_ACTIVE= "isActive";


    JSONArray employees = null;

    private List<Employee> employeeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EmployeeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getviewcasting();
        new GetData().execute();
        recyclerView = (RecyclerView) findViewById(R.id.employee_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearlayoutmanager);
        recyclerView.setHasFixedSize(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Employee is deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Employee details restored!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                snackbar.show();
            }

        });
    }
    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Resume");
    }
        private class GetData extends AsyncTask<Void, Void, Void> {
            private ProgressDialog pDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = new ProgressDialog(RecyclerActivity.this);
                pDialog.setMessage("Getting Data ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();

            }

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String json = sh.makeServiceCall(url);
                Log.e(TAG_EMPLOYEE, "Response from url: " + json);
                if (json != null) {
                    try {

                        employees = new JSONArray(json);
                        // Storing  JSON item in a Variable
                        for (int i = 0; i < employees.length(); i++) {
                            JSONObject jsonObject = employees.getJSONObject(i);

                            String name = jsonObject.getString(TAG_NAME);
                            String gender = jsonObject.getString(TAG_GENDER);
                            String age = jsonObject.getString(TAG_AGE);
                            String eye = jsonObject.getString(TAG_EYECOLOR);
                            String phone = jsonObject.getString(TAG_PHONE);
                            String email = jsonObject.getString(TAG_EMAIL);
                            String address = jsonObject.getString(TAG_ADDRESS);
                            String company = jsonObject.getString(TAG_COMPANY);
                            String balance = jsonObject.getString(TAG_BALANCE);
                            String isactive = jsonObject.getString(TAG_IS_ACTIVE);
                            Employee employee = new Employee(name, address, phone, gender, eye, age, email, company, balance, isactive);

                            employeeList.add(employee);
                        }

                    } catch (final JSONException e) {
                        Log.e(TAG_EMPLOYEE, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {
                    Log.e(TAG_EMPLOYEE, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();
                /**
                 * Updating parsed JSON data into ListView
                 * */

                mAdapter = new EmployeeAdapter(employeeList);
                recyclerView.setAdapter(mAdapter);
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.tab_fragment_pager) {
            Intent intent=new Intent(this,TabFragment.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.list) {
            Intent intent=new Intent(this,EmployeeListActivity.class);
            startActivity(intent);

        } else if (id == R.id.expandable_list) {
            Intent intent=new Intent(this,EmployeeExpandableActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_map) {
            Intent intent=new Intent(this,MapsActivity.class);
            startActivity(intent);
//        } else if (id == R.id.nav_xml_parsing) {
////            Intent intent=new Intent(this,XMLParsingActivity.class);
////            startActivity(intent);
//        } else if (id == R.id.nav_json_parsing) {
//            Intent intent = getIntent();
//            finish();
//            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

            @Override
            public void onBackPressed() {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                    // set title
                    alertDialogBuilder.setTitle("Employee Portal");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Do you wanna exit!")
                            .setCancelable(true)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    finish();
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            }


    public void getviewcasting(){
        fab= (FloatingActionButton) findViewById(R.id.fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
    }
}
