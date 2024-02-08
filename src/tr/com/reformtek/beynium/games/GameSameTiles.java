package tr.com.reformtek.beynium.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

import tr.com.reformtek.beynium.scenes.GamePlayBaseScene;
import tr.com.reformtek.beynium.scenes.InfoSectionScene;
import tr.com.reformtek.beynium.util.ResourcesManager;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class GameSameTiles extends GamePlayBaseScene 
{
	public final int TILE_COUNT_MAX 		= 20;
	public final int TILE_ROW_COUNT_MAX 	= 5;
	public final int TILE_COLUMN_COUNT_MAX 	= 4;

	public Tile spriteTile1, spriteTile2, spriteTile3,
				spriteTile4, spriteTile5, spriteTile6,
				spriteTile7, spriteTile8, spriteTile9,
				spriteTile10, spriteTile11, spriteTile12,
				spriteTile13, spriteTile14, spriteTile15,
				spriteTile16, spriteTile17, spriteTile18,
				spriteTile19, spriteTile20;
	
	
	public Sprite 			frameSelectedLeft, frameSelectedRight;
	ArrayList<int[]> 		levels;
	ArrayList<Integer> 		indexesLeft, indexesRight;
	Map <Integer,Tile> 		tilesMapLeft, tilesMapRight;
	Map <Integer,Sprite> 	framesSelectedMapLeft, framesSelectedMapRight;
	
	
	public float[] locationLeftX, locationLeftY, locationRightX, locationRightY;
	public int [] level1, level2, level3, level4, level5, level6, level7, level8;

	public final int TILE_COUNT_LEVEL1 = 4;
	public final int TILE_COUNT_LEVEL2 = 6;
	public final int TILE_COUNT_LEVEL3 = 8;
	public final int TILE_COUNT_LEVEL4 = 10;
	public final int TILE_COUNT_LEVEL5 = 12;
	public final int TILE_COUNT_LEVEL6 = 14;
	public final int TILE_COUNT_LEVEL7 = 16;
	public final int TILE_COUNT_LEVEL8 = 20;
	
	public int LEVEL, coupling;
	private boolean isLeftSelected, isRightSelected;
	private int selectedIndexLeft, selectedIndexRight;
	public ScaleModifier sm;
	public FadeOutModifier fm;
	public Tile leftTile, rightTile;

	public GameSameTiles(ITextureRegion tex) {
		super(tex);
		informationText = "Şekilleri eşleştirebilecek misiniz?";
		setTimeSpan(SECONDS_90);
		fm 					= new FadeOutModifier(0.5f);
		LEVEL = coupling 	= 0;
		isLeftSelected 		= false;
		isRightSelected 	= false;
		selectedIndexLeft 	= -1;
		selectedIndexRight 	= -2;
	}

	@Override
	public void createScene() {
		super.createScene();
		
		tilesMapLeft 			= new TreeMap<Integer, Tile>();
		tilesMapRight 			= new TreeMap<Integer, Tile>();
		indexesLeft 			= new ArrayList<Integer>();
		indexesRight 			= new ArrayList<Integer>();
		framesSelectedMapLeft 	= new TreeMap<Integer, Sprite>();
		framesSelectedMapRight 	= new TreeMap<Integer, Sprite>();
		
		locationLeftX 	= new float[TILE_COUNT_MAX];
		locationLeftY 	= new float[TILE_COUNT_MAX];
		locationRightX 	= new float[TILE_COUNT_MAX];
		locationRightY 	= new float[TILE_COUNT_MAX];
		
		level1 = new int[]{5,6,9,10};
		level2 = new int[]{5,6,9,10,13,14};
		level3 = new int[]{1,2,5,6,9,10,13,14};
		level4 = new int[]{1,2,5,6,9,10,13,14};
		level4 = new int[]{1,2,5,6,9,10,13,14,17,18};
		level5 = new int[]{1,2,5,6,9,10,13,14,17,18};
		level5 = new int[]{1,2,3,5,6,7,9,10,11,13,14,15};
		level6 = new int[]{1,2,3,5,6,7,8,9,10,11,12,13,14,15};
		level7 = new int[]{4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
		level8 = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		levels = new ArrayList<int[]>();
		
		levels.add(level1);
		levels.add(level2);
		levels.add(level3);
		levels.add(level4);
		levels.add(level5);
		levels.add(level6);
		levels.add(level7);
		levels.add(level8);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.GAME_SAME_TILES;
	}

	@Override
	public void disposeScene() {
		for(Tile t : tilesMapLeft.values()){
			t.setVisible(false);
			detachChild(t);
			unregisterTouchArea(t);
		}
		for(Tile t : tilesMapRight.values()){
			t.setVisible(false);
			detachChild(t);
			unregisterTouchArea(t);
		}
		for(Sprite f : framesSelectedMapRight.values()){
			f.setVisible(false);
			detachChild(f);
		}
		for(Sprite f : framesSelectedMapLeft.values()){
			f.setVisible(false);
			detachChild(f);
		}
		tilesMapLeft.clear();
		tilesMapRight.clear();
		framesSelectedMapRight.clear();
		framesSelectedMapLeft.clear();
		
		if(!this.isDisposed()) dispose();
	}

	public void shuffle(){
		indexesLeft.clear();
		indexesRight.clear();
		for(int i = 0; i < TILE_COUNT_MAX; ++i)
			indexesLeft.add(i);
		
		for(int i = 0; i < TILE_COUNT_MAX; ++i)
			indexesRight.add(i);
		
		Collections.shuffle(indexesRight);
		Collections.shuffle(indexesLeft);
	}
	
	@Override
	public void loadSprites(){
		shuffle();
		int index,indexR;
		float leftX,leftY,rightX,rightY;
    	for(int row = 0; row < TILE_ROW_COUNT_MAX; ++row)
    		for(int column = 0; column < TILE_COLUMN_COUNT_MAX; ++column){
    			index 	= indexR	= TILE_COLUMN_COUNT_MAX * row + column;
    			
    			leftX 	= 150 + column * ResourcesManager.texFrameSelected.getWidth(); //start tiles from this position
    			leftY 	= rightY = 450 - row * ResourcesManager.texFrameSelected.getWidth(); //on the same row
    			rightX 	= leftX + 380; //giving a gap between tiles boxes
    			
    			//keep this coordinates for future use
    			locationLeftX[index] 	= leftX;
    			locationLeftY[index] 	= leftY;
    			locationRightX[indexR] 	= rightX;
    			locationRightY[indexR] 	= rightY;
    			
    			index 	= indexesLeft.get(index); //shuffle left tiles
    			indexR 	= indexesRight.get(indexR); //shuffle right tiles
    			
    			frameSelectedLeft 	= new Sprite(leftX, leftY, ResourcesManager.texFrameSelected.getWidth(),
    														   ResourcesManager.texFrameSelected.getHeight(), 
    														   ResourcesManager.texFrameSelected, resourcesManager.vbom);
    			
    			frameSelectedRight 	= new Sprite(rightX, rightY, ResourcesManager.texFrameSelected.getWidth(),
    															 ResourcesManager.texFrameSelected.getHeight(), 
    															 ResourcesManager.texFrameSelected, resourcesManager.vbom);
    			
    			framesSelectedMapLeft.put(index, frameSelectedLeft);
    			framesSelectedMapRight.put(indexR, frameSelectedRight);
    			
    			leftTile 			= new Tile(leftX, leftY, index,true);
    			rightTile 			= new Tile(rightX, rightY, indexR,false);
    			
    			tilesMapLeft.put(index, leftTile);
    			tilesMapRight.put(indexR, rightTile);

    			attachChild(framesSelectedMapLeft.get(index));
    			attachChild(framesSelectedMapRight.get(indexR));
    			
    			framesSelectedMapLeft.get(index).setVisible(false);
    			framesSelectedMapRight.get(indexR).setVisible(false);
    			
    			attachChild(tilesMapLeft.get(index));
    			attachChild(tilesMapRight.get(indexR));
    		}
    	adjustLevel(LEVEL);
	}
	
	public void adjustLevel(final int level){
		coupling = 0;
		if(level >= levels.size()){
			endGame();
			return;
		}
		ArrayList<Integer> orderList = new ArrayList<Integer>();
		for(int i = 0; i < TILE_COUNT_MAX; ++i)
			orderList.add(i);
		
		ArrayList<Integer> randomList = new ArrayList<Integer>();
		for(int i = 0; i < TILE_COUNT_MAX; ++i)
			randomList.add(i);
		Collections.shuffle(randomList);
		
		for(Tile t : tilesMapLeft.values()){
			t.setVisible(false);
			unregisterTouchArea(t);
		}
		for(Tile t : tilesMapRight.values()){
			unregisterTouchArea(t);
			t.setVisible(false);
		}
		
		for(int i = 0; i < levels.get(level).length; ++i)
			swapLocations(orderList.get(i), randomList.get(i));
		
		for(int i = 0; i < levels.get(level).length; ++i){
			int index = orderList.get(i);

			tilesMapLeft.get(index).setVisible(true);
			tilesMapRight.get(index).setVisible(true);
			
			registerTouchArea(tilesMapLeft.get(index));
			registerTouchArea(tilesMapRight.get(index));
		}
	}
	
	public void swapLocations(final int targetIndex, final int destinationIndex){
		if(targetIndex == destinationIndex) return;
		int tempLeftX = (int)tilesMapLeft.get(targetIndex).getX();
		int tempLeftY = (int)tilesMapLeft.get(targetIndex).getY();
		int tempIndexLeft = tilesMapLeft.get(targetIndex).index;
		
		int tempRightX = (int)tilesMapRight.get(targetIndex).getX();
		int tempRightY = (int)tilesMapRight.get(targetIndex).getY();
		int tempIndexRight = tilesMapRight.get(targetIndex).index;
		
		int newLeftTargetIndex, newRightTargetIndex;
		
		newLeftTargetIndex = tilesMapLeft.get(targetIndex).index = tilesMapLeft.get(destinationIndex).index;
		tilesMapLeft.get(destinationIndex).index = tempIndexLeft;
		
		tilesMapLeft.get(targetIndex).setPosition(tilesMapLeft.get(destinationIndex));
		tilesMapLeft.get(destinationIndex).setPosition(tempLeftX, tempLeftY);
		
		newRightTargetIndex = tilesMapRight.get(targetIndex).index = tilesMapRight.get(destinationIndex).index;
		tilesMapRight.get(destinationIndex).index = tempIndexRight;
		tilesMapRight.get(targetIndex).setPosition(tilesMapRight.get(destinationIndex));
		tilesMapRight.get(destinationIndex).setPosition(tempRightX, tempRightY);
		
		Tile left = tilesMapLeft.get(destinationIndex);
		Tile leftT = tilesMapLeft.get(targetIndex);
		Tile right = tilesMapRight.get(destinationIndex);
		Tile rightT = tilesMapRight.get(targetIndex);
		
		tilesMapLeft.put(tempIndexLeft, left);
		tilesMapLeft.put(newLeftTargetIndex, leftT);
		tilesMapRight.put(tempIndexRight, right);
		tilesMapRight.put(newRightTargetIndex, rightT);
	}
	
	public void removeCoupledTiles(int index){
		unregisterTouchArea(tilesMapLeft.get(index));
		unregisterTouchArea(tilesMapRight.get(index));
	}
	
	@Override 
	public void clearScene(){
		for(Tile t : tilesMapLeft.values()){
			t.setVisible(false);
			unregisterTouchArea(t);
		}
		for(Tile t : tilesMapRight.values()){
			t.setVisible(false);
			unregisterTouchArea(t);
		}
		for(Sprite f : framesSelectedMapRight.values()){
			f.setVisible(false);
		}
		for(Sprite f : framesSelectedMapLeft.values()){
			f.setVisible(false);
		}
	}
	
	private class Tile extends Sprite{
		public int index;
		public boolean isLeft;

		public Tile(float pX, float pY, int index, boolean isLeft) {
			super(pX, pY, GameSameTiles.getTextureRegion(index).getWidth(),
						  GameSameTiles.getTextureRegion(index).getHeight(), 
						  GameSameTiles.getTextureRegion(index), vbom);
			this.index = index;
			this.isLeft = isLeft;
		}

		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
				float pTouchAreaLocalX, float pTouchAreaLocalY) 
		{
			if(pSceneTouchEvent.isActionDown())
			{
				if(this.isLeft) {
					if(isLeftSelected){
						//previously one selected on this side
						framesSelectedMapLeft.get(selectedIndexLeft).setVisible(false);
					}
					selectedIndexLeft = this.index;
					isLeftSelected = true;
					framesSelectedMapLeft.get(selectedIndexLeft).setVisible(true);
				}
				else {
					if(isRightSelected){
						//previously one selected on this side
						framesSelectedMapRight.get(selectedIndexRight).setVisible(false);
					}
					isRightSelected = true;
					selectedIndexRight = this.index;
					framesSelectedMapRight.get(selectedIndexRight).setVisible(true);
				}
				if((selectedIndexLeft == selectedIndexRight) && (selectedIndexLeft > -1 || selectedIndexRight > -1))
				{
					tilesMapLeft.get(selectedIndexLeft).setVisible(false);
					tilesMapRight.get(selectedIndexRight).setVisible(false);
					
					framesSelectedMapLeft.get(selectedIndexLeft).setVisible(false);
					framesSelectedMapRight.get(selectedIndexRight).setVisible(false);
					isLeftSelected = false;
					isRightSelected = false;
					selectedIndexLeft = -1;
					selectedIndexRight = -2;
					coupling++;

					removeCoupledTiles(this.index);
					SCORE += 2;
					InfoSectionScene.getInstance().setScore(SCORE);
					if(coupling >= levels.get(LEVEL).length){
						SCORE += LEVEL;
						InfoSectionScene.getInstance().setScore(SCORE);
						adjustLevel(++LEVEL);
					}
				}
				else if(isLeftSelected && isRightSelected)
				{
					SCORE -= 1;
					if(SCORE < 0) SCORE = 0;
					InfoSectionScene.getInstance().setScore(SCORE);
					isLeftSelected = false;
					isRightSelected = false;
					if(this.isLeft){
						framesSelectedMapLeft.get(selectedIndexLeft).setVisible(false);
						framesSelectedMapRight.get(selectedIndexRight).setVisible(false);
						framesSelectedMapLeft.get(this.index).setVisible(true);
						selectedIndexLeft = this.index;
						selectedIndexRight = -1;
						isLeftSelected = true;
					}
					else {
						framesSelectedMapRight.get(selectedIndexRight).setVisible(false);
						framesSelectedMapLeft.get(selectedIndexLeft).setVisible(false);
						framesSelectedMapRight.get(this.index).setVisible(true);
						selectedIndexLeft = -2;
						selectedIndexRight = this.index;
						isRightSelected = true;
					}
					this.resetEntityModifiers();
				}
			}
			return true;
		}
	}
	private static ITextureRegion getTextureRegion(final int index){
		switch( index ) {
			case 0:
				return ResourcesManager.texTile1;
			case 1:
				return ResourcesManager.texTile2;
			case 2:
				return ResourcesManager.texTile3;
			case 3:
				return ResourcesManager.texTile4;
			case 4:
				return ResourcesManager.texTile5;
			case 5:
				return ResourcesManager.texTile6;
			case 6:
				return ResourcesManager.texTile7;
			case 7:
				return ResourcesManager.texTile8;
			case 8:
				return ResourcesManager.texTile9;
			case 9:
				return ResourcesManager.texTile10;
			case 10:
				return ResourcesManager.texTile11;
			case 11:
				return ResourcesManager.texTile12;
			case 12:
				return ResourcesManager.texTile13;
			case 13:
				return ResourcesManager.texTile14;
			case 14:
				return ResourcesManager.texTile15;
			case 15:
				return ResourcesManager.texTile16;
			case 16:
				return ResourcesManager.texTile17;
			case 17:
				return ResourcesManager.texTile18;
			case 18:
				return ResourcesManager.texTile19;
			case 19:
				return ResourcesManager.texTile20;
			default:
				return ResourcesManager.texTile1;
		}
	}
}
