package tr.com.reformtek.beynium.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import tr.com.reformtek.beynium.MainActivity;
import tr.com.reformtek.beynium.util.ResourcesManager;
import tr.com.reformtek.beynium.util.SceneManager;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class CountdownScene extends BaseScene {

	private int COUNT;
	
	private SceneType screen;
	public AnimatedSprite countdownSprite;
	private TimerHandler timerHandler;
	@Override
	public void createScene() {
		COUNT = 2;
		attachChild(new Sprite(MainActivity.SCREEN_WIDTH/2,
							   MainActivity.SCREEN_HEIGHT/2, 
							   resourcesManager.texRegGameBackground,
							   vbom)
		{
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) 
			{
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		});
		countdownSprite = new AnimatedSprite(MainActivity.SCREEN_WIDTH/2, 
											 MainActivity.SCREEN_HEIGHT/2, 
											 resourcesManager.texCountdownTile,
											 vbom);
		setBackgroundEnabled(false);
		attachChild(countdownSprite);
		countdownSprite.setCurrentTileIndex(COUNT);
	}
	
	@Override
	public void onBackKeyPressed() {
		COUNT = 3;
		ResourcesManager.getInstance().activity.getEngine().unregisterUpdateHandler(timerHandler);
		InfoSectionScene.getInstance().onBackKeyPressed();
		SceneManager.getInstance().setSceneType(SceneType.SCENE_GAME);
		disposeScene();
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.COUNTDOWN_SCENE;
	}

	@Override
	public void disposeScene() {
		this.clearUpdateHandlers();
		if(!isDisposed())dispose();
	}
	
	public void setSceneType(SceneType sc) {
		screen = sc;
	}
	
	public synchronized void start(){
		SceneManager.getInstance().setScene(this);
		if(SceneManager.getInstance().getSceneInstance(screen) != null)
			SceneManager.getInstance().getSceneInstance(screen).makeToast();
		ResourcesManager.getInstance().activity.getEngine().registerUpdateHandler(new TimerHandler(1f, true, new ITimerCallback(){
	        @Override
	        public void onTimePassed(TimerHandler pTimerHandler) {
	        	timerHandler = pTimerHandler;
	        	int index = (((--COUNT % 3) + 3) % 3);
	        	countdownSprite.setCurrentTileIndex(index);
	            if(COUNT < 0)
	            {
	            	COUNT = 3;
	            	ResourcesManager.getInstance().activity.getEngine().unregisterUpdateHandler(timerHandler);
	            	InfoSectionScene.getInstance().prepareGameItems();
	            	InfoSectionScene.getInstance().startTimer(SceneManager.getInstance().getSceneInstance(screen).getTimeSpan());
	            	SceneManager.getInstance().loadGameScene(screen);
	            }
	        }
	    }));
	}

	@Override
	public void makeToast(String str) {
		
	}

	@Override
	public void makeToast() {
		
	}

	@Override
	public void clearScene() {
	}
	
}
