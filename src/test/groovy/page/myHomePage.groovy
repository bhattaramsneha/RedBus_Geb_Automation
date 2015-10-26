package page

import geb.Page
import module.myHomePageModule

/**
 * Created by snehab on 16-10-2015.
 */
class myHomePage extends Page{

    static content = {
        MyHomePageModule{module(myHomePageModule)}
    } //calling the locator

    static at = {
        waitFor{MyHomePageModule.myAccount.displayed}
        MyHomePageModule.myAccount.displayed
        }

    def enterJourneyDetails(String fromLocation, String toLocation){
        MyHomePageModule.fromLocation.value(fromLocation)
        MyHomePageModule.toLocation.value(toLocation)
        MyHomePageModule.calendar.click()
        MyHomePageModule.date.click()
        MyHomePageModule.searchBuses.click()
    }
}
