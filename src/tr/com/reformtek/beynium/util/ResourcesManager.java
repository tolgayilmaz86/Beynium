package tr.com.reformtek.beynium.util;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import tr.com.reformtek.beynium.MainActivity;

import android.graphics.Typeface;
import android.util.Log;

public class ResourcesManager
{
    private static final ResourcesManager INSTANCE = new ResourcesManager();
    
    public Font font;
    public Font instructionsFont;
    public Font fontDigital;
    public Font fontPencil;
    public Font brushFont;
    public Font mathFont;
    public Font scoreInfoFont;
    public Font mFont;
    public Engine engine;
    public MainActivity activity;
    public Camera camera;
	public Text countdownText;
	public Text gameoverText;
    public VertexBufferObjectManager vbom;
    public AnimatedSprite countdownSprite;
    
    public ITextureRegion info_section_region;
    public ITextureRegion texHorizontalWall;
    public ITextureRegion texVerticalWall;
    public ITextureRegion info_region;
    public ITextureRegion texPlayButton;
    public ITextureRegion splash_region;
    public ITextureRegion menu_background_region;
    public ITextureRegion texRegGreater;
    public ITextureRegion texRegLess;
    public ITextureRegion texGameSelectionBox;
    public ITextureRegion stats_region_clicked;
    public ITextureRegion texRegGameBackground;
    public ITextureRegion texRegTrue;
    public ITextureRegion texRegFalse;
    public ITextureRegion texLampOn;
    public ITextureRegion texLampOff;
    public ITextureRegion finishFlag;
    public ITextureRegion texGame1Button;
    public ITextureRegion texGame2Button;
    public ITextureRegion texGame3Button;
    public ITextureRegion texGame4Button;
    public ITextureRegion texGame5Button;
    public ITextureRegion texGame6Button;
    public ITextureRegion texGame7Button;
    public ITextureRegion texGame8Button;
    public ITextureRegion texArrow;
    public ITextureRegion texRegCircle;
    public ITextureRegion texRegCursor;
    public ITextureRegion texBlackboard;
    public ITextureRegion texNotebook;
	public ITextureRegion texSum;
	public ITextureRegion texMultiply;
	public ITextureRegion texDivide;
	public ITextureRegion texSubtract;
	public ITextureRegion texQuestion;
	public ITextureRegion texEqual;
	public ITextureRegion texOpFrame;
	public ITextureRegion texInfoBackground;
	public ITextureRegion texScoreTable;
	public ITextureRegion texCongratulations;
	public ITextureRegion texClear, texEquals;
	public static ITextureRegion tex0, tex1,tex2,tex3,tex4,tex5,tex6,tex7,tex8,tex9;
	
	public static ITextureRegion texTile1;
	public static ITextureRegion texTile2;
	public static ITextureRegion texTile3;
	public static ITextureRegion texTile4;
	public static ITextureRegion texTile5;
	public static ITextureRegion texTile6;
	public static ITextureRegion texTile7;
	public static ITextureRegion texTile8;
	public static ITextureRegion texTile9;
	public static ITextureRegion texTile10;
	public static ITextureRegion texTile11;
	public static ITextureRegion texTile12;
	public static ITextureRegion texTile13;
	public static ITextureRegion texTile14;
	public static ITextureRegion texTile15;
	public static ITextureRegion texTile16;
	public static ITextureRegion texTile17;
	public static ITextureRegion texTile18;
	public static ITextureRegion texTile19;
	public static ITextureRegion texTile20;
	public static ITextureRegion texFrameSelected;
	
	public static ITextureRegion texShape1;
	public static ITextureRegion texShape2;
	public static ITextureRegion texShape3;
	public static ITextureRegion texShape4;
	public static ITextureRegion texShape5;
	public static ITextureRegion texShape6;
	public static ITextureRegion texShape7;
	public static ITextureRegion texShape8;
	public static ITextureRegion texShape9;
	public static ITextureRegion texShape10;
	public static ITextureRegion texShape11;
	public static ITextureRegion texShape12;
	public static ITextureRegion texShape13;
	public static ITextureRegion texShape14;
	public static ITextureRegion texShape15;
	public static ITextureRegion texShape16;
	public static ITextureRegion texShape17;
	public static ITextureRegion texShape18;
	public static ITextureRegion texShape19;
	public static ITextureRegion texShape20;
	public static ITextureRegion texShape21;
	public static ITextureRegion texShape22;
	public static ITextureRegion texShape23;
	public static ITextureRegion texShape24;
	public static ITextureRegion texShape25;
	public static ITextureRegion texShape26;
	public static ITextureRegion texShape27;
	public static ITextureRegion texShape28;
	public static ITextureRegion texShape29;
	public static ITextureRegion texShape30;
	
	public ITextureRegion texSky;
	public ITextureRegion texCloudFront;
	public ITextureRegion texCloudBack;
	public static ITextureRegion texImg1;
	public static ITextureRegion texImg2;
	public static ITextureRegion texImg3;
	public static ITextureRegion texImg4;
	public static ITextureRegion texImg5;
	public static ITextureRegion texImg6;
	public static ITextureRegion texImg7;
	public static ITextureRegion texImg8;
	public static ITextureRegion texImg9;
	public static ITextureRegion texImg10;
	public static ITextureRegion texImg11;
	public static ITextureRegion texImg12;
	public static ITextureRegion texImg13;
	public static ITextureRegion texImg14;
	public static ITextureRegion texImg15;
	public static ITextureRegion texImg16;
	public static ITextureRegion texImg17;
	public static ITextureRegion texImg18;
	public static ITextureRegion texImg19;
	public static ITextureRegion texImg20;
	public static ITextureRegion texImg21;
	public static ITextureRegion texImg22;
	public static ITextureRegion texImg23;
	public static ITextureRegion texImg24;
	public static ITextureRegion texImg25;
	public static ITextureRegion texImg26;
	public static ITextureRegion texImg27;
	public static ITextureRegion texImg28;
	public static ITextureRegion texImg29;
	public static ITextureRegion texImg30;
	public static ITextureRegion texImg31;
	public static ITextureRegion texImg32;
	public static ITextureRegion texImg33;
	public static ITextureRegion texImg34;
	public static ITextureRegion texImg35;
	public TiledTextureRegion texRegNumbersTile;
	public TiledTextureRegion texCountdownTile;
    
    public BitmapTextureAtlas playButtonAtlas;
    public BitmapTextureAtlas horizontalwallAtlas;
    public BitmapTextureAtlas verticalWallAtlas;
    public BitmapTextureAtlas lessTextureAtlas;
    public BitmapTextureAtlas greaterTextureAtlas;
    public BitmapTextureAtlas menuTextureAtlas;
    public BitmapTextureAtlas circleTextureAtlas;
    public BitmapTextureAtlas splashTextureAtlas;
    public BitmapTextureAtlas infoSectionTextureAtlas;
    public BitmapTextureAtlas game1ButtonTextureAtlas;
    public BitmapTextureAtlas game2ButtonTextureAtlas;
    public BitmapTextureAtlas game3ButtonTextureAtlas;
    public BitmapTextureAtlas game4ButtonTextureAtlas;
    public BitmapTextureAtlas game5ButtonTextureAtlas;
    public BitmapTextureAtlas game6ButtonTextureAtlas;
    public BitmapTextureAtlas game7ButtonTextureAtlas;
    public BitmapTextureAtlas game8ButtonTextureAtlas;
    public BitmapTextureAtlas gameBackgroundTextureAtlas;
    public BitmapTextureAtlas gameSelectionBoxTextureAtlas;
    public BitmapTextureAtlas blackboardTextureAtlas;
    public BitmapTextureAtlas cursorTextureAtlas;
    public BitmapTextureAtlas trueTextureAtlas;
    public BitmapTextureAtlas falseTextureAtlas;
    public BitmapTextureAtlas lampOnTextureAtlas;
    public BitmapTextureAtlas lampOffTextureAtlas;
    public BitmapTextureAtlas finishFlagTextureAtlas;
    public BitmapTextureAtlas notebookTextureAtlas;
    public BitmapTextureAtlas frameSelectedAtlas;
    public BitmapTextureAtlas tileAtlas1;
    public BitmapTextureAtlas tileAtlas2;
    public BitmapTextureAtlas tileAtlas3;
    public BitmapTextureAtlas tileAtlas4;
    public BitmapTextureAtlas tileAtlas5;
    public BitmapTextureAtlas tileAtlas6;
    public BitmapTextureAtlas tileAtlas7;
    public BitmapTextureAtlas tileAtlas8;
    public BitmapTextureAtlas tileAtlas9;
    public BitmapTextureAtlas tileAtlas10;
    public BitmapTextureAtlas tileAtlas11;
    public BitmapTextureAtlas tileAtlas12;
    public BitmapTextureAtlas tileAtlas13;
    public BitmapTextureAtlas tileAtlas14;
    public BitmapTextureAtlas tileAtlas15;
    public BitmapTextureAtlas tileAtlas16;
    public BitmapTextureAtlas tileAtlas17;
    public BitmapTextureAtlas tileAtlas18;
    public BitmapTextureAtlas tileAtlas19;
    public BitmapTextureAtlas tileAtlas20;
    public BitmapTextureAtlas imgAtlas1;
    public BitmapTextureAtlas imgAtlas2;
    public BitmapTextureAtlas imgAtlas3;
    public BitmapTextureAtlas imgAtlas4;
    public BitmapTextureAtlas imgAtlas5;
    public BitmapTextureAtlas imgAtlas6;
    public BitmapTextureAtlas imgAtlas7;
    public BitmapTextureAtlas imgAtlas8;
    public BitmapTextureAtlas imgAtlas9;
    public BitmapTextureAtlas imgAtlas10;
    public BitmapTextureAtlas imgAtlas11;
    public BitmapTextureAtlas imgAtlas12;
    public BitmapTextureAtlas imgAtlas13;
    public BitmapTextureAtlas imgAtlas14;
    public BitmapTextureAtlas imgAtlas15;
    public BitmapTextureAtlas imgAtlas16;
    public BitmapTextureAtlas imgAtlas17;
    public BitmapTextureAtlas imgAtlas18;
    public BitmapTextureAtlas imgAtlas19;
    public BitmapTextureAtlas imgAtlas20;
    public BitmapTextureAtlas imgAtlas21;
    public BitmapTextureAtlas imgAtlas22;
    public BitmapTextureAtlas imgAtlas23;
    public BitmapTextureAtlas imgAtlas24;
    public BitmapTextureAtlas imgAtlas25;
    public BitmapTextureAtlas imgAtlas26;
    public BitmapTextureAtlas imgAtlas27;
    public BitmapTextureAtlas imgAtlas28;
    public BitmapTextureAtlas imgAtlas29;
    public BitmapTextureAtlas imgAtlas30;
    public BitmapTextureAtlas imgAtlas31;
    public BitmapTextureAtlas imgAtlas32;
    public BitmapTextureAtlas imgAtlas33;
    public BitmapTextureAtlas imgAtlas34;
    public BitmapTextureAtlas imgAtlas35;
    public BitmapTextureAtlas shapeAtlas1;
    public BitmapTextureAtlas shapeAtlas2;
    public BitmapTextureAtlas shapeAtlas3;
    public BitmapTextureAtlas shapeAtlas4;
    public BitmapTextureAtlas shapeAtlas5;
    public BitmapTextureAtlas shapeAtlas6;
    public BitmapTextureAtlas shapeAtlas7;
    public BitmapTextureAtlas shapeAtlas8;
    public BitmapTextureAtlas shapeAtlas9;
    public BitmapTextureAtlas shapeAtlas10;
    public BitmapTextureAtlas shapeAtlas11;
    public BitmapTextureAtlas shapeAtlas12;
    public BitmapTextureAtlas shapeAtlas13;
    public BitmapTextureAtlas shapeAtlas14;
    public BitmapTextureAtlas shapeAtlas15;
    public BitmapTextureAtlas shapeAtlas16;
    public BitmapTextureAtlas shapeAtlas17;
    public BitmapTextureAtlas shapeAtlas18;
    public BitmapTextureAtlas shapeAtlas19;
    public BitmapTextureAtlas shapeAtlas20;
    public BitmapTextureAtlas shapeAtlas21;
    public BitmapTextureAtlas shapeAtlas22;
    public BitmapTextureAtlas shapeAtlas23;
    public BitmapTextureAtlas shapeAtlas24;
    public BitmapTextureAtlas shapeAtlas25;
    public BitmapTextureAtlas shapeAtlas26;
    public BitmapTextureAtlas shapeAtlas27;
    public BitmapTextureAtlas shapeAtlas28;
    public BitmapTextureAtlas shapeAtlas29;
    public BitmapTextureAtlas shapeAtlas30;
    public BitmapTextureAtlas arrowAtlas;
    public BitmapTextureAtlas sumAtlas;
    public BitmapTextureAtlas subtractAtlas;
    public BitmapTextureAtlas multiplyAtlas;
    public BitmapTextureAtlas divideAtlas;
    public BitmapTextureAtlas questionAtlas;
    public BitmapTextureAtlas equalAtlas;
    public BitmapTextureAtlas opFrameAtlas;
    public BitmapTextureAtlas skyAtlas;
    public BitmapTextureAtlas cloudFrontAtlas;
    public BitmapTextureAtlas cloudBackAtlas;
    public BitmapTextureAtlas infoBackgroundAtlas;
    public BitmapTextureAtlas scoreTableAtlas;
    public BitmapTextureAtlas congratulationsAtlas;
    public BitmapTextureAtlas equalsAtlas;
    public BitmapTextureAtlas clearAtlas;
    public BitmapTextureAtlas tex1Atlas,tex2Atlas,tex3Atlas,tex4Atlas,tex5Atlas,tex6Atlas,tex7Atlas,tex8Atlas,tex9Atlas,tex0Atlas;
    public BuildableBitmapTextureAtlas numbersTileTextureAtlas;
    public BuildableBitmapTextureAtlas countdownTileTextureAtlas;
	
	public static final String TILE_1  = "tiles/icon1.png";
	public static final String TILE_2  = "tiles/icon2.png";
	public static final String TILE_3  = "tiles/icon3.png";
	public static final String TILE_4  = "tiles/icon4.png";
	public static final String TILE_5  = "tiles/icon5.png";
	public static final String TILE_6  = "tiles/icon6.png";
	public static final String TILE_7  = "tiles/icon7.png";
	public static final String TILE_8  = "tiles/icon8.png";
	public static final String TILE_9  = "tiles/icon9.png";
	public static final String TILE_10 = "tiles/icon10.png";
	public static final String TILE_11 = "tiles/icon11.png";
	public static final String TILE_12 = "tiles/icon12.png";
	public static final String TILE_13 = "tiles/icon13.png";
	public static final String TILE_14 = "tiles/icon14.png";
	public static final String TILE_15 = "tiles/icon15.png";
	public static final String TILE_16 = "tiles/icon16.png";
	public static final String TILE_17 = "tiles/icon17.png";
	public static final String TILE_18 = "tiles/icon18.png";
	public static final String TILE_19 = "tiles/icon19.png";
	public static final String TILE_20 = "tiles/icon20.png";
	public static final String FRAME_SELECTED  = "tiles/frameselected.png";
	
	public static final String IMG_1  = "tiles/img1.png";
	public static final String IMG_2  = "tiles/img2.png";
	public static final String IMG_3  = "tiles/img3.png";
	public static final String IMG_4  = "tiles/img4.png";
	public static final String IMG_5  = "tiles/img5.png";
	public static final String IMG_6  = "tiles/img6.png";
	public static final String IMG_7  = "tiles/img7.png";
	public static final String IMG_8  = "tiles/img8.png";
	public static final String IMG_9  = "tiles/img9.png";
	public static final String IMG_10 = "tiles/img10.png";
	public static final String IMG_11 = "tiles/img11.png";
	public static final String IMG_12 = "tiles/img12.png";
	public static final String IMG_13 = "tiles/img13.png";
	public static final String IMG_14 = "tiles/img14.png";
	public static final String IMG_15 = "tiles/img15.png";
	public static final String IMG_16 = "tiles/img16.png";
	public static final String IMG_17 = "tiles/img17.png";
	public static final String IMG_18 = "tiles/img18.png";
	public static final String IMG_19 = "tiles/img19.png";
	public static final String IMG_20 = "tiles/img20.png";
	public static final String IMG_21 = "tiles/img21.png";
	public static final String IMG_22 = "tiles/img22.png";
	public static final String IMG_23 = "tiles/img23.png";
	public static final String IMG_24 = "tiles/img24.png";
	public static final String IMG_25 = "tiles/img25.png";
	public static final String IMG_26 = "tiles/img26.png";
	public static final String IMG_27 = "tiles/img27.png";
	public static final String IMG_28 = "tiles/img28.png";
	public static final String IMG_29 = "tiles/img29.png";
	public static final String IMG_30 = "tiles/img30.png";
	public static final String IMG_31 = "tiles/img31.png";
	public static final String IMG_32 = "tiles/img32.png";
	public static final String IMG_33 = "tiles/img33.png";
	public static final String IMG_34 = "tiles/img34.png";
	public static final String IMG_35 = "tiles/img35.png";
	
	public static final String SHAPE_1  = "shapes/shape1.png";
	public static final String SHAPE_2  = "shapes/shape2.png";
	public static final String SHAPE_3  = "shapes/shape3.png";
	public static final String SHAPE_4  = "shapes/shape4.png";
	public static final String SHAPE_5  = "shapes/shape5.png";
	public static final String SHAPE_6  = "shapes/shape6.png";
	public static final String SHAPE_7  = "shapes/shape7.png";
	public static final String SHAPE_8  = "shapes/shape8.png";
	public static final String SHAPE_9  = "shapes/shape9.png";
	public static final String SHAPE_10 = "shapes/shape10.png";
	public static final String SHAPE_11 = "shapes/shape11.png";
	public static final String SHAPE_12 = "shapes/shape12.png";
	public static final String SHAPE_13 = "shapes/shape13.png";
	public static final String SHAPE_14 = "shapes/shape14.png";
	public static final String SHAPE_15 = "shapes/shape15.png";
	public static final String SHAPE_16 = "shapes/shape16.png";
	public static final String SHAPE_17 = "shapes/shape17.png";
	public static final String SHAPE_18 = "shapes/shape18.png";
	public static final String SHAPE_19 = "shapes/shape19.png";
	public static final String SHAPE_20 = "shapes/shape20.png";
	public static final String SHAPE_21 = "shapes/shape21.png";
	public static final String SHAPE_22 = "shapes/shape22.png";
	public static final String SHAPE_23 = "shapes/shape23.png";
	public static final String SHAPE_24 = "shapes/shape24.png";
	public static final String SHAPE_25 = "shapes/shape25.png";
	public static final String SHAPE_26 = "shapes/shape26.png";
	public static final String SHAPE_27 = "shapes/shape27.png";
	public static final String SHAPE_28 = "shapes/shape28.png";
	public static final String SHAPE_29 = "shapes/shape29.png";
	public static final String SHAPE_30 = "shapes/shape30.png";
	public static final String ARROW 	= "arrow.png";
	
    public static final String ASSET_BASE_PATH 			= "gfx/";
    public static final String FONTS_BASE_PATH 			= "fonts/";
    public static final String INFO_SECTION_BACKGROUND 	= "infosection.png";
    public static final String CURSOR 					= "cursor.png";
    public static final String CIRCLE 					= "circle.png";
    public static final String GREATER 					= "greater.png";
    public static final String LESS 					= "less.png";
    public static final String TRUE 					= "true.png";
    public static final String FALSE 					= "false.png";
    public static final String LAMP_ON 					= "lamp_on.png";
    public static final String LAMP_OFF 				= "lamp_off.png";
    public static final String FINISH_FLAG 				= "finish.png";
    public static final String SUM 						= "sum.png";
    public static final String SUBTRACT 				= "subtract.png";
    public static final String MULTIPLY 				= "multiply.png";
    public static final String DIVIDE 					= "divide.png";
    public static final String QUESTION 				= "questionMark.png";
    public static final String EQUAL 					= "equal.png";
    public static final String OPFRAME 					= "operations_frame.png";
    public static final String SKY 						= "sky.png";
    public static final String CLOUD_FRONT 				= "cloud_front.png";
    public static final String CLOUD_BACK 				= "cloud_back.png";
    public static final String COUNTDOWN_TILES 			= "countdown.png";
    public static final String CONGRATULATIONS 			= "congratulations.png";
    public static final String HORIZONTAL_WALL 			= "horizontal_wall.png";
    public static final String VERTICAL_WALL 			= "vertical_wall.png";

    private static final String SPLASH 					= "splash.png";
    public  static final String GAMES_MENU_BACKGROUND 	= "games_menu/gamesbackground.png";
    private static final String GAME_PLAY_BACKGROUND 	= "gamebackground.png";
    private static final String BLACKBOARD_BACKGROUND 	= "blackboard.png";
    private static final String NOTEBOOK_BACKGROUND 	= "notebookbackground.png";
    private static final String SCORE_TABLE 			= "credit_score.png";
    
    private static final String NUMBERS_TILE 			= "numbers.png";

    private static final String GAME_1_MENU_ITEM = "games_menu/game1.png";
    private static final String GAME_2_MENU_ITEM = "games_menu/game2.png";
    private static final String GAME_3_MENU_ITEM = "games_menu/game3.png";
    private static final String GAME_4_MENU_ITEM = "games_menu/game4.png";
    private static final String GAME_5_MENU_ITEM = "games_menu/game5.png";
    private static final String GAME_6_MENU_ITEM = "games_menu/game6.png";
    private static final String GAME_7_MENU_ITEM = "games_menu/game7.png";
    private static final String GAME_8_MENU_ITEM = "games_menu/game8.png";
    private static final String SELECTIONBOX 	 = "games_menu/selectionbox.png";
    private static final String INFO_BACKGROUND  = "games_menu/info_background.png";
    private static final String INFO_PLAY_BUTTON = "games_menu/play_button.png";
    
    private static final String BUTTON_0 = "calc/0.png";
    private static final String BUTTON_1 = "calc/1.png";
    private static final String BUTTON_2 = "calc/2.png";
    private static final String BUTTON_3 = "calc/3.png";
    private static final String BUTTON_4 = "calc/4.png";
    private static final String BUTTON_5 = "calc/5.png";
    private static final String BUTTON_6 = "calc/6.png";
    private static final String BUTTON_7 = "calc/7.png";
    private static final String BUTTON_8 = "calc/8.png";
    private static final String BUTTON_9 = "calc/9.png";
    private static final String CLEAR 	 = "calc/clear.png";
    private static final String EQUALS	 = "calc/equals.png";

    private static final String DIGITAL_FONT 		= "digital.ttf";
    private static final String HANDWRITING_FONT 	= "handwriting.ttf";
    private static final String SKETCH_FONT 		= "font.ttf";
    private static final String BRUSH_FONT 			= "brushed.ttf";
    private static final String MATH_FONT 			= "Blox2.ttf";
    private static final String INSTRUCTIONS_FONT 	= "bigfish.ttf";
    private static final String SCORE_INFO_FONT 	= "forgottenWorld.ttf";

    public void loadMenuResources()
    {
        loadMenuGraphics();
        loadMenuFonts();
    }
    
    public void loadGameResources(){
        loadGameFonts();
    }
    
    private void loadMenuGraphics()
    {        
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(ASSET_BASE_PATH);
    	
    	//==========================================
    	// Loading GamesMathBalloonScene resources
    	//==========================================
    	horizontalwallAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 820, 40, TextureOptions.BILINEAR);
    	texHorizontalWall = BitmapTextureAtlasTextureRegionFactory.createFromAsset(horizontalwallAtlas, activity, HORIZONTAL_WALL,0,0);
    	horizontalwallAtlas.load();
    	
    	verticalWallAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 40, 600, TextureOptions.BILINEAR);
    	texVerticalWall = BitmapTextureAtlasTextureRegionFactory.createFromAsset(verticalWallAtlas, activity, VERTICAL_WALL,0,0);
    	verticalWallAtlas.load();
    	
		circleTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 100, 100, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texRegCircle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(circleTextureAtlas, activity, CIRCLE,0,0);
		circleTextureAtlas.load();
    	//==========================================
    	// End of GamesMathBalloonScene resources
    	//==========================================
		
    	//==========================================
    	// Loading GamesScene resources
    	//==========================================
    	infoBackgroundAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 600, 400, TextureOptions.BILINEAR);
    	texInfoBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(infoBackgroundAtlas, activity, INFO_BACKGROUND,0,0);
    	infoBackgroundAtlas.load();
    	
    	playButtonAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 152, 80, TextureOptions.BILINEAR);
    	texPlayButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(playButtonAtlas, activity, INFO_PLAY_BUTTON,0,0);
    	playButtonAtlas.load();
    	
		game1ButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 400, 500, TextureOptions.BILINEAR);
    	texGame1Button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game1ButtonTextureAtlas, activity, GAME_1_MENU_ITEM,0,0);
    	game1ButtonTextureAtlas.load();
    	
		game2ButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 400, 500, TextureOptions.BILINEAR);
    	texGame2Button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game2ButtonTextureAtlas, activity, GAME_2_MENU_ITEM,0,0);
    	game2ButtonTextureAtlas.load();
    	
		game3ButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 400, 500, TextureOptions.BILINEAR);
		texGame3Button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game3ButtonTextureAtlas, activity, GAME_3_MENU_ITEM,0,0);
		game3ButtonTextureAtlas.load();
		
		game4ButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 400, 500, TextureOptions.BILINEAR);
		texGame4Button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game4ButtonTextureAtlas, activity, GAME_4_MENU_ITEM,0,0);
		game4ButtonTextureAtlas.load();
		
		game5ButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 400, 500, TextureOptions.BILINEAR);
		texGame5Button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game5ButtonTextureAtlas, activity, GAME_5_MENU_ITEM,0,0);
		game5ButtonTextureAtlas.load();
		
		game6ButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 400, 500, TextureOptions.BILINEAR);
		texGame6Button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game6ButtonTextureAtlas, activity, GAME_6_MENU_ITEM,0,0);
		game6ButtonTextureAtlas.load();
		
		game7ButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 400, 500, TextureOptions.BILINEAR);
		texGame7Button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game7ButtonTextureAtlas, activity, GAME_7_MENU_ITEM,0,0);
		game7ButtonTextureAtlas.load();
		
		game8ButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 400, 500, TextureOptions.BILINEAR);
		texGame8Button = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game8ButtonTextureAtlas, activity, GAME_8_MENU_ITEM,0,0);
		game8ButtonTextureAtlas.load();
		
		gameSelectionBoxTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 200, 400, TextureOptions.BILINEAR);
		texGameSelectionBox = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameSelectionBoxTextureAtlas, activity, SELECTIONBOX,0,0);
		gameSelectionBoxTextureAtlas.load();
		
		gameBackgroundTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), MainActivity.SCREEN_WIDTH, MainActivity.SCREEN_WIDTH, TextureOptions.BILINEAR);
		texRegGameBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, GAME_PLAY_BACKGROUND,0,0);
    	gameBackgroundTextureAtlas.load();
    	
    	scoreTableAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 200, 200, TextureOptions.BILINEAR);
    	texScoreTable = BitmapTextureAtlasTextureRegionFactory.createFromAsset(scoreTableAtlas, activity, SCORE_TABLE,0,0);
    	scoreTableAtlas.load();
    	
    	infoSectionTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),200, 600, TextureOptions.BILINEAR);
    	info_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(infoSectionTextureAtlas, activity, INFO_SECTION_BACKGROUND, 0, 0);
    	infoSectionTextureAtlas.load();
    	
    	congratulationsAtlas = new BitmapTextureAtlas(activity.getTextureManager(),512, 256, TextureOptions.BILINEAR);
    	texCongratulations = BitmapTextureAtlasTextureRegionFactory.createFromAsset(congratulationsAtlas, activity, CONGRATULATIONS, 0, 0);
    	congratulationsAtlas.load();
    	
    	//==========================================
    	// End of GamesScene resources
    	//==========================================
    	
    	//==========================================
    	// Loading GameMathOperations and
    	// GameBigSmall resources
    	//==========================================
    	clearAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
    	texClear = BitmapTextureAtlasTextureRegionFactory.createFromAsset(clearAtlas, activity, CLEAR, 0, 0);
    	clearAtlas.load();
    	
    	equalsAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
    	texEquals = BitmapTextureAtlasTextureRegionFactory.createFromAsset(equalsAtlas, activity, EQUALS, 0, 0);
    	equalsAtlas.load();
    	
		tex0Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex0 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex0Atlas, activity, BUTTON_0, 0, 0);
		tex0Atlas.load();
		
		tex1Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex1Atlas, activity, BUTTON_1, 0, 0);
		tex1Atlas.load();
		
		tex2Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex2Atlas, activity, BUTTON_2, 0, 0);
		tex2Atlas.load();
		
		tex3Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex3Atlas, activity, BUTTON_3, 0, 0);
		tex3Atlas.load();
		
		tex4Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex4Atlas, activity, BUTTON_4, 0, 0);
		tex4Atlas.load();
		
		tex5Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex5Atlas, activity, BUTTON_5, 0, 0);
		tex5Atlas.load();
		
		tex6Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex6Atlas, activity, BUTTON_6, 0, 0);
		tex6Atlas.load();
		
		tex7Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex7Atlas, activity, BUTTON_7, 0, 0);
		tex7Atlas.load();
		
		tex8Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex8Atlas, activity, BUTTON_8, 0, 0);
		tex8Atlas.load();
		
		tex9Atlas = new BitmapTextureAtlas(activity.getTextureManager(), 150, 150, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		tex9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex9Atlas, activity, BUTTON_9, 0, 0);
		tex9Atlas.load();
    	
		cursorTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 48, 48, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texRegCursor = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cursorTextureAtlas, activity, ResourcesManager.CURSOR,0,0);
		cursorTextureAtlas.load();
		
		greaterTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texRegGreater = BitmapTextureAtlasTextureRegionFactory.createFromAsset(greaterTextureAtlas, activity, GREATER,0,0);
		greaterTextureAtlas.load();
		
		blackboardTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 600, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texBlackboard = BitmapTextureAtlasTextureRegionFactory.createFromAsset(blackboardTextureAtlas, activity, BLACKBOARD_BACKGROUND,0,0);
		blackboardTextureAtlas.load();
		
		lessTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texRegLess = BitmapTextureAtlasTextureRegionFactory.createFromAsset(lessTextureAtlas, activity, LESS,0,0);
		lessTextureAtlas.load();
		
		trueTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 96, 96, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texRegTrue = BitmapTextureAtlasTextureRegionFactory.createFromAsset(trueTextureAtlas, activity, TRUE,0,0);
		trueTextureAtlas.load();
		
		falseTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 96, 96, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texRegFalse = BitmapTextureAtlasTextureRegionFactory.createFromAsset(falseTextureAtlas, activity, FALSE,0,0);
		falseTextureAtlas.load();
		
		lampOnTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 96, 96, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texLampOn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(lampOnTextureAtlas, activity, LAMP_ON,0,0);
		lampOnTextureAtlas.load();
		
		lampOffTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 96, 96, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texLampOff = BitmapTextureAtlasTextureRegionFactory.createFromAsset(lampOffTextureAtlas, activity, LAMP_OFF,0,0);
		lampOffTextureAtlas.load();
		
		finishFlagTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		finishFlag = BitmapTextureAtlasTextureRegionFactory.createFromAsset(finishFlagTextureAtlas, activity, FINISH_FLAG,0,0);
		finishFlagTextureAtlas.load();
		
		numbersTileTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1000, 1000, TextureOptions.NEAREST);
		texRegNumbersTile = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(numbersTileTextureAtlas, activity, NUMBERS_TILE,5,2);
		
		countdownTileTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1000, 1000, TextureOptions.NEAREST);
		texCountdownTile = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(countdownTileTextureAtlas, activity, COUNTDOWN_TILES,3,1);
    	//==========================================
    	// End of GameMathOperations and GameBigSmall resources
    	//==========================================
		
    	//==========================================
    	// Loading GameSameTiles resources
    	//==========================================
		frameSelectedAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texFrameSelected = BitmapTextureAtlasTextureRegionFactory.createFromAsset(frameSelectedAtlas, activity, FRAME_SELECTED,0,0);
		frameSelectedAtlas.load();
		
		notebookTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), MainActivity.SCREEN_WIDTH, MainActivity.SCREEN_HEIGHT, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texNotebook = BitmapTextureAtlasTextureRegionFactory.createFromAsset(notebookTextureAtlas, activity, NOTEBOOK_BACKGROUND,0,0);
		notebookTextureAtlas.load();
		
		tileAtlas1 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas1, activity, TILE_1,0,0);
    	tileAtlas1.load();
    	
		tileAtlas2 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas2, activity, TILE_2,0,0);
    	tileAtlas2.load();
    	
		tileAtlas3 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas3, activity, TILE_3,0,0);
    	tileAtlas3.load();
    	
		tileAtlas4 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas4, activity, TILE_4,0,0);
    	tileAtlas4.load();
    	
		tileAtlas5 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas5, activity, TILE_5,0,0);
    	tileAtlas5.load();
    	
		tileAtlas6 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas6, activity, TILE_6,0,0);
    	tileAtlas6.load();
    	
		tileAtlas7 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas7, activity, TILE_7,0,0);
    	tileAtlas7.load();
    	
		tileAtlas8 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas8, activity, TILE_8,0,0);
    	tileAtlas8.load();
    	
		tileAtlas9 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas9, activity, TILE_9,0,0);
    	tileAtlas9.load();
    	
		tileAtlas10 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas10, activity, TILE_10,0,0);
    	tileAtlas10.load();
    	
		tileAtlas11 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas11, activity, TILE_11,0,0);
    	tileAtlas11.load();
    	
		tileAtlas12 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas12, activity, TILE_12,0,0);
    	tileAtlas12.load();
    	
		tileAtlas13 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas13, activity, TILE_13,0,0);
    	tileAtlas13.load();
    	
		tileAtlas14 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas14, activity, TILE_14,0,0);
    	tileAtlas14.load();
    	
		tileAtlas15 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas15, activity, TILE_15,0,0);
    	tileAtlas15.load();
    	
		tileAtlas16 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
		texTile16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas16, activity, TILE_16,0,0);
    	tileAtlas16.load();
    	
    	tileAtlas17 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
    	texTile17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas17, activity, TILE_17,0,0);
    	tileAtlas17.load();
    	
    	tileAtlas18 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
    	texTile18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas18, activity, TILE_18,0,0);
    	tileAtlas18.load();
    	
    	tileAtlas19 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
    	texTile19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas19, activity, TILE_19,0,0);
    	tileAtlas19.load();
    	
    	tileAtlas20 = new BitmapTextureAtlas(activity.getTextureManager(), 80, 80, TextureOptions.BILINEAR);
    	texTile20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tileAtlas20, activity, TILE_20,0,0);
    	tileAtlas20.load();
    	//==========================================
    	// End of GameSameTiles resources
    	//==========================================
    	
    	//==========================================
    	// Loading GameMemorizeImages resources
    	//==========================================
		skyAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 600, TextureOptions.BILINEAR);
		texSky = BitmapTextureAtlasTextureRegionFactory.createFromAsset(skyAtlas, activity, SKY,0,0);
		skyAtlas.load();
		
		cloudFrontAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 830, 160, TextureOptions.BILINEAR);
		texCloudFront = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cloudFrontAtlas, activity, CLOUD_FRONT,0,0);
		cloudFrontAtlas.load();
		
		cloudBackAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 800, 160, TextureOptions.BILINEAR);
		texCloudBack = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cloudBackAtlas, activity, CLOUD_BACK,0,0);
		cloudBackAtlas.load();
		
		imgAtlas1 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas1, activity, IMG_1,0,0);
    	imgAtlas1.load();
    	
		imgAtlas2 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas2, activity, IMG_2,0,0);
    	imgAtlas2.load();
    	
		imgAtlas3 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas3, activity, IMG_3,0,0);
    	imgAtlas3.load();
    	
		imgAtlas4 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas4, activity, IMG_4,0,0);
    	imgAtlas4.load();
    	
		imgAtlas5 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas5, activity, IMG_5,0,0);
    	imgAtlas5.load();
    	
		imgAtlas6 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas6, activity, IMG_6,0,0);
    	imgAtlas6.load();
    	
		imgAtlas7 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas7, activity, IMG_7,0,0);
    	imgAtlas7.load();
    	
		imgAtlas8 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas8, activity, IMG_8,0,0);
    	imgAtlas8.load();
    	
		imgAtlas9 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas9, activity, IMG_9,0,0);
    	imgAtlas9.load();
    	
		imgAtlas10 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas10, activity, IMG_10,0,0);
    	imgAtlas10.load();
    	
		imgAtlas11 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas11, activity, IMG_11,0,0);
    	imgAtlas11.load();
    	
		imgAtlas12 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas12, activity, IMG_12,0,0);
    	imgAtlas12.load();
    	
		imgAtlas13 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas13, activity, IMG_13,0,0);
    	imgAtlas13.load();
    	
		imgAtlas14 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas14, activity, IMG_14,0,0);
    	imgAtlas14.load();
    	
		imgAtlas15 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas15, activity, IMG_15,0,0);
    	imgAtlas15.load();
    	
		imgAtlas16 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		texImg16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas16, activity, IMG_16,0,0);
    	imgAtlas16.load();
    	
    	imgAtlas17 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas17, activity, IMG_17,0,0);
    	imgAtlas17.load();
    	
    	imgAtlas18 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas18, activity, IMG_18,0,0);
    	imgAtlas18.load();
    	
    	imgAtlas19 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas19, activity, IMG_19,0,0);
    	imgAtlas19.load();
    	
    	imgAtlas20 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas20, activity, IMG_20,0,0);
    	imgAtlas20.load();
    	
    	imgAtlas21 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas21, activity, IMG_21,0,0);
    	imgAtlas21.load();
    	
    	imgAtlas22 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas22, activity, IMG_22,0,0);
    	imgAtlas22.load();
    	
    	imgAtlas23 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas23, activity, IMG_23,0,0);
    	imgAtlas23.load();
    	
    	imgAtlas24 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas24, activity, IMG_24,0,0);
    	imgAtlas24.load();
    	
    	imgAtlas25 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas25, activity, IMG_25,0,0);
    	imgAtlas25.load();
    	
    	imgAtlas26 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg26 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas26, activity, IMG_26,0,0);
    	imgAtlas26.load();
    	
    	imgAtlas27 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg27 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas27, activity, IMG_27,0,0);
    	imgAtlas27.load();
    	
    	imgAtlas28 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg28 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas28, activity, IMG_28,0,0);
    	imgAtlas28.load();
    	
    	imgAtlas29 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg29 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas29, activity, IMG_29,0,0);
    	imgAtlas29.load();
    	
    	imgAtlas30 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg30 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas30, activity, IMG_30,0,0);
    	imgAtlas30.load();
    	
    	imgAtlas31 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg31 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas31, activity, IMG_31,0,0);
    	imgAtlas31.load();
    	
    	imgAtlas32 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg32 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas32, activity, IMG_32,0,0);
    	imgAtlas32.load();
    	
    	imgAtlas33 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg33 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas33, activity, IMG_33,0,0);
    	imgAtlas33.load();
    	
    	imgAtlas34 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg34 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas34, activity, IMG_34,0,0);
    	imgAtlas34.load();
    	
    	imgAtlas35 = new BitmapTextureAtlas(activity.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
    	texImg35 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(imgAtlas35, activity, IMG_35,0,0);
    	imgAtlas35.load();
    	//==========================================
    	// End of GameMemorizeImages resources
    	//==========================================
    	
    	//==========================================
    	// Loading GameShapeOrder resources
    	//==========================================
    	arrowAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 520, 180, TextureOptions.BILINEAR);
    	texArrow = BitmapTextureAtlasTextureRegionFactory.createFromAsset(arrowAtlas, activity, ARROW,0,0);
    	arrowAtlas.load();
    	
		shapeAtlas1 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas1, activity, SHAPE_1,0,0);
    	shapeAtlas1.load();
    	
		shapeAtlas2 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas2, activity, SHAPE_2,0,0);
    	shapeAtlas2.load();
    	
		shapeAtlas3 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas3, activity, SHAPE_3,0,0);
    	shapeAtlas3.load();
    	
		shapeAtlas4 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas4, activity, SHAPE_4,0,0);
    	shapeAtlas4.load();
    	
		shapeAtlas5 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas5, activity, SHAPE_5,0,0);
    	shapeAtlas5.load();
    	
		shapeAtlas6 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas6, activity, SHAPE_6,0,0);
    	shapeAtlas6.load();
    	
		shapeAtlas7 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas7, activity, SHAPE_7,0,0);
    	shapeAtlas7.load();
    	
		shapeAtlas8 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas8, activity, SHAPE_8,0,0);
    	shapeAtlas8.load();
    	
		shapeAtlas9 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape9 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas9, activity, SHAPE_9,0,0);
    	shapeAtlas9.load();
    	
		shapeAtlas10 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape10 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas10, activity, SHAPE_10,0,0);
    	shapeAtlas10.load();
    	
		shapeAtlas11 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape11 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas11, activity, SHAPE_11,0,0);
    	shapeAtlas11.load();
    	
		shapeAtlas12 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape12 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas12, activity, SHAPE_12,0,0);
    	shapeAtlas12.load();
    	
		shapeAtlas13 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape13 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas13, activity, SHAPE_13,0,0);
    	shapeAtlas13.load();
    	
		shapeAtlas14 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape14 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas14, activity, SHAPE_14,0,0);
    	shapeAtlas14.load();
    	
		shapeAtlas15 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape15 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas15, activity, SHAPE_15,0,0);
    	shapeAtlas15.load();
    	
		shapeAtlas16 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
		texShape16 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas16, activity, SHAPE_16,0,0);
    	shapeAtlas16.load();
    	
    	shapeAtlas17 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape17 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas17, activity, SHAPE_17,0,0);
    	shapeAtlas17.load();
    	
    	shapeAtlas18 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape18 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas18, activity, SHAPE_18,0,0);
    	shapeAtlas18.load();
    	
    	shapeAtlas19 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape19 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas19, activity, SHAPE_19,0,0);
    	shapeAtlas19.load();
    	
    	shapeAtlas20 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape20 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas20, activity, SHAPE_20,0,0);
    	shapeAtlas20.load();
    	
    	shapeAtlas21 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape21 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas21, activity, SHAPE_21,0,0);
    	shapeAtlas21.load();
    	
    	shapeAtlas22 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape22 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas22, activity, SHAPE_22,0,0);
    	shapeAtlas22.load();
    	
    	shapeAtlas23 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape23 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas23, activity, SHAPE_23,0,0);
    	shapeAtlas23.load();
    	
    	shapeAtlas24 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape24 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas24, activity, SHAPE_24,0,0);
    	shapeAtlas24.load();
    	
    	shapeAtlas25 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape25 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas25, activity, SHAPE_25,0,0);
    	shapeAtlas25.load();
    	
    	shapeAtlas26 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape26 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas26, activity, SHAPE_26,0,0);
    	shapeAtlas26.load();
    	
    	shapeAtlas27 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape27 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas27, activity, SHAPE_27,0,0);
    	shapeAtlas27.load();
    	
    	shapeAtlas28 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape28 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas28, activity, SHAPE_28,0,0);
    	shapeAtlas28.load();
    	
    	shapeAtlas29 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape29 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas29, activity, SHAPE_29,0,0);
    	shapeAtlas29.load();
    	
    	shapeAtlas30 = new BitmapTextureAtlas(activity.getTextureManager(), 90, 90, TextureOptions.BILINEAR);
    	texShape30 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(shapeAtlas30, activity, SHAPE_30,0,0);
    	shapeAtlas30.load();
    	//==========================================
    	// End of GameShapeOrder resources
    	//==========================================
    	
    	
    	//==========================================
    	// Loading GameFourOperations resources
    	//==========================================
    	sumAtlas 		= new BitmapTextureAtlas(activity.getTextureManager(), 64, 64, TextureOptions.BILINEAR);
    	texSum 			= BitmapTextureAtlasTextureRegionFactory.createFromAsset(sumAtlas, activity, SUM, 0, 0);
    	sumAtlas.load();
    	
    	subtractAtlas 	= new BitmapTextureAtlas(activity.getTextureManager(), 64, 64, TextureOptions.BILINEAR);
    	texSubtract 	= BitmapTextureAtlasTextureRegionFactory.createFromAsset(subtractAtlas, activity, SUBTRACT, 0, 0);
    	subtractAtlas.load();
    	
    	multiplyAtlas 	= new BitmapTextureAtlas(activity.getTextureManager(), 64, 64, TextureOptions.BILINEAR);
    	texMultiply 	= BitmapTextureAtlasTextureRegionFactory.createFromAsset(multiplyAtlas, activity, MULTIPLY, 0, 0);
    	multiplyAtlas.load();
    	
    	divideAtlas 	= new BitmapTextureAtlas(activity.getTextureManager(), 64, 64, TextureOptions.BILINEAR);
    	texDivide 		= BitmapTextureAtlasTextureRegionFactory.createFromAsset(divideAtlas, activity, DIVIDE, 0, 0);
    	divideAtlas.load();
    	
    	questionAtlas 	= new BitmapTextureAtlas(activity.getTextureManager(), 64, 64, TextureOptions.BILINEAR);
    	texQuestion 	= BitmapTextureAtlasTextureRegionFactory.createFromAsset(questionAtlas, activity, QUESTION, 0, 0);
    	questionAtlas.load();
    	
    	equalAtlas 		= new BitmapTextureAtlas(activity.getTextureManager(), 64, 64, TextureOptions.BILINEAR);
    	texEqual 		= BitmapTextureAtlasTextureRegionFactory.createFromAsset(equalAtlas, activity, EQUAL, 0, 0);
    	equalAtlas.load();

    	opFrameAtlas 	= new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
    	texOpFrame 		= BitmapTextureAtlasTextureRegionFactory.createFromAsset(opFrameAtlas, activity, OPFRAME, 0, 0);
    	opFrameAtlas.load();
    	//==========================================
    	// End of GameFourOperations resources
    	//==========================================
		try {
			numbersTileTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
			countdownTileTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
		} catch (TextureAtlasBuilderException e) {
			Log.d("numberstile", e.getMessage());
		}
		numbersTileTextureAtlas.load();
		countdownTileTextureAtlas.load();
    }

    private void loadMenuFonts()
    {
        FontFactory.setAssetBasePath(FONTS_BASE_PATH);
        final ITexture mainFontTexture 				= new BitmapTextureAtlas(activity.getTextureManager(), 
        																		MainActivity.SCREEN_WIDTH/3, 
        																		MainActivity.SCREEN_WIDTH/2,
        																		TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture digitalFontTexture 			= new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture pencilFontTexture 			= new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture brushFontTexture 			= new BitmapTextureAtlas(activity.getTextureManager(), 320, 320, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture mathFontTexture 				= new BitmapTextureAtlas(activity.getTextureManager(), 320, 320, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture instructionsFontTexture 		= new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture scoreInfoFontTexture 		= new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font 		= 	FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), 
        											SKETCH_FONT, 50f, true, android.graphics.Color.WHITE, 0.03f,android.graphics.Color.RED);
        
        fontDigital = 	FontFactory.createFromAsset(activity.getFontManager(), digitalFontTexture, activity.getAssets(), 
        											DIGITAL_FONT, 45f, true, Color.GREEN.getARGBPackedInt());//Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        
        fontPencil 	= 	FontFactory.createFromAsset(activity.getFontManager(), pencilFontTexture, activity.getAssets(), 
        											HANDWRITING_FONT, 75, true, Color.WHITE.getARGBPackedInt());
        
		mFont 		= 	FontFactory.create(activity.getFontManager(), activity.getTextureManager(), 256, 256,
													Typeface.create(Typeface.DEFAULT, Typeface.BOLD), (float) 32.0f, Color.WHITE_ARGB_PACKED_INT);
		
		brushFont 	= 	FontFactory.createFromAsset(activity.getFontManager(), brushFontTexture, activity.getAssets(), 
        											BRUSH_FONT, 60, true, Color.YELLOW.getARGBPackedInt());
		
		mathFont 	= 	FontFactory.createFromAsset(activity.getFontManager(), mathFontTexture, activity.getAssets(), 
													MATH_FONT, 90, true, Color.BLUE.getARGBPackedInt());
		
		instructionsFont = FontFactory.createFromAsset(activity.getFontManager(), instructionsFontTexture, activity.getAssets(), 
													INSTRUCTIONS_FONT, 32, true, Color.YELLOW.getARGBPackedInt());
		
		scoreInfoFont = FontFactory.createFromAsset(activity.getFontManager(), scoreInfoFontTexture, activity.getAssets(), 
													SCORE_INFO_FONT, 50, true, Color.RED.getARGBPackedInt());
		font.load();
		mFont.load();
		mathFont.load();
		brushFont.load();
		fontPencil.load();
		fontDigital.load();
		scoreInfoFont.load();
		instructionsFont.load();
    }
    
    private void loadGameFonts()
    {
    	countdownText = new Text(430, 250, ResourcesManager.getInstance().fontDigital, "0123", ResourcesManager.getInstance().vbom);
    	countdownText.setText("");
    	gameoverText = new Text(280, 350, ResourcesManager.getInstance().brushFont, "zamndolusürebit.", ResourcesManager.getInstance().vbom);
    	gameoverText.setText("");
    }
    
    public void loadSplashScreen()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(ASSET_BASE_PATH);
    	splashTextureAtlas 	= new BitmapTextureAtlas(activity.getTextureManager(),1024, 600, TextureOptions.BILINEAR);
    	splash_region 		= BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, SPLASH, 0, 0);
    	splashTextureAtlas.load();
    }
    
    public void unloadSplashScreen(){
    	splashTextureAtlas.unload();
    	splash_region = null;
    }
    
    public static void prepareManager(Engine engine, MainActivity activity, Camera camera, VertexBufferObjectManager vbom){
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }
    
    public static ResourcesManager getInstance(){
        return INSTANCE;
    }
}