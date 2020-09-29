package src.main.java.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAccueil extends ElementsCommuns {
	
	@FindBy(xpath = "//section[@class=\"products-grid\"]//a[@class=\"addToCart\"][1]")
	public WebElement btn_addToCart_1;
	
	
}
