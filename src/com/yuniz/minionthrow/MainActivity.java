package com.yuniz.minionthrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yuniz.minionthrow.R;

import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnGestureListener{
	
	public int screenWidth = 0;
	public int screenHeight = 0;

	public int scaleSize1 = 1;
	public int scaleSize2 = 1;
	public int scaleSize3 = 1;
	
	public int titanLocNow1 = 30;
	public int titanLocNow2 = 60;
	public int titanLocNow3 = 90;
	
	public int enemyTurn = 1;
	
	public int moveNowNumber = 0;
	
	public int humanPosition = 1;
	
	public int totalHits = 0;
	
	public int basketX,basketY;
	
	public boolean canMoveNow = true;
	
	private float previousX,previousY,curX,curY = 0;
	
	private String curPosistion = "0";
	private String curPage = "1";
	private String breakRecords = "0";
	
	private RelativeLayout gameMenu;
	private RelativeLayout gameStage1;
	private RelativeLayout gameStage_human;
	private RelativeLayout gameIntro;
	private RelativeLayout gameOver;
	private RelativeLayout hallOfFameBoard;
	
	private ImageView playBtn;
	private ImageView quitBtn;
	private ImageView startBtn;
	private ImageView rePlayBtn;
	private ImageView submitBtn;
	private ImageView playBtn2;
	private ImageView openHallOfFameBtn;
	private ImageView quit2Btn;
	
	private ImageView titanIcon;
	
	private ImageView boardNextBtn;
	private ImageView boardPrevBtn;
	
	private ImageView human;
	
	private ImageView titan1;
	
	private TextView gameScore;
	private TextView resultTxt;
	
	private EditText mynickName;
	
	private TextView txtNo1;
	private TextView txtNick1;
	private TextView txtScore1;
	private TextView txtNo2;
	private TextView txtNick2;
	private TextView txtScore2;
	private TextView txtNo3;
	private TextView txtNick3;
	private TextView txtScore3;
	private TextView txtNo4;
	private TextView txtNick4;
	private TextView txtScore4;
	private TextView txtNo5;
	private TextView txtNick5;
	private TextView txtScore5;
	private TextView txtNo6;
	private TextView txtNick6;
	private TextView txtScore6;
	private TextView txtNo7;
	private TextView txtNick7;
	private TextView txtScore7;
	private TextView txtNo8;
	private TextView txtNick8;
	private TextView txtScore8;
	private TextView txtNo9;
	private TextView txtNick9;
	private TextView txtScore9;
	private TextView txtNo10;
	private TextView txtNick10;
	private TextView txtScore10;
	
	private GestureDetector gDetector;
	
	MediaPlayer bgMusic,clickEffect,throwEffect;
	
	Timer t = new Timer();
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		int sdk = android.os.Build.VERSION.SDK_INT;
		
		//----------detect device setting and adapt environment
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		
		boolean smallScreen = false;
		try
		{ 
			display.getSize(size); 
			screenWidth = size.x; 
			screenHeight = size.y; 
			smallScreen = false;
		} 
		catch (NoSuchMethodError e) 
		{ 
			screenWidth = display.getWidth(); 
			screenHeight = display.getHeight(); 
			smallScreen = true;
		} 
	
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		//----------detect device setting and adapt environment

	    double setNewHeight = screenHeight;
		double setNewWidth = screenWidth;
		
		gameMenu = (RelativeLayout) findViewById(R.id.gameMenu);
		gameStage1 = (RelativeLayout) findViewById(R.id.gameStage1);
		gameStage_human = (RelativeLayout) findViewById(R.id.gameStage_human);
		gameIntro = (RelativeLayout) findViewById(R.id.gameIntro);
		gameOver = (RelativeLayout) findViewById(R.id.gameOver);
		hallOfFameBoard = (RelativeLayout) findViewById(R.id.hallOfFameBoard);
		
		playBtn = (ImageView) findViewById(R.id.playBtn);
		quitBtn = (ImageView) findViewById(R.id.quitBtn);
		startBtn = (ImageView) findViewById(R.id.startBtn);
		rePlayBtn = (ImageView) findViewById(R.id.rePlayBtn);
		submitBtn = (ImageView) findViewById(R.id.submitBtn);
		playBtn2 = (ImageView) findViewById(R.id.playBtn2);
		openHallOfFameBtn = (ImageView) findViewById(R.id.openHallOfFameBtn);
		quit2Btn = (ImageView) findViewById(R.id.quit2Btn);
		
		titanIcon = (ImageView) findViewById(R.id.titanIcon);
		
		boardNextBtn = (ImageView) findViewById(R.id.boardNextBtn);
		boardPrevBtn = (ImageView) findViewById(R.id.boardPrevBtn);
		
		human = (ImageView) findViewById(R.id.human);
		
		titan1 = (ImageView) findViewById(R.id.titan1);
		
		gameScore = (TextView) findViewById(R.id.gameScore);
		resultTxt = (TextView) findViewById(R.id.resultTxt);
		mynickName = (EditText) findViewById(R.id.mynickName);
		
		txtNo1 = (TextView) findViewById(R.id.txtNo1);
		txtNick1 = (TextView) findViewById(R.id.txtNick1);
		txtScore1 = (TextView) findViewById(R.id.txtScore1);
		txtNo2 = (TextView) findViewById(R.id.txtNo2);
		txtNick2 = (TextView) findViewById(R.id.txtNick2);
		txtScore2 = (TextView) findViewById(R.id.txtScore2);
		txtNo3 = (TextView) findViewById(R.id.txtNo3);
		txtNick3 = (TextView) findViewById(R.id.txtNick3);
		txtScore3 = (TextView) findViewById(R.id.txtScore3);
		txtNo4 = (TextView) findViewById(R.id.txtNo4);
		txtNick4 = (TextView) findViewById(R.id.txtNick4);
		txtScore4 = (TextView) findViewById(R.id.txtScore4);
		txtNo5 = (TextView) findViewById(R.id.txtNo5);
		txtNick5 = (TextView) findViewById(R.id.txtNick5);
		txtScore5 = (TextView) findViewById(R.id.txtScore5);
		txtNo6 = (TextView) findViewById(R.id.txtNo6);
		txtNick6 = (TextView) findViewById(R.id.txtNick6);
		txtScore6 = (TextView) findViewById(R.id.txtScore6);
		txtNo7 = (TextView) findViewById(R.id.txtNo7);
		txtNick7 = (TextView) findViewById(R.id.txtNick7);
		txtScore7 = (TextView) findViewById(R.id.txtScore7);
		txtNo8 = (TextView) findViewById(R.id.txtNo8);
		txtNick8 = (TextView) findViewById(R.id.txtNick8);
		txtScore8 = (TextView) findViewById(R.id.txtScore8);
		txtNo9 = (TextView) findViewById(R.id.txtNo9);
		txtNick9 = (TextView) findViewById(R.id.txtNick9);
		txtScore9 = (TextView) findViewById(R.id.txtScore9);
		txtNo10 = (TextView) findViewById(R.id.txtNo10);
		txtNick10 = (TextView) findViewById(R.id.txtNick10);
		txtScore10 = (TextView) findViewById(R.id.txtScore10);
		
		bgMusic  = new MediaPlayer();
		clickEffect  = new MediaPlayer();
		throwEffect  = new MediaPlayer();
		
		try 
		{
		    InputStream ims = getAssets().open("menu.jpg");
		    Drawable d = Drawable.createFromStream(ims, null);
		    
		    InputStream ims2 = getAssets().open("stage_1.jpg");
		    Drawable d2 = Drawable.createFromStream(ims2, null);
		    
		    InputStream ims3 = getAssets().open("intro.jpg");
		    Drawable d3 = Drawable.createFromStream(ims3, null);
		    
		    InputStream ims4 = getAssets().open("gameover.jpg");
		    Drawable d4 = Drawable.createFromStream(ims4, null);

		    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
		    	gameMenu.setBackgroundDrawable(d);
		    	gameStage1.setBackgroundDrawable(d2);
		    	gameIntro.setBackgroundDrawable(d3);
		    	gameOver.setBackgroundDrawable(d4);
		    	hallOfFameBoard.setBackgroundDrawable(d4);
		    } else {
		    	gameMenu.setBackground(d);
		    	gameStage1.setBackground(d2);
		    	gameIntro.setBackground(d3);
		    	gameOver.setBackground(d4);
		    	hallOfFameBoard.setBackground(d4);
		    }
		    
		    InputStream ims1 = getAssets().open("playBtn.png");
		    Drawable d1 = Drawable.createFromStream(ims1, null);
		    playBtn.setImageDrawable(d1);
		    playBtn2.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("quitBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    quitBtn.setImageDrawable(d1);
		    quit2Btn.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("startBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    startBtn.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("replayBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    rePlayBtn.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("submitBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    submitBtn.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("halloffameBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    openHallOfFameBtn.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("boardNextBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    boardNextBtn.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("boardPrevBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    boardPrevBtn.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("dustbin.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    titan1.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("minion_7.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    human.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("dustbin.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    titanIcon.setImageDrawable(d1);
		}
		catch(IOException ex) 
		{
		    return;
		}
		
		//----------auto Adjust UI Elements size----------
		if(smallScreen == true){
			human.setAdjustViewBounds(true);
		}
		
		setNewWidth = screenWidth * 0.35;
		setNewHeight = screenHeight * 0.2;
		playBtn.setMinimumHeight((int)setNewHeight);
		playBtn.setMaxHeight((int)setNewHeight);
		playBtn.setMinimumWidth((int)setNewWidth);
		playBtn.setMaxWidth((int)setNewWidth);

		quitBtn.setMinimumHeight((int)setNewHeight);
		quitBtn.setMaxHeight((int)setNewHeight);
		quitBtn.setMinimumWidth((int)setNewWidth);
		quitBtn.setMaxWidth((int)setNewWidth);
		
		quit2Btn.setMinimumHeight((int)setNewHeight);
		quit2Btn.setMaxHeight((int)setNewHeight);
		quit2Btn.setMinimumWidth((int)setNewWidth);
		quit2Btn.setMaxWidth((int)setNewWidth);
		
		startBtn.setMinimumHeight((int)setNewHeight);
		startBtn.setMaxHeight((int)setNewHeight);
		startBtn.setMinimumWidth((int)setNewWidth);
		startBtn.setMaxWidth((int)setNewWidth);
		
		rePlayBtn.setMinimumHeight((int)setNewHeight);
		rePlayBtn.setMaxHeight((int)setNewHeight);
		rePlayBtn.setMinimumWidth((int)setNewWidth);
		rePlayBtn.setMaxWidth((int)setNewWidth);
		
		submitBtn.setMinimumHeight((int)setNewHeight);
		submitBtn.setMaxHeight((int)setNewHeight);
		submitBtn.setMinimumWidth((int)setNewWidth);
		submitBtn.setMaxWidth((int)setNewWidth);
		
		openHallOfFameBtn.setMinimumHeight((int)setNewHeight);
		openHallOfFameBtn.setMaxHeight((int)setNewHeight);
		openHallOfFameBtn.setMinimumWidth((int)setNewWidth);
		openHallOfFameBtn.setMaxWidth((int)setNewWidth);
		
		setNewWidth = screenWidth * 0.25;
		setNewHeight = screenHeight * 0.15;
		playBtn2.setMinimumHeight((int)setNewHeight);
		playBtn2.setMaxHeight((int)setNewHeight);
		playBtn2.setMinimumWidth((int)setNewWidth);
		playBtn2.setMaxWidth((int)setNewWidth);
		
		setNewWidth = screenWidth * 0.10;
		setNewHeight = screenHeight * 0.15;
		boardNextBtn.setMinimumHeight((int)setNewHeight);
		boardNextBtn.setMaxHeight((int)setNewHeight);
		boardNextBtn.setMinimumWidth((int)setNewWidth);
		boardNextBtn.setMaxWidth((int)setNewWidth);
		
		boardPrevBtn.setMinimumHeight((int)setNewHeight);
		boardPrevBtn.setMaxHeight((int)setNewHeight);
		boardPrevBtn.setMinimumWidth((int)setNewWidth);
		boardPrevBtn.setMaxWidth((int)setNewWidth);
		
		setNewWidth = screenWidth * 0.7;
		mynickName.setMinimumWidth((int)setNewWidth);
		mynickName.setMaxWidth((int)setNewWidth);
		
		setNewWidth = screenWidth * 0.2;
		setNewHeight = screenHeight * 0.4;
		human.setMinimumHeight((int)setNewHeight);
		human.setMaxHeight((int)setNewHeight);
		human.setMinimumWidth((int)setNewWidth);
		human.setMaxWidth((int)setNewWidth);
		
		setNewWidth = screenWidth * 0.1;
		setNewHeight = screenHeight * 0.2;
		titan1.setMinimumHeight((int)setNewHeight);
		titan1.setMaxHeight((int)setNewHeight);
		titan1.setMinimumWidth((int)setNewWidth);
		titan1.setMaxWidth((int)setNewWidth);
		
		titanIcon.setMinimumHeight((int)setNewHeight);
		titanIcon.setMaxHeight((int)setNewHeight);
		titanIcon.setMinimumWidth((int)setNewWidth);
		titanIcon.setMaxWidth((int)setNewWidth);

		playBtn.setAdjustViewBounds(true);
		quitBtn.setAdjustViewBounds(true);
		startBtn.setAdjustViewBounds(true);
		rePlayBtn.setAdjustViewBounds(true);
		submitBtn.setAdjustViewBounds(true);
		playBtn2.setAdjustViewBounds(true);
		openHallOfFameBtn.setAdjustViewBounds(true);
		quit2Btn.setAdjustViewBounds(true);
		
		boardNextBtn.setAdjustViewBounds(true);
		boardPrevBtn.setAdjustViewBounds(true);
		
		human.setAdjustViewBounds(true);
		titan1.setAdjustViewBounds(true);
		
		titanIcon.setAdjustViewBounds(true);
		
		playBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		quitBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		startBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		rePlayBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		submitBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		playBtn2.setScaleType( ImageView.ScaleType.FIT_CENTER);
		openHallOfFameBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		quit2Btn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		
		boardNextBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		boardPrevBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		
		human.setScaleType( ImageView.ScaleType.FIT_XY);
		
		titan1.setScaleType( ImageView.ScaleType.FIT_XY);
		
		titanIcon.setScaleType( ImageView.ScaleType.FIT_CENTER);
		//----------auto Adjust UI Elements size----------
		
		gDetector = new GestureDetector(this);
		
		initTitans(titan1,generateNumber(0,3));
	}

	public void initTitans(ImageView titanSelect, int locations){
		
		int curScaleSize = 1;
		
		double setNewWidth = screenWidth * 0.1;
		double setNewHeight = screenHeight * 0.2;
		
		switch(locations) {
		    case 0:
		        basketX = generateNumber(50,67);
		        basketY = generateNumber(65,75);
		        break;
		    case 1:
		    	basketX = generateNumber(43,70);
		        basketY = generateNumber(65,75);
		        break;
		    case 2:
		    	basketX = generateNumber(35,73);
		        basketY = generateNumber(75,85);
		        curScaleSize = 2;
		        
		        setNewWidth = screenWidth * 0.2;
				setNewHeight = screenHeight * 0.4;
		        
		        break;    
		    default:
		    	basketX = generateNumber(40,70);
		        basketY = generateNumber(70,80);
		}
		
		titan1.setMinimumHeight((int)setNewHeight);
		titan1.setMaxHeight((int)setNewHeight);
		titan1.setMinimumWidth((int)setNewWidth);
		titan1.setMaxWidth((int)setNewWidth);
		
		int arg0 = (screenWidth / 100) * basketX;
		int arg2 = (screenHeight / 100) * basketY;
		
		arg0 = arg0 - ( titanSelect.getWidth() / 2);
		arg2 = arg2 - ( titanSelect.getHeight() / 2);

		//Animation animationScale = new ScaleAnimation(curScaleSize, ( curScaleSize + 1 ), curScaleSize, ( curScaleSize + 1 ), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.9f);
    	//animationScale.setDuration(0);

    	Animation animationLoc = new TranslateAnimation(arg0, arg0,arg2, arg2);
    	animationLoc.setDuration(0);

		AnimationSet animSet = new AnimationSet(true);
		animSet.setFillAfter(true);
		//animSet.addAnimation(animationScale);
		animSet.addAnimation(animationLoc);
		
		titanSelect.startAnimation(animSet);
	}
	
	public void moveHuman(float arg0, float arg1){
		
		if(canMoveNow == false){
			return;
		}
		
		arg0 = arg0 - (human.getWidth() / 2);
		arg1 = arg1 + (human.getHeight() / 2);

		arg1 = arg1 - screenHeight;
	
		curX = arg0;
		curY = arg1;
		
		double moveThanLimit = screenHeight * 0.1;
		
		if((previousY - arg1) > (int)moveThanLimit && arg1 < screenHeight){
			canMoveNow = false;
			int curScaleSize = 1;
			
			/*if(arg0 > (screenWidth/2)){
				arg0 = arg0 - (screenWidth / 100 * 20);
			}else{
				arg0 = arg0 + (screenWidth / 100 * 20);
			}*/
			
			Animation animationScale = new ScaleAnimation(( curScaleSize + 1 ), (int)(curScaleSize * 0.5), ( curScaleSize + 1 ), (int)(curScaleSize * 0.5), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.9f);
	    	animationScale.setDuration(500);

	    	Animation animationLoc = new TranslateAnimation(previousX, arg0,arg1, arg1);
	    	animationLoc.setDuration(500);
	    	
	    	animationLoc.setAnimationListener(new AnimationListener() {

	            @Override
	            public void onAnimationStart(Animation animation) {
	            	// TODO Auto-generated method stub
	            }

	            @Override
	            public void onAnimationRepeat(Animation animation) {
	                // TODO Auto-generated method stub
	            }

	            @Override
	            public void onAnimationEnd(Animation animation) {
	            	/*double curBasketX = ( (double)basketX - titan1.getWidth() ) / 100 * screenWidth;
	    			double curBasketY = ( (double)basketY - titan1.getHeight() ) / 100 * screenHeight - screenHeight;

	    			double basketRatio = 0.5;
	    			double curBasketXA = (int)curBasketX - (titan1.getWidth() * basketRatio );
	    			double curBasketXB = (int)curBasketX + titan1.getWidth() + (titan1.getWidth() * basketRatio );
	    			
	    			double curBasketYA = (int)curBasketY - (titan1.getHeight() * basketRatio );
	    			double curBasketYB = (int)curBasketY + titan1.getHeight() + (titan1.getHeight() * basketRatio );*/
	            	
	            	double curXPercent = ((curX + (human.getWidth()/2))) * 100 / screenWidth;
	            	double curYPercent = 100 + ((curY - (human.getHeight()/2)) * 100 / screenHeight);
	            	
	            	double basketRatio = 10;
	            	double curBasketXA = basketX - basketRatio;
	    			double curBasketXB = basketX + basketRatio;
	    			
	    			double curBasketYA = basketY - 10 - basketRatio;
	    			double curBasketYB = basketY - 10;
	    			  						
//Log.v("debug",basketRatio + "||" + curX + "|" + curBasketXA + "|" + curBasketXB + "||" + curY + "|" + curBasketYA + "|" + curBasketYB);
Log.v("debug",titan1.getHeight() + "||" + basketRatio + "||" + curXPercent + "|" + curBasketXA + "|" + curBasketXB + "||" + curYPercent + "|" + curBasketYA + "|" + curBasketYB);  			
	    			//if( ( curX > (float)curBasketXA && curX < (float)curBasketXB ) && ( curY > (float)curBasketYA && curY < (float)curBasketYB ) ){
					if( ( curXPercent > (float)curBasketXA && curXPercent < (float)curBasketXB ) && ( curYPercent > (float)curBasketYA && curYPercent < (float)curBasketYB ) ){
						initTitans(titan1,generateNumber(0,3));
	    				
	    				monionThrowSound();
	    				
	    				totalHits++;
		    			gameScore.setText(totalHits + " X");
	    			}

	    			reFreshMinions();
	    			
	    			canMoveNow = true;
	    			
	    			int backToNormalPos = (screenWidth/2) - (human.getWidth()/2);
	    			Animation animation2 = new TranslateAnimation(backToNormalPos, backToNormalPos,0, 0);
	    			
	    			animation2.setDuration(0);
	    			animation2.setFillAfter(true);
	    			human.startAnimation(animation2);
	    			
	            }
	        });
	    	
			AnimationSet animSet = new AnimationSet(true);
			animSet.setFillAfter(true);
			animSet.addAnimation(animationScale);
			animSet.addAnimation(animationLoc);
			
			human.startAnimation(animSet);

		}else{
			Animation animation = new TranslateAnimation(previousX, arg0,0, 0);
			
			animation.setDuration(0);
			animation.setFillAfter(true);
			human.startAnimation(animation);
		}
		
		previousX = arg0;
		previousY = arg1;

		
	}
	
	public void reFreshMinions(){
		String minionGIF = "minion_" + generateNumber(1,7) + ".png";
		
		try 
		{
		    InputStream ims1 = getAssets().open(minionGIF);
		    Drawable d1 = Drawable.createFromStream(ims1, null);

		    human.setImageDrawable(d1);
		}
		catch(IOException ex) 
		{
		    return;
		}
	}
	
	public int generateNumber(int startFrom, int stopAt){
		Random r = new Random();
		int i1=r.nextInt(stopAt-startFrom) + startFrom;
		return i1;
	}
	
	public void loadBoard(JSONArray jsoncontacts){
		if(jsoncontacts.length() == 0 && Integer.parseInt(curPage) > 1){
			backToLastBoard();
			Toast.makeText(getApplicationContext(), "This is the last page of Hall Of Fame." , Toast.LENGTH_LONG).show();
		}else{

			for(int i=0;i < jsoncontacts.length();i++){						
				
	        	try {
					JSONObject e = jsoncontacts.getJSONObject(i);
					
					if(e.getString("no") != "0"){
						pushScoreToBoard((i+1), e.getString("no"), e.getString("n"), e.getString("s"));
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
			}
		}
	}
	
	public void pushScoreToBoard(int slotNo, String noNumber, String nickName, String scores){
		
		if(noNumber == "0"){
			return;
		}
		
		try {
			nickName = new String(nickName.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(slotNo == 1){
			txtNo1.setText(noNumber);
			txtNick1.setText(nickName);
			txtScore1.setText(scores);
		}else if(slotNo == 2){
			txtNo2.setText(noNumber);
			txtNick2.setText(nickName);
			txtScore2.setText(scores);
		}else if(slotNo == 3){
			txtNo3.setText(noNumber);
			txtNick3.setText(nickName);
			txtScore3.setText(scores);
		}else if(slotNo == 4){
			txtNo4.setText(noNumber);
			txtNick4.setText(nickName);
			txtScore4.setText(scores);
		}else if(slotNo == 5){
			txtNo5.setText(noNumber);
			txtNick5.setText(nickName);
			txtScore5.setText(scores);
		}else if(slotNo == 6){
			txtNo6.setText(noNumber);
			txtNick6.setText(nickName);
			txtScore6.setText(scores);
		}else if(slotNo == 7){
			txtNo7.setText(noNumber);
			txtNick7.setText(nickName);
			txtScore7.setText(scores);
		}else if(slotNo == 8){
			txtNo8.setText(noNumber);
			txtNick8.setText(nickName);
			txtScore8.setText(scores);
		}else if(slotNo == 9){
			txtNo9.setText(noNumber);
			txtNick9.setText(nickName);
			txtScore9.setText(scores);
		}else if(slotNo == 10){
			txtNo10.setText(noNumber);
			txtNick10.setText(nickName);
			txtScore10.setText(scores);
		}
	}
	
	public void gameScoreSubmitAPI(String nickname, int scores){
		try {
			nickname = URLEncoder.encode(nickname, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "http://www.yuniz.com/apps/aot/?mod=1&nickname=" + nickname + "&score=" + scores;
		//-------load JSON
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        //nameValuePairs.add(new BasicNameValuePair("convo_id", "4546db1fd1"));
        //nameValuePairs.add(new BasicNameValuePair("say", words));

		JSONObject json = getJSONfromURL(url, nameValuePairs);
		try {
			if(json == null){
				Toast.makeText(getApplicationContext(), "You need internet connection to continue." , Toast.LENGTH_LONG).show();
			}else{
				curPosistion = json.getString("curPosistion");
				curPage = json.getString("curPage");
				breakRecords = json.getString("breakRecords");
				
				loadBoard(json.getJSONArray("hallOfFame"));
				
				gameOver.setVisibility(View.INVISIBLE);
				hallOfFameBoard.setVisibility(View.VISIBLE);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//-------load JSON
	}
	
	public void gameScoresAPI(String pages){
		String url = "http://www.yuniz.com/apps/aot/?mod=2&page=" + pages;
		//-------load JSON
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        //nameValuePairs.add(new BasicNameValuePair("convo_id", "4546db1fd1"));
        //nameValuePairs.add(new BasicNameValuePair("say", words));
		
		JSONObject json = getJSONfromURL(url, nameValuePairs);
		try {
			if(json == null){
				Toast.makeText(getApplicationContext(), "You need internet connection to continue." , Toast.LENGTH_LONG).show();
			}else{
				loadBoard(json.getJSONArray("hallOfFame"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//-------load JSON
	}
	
	public static JSONObject getJSONfromURL(String url,List<NameValuePair> postDatas ){

		//initialize
		InputStream is = null;
		String result = "";
		JSONObject jArray = null;

		//http post
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			
	        httppost.setEntity(new UrlEncodedFormEntity(postDatas));
			
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		}catch(Exception e){
			Log.e("log_tag", "Error in http connection "+e.toString());
		}

		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();
		}catch(Exception e){
			Log.e("log_tag", "Error converting result "+e.toString());
		}

		//try parse the string to a JSON object
		try{
	        	jArray = new JSONObject(result);
		}catch(JSONException e){
			Log.e("log_tag", "Error parsing data "+e.toString());
		}

		return jArray;
	} 
	
	public void openHallOfFameBtn(View v) {
		buttonClicks();
		
		if(!isNetworkAvailable()){
			Toast.makeText(getApplicationContext(), "You need internet connection to open Hall Of Fame." , Toast.LENGTH_LONG).show();
		}else{
			totalHits = 0;
			gameScore.setText("0 X");
			
			gameMenu.setVisibility(View.INVISIBLE);
			hallOfFameBoard.setVisibility(View.VISIBLE);
			
			gameScoresAPI(curPage);
		}
	}
	
	public void boardNextBtn(View v) {
		buttonClicks();

		int nextPage = Integer.parseInt(curPage) + 1;
		
		curPage = Integer.toString(nextPage);
		
		gameScoresAPI(curPage);
	}
	
	public void backToLastBoard(){
		int nextPage = Integer.parseInt(curPage) - 1;
		
		if(nextPage<1){
			nextPage = 1;
		}
		
		curPage = Integer.toString(nextPage);
		
		//gameScoresAPI(curPage);
	}
	
	public void boardPrevBtn(View v) {
		buttonClicks();
		
		if(curPage == "1"){
			Toast.makeText(getApplicationContext(), "These are the TOP 10 players." , Toast.LENGTH_LONG).show();
		}else{

			int nextPage = Integer.parseInt(curPage) - 1;
			
			if(nextPage<1){
				nextPage = 1;
			}
			
			curPage = Integer.toString(nextPage);
			
			gameScoresAPI(curPage);
		}
	}
	
	public void quitBtn(View v) {
		finish();
	}
	
	public void gameOver(){
		t.cancel();
		
		resultTxt.setText(gameScore.getText());
		
		gameStage_human.setVisibility(View.INVISIBLE);
		gameStage1.setVisibility(View.INVISIBLE);
		gameOver.setVisibility(View.VISIBLE);
	}
	
	public void startBtn(View v) {
		buttonClicks();
		
		gameIntro.setVisibility(View.INVISIBLE);
		gameStage_human.setVisibility(View.VISIBLE);
		gameStage1.setVisibility(View.VISIBLE);
	}
	
	public void rePlayBtn(View v) {
		buttonClicks();
		
		totalHits = 0;
		gameScore.setText("0 X");
		
		gameOver.setVisibility(View.INVISIBLE);
		gameStage_human.setVisibility(View.VISIBLE);
		gameStage1.setVisibility(View.VISIBLE);
	}
	
	public void submitBtn(View v) {
		buttonClicks();
		
		if(!isNetworkAvailable()){
			Toast.makeText(getApplicationContext(), "You need internet connection to submit game score." , Toast.LENGTH_LONG).show();
		}else{
			if(mynickName.getText().toString().trim().length() == 0){
				Toast.makeText(getApplicationContext(), "Please type your nickname." , Toast.LENGTH_LONG).show();
			}else{
				gameScoreSubmitAPI(mynickName.getText().toString(), totalHits);
			}
		}
	}
	
	public void playBtn(View v) {
		buttonClicks();
		
		totalHits = 0;
		gameScore.setText("0 X");
		
		gameMenu.setVisibility(View.INVISIBLE);
		gameIntro.setVisibility(View.VISIBLE);
	}
	
	public void buttonClicks(){
		clickEffect.reset();
		clickEffect.release();
		clickEffect = null;
    	
		AssetFileDescriptor descriptor = null;
		try {
			descriptor = getAssets().openFd("button_sound.mp3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long start = descriptor.getStartOffset();
		long end = descriptor.getLength();
		
		clickEffect = new MediaPlayer();
		try {
			clickEffect.setDataSource(descriptor.getFileDescriptor(), start, end);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			descriptor.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			clickEffect.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickEffect.start();
	}
	
	public void monionThrowSound(){
		throwEffect.reset();
		throwEffect.release();
		throwEffect = null;
    	
		AssetFileDescriptor descriptor = null;
		try {
			descriptor = getAssets().openFd("throweffect.mp3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long start = descriptor.getStartOffset();
		long end = descriptor.getLength();
		
		throwEffect = new MediaPlayer();
		try {
			throwEffect.setDataSource(descriptor.getFileDescriptor(), start, end);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			descriptor.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			throwEffect.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throwEffect.start();
	}
	
	public void playBGMusic(String filename){
		bgMusic.reset();
		bgMusic.release();
		bgMusic = null;
    	
		AssetFileDescriptor descriptor = null;
		try {
			descriptor = getAssets().openFd(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long start = descriptor.getStartOffset();
		long end = descriptor.getLength();
		
		bgMusic = new MediaPlayer();
		try {
			bgMusic.setDataSource(descriptor.getFileDescriptor(), start, end);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			descriptor.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			bgMusic.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bgMusic.setVolume(0.4f,0.4f);
		
		bgMusic.setLooping(true);
		bgMusic.start();
	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public void openURL(View v) {
		Uri uri = Uri.parse("http://www.yuniz.com");
		 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		 startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
	 // TODO Auto-generated method stub
	 super.onDestroy();
	 
	 t.cancel();
	 
	 bgMusic.stop();
	 bgMusic.release();
	 
	 clickEffect.stop();
	 clickEffect.release();
	}
	
	@Override
	protected void onPause() {
		super.onPause();

		if(bgMusic.isPlaying()){
			bgMusic.pause();
		}
	}
	
	protected void onResume() {
		try {
			bgMusic.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!bgMusic.isPlaying()){
			bgMusic.start();
		}
		
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent start, MotionEvent finish, float xVelocity, float yVelocity) {
		// TODO Auto-generated method stub
        /*if (start.getRawY() < finish.getRawY()) {
		    Log.v("DEBUG","Go DOWN(" + start.getRawY() + "|" + finish.getRawY() + ")");
		} else {
			Log.v("DEBUG","Go UP(" + start.getRawY() + "|" + finish.getRawY() + ")");
		}
        
        if (start.getRawX() < finish.getRawX()) {
		    Log.v("DEBUG","Go LEFT(" + start.getRawX() + "|" + finish.getRawX() + ")");
		} else {
			Log.v("DEBUG","Go RIGHT(" + start.getRawX() + "|" + finish.getRawX() + ")");
		}*/
		
        //moveHuman(finish.getRawX());
        
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		 moveHuman(arg1.getRawX(),arg1.getRawY());	 
		
		return true;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override

	public boolean onTouchEvent(MotionEvent me) {

		return gDetector.onTouchEvent(me);

	}

}
