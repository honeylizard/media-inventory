package com.weissbern.media.inventory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * class Media
 */
public class Media {

    private int id;
    private String title;
    private String types;
    private String notes;
    private int image;

    private static final String TYPE_VIDEO = "video";
    private static final String TYPE_GAME = "game";
    private static final String TYPE_BOOK = "book";
    private static final String TYPE_MUSIC = "music";

    // Movie/TV Formats
    private static final String SUBTYPE_VIDEO_DVD = "dvd";
    private static final String SUBTYPE_VIDEO_BLURAY = "bluray";

    // Sony Playstation Game Formats
    private static final String SUBTYPE_GAME_PS1 = "ps1";
    private static final String SUBTYPE_GAME_PS2 = "ps2";
    private static final String SUBTYPE_GAME_PS3 = "ps3";
    private static final String SUBTYPE_GAME_PS4 = "ps4";

    // Microsoft Xbox Game Formats
    private static final String SUBTYPE_GAME_XBOX = "xbox";
    private static final String SUBTYPE_GAME_XBOX360 = "xbox360";
    private static final String SUBTYPE_GAME_XBOX1 = "xboxone";

    // Nintendo Game Formats
    private static final String SUBTYPE_GAME_WII = "wii";
    private static final String SUBTYPE_GAME_SWITCH = "switch";

    /**
     * Class constructor.
     */
    public Media(int id, String title, String types, String notes, int image) {
        this.id = id;
        this.title = title;
        this.types = types;
        this.notes = notes;
        this.image = image;
    }

    /**
     * Get the ID of the media.
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Get the title of the media.
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the types of the media. Examples:
     * Video: DVD, Blu-Ray
     * Game: Playstation, Microsoft, Nintendo
     *
     * @return String
     */
    public String getTypes() {
        return types;
    }

    /**
     * Get the notes related to the media.
     *
     * @return String
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Get the image icon ID related to the media.
     *
     * @return int
     */
    public int getImage() {
        return image;
    }

    /**
     * Gets the icon ID for the type of media.
     *
     * @param media the media object in JSON format.
     * @return int
     */
    public static int getMediaTypeDrawable(JSONObject media) {
        String media_type = "default";

        try {
            media_type = media.getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int drawable_id;

        switch (media_type) {
            case Media.TYPE_VIDEO:
                drawable_id = R.drawable.ic_theaters_black_24dp;
                break;
            case Media.TYPE_GAME:
                drawable_id = R.drawable.ic_videogame_asset_black_24dp;
                break;
            case Media.TYPE_MUSIC:
                drawable_id = R.drawable.ic_music_note_black_24dp;
                break;
            default:
                drawable_id = R.drawable.ic_texture_black_24dp;
                break;
        }

        return drawable_id;
    }

    /**
     * Generate a list of subtypes associated with the media.
     *
     * @param media the media object in JSON format.
     * @return String
     */
    public static String getSubtypesList(JSONObject media) {
        String media_type = "default";

        try {
            media_type = media.getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String output = "";
        if (media_type.equals(Media.TYPE_VIDEO)) {
            output = getVideoSubtypesList(media);
        }
        if (media_type.equals(Media.TYPE_GAME)) {
            output = getGameSubtypesList(media);
        }

        return output;
    }

    /**
     * Generate a list of video types associated with the media.
     *
     * @param media the media object in JSON format.
     * @return String
     */
    private static String getVideoSubtypesList(JSONObject media) {
        String output = "";

        try {
            // TODO: import FontAwesome and convert text to icons.
            if (media.getString(Media.SUBTYPE_VIDEO_DVD).equals("X")) {
                output += "DVD ";
            }
            if (media.getString(Media.SUBTYPE_VIDEO_BLURAY).equals("X")) {
                output += "Blu-Ray ";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * Generate a list of game types associated with the media.
     *
     * @param media the media object in JSON format.
     * @return String
     */
    private static String getGameSubtypesList(JSONObject media) {
        String output = "";

        try {
            // TODO: import FontAwesome and convert text to icons.
            if (media.getString(Media.SUBTYPE_GAME_PS1).equals("X")) {
                output += "PS1 ";
            }
            if (media.getString(Media.SUBTYPE_GAME_PS2).equals("X")) {
                output += "PS2 ";
            }
            if (media.getString(Media.SUBTYPE_GAME_PS3).equals("X")) {
                output += "PS3 ";
            }
            if (media.getString(Media.SUBTYPE_GAME_PS4).equals("X")) {
                output += "PS4 ";
            }
            if (media.getString(Media.SUBTYPE_GAME_XBOX).equals("X")) {
                output += "Xbox ";
            }
            if (media.getString(Media.SUBTYPE_GAME_XBOX360).equals("X")) {
                output += "Xbox 360 ";
            }
            if (media.getString(Media.SUBTYPE_GAME_XBOX1).equals("X")) {
                output += "Xbox One ";
            }
            if (media.getString(Media.SUBTYPE_GAME_WII).equals("X")) {
                output += "Nintendo Wii ";
            }
            if (media.getString(Media.SUBTYPE_GAME_SWITCH).equals("X")) {
                output += "Nintendo Switch ";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return output;
    }

}
