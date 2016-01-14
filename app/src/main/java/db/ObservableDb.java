package db;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import model.MercuryProgrammeDataItem;
import model.ProgrammeDataItem;
import rx.Observable;
import rx.subjects.PublishSubject;

public class ObservableDb {
	private PublishSubject<List<ProgrammeDataItem>> mSubject = PublishSubject.create();
	private DontMissDbHelper mDbHelper;

	public ObservableDb(Context c) {
		mDbHelper = new DontMissDbHelper(c);
	}

	public Observable<List<ProgrammeDataItem>> getObservable() {
		Observable<List<ProgrammeDataItem>> firstTimeObservable =
			Observable.fromCallable(this:: getAllProgrammesFromDb);
        /* concatWith returns will emit emissions from mSubject in the same sequence */
		return firstTimeObservable.concatWith(mSubject);
	}

	private List<ProgrammeDataItem> getAllProgrammesFromDb() {
		mDbHelper.openForRead();
		List<ProgrammeDataItem> programmes = new ArrayList<>();
		Cursor c = mDbHelper.getAllProgrammes();
		if (!c.moveToFirst()) { // empty
			return programmes;
		}
		do {
			programmes.add(getItem(c));
		} while (c.moveToNext());
		c.close();
		mDbHelper.close();
		return programmes;
	}

	//todo fix the db
	@NonNull
	private MercuryProgrammeDataItem getItem(Cursor c) {
		return new MercuryProgrammeDataItem(
			c.getString(DontMissDbHelper.PROGRAMME_TITLE_COLUMN_POSITION_2),
			c.getString(DontMissDbHelper.EPISODE_TITLE_POSITION_3),
			c.getString(DontMissDbHelper.SYNOPSIS_COLUMN_POSITION_4),
			c.getString(DontMissDbHelper.IMAGE_URL_COLUMN_POSITION_5),
			c.getString(DontMissDbHelper.CHANNEL_COLUMN_POSITION_6),
			c.getString(DontMissDbHelper.GENRES__COLUMN_POSITION_7),
			0,
			null,
			0,
			c.getString(DontMissDbHelper.PARENTAL_GUIDANCE_WARNING_COLUMN_POSITION_11),
			null
			);
	}
}
