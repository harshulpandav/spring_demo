package org.idiginfo.docsvc.rest;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
//import javax.servlet.*;

import org.idiginfo.docsvc.jpa.citagora.TransactionService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Class to implement Rest service for various Collection types
 * 
 * @author griccardi
 * 
 */

@Path("/rest")
@Produces(MediaType.APPLICATION_XML)
public class RestService {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	private static final long serialVersionUID = 1L;
	public ApplicationContext ac = null;
	
	@PostConstruct
	void postConstruct(@Context ServletContext servletContext) {
		ac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//		ControllerFactory.init(persistence, logFile);
	}



	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response get(@QueryParam("collection") String collection, @Context ServletContext servletContext) {
		if(ac==null)
		ac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		MultivaluedMap<String, String> queryParams = uriInfo
				.getQueryParameters();
		String message = null;
		TransactionService transactionService = (TransactionService) ac.getBean("transactionService");
		if(!transactionService.modify(queryParams)){
			transactionService.createContainer(queryParams);
			message ="<h1>Added Reference Successfully!!</h1>";
		}
		else
			message ="<h1>Modified Reference with URI: "+queryParams.getFirst("uri")+"</h1>";
//		testSpring.read();
//		response.getWriter().close();
		// return result.body as a Response object;
		// creating and returning a Response object allows the charset to be
		// specified
		return Response
				.status(200)
				.entity(message).build();
	}
}
