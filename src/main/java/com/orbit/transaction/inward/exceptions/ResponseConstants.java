package com.orbit.transaction.inward.exceptions;

public class ResponseConstants {
    public static final String SUCCEESS_CODE = "00";
    public static final String SUCCEESS_MESSAGE = "Successful";
    //insufficient funds
    public static final String INSUFFICIENT_CODE = "01";
    public static final String INSUFFICIENT_MESSAGE = "Insufficient Funds";

    // sybase
    public static final String SYBASE_DATABASE = "SYBASE";
    public static final String SYBASE_DRIVER = "com.sybase.jdbc3.jdbc.SybDriver";
    public static final String SYBASE_CONNECTION_URL_PREFIX = "jdbc:sybase:Tds:";
}
