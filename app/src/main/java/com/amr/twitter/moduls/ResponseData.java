package com.amr.twitter.moduls;

import java.util.ArrayList;

/**
 * Created by amr on 01/02/18.
 */

public class ResponseData {
    public ArrayList<Follower> users;
    public long next_cursor;
    public String next_cursor_str;
    public long previous_cursor;
    public String previous_cursor_str;

    public ArrayList<Follower> getUsers() {
        return users;
    }

    public long getNext_cursor() {
        return next_cursor;
    }
}
