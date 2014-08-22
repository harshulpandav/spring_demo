import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.idiginfo.docsvc.jpa.citagora.TestSpring;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CalServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
//		TestSpring testSpring = (TestSpring) ac.getBean("testSpring");
		TestSpring testSpring = new TestSpring();
		testSpring.read();
		testSpring.save();
//		response.getWriter().print("Adding of numbers is = " + cal.doAdd());
		response.getWriter().close();
	}
}
