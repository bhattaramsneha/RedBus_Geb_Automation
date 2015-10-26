package page

import geb.Page
import module.homPageModule

/**
 * Created by snehab on 15-10-2015.
 */
class homePage extends Page{

    static at = {title.contains("redBus : Online Bus Ticket Booking, Book Volvo AC Bus Tickets, Reservation")
        println "Into home page!"}

    static content = {
        BasePageModule{module(homPageModule)}
    } //calling the locator

    def clickOnSignInLink(){
        waitFor{BasePageModule.signInLink.displayed}
        BasePageModule.signInLink.click()
    }
}
