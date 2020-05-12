package com.ernest.watsappupdateclone;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.appcompat.app.AlertDialog;

import org.jsoup.Jsoup;

import java.io.IOException;

public class GPlaystoreVersionChecker extends AsyncTask<String, String, String> {

    private String newVersion;

    private Activity context;

    public GPlaystoreVersionChecker(Activity context) {
        this.context=context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id="+context.getPackageName()+"&hl=en")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select(".IQ1z0d .htlgb")
                    .get(7)
                    .ownText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newVersion;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);



        String latestVersion = newVersion;
        if(!Variables.versionname.equals(latestVersion) && (latestVersion!=null)){

            AlertDialog.Builder alert=new AlertDialog.Builder(context);
            alert.setTitle("Update  Watsapp Update Clone")
                    .setCancelable(true)
                    .setMessage("There is a new version of Watsapp Update Clone, Please update else after 30 days your version will become out of date .")
                    .setNegativeButton("Update", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+context.getPackageName())));
                            context.finish();
                        }
                    })
                    .setPositiveButton("Later", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            context.finish();
                        }
                    });

            alert.setCancelable(false);
            alert.show();
        }
    }
}
