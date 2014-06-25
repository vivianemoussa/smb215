package com.example.projetsmb215;

import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Patterns;

public class Common extends Application {
	//public static final String PROJECT_NUMBER = "1086298744334";
	 
    public static final String PROFILE_ID = null;
	public static String[] email_arr;
    private static SharedPreferences prefs;
 
    @Override
    public void onCreate() {
        super.onCreate();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
         
        List<String> emailList = getEmailList();
        email_arr = emailList.toArray(new String[emailList.size()]);
    }
     
    private List<String> getEmailList() {
        List<String> lst = new ArrayList<String>();
        Account[] accounts = AccountManager.get(this).getAccounts();
        for (Account account : accounts) {
            if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches()) {
                lst.add(account.name);
            }
        }
        return lst;
    }

	public static String getRingtone() {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean isNotify() {
		// TODO Auto-generated method stub
		return false;
	}

	public static Object getPreferredEmail() {
		// TODO Auto-generated method stub
		return null;
	}   
}