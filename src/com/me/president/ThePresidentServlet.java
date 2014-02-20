package com.me.president;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

@SuppressWarnings("serial")
public class ThePresidentServlet extends HttpServlet {
	
	MustacheFactory mf = new DefaultMustacheFactory();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	    InputStream template = getServletContext().getResourceAsStream("/WEB-INF/comments.html");
		Mustache mustache = mf.compile(new InputStreamReader(template, "UTF-8"), "example");
		
		Map<String, Object> args = Maps.newHashMap();
		args.put("name", "Obama?");
		
		Writer writer = resp.getWriter();
	    mustache.execute(writer, args);
	    writer.flush();
	}
	
}
