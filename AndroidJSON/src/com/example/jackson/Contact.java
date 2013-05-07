package com.example.jackson;

public class Contact {
	
//	public String name;
//    public String id;
//    public String recipe;
//    public String image;
    
	public String id;
	public String name;
	public String email;
	public String address;
	public String gender;
	public Phone phone;
	
	public class Phone{
		public String mobile;
		public String home;
		public String office;
	}
	
}
/**
 * {
 *  "id": "c200",
 *  "name": "Ravi Tamada",
 *	"email": "ravi@gmail.com",
 *	"address": "xx-xx-xxxx,x - street, x - country",
 *	"gender" : "male",
 *	"phone": {
 *     "mobile": "+91 0000000000",
 *     "home": "00 000000",
 *     "office": "00 000000"
 *   }
 * }
 */
