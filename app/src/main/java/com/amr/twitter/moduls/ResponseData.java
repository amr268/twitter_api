package com.amr.twitter.moduls;

import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;

/**
 * Created by amr on 01/02/18.
 */

public class ResponseData {
    public ArrayList<User> users;
    public int next_cursor;
    public String next_cursor_str;
    public int previous_cursor;
    public String previous_cursor_str;

    public ArrayList<User> getUsers() {
        return users;
    }
}
