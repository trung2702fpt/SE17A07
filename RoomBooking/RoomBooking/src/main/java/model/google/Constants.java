package model.google;

public class Constants {
	public static String GOOGLE_CLIENT_ID = "89306432316-rs2mce69g2ta88splene875bpvihem12.apps.googleusercontent.com";
	public static String GOOGLE_CLIENT_SECRET = "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";
	public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/RoomBooking/Login";
	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=";
	public static String GOOGLE_GRANT_TYPE = "authorization_code";
        public static String GOOGLE_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
}
