package tr.com.reformtek.beynium.games;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.adt.color.Color;

import tr.com.reformtek.beynium.logic.Statement;
import tr.com.reformtek.beynium.scenes.GamePlayBaseScene;
import tr.com.reformtek.beynium.scenes.InfoSectionScene;
import tr.com.reformtek.beynium.util.ResourcesManager;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class GameMathOperations extends GamePlayBaseScene 
{
	public static int STATEMENTS_COUNT_PER_PAGE = 8;
	public static int STATEMENTS_COUNT_TOTAL = 32;

	Map <Integer, Number> numbersMap;
	private Sprite cursorSprite, equalsSprite, clearSprite;
	public ArrayList<Statement> statementsList;
	Statement pastStatement, currentStatement, nextStatement;
	String answer, lastValue;
	Number numberButton;
	int [] numbersSequence;
	StringBuilder answerPreparator;
	public int answerLength,answeredCount, statementIndex, BUTTONS;
	
	public GameMathOperations(ITextureRegion tex){
		super(tex);
		informationText = "İşlemlerin sonucunu en hızlı şekilde hesapla!";
		this.setTimeSpan(SECONDS_60);
	}
	
	@Override
	public void createScene(){
		super.createScene();
		numbersMap		= new TreeMap<Integer, Number>();
		statementsList 	= new ArrayList<Statement>();
		numbersSequence = new int[]{1,2,3,4,5,6,7,8,9,0};//maybe calculator standard 7,8,9,4,5,6,1,2,3,0
		statementIndex 	= answeredCount = 0 ;
		answerPreparator= new StringBuilder(16);
		BUTTONS = 10;
		answerLength = 0;
	}

	@Override
	public final SceneType getSceneType() {
		return SceneType.GAME_MATH_OPERATIONS;
	}

	@Override
	public void disposeScene() {
		for(int i = 0; i < statementsList.size(); ++i)
		{
			detachChild(statementsList.get(i).stQuestionText);
		}
    	statementsList.clear();
    	detachChild(cursorSprite);
    	if(!isDisposed()) dispose();
	}
	
	@Override
	public void clearScene() {
		for(int i = 0; i < statementsList.size(); ++i){
			statementsList.get(i).stQuestionText.setVisible(false);
		}
		for(Number n : numbersMap.values()){
			n.setVisible(false);
		}
    	cursorSprite.setVisible(false);
    	equalsSprite.setVisible(false);
    	clearSprite.setVisible (false);
	}
	
	@Override
	public void loadSprites() 
	{
		// TODO iki kere çağırmadan ilk açılışta yüklemiyor kontrol edilecek
		fillStatements();
		prepateCalculator();
		
    	cursorSprite = new Sprite(60, statementsList.get(statementIndex).stQuestionText.getY(), resourcesManager.texRegCursor, vbom);
    	cursorSprite.setScale(1.750f);
    	
    	clearSprite = new Sprite(numbersMap.get(0).getX()+resourcesManager.texClear.getWidth()/2, 120, resourcesManager.texClear, vbom)
    	{
    		@Override
    		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
    		{
    			if(pSceneTouchEvent.isActionDown())
    			{
    				if(answerLength == 0 || currentStatement.answerString.equalsIgnoreCase("?")) return true;
    				answerPreparator.replace(answerPreparator.indexOf("=") + 1, answerPreparator.length(), "?");
    				currentStatement.stQuestionText.setText(answerPreparator.toString());
    				currentStatement.answerString = "?";
    				answerPreparator.setLength(0);
    				answerLength = 0;
    			}
    			return true;
    		}
    	};
    	
    	equalsSprite = new Sprite(clearSprite.getX() - resourcesManager.texClear.getWidth(), 120, resourcesManager.texEquals, vbom)
    	{
    		@Override
    		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
    		{
    			if(pSceneTouchEvent.isActionDown())
    			{
    				answer = answerPreparator.substring(answerPreparator.indexOf("=")+1, answerPreparator.length()).trim();
    				try{
    					if(Integer.parseInt(answer.trim()) == currentStatement.correctValue) {
    						SCORE += 5;
    						InfoSectionScene.getInstance().setScore(SCORE);
    						currentStatement.stQuestionText.setColor(Color.GREEN_ABGR_PACKED_INT);
    					}
    					else{
    						SCORE -= 2;
    						if(SCORE < 0) SCORE = 0;
    						InfoSectionScene.getInstance().setScore(SCORE);
    						currentStatement.stQuestionText.setColor(Color.RED);
    					}
    				}catch(NumberFormatException ex){
    					return true;
    				}

    				if(++answeredCount >= STATEMENTS_COUNT_TOTAL){
    					endGame();
    					return true;
    				}
    				
    				nextStatement = statementsList.get((++statementIndex)%STATEMENTS_COUNT_PER_PAGE);
    				cursorSprite.setPosition(40, nextStatement.stQuestionText.getY());
    				answerPreparator.setLength(0);
    				currentStatement = nextStatement;
    				if(statementIndex == STATEMENTS_COUNT_PER_PAGE){
    					cursorSprite.setPosition(30, 550);
    					fillStatements();
    				}
    				answerLength = 0;
    			}
    			return true;
    		}
    	};
    	
    	// TODO iki kere çağırmadan ilk açılışta yüklemiyor kontrol edilecek
    	fillStatements();
    	attachChild(equalsSprite);
    	attachChild(clearSprite);
    	attachChild(cursorSprite);
    	registerTouchArea(equalsSprite);
    	registerTouchArea(clearSprite);
	}
	
	public void prepateCalculator(){
		int index, value;
		float pX, pY;
		index = 0;
		for(int row = 0; row < 4; ++row){
			for(int column = 0; column < 3; ++column){
				if(index >= numbersSequence.length) break;
				value = numbersSequence[index++];
				
				pX = 520 + column * (GameMathOperations.getTextureRegion(value).getWidth()/2 + 12);
				pY = 500 - row    * (GameMathOperations.getTextureRegion(value).getHeight()/2 + 12);
				numberButton = new Number(pX, pY, value);
				numbersMap.put(value,numberButton);
				numbersMap.get(value).setScale(0.6f);
				attachChild(numbersMap.get(value));
				registerTouchArea(numbersMap.get(value));
			}
		}
		numbersMap.get(0).setPosition(numbersMap.get(8).getX(),numbersMap.get(0).getY()); //set 0's position in the middle of bottom
	}
	
	private synchronized void fillStatements()
	{
		final int size = statementsList.size();
		
		for(int i = 0; i < size; ++i)
			detachChild(statementsList.get(i).stQuestionText);
    	statementsList.clear();
    	Statement st;
    	for(int i = 0; i < STATEMENTS_COUNT_PER_PAGE; ++i)
    	{
    		st = new Statement(resourcesManager.fontPencil, 3);
    		statementsList.add(st);
    	}
    	
    	for(int i = 0; i < STATEMENTS_COUNT_PER_PAGE; ++i)
    	{
    		statementsList.get(i).stQuestionText.setPosition(260,530-i*64);
    		statementsList.get(i).stQuestionText.setColor(.7f,.7f,.7f);
    		attachChild(statementsList.get(i).stQuestionText);
    	}
    	statementIndex = 0;
	}
	
	private class Number extends Sprite {
		public int value;
		
		public Number(float pX, float pY, final int value) {
			super(pX, pY, GameMathOperations.getTextureRegion(value), vbom);
			this.value = value;
		}
		
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
				float pTouchAreaLocalX, float pTouchAreaLocalY){
			if(pSceneTouchEvent.isActionDown())
			{
				if(answerLength > 3) return true;
				answerLength++;
				answer = Integer.toString(this.value);
				currentStatement = statementsList.get(statementIndex % STATEMENTS_COUNT_PER_PAGE);

				if(currentStatement.answerString.equalsIgnoreCase("?") || answerPreparator.length() == 0)
					answerPreparator.append(currentStatement.stQuestionText.getText());
				if(!answerPreparator.toString().endsWith("?"))
					answerPreparator.append(answer);
				else
					answerPreparator.replace(answerPreparator.indexOf("=") + 1, answerPreparator.length(), answer);
				
				currentStatement.stQuestionText.setText(answerPreparator.toString());
				currentStatement.answerString = answer;
			}
			return true;
		}
	}
	
	private static ITextureRegion getTextureRegion(int index){
		switch( index ) {
			case 0:
				return ResourcesManager.tex0;
			case 1:
				return ResourcesManager.tex1;
			case 2:
				return ResourcesManager.tex2;
			case 3:
				return ResourcesManager.tex3;
			case 4:
				return ResourcesManager.tex4;
			case 5:
				return ResourcesManager.tex5;
			case 6:
				return ResourcesManager.tex6;
			case 7:
				return ResourcesManager.tex7;
			case 8:
				return ResourcesManager.tex8;
			case 9:
				return ResourcesManager.tex9;
			default:
				return ResourcesManager.tex0;
		}
	}
}

