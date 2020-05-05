package com.kip.gillz.vaccine_guide_system.Search;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.kip.gillz.vaccine_guide_system.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchBacktask extends AsyncTask<String,Void,String> {
    String upload_url="http://www.snippet.io.ke/Pos_C/sales.php";
    //String upload_url="http://192.168.43.222/Pos_C/sales.php";



    Context ctx;
    private ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;
    RecyclerView recyclerView;
    private Searchadapter adapte;
    List<SearchModel> productList;

    public SearchBacktask(Context ctx){
        this.ctx=ctx;
        activity= (Activity) ctx;
    }
    @Override
    protected String doInBackground(String... params) {

        HashMap<String, String> data1 = new HashMap<>();
        try{
            URL url=new URL(upload_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(6000);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            bufferedWriter.write(getPostDataString(data1));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line="";
            while((line=bufferedReader.readLine())!= null){
                stringBuilder.append(line+"\n");

            }
            httpURLConnection.disconnect();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString().trim();


        }catch (MalformedURLException e)
        {
            e.printStackTrace();
        }catch (IOException e){

            e.printStackTrace();
        }


        return null;

    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result= new StringBuilder();
        boolean first=true;
        for (Map.Entry<String,String>entry:params.entrySet()){
            if (first){
                first=false;
            }else{
                result.append("&");

            }
            result.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
        }

        return result.toString();

    }


    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(ctx);
        builder = new AlertDialog.Builder(activity);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Loading Individual sale reports");
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        recyclerView = activity.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        recyclerView.setHasFixedSize(true);
        productList = new ArrayList<>();
        adapte = new Searchadapter(ctx, productList);
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String json) {
        if (json == null) {
          progressDialog.dismiss();
            recyclerView.setAdapter(null);
        }
        else {
            progressDialog.dismiss();

            //Toast.makeText(ctx,json,Toast.LENGTH_LONG).show();
            final String text33 = json.trim().replaceAll("\\s", "").substring(0, 1);
            if (text33.equals("<")) {
                recyclerView.setAdapter(null);
                progressDialog.dismiss();

            }
            else if(json.trim().replaceAll("\\s","").contentEquals("[]")){
                recyclerView.setAdapter(null);
                progressDialog.dismiss();


            } else {
                progressDialog.dismiss();
                try {


                    JSONArray array = new JSONArray(json);

                    productList.clear();

                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject xxx = array.getJSONObject(i);

                        //adding the product to product list
                        productList.add(new SearchModel(
                                xxx.getString("Item"),
                                xxx.getInt("Quantity"),
                                xxx.getInt("Price"),
                                xxx.getString("Receipt"),
                                xxx.getString("TDate"),
                                xxx.getString("Item_id"),
                                xxx.getString("Agent"),
                                xxx.getInt("Product_Count"),
                                xxx.getInt("Tprice")

                        ));
                    }
                    //creating adapter object and setting it to recyclerview
                    //animation_type = ItemAnimation.FADE_IN;
                    adapte = new Searchadapter(ctx, productList);
                    adapte.notifyDataSetChanged();
                    recyclerView.setAdapter(adapte);




                    //progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  void  showDialog(String title, String message, String code){
        builder.setTitle(title);
        if(code.equals("upload_true")||code.equals("upload_false")){
            builder.setMessage(message);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }

}