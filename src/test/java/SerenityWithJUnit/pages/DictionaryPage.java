package SerenityWithJUnit.pages;

import static ch.lambdaj.Lambda.convert;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ch.lambdaj.function.convert.Converter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://en.wikipedia.org/")
public class DictionaryPage extends PageObject {

	@FindBy(id = "searchInput")
	private WebElementFacade searchTerm;

	@FindBy(id = "searchButton")
	private WebElementFacade searchButton;
	
	@FindBy(className = "normal")
	private WebElementFacade multimediaButton;
	
	@FindBy(id = "mw-search-DYM-suggestion")
	private WebElementFacade suggestionButton;

	public void addKeyword(String keyword) {
		searchTerm.type(keyword);
	}

	public void searchKeyword() {
		searchButton.click();
	}

	public void selectMultimedia() {
		multimediaButton.click();
	}
	
	public void selectSuggestion(){
		suggestionButton.click();
	}

	public List<String> getDefinitions() {
		WebElementFacade definitionList = find(By.id("mw-content-text"));
		List<WebElement> results = definitionList.findElements(By.tagName("p"));
		return convert(results, toStrings());
	}

	public List<String> getSuggestions() {
		WebElementFacade suggestionList = find(By.id("mw-content-text"));
		List<WebElement> results = suggestionList.findElements(By.tagName("div"));
		return convert(results, toStrings());
	}

	private Converter<WebElement, String> toStrings() {
		return new Converter<WebElement, String>() {
			public String convert(WebElement from) {
				return from.getText();
			}
		};
	}
}