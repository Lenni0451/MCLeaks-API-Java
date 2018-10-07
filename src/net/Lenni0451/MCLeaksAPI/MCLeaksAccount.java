package net.Lenni0451.MCLeaksAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class MCLeaksAccount {
	
	private final String token;
	private final String username;
	private final String session;
	
	public MCLeaksAccount(final String token, final String username, final String session) {
		this.token = token;
		this.username = username;
		this.session = session;
	}

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}

	public String getSession() {
		return session;
	}
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		JsonObject accountObject = new JsonObject();
		accountObject.add("token", gson.toJsonTree(this.token));
		accountObject.add("name", gson.toJsonTree(this.username));
		accountObject.add("session", gson.toJsonTree(this.session));
		return accountObject.toString();
	}
	
}
