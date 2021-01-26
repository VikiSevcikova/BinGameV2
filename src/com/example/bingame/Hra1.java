package com.example.bingame;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.view.Window;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Hra1 extends Activity {	
	private ImageView b1,b2,b4,b8,b16;
	private int klik;
	int klikK[] = new int [5];
	boolean spr = false;
	private Random r = new Random();
	private ImageView odpImg,info;
	private int hodnota;
	private ImageView jedna1,dva1,dva2,styri1,styri2,styri3,styri4,osem1,osem2,osem3,
				osem4,osem5,osem6,osem7,osem8,sestnast1,sestnast2,sestnast3,sestnast4,
				sestnast5,sestnast6,sestnast7,sestnast8,sestnast9,sestnast10,sestnast11,
				sestnast12,sestnast13,sestnast14,sestnast15,sestnast16;
	private ImageView[] kartaDva=new ImageView[2];
	private ImageView[] kartaStyri=new ImageView[4];
	private ImageView[] kartaOsem = new ImageView[8];
	private ImageView[] kartaSestnast = new ImageView[16];
	private TextView pocet_stvorcov,odpovedTxt,txtStvorce;
	private LinearLayout layoutStvorce;
	private RelativeLayout stvorec;
	private ImageView stvorce;
	private Button btnKontrola;
	MediaPlayer zvukS;
	MediaPlayer zvukN;
	ImageView zvukIcon;
	boolean zvukStav;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hra1);
		zvukS = MediaPlayer.create(this,R.raw.correct);
		zvukN = MediaPlayer.create(this, R.raw.wrong);
		zvukIcon = (ImageView) findViewById(R.id.zvukIcon);
		zvukStav = true;
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		Typeface txt = Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf");
		btnKontrola = (Button) findViewById(R.id.kontrola);
		odpovedTxt = (TextView) findViewById(R.id.odpovedTxt);
		txtStvorce = (TextView) findViewById(R.id.textView2);
		pocet_stvorcov =(TextView)findViewById(R.id.pocet);
		odpImg = (ImageView) findViewById(R.id.odpoved);
		info = (ImageView) findViewById(R.id.info);	
		stvorec = (RelativeLayout) findViewById(R.id.stvorceOznam);
		layoutStvorce = (LinearLayout) findViewById(R.id.stvorce);
		btnKontrola.setVisibility(View.VISIBLE);
		btnKontrola.setTypeface(txt);
		pocet_stvorcov.setTypeface(txt);
		odpovedTxt.setTypeface(txt);
		txtStvorce.setTypeface(txt);
		klik = 0;		
		jedna1 = (ImageView) findViewById(R.id.jedna1);
			jedna1.setBackgroundResource(R.drawable.vypln0);
			jedna1.setClickable(true);

		
		dva1 = (ImageView) findViewById(R.id.dva1);
		dva2 = (ImageView) findViewById(R.id.dva2);
		kartaDva[0] = dva1;
		kartaDva[1] = dva2;
		for(int i = 0; i<=(kartaDva.length-1); i++){
			kartaDva[i].setBackgroundResource(R.drawable.vypln0);
			kartaDva[i].setClickable(true);
		}
		
		styri1 = (ImageView) findViewById(R.id.styri1);
		styri2 = (ImageView) findViewById(R.id.styri2);
		styri3 = (ImageView) findViewById(R.id.styri3);
		styri4 = (ImageView) findViewById(R.id.styri4);		
		kartaStyri[0] = styri1;kartaStyri[2] = styri3;
		kartaStyri[1] = styri2;kartaStyri[3] = styri4;
		for(int i = 0; i<=(kartaStyri.length-1); i++){
			kartaStyri[i].setBackgroundResource(R.drawable.vypln0);
			kartaStyri[i].setClickable(true);
		}
		
		osem1 = (ImageView) findViewById(R.id.osem1);
		osem2 = (ImageView) findViewById(R.id.osem2);
		osem3 = (ImageView) findViewById(R.id.osem3);
		osem4 = (ImageView) findViewById(R.id.osem4);
		osem5 = (ImageView) findViewById(R.id.osem5);
		osem6 = (ImageView) findViewById(R.id.osem6);
		osem7 = (ImageView) findViewById(R.id.osem7);
		osem8 = (ImageView) findViewById(R.id.osem8);
		
		kartaOsem[0] = osem1;kartaOsem[4] = osem5;
		kartaOsem[1] = osem2;kartaOsem[5] = osem6;
		kartaOsem[2] = osem3;kartaOsem[6] = osem7;
		kartaOsem[3] = osem4;kartaOsem[7] = osem8;
		for(int i = 0; i<=(kartaOsem.length-1); i++){
			kartaOsem[i].setBackgroundResource(R.drawable.vypln0);
			kartaOsem[i].setClickable(true);
		}
		
		sestnast1 = (ImageView) findViewById(R.id.sestnast1);
		sestnast2 = (ImageView) findViewById(R.id.sestnast2);
		sestnast3 = (ImageView) findViewById(R.id.sestnast3);
		sestnast4 = (ImageView) findViewById(R.id.sestnast4);
		sestnast5 = (ImageView) findViewById(R.id.sestnast5);
		sestnast6 = (ImageView) findViewById(R.id.sestnast6);
		sestnast7 = (ImageView) findViewById(R.id.sestnast7);
		sestnast8 = (ImageView) findViewById(R.id.sestnast8);
		sestnast9 = (ImageView) findViewById(R.id.sestnast9);
		sestnast10 = (ImageView) findViewById(R.id.sestnast10);
		sestnast11 = (ImageView) findViewById(R.id.sestnast11);
		sestnast12 = (ImageView) findViewById(R.id.sestnast12);
		sestnast13 = (ImageView) findViewById(R.id.sestnast13);
		sestnast14 = (ImageView) findViewById(R.id.sestnast14);
		sestnast15 = (ImageView) findViewById(R.id.sestnast15);
		sestnast16 = (ImageView) findViewById(R.id.sestnast16);
		kartaSestnast[0] = sestnast1;kartaSestnast[8] = sestnast9;
		kartaSestnast[1] = sestnast2;kartaSestnast[9] = sestnast10;
		kartaSestnast[2] = sestnast3;kartaSestnast[10] = sestnast11;
		kartaSestnast[3] = sestnast4;kartaSestnast[11] = sestnast12;
		kartaSestnast[4] = sestnast5;kartaSestnast[12] = sestnast13;
		kartaSestnast[5] = sestnast6;kartaSestnast[13] = sestnast14;
		kartaSestnast[6] = sestnast7;kartaSestnast[14] = sestnast15;
		kartaSestnast[7] = sestnast8;kartaSestnast[15] = sestnast16;
		
		for(int i = 0; i<=(kartaSestnast.length-1); i++){
			kartaSestnast[i].setBackgroundResource(R.drawable.vypln0);
			kartaSestnast[i].setClickable(true);
		}			
		odpovedTxt.setVisibility(View.INVISIBLE);
		odpImg.setVisibility(View.INVISIBLE);
		hodnota = (r.nextInt(31) + 1);
		pocet_stvorcov.setText(Integer.toString(hodnota));
		
		for(int i = 0; i< hodnota;i++){
	   		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(40, 40);
	   		stvorce = new ImageView(this);
	   		stvorce.setBackgroundResource(R.drawable.vypln8);
	   		stvorce.setLayoutParams(params);
			layoutStvorce.addView(stvorce);
		}
		
		nastavitKlik(jedna1,0,1);	
		
		for (int i = 0; i<=(kartaDva.length-1); i++){
			nastavitKlik(kartaDva[i],1,2);	
		}
		for (int i = 0; i<=(kartaStyri.length-1); i++){
			nastavitKlik(kartaStyri[i],2,4);	
		}
		for (int i = 0; i<=(kartaOsem.length-1); i++){
			nastavitKlik(kartaOsem[i],3,8);	
		}
		for (int i = 0; i<=(kartaSestnast.length-1); i++){
			nastavitKlik(kartaSestnast[i],4,16);	
		}
		b1 = (ImageView) findViewById(R.id.bit1);
		b1.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {	
				jedna1.setBackgroundResource(R.drawable.vypln0);
				jedna1.setClickable(true);
				klik = klik - klikK[0];
				klikK[0] = 0;				
				pocet_stvorcov.setText(Integer.toString(hodnota-klik));
				pridatStvorce();
				return false;
			}
		});
		b2 = (ImageView) findViewById(R.id.bit2);
		b2.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				klik = klik - klikK[1];
				nastavStvorce(kartaDva,1);
				return false;
			}
		});
		b4 = (ImageView) findViewById(R.id.bit4);
		b4.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				klik = klik - klikK[2];
				nastavStvorce(kartaStyri,2);
				return false;
			}
		});
		b8 = (ImageView) findViewById(R.id.bit8);
		b8.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				klik = klik - klikK[3];
				nastavStvorce(kartaOsem,3);		
				return false;
			}
		});
		b16 = (ImageView) findViewById(R.id.bit16);
		b16.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {	
				klik = klik - klikK[4];				
				nastavStvorce(kartaSestnast,4);
				return false;
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
					AlertDialog alertDialog = new AlertDialog.Builder(Hra1.this).create();
					alertDialog.setTitle("Informácia");
					alertDialog.setIcon(R.drawable.info);
					alertDialog.setMessage("Klikaním na karty vyplníte štvorce a podržaním ich vymažete.");
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
	public void skontrolovat(View v){
		int klikS =0;
		for(int i =0; i<=(klikK.length-1);i++){
			klikS=klikS+klikK[i];
		}
		if((hodnota-klik) == 0){
			if((klikS == hodnota) && 
				((klikK[0]==0)||(klikK[0]==1))&&((klikK[1]==0)||(klikK[1]==2))&&
				((klikK[2]==0)||(klikK[2]==4))&&((klikK[3]==0)||(klikK[3]==8))&&
				((klikK[4]==0)||(klikK[4]==16))){
				spr = true;
				if(klikK[0]==0){
					b1.setImageResource(R.drawable.b0);	
				}else{
					b1.setImageResource(R.drawable.b1);
				}
				if(klikK[1]==0){
					b2.setImageResource(R.drawable.b0);
				}else{
					b2.setImageResource(R.drawable.b1);	
				}
				if(klikK[2]==0){
					b4.setImageResource(R.drawable.b0);
				}else{
					b4.setImageResource(R.drawable.b1);	
				}
				if(klikK[3]==0){
					b8.setImageResource(R.drawable.b0);
				}else{
					b8.setImageResource(R.drawable.b1);	
				}
				if(klikK[4]==0){
					b16.setImageResource(R.drawable.b0);
				}else{
					b16.setImageResource(R.drawable.b1);	
				}
				jedna1.setBackgroundResource(R.drawable.vypln0);
				jedna1.setClickable(true);
				klikK[0] = 0;
				nastavStvorce(kartaDva, 1);		
				nastavStvorce(kartaStyri, 2);
				nastavStvorce(kartaOsem, 3);		    		
				nastavStvorce(kartaSestnast, 4);
			}else{
			spr = false;
			}
		}else{
			spr = false;
		}
		zobraz();
	}
	
	public void zobraz(){ //zobrazenie odpovede, po uplynutí èasu samé zmizne 
		new CountDownTimer(4000, 1000) {

		    public void onTick(long millisUntilFinished) {
		        if(spr){
		        	btnKontrola.setVisibility(View.INVISIBLE);
		        	odpImg.setImageResource(R.drawable.right);
		        	odpImg.setVisibility(View.VISIBLE);
		        	odpovedTxt.setText("OK, v desiatkovej sústave je to "+hodnota);
		        	odpovedTxt.setVisibility(View.VISIBLE);		
		        	stvorec.setVisibility(View.INVISIBLE);
		        	if(zvukStav){
		        		zvukS.start();
		        	}
		        }else{
		        	odpImg.setImageResource(R.drawable.wrong);
		        	odpImg.setVisibility(View.VISIBLE);
		        	if((hodnota-klik) == 0){
		        		odpovedTxt.setText("Každá karta musí by vyplnená úplne alebo vôbec.");
		        	}else{
		        		odpovedTxt.setText("Neminuli ste všetky štvorce.");
		        	}
		        	odpovedTxt.setVisibility(View.VISIBLE);
		        	if(zvukStav){
		        		zvukN.start();
		        	}
		        }
		    }

		    public void onFinish() {
		    	if(spr){
		    		nastav();
		    		btnKontrola.setVisibility(View.VISIBLE);		    		
		    	}else{
			    	odpImg.setVisibility(View.INVISIBLE);
			    	odpovedTxt.setVisibility(View.INVISIBLE);
		    	}
		    }

		}.start();
	}
	public void nastav(){ //nastavia sa karty do pôvodného stavu
		stvorec.setVisibility(View.VISIBLE);
		klik = 0;
		hodnota = (r.nextInt(31) + 1);
		pridatStvorce();
		pocet_stvorcov.setText(Integer.toString(hodnota));
		odpovedTxt.setVisibility(View.INVISIBLE);
		odpImg.setVisibility(View.INVISIBLE);
		b1.setImageResource(R.drawable.jedna0);
		b2.setImageResource(R.drawable.dva0);
		b4.setImageResource(R.drawable.styri0);
		b8.setImageResource(R.drawable.osem0);
		b16.setImageResource(R.drawable.sestnast0);
	}
	public void nastavitKlik(final ImageView img,final int k,final int b){	
		img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(hodnota>klik && klikK[k]<b){ //porovnáva sa èi hodnota štvorcov je väèšia ako hodnota všetkých kliknutí a zároveò hodnota kliknutí karty je menšia ako poèet štvorcov na karte
				img.setBackgroundResource(R.drawable.vypln8); //nastaví sa aký obrázok má by vo štvorci
				img.setClickable(false); //nastaví sa klikanie na false, aby sa nezvyšovalo klikanie
				klik = klik+1;
				klikK[k]=klikK[k]+1;
				pocet_stvorcov.setText(Integer.toString(hodnota-klik));
				layoutStvorce.removeViewAt(hodnota-klik);
				}
			}
		});  
	}
	public void nastavStvorce(ImageView[] iv,int k){
		for(int i = 0; i<=(iv.length-1); i++){
			iv[i].setBackgroundResource(R.drawable.vypln0);
			iv[i].setClickable(true);
			klikK[k] = 0;				
			pocet_stvorcov.setText(Integer.toString(hodnota-klik));
			
		}
		pridatStvorce();
	}
	public void pridatStvorce(){
		for(int i = 0; i< hodnota-klik;i++){
			layoutStvorce.removeAllViews();
		}
		for(int i = 0; i< hodnota-klik;i++){
	   		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(40, 40);
	   		stvorce = new ImageView(this);
	   		stvorce.setBackgroundResource(R.drawable.vypln8);
	   		stvorce.setLayoutParams(params);
			layoutStvorce.addView(stvorce);
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
	
	/*public void zmenakliknuti(int klik, int klikBitu, int hodnota){
		
	}*/
}
