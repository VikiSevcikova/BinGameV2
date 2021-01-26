package com.example.bingame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity{
	protected TextView txt;
	protected Button btn1;
	protected Button btn2;
	protected Button btn3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  
		txt = (TextView) findViewById(R.id.title);
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		Typeface title = Typeface.createFromAsset(getAssets(), "IDroid.otf");
	    txt.setTypeface(title);
	    Typeface btn = Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf");
	    btn1.setTypeface(btn);
	    btn2.setTypeface(btn);
	    btn3.setTypeface(btn);
	}
	public void spustiJedna(View v){
		Intent myIntent = new Intent(MainActivity.this, Hra1.class);
		startActivity(myIntent);
	}
	public void spustiDva(View v){
		Intent myIntent = new Intent(MainActivity.this, Hra2.class);
		startActivity(myIntent);
	}
	public void spustiTri(View v){
		Intent myIntent = new Intent(MainActivity.this, Hra3.class);
		startActivity(myIntent);
	}
	@Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Vypn˙ù aplik·ciu?");
        alertDialogBuilder
                .setMessage("Kliknite ·no pre vypnutie!")
                .setCancelable(false)
                .setPositiveButton("¡no",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
