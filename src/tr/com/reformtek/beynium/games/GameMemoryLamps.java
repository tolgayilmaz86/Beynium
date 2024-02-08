package tr.com.reformtek.beynium.games;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

import tr.com.reformtek.beynium.scenes.GamePlayBaseScene;
import tr.com.reformtek.beynium.scenes.InfoSectionScene;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class GameMemoryLamps extends GamePlayBaseScene 
{
	private final static int LAMP_ROW_COUNT = 6;
	private final static int LAMP_COLUMN_COUNT = 6;
	private final static float LIGHT_ON_DURATION = .7f;
	private final static int LAMP_COUNT = LAMP_COLUMN_COUNT*LAMP_ROW_COUNT;

	private ScaleModifier 	sm;
	private TimerHandler 	timerHandler;
	private boolean 		isAnimationFinished;
	private Sprite 			falseSprite, trueSprite;
	private Lamp 			lampOnSprite,lampOffSprite;
	private int 			LEVEL, sequence, ANIMATE_COUNT;
	private int [] 			animateCountlevels, 
							level_1_2_visible, 
							level_3_4_5_visible;

	private ArrayList<Integer> lightPattern;
	private Map<Integer, Lamp> lampOffSpriteMap,lampOnSpriteMap;

	public GameMemoryLamps() {
		informationText = "Lambaların hangi sırayla yandığını hatırlayabilir misiniz?";
		setTimeSpan(SECONDS_60);
	}

	@Override
	public void createScene(){
		super.createScene();
		LEVEL = sequence 	= 0;
		isAnimationFinished = false;
		timerHandler 		= null;
		lightPattern 		= new ArrayList<Integer>(10);
		lampOnSpriteMap 	= new TreeMap<Integer,Lamp>();
		lampOffSpriteMap 	= new TreeMap<Integer,Lamp>();
		
		animateCountlevels 	= new int []{3,3,3,4,4,4,3,4,4,5,5,5,3,3,4,4,5};
		level_1_2_visible 	= new int []{7,8,9,10,13,14,15,16,19,20,21,22,25,26,27,28};//the lamps that are visible in 1. and 2. levels
		level_3_4_5_visible = new int []{0,1,2,3,4,6,7,8,9,10,12,13,14,15,16,18,19,20,21,22,24,25,26,27,28};//the lamps that are visible in 3. 4. 5. and next levels
		ANIMATE_COUNT 		= animateCountlevels[LEVEL];
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.GAME_MEMORY_LAMPS;
	}

	@Override
	public void disposeScene()
	{
//		unregisterUpdateHandler(this.timerHandler);
		clearUpdateHandlers();
		for(Lamp lampOn : lampOnSpriteMap.values()){
			lampOn.setVisible(false);
			detachChild(lampOn);
		}
		lampOnSpriteMap.clear();
		for(Lamp lampOff : lampOffSpriteMap.values()){
			unregisterTouchArea(lampOff);
			lampOff.setVisible(false);
			detachChild(lampOff);
		}
		lampOffSpriteMap.clear();
		lightPattern.clear();
		detachChild(falseSprite);
		detachChild(trueSprite);
		if(!this.isDisposed()) this.dispose();
	}
	
	@Override
	public void loadSprites(){
		lampOnSpriteMap.clear();
		lampOffSpriteMap.clear();
		generateSequence();
		
		for(int row = 0; row < LAMP_ROW_COUNT; ++row){
			for(int column = 0; column < LAMP_COLUMN_COUNT; ++column){
				final int index = row*LAMP_ROW_COUNT + column;
				float pX = 80+column*resourcesManager.texLampOn.getWidth();
				float pY = 540-row*resourcesManager.texLampOn.getHeight();
				
				lampOffSprite = new Lamp(pX,pY, resourcesManager.texLampOff, index, true);
				lampOffSpriteMap.put(index, lampOffSprite);
				lampOffSpriteMap.get(index).setVisible(false);
				attachChild(lampOffSpriteMap.get(index));
				registerTouchArea(lampOffSpriteMap.get(index));
				
				lampOnSprite = new Lamp(pX,pY,resourcesManager.texLampOn, index, false);
				lampOnSpriteMap.put(index, lampOnSprite);
				lampOnSpriteMap.get(index).setVisible(false);
				attachChild(lampOnSpriteMap.get(index));
			}
		}
		falseSprite = new Sprite(300, 300, resourcesManager.texRegFalse, vbom);
		trueSprite 	= new Sprite(300, 300, resourcesManager.texRegTrue, vbom);
		sm = new ScaleModifier(.3f,4.0f,0.0f);
		
		adjustLevel();
		
		attachChild(falseSprite);
		attachChild(trueSprite);
		falseSprite.setVisible(false);
		trueSprite.setVisible(false);
		animate();
	}
	
	public void generateSequence(){
		lightPattern.clear();
		ANIMATE_COUNT = animateCountlevels[LEVEL];
		for(int i = 0; i < ANIMATE_COUNT; ++i){
			int lampIndex = 0;
			//guarantee that we have totally different lamps
			do{
				if(LEVEL < 3)
					lampIndex = level_1_2_visible[(int)(Math.random()*level_1_2_visible.length)];
				else if(LEVEL < 6)
					lampIndex = level_3_4_5_visible[(int)(Math.random()*level_3_4_5_visible.length)];
				else
					lampIndex = (int)(Math.random()*LAMP_COUNT);
			} while(lightPattern.contains(lampIndex));
			lightPattern.add(lampIndex);
		}
	}
	
	public synchronized void animate(){
		sequence = 0;
		if(LEVEL >= animateCountlevels.length){
			endGame();
			return;
		}
		adjustLevel();
		for(Lamp lamp : lampOnSpriteMap.values())
			lamp.setVisible(false);
		if(isAnimationFinished) generateSequence();
		
		if(timerHandler != null) unregisterUpdateHandler(timerHandler);
		
		isAnimationFinished = false;
		timerHandler = new TimerHandler(LIGHT_ON_DURATION, true, new ITimerCallback(){
			int index = 0;
	        @Override
	        public void onTimePassed(TimerHandler pTimerHandler) {
	        	if(index < lightPattern.size()){
	        		lampOnSpriteMap.get(lightPattern.get(index)).setVisible(true);
	        		if(index > 0)
	        			lampOnSpriteMap.get(lightPattern.get(index-1)).setVisible(false);
	        	}
	        	else{
	        		lampOnSpriteMap.get(lightPattern.get(lightPattern.size()-1)).setVisible(false);
	        		isAnimationFinished = true;
	        	}
	        	++index;
	        }
		});
		registerUpdateHandler(timerHandler);
	}
	
	public void adjustLevel(){
        switch (LEVEL)
        {
            case 0:
            case 1:
            case 2:
            	for(int key : lampOffSpriteMap.keySet())
            		for(int i = 0; i < level_1_2_visible.length; ++i)
            			if(key == level_1_2_visible[i])
            				lampOffSpriteMap.get(key).setVisible(true);
            	break;
            	
            case 3:
            case 4:
            case 5:
            	for(int key : lampOffSpriteMap.keySet())
            		for(int i = 0; i < level_3_4_5_visible.length; ++i)
            			if(key == level_3_4_5_visible[i])
            				lampOffSpriteMap.get(key).setVisible(true);
            	break;
            	
            default:
            	for(int key : lampOffSpriteMap.keySet())
            		lampOffSpriteMap.get(key).setVisible(true);
            	break;
       }
	}
	
	@Override
	public void clearScene(){
		unregisterUpdateHandler(this.timerHandler);
		for(Lamp lampOn : lampOnSpriteMap.values()){
			lampOn.setVisible(false);
		}
		for(Lamp lampOff : lampOffSpriteMap.values()){
			unregisterTouchArea(lampOff);
			lampOff.setVisible(false);
		}
		trueSprite.setVisible(false);
		falseSprite.setVisible(false);
	}
	
	private class Lamp extends Sprite {
		public final int index;
		private boolean isTouchable;
		public Lamp(float pX, float pY, ITextureRegion pTextureRegion, final int pIndex, boolean pTouchable) {
			super(pX, pY, pTextureRegion, vbom);
			index = pIndex;
			isTouchable = pTouchable;
		}
		
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
		{
			if(!isTouchable) return false;
			if(pSceneTouchEvent.isActionDown()){
				if(sequence >= lightPattern.size()){
					animate();
					return true;
				}
				//if the order must be the same
				if(isAnimationFinished && lightPattern.get(sequence) != index){
					lampOnSpriteMap.get(index).setVisible(false);
					falseSprite.setVisible(true);
					falseSprite.registerEntityModifier(sm);
					falseSprite.resetEntityModifiers();
					++sequence;
					SCORE -= 1;
					if(SCORE < 0) SCORE = 0;
					InfoSectionScene.getInstance().setScore(SCORE);
					animate();
				}
				else if(isAnimationFinished && lightPattern.get(sequence) == index){
					lampOnSpriteMap.get(index).setVisible(true);
					SCORE += LEVEL / 2 +1;
					InfoSectionScene.getInstance().setScore(SCORE);
					++sequence;
					if(sequence >= lightPattern.size()) {
    					trueSprite.setVisible(true);
    					++LEVEL;
    					trueSprite.registerEntityModifier(sm);
    					trueSprite.resetEntityModifiers();
    					SCORE += 4;
    					InfoSectionScene.getInstance().setScore(SCORE);
						animate();
					}
				}
			}
			return true;
		}
	}
}
