package tr.com.reformtek.beynium.games;

import java.util.ArrayList;

import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

import tr.com.reformtek.beynium.logic.Statement;
import tr.com.reformtek.beynium.logic.Statement.OPERATION;
import tr.com.reformtek.beynium.scenes.GamePlayBaseScene;
import tr.com.reformtek.beynium.scenes.InfoSectionScene;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class GameFourOperations extends GamePlayBaseScene 
{
	public static final int STATEMENTS_COUNT = 25;
	public static final int Y_LINE = 200;
	public ArrayList<Statement> statementsList;
	Statement currentStatement;
	int statementIndex;
	int correctCount, puan;
	public OPERATION op;
	Sprite sum,subtract, multiply, divide, equal, trueSprite, falseSprite, questionSprite, opFrame;
	ScaleModifier sm;
	public float OPERATIONS_ROW = 500;
	
	public GameFourOperations(ITextureRegion tex){
		super(tex);
		informationText = "Sence bu sayılara hangi işlem uygulanmalı?";
		this.setTimeSpan(SECONDS_90);
		sm = new ScaleModifier(0.3f,2.0f,0.0f);
	}
	
	@Override
	public void createScene(){
		super.createScene();
		statementsList = new ArrayList<Statement>();
		statementIndex = 0;
		correctCount = 0;
		puan = 3;
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.GAME_FOUR_OPERATIONS;
	}

	@Override
	public void disposeScene() {
		for(int i = 0; i < statementsList.size(); ++i)
		{
			detachChild(statementsList.get(i).firstOperandText);
			detachChild(statementsList.get(i).secondOperandText);
			detachChild(statementsList.get(i).answerText);
		}
		detachChild(trueSprite);
		detachChild(falseSprite);
		detachChild(sum);
		detachChild(equal);
		detachChild(multiply);
		detachChild(subtract);
		detachChild(divide);
		detachChild(questionSprite);
    	statementsList.clear();
    	if(!isDisposed()) dispose();
	}
	
	@Override
	public void clearScene() {
		for(int i = 0; i < statementsList.size(); ++i)
		{
			statementsList.get(i).firstOperandText.setVisible(false);
			statementsList.get(i).secondOperandText.setVisible(false);
			statementsList.get(i).answerText.setVisible(false);
		}
		sum.setVisible(false);
		equal.setVisible(false);
		divide.setVisible(false);
		opFrame.setVisible(false);
		multiply.setVisible(false);
		subtract.setVisible(false);
		trueSprite.setVisible(false);
		falseSprite.setVisible(false);
		questionSprite.setVisible(false);
		clearTouchAreas();
	}
	
	@Override
	public void loadSprites() 
	{
		trueSprite 		= new Sprite(400, 400, resourcesManager.texRegTrue, vbom);
		falseSprite 	= new Sprite(400, 400, resourcesManager.texRegFalse, vbom);
		questionSprite  = new Sprite(400, 350, resourcesManager.texQuestion, vbom);
		opFrame			= new Sprite(405, 255, resourcesManager.texOpFrame, vbom);
		
		sum = new Sprite (300, 250, resourcesManager.texSum, vbom)
		{
			@Override
    		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
    		{
    			if(pSceneTouchEvent.isActionDown()){
    				evaluateAnswer(OPERATION.PLUS);
    			}
    			return true;
    		}
		};
		
		subtract = new Sprite (370, 250, resourcesManager.texSubtract, vbom)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
			{
				if(pSceneTouchEvent.isActionDown()){
					evaluateAnswer(OPERATION.MINUS);
				}
				return true;
			}
		};
		
		multiply = new Sprite (440, 250, resourcesManager.texMultiply, vbom)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
			{
				if(pSceneTouchEvent.isActionDown()){
					evaluateAnswer(OPERATION.MULTIPLY);
				}
				return true;
			}
		};
		
		divide = new Sprite (510, 250, resourcesManager.texDivide, vbom)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
			{
				if(pSceneTouchEvent.isActionDown()){
    				evaluateAnswer(OPERATION.DIVIDE);
				}
				return true;
			}
		};
		
		equal = new Sprite (580, 350, resourcesManager.texEqual, vbom);
		
		attachChild(trueSprite);
		attachChild(falseSprite);
		trueSprite.setVisible(false);
		falseSprite.setVisible(false);
		
		attachChild(sum);
		attachChild(subtract);
		attachChild(multiply);
		attachChild(divide);
		attachChild(equal);
		attachChild(opFrame);
		attachChild(questionSprite);
		
		registerTouchArea(sum);
		registerTouchArea(subtract);
		registerTouchArea(multiply);
		registerTouchArea(divide);
		fillStatements();
	}
	
	public void evaluateAnswer(OPERATION op){
		if(statementsList.get(statementIndex).operation == op){
			trueSprite.setVisible(true);
			trueSprite.registerEntityModifier(sm);
			trueSprite.resetEntityModifiers();
			SCORE += puan;
			InfoSectionScene.getInstance().setScore(SCORE);
		}else{
			falseSprite.setVisible(true);
			falseSprite.registerEntityModifier(sm);
			falseSprite.resetEntityModifiers();
			SCORE--;
			InfoSectionScene.getInstance().setScore(SCORE);
		}
		showNextStatement();
	}
	
	private synchronized void fillStatements()
	{
		final int size = statementsList.size();
		
		for(int i = 0; i < size; ++i)
		{
			detachChild(statementsList.get(i).firstOperandText);
			detachChild(statementsList.get(i).secondOperandText);
			detachChild(statementsList.get(i).answerText);
		}		
		statementsList.clear();
    	
    	for(int i = 0; i < STATEMENTS_COUNT; ++i)
    	{
    		Statement st = new Statement(resourcesManager.mathFont,4);
    		statementsList.add(st);
    	}
    	for(int i = 0; i < STATEMENTS_COUNT; ++i)
    	{
    		statementsList.get(i).firstOperandText.setPosition(questionSprite.getX() - 100, 440);
    		statementsList.get(i).secondOperandText.setPosition(questionSprite.getX() + 100, 440);
    		statementsList.get(i).answerText.setPosition(questionSprite.getX() + 260, 440);
    		
    		statementsList.get(i).firstOperandText.setHeight(256f);
    		statementsList.get(i).secondOperandText.setHeight(256f);
    		statementsList.get(i).answerText.setHeight(256f);
    	}
    	statementIndex = 0;
		attachChild(statementsList.get(statementIndex).firstOperandText);
		attachChild(statementsList.get(statementIndex).secondOperandText);
		attachChild(statementsList.get(statementIndex).answerText);
	}
	
	private void showNextStatement()
	{
		detachChild(statementsList.get(statementIndex).firstOperandText);
		detachChild(statementsList.get(statementIndex).secondOperandText);
		detachChild(statementsList.get(statementIndex).answerText);

		++statementIndex;
		if(statementIndex >= STATEMENTS_COUNT){//end of contest clear scene and display congratulations
			endGame();
			return;
		}
		attachChild(statementsList.get(statementIndex).firstOperandText);
		attachChild(statementsList.get(statementIndex).secondOperandText);
		attachChild(statementsList.get(statementIndex).answerText);
	}

}
