package tr.com.reformtek.beynium.scenes;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import tr.com.reformtek.beynium.MainActivity;
import tr.com.reformtek.beynium.util.ResourcesManager;
import tr.com.reformtek.beynium.util.SceneManager;
import tr.com.reformtek.beynium.util.SceneManager.SceneType;

public class GamesScene extends BaseScene implements IOnMenuItemClickListener{
	public static final int FRAME_WIDTH 	= 800;
	public static final int FRAME_HEIGHT 	= 600;

	GamesMenuItem 		btnGame1, btnGame2, btnGame3, btnGame4,
	 			  		btnGame5, btnGame6, btnGame7, btnGame8;
	String 				instructions;
	public int 			openSceneID;
	private MenuScene 	menuGames;
	public InfoWindow 	infoWindow;
	ITextureRegion 		texRegGamesBackground;
	private BitmapTextureAtlas gamesBackgroundTextureAtlas;

	@Override
	public void createScene() {
		openSceneID = 0;
		instructions = "Oyuna başlamak için önce bir oyun seçmen gerekli..\n";
		createBackground();
	}

	@Override
	public void onBackKeyPressed() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene(){
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		return false;
	}
	
	public void createBackground(){
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(ResourcesManager.ASSET_BASE_PATH);
    	
    	gamesBackgroundTextureAtlas = new BitmapTextureAtlas(resourcesManager.activity.getTextureManager(), 
    														MainActivity.SCREEN_WIDTH, 
    														MainActivity.SCREEN_WIDTH, 
    														TextureOptions.BILINEAR);
    	texRegGamesBackground 		= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gamesBackgroundTextureAtlas, 
    																				   		activity, ResourcesManager.GAMES_MENU_BACKGROUND,0,0);
    	gamesBackgroundTextureAtlas.load();
    	
		attachChild(new Sprite(MainActivity.SCREEN_WIDTH/2,MainActivity.SCREEN_HEIGHT/2, texRegGamesBackground, vbom));
		loadGameButtons();
		setBackgroundEnabled(true);
	}
	
	public void loadGameButtons(){
		menuGames = new MenuScene(camera);
		menuGames.setPosition(-280, 0);
		
		infoWindow = new InfoWindow (700, 300, resourcesManager.texInfoBackground, vbom);
		
		btnGame1 = new GamesMenuItem(SceneType.GAME_MATH_BALLOON.ordinal(), 	resourcesManager.texGame1Button, vbom);
    	btnGame2 = new GamesMenuItem(SceneType.GAME_MATH_OPERATIONS.ordinal(), 	resourcesManager.texGame2Button, vbom);
		btnGame3 = new GamesMenuItem(SceneType.GAME_MATH_BIG_SMALL.ordinal(), 	resourcesManager.texGame3Button, vbom);
		btnGame4 = new GamesMenuItem(SceneType.GAME_MEMORY_LAMPS.ordinal(), 	resourcesManager.texGame4Button, vbom);
		btnGame5 = new GamesMenuItem(SceneType.GAME_SHAPES_ORDER.ordinal(), 	resourcesManager.texGame5Button, vbom);
		btnGame6 = new GamesMenuItem(SceneType.GAME_SAME_TILES.ordinal(), 		resourcesManager.texGame6Button, vbom);
		btnGame7 = new GamesMenuItem(SceneType.GAME_FOUR_OPERATIONS.ordinal(), 	resourcesManager.texGame7Button, vbom);
		btnGame8 = new GamesMenuItem(SceneType.GAME_MEMORIZE_IMAGES.ordinal(), 	resourcesManager.texGame8Button, vbom);
		
		menuGames.addMenuItem(btnGame1);
		menuGames.addMenuItem(btnGame2);
		menuGames.addMenuItem(btnGame3);
		menuGames.addMenuItem(btnGame4);
		menuGames.addMenuItem(btnGame5);
		menuGames.addMenuItem(btnGame6);
		menuGames.addMenuItem(btnGame7);
		menuGames.addMenuItem(btnGame8);
		
		menuGames.buildAnimations();
		menuGames.setBackgroundEnabled(false);
		menuGames.setOnMenuItemClickListener(this);
		
		// First row
		btnGame1.setPosition(390,470);
		btnGame2.setPosition(600,470);
		btnGame3.setPosition(810,470);
		btnGame4.setPosition(1020,470);
		
		// Second row
		btnGame5.setPosition(390,180);
		btnGame6.setPosition(600,180);
		btnGame7.setPosition(810,180);
		btnGame8.setPosition(1020,180);
		
		setChildScene(menuGames,false,false,false);
		menuGames.attachChild(infoWindow);
		infoWindow.setVisible(false);
		InfoSectionScene.getInstance().setVisible(true);
		registerTouchArea(infoWindow);
	}

	public void switchTouchable(boolean enable){
		if(!enable){
			unregisterTouchArea(btnGame1);
			unregisterTouchArea(btnGame2);
			unregisterTouchArea(btnGame3);
			unregisterTouchArea(btnGame4);
			unregisterTouchArea(btnGame5);
			unregisterTouchArea(btnGame6);
			unregisterTouchArea(btnGame7);
			unregisterTouchArea(btnGame8);
		}
		else {
			registerTouchArea(btnGame1);
			registerTouchArea(btnGame2);
			registerTouchArea(btnGame3);
			registerTouchArea(btnGame4);
			registerTouchArea(btnGame5);
			registerTouchArea(btnGame6);
			registerTouchArea(btnGame7);
			registerTouchArea(btnGame8);
		}
	}

	public class InfoWindow extends Sprite 
	{
		Sprite playButton;
		Text instructionsText;
		public InfoWindow(float pX, float pY, ITextureRegion pTextureRegion,
				VertexBufferObjectManager pVertexBufferObjectManager) {
			
			super(pX, pY,pTextureRegion, pVertexBufferObjectManager);
			instructionsText = new Text(300, 210, resourcesManager.instructionsFont, instructions, 512, 
										new TextOptions(HorizontalAlign.CENTER), resourcesManager.vbom);

			playButton = new Sprite(800, 200, resourcesManager.texPlayButton, resourcesManager.vbom){
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float pTouchAreaLocalX, float pTouchAreaLocalY) 
				{
					if(!this.isVisible()) return true;
					if(pSceneTouchEvent.isActionDown()){
						infoWindow.setVisible(false); 
						InfoSectionScene.getInstance().setVisible(true);
						switchTouchable(true);
						if(openSceneID < 0) return true;
						SceneManager.getInstance().countdownScene.setSceneType(SceneType.values()[openSceneID]);
						SceneManager.getInstance().countdownScene.start();
						openSceneID = -1;
					}
					return true;
				}
			};
			this.attachChild(playButton);
			this.attachChild(instructionsText);
			instructionsText.setVisible(true);
			registerTouchArea(this.playButton);
		}
		
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
				float pTouchAreaLocalX, float pTouchAreaLocalY) 
		{
			if(pSceneTouchEvent.isActionDown()){
				this.setVisible(false);
			}
			return true;
		}
	}
	
	public class GamesMenuItem extends SpriteMenuItem {
		public BitmapTextureAtlas selectionBoxTextureAtlas;
		public ITextureRegion texSelectionBox;
		public int sceneID = 0;

		public GamesMenuItem(int pID, ITextureRegion pTextureRegion,
				VertexBufferObjectManager vbom) {
			super(pID, pTextureRegion, vbom);
			sceneID = pID;
			//this is common in all menu items 
			attachChild(new Sprite(80,120, resourcesManager.texGameSelectionBox, vbom));
		}

		@Override
		public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, 
				final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
			if(pSceneTouchEvent.isActionDown()){
				if(infoWindow.isVisible()) {
					infoWindow.setVisible(false);
					InfoSectionScene.getInstance().setVisible(true);
					unregisterTouchArea(infoWindow.playButton);
					return true;
				}
				openSceneID = this.sceneID;
				setInformationText(openSceneID);
				infoWindow.setVisible(true);
				InfoSectionScene.getInstance().setVisible(false);
				registerTouchArea(infoWindow.playButton);
				infoWindow.instructionsText.setText(instructions);
				switchTouchable(false);
			}
			return true;
		}
	}
	
	public void setInformationText(int sceneId){
		instructions = "";
		if(sceneId == SceneType.GAME_MATH_BALLOON.ordinal()){
			instructions = " << Sayı Sıralama >>\n " +
					"Sağa sola kaçışmya başlayan topları yapabileceğiniz\n" +
					"en kısa sürede küçükten büyüğe doğru yakalamaya çalışın.\n" +
					"Hepsi bittiginde yeni toplar gorunecek, zaman bitmeden\n" +
					"yakalayabildiginiz kadar yakalayin!\n";
		}
		else if(sceneId == SceneType.GAME_MATH_OPERATIONS.ordinal()){
			instructions = " << Hızlı Matematik >>\n " +
					"Okun gösterdiği işlemleri doğru hesaplamaya çalışın\n" +
					"Ekrandaki tuşları kullanarak işlemleri doğru bir\n" +
					"şekilde hesaplamaya çalışın, bakalım zaman bitene kadar\n" +
					"kaç işlemi doğru cevaplayabileceksin\n";
		}
		else if(sceneId == SceneType.GAME_MATH_BIG_SMALL.ordinal()){
			instructions = " << Büyük Sayıyı Bul >>\n " +
					"İki taraftaki işlemlerden büyük olanı hesapla\n" +
					"daha sonra sayının yanındaki büyüktür tuşuna bas.\n " +
					"Süren dolana kadar kaçını doğru tahmin edebilirsin\n";
		}
		else if(sceneId == SceneType.GAME_MEMORY_LAMPS.ordinal()){
			instructions = " << Hangi Lambalar Yandı? >>\n " +
					"Lambaların yanıp sönmesini bekle  ve sonra\n" +
					"Yanıp sönen lambaların hangi sıra ile yandığını bul.\n" +
					"Yanlış lambaya basarsan lambalar yeniden yanıp sönecek.\n" +
					"Doğru bilirsen dikkat et işler biraz daha zorlaşabilir.\n";
		}
		else if(sceneId == SceneType.GAME_SHAPES_ORDER.ordinal()){
			instructions = " << Şekil Sıralama >>\n " +
					"Üst tarafta yan yana gösterilen şekilleri\n" +
					"soldan sağa doğru gösterilme sırasına göre\n" +
					"aşağıdaki şekillerden bularak sıralamaya çalış.\n" +
					"Doğru bilirsen dikkat et işler biraz daha zorlaşabilir.\n";
		}
		else if(sceneId == SceneType.GAME_SAME_TILES.ordinal()){
			instructions = " << Aynı Şekilleri Bul >>\n " +
					"Ekranda çıkan şekillerden aynı olanlara sırayla dokun.\n" +
					"Eğer şekiller aynıysa ekrandan kaybolur ve hepsi bitince\n" +
					"yeni şekiller gelecek, gözlerini dört aç ve en kısa sürede\n" +
					"en çok şekli eşleştirmeye çalış, şekiller gittikçe artabilir.\n";
		}
		else if(sceneId == SceneType.GAME_FOUR_OPERATIONS.ordinal()){
			instructions = " << Doğru İşlem Hangisi >>\n " +
					"Gördüğün matematik işleminin doğru olması için\n" +
					"sence bu sayılara hangi işlemi uygulanmalı?\n" +
					"Toplama mı, çıkarma mı çarpma mı, yoksa bölme mi?\n" +
					"Süren dolmadan yapabildiğin kadar doğru hesapla.\n";
		}
		else if(sceneId == SceneType.GAME_MEMORIZE_IMAGES.ordinal()){
			instructions = " << Kaybolan Resim Nerede >>\n " +
					"Yukarıdan aşağı giden şekilleri iyi takip et.\n" +
					"Bulutun yuttuğu şekil hangisi tahmin edebilir misin?\n" +
					"Bakalım şekilleri aklınızda tutabiliyor musunuz?\n " +
					"Dikkat et şekiller gittikçe artabilir...\n";
		}
		int rekor = MainActivity.DB.selectHighScore(SceneType.values()[sceneId].name());
		instructions += "REKOR: "+ ((rekor <= 0)?"Henüz oynamadın":rekor)+"\n";
		instructions += "Oyuna baslamak icin oyna'a tikla\n";
	}

	@Override
	public void clearScene() {
		detachChild(btnGame1);
		detachChild(btnGame2);
		detachChild(btnGame3);
		detachChild(btnGame4);
		detachChild(btnGame5);
		detachChild(btnGame6);
		detachChild(btnGame7);
		detachChild(btnGame8);
	}
}
