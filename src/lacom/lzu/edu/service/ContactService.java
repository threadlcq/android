package lacom.lzu.edu.service;

import java.util.ArrayList;
import java.util.List;

import lacom.lzu.edu.ado.Contact;

public class ContactService
{
	 public List<Contact> getContacts(){  
	        List<Contact> contacts = new ArrayList<Contact>();  
	        contacts.add(new Contact(2, "shenlu", "5201314"));  
	        contacts.add(new Contact(12, "shenlu", "5201314"));  
	        contacts.add(new Contact(22, "shenlu", "5201314"));  
	        return contacts;  
	    }  
}
