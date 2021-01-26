package com.example.bingame;

import java.util.Random;
import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Hra3 extends Activity implements SensorEventListener{	
	private ImageView b1;
	private ImageView b2;
	private ImageView b4;
	private ImageView b8;
	private ImageView b16;
	private ImageView odpImg,info;
	private ImageButton imgAno,imgNie;
	int h;
	private SensorManager sensorManager;
	private long lastUpdate = 0;
	int spravne,nespravne;
	int stav[] = new int [5];  
	boolean spr= false;
	Random r = new Random();
	private int hodnota;
	private int pocetZadani = 10;
	private TextView zadanie,odpovedTxt,c,cas;
	int celkomZadani = 10;
	MediaPlayer zvukS;
	MediaPlayer zvukN;
	ImageView zvukIcon;
	boolean zvukStav,odpovedal;
	int time,totalTime;
	private Handler handler;	
	private Runnable runnable = new Runnable(){
	    public void run() { 
	    	totalTime++;
	    	time--;	
	    	cas.setText("Zostáva "+time+" sekúnd."); 	 	    	  
	    	if(time<=0){	 	    	  
	            odpovedal = false;
				skontrolovat(false,odpovedal);	    		    		
	    	}
	    	handler.postDelayed(this,1000); //naplánuje sa èo sa má skusti a oneskorenie(v milisekundách)
	    }
		};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hra3);
		zvukS = MediaPlayer.create(this,R.raw.correct);
		zvukN = MediaPlayer.create(this, R.raw.wrong);
		zvukIcon = (ImageView) findViewById(R.id.zvukIcon);
		zvukStav = true; 	 	    	  
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		Typeface txt = Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf");

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
	    sensorManager.registerListener(this,
	            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	            SensorManager.SENSOR_DELAY_NORMAL);
		}else{
			//zariadenie nemá akcelometer
		}
		totalTime = 0;
		time=15;
		cas = (TextView)findViewById(R.id.cas);
		cas.setTypeface(txt);
    	cas.setText("Zostáva "+time+" sekúnd.");
	    lastUpdate = System.currentTimeMillis(); //pre senzor
		spravne = nespravne=0;
		c = (TextView) findViewById(R.id.cislo);
		b1 = (ImageView) findViewById(R.id.bit1);	
		b2 = (ImageView) findViewById(R.id.bit2);
		b4 = (ImageView) findViewById(R.id.bit4);				
		b8 = (ImageView) findViewById(R.id.bit8);				
		b16 = (ImageView) findViewById(R.id.bit16);	
		odpImg = (ImageView) findViewById(R.id.odpoved);
		info = (ImageView) findViewById(R.id.info);
		odpImg.setVisibility(View.INVISIBLE);
		odpovedTxt = (TextView) findViewById(R.id.odpovedTxt);
		odpovedTxt.setVisibility(View.INVISIBLE);
		zadanie = (TextView)findViewById(R.id.zadanie);
		c.setTypeface(txt);
		zadanie.setTypeface(txt);
		odpovedTxt.setTypeface(txt);
		imgAno = (ImageButton) findViewById(R.id.stvorec);
		imgAno.setOnClickListener(new View.OnClickListener() {					
			public void onClick(View v) {				
				odpovedal = true;
				skontrolovat(true,odpovedal);
				}
		});
		imgNie = (ImageButton) findViewById(R.id.btn_nie);
		imgNie.setOnClickListener(new View.OnClickListener() {					
			public void onClick(View v) {	
				odpovedal = true;
				skontrolovat(false,odpovedal);
			}
		});
		nastavCisla();
		
		zvukIcon.setOnClickListener(new View.OnClickListener() {					
			public void onClick(View v) {				
				if(zvukStav){
					zvukIcon.setBackgroundResource(R.drawable.sound_off);
					zvukStav = false;
				}else{
					zvukIcon.setBackgroundResource(R.drawable.sound_on);
					zvukStav = true;
				}
				}
		});
		 info.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					AlertDialog alertDialog = new AlertDialog.Builder(Hra3.this).create();
					alertDialog.setTitle("Informácia");
					alertDialog.setIcon(R.drawable.info);
					alertDialog.setMessage("Kliknite na áno/nie alebo nakloòte tablet vpravo/v¾avo pod¾a toho èi platí rovnos.");
					alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int which) {
					            dialog.dismiss();
					        }
					    });
					alertDialog.show();
				}
			});  
			handler = new Handler();
			handler.postDelayed(runnable,1000);
	}
//ked už odpovie nekontrolova
	public void skontrolovat(boolean odpoved,boolean odpovedal){
		if(!odpovedal){
			nespravne++;
		}else{
			if(odpoved){
				if(hodnota == h){
					spr = true;
					spravne++;			
				}else{
					spr = false;
					nespravne++;			
				}
			}else{
				if(hodnota == h){
					spr = false;
					nespravne++;			
				}else{
					spr = true;
					spravne++;			
				}
			}
		}	
			zobrazenieOdpovede();
	}

	public void zobrazenieOdpovede(){
		imgAno.setClickable(false);
		imgNie.setClickable(false);
		if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
			sensorManager.unregisterListener(this,
		            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
			}else{
				//zariadenie nemá akcelometer
			}
		new CountDownTimer(4000, 1000) {
		    public void onTick(long millisUntilFinished) {
		    	handler.removeCallbacks(runnable);
		    	cas.setVisibility(View.INVISIBLE);
		    	if(odpovedal){
		    		if(spr){
		    			odpImg.setImageResource(R.drawable.right);
		    			odpImg.setVisibility(View.VISIBLE);
		    			odpovedTxt.setText("OK");
		    			odpovedTxt.setVisibility(View.VISIBLE);
		    			if(zvukStav){
		    				zvukS.start();
		    			}
		    		}else{
		    			odpImg.setImageResource(R.drawable.wrong);
		    			odpImg.setVisibility(View.VISIBLE);
		    			String str = "";
		    			prevod(hodnota); //aká hodnota tam mala by
		    			for (int i=(stav.length-1);i>=0;i--){
		    				str += ""+stav[i];
		    			}
		    			odpovedTxt.setText(""+str+" = "+hodnota);
		    			odpovedTxt.setVisibility(View.VISIBLE);
		    			if(zvukStav){
		    				zvukN.start();
		    			}
		    		}    	
		    	}else{
		    		odpImg.setImageResource(R.drawable.time);
	    			odpImg.setVisibility(View.VISIBLE);
	    			String str = "";
	    			prevod(hodnota); //aká hodnota tam mala by
	    			for (int i=(stav.length-1);i>=0;i--){
	    				str += ""+stav[i];
	    			}
	    			odpovedTxt.setText(""+str+" = "+hodnota);
	    			odpovedTxt.setVisibility(View.VISIBLE);
	    			if(zvukStav){
	    				zvukN.start();
	    			}
		    	}
		    }

		    public void onFinish() { 
				handler.postDelayed(runnable,1000);
		    	cas.setVisibility(View.VISIBLE);
		    	odpImg.setVisibility(View.INVISIBLE);
		    	odpovedTxt.setVisibility(View.INVISIBLE);
		    	nastavCisla();
		    }

		}.start();
		
	}
	public void nastavCisla(){		
		if (pocetZadani == 0){
			finish();
			}
		else if(pocetZadani > 0){
			imgAno.setClickable(true);
			imgNie.setClickable(true);
			if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
				sensorManager.registerListener(this, sensorManager.getDefaultSensor
						(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		   
			}else{
				//zariadenie nemá akcelometer
			} 	 
			h=0;
			ImageView b[] = {b1,b2,b4,b8,b16};
			hodnota = (r.nextInt(31) + 1);
			Log.d("hodnota",""+hodnota);
			c.setText(Integer.toString(hodnota));
			zadanie.setText(((celkomZadani)-pocetZadani+1)+"/"+celkomZadani);
			switch(r.nextInt(2)){
				case 0:							
					for (int i = 0; i<=(b.length-1); i++){
					switch(r.nextInt(2)){
						case 0: b[i].setImageResource(R.drawable.b0);
							stav[i]=0;
						break;
						case 1:	b[i].setImageResource(R.drawable.b1);
							stav[i]=1;
						break;
					}		
					}
					break;
				case 1:
					int n = hodnota;
					int zvysok = 0;
					for(int i = 0; i<=(b.length-1); i++){				
						if(n == 1){
							b[i].setImageResource(R.drawable.b1);
							stav[i]=1;
						}else if(n>0){
							zvysok = n%2;					
							if(zvysok == 0){
								b[i].setImageResource(R.drawable.b0);
								stav[i]=0;
								Log.d("stav"+i, ""+stav[i]);
								Log.d("zvysok"+i, ""+zvysok);
							}else{
								b[i].setImageResource(R.drawable.b1);
								stav[i]=1;
								Log.d("stav"+i, ""+stav[i]);
								Log.d("zvysok"+i, ""+zvysok);
							}
						}else{
							b[i].setImageResource(R.drawable.b0);
							stav[i]=0;
							Log.d("stav"+i, ""+stav[i]);	
						}
						n=n/2;
					}
					break;
			}
			for(int i = 0;i<=(stav.length-1);i++){
				h= h+(stav[i]*(int)Math.pow(2, i));
				
			}
			Log.d("Hodnota stavov", ""+h);
			pocetZadani--;
			time=15;
	    	cas.setText("Zostáva "+time+" sekúnd."); 
			}
	}
	public void prevod(int n){
		int zvysok =0;
		for(int i = 0; i<=(stav.length-1); i++){
			if(n == 1){
				stav[i]=1;
			}else if(n>0){
				zvysok = n%2;					
				if(zvysok == 0){
					stav[i]=0;
					Log.d("stav"+i, ""+stav[i]);
					Log.d("zvysok"+i, ""+zvysok);
				}else{
					stav[i]=1;
					Log.d("stav"+i, ""+stav[i]);
					Log.d("zvysok"+i, ""+zvysok);
				}
			}else{
				stav[i]=0;
				Log.d("stav"+i, ""+stav[i]);	
			}
			n=n/2;
		}
	}
	public void finish(){	
    	cas.setVisibility(View.INVISIBLE);
    	handler.removeCallbacks(runnable);
		if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
			sensorManager.unregisterListener(this,
		            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
			}else{
				//zariadenie nemá akcelometer
			}
		Intent i = new Intent(this, VyhodnotenieHry3.class);
		i.putExtra("spravne", spravne);
		i.putExtra("nespravne", nespravne);
		i.putExtra("totalTime", totalTime);
		this.startActivity(i);
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		 if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

		        float[] values = event.values;

		        // zmena pohybu
		        float x = values[0];
		        float y = values[1];
		        float z = values[2];

		        Log.d("Shakes","X: "+x+"  Y: "+y+"  Z: "+z);

		        long actualTime = System.currentTimeMillis();
		        if ((actualTime - lastUpdate) > 700) 
		        {
		            if(Math.round(x+0.4)>2.0000){
			            Log.d("sensor", "=====LEFT_Landscape====");
			            odpovedal = true;
						skontrolovat(true,odpovedal);
			        }
			        else if(Math.round(x+0.4)<-2.0000){
			            Log.d("sensor", "=====RIGHT_lanscape====");
			            odpovedal = true;
						skontrolovat(false,odpovedal);
			        }
		        

		/*        float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

		        if (speed > SHAKE_THRESHOLD) {
		            //Log.d("sensor", "shake detected w/ speed: " + speed);
		            //Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
		        }
		        last_x = x;
		        last_y = y;
		        last_z = z;
		        */
		        }
	        }
		
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
			sensorManager.unregisterListener(this,
		            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
			}else{
				//zariadenie nemá akcelometer
			}
	}
	
}
