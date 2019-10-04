package ctrl;



import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Start
 */
@WebServlet({"/Start", "/Startup", "/Startup/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Hello,Got a GET request!");
		
		
		response.setContentType("text/plain");
		Writer resOut = response.getWriter();
		resOut.write("\nHello, World!\n");
		
		
		String clientIP = request.getRemoteAddr();
		//String clientIP = request.getLocalAddr();
		resOut.write("Client IP: " + clientIP + "\n");		
		int clientPort = request.getRemotePort();
		resOut.write("Client Port: " + clientPort + "\n\n");
		
		
		
		String httpProtocol = request.getProtocol();
		resOut.write("Http Protocol: " + httpProtocol + "\n");		
		String httpMethod = request.getMethod();
		resOut.write("Http Method: " + httpMethod + "\n\n");
		
		
		
		String clientQueryString = request.getQueryString();
		resOut.write("Client Query String: " + clientQueryString + "\n");
		String foo = request.getParameter("foo");
		resOut.write("Query Param foo = " + foo + "\n\n");
		
		
		
		String Url = request.getRequestURL().toString();
		resOut.write("Url: " + Url + "\n");
		String EmbedString = URLEncoder.encode(Url, "UTF-8");
		resOut.write("Embed String Url: " + EmbedString + "\n\n");
		
		
		
		String urL = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		
		String scheme = request.getScheme();
        String serverName = request.getServerName();
        int portNumber = request.getServerPort();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String query = request.getQueryString();

        resOut.write("Url: " + urL + "\n");
        resOut.write("Uri: " + uri + "\n");
        resOut.write("Scheme: " + scheme + "\n");
        resOut.write("Server Name: " + serverName + "\n");
        resOut.write("Port: " + portNumber + "\n");
        resOut.write("Context Path: " + contextPath + "\n");
        resOut.write("Servlet Path: " + servletPath + "\n");
        resOut.write("Path Info: " + pathInfo + "\n");
        resOut.write("Query: " + query + "\n\n");
        
        
        
        String url= this.getServletContext().getContextPath() +"/Start";
        //resOut.write("Url: " + url + "\n\n");
        
        if(request.getRequestURI().contains("/Startup/YorkBank"))
        {
        	response.sendRedirect(url);
        }
        
        
        
        String appName = (this.getServletContext().getInitParameter("appName"));
        resOut.write("appName: " + appName + "\n\n");
        
        //Fix values for Principal, Period and Interest
        
        //double p = Double.parseDouble(this.getServletContext().getInitParameter("p"));
        //resOut.write("Principal: " + p );
        //double n = Double.parseDouble(this.getServletContext().getInitParameter("n"));
        //resOut.write("\tPeriod: " + n );
        //double r = Double.parseDouble(this.getServletContext().getInitParameter("r"));
        //resOut.write("\tInterest: " + r + "\n\n");
        
        
        double principal, period, interest;
        
        resOut.write("---- Monthaly Payments ----\n");
               
        if(request.getParameter("principal") != null)
        {
        	principal = Double.parseDouble(request.getParameter("principal"));
            resOut.write("Based on Principal=" + principal );
        }
        else
        {
        	principal = Double.parseDouble(this.getServletContext().getInitParameter("principal"));
        	resOut.write("Based on Principal=" + principal );
        }
        
        if(request.getParameter("period") != null)
        {
        	period = Double.parseDouble(request.getParameter("period"));
            resOut.write("\tPeriod=" + period );
        }
        else
        {
        	period = Double.parseDouble(this.getServletContext().getInitParameter("period"));
        	resOut.write("\tPeriod=" + period );
        }
        
        if(request.getParameter("interest") != null)
        {
        	interest = Double.parseDouble(request.getParameter("interest"));
            resOut.write("\tInterest=" + interest + "\n");
        }
        else
        {
        	interest = Double.parseDouble(this.getServletContext().getInitParameter("interest"));
        	resOut.write("\tInterest=" + interest + "\n");
        }
        
        double osap = (((interest/100) / 12) * principal) / (1 - (Math.pow(1 + (interest/100) / 12, -period)) );
        
        DecimalFormat df = new DecimalFormat("#.#");
        
        resOut.write("Monthly Payments: " + df.format(osap) + "\n");
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
