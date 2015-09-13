package com.quikr.pluto;

import android.util.Log;

import com.quikr.pluto.Structure.DataSet;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Calendar;

/**
 * Created by kanishth on 13/9/15.
 */
public class GetAd {
    private static DataSet dataSet;
    public static DataSet getAdsByCategory(final String param[])
    {
        final String apiName = "/public/adsByCategory";
        final String url = "https://api.quikr.com/public/adsByCategory";
        final String categoryId = param[0];
        final String from = param[1];
        final String size = param[2];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Default dates
                Calendar calendar = Calendar.getInstance();

                String year = calendar.get(Calendar.YEAR) + "";
                String month = (calendar.get(Calendar.MONTH) > 9) ? "" : "0";
                month += calendar.get(Calendar.MONTH);
                String day = (calendar.get(Calendar.DAY_OF_MONTH) > 9) ? "" : "0";
                day += calendar.get(Calendar.DAY_OF_MONTH);

                String date = year + "-" + month + "-" + day;
                String data = CommonResources.appId+apiName+"2015-09-14";

                //Log.d("****", data);

                String hmac = "";

                try {
                    hmac = Encode.calculateRFC2104HMAC(data,CommonResources.token);
                } catch (Exception e) {
                    Log.d("****pluto1**", e.toString());
                }

                HttpClient httpClient = new DefaultHttpClient(); //Deprecated

                try {
                    String url2 = url+"?"+"categoryId="+categoryId+"&from="+from+"&size="+size;
                    HttpGet get = new HttpGet(url2);

                    get.addHeader("content-type","application/json");
                    get.addHeader("X-Quikr-App-Id", CommonResources.appId+"");
                    get.addHeader("X-Quikr-Token-Id", CommonResources.tokenId);
                    get.addHeader("X-Quikr-Signature",hmac);
                    get.addHeader("Cache-Control", "no-cache");
                    //post.setEntity(params);

                    HttpResponse response = httpClient.execute(get);
                    String r1 = EntityUtils.toString(response.getEntity());
                    //Log.d("**response**", r1);
                    setDataSet(JSONExtracter.extract(r1));

                    //handle response here...
                } catch (Exception ex) {
                    Log.d("***plutoEX**", ex.toString());
                }
            }
        });
        thread.start();
        while(thread.isAlive()){
        }
        return dataSet;
    }
    private static void setDataSet(DataSet ds)
    {
        dataSet = ds;
    }
}
