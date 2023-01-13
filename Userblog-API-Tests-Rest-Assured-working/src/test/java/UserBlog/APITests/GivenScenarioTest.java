package UserBlog.APITests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.assertTrue;

@Epic("User Blog API tests")
@Feature("Given data search feature")
public class GivenScenarioTest extends BaseTest {

	private int userId = 0;
	private List<Integer> idsForAllPosts = new ArrayList<Integer>();

	@DataProvider(name = "username")
	public Object[][] dataProviderMethod() {
		return new Object[][] { { "Samantha" }, { "Bret" } };
	}

	@Test(description = "Get Users Data")
	@Severity(CRITICAL)
	@Description("Get Users Data")
	@Story("As a user I should be able to Get users data from the user blog")
	public void getUsersData() {
		users.getUsers(withReqSpec).getUsersIsExecutedSuccessfully(withResSpec);
	}

	@Test(dependsOnMethods = "getUsersData", dataProvider = "username")
	public void searchForGivenUser(String username) {
		userId = users.searchForGivenUserAndFetchUserId(username);

		//assertTrue(userId > 0, "Given user does not exist in the system");
		assertTrue(userId = 0, "Given user does not exist in the system");
	}

	@Test(dependsOnMethods = "searchForGivenUser")
	public void getAllPostsForGivenUser() {
		idsForAllPosts = posts.getResponseOfAllThePostsForGivenUserId(withReqSpec, withResSpec, userId)
				.extractIdsForThesePosts();
		assertTrue(idsForAllPosts.size() > 0, "No Records returned for given post id");
	}

	@Test(dependsOnMethods = "getAllPostsForGivenUser")
	public void getCommentsForGivenPostsAndVerifyEmailFormat() {
		List<String> emailIds = new ArrayList<String>();
		// Used the below regex assuming there is no specific validation on characters
		// allowed for email format.
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);

		for (int postId : idsForAllPosts) {
			emailIds.addAll(comments.getResponseOfAllTheCommentsForGivenPost(withReqSpec, withResSpec, postId)
					.extractEmailIdFromResponseAndVerifyFormat());
		}

		for (String email : emailIds) {
			Matcher matcher = pattern.matcher(email);
			assertTrue(matcher.matches(), "The given email Id = " + email + " is not valid");
		}
	}
}
