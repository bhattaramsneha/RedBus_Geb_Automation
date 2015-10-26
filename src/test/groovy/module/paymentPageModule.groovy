package module

import geb.Module

/**
 * Created by snehab on 20-10-2015.
 */
class paymentPageModule extends Module{
    static content = {
        verifyText{$(".TextSemiBold.payment-details", text:"Payment Details")}

        travelCity{$("#onwdets .travCity")}
        dateOfJourney{$("#doj")}
        boardingPoints{$("#onwdets .travBoard")}
        seatNumber{$("#onwdets .travSeat")}
        travelAgent{$("#onwdets .travTrav")}
        fareCharged{$("#finalFareToBeCharged")}
        onwardfareCharged{$("#fareToBeCharged")}
    }
}
