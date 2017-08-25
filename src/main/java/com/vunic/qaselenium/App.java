package com.vunic.qaselenium;

import com.vunic.qaselenium.testSE.FlujoCalculoTravelClub;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
    	try 
    	{
        	new FlujoCalculoTravelClub().testFlujoPromocion();
		} 
    	catch (Exception e) 
    	{
			// TODO: handle exception
		}
    }
}
