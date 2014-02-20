package com.me.president;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.common.collect.Maps;

@SuppressWarnings("serial")
public class ThePresidentServlet extends HttpServlet {
	
	MustacheFactory mf = new DefaultMustacheFactory();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	    InputStream template = getServletContext()
	    		.getResourceAsStream("/WEB-INF/comments.html");
		Mustache mustache = mf.compile(
				new InputStreamReader(template, "UTF-8"), "example");
		
		Writer writer = resp.getWriter();
	    mustache.execute(writer, initArgs(req));
	    writer.flush();
	}
	
	private Map<String, Object> initArgs(HttpServletRequest req) {
		UserService userService = UserServiceFactory.getUserService();
		Map<String, Object> args = Maps.newHashMap();
		args.put("name", "Obama?");
		args.put("user", userService.getCurrentUser());
		args.put("loginUrl", userService.createLoginURL(req.getRequestURI()));
		args.put("logoutUrl", userService.createLogoutURL(req.getRequestURI()));
		return args;
	}
	
}
