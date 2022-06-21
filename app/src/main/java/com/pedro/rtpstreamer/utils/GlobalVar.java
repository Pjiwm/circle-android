package com.pedro.rtpstreamer.utils;

import android.app.Application;

public class GlobalVar extends Application {
    public void setCurrentUser(AuthClass currentUser) {
        this.currentUser = currentUser;
    }

    public AuthClass getCurrentUser() {
        return currentUser;
    }

    private AuthClass currentUser;

}
