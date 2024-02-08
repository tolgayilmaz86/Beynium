package tr.com.reformtek.beynium.scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import tr.com.reformtek.beynium.MainActivity;
import tr.com.reformtek.beynium.util.ResourcesManager;
import tr.com.reformtek.beynium.util.SceneManager;
import tr.com.reformtek.beynium.util.TimeCounter;

public class InfoSectionScene extends HUD
{
	private static final InfoSectionScene INSTANCE = new InfoSectionScene();

	public 	TimerHandler timerHandler;
	private Text info, scoreTextHundreds, scoreTextTens, scoreTextOnes, scoreInfoMax, scoreInfoLast;
	private TimeCounter t1,t10;
	private int COUNT_ONES, COUNT_TENS, currentScore, highScore;

	private boolean timeisup, isTimeDisabled;
	
	public Sprite finishFlagSprite, scoreTable, backgroundSprite, congratulations;
	public String scoreHundreds, scoreTens, scoreOnes, initialMaxScore, initialCurrentScore;
	
	private InfoSectionScene(){
		timeisup 			= false;
		isTimeDisabled 		= false;
		scoreHundreds		= scoreTens = scoreOnes = "0";
		initialMaxScore 	= "En Yüksek Puan: ";
		initialCurrentScore = "PUANIN: ";

		congratulations 	= new Sprite(420, 350,	ResourcesManager.getInstance().texCongratulations, ResourcesManager.getInstance().vbom);
    	scoreTable 			= new Sprite(926, 90,	ResourcesManager.getInstance().texScoreTable, 	   ResourcesManager.getInstance().vbom);
    	finishFlagSprite 	= new Sprite(460, 470, 	ResourcesManager.getInstance().finishFlag,	 	   ResourcesManager.getInstance().vbom);
    	
    	backgroundSprite 	= new Sprite(MainActivity.SCREEN_WIDTH - ResourcesManager.getInstance().info_region.getWidth()/2, 
    									 MainActivity.SCREEN_HEIGHT/2, ResourcesManager.getInstance().info_region,  
    									 ResourcesManager.getInstance().vbom);
    	info 				= new Text ( 824, 550, 	ResourcesManager.getInstance().font, "BEYNiUM", 
    										new TextOptions(HorizontalAlign.LEFT), ResourcesManager.getInstance().vbom);
    	scoreTextHundreds 	= new Text ( 880, 96, 	ResourcesManager.getInstance().fontDigital, scoreHundreds, 
											new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
    	scoreTextTens 		= new Text ( 930, 96, 	ResourcesManager.getInstance().fontDigital, scoreTens, 
    										new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
    	scoreTextOnes 		= new Text ( 975, 96, 	ResourcesManager.getInstance().fontDigital, scoreOnes, 
    										new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
    	scoreInfoMax 		= new Text ( 400, 96, 	ResourcesManager.getInstance().scoreInfoFont, initialMaxScore, 22,
											new TextOptions(HorizontalAlign.LEFT), ResourcesManager.getInstance().vbom);
    	scoreInfoLast 		= new Text ( 400, 196, 	ResourcesManager.getInstance().scoreInfoFont, initialCurrentScore, 12,
											new TextOptions(HorizontalAlign.LEFT), ResourcesManager.getInstance().vbom);
    	scoreInfoLast.setColor( Color.GREEN.getARGBPackedInt());
    	
    	t10 = new TimeCounter(875, MainActivity.SCREEN_HEIGHT/2, 10f);
    	t1 	= new TimeCounter(955, MainActivity.SCREEN_HEIGHT/2, 1f);
    	
    	backgroundSprite.setAlpha(0.4f);
    	finishFlagSprite.setScale(0.7f);

    	info.setAlpha(0.2f);
    	info.setColor(new Color(.9f, .9f, 0));
    	info.setText("BEYNiUM");
	    info.setAnchorCenter(0, 0);   
	    
	    this.attachChild(congratulations);
	    this.attachChild(backgroundSprite);
	    this.attachChild(t1);
	    this.attachChild(t10);
	    this.attachChild(info);
	    this.attachChild(scoreTable);
	    this.attachChild(scoreTextHundreds);
	    this.attachChild(scoreTextTens);
	    this.attachChild(scoreTextOnes);
	    this.attachChild(finishFlagSprite);
	    this.attachChild(scoreInfoMax);
	    this.attachChild(scoreInfoLast);
	    
	    t1.setVisible(false);
	    t10.setVisible(false);
	    scoreTable.setVisible(false);
	    scoreTextTens.setVisible(false);
	    scoreTextOnes.setVisible(false);
	    finishFlagSprite.setVisible(false);
	    scoreTextHundreds.setVisible(false);
	    congratulations.setVisible(false);
	    scoreInfoLast.setVisible(false);
	    scoreInfoMax.setVisible(false);
		
		ResourcesManager.getInstance().gameoverText.setAnchorCenter(0, 0);   
		ResourcesManager.getInstance().gameoverText.setAlpha(1.0f);
		ResourcesManager.getInstance().gameoverText.setSize(80,120);
		ResourcesManager.getInstance().gameoverText.setText("zaman doldu...");
		ResourcesManager.getInstance().gameoverText.setVisible(timeisup);
		
		attachChild(ResourcesManager.getInstance().gameoverText);
	}
    
    public static InfoSectionScene getInstance(){
        return INSTANCE;
    }
    
    public void stopTimer(){
    	SceneManager.getInstance().getCurrentScene().clearScene();
    	ResourcesManager.getInstance().activity.getEngine().unregisterUpdateHandler(timerHandler);

    	finishFlagSprite.setVisible(timeisup);
    	ResourcesManager.getInstance().gameoverText.setVisible(timeisup);
		
    	scoreInfoMax.setText(initialMaxScore);
		scoreInfoLast.setText(initialCurrentScore);
		if(SceneManager.getInstance().getCurrentScene() instanceof GamePlayBaseScene)
			highScore   = ((GamePlayBaseScene)SceneManager.getInstance().getCurrentScene()).HIGH_SCORE;
		if (highScore <= 0) highScore = currentScore;//for the first time game ever played

		String last = scoreInfoLast.getText().toString() + Integer.toString(currentScore);
		String max 	= scoreInfoMax.getText().toString() + Integer.toString(highScore);
		scoreInfoLast.setText(last);
		scoreInfoMax.setText(max);
		
		scoreInfoLast.setVisible(timeisup);
		scoreInfoMax.setVisible(timeisup);
		if(timeisup)
			((GamePlayBaseScene)SceneManager.getInstance().getCurrentScene()).updateDatabase();
		this.clearUpdateHandlers();
    }
    
    public void startTimer(final int from){
    	if(isTimeDisabled) return;
    	timeisup = false;
    	ResourcesManager.getInstance().gameoverText.setVisible(false);
    	COUNT_ONES = from%10;
    	COUNT_TENS = from/10;
    	
    	t1.setCurrentTileIndex(COUNT_ONES);
    	t10.setCurrentTileIndex(COUNT_TENS);
    	start();
    }
    
	private synchronized void start(){
		COUNT_ONES--;
		ResourcesManager.getInstance().activity.getEngine().registerUpdateHandler(new TimerHandler(0.996f, true, new ITimerCallback(){
	        @Override
	        public void onTimePassed(TimerHandler pTimerHandler){
	        	timerHandler = pTimerHandler;
	        	
	        	if(COUNT_ONES < 0){
	        		COUNT_ONES = 9;
	        		--COUNT_TENS;
	        		if(COUNT_TENS < 1) t10.setVisible(false);
	        	}
	        	if(!t10.isVisible() && COUNT_ONES == 0) {
	        		timeisup = true;
	        		stopTimer();
	        	}
	        	t1.setCurrentTileIndex(COUNT_ONES--);
	        	t10.setCurrentTileIndex(COUNT_TENS);
	        }
	    }));
	}
	
	public final void setScore(final int score){
		if(score < 0) return;
		currentScore = score;
		scoreTextHundreds.setText(Integer.toString(score/100));
		scoreTextTens.setText(Integer.toString((score/10)%10));
		scoreTextOnes.setText(Integer.toString(score%10));
	}
	
	public final int getCurrentTime(){
		return t1.getCurrentTileIndex() + 10*t10.getCurrentTileIndex();
	}
	
	public final boolean timeIsUp(){
		return timeisup;
	}
	public final void setTimeDisabled(boolean disable){
		isTimeDisabled = disable;
	}
	
	public void hideGameItems(){
		t1.setVisible(false);
		t10.setVisible(false);
		scoreTable.setVisible(false);
		scoreTextHundreds.setVisible(false);
		scoreTextTens.setVisible(false);
		scoreTextOnes.setVisible(false);
		congratulations.setVisible(false);
	}
	
	public void onBackKeyPressed(){
		stopTimer();
		setScore(0);
		timeisup = false;
		t1.setVisible(false);
		t10.setVisible(false);
		scoreTable.setVisible(false);
		scoreTextTens.setVisible(false);
		scoreTextOnes.setVisible(false);
		finishFlagSprite.setVisible(false);
		scoreTextHundreds.setVisible(false);
		scoreInfoMax.setVisible(false);
		scoreInfoLast.setVisible(false);
		congratulations.setVisible(false);
		ResourcesManager.getInstance().gameoverText.setVisible(false);
	}
	
	public void prepareGameItems(){
		t1.setVisible(true);
		t10.setVisible(true);
		info.setVisible(true);
		scoreTable.setVisible(true);
		scoreTextHundreds.setVisible(true);
		scoreTextTens.setVisible(true);
		scoreTextOnes.setVisible(true);
	}
	
	public void showGreeting(){
		stopTimer();//be sure to call this first
		congratulations.setVisible(true);
		scoreInfoMax.setVisible(true);
		scoreInfoLast.setVisible(true);
		((GamePlayBaseScene)SceneManager.getInstance().getCurrentScene()).updateDatabase();
	}
}
