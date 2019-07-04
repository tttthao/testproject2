package utilities.selenium;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import utilities.reporting.TestAssertion;

public class WebActions extends PageGenerator {
	private WebDriver webDriver = DriverFactory.getDriver();
	private WebDriverWait defaultWait = DriverFactory.getWait(webDriver);

	/** Sleep and JavaScript wait methods **/
	protected void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	protected void executeJavaScript(String script) {

		try {
			((JavascriptExecutor) webDriver).executeScript(script);
			TestAssertion.info("[Executed] Javascript [" + script + "]");
		} catch (Exception ex) {
			TestAssertion.fail("Fail to execute Javascript!", ex);
		}
	}

	protected boolean waitJSReady() {
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver1) {
				return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver1) {
				try {
					return ((Long) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		return defaultWait.until(jsLoad) && defaultWait.until(jQueryLoad);
	}

	protected void waitPageReady() {
		waitJSReady();
	}

	/** JavaScript common methods **/
	protected void focusCurrentBrowser() {
		try {
			((JavascriptExecutor) webDriver).executeScript("window.focus();");
			sleep(1000);
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	protected void focusByJSUsingElement(WebElement element) {
		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].focus();", element);
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	protected void scrollIntoViewElement(WebElement element) {
		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	protected void scrollToTopOfPage() {
		try {
			((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	protected void scrollToBottomOfPage() {
		try {
			((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	protected void clickByJSUsingElement(WebElement element, String elementName) {
		try {
			waitElementVisible(element);
			waitElementClickable(element);
			focusByJSUsingElement(element);
			sleep(1000);
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);

			TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	protected void clickByJSUsingXpath(String eXpath, String elementName) {
		try {
			String actualScript = String.format(
					"document.evaluate(\"%s\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click()",
					eXpath);
			((JavascriptExecutor) webDriver).executeScript(actualScript);
			sleep(1000);
			TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	/** Wait methods **/
	protected boolean waitElementVisible(WebElement element) {
		try {
			defaultWait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (NoSuchElementException | TimeoutException ex) {
			return false;
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
			throw new AssertionError(ex.getMessage());
		}
	}

	protected boolean waitElementVisible(WebElement element, int expectedTimeout) {
		try {
			new WebDriverWait(webDriver, expectedTimeout).until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (NoSuchElementException | TimeoutException ex) {
			return false;
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
			throw new AssertionError(ex.getMessage());
		}
	}

	protected boolean waitElementVisible(By eby) {
		try {
			defaultWait.until(ExpectedConditions.visibilityOfElementLocated(eby));
			return true;
		} catch (NoSuchElementException | TimeoutException ex) {
			return false;
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
			throw new AssertionError(ex.getMessage());
		}
	}

	protected boolean waitElementVisible(By eby, int expectedTimeout) {
		try {
			new WebDriverWait(webDriver, expectedTimeout).until(ExpectedConditions.visibilityOfElementLocated(eby));
			return true;
		} catch (NoSuchElementException | TimeoutException ex) {
			return false;
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
			throw new AssertionError(ex.getMessage());
		}
	}

	protected boolean waitElementsVisible(List<WebElement> lstElements) {
		try {
			defaultWait.until(ExpectedConditions.visibilityOfAllElements(lstElements));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitElementsVisible(List<WebElement> lstElements, int expectedTimeout) {
		try {
			new WebDriverWait(webDriver, expectedTimeout)
					.until(ExpectedConditions.visibilityOfAllElements(lstElements));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitElementClickable(WebElement webElement) {
		try {
			defaultWait.until(ExpectedConditions.elementToBeClickable(webElement));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected boolean waitUrlContains(String url) {
		try {
			defaultWait.until(ExpectedConditions.urlContains(url));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitUntilElementDisplayed(WebElement element) {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		ExpectedCondition<Boolean> elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					element.isDisplayed();
					return true;
				} catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
					return false;
				}
			}
		};
		return wait.until(elementIsDisplayed);
	}

	/**
	 * @deprecated (This methods might not correct.) Will be updated later
	 */
	@Deprecated
	protected void waitUntilElementNotDisplayed(WebElement element) {
		WebDriverWait wait = new WebDriverWait(webDriver, 60);
		ExpectedCondition<Boolean> elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					element.isDisplayed();
					return false;
				} catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
					return true;
				}
			}
		};
		wait.until(elementIsDisplayed);
	}

	protected boolean fluentWaitClickable(WebElement element) {
		WebElement waitingElement = null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		waitingElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		return (waitingElement != null);
	}

	protected boolean fluentWaitVisible(WebElement element) {
		WebElement waitingElement = null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		waitingElement = wait.until(ExpectedConditions.visibilityOf(element));
		return (waitingElement != null);
	}

	protected boolean waitElementInvisible(By eBy) {
		try {
			defaultWait.until(ExpectedConditions.invisibilityOfElementLocated(eBy));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitElementInvisible(WebElement element) {
		try {
			defaultWait.until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitAttributeElementContains(WebElement element, String attribute, String value) {
		try {
			defaultWait.until(ExpectedConditions.attributeContains(element, attribute, value));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitAttributeElementContains(WebElement element, String attribute, String value, int timeout) {
		try {
			new WebDriverWait(webDriver, timeout)
					.until(ExpectedConditions.attributeContains(element, attribute, value));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitElementAttributeNotEmpty(WebElement element, String attribute) {
		try {
			defaultWait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitAttributeElementEquals(WebElement element, String attribute, String value) {
		try {
			defaultWait.until(ExpectedConditions.attributeToBe(element, attribute, value));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitElementTextEquals(WebElement element, String text) {
		try {
			defaultWait.until(ExpectedConditions.textToBePresentInElement(element, text));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitAllElementsContainsText(List<WebElement> lstElements, String expectedText) {
		boolean contains = true;
		try {
			if (waitElementsVisible(lstElements)) {
				for (WebElement element : lstElements) {
					String elementText = element.getText();
					if (!elementText.contains(expectedText))
						contains = false;
				}
			}
		} catch (Exception ex) {
			contains = false;
		}
		return contains;
	}

	protected boolean waitListTextContainAllElementsText(List<String> expectedListText, List<WebElement> lstElements) {
		boolean contains = true;
		try {
			if (waitElementsVisible(lstElements)) {
				for (WebElement element : lstElements) {
					String eText = element.getText();
					if (!expectedListText.contains(eText)) {
						contains = false;
					}
				}
			}
		} catch (Exception ex) {
			contains = false;
		}
		return contains;
	}

	protected boolean waitAllElementsTextContainListText(List<WebElement> lstElements, List<String> expectedListText) {
		boolean contains = true;
		try {
			List<String> lstActualText = new ArrayList<String>();
			if (waitElementsVisible(lstElements)) {
				for (WebElement element : lstElements)
					lstActualText.add(element.getText());
				for (String text : expectedListText)
					if (!lstActualText.contains(text))
						contains = false;
			}
		} catch (Exception ex) {
			contains = false;
		}
		return contains;
	}

	protected boolean waitFrameDisplayAndSwitchTo(WebElement iFrame) {
		try {
			defaultWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitAnyElementsEqualsText(List<WebElement> lstElements, String expectedText) {
		boolean equals = false;
		try {
			if (waitElementsVisible(lstElements)) {
				for (WebElement element : lstElements) {
					if (element.getText().equals(expectedText)) {
						equals = true;
					}
				}
			}
		} catch (Exception ex) {
			equals = false;
		}
		return equals;
	}

	protected boolean waitUrlNotEquals(String lastUrl) {
		try {
			defaultWait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(lastUrl)));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitUrlEquals(String expectedUrl) {
		try {
			defaultWait.until(ExpectedConditions.urlToBe(expectedUrl));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean waitNumberElementsEquals(List<WebElement> lstElements, int expectedNumber) {
		try {
			int actualNumber = lstElements.size();
			int currentSeconds = 0;
			while (actualNumber != expectedNumber && currentSeconds < 60) {
				actualNumber = lstElements.size();
				sleep(1000);
			}
			return (actualNumber == expectedNumber);

		} catch (Exception ex) {
			return false;
		}
	}

	protected WebElement findElementByParameter(By by) {
		try {
			waitElementVisible(by);
			return webDriver.findElement(by);
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
			return null;
		}
	}

	protected WebElement findElementByParameter(By by, int timeOut) {
		try {
			waitElementVisible(by, timeOut);
			return webDriver.findElement(by);
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
			return null;
		}
	}

	protected List<WebElement> findElementsByParameter(By by) {
		return webDriver.findElements(by);
	}

	/** Handle internal contents (iframe, alert) methods **/
	protected void switchToDefaultContent() {
		try {
			webDriver.switchTo().defaultContent();
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	protected void switchToIframe(WebElement element) {
		try {
			webDriver.switchTo().frame(element);
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	protected boolean isAlertPresent() {
		try {
			defaultWait.until(ExpectedConditions.alertIsPresent());
			webDriver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Step("Click Accept on displaying Alert")
	protected void checkAndAcceptAlert() {
		try {
			if (isAlertPresent()) {
				Alert alert = webDriver.switchTo().alert();
				alert.accept();
				webDriver.switchTo().defaultContent();
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Click Cancle on displaying Alert")
	protected void checkAndDismissAlert() {
		try {
			if (isAlertPresent()) {
				Alert alert = webDriver.switchTo().alert();
				alert.dismiss();
				webDriver.switchTo().defaultContent();
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Login on Authentication Alert with Username: {0}")
	protected void loginOnAuthenticationAlert(String username, String password) {
		try {
			if (isAlertPresent()) {
				Alert alert = webDriver.switchTo().alert();
				alert.sendKeys(username + Keys.TAB + password + Keys.TAB);
				sleep(1000);
				alert.accept();
				TestAssertion.pass("Logged in successfully with account [" + username + "]");
			} else {
				TestAssertion.info("Authentication Alert is not displayed! No log-in require.");
			}
		} catch (Exception ex) {
			TestAssertion.fail("Can not login on Authentication Alert!!!", ex);
		}
	}

	protected String checkAndGetAlertMessage() {
		String alertMessage = null;
		try {
			if (isAlertPresent()) {
				Alert alert = webDriver.switchTo().alert();
				alertMessage = alert.getText();
				alert.dismiss();
				webDriver.switchTo().defaultContent();
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
		return alertMessage;
	}

	/** Interact action (click, navigate, input, hover, focus) methods */
	@Step("Navigate to URL: {0}")
	protected void navigateToURL(String webUrl) {
		try {
			webDriver.navigate().to(webUrl);
			TestAssertion.info("Successfull navigated to url: [" + webUrl + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Navigate and wait until URL change. URL: {0}, Page Title: {1}")
	protected void accessAndWaitUrlChange(String webUrl, String pageTitle) {
		try {
			webDriver.navigate().to(webUrl);
			waitUrlNotEquals(webUrl);
			TestAssertion.info("Succesfully access to [" + pageTitle + "] with url [" + webUrl + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Navigate back to previous page.")
	protected void navigateBack() {
		try {
			webDriver.navigate().back();
			TestAssertion.info("Successfully Back to the previous page");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Get current Page Title of Website")
	protected String getCurrentPageTitle() {
		String pageTitle = null;
		try {
			waitPageReady();
			pageTitle = webDriver.getTitle();
			TestAssertion.info("Current Page Title is [" + pageTitle + "]");
			return pageTitle;
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
			return null;
		}
	}

	@Step("Get URL of current page of Website")
	protected String getCurrentPageUrl() {
		String currentURL = null;
		try {
			waitPageReady();
			currentURL = webDriver.getCurrentUrl();
			TestAssertion.info("Current URL is [" + currentURL + "]");
			return currentURL;
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
			return null;
		}
	}

	@Step("Hover on Element: {1}")
	protected void hoverElement(WebElement element, String elementName) {
		try {
			Actions action = new Actions(webDriver);
			action.moveToElement(element).build().perform();
			TestAssertion.info("Successfully Move and hover on [" + elementName + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Hover on Element: {1}, and Select Element: {3}")
	protected void hoverAndSelectSubMenuOption(WebElement parrentMenu, String parrentName, WebElement subMenu,
			String subMenuName) {
		try {
			Actions action = new Actions(webDriver);
			action.moveToElement(parrentMenu).click().build().perform();
			sleep(1000);
			TestAssertion.info("Successfully Move and hover on [" + parrentName + "]");

			action.moveToElement(subMenu).click().build().perform();
			TestAssertion.info("Successfully Hover on and Select [" + subMenuName + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Hover on Element: {1}, and Select Element: {3}")
	protected void hoverAndSelectSubMenuByClickAndHold(WebElement parrentMenu, String parrentName, WebElement subMenu,
			String subMenuName) {
		try {
			waitElementVisible(parrentMenu);
			Actions action = new Actions(webDriver);
			action.moveToElement(parrentMenu).clickAndHold().build().perform();
			TestAssertion.info("Successfully Move,click and hold on [" + parrentName + "]");
			sleep(1000);
			waitElementVisible(subMenu);
			action.moveToElement(subMenu).release(subMenu).click(subMenu).build().perform();
			TestAssertion.info("Successfully Move and hover on and click on [" + subMenuName + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Input into Text Field: {1}")
	protected void inputByAction(WebElement element, String elementName, String strInput) {
		try {
			waitElementVisible(element);
			Actions actions = new Actions(webDriver);
			actions.click(element);
			actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build().perform();
			actions.click(element).sendKeys(strInput).build().perform();
			TestAssertion.info("[Inputted] into [" + elementName + "] with value [" + strInput + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Click on: {1}")
	protected <T> void clickByAction(T elementAttr, String elementName) {
		try {
			WebElement element = null;
			if (elementAttr.getClass().getName().contains("By")) {
				element = webDriver.findElement((By) elementAttr);
			} else {
				element = (WebElement) elementAttr;
			}
			waitElementVisible(element);
			waitElementClickable(element);
			Actions actions = new Actions(webDriver);
			actions.click(element).build().perform();
			TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
		} catch (Exception ex) {
			TestAssertion.fail("Fail to click on element!", ex);
		}
	}

	@Step("Perform Right Mouse Click on: {1}")
	public void rightClickByAction(WebElement element, String elementName) {
		try {
			waitElementVisible(element);
			waitElementClickable(element);
			focusByJSUsingElement(element);
			Actions actions = new Actions(webDriver);
			actions.moveToElement(element).pause(Duration.ofSeconds(2, 1));
			actions.contextClick(element).build().perform();
			TestAssertion.info("Successfull Right Clicked on: [" + elementName + "]");
		} catch (Exception ex) {
			TestAssertion.fail("Fail to perform Right click on element!", ex);
		}
	}

	@Step("Input into Text Field: {1}, with Text: {2}")
	protected <T> void inputText(T elementAttr, String elementName, String strInput) {
		try {
			WebElement element = null;
			if (elementAttr.getClass().getName().contains("By")) {
				element = webDriver.findElement((By) elementAttr);
			} else {
				element = (WebElement) elementAttr;
			}
			waitElementVisible(element);

			element.click();
			element.clear();
			element.sendKeys(strInput);
			if (elementName.toLowerCase().contains("password")) {
				strInput = "********";
			}
			TestAssertion.info("[Inputted] into [" + elementName + "] with value [" + strInput + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Attach file at: {2}, into Field: {1}")
	protected void attachFile(WebElement element, String elementName, String filePath) {
		try {
			element.sendKeys(filePath);
			TestAssertion.info("[Attach File] into [" + elementName + "] with value [" + filePath + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Click on: {1}")
	protected <T> void click(T elementAttr, String elementName) {
		try {
			WebElement element = null;
			if (elementAttr.getClass().getName().contains("By")) {
				element = webDriver.findElement((By) elementAttr);
			} else {
				element = (WebElement) elementAttr;
			}
			waitElementVisible(element);
			waitElementClickable(element);

			focusByJSUsingElement(element);
			element.click();
			sleep(1000);
			TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
		} catch (Exception ex) {
			TestAssertion.fail("Fail to click on element!", ex);
		}
	}

	@Step("Click on: {1}, until URL is changed")
	protected void clickUntilURLChange(WebElement clickElement, String elementName, int tryTime) {
		try {
			int clickTime = 0;
			String currentURL = webDriver.getCurrentUrl();
			String newURL;
			do {
				fluentWaitVisible(clickElement);
				fluentWaitClickable(clickElement);
				focusByJSUsingElement(clickElement);
				clickElement.click();
				clickTime++;
				sleep(1000);
				newURL = webDriver.getCurrentUrl();
			} while (newURL.equals(currentURL) && clickTime < tryTime);
			if (clickTime == tryTime) {
				TestAssertion.fail("Fail on [Click] on [" + elementName + "]");
			} else {
				TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Click on: {1}, until Element is unselected")
	protected void clickUntilElementUnselected(WebElement element, String elementName, int timeout) {
		try {
			waitElementClickable(element);
			int currentSeconds = 0;
			do {
				element.click();
				sleep(1000);
				waitPageReady();
				currentSeconds++;
			} while (element.isSelected() && currentSeconds != timeout);

			if (currentSeconds == timeout) {
				TestAssertion.fail("Fail on [Click] on [" + elementName + "]");
			} else {
				TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Click on: {1}, until it contain Attribute: {2}")
	protected void clickUntilAttributeContains(WebElement element, String elementName, String attribute, String value,
			int timeout) {
		try {
			int currentSeconds = 0;
			do {
				waitElementVisible(element);
				waitElementClickable(element);
				element.click();
				sleep(1000);
				waitPageReady();
				currentSeconds++;
			} while (!waitAttributeElementContains(element, attribute, value, 1) && currentSeconds != timeout);

			if (currentSeconds == timeout) {
				TestAssertion.fail("Fail on [Click] on [" + elementName + "]");
			} else {
				TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Click on: {1}, until it is invisible")
	protected void clickUntilElementInvisible(WebElement element, String elementName, int timeout) {
		try {
			int currentSeconds = 0;
			do {
				waitElementVisible(element);
				waitElementClickable(element);
				element.click();
				sleep(2000);
				waitPageReady();
				currentSeconds++;
			} while (waitElementVisible(element, 1) && currentSeconds != timeout);

			if (currentSeconds == timeout) {
				TestAssertion.fail("Fail on [Click] on [" + elementName + "]");
			} else {
				TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Click on: {1}, until new element is displayed")
	protected void clickUntilNewElementVisible(WebElement element, String elementName, WebElement newElement,
			int timeout) {
		try {
			waitElementClickable(element);
			int currentSeconds = 0;
			do {
				element.click();
				sleep(1000);
				waitPageReady();
				currentSeconds++;
			} while (!waitElementVisible(newElement, 1) && currentSeconds != timeout);

			if (currentSeconds == timeout) {
				TestAssertion.fail("Fail on [Click] on [" + elementName + "]");
			} else {
				TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Click on: {1}, and wait URL is changed")
	protected void clickAndWaitUrlChange(WebElement element, String elementName) {
		try {
			String beforeUrl = webDriver.getCurrentUrl();
			waitElementClickable(element);
			focusByJSUsingElement(element);
			element.click();
			if (!waitUrlNotEquals(beforeUrl)) {
				TestAssertion.info("Successfull Clicked on: [" + elementName + "]");
			} else {
				TestAssertion.fail("Fail on [Click] on [" + elementName + "]");
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Send Key from Keyboard with Key: {0}")
	protected void actionSendKey(Keys key) {
		try {
			Actions actions = new Actions(webDriver);
			actions.sendKeys(key).build().perform();
		} catch (Exception ex) {
			TestAssertion.fail("Can not input keyboard with key [" + key.toString() + "]", ex);
		}
	}

	@Step("Send Key from Keyboard with String: {0}")
	protected void actionSendKeyByString(String keyString) {
		try {
			Actions actions = new Actions(webDriver);
			actions.sendKeys(keyString).build().perform();
		} catch (Exception ex) {
			TestAssertion.fail("Can not input keyboard with key [" + keyString + "]", ex);
		}
	}

	@Step("Drag element: {1}, to location of element: {3}")
	protected void dragAndDrop(WebElement sourceElement, String sourceElementName, WebElement targetElement,
			String targetElementName) {
		Actions action;
		try {
			action = new Actions(webDriver);
			scrollIntoViewElement(sourceElement);
			sleep(500);
			action.clickAndHold(sourceElement).release(targetElement).build().perform();
			sleep(1000);
			TestAssertion.info(
					"Successfully [Drag and Drop] the [" + sourceElementName + "] to [" + targetElementName + "]");

		} catch (Exception ex) {
			try {
				action = new Actions(webDriver);
				scrollIntoViewElement(sourceElement);
				sleep(500);
				action.clickAndHold(sourceElement).build().perform();
				scrollIntoViewElement(targetElement);
				sleep(500);
				action.release(targetElement).build().perform();
				sleep(1000);

				TestAssertion.info(
						"Successfully [Drag and Drop] the [" + sourceElementName + "] to [" + targetElementName + "]");
			} catch (Exception ex1) {
				TestAssertion.fail(ex1.getMessage(), ex1);
			}
		}
	}

	@Step("Select combobox: {1}, with value: {2}")
	protected void selectComboBoxByValue(WebElement eSelect, String elementName, String optionValue) {
		try {
			new Select(eSelect).selectByValue(optionValue);
			sleep(500);
			TestAssertion
					.info("Successfully [Selected] option from [" + elementName + "] with value [" + optionValue + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Select radio option: {1}")
	protected <T> void selectRadioOption(T elementAttr, String elementName) {
		try {
			WebElement element = null;
			if (elementAttr.getClass().getName().contains("By")) {
				element = webDriver.findElement((By) elementAttr);
			} else {
				element = (WebElement) elementAttr;
			}
			waitElementVisible(element);
			waitElementClickable(element);

			focusByJSUsingElement(element);

			if (element.isSelected()) {
				// do nothing as already selected
			} else {
				element.click();
			}
			TestAssertion.info("[Selected] Radio option [" + elementName + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Get text of Element")
	protected <T> String readText(T elementAttr) {
		String returnedText = null;
		try {
			WebElement element = null;
			if (elementAttr.getClass().getName().contains("By")) {
				element = webDriver.findElement((By) elementAttr);
			} else {
				element = (WebElement) elementAttr;
			}
			waitElementVisible(element);
			focusByJSUsingElement(element);
			returnedText = element.getText();
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
		return returnedText;
	}

	@Step("Refresh current browser")
	protected void refreshBrowser() {
		try {
			webDriver.navigate().refresh();
			TestAssertion.info("Refreshed current Page");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	protected int getIndexByTextEqualy(List<WebElement> listElement, String textDefinition) {
		int index = 0;
		boolean indexFound = false;
		for (int i = 0; i < listElement.size(); i++) {
			if (listElement.get(i).getText().equalsIgnoreCase(textDefinition)) {
				index = i;
				indexFound = true;
				break;
			}
		}
		if (indexFound) {
			return index;
		} else {
			TestAssertion.fail("Element with text [" + textDefinition + "] is not found on page", null);
			return -1;
		}
	}

	protected int getIndexByContainText(List<WebElement> listElement, String textDefinition) {
		int index = 0;
		boolean indexFound = false;
		for (int i = 0; i < listElement.size(); i++) {
			if (listElement.get(i).getText().contains(textDefinition)) {
				index = i;
				indexFound = true;
				break;
			}
		}
		if (indexFound) {
			return index;
		} else {
			TestAssertion.fail("Element with text [" + textDefinition + "] is not found on page", null);
			return -1;
		}
	}

	protected WebElement getElementInListByContainText(List<WebElement> listElement, String textDefinition) {
		WebElement webElementFound = null;
		for (WebElement webElement : listElement) {
			if (webElement.getText().contains(textDefinition))
				webElementFound = webElement;
		}
		if (webElementFound == null) {
			TestAssertion.fail("Element with text [" + textDefinition + "] is not found on page", null);
			return null;
		} else {
			return webElementFound;
		}
	}

	protected String getElementAttributeValue(WebElement element, String attributelementName) {
		String attributeValue = null;
		try {
			attributeValue = element.getAttribute(attributelementName);
		} catch (Exception ex) {
			return null;
		}
		return attributeValue;
	}

	/** Windows Handling methods **/
	protected int getNumberOfWindows() {
		return webDriver.getWindowHandles().size();
	}

	protected String getCurrentListWindow() {
		try {
			return webDriver.getWindowHandle();
		} catch (Exception ex) {
			TestAssertion.fail("Unable to get current openning browser Window", ex);
		}
		return null;
	}

	@Step("Switch to New browser tab")
	protected void switchToNewWindow(int expectedNumberOfWindows, int tabNumber) {
		WebDriverWait wait = new WebDriverWait(webDriver, 60);
		wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
		try {
			ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
			webDriver.switchTo().window(tabs.get(tabNumber));
		} catch (Exception ex) {
			TestAssertion.fail("Unable to switch to new opened browser Window-Tab", ex);
		}
	}

	@Step("Back to previous Browser tab")
	protected void backToMainPage(String startWindow) {
		try {
			webDriver.close();
			webDriver.switchTo().window(startWindow);
		} catch (Exception ex) {
			TestAssertion.fail("Unable to switch to Started browser Window-Tab", ex);
		}
	}

	/** Common Verify methods **/
	@Step("Verify element: {1} is displayed on Page")
	protected void verifyElementPresent(WebElement element, String elementName) {
		try {
			defaultWait.until(ExpectedConditions.visibilityOf(element));
			TestAssertion.pass("[" + elementName + "] is displayed on page correctly");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Verify element: {1} is not displayed on Page")
	protected void verifyElementNotPresent(By by, String elementName) {
		try {
			waitElementVisible(by, 5);
			webDriver.findElement(by);
			TestAssertion.fail("Element [" + elementName + "] is displayed on page", null);

		} catch (NoSuchElementException noElementEx) {
			TestAssertion.pass("[" + elementName + "] is not displayed on page");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Verify text is displayed as expected: {2}")
	protected void verifyText(WebElement element, String expectedText, String actual, String message) {
		try {
			waitElementVisible(element);
			TestAssertion.info(message);
			if (actual.equalsIgnoreCase(expectedText)) {
				TestAssertion
						.pass("Text is displayed correctly. Expected: [" + expectedText + "], found [" + actual + "]");
			} else {
				TestAssertion.fail("Text is not displayed correctly. Expected: [" + expectedText + "], but found ["
						+ actual + "]");
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Verify All messages in List are same as expectedText")
	protected void verifyTextInList(List<WebElement> listElement, String expectedText) {
		int index = 0;
		boolean matched = true;
		for (int i = 0; i < listElement.size(); i++) {
			if (!listElement.get(i).getText().equalsIgnoreCase(expectedText)) {
				index = i;
				matched = false;
				break;
			}
		}
		if (!matched) {
			TestAssertion.fail("Element with text [" + expectedText + "] is not match with row [" + index
					+ "] with found text [" + listElement.get(index).getText() + "]", null);
		} else {

			TestAssertion.pass("All records in list are matched with [" + expectedText + "]");
		}
	}

	@Step("Verify text is displayed as expected in List")
	protected void verifyListContainText(List<WebElement> listElement, String expectedText) {
		boolean matched = false;
		for (int i = 0; i < listElement.size(); i++) {
			if (listElement.get(i).getText().equalsIgnoreCase(expectedText)) {
				matched = true;
				break;
			}
		}
		if (!matched) {
			TestAssertion.fail("[" + expectedText + "] is not match with any rows in list", null);
		} else {
			TestAssertion.pass("Record found. Text is displayed correctly as [" + expectedText + "]");
		}
	}

	@Step("Verify All messages in List are same as Expected List")
	protected void verifyEqualityTextInList(List<WebElement> listElement, String[] expectedTextList, String message) {
		try {
			TestAssertion.info(message);
			waitElementsVisible(listElement);
			int totalItems = listElement.size();
			String[] actualTextList = new String[totalItems];
			for (int i = 0; i < totalItems; i++) {
				actualTextList[i] = listElement.get(i).getText();
			}
			if (Arrays.equals(actualTextList, expectedTextList)) {
				TestAssertion.pass("All records in list are matched with expected list texts");
			} else {
				TestAssertion.fail("Two array lists are not matched!", null);
			}
		} catch (Exception ex) {
			TestAssertion.fail("Fail to compare two arrays!", ex);
		}
	}

	@Step("Verify displaying message are contains Expected text")
	protected void verifyTextWildCard(WebElement element, String expectedText, String actual, String message) {
		try {
			waitElementVisible(element);
			TestAssertion.info("Verify Wildcard text for: " + message);
			if (actual.contains(expectedText) || expectedText.contains(actual)) {
				TestAssertion
						.pass("Text is displayed correctly. Expected: [" + expectedText + "], found [" + actual + "]");
			} else {
				TestAssertion.fail("Text is not displayed correctly. Expected: [" + expectedText + "], but found ["
						+ actual + ".*]", null);
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Verify All messages in List are contains Expected Text")
	protected void verifyListTextWildCard(List<WebElement> listElement, String expectedText, String actual,
			String message) {
		try {
			int index = 0;
			boolean matched = true;
			waitElementsVisible(listElement);
			TestAssertion.info("Verify Wildcard text for: " + message);
			for (int i = 0; i < listElement.size(); i++) {
				if (!listElement.get(i).getText().contains(expectedText)) {
					index = i;
					matched = false;
					break;
				}
			}
			if (!matched) {
				TestAssertion.fail("[" + expectedText + "] is not match with row [" + index + "] with found text ["
						+ listElement.get(index).getText() + "]", null);
			} else {
				TestAssertion.pass("All records in list are matched with [" + expectedText + "]");
			}
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage(), ex);
		}
	}

	@Step("Verify value for Attribute: {2}, of Element: {1}")
	protected <T> void compareAttribute(T elementType, String elementName, String attributelementName,
			String expectedAttributeValue) {
		try {
			WebElement element = null;
			if (elementType.getClass().getName().contains("By")) {
				element = webDriver.findElement((By) elementType);
			} else {
				element = (WebElement) elementType;
			}
			String actualAttributeValue = element.getAttribute(attributelementName);
			if (actualAttributeValue.trim().equalsIgnoreCase(expectedAttributeValue.trim())) {
				TestAssertion.pass(String.format("Attribute [%s] of %s display as expected: %s", attributelementName,
						elementName, expectedAttributeValue));

			} else {
				TestAssertion.fail(String.format("Attribute [%s] of %s does not display as expected: %s. Actual: %s",
						attributelementName, elementName, expectedAttributeValue, actualAttributeValue), null);
			}
		} catch (Exception ex) {
			TestAssertion.fail("Failed to compare attribute.", ex);
		}
	}

	@Step("Verify value for CSS: {2}, of Element: {1}")
	protected <T> void compareCssValue(T elementType, String elementName, String cssName, String expectedCssValue) {
		try {
			WebElement element = null;
			if (elementType.getClass().getName().contains("By")) {
				element = webDriver.findElement((By) elementType);
			} else {
				element = (WebElement) elementType;
			}
			String actualCssValue = element.getCssValue(cssName);
			if (actualCssValue.trim().equalsIgnoreCase(expectedCssValue.trim())) {
				TestAssertion.pass(String.format("Css [%s] of %s display as expected: %s", cssName, elementName,
						expectedCssValue));
			} else {
				TestAssertion.fail(String.format("Css [%s] of %s does not display as expected: %s. Actual: %s", cssName,
						elementName, expectedCssValue, actualCssValue), null);
			}
		} catch (Exception ex) {
			TestAssertion.fail("Failed to compare attribute.", ex);
		}
	}

	/** Action methods perform by Robot **/
	@Step("Input text by using Robot into: {1}")
	protected void inputUsingRobot(WebElement element, String elementName, String strInputText) {
		try {
			focusCurrentBrowser();
			Robot robot = new Robot();
			sleep(1000);
			for (char c : strInputText.toCharArray()) {
				int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
				if (KeyEvent.CHAR_UNDEFINED == keyCode) {
					TestAssertion.fail("Key code not found for character '" + c + "'");
				}
				robot.keyPress(keyCode);
				robot.delay(100);
				robot.keyRelease(keyCode);
				robot.delay(100);
			}
			TestAssertion.info("[Input] into " + elementName + " with value [" + strInputText + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage());
		}
	}

	@Step("Move mouse pointer to coordinates [{0},{1}] using Robot")
	protected void moveMouseByRobot(int x, int y) {
		try {
			Robot robot = new Robot();
			robot.mouseMove(x, y);
			robot.mouseRelease(0);
			sleep(500);
			TestAssertion.info("Moved mouse pointer to coordinates [" + x + "," + y + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage());
		}
	}

	@Step("Pressed Key using Robot")
	protected void pressKeyByRobot(int keyEvent) {
		try {
			Robot robot = new Robot();
			robot.delay(300);
			robot.keyPress(keyEvent);
			TestAssertion.info("Pressed Key [" + KeyEvent.getKeyText(keyEvent) + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage());
		}
	}

	@Step("Release Key Pressed By Robot")
	protected void releaseKeyPressedByRobot(int keyEvent) {
		try {
			Robot robot = new Robot();
			robot.delay(300);
			robot.keyRelease(keyEvent);
			TestAssertion.info("Release Key Pressed [" + KeyEvent.getKeyText(keyEvent) + "]");
		} catch (Exception ex) {
			TestAssertion.fail(ex.getMessage());
		}
	}
}
