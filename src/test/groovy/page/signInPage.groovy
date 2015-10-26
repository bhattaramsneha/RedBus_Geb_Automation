package page

import geb.Page
import module.signInPageModule


/**
 * Created by snehab on 15-10-2015.
 */
class signInPage extends Page {

    static content = {
        SignInPageModule{module(signInPageModule)}
    } //calling the locator

    static at = {
        withFrame(SignInPageModule.loginFrame,{
            SignInPageModule.signInLink.displayed
        })
    }

    def gotoSignInLink(){
        withFrame(SignInPageModule.loginFrame,{
            SignInPageModule.signInLink.click()
        })
    }

    def login(String username, String Password){
        withFrame(SignInPageModule.loginFrame,{
            SignInPageModule.username.value(username)
        })
        withFrame(SignInPageModule.loginFrame,{
            SignInPageModule.password.value(Password)
        })
        withFrame(SignInPageModule.loginFrame,{
            SignInPageModule.signIn.click()
        })
    }
}
