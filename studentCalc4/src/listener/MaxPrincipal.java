package listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MaxPrincipal
 *
 */
@WebListener
public class MaxPrincipal implements HttpSessionAttributeListener {
	private static double maxPrincipal = 0;
    /**
     * Default constructor. 
     */
    public MaxPrincipal() {
        // TODO Auto-generated constructor stub
    	maxPrincipal = 0;
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	//System.out.println("m add: " + arg0.getName() + " : " + arg0.getValue());
    	getAttribute(arg0);
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	//System.out.println("m replace: " + arg0.getName() + " : " + arg0.getValue());
    	getAttribute(arg0);
    }
	
    public void getAttribute(HttpSessionBindingEvent arg0)
    {
    	double tempMaxPrincipal;
    	
    	if(arg0.getName().equals("principal"))
    	{
    		tempMaxPrincipal = Double.parseDouble(arg0.getValue().toString());
    		if(tempMaxPrincipal > maxPrincipal)
    		{
    			maxPrincipal = tempMaxPrincipal;
    		}
    	}
    }
    
    public static double maxPrincipal()
    {
    	return maxPrincipal;
    }
}
