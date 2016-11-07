package com.a2016reserch.sliit.insight.calculatorSpeedDialer;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sandasala on 11/7/2016.
 */
public class SharedPreference {

    public static final String CONTACTSS = "contactss";
    public static final String PREFS_NAME = "PRODUCT_APP";


    public SharedPreference() {
        super();

    }


    public void addContacts(Context context, contacts bmItem) {
        List<contacts> itms = getContacts(context);
        if (itms == null)
            itms = new ArrayList<contacts>();
        itms.add(bmItem);
        saveContacts(context, itms);
    }


    public ArrayList<contacts> getContacts(Context context) {
        SharedPreferences settings;
        List<contacts> bmitms;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);



        if (settings.contains(CONTACTSS)) {
            String jsonFavorites = settings.getString(CONTACTSS, null);
            Gson gson = new Gson();
            contacts[] sItems = gson.fromJson(jsonFavorites,
                    contacts[].class);

            bmitms = Arrays.asList(sItems);
            bmitms = new ArrayList<contacts>(bmitms);

        } else
            return null;


        return (ArrayList<contacts>) bmitms;
    }



    public void saveContacts(Context context, List<contacts> bkitems) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonSitems = gson.toJson(bkitems);

        editor.putString(CONTACTSS, jsonSitems);

        editor.commit();
    }

    public void removeAll(Context context){
        ArrayList<contacts> arrContacts = getContacts(context);

        if(arrContacts!=null) {

            arrContacts.removeAll(arrContacts);
            saveContacts(context, arrContacts);
        }
    }

    public void editContacts(Context context,contacts contact,int no) {
        ArrayList<contacts> arrShop = getContacts(context);

        if(arrShop!=null){
            arrShop.remove(contact);
            arrShop.remove(no);
            arrShop.add(no,contact);
            ArrayList<contacts> arrSh=arrShop;
            arrSh.toString();
            saveContacts(context,arrShop);
        }
    }



    public void removeAdetail(Context context, contacts contact, int no){
        ArrayList<contacts> arrShop = getContacts(context);

        if(arrShop!=null){
            arrShop.remove(contact);
            arrShop.remove(no);
            ArrayList<contacts> arrSh=arrShop;
            arrSh.toString();
            saveContacts(context,arrShop);
        }
    }

}
