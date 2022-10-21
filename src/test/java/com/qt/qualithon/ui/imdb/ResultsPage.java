package com.qt.qualithon.ui.imdb;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;
import com.qt.qualithon.model.Movie;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * represents IMDb web search results page elements and actions (page object)
 **/
public class ResultsPage extends Page{

    public ResultsPage(TestSession testSession){
        super(testSession);
            }

    /**
     * get a list of result link elements
     * 
     * @return    list of movie link web elements from results page
     **/
    public List<WebElement> movieResultLinks(){
    	
    	
		JavascriptExecutor js =(JavascriptExecutor) this.testSession.driver();

 
    	for(int i=0;i<=3;i++){
    	
    			if(!this.testSession.driver().findElement(By.cssSelector(".findHeader")).isDisplayed()) {//.findHeader
    				// stop loading
    				js.executeScript("window.stop();");
    				this.testSession.driverWait();
    				//for refresh
    				this.testSession.driver().navigate().refresh();// refresh
    				// seconds wait
    				this.testSession.driverWait();

    			}
    			else {

    				break;
    			}
    			
    			if(i==3) {
    				System.out.println("Can't able to get the page");
    			}
    	}
    	// your expected page should be present
        List<WebElement> resultLinks = this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(".findList .findResult a")
            )
        );
        return resultLinks;
    	}
//    	else{//section[data-testid="find-results-section-title"]
//    		 
//
//    		    		List<WebElement> resultLinks = this.testSession.driverWait().until(
//                ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                    By.cssSelector("li[class=\"ipc-metadata-list-summary-item ipc-metadata-list-summary-item--click find-result-item find-title-result\"]​")
//                )
//            );
//            return resultLinks;
//   		      }

        	
    

    /**
     * open first movie result link from result page and return movie page page object
     *
     * @return    Movie Page imdb page object
     **/
    public MoviePage firstMovieResult(){
        this.movieResultLinks().get(0).click();
        return new MoviePage(this.testSession);
    }
}
