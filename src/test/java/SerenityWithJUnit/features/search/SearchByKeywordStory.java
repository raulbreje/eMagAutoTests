package SerenityWithJUnit.features.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import SerenityWithJUnit.steps.serenity.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class SearchByKeywordStory {

	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@Steps
	public EndUserSteps endUser;

	@Test
	public void searching_by_keyword_valid() {
		endUser.openPage();
		endUser.looksFor("mechanics");
		endUser.shouldSeeDefinition(
				"Mechanics (Greek μηχανική) is an area of science concerned with the behavior of physical bodies when subjected to");
	}

	@Test
	public void searching_by_keyword_invalid() {
		endUser.openPage();
		endUser.looksFor("mecanics");
		endUser.shouldSeeSuggestion("Did you mean: mechanics");
	}

	@Test
	public void filter_laptop_valid() {
		endUser.openPage();
		endUser.looksFor("mecanics");
		endUser.clickMultimedia();
		endUser.shouldSeeSuggestion("The page \"Mecanics\" does not exist.");
	}

	@Test
	public void filter_laptop_invalid() {
		endUser.openPage();
		endUser.looksFor("mecanics");
		endUser.clickMultimedia();
		endUser.shouldSeeSuggestion("The page \"Mecanics\" does not exist.");
		endUser.clickSuggestion();
		endUser.shouldSeeSuggestion("There is a page named \"Mechanics\" on Wikipedia");

	}

}