import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SSLExample {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        DesiredCapabilities capability = DesiredCapabilities.chrome ();
        // To Accept SSL certificate
        capability.setCapability ( CapabilityType.ACCEPT_SSL_CERTS, true );
        // create Google Chrome instance and maximize it
        driver = new ChromeDriver ( capability );
        driver.manage ().window ().maximize ();
    }

    @Test
    public void openApplication() throws InterruptedException {
        System.out.println ( "Navigating application" );
        driver.get ( "https://cacert.org/" );
        Thread.sleep ( 5000 );
        //TC001
        WebElement headingEle = driver.findElement ( By.cssSelector ( ".story h3" ) );
        // Validate heading after accepting untrusted connection
        String expectedHeading = "Are you new to CAcert?";
        Assert.assertEquals ( headingEle.getText (), expectedHeading );
        //TC002
        WebElement element = driver.findElement ( By.xpath ( "//*[@id='content']/div/h3[2]" ) );
        String strng = element.getText ();
        System.out.println ( strng );
        Assert.assertEquals ( "For CAcert Community Members", strng );
        //TC003
        driver.findElement ( By.cssSelector ( "#pageLogo > a > img" ) );
        //TC004
        driver.findElement ( By.className ( "sponsorinfo" ) );
    }

        @AfterClass
        public void tearDown () {
            if (driver != null)
                driver.quit ();
        }
    }
