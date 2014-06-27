package com.example.projetsmb215;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {

    static final String SERVER_URL = "http://localhost:8080/GcmServer/";

    static final String SENDER_ID = "1086298744334";

    static final String TAG = "GCMTesting";

    static final String DISPLAY_MESSAGE_ACTION = "com.google.android.gcm.demo.app.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";

    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
