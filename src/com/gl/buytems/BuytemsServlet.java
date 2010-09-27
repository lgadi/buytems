package com.gl.buytems;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gl.buytems.data.User;

@SuppressWarnings("serial")
public class BuytemsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user = new User("Gadi" + System.currentTimeMillis(), new Date());
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(user);
			resp.setContentType("text/plain");
			resp.getWriter().println("Hello, world");
			String query = "select from " + User.class.getName();
			List<User> users = (List<User>) pm.newQuery(query).execute();
			for (User user1:users) {
				resp.getWriter().println("<br>"+user1.getName());
			}
		} finally {
			pm.close();
		}

		
	}
}
