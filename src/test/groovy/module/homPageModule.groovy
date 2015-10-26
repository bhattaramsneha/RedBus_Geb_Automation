package module

import geb.Module


/**
 * Created by snehab on 15-10-2015.
 */
class homPageModule extends Module {
    static content = {
        signInLink{$("#signInLink")}
    }
}
