package tr.com.reformtek.beynium;

import java.io.IOException;

import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import tr.com.reformtek.beynium.database.DB;
import tr.com.reformtek.beynium.scenes.InfoSectionScene;
import tr.com.reformtek.beynium.util.ResourcesManager;
import tr.com.reformtek.beynium.util.SceneManager;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends BaseGameActivity 
{
		public static final float CAMERA_HEIGHT = 600;
		public static final float CAMERA_WIDTH = 1024;
		
		public static final int SCREEN_WIDTH = 1024;
		public static final int SCREEN_HEIGHT = 600;
		public static DB DB;
		public Camera camera;
		@SuppressWarnings("unused")
		private ResourcesManager rm;
		
		public Handler toaster = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				Toast.makeText(getBaseContext(), msg.getData().getString("msg"), Toast.LENGTH_SHORT).show();
			}
	    };
	
		@Override
		public EngineOptions onCreateEngineOptions() {
			camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
			EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new FillResolutionPolicy(), camera);
			engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
			engineOptions.getAudioOptions().setNeedsMusic(true);
			engineOptions.getAudioOptions().setNeedsSound(true);
			return engineOptions;
		}
		
		@Override
		public Engine onCreateEngine(EngineOptions pEngineOptions) 
		{
		    return new LimitedFPSEngine(pEngineOptions, 60);
		}
	
		@Override
		public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException 
		{
			SoundFactory.setAssetBasePath("sfx/");
			MusicFactory.setAssetBasePath("sfx/");
		    ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
		    rm = ResourcesManager.getInstance();
		    DB = new DB(this);
		    pOnCreateResourcesCallback.onCreateResourcesFinished();
		}
	
		@Override
		public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
			SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
		}
	
		@Override
		public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback)
						throws IOException {
			   mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() 
			    {
			        public void onTimePassed(final TimerHandler pTimerHandler) 
			        {
			            mEngine.unregisterUpdateHandler(pTimerHandler);
			            SceneManager.getInstance().createMenuScene();
			            camera.setHUD(InfoSectionScene.getInstance());
			        }
			    }));
			    pOnPopulateSceneCallback.onPopulateSceneFinished();
		}

		@Override
		protected void onDestroy()
		{
		    super.onDestroy();
		    System.exit(0);
		}
		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) 
		{  
		    if (keyCode == KeyEvent.KEYCODE_BACK)
		    {
		        SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
		    }
		    return false; 
		}
		
		public Camera getCamera(){
			return this.camera;
		}
}
