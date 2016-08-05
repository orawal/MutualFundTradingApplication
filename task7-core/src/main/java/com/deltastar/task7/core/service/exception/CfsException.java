package com.deltastar.task7.core.service.exception;

import java.util.ResourceBundle;

/**
 * CCConstants class for views names.
 * <p>
 * Delta Star Team
 */
public class CfsException extends Exception {


    public static final int CODE_INSUFFICIENT_BALANCE = 1;
    public static final int CODE_INVALID_FUND_NAME = 2;
    public static final int CODE_INSUFFICIENT_SHARES = 3;
    public static final int CODE_INVALID_CUSTOMER = 4;
    public static final int CODE_INVALID_SYMBOL = 5;
    public static final int CODE_INVALID_CUSTOMER_ID = 6;
    public static final int CODE_INVALID_FUND_ID = 7;
    public static final int CODE_INVALID_KEYWORDS = 8;
    public static final int CODE_INVALID_AMOUNT = 9;
    public static final int CODE_INVALID_INPUT_DATA = 10;
    public static final int CODE_EXISTED_FUND_NAME = 11;
    public static final int CODE_EXISTED_SYMBOL = 12;
    public static final int CODE_INVALID_DATE = 13;
    public static final int CODE_INVALID_EMPLOYEE = 14;
    public static final int CODE_INVALID_EMPLOYEE_USERNAME = 15;
    public static final int CODE_INVALID_EMPLOYEE_PASSWORD = 16;
    public static final int CODE_INVALID_EMPLOYEE_FIRST_NAME = 17;
    public static final int CODE_INVALID_EMPLOYEE_LAST_NAME = 18;
    public static final int CODE_INVALID_EXECUTION_DATE = 19;
    public static final int CODE_INVALID_CUSTOMER_PASSWORD = 20;
    public static final int CODE_INVALID_CUSTOMER_FIRST_NAME = 21;
    public static final int CODE_INVALID_CUSTOMER_LAST_NAME = 22;
    public static final int CODE_INVALID_CUSTOMER_ADDRESS_LINE1 = 23;
    public static final int CODE_INVALID_CUSTOMER_CITY = 24;
    public static final int CODE_INVALID_CUSTOMER_STATE = 25;
    public static final int CODE_INVALID_CUSTOMER_ZIPCODE = 26;
    public static final int CODE_INVALID_CUSTOMER_USERNAME = 27;
    public static final int CODE_MAX_DEPOSITION = 28;
    public static final int CODE_INVALID_EMPLOYEE_UPDATE_CUSTOMER_PASSWORD = 29;



    private int code;

    public CfsException(int code) {
        super(getMessageByErrorCode(code));
        this.code = code;

    }

    public CfsException(int code, String message) {
        super(message);
        this.code = code;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CfsException(String message) {
        super(message);
    }

    public static String getMessageByErrorCode(int errorCode) {
        switch (errorCode) {
            case CODE_INSUFFICIENT_BALANCE:
                return ResourceBundle.getBundle("cfs").getString("insufficient.balance");
            case CODE_INVALID_FUND_NAME:
                return ResourceBundle.getBundle("cfs").getString("invalid.fund.name");
            case CODE_INSUFFICIENT_SHARES:
                return ResourceBundle.getBundle("cfs").getString("insufficient.shares");
            case CODE_INVALID_SYMBOL:
                return ResourceBundle.getBundle("cfs").getString("invalid.symbol");
            case CODE_INVALID_CUSTOMER_ID:
                return ResourceBundle.getBundle("cfs").getString("invalid.customerId");
            case CODE_INVALID_FUND_ID:
                return ResourceBundle.getBundle("cfs").getString("invalid.fundId");
            case CODE_INVALID_KEYWORDS:
                return ResourceBundle.getBundle("cfs").getString("invalid.keyWords");
            case CODE_INVALID_AMOUNT:
                return ResourceBundle.getBundle("cfs").getString("invalid.amount");
            case CODE_INVALID_INPUT_DATA:
                return ResourceBundle.getBundle("cfs").getString("invalid.inputData");
            case CODE_EXISTED_FUND_NAME:
                return ResourceBundle.getBundle("cfs").getString("existed.fund.name");
            case CODE_EXISTED_SYMBOL:
                return ResourceBundle.getBundle("cfs").getString("existed.symbol");
            case CODE_INVALID_DATE:
                return ResourceBundle.getBundle("cfs").getString("invalid.date");
            case CODE_INVALID_EMPLOYEE:
                return ResourceBundle.getBundle("cfs").getString("employee.existed.username");
            case CODE_INVALID_EMPLOYEE_USERNAME:
                return ResourceBundle.getBundle("cfs").getString("employee.invalid.username");
            case CODE_INVALID_EMPLOYEE_PASSWORD:
                return ResourceBundle.getBundle("cfs").getString("employee.invalid.password");
            case CODE_INVALID_EMPLOYEE_FIRST_NAME:
                return ResourceBundle.getBundle("cfs").getString("employee.invalid.first.name");
            case CODE_INVALID_EMPLOYEE_LAST_NAME:
                return ResourceBundle.getBundle("cfs").getString("employee.invalid.last.name");
            case CODE_INVALID_EXECUTION_DATE:
                return ResourceBundle.getBundle("cfs").getString("invalid.execution.date");
            case CODE_INVALID_CUSTOMER:
                return ResourceBundle.getBundle("cfs").getString("customer.existed.username");
            case CODE_INVALID_CUSTOMER_USERNAME:
                return ResourceBundle.getBundle("cfs").getString("customer.invalid.username");
            case CODE_INVALID_CUSTOMER_PASSWORD:
                return ResourceBundle.getBundle("cfs").getString("customer.invalid.password");
            case CODE_INVALID_CUSTOMER_FIRST_NAME:
                return ResourceBundle.getBundle("cfs").getString("customer.invalid.first.name");
            case CODE_INVALID_CUSTOMER_LAST_NAME:
                return ResourceBundle.getBundle("cfs").getString("customer.invalid.last.name");
            case CODE_INVALID_CUSTOMER_ADDRESS_LINE1:
                return ResourceBundle.getBundle("cfs").getString("customer.invalid.address.line1");
            case CODE_INVALID_CUSTOMER_CITY:
                return ResourceBundle.getBundle("cfs").getString("customer.invalid.city");
            case CODE_INVALID_CUSTOMER_STATE:
                return ResourceBundle.getBundle("cfs").getString("customer.invalid.state");
            case CODE_INVALID_CUSTOMER_ZIPCODE:
                return ResourceBundle.getBundle("cfs").getString("customer.invalid.zipcode");
            case CODE_MAX_DEPOSITION:
                return ResourceBundle.getBundle("cfs").getString("max.deposition");
            case CODE_INVALID_EMPLOYEE_UPDATE_CUSTOMER_PASSWORD:
                return ResourceBundle.getBundle("cfs").getString("employee.change.password.invalid");
            default:
                return null;
        }
    }

}
