package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DontMissDbHelper {

	private static final String TAG = "DbHelper";

	private static final String DATABASE_NAME = "DontMissDbHelper.db";
	private static final int DATABASE_VERSION = 1;


	// Variable to hold the database instance
	protected SQLiteDatabase sqliteDb;
	// Context of the application using the database.
	private final Context context;
	// Database open/upgrade helper
	private DbHelper dbHelper;

	public DontMissDbHelper(Context context) {
		this.context = context;
		dbHelper = new DbHelper(this.context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public DontMissDbHelper open() throws SQLException {
		sqliteDb = dbHelper.getWritableDatabase();
		return this;
	}

	public DontMissDbHelper openForRead() throws SQLException {
		sqliteDb = dbHelper.getReadableDatabase();
		return this;
	}

	public void close() {
		sqliteDb.close();
	}

	public static final String ROW_ID = "_id";


	// -------------- DEFINITIONS ------------
	public static final String TABLE = "DontMiss";

	public static final String ID_COLUMN_1 = "Id";
	public static final int ID_COLUMN_POSITION = 1;

	public static final String PROGRAMME_TITLE_COLUMN_2 = "ProgrammeTitle";
	public static final int PROGRAMME_TITLE_COLUMN_POSITION = 2;

	public static final String EPISODE_TITLE_COLUMN_3 = "EpisodeTitle";
	public static final int EPISODE_TITLE_POSITION = 3;

	public static final String SYNOPSIS_COLUMN_4 = "Synopsis";
	public static final int SYNOPSIS_COLUMN_POSITION = 4;

//	String getImageUrl();

	public static final String IMAGE_URL_COLUMN_5 = "ImageUrl";
	public static final int IMAGE_URL_COLUMN_POSITION = 5;

//	String getChannel();

	public static final String CHANNEL_COLUMN_6 = "Channel";
	public static final int CHANNEL_COLUMN_POSITION = 6;

//		String getGenres();

	public static final String GENRES_COLUMN_7 = "Genres";
	public static final int GENRES__COLUMN_POSITION = 7;

//	int getDuration();

	public static final String DURATION_COLUMN_8 = "Duration";
	public static final int DURATION_COLUMN_POSITION = 8;

//	GregorianCalendar getLastBroadcastDate();

	public static final String LAST_BROADCAST_DATE_COLUMN_9 = "LastBroadcastDate";
	public static final int LAST_BROADCAST_DATE_COLUMN_POSITION = 9;

//	int getDaysRemaining();

	public static final String DAYS_REMAINING_COLUMN_10 = "DaysRemaining";
	public static final int DAYS_REMAINING_COLUMN_POSITION = 10;

//	String getParentalGuidanceWarning();


	public static final String PARENTAL_GUIDANCE_WARNING_COLUMN_11 = "ParentalGuidanceWarning";
	public static final int PARENTAL_GUIDANCE_WARNING_COLUMN_POSITION = 11;

// ----	ProgrammeMetadata ----

//	String getProgrammeId();


	public static final String PROGRAMME_ID_COLUMN_12 = "ProgrammeId";
	public static final int PROGRAMME_ID_COLUMN_POSITION = 12;

//		String getEpisodeId();


	public static final String EPISODE_ID_COLUMN_13 = "EpisodeId";
	public static final int EPISODE_ID_COLUMN_POSITION = 13;

//	String getEpisodeProductionId();


	public static final String EPISODE_PRODUCTION_ID_COLUMN_14 = "EpisodeProductionId";
	public static final int EPISODE_PRODUCTION_ID_COLUMN_POSITION = 14;








	// -------- TABLES CREATION ----------


	// Repo CREATION
	private static final String DATABASE_REPO_CREATE = "create table " + TABLE + " (" +
		"_id integer primary key autoincrement, " +
		ID_COLUMN_1 + " text, " +
		PROGRAMME_TITLE_COLUMN_2 + " text, " +
		EPISODE_TITLE_COLUMN_3 + " text, " +
		IMAGE_URL_COLUMN_5 + " text" +
		CHANNEL_COLUMN_6 + " text" +
		GENRES_COLUMN_7 + " text" +
		DURATION_COLUMN_8 + " text" +
		LAST_BROADCAST_DATE_COLUMN_9 + " text" +
		DAYS_REMAINING_COLUMN_10 + " text" +
		PARENTAL_GUIDANCE_WARNING_COLUMN_11 + " text" +
		PROGRAMME_ID_COLUMN_12 + " text" +
		EPISODE_ID_COLUMN_13 + " text" +
		EPISODE_PRODUCTION_ID_COLUMN_14 + " text" +
		")";



	// ----------------Repo HELPERS --------------------
	public long addRepo (String Id, String Name, String FullName, String Owner) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(ID_COLUMN_1, Id);
		contentValues.put(PROGRAMME_TITLE_COLUMN_2, Name);
		contentValues.put(EPISODE_TITLE_COLUMN_3, FullName);
		contentValues.put(SYNOPSIS_COLUMN_4, Owner);
		return sqliteDb.insert(TABLE, null, contentValues);
	}

	public long updateRepo (long rowIndex,String Id, String Name, String FullName, String Owner) {
		String where = ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(ID_COLUMN_1, Id);
		contentValues.put(PROGRAMME_TITLE_COLUMN_2, Name);
		contentValues.put(EPISODE_TITLE_COLUMN_3, FullName);
		contentValues.put(SYNOPSIS_COLUMN_4, Owner);
		return sqliteDb.update(TABLE, contentValues, where, null);
	}

	public boolean removeRepo(long rowIndex){
		return sqliteDb.delete(TABLE, ROW_ID + " = " + rowIndex, null) > 0;
	}

	public boolean removeAllRepo(){
		return sqliteDb.delete(TABLE, null, null) > 0;
	}

	public Cursor getAllRepo(){
		return sqliteDb.query(TABLE, new String[] {
			ROW_ID, ID_COLUMN_1, PROGRAMME_TITLE_COLUMN_2, EPISODE_TITLE_COLUMN_3, SYNOPSIS_COLUMN_4
		}, null, null, null, null, null);
	}

	public Cursor getRepo(long rowIndex) {
		Cursor res = sqliteDb.query(TABLE, new String[] {
			ROW_ID, ID_COLUMN_1, PROGRAMME_TITLE_COLUMN_2, EPISODE_TITLE_COLUMN_3, SYNOPSIS_COLUMN_4
		}, ROW_ID + " = " + rowIndex, null, null, null, null);

		if(res != null){
			res.moveToFirst();
		}
		return res;
	}


	private static class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		// Called when no database exists in disk and the helper class needs
		// to create a new one.
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_REPO_CREATE);

		}

		// Called when there is a database version mismatch meaning that the version
		// of the database on disk needs to be upgraded to the current version.
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Log the version upgrade.
			Log.w(TAG, "Upgrading from version " +
				oldVersion + " to " +
				newVersion + ", which will destroy all old data");

			// Upgrade the existing database to conform to the new version. Multiple
			// previous versions can be handled by comparing _oldVersion and _newVersion
			// values.

			// The simplest case is to drop the old table and create a new one.
			db.execSQL("DROP TABLE IF EXISTS " + TABLE + ";");

			// Create a new one.
			onCreate(db);
		}
	}
}