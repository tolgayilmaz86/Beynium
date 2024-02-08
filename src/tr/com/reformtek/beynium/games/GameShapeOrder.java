package tr.com.reformtek.beynium.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.adt.color.Color;

import tr.com.reformtek.beynium.scenes.GamePlayBaseScene;
import tr.com.reformtek.beynium.scenes.InfoSectionScene;
import tr.com.reformtek.beynium.util.ResourcesManager;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class GameShapeOrder extends GamePlayBaseScene {
	public static final int SHAPE_ROW_COUNT 	= 5;
	public static final int SHAPE_COLUMN_COUNT 	= 6;
	public static final int ORDER_Y_LIMIT	 	= 520;
	public static final int SHAPE_COUNT 		= SHAPE_COLUMN_COUNT * SHAPE_ROW_COUNT;

	Sprite						arrow;
	Shape 						shape;
	public int [] 				shapeOrderCount, shpeChoiceCount;
	public int [][] 			shapeChoiceIndex;
	public float []				indexX, indexY;
	public int					LEVEL, SEQ;
	public List <Shape> 		shapesOrderList, shapesChoiceList;
	public List <Integer>		randomList, orderPattern, choicePattern;
	public Map <Integer, Shape> shapesMap;
	
	public GameShapeOrder() {
		setTimeSpan(SECONDS_60);
		informationText = "Şekilleri yukarıda gösterildiği sırada bulabilir misin?";
	}

	@Override
	public void createScene(){
		super.createScene();
		LEVEL 				= SEQ = 0;
		orderPattern		= new ArrayList<Integer>(10);
		choicePattern		= new ArrayList<Integer>(30);
		shapesOrderList		= new ArrayList<Shape>(10);
		randomList			= new ArrayList<Integer>(SHAPE_COUNT);
		shapesChoiceList	= new ArrayList<Shape>(SHAPE_COUNT);
		shapesMap 			= new TreeMap<Integer, Shape>();
		shapeOrderCount 	= new int[] {3,3,3,4,4,4,5,5,5,6};
		shpeChoiceCount 	= new int[] {6,6,6,8,8,8,10,10,12,12};
		shapeChoiceIndex 	= new int[][] {{0,1,2,3,4},{0,1,2,3,4}};
		indexX 				= new float[SHAPE_COUNT];
		indexY 				= new float[SHAPE_COUNT];
		arrow 				= new Sprite(460, ORDER_Y_LIMIT, ResourcesManager.getInstance().texArrow, vbom);
	}
	
	@Override 
	public void loadSprites(){
		int id;
		attachChild(arrow);
		arrow.setVisible(true);
		for(int row = 0; row < SHAPE_ROW_COUNT; ++row){
			for(int column = 0; column < SHAPE_COLUMN_COUNT; ++column){
				id = row * SHAPE_COLUMN_COUNT + column;
				randomList.add(id);
    			indexX[id] 	= 150 + column * getTextureRegion(id).getWidth() + column; //start shapes from this position
    			indexY[id]	= 400 -    row * getTextureRegion(id).getWidth() + row * 1.4f; //on the same row
				shape 		= new Shape(indexX[id], indexY[id], id);
				shapesMap.put(id, shape);
				attachChild(shapesMap.get(id));
				shapesMap.get(id).setVisible(false);
			}
		}
		adjustLevel();
	}
	
	public synchronized void adjustLevel(){

		if(LEVEL >= shapeOrderCount.length){
			endGame();
			return;
		}
		for(Shape s : shapesMap.values()){
			s.setVisible(false);
			unregisterTouchArea(s);
			s.setColor(new Color(1,1,1));
		}
		for(int i = 0; i < shapesOrderList.size(); ++i){
			detachChild(shapesOrderList.get(i));
		}
		SEQ = 0;
		Collections.shuffle(randomList);
		
		//Arrange order shapes
		shapesOrderList.clear();
		shapesChoiceList.clear();
		orderPattern.clear();
		choicePattern.clear();
		//show randomly chosen order shapes at the top
		int id;
		Shape copy;
		for(int i = 0; i < shapeOrderCount[LEVEL]; ++i)
		{
			id = randomList.get(i);
			copy = shapesMap.get(id).copy();
			shapesOrderList.add(copy);
			orderPattern.add(id);
			shapesOrderList.get(i).setPosition(250 + i * getTextureRegion(id).getWidth(), ORDER_Y_LIMIT);
			attachChild(shapesOrderList.get(i));
			shapesOrderList.get(i).setVisible(true);
		}
		//Arrange choice shapes
		for(int i = 0; i < shpeChoiceCount[LEVEL] - shapeOrderCount[LEVEL]; ++i){
			id = randomList.get(i+3+shapeOrderCount[LEVEL]);//be sure not to include order shapes for now
			shapesChoiceList.add(shapesMap.get(randomList.get(id)));
		}
		//add a copy of order shapes into choices to guarantee that choices has these order shapes
		for(int i = 0; i < shapesOrderList.size(); ++i){
			shapesChoiceList.add(shapesMap.get(shapesOrderList.get(i).ID));
		}
		Collections.shuffle(shapesChoiceList);
		
		//place choice shapes
		for(int i = 0; i < shapesChoiceList.size(); ++i){
			int randomPlace;
			do{
				randomPlace = (int)(Math.random()*SHAPE_COUNT);
			}while(choicePattern.contains(randomPlace));
			choicePattern.add(randomPlace);
		}
		Collections.shuffle(choicePattern);
		
		for(int i = 0; i < shapesChoiceList.size(); ++i){
			shapesChoiceList.get(i).setVisible(true);
			shapesChoiceList.get(i).setPosition(indexX[ choicePattern.get(i) ], //1 + (i+(i/3) * SHAPE_COLUMN_COUNT / 2) % SHAPE_COUNT
			           							indexY[ choicePattern.get(i) ]);
			if(!shapesChoiceList.get(i).hasParent())
				attachChild(shapesChoiceList.get(i));
			registerTouchArea(shapesChoiceList.get(i));
		}
	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.GAME_SHAPES_ORDER;
	}

	@Override
	public void disposeScene() {
		for(Shape s : shapesMap.values()){
			detachChild(s);
		}
		for(int i = 0; i < shapesOrderList.size(); ++i){
			detachChild(shapesOrderList.get(i));
		}
		clearTouchAreas();
		if(!this.isDisposed())dispose();
	}
	
	@Override
	public void clearScene(){
		for(Shape s : shapesMap.values()){
			s.setVisible(false);
		}
		for(int i = 0; i < shapesOrderList.size(); ++i){
			shapesOrderList.get(i).setVisible(false);
		}
		clearTouchAreas();
	}
	
	private class Shape extends Sprite{
		public int ID;
		public boolean isChoice;
		
		public Shape(float pX, float pY, int pId) {
			super(pX, pY, GameShapeOrder.getTextureRegion(pId), vbom);
			ID = pId;
			isChoice = true;
		}
		
		public Shape(Shape s){
			super(s.getX(), s.getY(), GameShapeOrder.getTextureRegion(s.ID), vbom);
		}
		
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
				float pTouchAreaLocalX, float pTouchAreaLocalY) 
		{
			if(pSceneTouchEvent.isActionDown())
			{
				if(isChoice){
					if(this.ID == orderPattern.get(SEQ)){
						SCORE += 2;
						SEQ++;
						InfoSectionScene.getInstance().setScore(SCORE);
						this.setColor(new Color(.7f, .9f, .7f));
						if(SEQ >= shapeOrderCount[LEVEL]){
							LEVEL++;
							adjustLevel();
						}
					}else{
						this.setColor(new Color(.9f, .7f, .7f));
						SCORE -= 1;
						if(SCORE < 0) SCORE = 0;
						InfoSectionScene.getInstance().setScore(SCORE);
						adjustLevel();
					}
				}
			}
			return true;
		}
		
		public Shape copy(){
			Shape s = new Shape(this.getX(), this.getY(),this.ID);
			s.isChoice = false;
			s.ID = this.ID;
			return s;
		}
	}
	
	private static ITextureRegion getTextureRegion(final int index){
		switch( index ) {
			case 0:
				return ResourcesManager.texShape1;
			case 1:
				return ResourcesManager.texShape2;
			case 2:
				return ResourcesManager.texShape3;
			case 3:
				return ResourcesManager.texShape4;
			case 4:
				return ResourcesManager.texShape5;
			case 5:
				return ResourcesManager.texShape6;
			case 6:
				return ResourcesManager.texShape7;
			case 7:
				return ResourcesManager.texShape8;
			case 8:
				return ResourcesManager.texShape9;
			case 9:
				return ResourcesManager.texShape10;
			case 10:
				return ResourcesManager.texShape11;
			case 11:
				return ResourcesManager.texShape12;
			case 12:
				return ResourcesManager.texShape13;
			case 13:
				return ResourcesManager.texShape14;
			case 14:
				return ResourcesManager.texShape15;
			case 15:
				return ResourcesManager.texShape16;
			case 16:
				return ResourcesManager.texShape17;
			case 17:
				return ResourcesManager.texShape18;
			case 18:
				return ResourcesManager.texShape19;
			case 19:
				return ResourcesManager.texShape20;
			case 20:
				return ResourcesManager.texShape21;
			case 21:
				return ResourcesManager.texShape22;
			case 22:
				return ResourcesManager.texShape23;
			case 23:
				return ResourcesManager.texShape24;
			case 24:
				return ResourcesManager.texShape25;
			case 25:
				return ResourcesManager.texShape26;
			case 26:
				return ResourcesManager.texShape27;
			case 27:
				return ResourcesManager.texShape28;
			case 28:
				return ResourcesManager.texShape29;
			case 29:
				return ResourcesManager.texShape30;
			default:
				return null;
		}
	}
}
