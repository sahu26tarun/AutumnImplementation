package systemTesting.TestAPI;

import com.dt.hgw.apiRequestBuilder.getUsers.GetUsers;
import com.dt.hgw.global.Environments;
import com.dt.hgw.global.Groups;
import com.dt.hgw.global.TestCategory;
import com.dt.hgw.validators.CommonValidation;
import com.dt.hgw.validators.apiStatus.GetUserStatus;
import com.dt.hgwqa.autumn.executors.annotations.SkipCondition;
import com.dt.hgwqa.autumn.reporting.annotations.TestCategorizer;
import com.dt.hgwqa.autumn.reporting.assertions.CustomAssert;
import org.testng.annotations.Test;
import systemTesting.basePackage.Setup;

@SkipCondition(SkipConditions = {Environments.PROD,Environments.PREPROD})
public class Excute_GetUsers extends Setup {

    @TestCategorizer(Category = TestCategory.Execute_GetUsers)
    @Test(description = "Get user data", groups = {Groups.SANITY, Groups.SMOKE, Groups.REGRESSION})
    public void TC001_GetUserData() {
        String pageNumber = "2";
        GetUsers getUsers = new GetUsers(pageNumber);
        getUsers.createRequestJsonAndExecute();
        CommonValidation.getInstance().basicAsserts(getUsers, GetUserStatus.UserGetParams.SUCCESS_200);
        CustomAssert.assertEquals(getUsers.getResponsePojo().getPage().toString(), pageNumber, "");
    }
}
