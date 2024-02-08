package tr.com.reformtek.beynium.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.align.HorizontalAlign;

import tr.com.reformtek.beynium.MainActivity;
import tr.com.reformtek.beynium.scenes.GamePlayBaseScene;
import tr.com.reformtek.beynium.scenes.InfoSectionScene;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class GameMathBalloonScene extends GamePlayBaseScene 
{
	private static final int 	BALL_COUNT 		= 6;
	private static final int 	LEVEL_COUNT 	= 12;
	private static final float 	MAX_NUMBER 		= 90;
	private static final float 	BALL_VELOCITY 	= .9f;

	private Ball ball;
	private FixtureDef fixdef;
	private PhysicsConnector pc;
	private int LEVEL, COUNT;
	private List<Integer> values;
	private PhysicsWorld physicsWorld;
	Map <Integer, Ball> circleSpriteMap;
	private Sprite bottomWall, topWall, leftWall, rightWall;
	private Body bottomWallBody, topWallBody, leftWallBody, rightWallBody;

	@Override
	public void createScene(){
		super.createScene();
		LEVEL = COUNT 	= 0;
		circleSpriteMap = new TreeMap<Integer,Ball>();
		fixdef 			= PhysicsFactory.createFixtureDef(0.0f, 1.0f, 0.0f);
		physicsWorld 	= new PhysicsWorld(new Vector2(0,0), false);
		values			= new ArrayList<Integer>(6);
		
		informationText = "Haydi bakalım; topları küçükten büyüğe doğru yakalayalım!";
		setTimeSpan(SECONDS_60);
	}

	@Override
	public SceneType getSceneType(){
		return SceneType.GAME_MATH_BALLOON;
	}

	@Override
	public void disposeScene() {
		for(Ball ball : circleSpriteMap.values()){
			detachChild(ball);
		}
		clearTouchAreas();
		clearEntityModifiers();
		circleSpriteMap.clear();
		if(!isDisposed()) dispose();
	}
	
	@Override 
	public void clearScene(){
		clearTouchAreas();
		clearEntityModifiers();
		for(Ball ball : circleSpriteMap.values()){
			unregisterTouchArea(ball);
			ball.setVisible(false);
			detachChild(ball);
		}
	}
	
	@Override
	public void loadSprites(){
		bottomWall 	= new Sprite(resourcesManager.texHorizontalWall.getWidth()/2,
								resourcesManager.texHorizontalWall.getHeight()/2,
								resourcesManager.texHorizontalWall, vbom);
		
		topWall 	= new Sprite(resourcesManager.texHorizontalWall.getWidth()/2,
							 	MainActivity.CAMERA_HEIGHT - resourcesManager.texHorizontalWall.getHeight()/2,
							 	resourcesManager.texHorizontalWall, vbom);
		
		leftWall 	= new Sprite(resourcesManager.texVerticalWall.getWidth()/2,
							  	MainActivity.CAMERA_HEIGHT/2,
							  	resourcesManager.texVerticalWall, vbom);
		
		rightWall 	= new Sprite(topWall.getWidth() - resourcesManager.texVerticalWall.getWidth()/2,
								MainActivity.CAMERA_HEIGHT/2,
							   	resourcesManager.texVerticalWall, vbom);
		
		bottomWallBody 	= PhysicsFactory.createBoxBody(physicsWorld, bottomWall, BodyType.StaticBody, fixdef);
		topWallBody 	= PhysicsFactory.createBoxBody(physicsWorld, topWall, 	 BodyType.StaticBody, fixdef);
		leftWallBody 	= PhysicsFactory.createBoxBody(physicsWorld, leftWall, 	 BodyType.StaticBody, fixdef);
		rightWallBody 	= PhysicsFactory.createBoxBody(physicsWorld, rightWall,  BodyType.StaticBody, fixdef);
		
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(bottomWall, bottomWallBody, false, false));
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(topWall, 	topWallBody, 	false, false));
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(leftWall, 	leftWallBody, 	false, false));
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(rightWall, 	rightWallBody, 	false, false));
		
		this.registerUpdateHandler(physicsWorld);

		attachChild(bottomWall);
		attachChild(topWall);
		attachChild(leftWall);
		attachChild(rightWall);
		reloadBalls();
	}
	
	private void reloadBalls(){
		COUNT = 0;
		if(LEVEL >= LEVEL_COUNT){ 
			clearScene();
			endGame();
			return;
			}
		clearEntityModifiers();
		clearTouchAreas();
		for(Ball ball : circleSpriteMap.values()){
			ball.unregisterUpdateHandler(physicsWorld);
			detachChild(ball);
		}
		circleSpriteMap.clear();
		for(int i = BALL_COUNT-1; i > -1; --i){//for performance issues
			ball = new Ball(200 + i * 115, 100 + i * 50);
			registerTouchArea( ball );
			circleSpriteMap.put( ball.value, ball );
			values.add(ball.value);
			attachChild( ball );
		}
		Collections.sort(values);
	}

	public boolean minValueBall(final int value){
		if(values.get(0) == value) {
			values.remove(0);
			return true;
		}
		return false;
	}
	
	private final class Ball extends Sprite{
		private Text valueText;
		public int value;
		public Body body;
		public final ScaleModifier sm = new ScaleModifier(0.2f,1.0f,0.0f);
		
		public Ball(final float pX, final float pY){
			super(pX, pY, resourcesManager.texRegCircle, vbom);
			
			do{
				value = (int)(Math.random() * MAX_NUMBER + 1);
			}while(circleSpriteMap.containsKey(value));
			
			valueText = new Text(resourcesManager.texRegCircle.getWidth()/2, 
								 resourcesManager.texRegCircle.getHeight()/2, 
								 resourcesManager.font, Integer.toString(value), 
								 new TextOptions(HorizontalAlign.CENTER), vbom);
			
			body = PhysicsFactory.createCircleBody(physicsWorld, this, BodyType.DynamicBody, fixdef);
			body.setLinearVelocity( (float) Math.pow(-1, value%2) * BALL_VELOCITY, (float) Math.pow(-1, value%2) * BALL_VELOCITY);
			body.setAwake(true);
			
			pc = new PhysicsConnector(this, body, true, false);
			physicsWorld.registerPhysicsConnector(pc);
//			registerUpdateHandler(physicsWorld);
			
			valueText.setColor((float)Math.random()+0.1f, (float)Math.random()+0.1f, (float)Math.random()+0.1f);
			this.attachChild(valueText);
		}
			
		@Override
		protected void onManagedUpdate(final float pSecondsElapsed){
			super.onManagedUpdate(pSecondsElapsed);
		}
		
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
			if(pSceneTouchEvent.isActionDown()){
				if(minValueBall(this.value)){
					this.registerEntityModifier(sm);
					COUNT++;
					SCORE += 2;
					InfoSectionScene.getInstance().setScore(SCORE);
					if(COUNT >= BALL_COUNT){
						SCORE += 5;
						InfoSectionScene.getInstance().setScore(SCORE);
						LEVEL++;
						reloadBalls();
					}
				}
				else{
					if(SCORE > 0)
						SCORE -=1;
					InfoSectionScene.getInstance().setScore(SCORE);
				}
			}
			return true;
		}
	}
}
