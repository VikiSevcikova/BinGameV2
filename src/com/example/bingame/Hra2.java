package com.example.bingame;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.MotionEvent;
import android.view.View;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Hra2 extends Activity {	

	private AlertDialog.Builder dialog;
	public int ScreenWidth;
	public int Screenheight;
	public Hra2 game;
	private ImageView b1;
	private ImageView b2;
	private ImageView b4;
	private ImageView b8;
	private ImageView b16;
	private int stav1;
	private int stav2;
	private int stav4;
	private int stav8;
	private int stav16;
	ImageView odpImg,info;
	TextView c;
	Button btnKontrola;
	Random r = new Random();
	private int hodnota;
	MediaPlayer zvukS;
	MediaPlayer zvukN;
	ImageView zvukIcon;
	boolean zvukStav;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hra2);
		zvukS = MediaPlayer.create(this,R.raw.correct);
		zvukN = MediaPlayer.create(this, R.raw.wrong);
		zvukIcon = (ImageView) findViewById(R.id.zvukIcon);
		zvukStav = true;
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		Typeface txt = Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf");
		stav1 = 0;stav2 = 0;stav4 = 0;stav8 = 0;stav16 = 0;
		odpImg = (ImageView) findViewById(R.id.odpoved);
		info = (ImageView) findViewById(R.id.info);
		odpImg.setVisibility(View.INVISIBLE);
		c = (TextView) findViewById(R.id.cislo);
		btnKontrola = (Button) findViewById(R.id.kontrola);
		btnKontrola.setTypeface(txt);
		c.setTypeface(txt);
		hodnota = (r.nextInt(31) + 1);
		c.setText(Integer.toString(hodnota));
		
		b1 = (ImageView) findViewById(R.id.bit1);		
			b1.setOnClickListener(new View.OnClickListener() {					
				public void onClick(View v) {					
		    		switch(stav1){
		    			case 0: b1.setImageResource(R.drawable.b1);
		    				stav1=1;
		    				break;
		    			case 1: b1.setImageResource(R.drawable.b0);
		    				stav1=0;
		    				break;
		    		} 
				}			
		});    
			
		b2 = (ImageView) findViewById(R.id.bit2);
		b2.setOnClickListener(new View.OnClickListener() {					
			public void onClick(View v) {					
	    		switch(stav2){
	    			case 0: b2.setImageResource(R.drawable.b1);
	    				stav2=1;
	    				break;
	    			case 1: b2.setImageResource(R.drawable.b0);
	    				stav2=0;
	    				break;
	    		} 
			}			
		});   
		b4 = (ImageView) findViewById(R.id.bit4);
		b4.setOnClickListener(new View.OnClickListener() {					
			public void onClick(View v) {					
	    		switch(stav4){
	    			case 0: b4.setImageResource(R.drawable.b1);
	    				stav4=1;
	    				break;
	    			case 1: b4.setImageResource(R.drawable.b0);
	    				stav4=0;
	    				break;
	    		} 
			}			
		});   
		b8 = (ImageView) findViewById(R.id.bit8);
		b8.setOnClickListener(new View.OnClickListener() {					
			public void onClick(View v) {					
	    		switch(stav8){
	    			case 0: b8.setImageResource(R.drawable.b1);
	    				stav8=1;
	    				break;
	    			case 1: b8.setImageResource(R.drawable.b0);
	    				stav8=0;
	    				break;
	    		} 
			}			
		});   
		
		b16 = (ImageView) findViewById(R.id.bit16);
		b16.setOnClickListener(new View.OnClickListener() {					
			public void onClick(View v) {					
	    		switch(stav16){
	    			case 0: b16.setImageResource(R.drawable.b1);
	    				stav16=1;
	    				break;
	    			case 1: b16.setImageResource(R.drawable.b0);
	    				stav16=0;
	    				break;
	    		} 
			}			
		});   
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
					AlertDialog alertDialog = new AlertDialog.Builder(Hra2.this).create();
					alertDialog.setTitle("Informácia");
					alertDialog.setIcon(R.drawable.info);
					alertDialog.setMessage("Kliknutím na kartu ju premeníte na 1 alebo 0.");
					alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int which) {
					            dialog.dismiss();
					        }
					    });
					alertDialog.show();
				}
			});  
	}
	public void nastav(){
		hodnota = (r.nextInt(31) + 1);
		c.setText(Integer.toString(hodnota));
		stav1 = 0;stav2 = 0;stav4 = 0;stav8 = 0;stav16 = 0;
		b1.setImageResource(R.drawable.b0);
		b2.setImageResource(R.drawable.b0);
		b4.setImageResource(R.drawable.b0);
		b8.setImageResource(R.drawable.b0);
		b16.setImageResource(R.drawable.b0);
		odpImg.setVisibility(View.INVISIBLE);
	}
	public void skontrolovat(View v){
		new CountDownTimer(3000, 1000) {

		    public void onTick(long millisUntilFinished) {
		        if(hodnota == ((stav1*1)+(stav2*2)+(stav4*4)+(stav8*8)+(stav16*16))){ 	
		        	odpImg.setImageResource(R.drawable.right);
		        	odpImg.setVisibility(View.VISIBLE);
		        	if(zvukStav){
		        		zvukS.start();
		        	}
		        }else{
		        	odpImg.setImageResource(R.drawable.wrong);
		        	odpImg.setVisibility(View.VISIBLE);
		        	if(zvukStav){
		        		zvukN.start();
		        	}
		        }
		    }

		    public void onFinish() {
		    	if(hodnota == ((stav1*1)+(stav2*2)+(stav4*4)+(stav8*8)+(stav16*16))){
		    		odpImg.setVisibility(View.INVISIBLE);
		    		nastav();
		    	}else{
		    		odpImg.setVisibility(View.INVISIBLE);
		    	}
		    }

		}.start();

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
	
}
