package com.example.jackson;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

public class ContactsController {
	private final String json = "{ \"recipes\": \n" + "  [ \n" + "    {\n" + "      \"name\":\"Recipe 1\",\n" + "      \"id\":\"8aecfd9b2fa26e83012fa298c2a50017\",\n" + "      \"recipe\":\"1 Lorem ipsum...\",\n" + "      \"image\":\"/malibu-server/recipe/getImage/8aecfd9b2fa26e83012fa298c2a50017\"\n" + "    }, \n" + "    { \n" + "      \"name\":\"Recipe 2\",\n" + "      \"id\":\"8aecfd9b2fa26e83012fa298c2a90018\",\n" + "      \"recipe\":\"2 Lorem ipsum...\",\n" + "      \"image\": \"/malibu-server/recipe/getImage/8aecfd9b2fa26e83012fa298c2a90018\"\n" + "    },\n" + "    {\n" + "      \"name\": \"Recipe 3\",\n" + "      \"id\":\"8aecfd9b2fa26e83012fa298c2ae0019\",\n" + "      \"recipe\":\"3 Lorem ipsum...\",\n" + "      \"image\":  \"/malibu-server/recipe/getImage/8aecfd9b2fa26e83012fa298c2ae0019\"\n" + "      } \n" + "  ]\n" + "}\n" + "";
	
	private  String jString = null;
	private ObjectMapper objectMapper = null;
	 private JsonFactory jsonFactory = null;
	 private JsonParser jp = null;
	 private ArrayList<Contact> contacts = null;
	 private Contacts mContacts = null;
	 
	 public ContactsController()
     {
       objectMapper = new ObjectMapper();
       jsonFactory = new JsonFactory();
     }
	 
	 public ContactsController(String jsonString)
     {
       objectMapper = new ObjectMapper();
       jsonFactory = new JsonFactory();
       jString = jsonString;
     }

	 public void init()
     {
       try
         {
           jp = jsonFactory.createJsonParser(jString);
           mContacts = objectMapper.readValue(jp, Contacts.class);
           contacts = mContacts.get("contacts");
         }
       catch (JsonParseException e)
         {
           e.printStackTrace();
         }
       catch (IOException e)
         {
           e.printStackTrace();
         }
     }

	 public ArrayList<Contact> findAll()
     {
       return contacts;
     }

   	 public Contact findById(int id)
     {
       return contacts.get(id);
     }
}
