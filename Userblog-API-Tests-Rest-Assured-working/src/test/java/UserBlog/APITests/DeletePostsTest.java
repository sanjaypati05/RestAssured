package UserBlog.APITests;

import dto.PostsPojo;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureResultsWriter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;

import org.testng.annotations.Test;
import common.AssertHelper;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Epic("Order Placing")
@Feature("Search")
public class DeletePostsTest extends BaseTest {

	@Test(description = "Verify Create Posts requests are successful")
	@Severity(CRITICAL)
	@Description("Verify Create Posts requests are successful")
	@Story("As a user I should be able to send POST request to Create Posts in the user blog")
	public void verifyCreatePosts() {
		Allure.step("Start of Test - Verify create posts requests");
		PostsPojo newPost = new PostsPojo(3, 1, "Test Title", "Test Body");
		AssertHelper.assertTrue(posts.createANewPostForAUserId(withReqSpec, newPost) == 201,
				"Adding the given post was Unsuccessful");
		Allure.step("End of Test - Verify create posts requests");
	}
	
	
}