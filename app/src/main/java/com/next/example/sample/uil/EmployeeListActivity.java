package com.next.example.sample.uil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.next.example.sample.R;
import com.next.example.sample.bal.Bean;
import com.next.example.sample.dal.DataAccessLayer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class EmployeeListActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<Bean> employeeList;
    private ListViewAdapter mAdapter;
    private JSONObject sync_emp_jsonObj;
    private String mJSON_Str_for_Sync = "";
    private ProgressDialog progress;
    Runnable progressRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Employee Listview");
// check if you are connected or not
       if (isConnected()) {
            Toast.makeText(EmployeeListActivity.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
            try {
                syncDataToServer();
                System.out.println("Json String is length----=>" + mJSON_Str_for_Sync.length());
                new Http_Upload_Sync().execute("http://hmkcode.appspot.com/jsonservlet");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this,"You are not connected to Internet!! Please Check your Connection Settings!!!",Toast.LENGTH_SHORT).show();
        }
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listview);
        getDatabaseValue();

    }

    private class Http_Upload_Sync extends AsyncTask<String, Void, String> {

        //        StringCustomProgressDialog mCusDialog;
        @Override
        protected String doInBackground(String... urls) {
            progress.setMessage("Transferring Data..");
            System.out.println("Json String is length----=>" + mJSON_Str_for_Sync.length());
            return POST(urls[0],mJSON_Str_for_Sync);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            mCusDialog = new CustomProgressDialog( DashBoardActivity.this );
//            mCusDialog.show( DashBoardActivity.this, "Loading", "", true,
//                    false, null );

            progress = new ProgressDialog(EmployeeListActivity.this);
            progress.setMessage("Fetching Data..");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setCanceledOnTouchOutside(false);
            progress.setProgress(0);
            //   progress.setCancelable( true );
            progress.show();

            progressRunnable = new Runnable() {

                @Override
                public void run() {
                    progress.cancel();
                }
            };

            progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    progress.cancel();
                }
            });

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            updateSyncStatusData();
            // Dismiss the progress dialog
            if (progress.isShowing())
                progress.dismiss();
            System.out.println("Updated Table Sync Status");
        }
    }
    public void getDatabaseValue(){
    DataAccessLayer dal=new DataAccessLayer(this);
    dal.open();
    Cursor mCr_employee=dal.fetch(dal.TRN_TABLE_EMPLOYEE);
    employeeList = new ArrayList<Bean>();
    for (int i = 0; i < mCr_employee.getCount(); i++) {
        mCr_employee.moveToPosition(i);
        String fname = "First Name: "+mCr_employee.getString(mCr_employee.getColumnIndex("" + dal.TRN_EMPLOYEE_FIRST_NAME + ""));
        String lname = "Last Name: "+mCr_employee.getString(mCr_employee.getColumnIndex("" + dal.TRN_EMPLOYEE_LAST_NAME + ""));
        String team = "Team: "+mCr_employee.getString(mCr_employee.getColumnIndex("" + dal.TRN_EMPLOYEE_TEAM + ""));
        String uname ="Username: "+ mCr_employee.getString(mCr_employee.getColumnIndex("" + dal.TRN_EMPLOYEE_USER_NAME + ""));
        Bean bean = new Bean(fname,lname,team,uname);
        employeeList.add(bean);

    }
    mAdapter = new ListViewAdapter(this, employeeList);
    // Assign adapter to ListView
    listView.setAdapter(mAdapter);

//    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view,
//                                int position, long id) {
//
//            // ListView Clicked item index
//            int itemPosition     = position;
//
//            // ListView Clicked item value
//            String  itemValue    = (String) listView.getItemAtPosition(position);
//
//            // Show Alert
//            Toast.makeText(getApplicationContext(), "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//                    .show();
//
//        }
//
//    });
    dal.close();
}
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    public static String POST(String url, String Json){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(Json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
    public void syncDataToServer() throws JSONException {

        sync_emp_jsonObj = new JSONObject();

        DataAccessLayer dal = new DataAccessLayer(this);
        dal.open();
        Cursor mCr_reg = dal.columnIdFetch(dal.TRN_TABLE_EMPLOYEE, dal.TRN_EMPLOYEE_SYNC_STATUS, "0");
        System.out.println("Cursor Count" + mCr_reg.getCount());
        try {
            if (mCr_reg.getCount() > 0) {

                for (int i = 0; i < mCr_reg.getCount(); i++) {
                    mCr_reg.moveToPosition(i);


                    String str_employeeName = mCr_reg.getString(mCr_reg.getColumnIndex("" + dal.TRN_EMPLOYEE_FIRST_NAME + ""));
                    sync_emp_jsonObj.put("name", str_employeeName != null ? str_employeeName : null);

                    String str_employeeTeam = mCr_reg.getString(mCr_reg.getColumnIndex("" + DataAccessLayer.TRN_EMPLOYEE_TEAM + ""));
                    sync_emp_jsonObj.put("country", str_employeeTeam != null ? str_employeeTeam : null);

                    String str_empPass = mCr_reg.getString(mCr_reg.getColumnIndex("" + dal.TRN_EMPLOYEE_PASSWORD + ""));
                    sync_emp_jsonObj.put("twitter", str_empPass != null ? str_empPass : null);
                }
                mJSON_Str_for_Sync = sync_emp_jsonObj.toString();
                Log.i("SyncStatus", mJSON_Str_for_Sync);

            } else {
                Toast.makeText(this,"No data to Sync to Server",Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {

        } finally {
            mCr_reg.close();
            dal.close();
        }

    }
    public void updateSyncStatusData() {
    DataAccessLayer dal = new DataAccessLayer(this);
        dal.open();
        Cursor mCr_log = dal.columnIdFetch(dal.TRN_TABLE_EMPLOYEE,dal.TRN_EMPLOYEE_SYNC_STATUS,"0");
        if (mCr_log.getCount() > 0) {
            for (int i = 0; i < mCr_log.getCount(); i++) {
                mCr_log.moveToPosition(i);
                String mLogId = mCr_log.getString(mCr_log.getColumnIndex("" + dal.TRN_EMPLOYEE_ID + ""));
                dal.updateSyncStatus(dal.TRN_TABLE_EMPLOYEE,dal.TRN_EMPLOYEE_ID,mLogId);
            }
        }
        mCr_log.close();
        dal.close();
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}