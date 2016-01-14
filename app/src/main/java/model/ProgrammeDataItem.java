package model;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * an item that user can play
 */
public interface ProgrammeDataItem extends Serializable {



	/**
	 * Title of the programme, e.g. "Emmerdale"
	 * @return programme title
	 */
	String getProgrammeTitle();

	/**
	 * Title of the episode, e.g. "People in Yorkshire talk about Yorkshire"
	 * @return episode title
	 */
	String getEpisodeTitle();

	/**
	 * Synopsis of the episode, e.g. "John Doe talks a lot about Yorkshire, and no-one cares"
	 * @return synopsis
	 */
	String getSynopsis();

	/**
	 * Url of the episode image
	 * @return image url
	 */
	String getImageUrl();

	/**
	 * Channel that the episode is being played on, e.g. "ITV1"
	 * @return channel
	 */
	String getChannel();

	/**
	 * The show's genres, e.g. "MURDER; KITTENS"
	 * @return genres
	 */
	String getGenres();

	/**
	 * Duration of the episode, in minutes
	 * @return duration
	 */
	int getDuration();

	/**
	 * Date that the episode was last broadcast
	 * @return last broadcast date
	 */
	GregorianCalendar getLastBroadcastDate();

	/**
	 * Number of days that the episode will be available on catch-up
	 * @return days remaining
	 */
	int getDaysRemaining();

	/**
	 * Episode parental guidance warning
	 * @return parental guidance warning
	 */
	String getParentalGuidanceWarning();

	/**
	 * Is there a parental guidance warning associated with the episode
	 */
	boolean hasParentalGuidanceWarning();

	/**
	 * Programme metadata
	 * @return programme metadata
	 */
	ProgrammeMetadata getProgrammeMetadata();

}
