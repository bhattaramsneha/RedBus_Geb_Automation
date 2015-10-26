package specs


import geb.spock.GebReportingSpec
import page.homePage
import page.signInPage
import page.myHomePage
import page.searchBusesPage
import page.paymentPage
import spock.lang.Shared
import spock.lang.Stepwise

/**
 * Created by snehab on 15-10-2015.
 */
@Stepwise
class basePageLinkSpecs extends GebReportingSpec {

   @Shared def List<String> journeyInfo = new ArrayList<>();
    def fromLocation ="Hyderabad"
    def toLocation = "Bangalore"
    def travelAgent= "Geepee Travels"//"SVR TOURS And TRAVELSAPPLE I BUS TRAVELS"//"Kesineni Travels"
    def travelCity = "Hyderabad to Bangalore"

     def "Login into My redbus Page" (){
        given:
        to homePage

        when:
        clickOnSignInLink()

        then:
        at signInPage

         when:
         gotoSignInLink()

         and:
         login("snehafortest@gmail.com","redbus123")

         then:
         at myHomePage
         println "Into My Account home page"
    }

    def "Enter your journey details"(){
        when:
        at myHomePage

        and:
        enterJourneyDetails("Hyderabad","Bangalore")

       then:
       at searchBusesPage
       println "Into Buses page"
       verifyBusesPage(fromLocation,toLocation)
    }

    def "validate the buses information"(){
        when:
        at searchBusesPage

        and:
        sortByTravel(travelAgent)

        then:
        verifyBusesSort(travelAgent)
    }

    def "select the bus and the seat"(){
        def seatAvaliable
        when:
        at searchBusesPage

        and:
        seatAvaliable = checkForSeat(travelAgent)
        journeyInfo = selectSeat(seatAvaliable)
        returnConfirmationWindow()

        then:
        at paymentPage
        println "Into payment page"
    }

    def "payment page validations"(){
        when:
        at paymentPage

        then:
        validateTravelCity(travelCity)
        validateTravelAgent(travelAgent)
        validateBoardingPoint(journeyInfo.get(1))
        validaSeatNumber(journeyInfo.get(0))
        validateFare(journeyInfo.get(0))
        validateDate()
    }
}
