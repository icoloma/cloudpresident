package com.me.president;
import com.googlecode.objectify.*;

public class ObjectifyDAO {
    static { ObjectifyService.register(Comment.class); } // register all @Entity classes
    static Objectify ofy() { return ObjectifyService.ofy(); }  // proxy for Objectify
}
