package common;

import org.testng.Assert;

import io.qameta.allure.Allure;

public class AssertHelper extends Assert {

	static String[] oldCharacters = new String[] { "", "", "", "", "" };
	static String[] newChars;

	public static void assertTrue(boolean condition, String message) {
		if (!condition) {
			Assert.assertTrue(condition, message);
		} else {
			Allure.step(message.replace(" should be ", " is ").replace(" has to be", " is ").replace("was Unsuccessful",
					"was Successful"));

		}
	}

	public static void assertEquals(Object expected, Object actual, String message) {
		if (expected != actual) {
			Assert.assertEquals(actual, expected);
		} else {

			Allure.step(replacePassScenarioStatement(message));
		}
	}

	private static String replacePassScenarioStatement(String message) {
		return message.replace(" should be ", " is ").replace(" has to be", " is ").replace("was Unsuccessful",
				"was Successful");
	}
}