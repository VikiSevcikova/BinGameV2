package com.example.bingame;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VyhodnotenieHry3 extends Activity {
	TextView spravne, nespravne, cas;
	Button menu,opakuj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vyhodnotenie);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//z�skame referencie na textView
		spravne = (TextView)findViewById(R.id.spravne); 
		nespravne = (TextView)findViewById(R.id.nespravne);
		cas = (TextView)findViewById(R.id.totalTime);
		menu = (Button) findViewById(R.id.menu);
		opakuj = (Button) findViewById(R.id.repeat);
		Typeface txt = Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf");
		spravne.setTypeface(txt);
		nespravne.setTypeface(txt);
		cas.setTypeface(txt);
		menu.setTypeface(txt);
		opakuj.setTypeface(txt);
		
		Log.d("vyhodnotenie", "som tu");
		//inicializujeme si intent, aby sme z predo�lej aktivity z�skali �daje, ktor� sme pripojili
		Bundle b = getIntent().getExtras();
		spravne.setText("Po�et spr�vnych odpoved�: " + b.getInt("spravne", -1));
		nespravne.setText("Po�et nespr�vnych odpoved�: " + b.getInt("nespravne", -1));
		int c = b.getInt("totalTime", -1);
		cas.setText("�as trvania: "+ c + " sek�nd");
		//(int) ((c / (1*60)) % 60)
	}
	public void spatMenu(View v){
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
//po stla�en� tla�idla sa zavol� met�da opakujHru, vytvor�me explicitn� intent, pripoj�me na��tan� �daje z triedy Hra
//aby sme mohli spusti� t� ist� hru(kateg�riu) s �dajmi, ktor� sme u� stiahli
	public void opakujHru(View v){
		Intent intent = new Intent(this,Hra3.class);
		startActivity(intent);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
	
	
}