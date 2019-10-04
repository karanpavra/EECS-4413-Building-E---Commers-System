package ctrl;



import java.io.IOException;
import java.io.Writer;
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
	private static final String gi = "graceInterest";
	private static final String mp = "monthlyPayments";
	
	String sp = "/UI.jspx";
	String rp = "/Result.jspx";
    
	

	
	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		double fixedInterest = Double.parseDouble(this.getServletContext().getInitParameter("fixedInterest"));
	    
	    double gracePeriod = Double.parseDouble(this.getServletContext().getInitParameter("gracePeriod"));
	    
	    if(request.getParameter("submit") == null)
		{
			request.getRequestDispatcher(sp).forward(request, response);
			
		}
		else
		{	
			double principal, period, interest, totalInterest, monthlyPayments = 0, graceInterest = 0;
			if(request.getParameter("principal").isEmpty() != true)
	        {
	        	principal = Double.parseDouble(request.getParameter("principal"));
	        }
	        else
	        {
	        	principal = Double.parseDouble(this.getServletContext().getInitParameter("principal"));
	        }
	        
	        if(request.getParameter("period").isEmpty() != true)
	        {
	        	period = Double.parseDouble(request.getParameter("period"));
	        }
	        else
	        {
	        	period = Double.parseDouble(this.getServletContext().getInitParameter("period"));
	        }
	        
	        if(request.getParameter("interest").isEmpty() != true)
	        {
	        	interest = Double.parseDouble(request.getParameter("interest"));
	        }
	        else
	        {
	        	interest = Double.parseDouble(this.getServletContext().getInitParameter("interest"));
	        }
			
	        totalInterest = interest +  Double.parseDouble(this.getServletContext().getInitParameter("fixedInterest"));
	        
	        monthlyPayments = (((totalInterest/100) / 12) * principal) / (1 - (Math.pow(1 + (totalInterest/100) / 12, -period)) );
	        
	        if(request.getParameter("gracePeriod") != null)
			{
	        	graceInterest = principal * (((interest + fixedInterest) / 12) * 0.01) * gracePeriod;	        	
	        	monthlyPayments = monthlyPayments + (graceInterest / gracePeriod);
			}
			
	        
	        
	        DecimalFormat df = new DecimalFormat("#.##");
	        
	        request.setAttribute(gi, df.format(graceInterest));
	        request.setAttribute(mp, df.format(monthlyPayments));
	        
	        request.getRequestDispatcher(rp).forward(request, response);
	        
		}
		
	}
	
	/*
	 * Q & A: 
	 */
	
	/*
	 * It is awkward to intermix HTML output and Java code because it can not show the distinguish 
	 * output from those two and it also violates the separation concept. 
	 */
	
	/*
	 * The mixing validation, payment computation and presentation make code not user friendly.
	 * It also hard to fine and fix the error in code.
	 */
	
	/*
	 * The length in Contest Length is the size of the body and in the request in headers the size of the body
	 * request has been GET.
	 */
	
	/*
	 * using POST vs GET
	 * Adv: parameter can not save in browser history, value hiding in URL, difficult to hack
	 * Dis: can not be bookmarked, no restrictions on data type, not use for fetching documents
	 * using same thing on server side reduce it disadvantages.
	 */
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
