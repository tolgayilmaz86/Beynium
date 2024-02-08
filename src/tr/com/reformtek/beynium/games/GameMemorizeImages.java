package tr.com.reformtek.beynium.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

import tr.com.reformtek.beynium.MainActivity;
import tr.com.reformtek.beynium.scenes.GamePlayBaseScene;
import tr.com.reformtek.beynium.scenes.InfoSectionScene;
import tr.com.reformtek.beynium.util.ResourcesManager;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class GameMemorizeImages extends GamePlayBaseScene
{
	public static final int CHOICE_COUNT = 4;
	public static final int PICTURE_COUNT = 35;

	public int[] LEVEL_IMAGE_COUNT;
	public int[] LEVEL_CHOICE_COUNT;
	
	float STOP_LINE;
	boolean stopAnimation;
	private ScaleModifier sm;
	public int LEVEL, answered, lostID, lostIndex;
	Sprite cloudFront, cloudBack, falseSprite, trueSprite;
	ArrayList<Integer> invisible;
	ArrayList<Picture> viewImages;
	ArrayList<Picture> choiceImages;
	Map <Integer, Picture> imagesMap;
	
	public GameMemorizeImages(ITextureRegion tex) {
		super(tex);
		setTimeSpan(SECONDS_60);
		informationText = "Hangi resmin kaybolduğunu bulabilir misin?";
	}

	@Override
	public void createScene() {
		super.createScene();
		STOP_LINE = 100;
		stopAnimation = false;
		LEVEL = lostIndex = lostID = answered = 0;

		imagesMap 			= new TreeMap<Integer,Picture>();
		viewImages 			= new ArrayList<Picture>();
		cloudFront 			= new Sprite (410,270,resourcesManager.texCloudFront,vbom);
		cloudBack 			= new Sprite (490,310,resourcesManager.texCloudBack,vbom);
		invisible 			= new ArrayList<Integer>();
		choiceImages 		= new ArrayList<Picture>();
		LEVEL_IMAGE_COUNT 	= new int[]{3,3,3,4,4,4,4,5,5,5,5,6,6,6,6};
		LEVEL_CHOICE_COUNT 	= new int[]{2,3,4,4,4,5,5,5,5,5,6,6,6,6,6};
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.GAME_MEMORIZE_IMAGES;
	}

	@Override
	public void disposeScene() {
		for(Picture p : imagesMap.values()){
			detachChild(p);
		}
		imagesMap.clear();
		viewImages.clear();
		choiceImages.clear();
		detachChild(cloudBack);
		detachChild(cloudFront);
		clearEntityModifiers();
		clearUpdateHandlers();
		clearTouchAreas();
		
		if(!this.isDisposed()) dispose();
	}
	
	@Override
	public void clearScene(){
		trueSprite.setVisible(false);
		falseSprite.setVisible(false);
		for(Picture p : imagesMap.values())
			p.setVisible(false);
		cloudBack.setVisible(false);
		cloudFront.setVisible(false);
		clearEntityModifiers();
		clearTouchAreas();
		clearUpdateHandlers();
	}
	
	@Override
	public void loadSprites(){
		falseSprite = new Sprite(MainActivity.CAMERA_WIDTH/1.15f, 480, resourcesManager.texRegFalse, vbom);
		trueSprite 	= new Sprite(MainActivity.CAMERA_WIDTH/1.15f, 480, resourcesManager.texRegTrue, vbom);
		sm = new ScaleModifier(.9f,2.0f,0.0f);
		attachChild(falseSprite);
		attachChild(trueSprite);
		falseSprite.setVisible(false);
		trueSprite.setVisible(false);
		for (int i = 0; i < PICTURE_COUNT; ++i){
			imagesMap.put(i, new Picture(i));
		}
		attachChild(cloudBack);
		adjustLevel(LEVEL);
	}
	
	public void adjustLevel(final int level){
		if(level >= LEVEL_IMAGE_COUNT.length){
			endGame();
			return;
		}
		if(cloudFront.hasParent())
			detachChild(cloudFront);
		hideChoices();
		invisible.clear();
		int id;
		for(int i = 0; i < LEVEL_IMAGE_COUNT[level]; ++i){
			do{
				id = (int)(Math.random()*PICTURE_COUNT);
			} while(invisible.contains(id));
			viewImages.add(imagesMap.get(id));
			invisible.add(id);
		}
		startAnimation();
		attachChild(cloudFront);
	}
	
	public void startAnimation(){
		//Place pictures
		for(int i = 0; i < viewImages.size(); ++i){
			viewImages.get(i).setPosition(200 - (10 * viewImages.size()) +  i*GameMemorizeImages.getTextureRegion(i).getWidth()+10, 530);
			if(!viewImages.get(i).hasParent()) attachChild(viewImages.get(i));
			viewImages.get(i).setVisible(true);
		}
		lostIndex = (int)(Math.random() * viewImages.size());
		viewImages.get(lostIndex).isLost = true;
		lostID = viewImages.get(lostIndex).ID;
		stopAnimation = false;
		
		for(int i = 0; i < viewImages.size(); ++i){
			viewImages.get(i).move = new MoveModifier(4f, 	viewImages.get(i).getX(), 
															viewImages.get(i).getY(), 
															viewImages.get(i).getX(),
															STOP_LINE);
			viewImages.get(i).registerEntityModifier(viewImages.get(i).move);
		}
	}
	
	public void showChoices(){
		int index;
		invisible.clear();
		choiceImages.clear();
		
		for(int i = 0; i < viewImages.size(); ++i){
			viewImages.get(i).clearEntityModifiers();
			invisible.add(viewImages.get(i).ID);
		}

		for(int i = 0; i < LEVEL_CHOICE_COUNT[LEVEL]-1; ++i){
			do{
				index = (int)(Math.random() * PICTURE_COUNT);
			}while(invisible.contains(index));
			choiceImages.add(imagesMap.get(index));
			invisible.add(choiceImages.get(i).ID);
		}
		choiceImages.add(viewImages.get(lostIndex));
		
		Collections.shuffle(choiceImages);
		
		for(int i = 0; i < choiceImages.size(); ++i){
			choiceImages.get(i).setPosition(200 - (10 * choiceImages.size()) + i*GameMemorizeImages.getTextureRegion(i).getWidth() + 20, 530);
			if(!choiceImages.get(i).hasParent()) attachChild(choiceImages.get(i));
			choiceImages.get(i).isChoice = true;
			choiceImages.get(i).setVisible(true);
			registerTouchArea(choiceImages.get(i));
		}
		stopAnimation = true;
	}
	public void hideChoices(){
		for(int i = 0; i < choiceImages.size(); ++i){
			unregisterTouchArea(choiceImages.get(i));
			detachChild(choiceImages.get(i));
		}
		choiceImages.clear();
		for(int i = 0; i < viewImages.size(); ++i){
			if(viewImages.get(i).isLost) viewImages.get(i).setVisible(true);
			viewImages.get(i).isLost = false;
			detachChild(viewImages.get(i));
		}
		viewImages.clear();
	}
	
	public void replaceImages(){
		if(lostID != viewImages.get(0).ID)
			viewImages.get(0).setPosition(imagesMap.get(lostID));
		else if(lostID != viewImages.get(viewImages.size()-1).ID)
			viewImages.get(viewImages.size()-1).setPosition(imagesMap.get(lostID));
	}

	private class Picture extends Sprite {

		public int ID;
		MoveModifier move;
		public boolean isLost, isChoice;
		
		public Picture(int id) {
			super(	GameMemorizeImages.getTextureRegion(id).getWidth(), 
					GameMemorizeImages.getTextureRegion(id).getHeight(),  
					GameMemorizeImages.getTextureRegion(id), vbom );
			move = null;
			this.ID = id;
			this.isLost = false;
			this.setScale(0.75f);
		}
		
		@Override
		protected void onManagedUpdate(final float pSecondsElapsed) 
		{
			if(stopAnimation || InfoSectionScene.getInstance().timeIsUp()) return;
			if(this.getY() < cloudFront.getY() && isLost){
				this.setVisible(false);
				replaceImages();
			}
			if(this.move.isFinished()){
				this.clearEntityModifiers();
				stopAnimation = true;
				showChoices();
			}
			super.onManagedUpdate(pSecondsElapsed);
		}
		
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
				float pTouchAreaLocalX, float pTouchAreaLocalY) 
		{
			if(pSceneTouchEvent.isActionDown())
			{
				if(this.isChoice)
				{
					if(this.isLost){
						SCORE += 5;
						trueSprite.setVisible(true);
						trueSprite.registerEntityModifier(sm);
						trueSprite.resetEntityModifiers();
						InfoSectionScene.getInstance().setScore(SCORE);
						adjustLevel(++LEVEL);
					}
					else{
						SCORE -= 1;
						falseSprite.setVisible(true);
						falseSprite.registerEntityModifier(sm);
						falseSprite.resetEntityModifiers();
						if(SCORE < 0) SCORE = 0;
						InfoSectionScene.getInstance().setScore(SCORE);
						adjustLevel(LEVEL);
					}
				}
			}
			return true;
		}
		
//		@Override
//		protected void preDraw(GLState pGLState, Camera pCamera) 
//		{
//			super.preDraw(pGLState, pCamera);
//			pGLState.enableDither();
//		}
	}
	
	private static ITextureRegion getTextureRegion(final int index){
		switch( index ) {
			case 0:
				return ResourcesManager.texImg1;
			case 1:
				return ResourcesManager.texImg2;
			case 2:
				return ResourcesManager.texImg3;
			case 3:
				return ResourcesManager.texImg4;
			case 4:
				return ResourcesManager.texImg5;
			case 5:
				return ResourcesManager.texImg6;
			case 6:
				return ResourcesManager.texImg7;
			case 7:
				return ResourcesManager.texImg8;
			case 8:
				return ResourcesManager.texImg9;
			case 9:
				return ResourcesManager.texImg10;
			case 10:
				return ResourcesManager.texImg11;
			case 11:
				return ResourcesManager.texImg12;
			case 12:
				return ResourcesManager.texImg13;
			case 13:
				return ResourcesManager.texImg14;
			case 14:
				return ResourcesManager.texImg15;
			case 15:
				return ResourcesManager.texImg16;
			case 16:
				return ResourcesManager.texImg17;
			case 17:
				return ResourcesManager.texImg18;
			case 18:
				return ResourcesManager.texImg19;
			case 19:
				return ResourcesManager.texImg20;
			case 20:
				return ResourcesManager.texImg21;
			case 21:
				return ResourcesManager.texImg22;
			case 22:
				return ResourcesManager.texImg23;
			case 23:
				return ResourcesManager.texImg24;
			case 24:
				return ResourcesManager.texImg25;
			case 25:
				return ResourcesManager.texImg26;
			case 26:
				return ResourcesManager.texImg27;
			case 27:
				return ResourcesManager.texImg28;
			case 28:
				return ResourcesManager.texImg29;
			case 29:
				return ResourcesManager.texImg30;
			case 30:
				return ResourcesManager.texImg31;
			case 31:
				return ResourcesManager.texImg32;
			case 32:
				return ResourcesManager.texImg33;
			case 33:
				return ResourcesManager.texImg34;
			case 34:
				return ResourcesManager.texImg35;

			default:
				return null;
		}
	}
}
