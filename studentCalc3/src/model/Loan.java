package model;

public class Loan {

	public Loan()
	{
		
	}
	
	public double computePayment(String p, String a, String i, String g, String gp, String fi) throws Exception
	{
		double principal, period, interest, gracePeriod, fixedInterest, totalInterest, monthlyPayments = 0;
		
		principal = Double.parseDouble(p);
		period = Double.parseDouble(a);
		interest = Double.parseDouble(i);
		gracePeriod = Double.parseDouble(gp);
		fixedInterest = Double.parseDouble(fi);
		
		totalInterest = interest + fixedInterest;
		
		monthlyPayments = (((totalInterest/100) / 12) * principal) / (1 - (Math.pow(1 + (totalInterest/100) / 12, -period)) );
		
		if(principal < 0)
		{
			throw new Exception("Eneter positive Principal amount");
		}
		if(period < 0)
		{
			throw new Exception("Eneter positive period amount");
		}
		if(interest < 0)
		{
			throw new Exception("Eneter positive interest amount");
		}
		
		
		if(g != null)
		{
			monthlyPayments = monthlyPayments + (computeGraceInterest(p, gp, i, fi) / gracePeriod);
		}
		
		return monthlyPayments;
	}
	public double computeGraceInterest(String p, String gp, String i, String fi) throws Exception
	{
		double principal, gracePeriod, interest, fixedInterest, graceInterest;
		
		principal = Double.parseDouble(p);
		gracePeriod = Double.parseDouble(gp);
		interest = Double.parseDouble(i);
		fixedInterest = Double.parseDouble(fi);			
		
		graceInterest = principal * (((interest + fixedInterest) / 12) * 0.01) * gracePeriod;
		
		return graceInterest;
    }
	
}
