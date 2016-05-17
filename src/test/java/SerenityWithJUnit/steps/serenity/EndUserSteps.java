package SerenityWithJUnit.steps.serenity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

import SerenityWithJUnit.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class EndUserSteps extends ScenarioSteps {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4505248056090526610L;
	DictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
    	dictionaryPage.addKeyword(keyword);
    }

    @Step
    public void startSearch() {
    	dictionaryPage.searchKeyword();
    }

    @Step
    public void shouldSeeDefinition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }
    
    @Step
    public void shouldSeeSuggestion(String sugestion) {
        assertThat(dictionaryPage.getSuggestions(), hasItem(containsString(sugestion)));
    }

    @Step
    public void openPage() {
        dictionaryPage.open();
    }
    
    @Step
    public void clickMultimedia() {
        dictionaryPage.selectMultimedia();
    }
    
    @Step
    public void clickSuggestion() {
        dictionaryPage.selectSuggestion();
    }

    @Step
    public void looksFor(String term) {
        enters(term);
        startSearch();
    }
}