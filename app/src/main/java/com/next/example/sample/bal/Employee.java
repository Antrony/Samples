package com.next.example.sample.bal;

import android.widget.TextView;

/**
 * Created by next on 30/9/16.
 */
public class Employee {
    String employeeName;
    String employeeAddress;
    String employeePhone;
    String employeeGender;
    String employeeEye;
    String employeeAge;
    String employeeEmail;
    String employeeCompany;
    String employeeBalance;
    String employeeIsActive;
    public Employee(String employeeName,String employeeAddress,String employeePhone,String employeeGender,String employeeEye,String employeeAge,
                     String employeeEmail,String employeeCompany,String employeeBalance,String employeeIsActive){
        this.employeeName=employeeName;
        this.employeeAddress=employeeAddress;
        this.employeePhone=employeePhone;
        this.employeeGender=employeeGender;
        this.employeeEye=employeeEye;
        this.employeeAge=employeeAge;
        this.employeeEmail=employeeEmail;
        this.employeeCompany=employeeCompany;
        this.employeeBalance=employeeBalance;
        this.employeeIsActive=employeeIsActive;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeEye() {
        return employeeEye;
    }

    public void setEmployeeEye(String employeeEye) {
        this.employeeEye = employeeEye;
    }

    public String getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeCompany() {
        return employeeCompany;
    }

    public void setEmployeeCompany(String employeeCompany) {
        this.employeeCompany = employeeCompany;
    }

    public String getEmployeeBalance() {
        return employeeBalance;
    }

    public void setEmployeeBalance(String employeeBalance) {
        this.employeeBalance = employeeBalance;
    }
    public String getEmployeeIsActive() {
        return employeeIsActive;
    }
    public void setEmployeeIsActive(String employeeIsActive) {
        this.employeeIsActive = employeeIsActive;
    }
}
