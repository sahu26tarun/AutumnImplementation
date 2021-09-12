package systemTesting.basePackage;


import com.dt.hgw.dataManager.TestDataManager;
import com.dt.hgw.global.Constants;
import com.dt.hgw.global.LocalConfig;
import com.dt.hgwqa.autumn.executors.generics.ExecutorGenericFunctions;
import com.dt.hgwqa.autumn.executors.listeners.SkipAnalyserListener;
import com.dt.hgwqa.autumn.jira.generics.JiraGenericFunctions;
import com.dt.hgwqa.autumn.jira.listeners.JiraTestngListener;
import com.dt.hgwqa.autumn.reporting.extentReport.Logger;
import com.dt.hgwqa.autumn.reporting.generics.ReportingGenericFunctions;
import com.dt.hgwqa.autumn.reporting.listeners.ReportingTestngListener;
import com.dt.hgwqa.autumn.utils.commonUtil.DateTimeModifierUtil;
import com.dt.hgwqa.autumn.utils.databaseUtils.RedisUtil;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.dt.hgw.global.GlobalData.*;

@Listeners({ReportingTestngListener.class, JiraTestngListener.class, SkipAnalyserListener.class})
public class Setup {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws IOException {
        setServerReportDetails();
        initExtentReporter();
        initLog4j();
        initKlovReport();
        initZephyrReporter();
        addExecutionDetails_ExtentReport();
        setSkipCondition();
        ReportingGenericFunctions.initRetryListener(0);
        Logger.logInfoInLogger("******************** Start :- Execution Started ********************");
    }

    /**
     *
     * @param method
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        if (TestDataManager.getInstance().getMethodNameThread() == null) {
            TestDataManager.getInstance().setMethodNameThread(method.getName());
        }
    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        Logger.logInfoInLogger("******************** End :- Execution Completed ********************");
        initAPIPerfReporter();
        initEmailReport();
        initMicrosoftTeamsNotification();
        RedisUtil.getInstance().closeAllConnections();
    }


    public synchronized void setServerReportDetails() {
        if (Constants.JENKINSRUN) {
            ReportingGenericFunctions.setReportLoc(REPORT_FOLDER_NAME, JENKINS_REPORT_LOC, REPORT_SERVER_IP);
        }
    }

    public synchronized void initExtentReporter() {
        ReportingGenericFunctions.initExtentReporter(Constants.FLAG_REMOVE_RETRIEDTESTS, Constants.FLAG_UPDATE_SCREENSHOTS, FILE_NAME_REPORT, REPORT_TITLE);
    }

    public synchronized void initLog4j(){
        ReportingGenericFunctions.initLogger("loggerFile");
    }

    public synchronized void initZephyrReporter() {
        try {
            if (Constants.FLAG_UPDATE_ZEPHYR) {
                JiraGenericFunctions.setJiraDetails(JIRA_URL, JIRA_USER_NAME, JIRA_PASSWORD, JIRA_PROJECT_NAME);
                JiraGenericFunctions.setMongoZephyrTCDBDetails(LocalConfig.HGW_QA_MONGODB, ZEPHYR_TC_MAPPING_COLLECTION);
                String JIRA_RELEASE_NAME = "v1";
                String JIRA_NEW_CYCLE_NAME = JIRA_NEW_CYCLE_NAME_PREFIX + DateTimeModifierUtil.getInstance().getCurrentDateTime("yyyy-MM-dd");
                JiraGenericFunctions.initZephyrReporter(JIRA_RELEASE_NAME, JIRA_TEST_REPO_RELEASE_NAME, JIRA_TEST_REPO_CYCLE_NAME, JIRA_NEW_CYCLE_NAME);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized void setSkipCondition() {
        ExecutorGenericFunctions.setSkipCondition(LocalConfig.ENVIRONMENT + "_" + LocalConfig.TENANT);
    }


    public synchronized void initEmailReport() {
        if (Constants.JENKINSRUN) {
            ReportingGenericFunctions.setSMTPDetails_Email(SMTP_HOST, SMTP_PORT, SENDER_EMAIL_ID, SENDER_EMAIL_PASSWORD, SENDMAILTOGROUP);
            ReportingGenericFunctions.initEmailReport(REPORT_TITLE, LocalConfig.ENVIRONMENT, EMAIL_REPORT_TITLE, SERVER_USERNAME, SERVER_PASSWORD);
        }
    }

    public synchronized void initKlovReport() {
        if (Constants.JENKINSRUN) {
            ReportingGenericFunctions.initKlovReporter(REPORT_TITLE + " - " + LocalConfig.ENVIRONMENT, KLOV_SERVER_URL, KLOV_MONGO_IP, KLOV_MONGO_PORT);
        }
    }

    //To enabled with autumn version 0.0.7
    public synchronized void initMicrosoftTeamsNotification() {
        if (Constants.JENKINSRUN) {
            ReportingGenericFunctions.initMicrosoftTeamsNotification(LocalConfig.MICROSOFT_TEAMS_URI, LocalConfig.ENVIRONMENT, Constants.TESTTYPE.toUpperCase() + " Test Results");
        }
    }

    public synchronized void initAPIPerfReporter() {
        if (Constants.JENKINSRUN) {
            ReportingGenericFunctions.initAPIPerfReporter(FILE_NAME_REPORT_PERF, REPORT_TITLE);
        }
    }

    public synchronized void addExecutionDetails_ExtentReport() {
        Map<String, String> addSystemInfo = new LinkedHashMap<>();
        addSystemInfo.put("Environment", LocalConfig.ENVIRONMENT);
        addSystemInfo.put("ExecutionType", Constants.SUITETYPE);
        if (!Constants.SUITETYPE.equalsIgnoreCase("System"))
            addSystemInfo.put("Groups", Constants.TESTTYPE);
      //  addSystemInfo.put("Tennant", Constants.TENNANT);
        ReportingGenericFunctions.addExecutionDetails(addSystemInfo);
    }


}