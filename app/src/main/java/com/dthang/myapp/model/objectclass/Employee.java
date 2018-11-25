package com.dthang.myapp.model.objectclass;

public class Employee {
    private int EmployeeID,EmployeeTypeID;
    private String EmployeeName,EmployeeUserName,EmployeePassword,EmployeeAddress,EmployeeBirthday,EmployeeNumber,EmployeeGioiTinh,
            EmployeeCMND,EmployeeSendEmail;


    public Employee(int employeeID, int employeeTypeID, String employeeName, String employeeUserName, String employeePassword, String employeeSendEmail) {
        EmployeeID = employeeID;
        EmployeeTypeID = employeeTypeID;
        EmployeeName = employeeName;
        EmployeeUserName = employeeUserName;
        EmployeePassword = employeePassword;
        EmployeeSendEmail = employeeSendEmail;
    }

    public Employee(int employeeTypeID, String employeeName, String employeeUserName, String employeePassword, String employeeSendEmail) {
        EmployeeTypeID = employeeTypeID;
        EmployeeName = employeeName;
        EmployeeUserName = employeeUserName;
        EmployeePassword = employeePassword;
        EmployeeSendEmail = employeeSendEmail;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public int getEmployeeTypeID() {
        return EmployeeTypeID;
    }

    public void setEmployeeTypeID(int employeeTypeID) {
        EmployeeTypeID = employeeTypeID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getEmployeeUserName() {
        return EmployeeUserName;
    }

    public void setEmployeeUserName(String employeeUserName) {
        EmployeeUserName = employeeUserName;
    }

    public String getEmployeePassword() {
        return EmployeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        EmployeePassword = employeePassword;
    }

    public String getEmployeeAddress() {
        return EmployeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        EmployeeAddress = employeeAddress;
    }

    public String getEmployeeBirthday() {
        return EmployeeBirthday;
    }

    public void setEmployeeBirthday(String employeeBirthday) {
        EmployeeBirthday = employeeBirthday;
    }

    public String getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        EmployeeNumber = employeeNumber;
    }

    public String getEmployeeGioiTinh() {
        return EmployeeGioiTinh;
    }

    public void setEmployeeGioiTinh(String employeeGioiTinh) {
        EmployeeGioiTinh = employeeGioiTinh;
    }

    public String getEmployeeCMND() {
        return EmployeeCMND;
    }

    public void setEmployeeCMND(String employeeCMND) {
        EmployeeCMND = employeeCMND;
    }

    public String getEmployeeSendEmail() {
        return EmployeeSendEmail;
    }

    public void setEmployeeSendEmail(String employeeSendEmail) {
        EmployeeSendEmail = employeeSendEmail;
    }
}
