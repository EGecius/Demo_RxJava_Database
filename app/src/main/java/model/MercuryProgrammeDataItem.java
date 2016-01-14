package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Implements {@link ProgrammeDataItem}
 */
public class MercuryProgrammeDataItem implements ProgrammeDataItem {

	public static final String TAG = MercuryProgrammeDataItem.class.getSimpleName();

	/* Title of the programme, e.g. "Emmerdale" */
	private final String programmeTitle;

	/* Title of the episode, e.g. "People in Yorkshire talk about Yorkshire" */
	private final String episodeTitle;

	/* Synopsis of the episode, e.g. "John Doe talks a lot about Yorkshire, and no-one cares" */
	private final String synopsis;

	/* Url of the episode image */
	private final String imageUrl;

	/* Channel that the episode is being played on, e.g. "ITV1" */
	private final String channel;

	/* The show's genres, e.g. "MURDER; KITTENS" */
	private final String genres;

	/* Duration of the episode, in minutes */
	private final int duration;

	/* Date that the episode was last broadcast */
	private final GregorianCalendar lastBroadcastDate;

	/* Number of days that the episode will be available on catch-up */
	private final int daysRemaining;

	/* Episode parental guidance warning */
	private final String parentalGuidanceWarning;

	/* Programme metadata */
	private final ProgrammeMetadata programmeMetadata;

	public MercuryProgrammeDataItem(String programmeTitle, String episodeTitle, String synopsis, String imageUrl,
									String channel,  String genres, int duration, GregorianCalendar
										lastBroadcastDate,  int daysRemaining, String parentalGuidanceWarning,
									ProgrammeMetadata programmeMetadata) {
		this.programmeTitle = programmeTitle;
		this.episodeTitle = episodeTitle;
		this.synopsis = synopsis;
		this.imageUrl = imageUrl;
		this.channel = channel;
		this.genres = genres;
		this.duration = duration;
		this.lastBroadcastDate = lastBroadcastDate;
		this.daysRemaining = daysRemaining;
		this.parentalGuidanceWarning = parentalGuidanceWarning;
		this.programmeMetadata = programmeMetadata;
	}

	@Override
	public String getProgrammeTitle() {
		return programmeTitle;
	}

	@Override
	public String getEpisodeTitle() {
		return episodeTitle;
	}

	@Override
	public String getSynopsis() {
		return synopsis;
	}

	@Override
	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public String getChannel() {
		return channel;
	}

	@Override
	public String getGenres() {
		return genres;
	}

	@Override
	public int getDuration() {
		return duration;
	}

	@Override
	public GregorianCalendar getLastBroadcastDate() {
		return lastBroadcastDate;
	}

	@Override
	public int getDaysRemaining() {
		return daysRemaining;
	}

	@Override
	public String getParentalGuidanceWarning() {
		return parentalGuidanceWarning;
	}

	@Override
	public boolean hasParentalGuidanceWarning() {
		return getParentalGuidanceWarning() != null;
	}

	@Override
	public ProgrammeMetadata getProgrammeMetadata() {
		return programmeMetadata;
	}

	private GregorianCalendar computeLastBroadcastDate(String lastBroadcast) {
		try {
			DateFormat format = new SimpleDateFormat("dd MMMMM yyyy");
			Date date = format.parse(lastBroadcast);
			GregorianCalendar lastBroadcastDate = new GregorianCalendar();
			lastBroadcastDate.setTime(date);
			return lastBroadcastDate;
		} catch (ParseException e) {
			DebugLog.w(TAG, "Could not parse last broadcast date.  Raw field  was: " + lastBroadcast);
		}
		return null;
	}

}