package module


import geb.Module
import org.openqa.selenium.By


/**
 * Created by snehab on 15-10-2015.
 */

class signInPageModule extends Module {
    static content = {
        loginFrame{$(".modalIframe")}
            signInLink{$(".social.FC.DIB.active .signin-screen", text:"SIGN IN")}
        //signInLink{$(By.xpath(".//h2[text()='Login to your redBus Account!']"))}

        username{$("#email-mobile")}
        password{$(".signin.FC.DIB.active #password")}

        signIn{$("#doSignin")}
    }
}