package com.quikr.pluto;

import android.content.Context;
import android.util.Log;

import com.quikr.pluto.Backend.ReturningClass;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by kanishth on 13/9/15.
 */
public class GetToken
{
    public static void postMethod(final Context context)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                String data = "kanishthkarthik@gmail.com"+CommonResources.appId+CommonResources.date;

                String hmac = "";

                try {
                    hmac = Encode.calculateRFC2104HMAC(data, CommonResources.secretKey);
                } catch (Exception e) {
                    Log.d("****pluto1**", e.toString());
                }

                HttpClient httpClient = new DefaultHttpClient(); //Deprecated

                try {
                    HttpPost post = new HttpPost("https://api.quikr.com/app/auth/access_token");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("appId",CommonResources.appId);
                    jsonObject.put("signature", hmac);

                    StringEntity params = new StringEntity(jsonObject.toString());

                    Log.d("****",data);
                    Log.d("**jsonObject",jsonObject.toString());

                    post.addHeader("content-type","application/json");
                    post.setEntity(params);
                    HttpResponse response = httpClient.execute(post);
                    String r1 = EntityUtils.toString(response.getEntity());
                    Log.d("**response**", r1);

                    JSONObject jsonResponse = new JSONObject(r1);
                    String token = jsonResponse.getString("token");
                    String tokenId = jsonResponse.getString("tokenId");

                    ReturningClass.setToken(context, token);
                    ReturningClass.setTokenID(context, tokenId);
                    ReturningClass.setTokenDate(context ,CommonResources.date);

                    // handle response here...
                } catch (Exception ex) {
                    Log.d("***plutoEX**", ex.toString());
                }
            }
        });
        thread.start();
        while(thread.isAlive()){
        }
    }
}
