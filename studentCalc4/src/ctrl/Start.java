package ctrl;



import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Loan;

/**
 * Servlet implementation class Start
 */
@WebServlet({"/Start", "/Startup", "/Startup/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String gi = "graceInterest";
	private static final String mp = "monthlyPayments";
	private Loan l;
	
	String sp = "/UI.jspx";
	String rp = "/Result.jspx";
    
	String error;
	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();     
    }
    
    
    
    public void init() throws ServletException {
    	l = new Loan();
    	this.getServletContext().setAttribute("model", l);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String legend = this.getServletContext().getInitParameter("legend");
        this.getServletContext().setAttribute("legend", legend);
	    
	    if(request.getParameter("submit") == null && request.getParameter("ajax") == null)
		{
			request.getRequestDispatcher(sp).forward(request, response);
			
		}
		else
		{	
			String principal, period, interest, fixedinterest, gracePeriod, grace;
			double graceInterest = 0, monthlyPayments = 0;
			error = "";
			
		
			
			principal = request.getParameter("principal");
			period = request.getParameter("period");
	        interest = request.getParameter("interest");
	        grace = request.getParameter("grace");
	        fixedinterest = this.getServletContext().getInitParameter("fixedInterest");
	        gracePeriod = this.getServletContext().getInitParameter("gracePeriod");
	        
	        
	        try 
	        {
	        	if(grace != null)
	        	{
	        		graceInterest = l.computeGraceInterest(principal, gracePeriod, interest, fixedinterest);
	        	}
	        	monthlyPayments = l.computePayment(principal, period, interest, grace, gracePeriod, fixedinterest);
	        }
	        catch(NumberFormatException e)
	        {
	        	if(principal.isEmpty() == true)
	        	{
	        		error = error + "Eneter Principal amount ";
	        	}
	        	if(period.isEmpty() == true)
	        	{
	        		error = error + "Eneter period amount ";
	        	}
	        	if(interest.isEmpty() == true)
	        	{
	        		error = error + "Eneter interest amount";
	        	}	        	
	        }
	        catch(Exception e)
	        {
	        	error = e.getMessage();
	        } 
	        
	        request.getSession().setAttribute("principal", principal);
	        request.getSession().setAttribute("interest", interest);
	        request.getSession().setAttribute("period", period);
	        request.getSession().setAttribute("grace", request.getParameter("grace"));
	        request.getSession().setAttribute("error", error);
	        
	        this.getServletContext().setAttribute("principal", principal);
	        this.getServletContext().setAttribute("interest", interest);
	        this.getServletContext().setAttribute("period", period);
	        this.getServletContext().setAttribute("grace", request.getParameter("grace"));
	        this.getServletContext().setAttribute("error", error);	        
	        
	        
	        DecimalFormat df = new DecimalFormat("#.##");	        
	        
	        
	        String ginterst = df.format(graceInterest);        	
	        String mpayments = df.format(monthlyPayments);
	        		
	       
	        this.getServletContext().setAttribute(gi, df.format(graceInterest));
	        this.getServletContext().setAttribute(mp, df.format(monthlyPayments));
	        
	        request.getSession().removeAttribute("principal");
	        request.getSession().removeAttribute("interest");
	        request.getSession().removeAttribute("period");
	        request.getSession().removeAttribute("grace");
	        request.getSession().removeAttribute("error");
	        
	        if(error != "")
	        {
	        	request.getRequestDispatcher(sp).forward(request, response);
	        }
	        else
	        { 
	        	if(request.getParameter("ajax") != null)
	        	{
	    	       	response.getWriter().append(ginterst + "," + mpayments );	    	        
	        	}
	        	else
	        	{
	        		request.getRequestDispatcher(rp).forward(request, response);	     
	        	}	        	
	        }
	        
	        
		}
	    
	}
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
