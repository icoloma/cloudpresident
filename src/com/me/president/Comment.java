package com.me.president;
import java.util.*;
import com.googlecode.objectify.annotation.*;
import static com.me.president.ObjectifyDAO.ofy; // now ofy() goes through the DAO

@Entity public class Comment {
   
	@Id Long id;     
	@Index Date date;     
	public String user, text;

   public Comment() {}
   public Comment(String text, String user) { this.user = user; this.text = text; this.date = new Date(); }

   public static void store(String text, String user) { 
	   ofy().save().entity(new Comment(text, user)); 
   }

   public static List<Comment> retrieveAll() { 
	   return ofy().load().type(Comment.class).order("-date").limit(100).list(); 
   }
}