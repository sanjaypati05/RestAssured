package blog.modules.posts;

import dto.PostsPojo;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Posts {

	private Response response;

	/**
	 * @param withReqSpec
	 * @param withResSpec
	 * @param userId
	 * @return : returns all posts response
	 */
	public Posts getResponseOfAllThePostsForGivenUserId(RequestSpecification withReqSpec,
			ResponseSpecification withResSpec, int userId) {
		response = given().spec(withReqSpec).when().queryParam("userId", userId).get("/posts").then().spec(withResSpec)
				.extract().response();
		return this;
	}

	/**
	 * @return : list of ids of Posts
	 */
	public List<Integer> extractIdsForThesePosts() {
		return response.jsonPath().getList("id");
	}

	/**
	 * @return : responseBody as String
	 */
	public String getResponseBody() {
		return response.getBody().asString();
	}

	public int createANewPostForAUserId(RequestSpecification withReqSpec, PostsPojo post) {
		int statuscode;
		Response res = given().spec(withReqSpec).accept("application/json, text/plain, */*").body(post).when()
				.post("/posts").then().extract().response();
		statuscode = res.getStatusCode();
		// Allure.step("Create new post response => "+ res.asString());
		Allure.step("Status code returned = " + statuscode);
		return res.getStatusCode();
	}

	public int updateAPostForAUserid(RequestSpecification withReqSpec, PostsPojo post) {
		int statuscode;
		Response res = given().spec(withReqSpec).accept("application/json, text/plain, */*").body(post).when()
				.put("/posts/" + post.getId()).then().extract().response();
		Allure.step("Response body of update request of post for a user ID: \n" + res.body().asString());
		System.out.println("Response body of update request of post for a user ID: \n" + res.body().asString());
		statuscode = res.getStatusCode();
		Allure.step("Status code returned = " + statuscode);
		return res.getStatusCode();
	}
}
