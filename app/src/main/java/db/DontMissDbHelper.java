package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import model.ProgrammeDataItem;

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

//	public static final String ID_COLUMN_1 = "Id";
//	public static final int ID_COLUMN_POSITION = 1;

	public static final String PROGRAMME_TITLE_COLUMN_2 = "ProgrammeTitle";
	public static final int PROGRAMME_TITLE_COLUMN_POSITION_2 = 2;

	public static final String EPISODE_TITLE_COLUMN_3 = "EpisodeTitle";
	public static final int EPISODE_TITLE_POSITION_3 = 3;

	public static final String SYNOPSIS_COLUMN_4 = "Synopsis";
	public static final int SYNOPSIS_COLUMN_POSITION_4 = 4;

//	String getImageUrl();

	public static final String IMAGE_URL_COLUMN_5 = "ImageUrl";
	public static final int IMAGE_URL_COLUMN_POSITION_5 = 5;

//	String getChannel();

	public static final String CHANNEL_COLUMN_6 = "Channel";
	public static final int CHANNEL_COLUMN_POSITION_6 = 6;

//		String getGenres();

	public static final String GENRES_COLUMN_7 = "Genres";
	public static final int GENRES__COLUMN_POSITION_7 = 7;

//	int getDuration();

	public static final String DURATION_COLUMN_8 = "Duration";
	public static final int DURATION_COLUMN_POSITION_8 = 8;

//	GregorianCalendar getLastBroadcastDate();

	public static final String LAST_BROADCAST_DATE_COLUMN_9 = "LastBroadcastDate";
	public static final int LAST_BROADCAST_DATE_COLUMN_POSITION_9 = 9;

//	int getDaysRemaining();

	public static final String DAYS_REMAINING_COLUMN_10 = "DaysRemaining";
	public static final int DAYS_REMAINING_COLUMN_POSITION_10 = 10;

//	String getParentalGuidanceWarning();


	public static final String PARENTAL_GUIDANCE_WARNING_COLUMN_11 = "ParentalGuidanceWarning";
	public static final int PARENTAL_GUIDANCE_WARNING_COLUMN_POSITION_11 = 11;

// ----	ProgrammeMetadata ----

//	String getProgrammeId();


	public static final String PROGRAMME_ID_COLUMN_12 = "ProgrammeId";
	public static final int PROGRAMME_ID_COLUMN_POSITION_12 = 12;

//		String getEpisodeId();


	public static final String EPISODE_ID_COLUMN_13 = "EpisodeId";
	public static final int EPISODE_ID_COLUMN_POSITION_13 = 13;

//	String getEpisodeProductionId();


	public static final String EPISODE_PRODUCTION_ID_COLUMN_14 = "EpisodeProductionId";
	public static final int EPISODE_PRODUCTION_ID_COLUMN_POSITION_14 = 14;








	// -------- TABLES CREATION ----------


	// Repo CREATION
	private static final String DATABASE_REPO_CREATE = "create table " + TABLE + " (" +
		"_id integer primary key autoincrement, " +
		PROGRAMME_TITLE_COLUMN_2 + " text, " +
		EPISODE_TITLE_COLUMN_3 + " text, " +
		SYNOPSIS_COLUMN_4 + " text, " +
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



	// ---------------- HELPERS --------------------

	public long addProgrrameItem (ProgrammeDataItem item) {
		ContentValues values = getContentValues(item);
		return sqliteDb.insert(TABLE, null, values);
	}

	@NonNull
	private ContentValues getContentValues(ProgrammeDataItem item) {
		ContentValues values = new ContentValues();
		values.put(PROGRAMME_TITLE_COLUMN_2, item.getProgrammeTitle());
		values.put(EPISODE_TITLE_COLUMN_3, item.getEpisodeTitle());
		values.put(SYNOPSIS_COLUMN_4, item.getSynopsis());
		values.put(IMAGE_URL_COLUMN_5, item.getImageUrl());
		values.put(CHANNEL_COLUMN_6, item.getChannel());
		values.put(GENRES_COLUMN_7, item.getGenres());
		values.put(DURATION_COLUMN_8, item.getDuration());
		values.put(LAST_BROADCAST_DATE_COLUMN_9, item.getLastBroadcastDate().toString());
		values.put(DAYS_REMAINING_COLUMN_10, item.getDaysRemaining());
		values.put(PARENTAL_GUIDANCE_WARNING_COLUMN_11, item.getParentalGuidanceWarning());
		values.put(PROGRAMME_ID_COLUMN_12, item.getProgrammeMetadata().getProgrammeId());
		values.put(EPISODE_ID_COLUMN_13, item.getProgrammeMetadata().getEpisodeId());
		values.put(EPISODE_PRODUCTION_ID_COLUMN_14, item.getProgrammeMetadata().getEpisodeProductionId());
		return values;
	}

	public long updateProgramme(long rowIndex, ProgrammeDataItem item) {
		String where = ROW_ID + " = " + rowIndex;
		ContentValues contentValues = getContentValues(item);
		return sqliteDb.update(TABLE, contentValues, where, null);
	}

	public boolean removeProgramme(long rowIndex){
		return sqliteDb.delete(TABLE, ROW_ID + " = " + rowIndex, null) > 0;
	}

	public boolean removeAllProgrammes(){
		return sqliteDb.delete(TABLE, null, null) > 0;
	}

	public Cursor getAllProgrammes(){
		return sqliteDb.query(TABLE, new String[] {
			ROW_ID, PROGRAMME_TITLE_COLUMN_2, EPISODE_TITLE_COLUMN_3, SYNOPSIS_COLUMN_4,
			IMAGE_URL_COLUMN_5, CHANNEL_COLUMN_6, GENRES_COLUMN_7, DURATION_COLUMN_8, LAST_BROADCAST_DATE_COLUMN_9,
			DAYS_REMAINING_COLUMN_10, PARENTAL_GUIDANCE_WARNING_COLUMN_11, PROGRAMME_ID_COLUMN_12,
			EPISODE_ID_COLUMN_13, EPISODE_PRODUCTION_ID_COLUMN_14
		}, null, null, null, null, null);
	}

	public Cursor getProgramme(long rowIndex) {
		Cursor res = sqliteDb.query(TABLE, new String[] {
			ROW_ID, PROGRAMME_TITLE_COLUMN_2, EPISODE_TITLE_COLUMN_3, SYNOPSIS_COLUMN_4,
			IMAGE_URL_COLUMN_5, CHANNEL_COLUMN_6, GENRES_COLUMN_7, DURATION_COLUMN_8, LAST_BROADCAST_DATE_COLUMN_9,
			DAYS_REMAINING_COLUMN_10, PARENTAL_GUIDANCE_WARNING_COLUMN_11, PROGRAMME_ID_COLUMN_12,
			EPISODE_ID_COLUMN_13, EPISODE_PRODUCTION_ID_COLUMN_14
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