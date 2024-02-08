package tr.com.reformtek.beynium.database;

import tr.com.reformtek.beynium.util.SceneManager.SceneType;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
	//DATABASE
	private static final String DB_NAME = "beynium_db";
	//TABLE
	static final String tHighScores = "high_scores";
	//FIELDS
	static final String fGameID = "game_id";
	static final String fGameName = "game_name";
	static final String fHighestScore = "highScore";
	static final String fLastScore = "lastScore";
	
	public int GAME_ID;
	public String GAME_NAME; 

	public DB(Context context) {
		super(context, DB_NAME, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS "+tHighScores+" (" +
				fGameID + " INTEGER PRIMARY KEY , " +
				fGameName + " TEXT, " +
				fHighestScore + " INTEGER, " +
				fLastScore + " INTEGER " + 
				")");

		ContentValues cv = new ContentValues();
		cv.put(fGameID, SceneType.GAME_SHAPES_ORDER.ordinal());
		cv.put(fGameName, SceneType.GAME_SHAPES_ORDER.name());
		cv.put(fHighestScore, -1);
		cv.put(fLastScore, -1);
		db.insert(tHighScores, null, cv);

		cv.put(fGameID, SceneType.GAME_MATH_OPERATIONS.ordinal());
		cv.put(fGameName, SceneType.GAME_MATH_OPERATIONS.name());
		cv.put(fHighestScore, -1);
		cv.put(fLastScore, -1);
		db.insert(tHighScores, null, cv);
		
		cv.put(fGameID, SceneType.GAME_FOUR_OPERATIONS.ordinal());
		cv.put(fGameName, SceneType.GAME_FOUR_OPERATIONS.name());
		cv.put(fHighestScore, -1);
		cv.put(fLastScore, -1);
		db.insert(tHighScores, null, cv);
		
		cv.put(fGameID, SceneType.GAME_MATH_BALLOON.ordinal());
		cv.put(fGameName, SceneType.GAME_MATH_BALLOON.name());
		cv.put(fHighestScore, -1);
		cv.put(fLastScore, -1);
		db.insert(tHighScores, null, cv);
		
		cv.put(fGameID, SceneType.GAME_MATH_BIG_SMALL.ordinal());
		cv.put(fGameName, SceneType.GAME_MATH_BIG_SMALL.name());
		cv.put(fHighestScore, -1);
		cv.put(fLastScore, -1);
		db.insert(tHighScores, null, cv);
		
		cv.put(fGameID, SceneType.GAME_MEMORIZE_IMAGES.ordinal());
		cv.put(fGameName, SceneType.GAME_MEMORIZE_IMAGES.name());
		cv.put(fHighestScore, -1);
		cv.put(fLastScore, -1);
		db.insert(tHighScores, null, cv);
		
		cv.put(fGameID, SceneType.GAME_MEMORY_LAMPS.ordinal());
		cv.put(fGameName, SceneType.GAME_MEMORY_LAMPS.name());
		cv.put(fHighestScore, -1);
		cv.put(fLastScore, -1);
		db.insert(tHighScores, null, cv);
		
		cv.put(fGameID, SceneType.GAME_SAME_TILES.ordinal());
		cv.put(fGameName, SceneType.GAME_SAME_TILES.name());
		cv.put(fHighestScore, -1);
		cv.put(fLastScore, -1);
		db.insert(tHighScores, null, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// THIS METHOD DELETES THE EXISTING TABLE AND THEN CALLS THE METHOD onCreate() AGAIN TO RECREATE A NEW TABLE
		// THIS SERVES TO ESSENTIALLY RESET THE DATABASE
		// INSTEAD YOU COULD MODIFY THE EXISTING TABLES BY ADDING/REMOVING COLUMNS/ROWS/VALUES THEN NO EXISTING DATA WOULD BE LOST
		db.execSQL("DROP TABLE IF EXISTS "+tHighScores);
		onCreate(db);
	}

	public int selectHighScore(final int ID) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		Cursor cursor = myDB.rawQuery("SELECT "+ fHighestScore +" FROM "+ tHighScores +" WHERE "+ fGameID +"=?",new String []{String.valueOf(ID)});
		if(cursor!=null && cursor.getCount()==0){
			cursor.close();
			return -1;
		}
		if(!cursor.moveToFirst()){
			cursor.close();
			return -1;
		}
		int index = cursor.getColumnIndex(fHighestScore);
		int score = cursor.getInt(index);
		cursor.close();
		return score;
	}
	
	public int selectHighScore(final String gameName) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		Cursor cursor = myDB.rawQuery("SELECT "+ fHighestScore +" FROM "+ tHighScores +" WHERE "+ fGameName +"=?",new String []{String.valueOf(gameName)});
		if(cursor!=null && cursor.getCount()==0){
			cursor.close();
			return -1;
		}
		if(!cursor.moveToFirst()){
			cursor.close();
			return -1;
		}
		int index = cursor.getColumnIndex(fHighestScore);
		int score = cursor.getInt(index);
		cursor.close();
		return score;
	}
	
	public int selectLastScore(final int ID) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		Cursor cursor = myDB.rawQuery("SELECT "+ fLastScore +" FROM "+ tHighScores +" WHERE "+ fGameID +"=?",new String []{String.valueOf(ID)});
		if(cursor!=null && cursor.getCount()==0){
			cursor.close();
			return -1;
		}
		if(!cursor.moveToFirst()){
			cursor.close();
			return -1;
		}
		int index = cursor.getColumnIndex(fLastScore);
		int score = cursor.getInt(index);
		cursor.close();
		return score;
	}
	
	public int selectLastScore(final String gameName) {
		SQLiteDatabase myDB = this.getReadableDatabase();
		Cursor cursor = myDB.rawQuery("SELECT "+ fLastScore +" FROM "+ tHighScores +" WHERE "+ fGameName +"=?",new String []{String.valueOf(gameName)});
		if(cursor!=null && cursor.getCount()==0){
			cursor.close();
			return -1;
		}
		if(!cursor.moveToFirst()){
			cursor.close();
			return -1;
		}
		int index = cursor.getColumnIndex(fLastScore);
		int score = cursor.getInt(index);
		cursor.close();
		return score;
	}

	public int updateHighScore(final int ID, final int highSocre)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fHighestScore, highSocre);
		int numRowsAffected = myDB.update(tHighScores, cv, fGameID+"=?", new String []{String.valueOf(ID)});
		return numRowsAffected;
	}
	
	public int updateHighScore(final String gameName, final int highSocre)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fHighestScore, highSocre);
		int numRowsAffected = myDB.update(tHighScores, cv, fGameName+"=?", new String []{String.valueOf(gameName)});
		return numRowsAffected;
	}

	public int updateLastScore(final int ID, final int lastSocre)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fLastScore, lastSocre);
		int numRowsAffected = myDB.update(tHighScores, cv, fGameID+"=?", new String []{String.valueOf(ID)});
		return numRowsAffected;
	}
	
	public int updateLastScore(final String gameName, final int lastSocre)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(fLastScore, lastSocre);
		int numRowsAffected = myDB.update(tHighScores, cv, fGameName+"=?", new String []{String.valueOf(gameName)});
		return numRowsAffected;
	}
}
