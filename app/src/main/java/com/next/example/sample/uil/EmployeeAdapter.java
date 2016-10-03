package com.next.example.sample.uil;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.next.example.sample.R;
import com.next.example.sample.bal.Employee;

import java.util.List;


/**
 * Created by next on 31/8/16.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder  {

        CardView cardView;
        TextView employeeName,employeeAddress,employeePhone,employeeGender,employeeEye,employeeAge,employeeEmail,employeeCompany,employeeBalance,employeeIsActive;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardview);
            cardView.setUseCompatPadding(true);

//handling clicks on the cards
//to know which card was clicked, we will be sending the ID value of that particular record to the activity which is being called
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            employeeName = (TextView)itemView.findViewById(R.id.name);
            employeeGender = (TextView)itemView.findViewById(R.id.gender);
//            employeePhoto = (ImageView)itemView.findViewById(R.id.employee_photo);
            employeeEye = (TextView)itemView.findViewById(R.id.eyecolor);
            employeeAge = (TextView)itemView.findViewById(R.id.age);
            employeeEmail = (TextView)itemView.findViewById(R.id.email);
            employeePhone = (TextView)itemView.findViewById(R.id.phone);
            employeeAddress = (TextView)itemView.findViewById(R.id.address);
            employeeCompany = (TextView)itemView.findViewById(R.id.company);
            employeeBalance = (TextView)itemView.findViewById(R.id.balance);
            employeeIsActive = (TextView)itemView.findViewById(R.id.isactive);
        }

    }

    List<Employee> employees;

    EmployeeAdapter(List<Employee> employees){

        this.employees = employees;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_details, viewGroup, false);
        EmployeeViewHolder pvh = new EmployeeViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(EmployeeViewHolder employeeViewHolder, int i) {
        employeeViewHolder.employeeName.setText(employees.get(i).getEmployeeName());
        employeeViewHolder.employeeGender.setText(employees.get(i).getEmployeeGender());
        employeeViewHolder.employeeEye.setText(employees.get(i).getEmployeeEye());
        employeeViewHolder.employeeAge.setText(employees.get(i).getEmployeeAge());
        employeeViewHolder.employeeEmail.setText(employees.get(i).getEmployeeEmail());
        employeeViewHolder.employeeCompany.setText(employees.get(i).getEmployeeCompany());
        employeeViewHolder.employeeAddress.setText(employees.get(i).getEmployeeAddress());
        employeeViewHolder.employeePhone.setText(employees.get(i).getEmployeePhone());
        employeeViewHolder.employeeBalance.setText(employees.get(i).getEmployeeBalance());
        employeeViewHolder.employeeIsActive.setText(employees.get(i).getEmployeeIsActive());
//        employeeViewHolder.employeePhoto.setImageResource(employees.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

}
