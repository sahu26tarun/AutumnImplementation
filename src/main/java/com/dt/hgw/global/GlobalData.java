package com.dt.hgw.global;

public class GlobalData {

    //Extent Report
    public static final String FILE_NAME_REPORT = "AutomationReport";
    public static final String FILE_NAME_REPORT_PERF = "PerfReport";
    public static final String OUTPUT_FOLDER_REPORT = "reports/Automation";
    public static final String REPORT_TITLE = "API Automation";

    //Email Report
    public static final String SENDER_EMAIL_ID = "";
    public static final String SENDER_EMAIL_PASSWORD = "";
    public static final String SMTP_HOST = "";
    public static final String SMTP_PORT = "";
    public static final String SENDMAILTOGROUP= System.getProperty("SendEmailToGroup","");
    public static final String EMAIL_REPORT_TITLE="API Automation";
    public static final String SERVER_USERNAME = "";
    public static final String SERVER_PASSWORD = "";


    //Jenkins Server Details
    public static final String REPORT_FOLDER_NAME = "AUTOMATION_REPORT";
    public static final String JENKINS_REPORT_LOC = "/var/lib/jenkins/automation-reports/";
    public static final String REPORT_SERVER_IP = "";


    //CSV Data generation default Directory
    public static final String CSV_DATA_OUTPUT_DIRECTORY = System.getProperty("user.dir")+"/outputTestFiles/";

    //Home Data Manager
    public static final int WAIT_TIME_MILLISECONDS = 1000;
    public static final int WAIT_TIME_COUNTER = 300;
    public static final int X_RETRY_INTERVAL = 500;
    public static final int X_RETRY_COUNT = 120;
    public static final int X_RETRY_LARGE_INTERVAL = 1000;
    public static final int X_RULE_RETRY_INTERVAL = 6000;
    public static final int X_RULE_RETRY_COUNT = 10;
    public static final int X_DEVICE_RETRY_INTERVAL = 6000;
    public static final int X_DEVICE_RETRY_COUNT = 10;
    public static final int X_RETRY_COUNT_SMALL = 5;

    //Zephyr Details
    public static final String JIRA_URL="";
    public static final String JIRA_USER_NAME="";
    public static final String JIRA_PASSWORD="";
    public static final String JIRA_PROJECT_NAME="";
    public static final String JIRA_RELEASE_NAME_PREFIX="";
    public static final String JIRA_TEST_REPO_RELEASE_NAME="v1";
    public static final String JIRA_TEST_REPO_CYCLE_NAME="HGW_API_Cases";
    public static final String JIRA_NEW_CYCLE_NAME_PREFIX="Automation_";
    public static final String ZEPHYR_TC_MAPPING_COLLECTION = "";

    //Encryption Details
    public static final String DB_ENCRYPTION_ALGO="";
    public static final String DB_ENCRYPTION_PASSWORD="";
    public static final String TRANSIT_ENCRYPTION_ALGO="";

    //Consent Group
    public static final String []MANDATORY_CONSENT_GROUP =new String[]{"hgwServiceActivationGroup"};
    public static final String []OPTIONAL_CONSENT_GROUP =new String[]{"hgwGDPRGroup"};
    public static final String AUTO_CONSENT_GROUP = "Auto_hgwGDPRGroup";
    public static final String AUTO_CONSENT_CODE = "testNewAssignment";
    public static final String AUTO_CONSENT_LOCALE = "en";

    //klov reporter details
    public static final String KLOV_SERVER_URL="";
    public static final String KLOV_MONGO_IP="";
    public static final int KLOV_MONGO_PORT=123;

    //crash upload portal
    public static final String CRASH_UPLOAD_FILE="";
}
