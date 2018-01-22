package exampls.com.listenerproject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by 450 G1 on 22/01/2018.
 */

public class Worker {
    Context context;
    public String test = null ;
    public Listener listen = null;

    public void setListen(Listener listen) {
        this.listen = listen;
    }

    interface Listener{
        void onClick(String text);
    }

    public Worker(Context context){
        this.context =context;
    }
    void go(){

        String URL = "http://192.168.1.8/index.php";
        final Response.Listener listener = new Response.Listener<String>() {

            @Override
            public void onResponse(String  response) {
                listen.onClick(response);
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }

        };

        StringRequest stringRequest  = new StringRequest(Request.Method.GET, URL,  listener, errorListener);

        SingleTon.getInstance(context).addRequest(stringRequest);
    }

}
