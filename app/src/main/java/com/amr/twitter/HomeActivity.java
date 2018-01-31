package com.amr.twitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterAuthToken authToken = session.getAuthToken();
        String token = authToken.token;
        String secret = authToken.secret;

        getUserDetails(session);

//Here we get all the details of user's twitter account
        /*Call<User> userCall= TwitterCore.getInstance().getApiClient(session).getAccountService().verifyCredentials(true, false);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<com.twitter.sdk.android.core.models.User> call, Response<User> response) {
                MyTwitterApiClient myTwitterApiClient = new MyTwitterApiClient(session);
                myTwitterApiClient.getCustomService().list(response.body().id).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.e("list",response.body().toString());
                        BufferedReader reader = null;
                        StringBuilder sb = new StringBuilder();

                        reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));

                        String line;

                        try {
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }


                        String result = sb.toString();
                        System.out.println("Response is>>>>>>>>>"+result);
                        JsonObject obj= new Gson().fromJson(result,JsonObject.class);
                        JsonArray usersArray= (JsonArray) obj.get("users");
                        JsonArray userIds = new JsonArray();
                        for(int i=0;i<usersArray.size();i++){
                            JsonObject userObject = (JsonObject) usersArray.get(i);
                            userIds.add(userObject.get("id_str"));
                        }
                        // Api call with Twitter followers ids
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.add("twitter_ids",userIds);

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Utils.showToast(InviteFriendActivity.this,getString(R.string.something_went_wrong));
                        progressDialog.dismiss();
                    }

                });
            }

            @Override
            public void onFailure(Call<com.twitter.sdk.android.core.models.User> call, Throwable t) {
                Utils.showToast(InviteFriendActivity.this,getString(R.string.something_went_wrong));
                progressDialog.dismiss();
            }
        });*/
    }

    public void getUserDetails(final TwitterSession twitterSession) {
        TwitterCore.getInstance().getApiClient(twitterSession).getAccountService().verifyCredentials(true, true, true).enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> userResult) {
                try {
                    User user = userResult.data;
                    String fullname = user.name;
                    long twitterID = user.getId();
                    String userSocialProfile = user.profileImageUrl;
                    String userEmail = user.email;
                    String userFirstNmae = fullname.substring(0, fullname.lastIndexOf(" "));
                    String userLastNmae = fullname.substring(fullname.lastIndexOf(" "));
                    String userScreenName = user.screenName;
                    MyTwitterApiClient myTwitterApiClient = new MyTwitterApiClient(twitterSession);
                    myTwitterApiClient.getCustomService().getFollowers(twitterID).enqueue(new Callback<ResponseBody>() {


                        @Override
                        public void success(Result<ResponseBody> result) {
                            Log.e("list",result.data.toString());
                            BufferedReader reader = null;
                            StringBuilder sb = new StringBuilder();

                            reader = new BufferedReader(new InputStreamReader(result.data.byteStream()));

                            String line;

                            try {
                                while ((line = reader.readLine()) != null) {
                                    sb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            String result2 = sb.toString();
                            System.out.println("Response is>>>>>>>>>"+result2);
                            JsonObject obj= new Gson().fromJson(result2,JsonObject.class);
                            JsonArray usersArray= (JsonArray) obj.get("users");
                            JsonArray userIds = new JsonArray();
                            for(int i=0;i<usersArray.size();i++){
                                JsonObject userObject = (JsonObject) usersArray.get(i);
                                userIds.add(userObject.get("id_str"));
                            }
                            // Api call with Twitter followers ids
                            JsonObject jsonObject = new JsonObject();
                            jsonObject.add("twitter_ids",userIds);
                        }

                        @Override
                        public void failure(TwitterException exception) {
                            Log.d("error", exception.toString());
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void failure(TwitterException e) {
            }
        });
    }
}
