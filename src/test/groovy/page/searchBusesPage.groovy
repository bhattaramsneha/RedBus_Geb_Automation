package page

import com.thoughtworks.selenium.Wait
import geb.Page
import module.searchBusesModule

import javax.crypto.SealedObject
import java.text.SimpleDateFormat
import java.util.List
import geb.waiting.WaitTimeoutException

/**
 * Created by snehab on 16-10-2015.
 */
class searchBusesPage extends Page {
    def seatType=0;
    static at = {title.contains("Book Ticket - Search Buses")}

    static content = {
        SearchBusesModule{module(searchBusesModule)}
    } //calling the locator

    def date = new Date()
    def sdf = new SimpleDateFormat("dd-MMM-yyyy")

    def verifyBusesPage(String fromText, String toText) {
        if((SearchBusesModule.fromText.text().contains(fromText)) &&
                (SearchBusesModule.toText.text().contains(toText)) &&
                (SearchBusesModule.onwardDate.text().contains(sdf.format(date))))
        { println "Buses Page Verified"
            return true
        }
        else{
            println "Buses Page Failed to load"
            return false
        }
    }

    def sortByTravel(String travelAgent){
        travelAgent = travelAgent.replaceAll(" ", "_")

        waitFor{SearchBusesModule.filterByTravel.displayed}
        SearchBusesModule.filterByTravel.click()
        waitFor{SearchBusesModule.selectTravels(travelAgent).displayed}
        SearchBusesModule.selectTravels(travelAgent).click()
        SearchBusesModule.filterByTravel.click()
    }

    def verifyBusesSort(String travelAgent){
        waitFor{SearchBusesModule.travelSort.displayed}
        SearchBusesModule.travelSort.click()

        waitFor{SearchBusesModule.travelDescSort.displayed}
        SearchBusesModule.travelDescSort.click()
        if(SearchBusesModule.travelSortValidate.text().contains(travelAgent)) {
                    println "Asc sort verified"
                }
        else {
            println "sort failed"
        }

        waitFor{SearchBusesModule.travelAscSort.displayed}
        SearchBusesModule.travelAscSort.click()
        if(SearchBusesModule.travelSortValidate.text().contains(travelAgent)) {
            println "Desc sort verified"
            return true
        }
        else {
            println "sort failed"
            return false
        }
    }

    def checkForSeat(String travelAgent){
        def i
        def seatsAvailabiityStatus
        def size = SearchBusesModule.sizeOfTravelSelected.allElements().size()
        for (i=1; i<=size; i++)
        {
            waitFor{SearchBusesModule.busType(i).displayed}
            if(SearchBusesModule.busType(i).text().contains("Semi") || SearchBusesModule.busType(i).text().contains("Non A/C"))
            {
                seatType=1;

                try{
                    waitFor{SearchBusesModule.viewSeats(i).displayed}
                    SearchBusesModule.viewSeats(i).click()

                    waitFor{SearchBusesModule.avaliableSemiSleeperSeat(i).displayed}

                    if(SearchBusesModule.avaliableSemiSleeperSeat(i).allElements().size() != 0)
                     {
                       println "Seats are available"

                       if(SearchBusesModule.hideSeats(i).displayed)
                       {
                           SearchBusesModule.hideSeats(i).click()
                           println "seats can be hidden"
                       }
                       else {
                           println "not able to hide seats"
                       }
                         seatsAvailabiityStatus = true
                       return i
                   }
                }
                catch (WaitTimeoutException e){
                        seatsAvailabiityStatus = false
                }
            }
            else if (SearchBusesModule.busType(i).text().contains("A/C Sleeper")){
                seatType=2;
                try {
                    waitFor{SearchBusesModule.viewSeats(i).displayed}
                    SearchBusesModule.viewSeats(i).click()

                    waitFor {SearchBusesModule.avaliableSleeperSeat(i).displayed }
                        if (SearchBusesModule.avaliableSleeperSeat(i).allElements().size() != 0) {
                        println "Seats are available"
                        if (SearchBusesModule.hideSeats(i).displayed) {
                            SearchBusesModule.hideSeats(i).click()
                            println "seats can be hidden"
                        } else {
                            println "not able to hide seats"
                        }
                            seatsAvailabiityStatus = true
                            return i
                    }
                }
                catch (WaitTimeoutException e){
                    seatsAvailabiityStatus = false
                }
            }
        }
            assert seatsAvailabiityStatus == true : "No avaliable Seats for today in" +travelAgent+ "look for another travels"

    }

    def selectSeat(int seat){

        String title
        String className
        String boardingPoint
        List<String> journeyInfo = new ArrayList<>();


        if(seatType==1){
            waitFor{SearchBusesModule.viewSeats(seat).displayed}
            SearchBusesModule.viewSeats(seat).click()

            waitFor{SearchBusesModule.avaliableSemiSleeperSeat(seat).displayed}
            title= SearchBusesModule.avaliableSemiSleeperSeat(seat).attr("title")
            journeyInfo.add(title)
            SearchBusesModule.avaliableSemiSleeperSeat(seat).click()
            className = SearchBusesModule.avaliableSemiSleeperSeat(seat).attr("class")

            if(className.contains("selected"))
            {
                println "seat is selected"
                waitFor{SearchBusesModule.selectBoradingPoint(seat).displayed}
                boardingPoint= SearchBusesModule.selectBoradingPoint(seat).attr("text")
                journeyInfo.add(boardingPoint)
                SearchBusesModule.selectBoradingPoint(seat).click()
                waitFor{SearchBusesModule.selectContinue(seat).displayed}
                SearchBusesModule.selectContinue(seat).click()
            }
            else{
                println "seat is not selected try again"
            }

        }
        else if(seatType==2)
        {
            waitFor{SearchBusesModule.viewSeats(seat).displayed}
            SearchBusesModule.viewSeats(seat).click()

            waitFor{SearchBusesModule.avaliableSleeperSeat(seat).displayed}
            title= SearchBusesModule.avaliableSleeperSeat(seat).attr("title")
            journeyInfo.add(title)
            SearchBusesModule.avaliableSleeperSeat(seat).click()
            className = SearchBusesModule.avaliableSleeperSeat(seat).attr("class")

            if(className.contains("selected"))
            {
                println "seat is selected"
                waitFor{SearchBusesModule.selectBoradingPoint(seat).displayed}
                boardingPoint= SearchBusesModule.selectBoradingPoint(seat).attr("text")
                journeyInfo.add(boardingPoint)
                SearchBusesModule.selectBoradingPoint(seat).click()
                waitFor{SearchBusesModule.selectContinue(seat).displayed}
                SearchBusesModule.selectContinue(seat).click()
            }
            else{
                println "seat is not selected try again"
            }
        }

        return journeyInfo
    }

    def returnConfirmationWindow (){
        waitFor{SearchBusesModule.returnWindow.displayed}
        if(SearchBusesModule.returnWindow.text().contains("Would you like to book a return as well?")){
            println "asked for return booking"
            waitFor{SearchBusesModule.returnConfirm.displayed}
            SearchBusesModule.returnConfirm.click()
            println "no return"
        }
            else{
            println "return booking confirmation window failed"
        }
    }
}
