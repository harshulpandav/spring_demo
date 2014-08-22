import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.idiginfo.docsvc.jpa.citagora.AnnotationBodyImpl;
import org.idiginfo.docsvc.jpa.citagora.ContainerImpl;
import org.idiginfo.docsvc.jpa.citagora.PersonImpl;
import org.idiginfo.docsvc.jpa.citagora.ReferenceImpl;
import org.idiginfo.docsvc.jpa.citagora.ReviewImpl;
import org.idiginfo.docsvc.jpa.citagora.TagImpl;
import org.idiginfo.docsvc.jpa.citagora.TestSpring;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class CalServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ApplicationContext ac = null;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		super.init();
	}
	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
//		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		TestSpring testSpring = (TestSpring) ac.getBean("testSpring");
		if(!testSpring.modify(request)){
			testSpring.createContainer(request);
			response.getWriter().print("<h1>Added Reference Successfully!!</h1>");
		}
		else
		response.getWriter().print("<h1>Modified Reference with URI: "+request.getParameter("uri")+"</h1>");
//		testSpring.read();
		response.getWriter().close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
