package model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Returns system-specific fields used to identify the content item
 */
public interface ProgrammeMetadata extends Serializable {

	/**
	 * Programme id.  This identifies a specific programme, and seems to generally take the form of a GUID.
	 * This is a legacy identifier only used by Mercury; CPS should use Production ID instead
	 * @return programme id
	 */
	@NonNull
	String getProgrammeId();

	/**
	 * Episode id.  This identifies a specific episode, and seems to generally be a number series.
	 * This is a legacy identifier only used by Mercury; CPS should use Production ID instead
	 * @return episode id
	 */
	@Nullable
	String getEpisodeId();

	/**
	 * Production id.  This is used to identify a specific production (combination of programme and episode)
	 * @return production id
	 */
	@Nullable
	String getEpisodeProductionId();

}
