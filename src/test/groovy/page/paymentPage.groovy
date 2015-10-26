package page

import geb.Page
import module.paymentPageModule

import java.text.SimpleDateFormat

/**
 * Created by snehab on 20-10-2015.
 */
class paymentPage extends Page {
    static content = {
        PaymentPageModule { module(paymentPageModule) }
    } //calling the locator

    static at = {
        waitFor{PaymentPageModule.verifyText.displayed}
        PaymentPageModule.verifyText.displayed
    }

    def validateTravelCity(String travelCity)
    {
        waitFor{PaymentPageModule.travelCity.displayed}

        if(travelCity == PaymentPageModule.travelCity.text()) {
            println "travel locations is verified"
            return true
        }
        else{
            println "Incorrect  travel locations"
            return false}
    }

    def validateTravelAgent(String travelAgent)
    {
        waitFor{PaymentPageModule.travelAgent.displayed}
        if(travelAgent == PaymentPageModule.travelAgent.text()){
            println "travel agent is verified"
            return true
        }
        else{
            println "incorrect travel agent"
            return false
        }
    }
    def validateDate()
    {
        def date = new Date()
        def sdf = new SimpleDateFormat("EEE, MMM dd, YYYY")
        waitFor{PaymentPageModule.dateOfJourney.displayed}
        if(sdf.format(date) == PaymentPageModule.dateOfJourney.text()){
            println "Date is verified"
            return true
        }
        else{
            println "invalid date"
            return false
        }
    }

    def validateBoardingPoint(String boardingPoint)
    {
        waitFor{PaymentPageModule.boardingPoints.displayed}
        String sboardingPoint = boardingPoint.substring(0,boardingPoint.length()-2)
       if(sboardingPoint == PaymentPageModule.boardingPoints.text()){
           println "boarding point is verified"
           return true
       }
       else{
           println "incorrect boardingpoint"
           return false
       }

    }

    def validaSeatNumber(String seatNumber)
    {
        waitFor{PaymentPageModule.seatNumber.displayed}
        String seatNo= PaymentPageModule.seatNumber.text()
        if(seatNumber.contains(seatNo.substring(13,seatNo.length()))){

            println "Seat number  is verified"
            return true
        }
        else{
            println "incorrect Seat number"
            return false
        }
    }

    def validateFare(String Fare)
    {
        waitFor{PaymentPageModule.fareCharged.displayed}
        String onwardFare = PaymentPageModule.onwardfareCharged.text()
        println onwardFare
        if(Fare.contains(onwardFare)){

            println "Fare  is verified"
            return true
          }
        else{
            println "incorrect Fare"
            return false
        }

    }
}
