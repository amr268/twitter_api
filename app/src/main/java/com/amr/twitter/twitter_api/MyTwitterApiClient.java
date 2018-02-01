package com.amr.twitter.twitter_api;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by amr on 31/01/18.
 */

public class MyTwitterApiClient extends TwitterApiClient {

    public MyTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public IServicesTwitter getCustomService() {
        return getService(IServicesTwitter.class);
    }
}
