package tr.com.reformtek.beynium.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;

import tr.com.reformtek.beynium.MainActivity;
import tr.com.reformtek.beynium.util.ResourcesManager;
import tr.com.reformtek.beynium.util.SceneManager;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

import android.os.Bundle;
import android.os.Message;

public class GamePlayBaseScene extends BaseScene {
	public int SCORE;
	public int HIGH_SCORE;
	public static final int SECONDS_60 	= 60;
	public static final int SECONDS_90 	= 90;
	public GamePlayBaseScene() {
		SCORE = 0;
		
		attachChild(new Sprite(MainActivity.SCREEN_WIDTH/2,MainActivity.SCREEN_HEIGHT/2, resourcesManager.texRegGameBackground, vbom)
		{
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) 
			{
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		});
		setBackgroundEnabled(false);
		loadSprites();
	}
	
	public GamePlayBaseScene(ITextureRegion tex) {
		SCORE = 0;
		attachChild(new Sprite(MainActivity.SCREEN_WIDTH/2,MainActivity.SCREEN_HEIGHT/2, tex, vbom)
		{
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) 
			{
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		});
		setBackgroundEnabled(false);
		loadSprites();
	}

	@Override
	public void createScene() {
		SCORE = 0;
		HIGH_SCORE = MainActivity.DB.selectHighScore(this.getSceneType().name());
	}

	@Override
	public void onBackKeyPressed(){
		InfoSectionScene.getInstance().onBackKeyPressed();
		SceneManager.getInstance().setSceneType(SceneType.SCENE_GAME);
		disposeScene();
	}

	@Override
	public SceneType getSceneType() {
		return null;
	}

	@Override
	public void disposeScene() {
	}
	
	public void loadSprites(){
	}
	
	@Override
	public void clearScene(){
	}
	
	public void updateDatabase(){
		if(HIGH_SCORE < SCORE){
			MainActivity.DB.updateHighScore(this.getSceneType().name(), SCORE);
			HIGH_SCORE = SCORE;
		}
		MainActivity.DB.updateLastScore(this.getSceneType().name(), SCORE);
	}
	
	@Override
	public void makeToast(String str){
		Message status = ResourcesManager.getInstance().activity.toaster.obtainMessage();
		Bundle datax = new Bundle();
		datax.putString("msg", str);
		status.setData(datax);
		ResourcesManager.getInstance().activity.toaster.sendMessage(status);
    }

	@Override
	public void makeToast(){
		Message status = ResourcesManager.getInstance().activity.toaster.obtainMessage();
		Bundle datax = new Bundle();
		datax.putString("msg", informationText);
		status.setData(datax);
		ResourcesManager.getInstance().activity.toaster.sendMessage(status);
    }

	public void endGame() {
		SCORE += SCORE/4 ;
		InfoSectionScene.getInstance().setScore(SCORE);
		InfoSectionScene.getInstance().showGreeting();
	}
}
