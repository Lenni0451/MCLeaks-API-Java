package net.Lenni0451.MCLeaksAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.Lenni0451.MCLeaksAPI.exceptions.APIDownException;
import net.Lenni0451.MCLeaksAPI.exceptions.APIErrorException;
import net.Lenni0451.MCLeaksAPI.exceptions.InvalidResponseException;
import net.Lenni0451.MCLeaksAPI.exceptions.InvalidTokenException;
import net.Lenni0451.MCLeaksAPI.utils.NetworkUtils;

public class MCLeaksAPIConnection {
	
	private static final String apiURL = "https://auth.mcleaks.net/v1/";
	
	public static MCLeaksAccount getAccountFromToken(final String token) throws APIDownException, APIErrorException, InvalidResponseException, InvalidTokenException {
		if(token == null || token.length() != 16) {
			throw new InvalidTokenException();
		}
		JsonObject requestObject = new JsonObject();
		requestObject.add("token", new GsonBuilder().create().toJsonTree(token));
		String response;
		try {
			response = NetworkUtils.postJson(apiURL + "redeem", requestObject.toString());
		} catch (Exception e) {
			throw new APIDownException();
		}
		JsonObject responseObject;
		try {
			responseObject = new JsonParser().parse(response).getAsJsonObject();
		} catch (Exception e) {
			throw new InvalidResponseException(response);
		}
		if(!responseObject.get("success").getAsBoolean()) {
			throw new APIErrorException(responseObject.get("errorMessage").getAsString());
		}
		JsonObject resultObject = responseObject.get("result").getAsJsonObject();
		return new MCLeaksAccount(token, resultObject.get("mcname").getAsString(), resultObject.get("session").getAsString());
	}
	
	public static void joinServer(final MCLeaksAccount account, final String server, final String serverHash) throws APIDownException, InvalidResponseException, APIErrorException {
		Gson gson = new GsonBuilder().create();
		JsonObject requestObject = new JsonObject();
		requestObject.add("session", gson.toJsonTree(account.getSession()));
		requestObject.add("mcname", gson.toJsonTree(account.getUsername()));
		requestObject.add("serverhash", gson.toJsonTree(serverHash));
		requestObject.add("server", gson.toJsonTree(server));
		String response;
		try {
			response = NetworkUtils.postJson(apiURL + "joinserver", requestObject.toString());
		} catch (Exception e) {
			throw new APIDownException();
		}
		JsonObject responseObject;
		try {
			responseObject = new JsonParser().parse(response).getAsJsonObject();
		} catch (Exception e) {
			throw new InvalidResponseException(response);
		}
		if(!responseObject.get("success").getAsBoolean()) {
			throw new APIErrorException(responseObject.get("errorMessage").getAsString());
		}
	}
	
}
