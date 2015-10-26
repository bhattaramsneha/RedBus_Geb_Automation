package module

import geb.Module
import org.openqa.selenium.By

/**
 * Created by snehab on 16-10-2015.
 */
class myHomePageModule extends Module {
    static content = {
        myAccount { $("#accountLink", text: "My Account") }

        //myAccount{$(By.xpath(".//a[contains(text(),'My Account')]"))}

        fromLocation{$("#txtSource")}

        toLocation{$("#txtDestination")}

        calendar{$("#txtOnwardCalendar")}

        date{$("#rbcal_txtOnwardCalendar .current.day")}

        searchBuses{$("#searchBtn")}
    }
}

