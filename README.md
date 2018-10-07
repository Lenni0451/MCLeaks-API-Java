# MCLeaks-API-Java
This is an easy to use Java lib to comunicate with the MCLeaks API.
With this you can provide your users the possibility of using an MCLeaks Alt token (e.g for a Hacked Client).

I am not working together with the team behind [MCLeaks](https://mcleaks.net/).
I am only developing this API.

# How it works
To redeem an alto token (e.g 'GFUGuKqxCDHjdNAz') to get the aession ID and the account name back use:
```java
MCLeaksAccount account = MCLeaksAPIConnection.getAccountFromToken("GFUGuKqxCDHjdNAz");
```
The ```MCLeaksAccount``` you get as a result contains the alt token, the username and the session id.
You can get those by calling:
```java
account.getToken();   //Get the alt token
account.getUsername() //Get the username
account.getSession(); //Get the session id
```

To join a server you have to call:
```java
//Replace 'account' with your MCLeaksAccount variable
//Replace 'mc.hypixel.net:25565' with the server ip
//Replace '-1ja8s98dhj92h9aasd8' with the server hash
MCLeaksAPIConnection.joinServer(account, "mc.hypixel.net:25565", "-1ja8s98dhj92h9aasd8");
```

# Dependencies
This lib uses [Gson](https://github.com/google/gson) for all of the json stuff

# How to use it with maven
I don't know
