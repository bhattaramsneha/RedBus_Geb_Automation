/**
 * Created by snehab on 15-10-2015.
 */

import org.openqa.selenium.firefox.FirefoxDriver

waiting {
    timeout = 20
}

reportsDir = "target/geb-reports"

driver = { def driverInstance =  new FirefoxDriver()

    driverInstance.manage().window().maximize()

    driverInstance}

baseUrl = "https://www.redbus.in/"