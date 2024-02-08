package tr.com.reformtek.beynium.util;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import tr.com.reformtek.beynium.games.GameBigSmall;
import tr.com.reformtek.beynium.games.GameFourOperations;
import tr.com.reformtek.beynium.games.GameMathBalloonScene;
import tr.com.reformtek.beynium.games.GameMathOperations;
import tr.com.reformtek.beynium.games.GameMemorizeImages;
import tr.com.reformtek.beynium.games.GameMemoryLamps;
import tr.com.reformtek.beynium.games.GameSameTiles;
import tr.com.reformtek.beynium.games.GameShapeOrder;
import tr.com.reformtek.beynium.scenes.BaseScene;
import tr.com.reformtek.beynium.scenes.CountdownScene;
import tr.com.reformtek.beynium.scenes.GamesScene;
import tr.com.reformtek.beynium.scenes.SplashScene;

public class SceneManager
{
	private BaseScene gamesScene;
    private BaseScene splashScene;
    private BaseScene currentScene;
    
    public  CountdownScene 		 countdownScene;
    private GameMathBalloonScene gameMathBalloonScene;
    private GameMathOperations 	 gameMathOperationsScene;
    private GameBigSmall 		 gameBigSmall;
    private GameMemoryLamps 	 gameMemoryLamps;
    private GameShapeOrder 		 gameShapeOrder;
    private GameSameTiles 		 gameSameTiles;
    private GameFourOperations 	 gameFourOperations;
    private GameMemorizeImages 	 gameMemorizeImages;
    
    private Engine engine 					    = ResourcesManager.getInstance().engine;
    private SceneType currentSceneType 			= SceneType.SCENE_SPLASH;
    private static final SceneManager INSTANCE  = new SceneManager();
    
    public enum SceneType{
        SCENE_SPLASH,
        SCENE_GAME,
        COUNTDOWN_SCENE,
        //GAMES
        GAME_MATH_BALLOON,
        GAME_MATH_OPERATIONS,
        GAME_MATH_BIG_SMALL,
        GAME_MEMORY_LAMPS,
        GAME_SHAPES_ORDER,
        GAME_SAME_TILES,
        GAME_FOUR_OPERATIONS,
        GAME_MEMORIZE_IMAGES

    }
    
    public void setScene(BaseScene scene){
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    public void setSceneType(SceneType sceneType){
        switch (sceneType){
            case SCENE_GAME:
                setScene(gamesScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case COUNTDOWN_SCENE:
            	setScene(countdownScene);
            	break;
            default:
                break;
        }
    }
    
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback){
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    
    public void createMenuScene(){
        ResourcesManager.getInstance().loadMenuResources();
        ResourcesManager.getInstance().loadGameResources();
        gamesScene = new GamesScene();
        countdownScene = new CountdownScene();
        disposeSplashScene();
        setSceneType(SceneType.SCENE_GAME);
        SceneManager.getInstance().setScene(gamesScene);
    }
    
    private void disposeSplashScene(){
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }
    
    public void disposeCountdownScene(){
    	if(countdownScene != null)
    		countdownScene.disposeScene();
        countdownScene = null;
    }
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    
    public static SceneManager getInstance(){
        return INSTANCE;
    }
    
    public SceneType getCurrentSceneType(){
        return currentSceneType;
    }
    
    public BaseScene getSceneInstance(SceneType type){
        switch (type){
            case GAME_MATH_BALLOON:
            	if(gameMathBalloonScene == null || gameMathBalloonScene.isDisposed())
            		gameMathBalloonScene = new GameMathBalloonScene();
                return(gameMathBalloonScene);
                
            case GAME_MATH_OPERATIONS:
            	if(gameMathOperationsScene == null || gameMathOperationsScene.isDisposed())
            		gameMathOperationsScene = new GameMathOperations(ResourcesManager.getInstance().texBlackboard);
                return(gameMathOperationsScene);
                
            case GAME_MATH_BIG_SMALL:
            	if(gameBigSmall == null || gameBigSmall.isDisposed())
            		gameBigSmall = new GameBigSmall();
            	return(gameBigSmall);
            	
            case GAME_MEMORY_LAMPS:
            	if(gameMemoryLamps == null || gameMemoryLamps.isDisposed())
            		gameMemoryLamps = new GameMemoryLamps();
            	return(gameMemoryLamps);
            	
            case GAME_SHAPES_ORDER:
            	if(gameShapeOrder == null || gameShapeOrder.isDisposed())
            		gameShapeOrder = new GameShapeOrder();
            	return(gameShapeOrder);
            	
            case GAME_SAME_TILES:
            	if(gameSameTiles == null || gameSameTiles.isDisposed())
            		gameSameTiles = new GameSameTiles(ResourcesManager.getInstance().texNotebook);
            	return(gameSameTiles);
            	
            case GAME_FOUR_OPERATIONS:
            	if(gameFourOperations == null || gameFourOperations.isDisposed())
            		gameFourOperations = new GameFourOperations(ResourcesManager.getInstance().texNotebook);
            	return(gameFourOperations);
            	
            case GAME_MEMORIZE_IMAGES:
            	if(gameMemorizeImages == null || gameMemorizeImages.isDisposed())
            		gameMemorizeImages = new GameMemorizeImages(ResourcesManager.getInstance().texSky);
            	return(gameMemorizeImages);
            	
            case SCENE_GAME:
            	return(gamesScene);
            case SCENE_SPLASH:
            	return(splashScene);
            case COUNTDOWN_SCENE:
            	return(countdownScene);
            default:
                return null;
        }
    }
    
    public BaseScene getCurrentScene(){
        return currentScene;
    }
    
    public void loadGameScene(SceneType type){
        switch (type)
        {
            case GAME_MATH_BALLOON:
            	if(gameMathBalloonScene == null || gameMathBalloonScene.isDisposed())
            		gameMathBalloonScene = new GameMathBalloonScene();
                setScene(gameMathBalloonScene);
                break;

            case GAME_MATH_OPERATIONS:
            	if(gameMathOperationsScene == null || gameMathOperationsScene.isDisposed())
            		gameMathOperationsScene = new GameMathOperations(ResourcesManager.getInstance().texBlackboard);
                setScene(gameMathOperationsScene);
                break;

            case GAME_MATH_BIG_SMALL:
            	if(gameBigSmall == null || gameBigSmall.isDisposed())
            		gameBigSmall = new GameBigSmall();
            	setScene(gameBigSmall);
            	break;
            case GAME_MEMORY_LAMPS:
            	if(gameMemoryLamps == null || gameMemoryLamps.isDisposed())
            		gameMemoryLamps = new GameMemoryLamps();
            	setScene(gameMemoryLamps);
            	gameMemoryLamps.animate();
            	break;

            case GAME_SHAPES_ORDER:
            	if(gameShapeOrder == null || gameShapeOrder.isDisposed())
            		gameShapeOrder = new GameShapeOrder();
            	setScene(gameShapeOrder);
            	break;
            	
            case GAME_SAME_TILES:
            	if(gameSameTiles == null || gameSameTiles.isDisposed())
            		gameSameTiles = new GameSameTiles(ResourcesManager.getInstance().texNotebook);
            	setScene(gameSameTiles);
            	break;
            	
            case GAME_FOUR_OPERATIONS:
            	if(gameFourOperations == null || gameFourOperations.isDisposed())
            		gameFourOperations = new GameFourOperations(ResourcesManager.getInstance().texNotebook);
            	setScene(gameFourOperations);
            	break;
            	
            case GAME_MEMORIZE_IMAGES:
            	if(gameMemorizeImages == null || gameMemorizeImages.isDisposed())
            		gameMemorizeImages = new GameMemorizeImages(ResourcesManager.getInstance().texSky);
            	setScene(gameMemorizeImages);
            	break;
            default:
                break;
        }
    }
}
