package io.invariant.data.relay.constants;

public class DbConstants {

    public static final String SPACE_4 = "    ";
    public static final int NUM_BUCKETS = 32;
    public static final String MONEY_PRECISION = " (19, 4)";

    // ep db commands
    public static final String DB_INSERT = "ISRT";
    public static final String DB_UPDATE = "REPL";
    public static final String DB_DELETE = "DLET";

    // cdc db commands
    public static final String CDC_DB_INSERT = "RR";
    public static final String CDC_DB_UPDATE = "UP";
    public static final String CDC_DB_DELETE = "PL";

    // When data is sqooped
    public static final String DB_SQOOP = "SQOOP";

    // column names
    public static final String CDC_AUDIT_TIMESTAMP = "TRANS_TIME";
    public static final String CDC_AUDIT_OPERATION = "TRANS_TYPE";
    public static final String CDC_AUDIT_USER = "TRANS_USER";

    public static final String EP_AUDIT_TIMESTAMP = "IBMSNAP_LOGMARKER";
    public static final String EP_AUDIT_OPERATION = "IBMSNAP_OPERATION";

    public static final String INTERNAL_OPERATION = "DBACTION";
    public static final String INTERNAL_OFFSET = "PPL_OFFSET";
    public static final String INTERNAL_TIMESTAMP = "INVRECTIME";

    // merge timestamp
    public static final String INV_UPDTD_DTM = "INV_UPDTD_DTM";

    // Note - TEXT, NTEXT, IMAGE are deprecated as of SQL Server 2005
    // NVARCHAR should be used for storing JSON
    // DB2 has XML type

    // target data types
    public static final String SMALLINT_TYPE = "SMALLINT";
    public static final String BIGINT_TYPE = "BIGINT";
    public static final String INTEGER_TYPE = "INTEGER";
    public static final String INT_TYPE = "INT";
    public static final String TINYINT_TYPE = "TINYINT";
    public static final String CHAR_TYPE = "CHAR";
    public static final String NCHAR_TYPE = "NCHAR";
    public static final String FLOAT_TYPE = "FLOAT";
    public static final String REAL_TYPE = "REAL";
    public static final String DATE_TYPE = "DATE";
    public static final String DECIMAL_TYPE = "DECIMAL";
    public static final String MONEY_TYPE = "MONEY";
    public static final String SMALLMONEY_TYPE = "SMALLMONEY";

    public static final String STRING_TYPE = "STRING";
    public static final String TIMESTAMP_TYPE = "TIMESTAMP";
    public static final String VARCHAR_TYPE = "VARCHAR";
    public static final String NVARCHAR_TYPE = "NVARCHAR";
    public static final String TEXT_TYPE = "TEXT";
    public static final String NTEXT_TYPE = "NTEXT";
    public static final String XML_TYPE = "XML";
    // MS SQL
    public static final String DATETIME_TYPE = "DATETIME";
    public static final String DATETIME2_TYPE = "DATETIME2";
    public static final String SMALLDATETIME_TYPE = "SMALLDATETIME";
    public static final String DATETIMEOFFSET_TYPE = "DATETIMEOFFSET";
    public static final String TIME_TYPE = "TIME";
    // Postgres
    public static final String BOOL_TYPE = "BOOL";
    public static final String SERIAL_TYPE = "SERIAL";
    public static final String INT4_TYPE = "INT4";

}
