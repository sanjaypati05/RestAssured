package UserBlog.APITests;

import dto.PostsPojo;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;

import org.testng.annotations.Test;
import common.AssertHelper;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Epic("Order Placing")
@Feature("Update Posts")
public class UpdatePostsTest extends BaseTest {

	@Test(description = "Verify Update Posts requests are successful")
	@Severity(CRITICAL)
	@Description("Verify Update Posts requests are successful")
	@Story("As a user I should be able to send PUT request to update Posts in the user blog")
	public void verifyCreatePosts() {
		Allure.step("Start of Test - Verify Update posts requests");
		PostsPojo newPost = new PostsPojo(3, 1, "Test Title Updated", "Test Body updated");
		AssertHelper.assertEquals(posts.updateAPostForAUserid(withReqSpec, newPost), 200,
				"Updating the given post was Unsuccessful");
		Allure.step("Verifying the updated details of post having id:" + newPost.getId() + "\n"
				+ posts.getResponseOfAllThePostsForGivenUserId(withReqSpec, withResSpec, newPost.getUserId())
						.getResponseBody());
		Allure.step("End of Test - Verify Update posts requests");
	}
}