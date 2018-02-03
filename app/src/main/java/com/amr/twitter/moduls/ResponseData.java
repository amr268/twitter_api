package com.amr.twitter.moduls;

import java.util.ArrayList;

/**
 * Created by amr on 01/02/18.
 */

public class ResponseData {
    public ArrayList<Follower> users;
    public int next_cursor;
    public String next_cursor_str;
    public int previous_cursor;
    public String previous_cursor_str;

    public ArrayList<Follower> getUsers() {
        return users;
    }
}
