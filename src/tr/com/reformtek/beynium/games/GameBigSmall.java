package tr.com.reformtek.beynium.games;

import java.util.ArrayList;

import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

import tr.com.reformtek.beynium.logic.Statement;
import tr.com.reformtek.beynium.scenes.GamePlayBaseScene;
import tr.com.reformtek.beynium.scenes.InfoSectionScene;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class GameBigSmall extends GamePlayBaseScene 
{
	private static final int STATEMENTS_COUNT = 25;

	ScaleModifier sm;
	int statementIndex;
	private Sprite greaterSprite, lessSprite, trueSprite, falseSprite;
	private ArrayList<Statement> leftStatementsList, rightStatementsList;
	
	@Override
	public void createScene(){
		super.createScene();
		leftStatementsList = new ArrayList<Statement>();
		rightStatementsList = new ArrayList<Statement>();
		informationText =  "Sence hangi sayı daha büyük?";//resourcesManager.activity.getResources(R.string.gameBigSmalInfo);// "Sence hangi sayı daha büyük?";
		sm = new ScaleModifier(0.3f,2.0f,0.0f);
		statementIndex = 0;
		setTimeSpan(SECONDS_60);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.GAME_MATH_BIG_SMALL;
	}

	@Override
	public void disposeScene()
	{
		for(int i = 0; i < leftStatementsList.size(); ++i){
			detachChild(leftStatementsList.get(i).pureStatementText);
			detachChild(rightStatementsList.get(i).pureStatementText);
		}
		leftStatementsList.clear();
		rightStatementsList.clear();
		
		unregisterTouchArea(lessSprite);
		unregisterTouchArea(greaterSprite);
		detachChild(lessSprite);
		detachChild(greaterSprite);
		if(!isDisposed()) dispose();
	}
	
	@Override
	public void clearScene(){
		for(int i = 0; i < leftStatementsList.size(); ++i){
			leftStatementsList.get(i).pureStatementText.setVisible(false);
			rightStatementsList.get(i).pureStatementText.setVisible(false);
		}
		lessSprite.setVisible(false);
		greaterSprite.setVisible(false);
		unregisterTouchArea(lessSprite);
		unregisterTouchArea(greaterSprite);
		clearUpdateHandlers();
	}
	
	@Override
	public void loadSprites()
	{
		trueSprite 		= new Sprite(400, 400, resourcesManager.texRegTrue, vbom);
		falseSprite 	= new Sprite(400, 400, resourcesManager.texRegFalse, vbom);
    	
		greaterSprite 	= new Sprite(340, 300, resourcesManager.texRegGreater, vbom)
    	{
    		@Override
    		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
    		{
    			if(pSceneTouchEvent.isActionDown())
    			{
    				if(isCorrect(Side.RIGHT))
    				{
    					trueSprite.setVisible(true);
    			    	trueSprite.registerEntityModifier(sm);
    					trueSprite.resetEntityModifiers();
    					SCORE += 4;
    					InfoSectionScene.getInstance().setScore(SCORE);
    				}
    				else {
    					falseSprite.setVisible(true);
    			    	falseSprite.registerEntityModifier(sm);
    					falseSprite.resetEntityModifiers();
    					SCORE -= 1;
    					if(SCORE < 0) SCORE = 0;
    					InfoSectionScene.getInstance().setScore(SCORE);
    				}
    				showNextStatement();
    			}
    			return true;
    		}
    	};
    	
    	lessSprite = new Sprite(405 + resourcesManager.texRegLess.getWidth()/2, 300, resourcesManager.texRegLess, vbom)
    	{
    		@Override
    		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
    		{
    			if(pSceneTouchEvent.isActionDown())
    			{
    				if(isCorrect(Side.LEFT))
    				{   
    					trueSprite.setVisible(true);
    			    	trueSprite.registerEntityModifier(sm);
    					trueSprite.resetEntityModifiers();
    					SCORE += 4;
    					InfoSectionScene.getInstance().setScore(SCORE);
    				}
    				else {
    					falseSprite.setVisible(true);
    			    	falseSprite.registerEntityModifier(sm);
    					falseSprite.resetEntityModifiers();
    					SCORE -= 1;
    					if(SCORE < 0) SCORE = 0;
    					InfoSectionScene.getInstance().setScore(SCORE);
    				}
    				showNextStatement();
    			}
    			return true;
    		}
    	};
    	fillStatements();
    	attachChild(greaterSprite);
    	attachChild(lessSprite);
    	
    	attachChild(falseSprite);
    	attachChild(trueSprite);
    	falseSprite.setVisible(false);
    	trueSprite.setVisible(false);

    	registerTouchArea(greaterSprite);
    	registerTouchArea(lessSprite);
	}
	
	private void showNextStatement(){
		detachChild(rightStatementsList.get(statementIndex).pureStatementText);
		detachChild(leftStatementsList.get(statementIndex).pureStatementText);

		++statementIndex;
		if(statementIndex >= STATEMENTS_COUNT){//end of contest clear scene and display congrulations
			unregisterTouchArea(greaterSprite);
			unregisterTouchArea(lessSprite);
			detachChild(lessSprite);
			detachChild(greaterSprite);
			
			detachChild(trueSprite);
			detachChild(falseSprite);
			endGame();
			return;
		}
		attachChild(rightStatementsList.get(statementIndex).pureStatementText);
		attachChild(leftStatementsList.get(statementIndex).pureStatementText);
	}
	
	private boolean isCorrect(Side s){
		boolean b = false;
		if(leftStatementsList.get(statementIndex).correctValue > rightStatementsList.get(statementIndex).correctValue && s == Side.RIGHT)
			b = true;
		else if(leftStatementsList.get(statementIndex).correctValue < rightStatementsList.get(statementIndex).correctValue && s == Side.LEFT)
			b = true;
		return b;
	}
	
	private void fillStatements()
	{
		final int size = leftStatementsList.size();
		
		for(int i = 0; i < size; ++i){
			detachChild(leftStatementsList.get(i).pureStatementText);
			detachChild(rightStatementsList.get(i).pureStatementText);
		}		
		leftStatementsList.clear();
		rightStatementsList.clear();
		Statement stLeft, stRight;
    	for(int i = 0; i < STATEMENTS_COUNT; ++i)
    	{
    		stLeft = new Statement(resourcesManager.fontPencil, 3);
    		stRight = new Statement(resourcesManager.fontPencil, 3);
    		
    		//guarantee that both side is not equal since we are looking for the big one
    		while(stLeft.correctValue == stRight.correctValue)
    			stLeft = new Statement(resourcesManager.fontPencil, 3);
    		stLeft.pureStatementText.setColor(new Color(1,0,0));
    		stRight.pureStatementText.setColor(new Color(1,0.7f,0));
    		
    		leftStatementsList.add(stLeft);
    		rightStatementsList.add(stRight);
    	}
    	for(int i = 0; i < STATEMENTS_COUNT; ++i)
    	{
    		leftStatementsList.get(i).pureStatementText.setPosition(greaterSprite.getX() - leftStatementsList.get(i).pureStatementText.getWidth()/2 - greaterSprite.getWidth()+20,
    																greaterSprite.getY());
    		rightStatementsList.get(i).pureStatementText.setPosition(lessSprite.getX() + rightStatementsList.get(i).pureStatementText.getWidth()/2 + lessSprite.getWidth()-20,
    																 lessSprite.getY());
    		
    		leftStatementsList.get(i).pureStatementText.setScale(1.5f);
    		rightStatementsList.get(i).pureStatementText.setScale(1.5f);
    	}
    	statementIndex = 0;
    	
		attachChild(rightStatementsList.get(statementIndex).pureStatementText);
		attachChild(leftStatementsList.get(statementIndex).pureStatementText);
	}
	
	private enum Side{
		RIGHT,
		LEFT
	}

}
