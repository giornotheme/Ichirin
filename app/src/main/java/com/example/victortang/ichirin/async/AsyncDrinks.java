package com.example.victortang.ichirin.async;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victortang.ichirin.activities.MainActivity;
import com.example.victortang.ichirin.models.Drink;
import com.example.victortang.ichirin.utils.Constants;
import com.example.victortang.ichirin.utils.StreamHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AsyncDrinks extends AsyncTask<Void, Void, List<Drink>> {

    String searchVerification;

    private final MainActivity.MyAdapter adapter;

    public AsyncDrinks(MainActivity.MyAdapter adapter, String search) {
        this.adapter = adapter;
        this.searchVerification = search;
        Log.i("search",searchVerification);
    }

    public List<Drink> lstDrink = new ArrayList<>();

    public List<Drink> getLstDrink() {
        return lstDrink;
    }


    public void setLstDrink(List<Drink> lstDrink) {
        lstDrink = lstDrink;
    }

    public void addDrinksToList(Drink drink){
        lstDrink.add(drink);
    }

    public void searchMatchesList(String string, List<Drink> lstDrink, Drink drink){
        if (drink.getDrinkTitle().contains(string)) {
            lstDrink.add(drink);
        }
    }

        @Override
    protected List<Drink> doInBackground(Void... voids) {

        URL urlReq;
        JSONObject resJSON;

        try {
            urlReq = new URL(Constants.APIurl);
            Log.i("url",urlReq.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) urlReq.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String s = StreamHandler.readStream(in);
            resJSON = new JSONObject(s);
            //Afficher dans le logcat
            //Log.i("JFL", resJSON.toString());

            JSONArray drinkArray = resJSON.getJSONArray("drinks");

            for(int i=0; i<drinkArray.length();i++){
                Drink temp = new Drink();
                temp.setDrinkTitle(drinkArray.getJSONObject(i).getString("strDrink"));
                temp.setDrinkInstruction(drinkArray.getJSONObject(i).getString("strInstructions"));
                temp.setDrinkImage(drinkArray.getJSONObject(i).getString("strDrinkThumb"));


                if (searchVerification.isEmpty()) {
                    lstDrink.add(temp);
                    Log.i("Contient", temp.getDrinkTitle());
                }
                else if (temp.getDrinkTitle().contains(searchVerification)) {
                    lstDrink.add(temp);
                }
            }
            urlConnection.disconnect();


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return lstDrink;
    }

    protected void onPostExecute(List<Drink> lstDrink){
        //this.displayAllDrinks();
        if(lstDrink!=null) {
            for (int i = 0; i < lstDrink.size(); i++) {
                adapter.dd(lstDrink.get(i));
            }
            adapter.notifyDataSetChanged();
            //searchMatchesList(lstDrink, "Blue");
        }
    }

    //Afficher dans le logcat

    /*public void displayAllDrinks(){

        for(int i = 0; i < AsyncDrinks.lstDrink.size(); i++){
            Log.i("JFL", AsyncDrinks.lstDrink.get(i).toString());
        }

    }*/
}
