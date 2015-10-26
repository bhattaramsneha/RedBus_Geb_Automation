package module

import geb.Module
import org.openqa.selenium.By
/**
 * Created by snehab on 16-10-2015.
 */
class searchBusesModule extends Module {

    static content = {
        fromText{$(".tab.onward.active.BoxModel.W100Imp.OH .From.TextSemiBold")}
        toText{$(".tab.onward.active.BoxModel.W100Imp.OH .To.TextSemiBold")}
        onwardDate{$(".tab.onward.active.BoxModel.W100Imp.OH #dateDoubleOnward")}


        //selectionFrame{$("iframe[style=\"display: none;\"]")}
        filterByTravel{$(".filter.Travels .filterType")}
        selectTravels{val->$("#Travels_"+val+"")}
        //filterByTravel{$(By.xpath(".//span[contains(text(),'Travels')]"))}

        travelSort{$(".detailsBlock>a")}
        travelAscSort{$(".asc")}
        travelDescSort{$(".desc")}
        travelSortValidate{$(".BusList > li:nth-child(1) .BusName")}


        sizeOfTravelSelected{$(".BusName")}
        busType{val->$(".BusList > li:nth-child("+val+") .BusType")}
        viewSeats{val->$(".BusList > li:nth-child("+val+") .viewSeatsBtn")}
        avaliableSemiSleeperSeat{val->$(".BusList > li:nth-child("+val+") a.available.seat")}
        avaliableSemiSleeperLadiesSeat{val->$(".BusList > li:nth-child("+val+") a.available.ladies.seat")}
        avaliableSleeperSeat{val->$(".BusList > li:nth-child("+val+") a.available.sleeper")}
        hideSeats{val->$(".BusList > li:nth-child("+val+") .viewSeatsBtn", text:"Hide Seats")}

        selectBoradingPoint{val->$(".BusList > li:nth-child("+val+") .selectContainer.BPContainer>select>option:nth-child(2)")}
        selectContinue{val->$(".BusList > li:nth-child("+val+") .continueBtn")}

        returnWindow{$(".question.XXCN")}
        returnConfirm{$(".no.ancLink")}
    }
}
