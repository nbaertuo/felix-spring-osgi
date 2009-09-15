

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


import org.apache.jasper.servlet.JspServlet;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.DefaultServlet;
import org.mortbay.jetty.servlet.FilterHolder;
import org.mortbay.jetty.servlet.ServletHolder;
import org.springframework.js.resource.ResourceServlet;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;


/**
 * @author ertuo
 */

public class JettyWebStarter {


	/**
	 * @throws FileNotFoundException 
	 * @throws URISyntaxException 
	 */
	public void start() throws FileNotFoundException, URISyntaxException {
		Server server = new Server();
		Connector connector = new SocketConnector(); 
		connector.setPort(80); 
		server.addConnector(connector); 

        Context root = new Context(server, "/", Context.SESSIONS);
        
        ContextLoaderListener listener = new ContextLoaderListener();
        
        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("contextConfigLocation", "/WEB-INF/config/web-application-config.xml");
        root.setInitParams(initParams);
        /**servlet context*/
        root.setResourceBase("src/main/webapp");
        //root.addEventListener(listener);
        
        ServletHolder holder=new ServletHolder(new ResourceServlet());
        root.addServlet(holder, "/resources/*");
        
        /**spring control*/
        holder = new ServletHolder(new DispatcherServlet());
        holder.setInitParameter("contextConfigLocation", "/WEB-INF/config/web-application-config.xml");
        root.addServlet(holder, "/spring/*");
        
        
        holder=new ServletHolder(new JspServlet());
        root.addServlet(holder, "*.jsp");
        
        
        /**html*/
        holder=new ServletHolder(new DefaultServlet());
        root.addServlet(holder, "*.html");
        //root.addServlet(holder, "*.css");
        
        
        root.setWelcomeFiles(new String[]{"index.html"});
        
        //postStart(root);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	private void postStart(Context root){
		FilterHolder filterHolder=new FilterHolder(DelegatingFilterProxy.class);
		filterHolder.setName("springSecurityFilterChain");
		//root.addFilter(filterHolder, "/*", org.mortbay.jetty.Handler.DEFAULT);
		 
	}
	 
}
