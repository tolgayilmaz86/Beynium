package tr.com.reformtek.beynium.scenes;

import org.andengine.entity.sprite.Sprite;

import tr.com.reformtek.beynium.MainActivity;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class SplashScene extends BaseScene
{
	private Sprite splash;

	@Override
	public void createScene()
	{
		splash = new Sprite(0, 0, resourcesManager.splash_region, vbom);
		splash.setPosition(MainActivity.SCREEN_WIDTH/2, MainActivity.SCREEN_HEIGHT/2);
		attachChild(splash);
	}

	@Override
	public void onBackKeyPressed(){
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	public SceneType getSceneType(){
		return SceneType.SCENE_SPLASH;
	}

	@Override
	public void disposeScene(){
		splash.detachSelf();
		splash.dispose();
		this.detachSelf();
		this.dispose();
	}

	@Override
	public void clearScene() {
	}

	@Override
	public void makeToast(String str) {
	}

	@Override
	public void makeToast() {
	}
}
