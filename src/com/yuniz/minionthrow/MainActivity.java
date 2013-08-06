package com.yuniz.minionthrow;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;

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
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.Menu;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	
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
	
	private int previousX = 0;
	
	private String curPosistion = "0";
	private String curPage = "1";
	private String breakRecords = "0";
	
	private RelativeLayout gameMenu;
	private RelativeLayout gameStage1;
	private RelativeLayout gameStage_human;
	private RelativeLayout gameIntro;
	private RelativeLayout gameOver;
	private RelativeLayout hallOfFameBoard;
	
	private ImageView logo;
	private ImageView playBtn;
	private ImageView quitBtn;
	private ImageView startBtn;
	private ImageView rePlayBtn;
	private ImageView submitBtn;
	private ImageView playBtn2;
	private ImageView openHallOfFameBtn;
	private ImageView quit2Btn;
	
	private ImageView boardNextBtn;
	private ImageView boardPrevBtn;
	
	private ImageView human;
	
	private ImageView titan1;
	private ImageView titan2;
	private ImageView titan3;
	
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
	
	
	MediaPlayer bgMusic;
	MediaPlayer clickEffect;
	
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
		
		logo = (ImageView) findViewById(R.id.logo);
		playBtn = (ImageView) findViewById(R.id.playBtn);
		quitBtn = (ImageView) findViewById(R.id.quitBtn);
		startBtn = (ImageView) findViewById(R.id.startBtn);
		rePlayBtn = (ImageView) findViewById(R.id.rePlayBtn);
		submitBtn = (ImageView) findViewById(R.id.submitBtn);
		playBtn2 = (ImageView) findViewById(R.id.playBtn2);
		openHallOfFameBtn = (ImageView) findViewById(R.id.openHallOfFameBtn);
		quit2Btn = (ImageView) findViewById(R.id.quit2Btn);
		
		boardNextBtn = (ImageView) findViewById(R.id.boardNextBtn);
		boardPrevBtn = (ImageView) findViewById(R.id.boardPrevBtn);
		
		human = (ImageView) findViewById(R.id.human);
		
		titan1 = (ImageView) findViewById(R.id.titan1);
		titan2 = (ImageView) findViewById(R.id.titan2);
		titan3 = (ImageView) findViewById(R.id.titan3);
		
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
		
		try 
		{
		    InputStream ims = getAssets().open("menu_bg.jpg");
		    Drawable d = Drawable.createFromStream(ims, null);
		    
		    InputStream ims2 = getAssets().open("stage_1.jpg");
		    Drawable d2 = Drawable.createFromStream(ims2, null);
		    
		    InputStream ims3 = getAssets().open("infors.jpg");
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
		    
		    InputStream ims1 = getAssets().open("logo.png");
		    Drawable d1 = Drawable.createFromStream(ims1, null);
		    logo.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("playBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
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
		    
		    ims1 = getAssets().open("g" + generateNumber(1,4) + "_" + generateNumber(1,2) + ".png");
		    d1 = Drawable.createFromStream(ims1, null);
		    titan1.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("g" + generateNumber(1,4) + "_" + generateNumber(1,2) + ".png");
		    d1 = Drawable.createFromStream(ims1, null);
		    titan2.setImageDrawable(d1);
    
		    ims1 = getAssets().open("g" + generateNumber(1,4) + "_" + generateNumber(1,2) + ".png");
		    d1 = Drawable.createFromStream(ims1, null);
		    titan3.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("human_1.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    human.setImageDrawable(d1);
		}
		catch(IOException ex) 
		{
		    return;
		}
		
		//----------auto Adjust UI Elements size----------
		if(smallScreen == true){
			human.setAdjustViewBounds(true);
		}
		
		setNewWidth = screenWidth * 0.7;
		setNewHeight = screenHeight * 0.4;
		logo.setMinimumHeight((int)setNewHeight);
		logo.setMaxHeight((int)setNewHeight);
		logo.setMinimumWidth((int)setNewWidth);
		logo.setMaxWidth((int)setNewWidth);
		
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
		
		setNewWidth = screenWidth * 0.07;
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
		
		setNewWidth = screenWidth * 0.5;
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
		
		titan2.setMinimumHeight((int)setNewHeight);
		titan2.setMaxHeight((int)setNewHeight);
		titan2.setMinimumWidth((int)setNewWidth);
		titan2.setMaxWidth((int)setNewWidth);
		
		titan3.setMinimumHeight((int)setNewHeight);
		titan3.setMaxHeight((int)setNewHeight);
		titan3.setMinimumWidth((int)setNewWidth);
		titan3.setMaxWidth((int)setNewWidth);
		
		//logo.setAdjustViewBounds(true);
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
		
		titan1.setAdjustViewBounds(true);
		titan2.setAdjustViewBounds(true);
		titan3.setAdjustViewBounds(true);
		
		//logo.setScaleType( ImageView.ScaleType.FIT_CENTER);
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
		
		human.setScaleType( ImageView.ScaleType.FIT_CENTER);
		
		titan1.setScaleType( ImageView.ScaleType.FIT_CENTER);
		titan2.setScaleType( ImageView.ScaleType.FIT_CENTER);
		titan3.setScaleType( ImageView.ScaleType.FIT_CENTER);
		//----------auto Adjust UI Elements size----------
	}

	public int generateNumber(int startFrom, int stopAt){
		Random r = new Random();
		int i1=r.nextInt(stopAt-startFrom) + startFrom;
		return i1;
	}
	
	public void quitBtn(View v) {
		finish();
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

}
