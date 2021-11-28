import java.util.HashMap;
import java.util.*;

public class Main {
	
  public static void main (String[]args){

    String userChoice = "";
    URLShortner urlshortner = new URLShortner ();
    Scanner sc = new Scanner (System.in);

    do {
	userChoice = sc.nextLine ();

	String[] commandsSeparated = userChoice.split (" ");

	if (commandsSeparated.length > 0 && commandsSeparated.length <= 2) {
	    if (commandsSeparated.length == 1) {

		if (commandsSeparated[0].equalsIgnoreCase ("list")) {
		    urlshortner.listAllInfo ();
		  }
		else {
		    System.out.println ("Invalid Command and Argument");
		  }
		// System.out.println("Command is");
		// System.out.println(commandsSeparated[0]);
	      }

	    if (commandsSeparated.length == 2) {

		if (commandsSeparated[0].equalsIgnoreCase ("storeurl")) {
		    // System.out.print("Enter a url: "); 
		    urlshortner.setURL (commandsSeparated[1]);
		    urlshortner.createShortURL ();
		  }

		else if (commandsSeparated[0].equalsIgnoreCase ("get")) {
		    urlshortner.displayInfoByName (commandsSeparated[1]);
		}

		else if (commandsSeparated[0].equalsIgnoreCase ("count")) {
		    urlshortner.listCountByURL (commandsSeparated[1]);
		 }

		else{
		    System.out.println ("Invalid Command and Argument");
		  }
		// System.out.println("Command is");
		// System.out.println(commandsSeparated[0]);
		// System.out.println("argument is");
		// System.out.println(commandsSeparated[1]);
	      }
	  }
	else {
	    System.out.println ("Invalid Command and Argument");
	  }

      }while (userChoice.equalsIgnoreCase ("exit") != true);
  }
}

class URLShortner{

  private String url;
  private String shortendURL;
  private int count = 0;
  private HashMap < String, HashMap < String, Integer >> map =
    new HashMap < String, HashMap < String, Integer >> ();

  public final String randomString =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  public String getURL() {
    return this.url;
  }

  public String getShortendURL(){
    return this.shortendURL;
  }

  public void setURL(String url){
    this.url = url;
  }

  private void setShortendURL(String shortendURL){
    this.shortendURL = shortendURL;
  }

  public void displayInfo (){
    System.out.println("Saved:");
    System.out.println(this.url);
    System.out.println(this.shortendURL);
  }

  public void displayInfoByName (String url)
  {

    if (this.map.containsKey (url))
      {

	HashMap < String, Integer > innerMap = map.get (url);

      for (String key:innerMap.keySet ())
	  {
	    System.out.println (key + " short link-" + innerMap.get (key));
	    Integer j = innerMap.get (key);
	    innerMap.put (key, (j + 1));
	    System.out.println ("value for this url " + url + " is:- " + key);
	    System.out.println ("count for this url:-" + j);
	  }

      }
    else
      {
	System.out.println ("this url doesnt exist");
      }
  }

  public void createShortURL ()
  {
    String shortLink = "";

    for (int i = 0; i < 12; i++)
      {
	int index = (int) (Math.random () * 100) % 62;
	shortLink += this.randomString.charAt (index);
      }

    HashMap < String, Integer > innerMap = new HashMap <> ();
    innerMap.put (shortLink, this.count);

    this.map.put (this.url, innerMap);
    this.setShortendURL (shortLink);
    displayInfo ();
  }

  public void listCountByURL (String url){

    if (this.map.containsKey (url)){

	HashMap < String, Integer > innerMap = map.get (url);

      for (String key:innerMap.keySet ()) {
	    Integer j = innerMap.get (key);
	    innerMap.put (key, (j + 1));
	    System.out.println ("count for " + key + " :-" + j);
	  }

      }
    else {
	System.out.println ("this url doesnt exist");
      }

  }

  public void listAllInfo (){

  for (String key:map.keySet ()) {
	HashMap < String, Integer > innerMap = this.map.get (key);

      for (String innerKey:innerMap.keySet ()) {
	    System.out.println (innerKey + " short link-" +
				innerMap.get (innerKey));
	    System.out.println ("value for this url " + url + " is:- " +
				innerKey);
	    System.out.println ("count for this url:-" +
				innerMap.get (innerKey));
	  }
      }
  }
}
