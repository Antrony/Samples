package com.next.example.sample.uil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.next.example.sample.R;

/**
 * Created by next on 28/9/16.
 */
public class TabFragments1 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_grid,
                    container, false);
            Button btn_1=(Button)view.findViewById(R.id.btn_first);
            btn_1.setOnClickListener(new View.OnClickListener() {
//
                @Override
                public void onClick(View arg0) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

                    // set title
                    alertDialogBuilder.setTitle("Next Techno Solutions");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Click yes to exit!")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    getActivity().finish();
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
            });
            Button btn_2=(Button)view.findViewById(R.id.btn_second);
            btn_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"Welcome",Toast.LENGTH_SHORT).show();
                } });
            return view;

            }
    }

