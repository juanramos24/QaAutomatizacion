package com.vunic.qaselenium.testSE;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.PreparedStatement;
import java.text.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.*;

import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mysql.jdbc.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.vunic.core.FactorySvc;
import com.vunic.core.datos.DatosService;
import com.vunic.core.datos.NoSqlService;
import com.vunic.qaselenium.datos.impl.MongoOverDAO;
import com.vunic.qaselenium.dto.ControlEjecucionCalculoDTO;
import com.vunic.qaselenium.dto.ControlEjecucionCalculoSegmentadoDTO;
import com.vunic.qaselenium.dto.MongoOverDTO;
import com.vunic.qaselenium.dto.OverDTO;
import com.vunic.qaselenium.dto.SuggestionDTO;
import com.vunic.qaselenium.mgr.TravelClubMgr;

import org.openqa.selenium.firefox.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class FlujoCalculoTravelClub 
{

	// Constantes usadas en la clase Test
	private static final String HOST_SEL = "selenium.host";
	private static final String PORT_SEL = "selenium.port";
	private static final String URL_SEL =	"http://%s:%s/wd/hub";
	private static final int TIMEOUT_SECONDS = 10;

	// Variables  usadas en la clase Test
	private WebDriver driver;
	private String urlSel;
	private String hostSel;
	private String puertoSel;
	private String baseUrl = "";
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	
	// Variables instancias Mgr
	TravelClubMgr mongoOverMgr = new TravelClubMgr();
	TravelClubMgr mongoSuggestionMgr = new TravelClubMgr();
	TravelClubMgr obtenerDatosPruebaMgr = new TravelClubMgr();
	

    /**
     * Runs the test case.
     */
      public static void main(String args[]) {
            //junit.textui.TestRunner.run(suite());
            try {
                  //junit.textui.TestRunner.run(new FlujoCalculoTravelClub().suite());
                  new FlujoCalculoTravelClub().testFlujoPromocion();

            } catch (Exception e) {
            	FactorySvc.Log(FlujoCalculoTravelClub.class).Info("Error al ejecutar el test!!",e);
            }
      }

	/**
	 * Constructor publico por defecto
	 */
	public FlujoCalculoTravelClub() 
	{
		// Obtener valores configuración instancia Selenium
		hostSel = FactorySvc.Prop().getProperty(HOST_SEL);
		puertoSel = FactorySvc.Prop().getProperty(PORT_SEL);
		urlSel =  String.format(URL_SEL,hostSel,puertoSel);
	}

	@Before
	public void setUp() throws Exception 
	{

		
	}

	@Test
	public void testFlujoPromocion() throws Exception 
	{
		List<ControlEjecucionCalculoSegmentadoDTO> lstCecsDTO = null;
		FactorySvc.Log(this.getClass()).Info("Inicio - Conexión con servidor Selenium: " + urlSel);

		// Generar datos de entrada para el Caso de Prueba y ejecutar Browser
		lstCecsDTO = GenerarDatosPrueba();
		if (lstCecsDTO != null && lstCecsDTO.size()>0 )
		{
			// Ejecutar Broser para cada registro de los datos generados
			WebDriver driver = new RemoteWebDriver(new URL(urlSel), DesiredCapabilities.chrome());
			
			//Inicio prueba en browser
			browserFlujoPromocion(driver,lstCecsDTO);
			//Fin prueba en browser
			
		}
	}

	public List<ControlEjecucionCalculoSegmentadoDTO> GenerarDatosPrueba() throws Exception 
	{

		int cont_main = 1;
		List<ControlEjecucionCalculoSegmentadoDTO> lstCecsDTO = null;

		try 
		{

			boolean delBD = mongoOverMgr.deleteFullBD();


			if (delBD)
			{
				FactorySvc.Log(getClass()).Info("BD MySql Borrada OK");

				// Obtener lista coleccion BD Mongo Over
				List<OverDTO> lstOver = mongoOverMgr.obtenerListaOver();

				// Insertar promociones 01
				mongoOverMgr.insertOverBD(lstOver);

				/***************************************************************************/

				// Obtener lista coleccion BD Mongo Suggestion
				List<SuggestionDTO> lstSuggestion = mongoSuggestionMgr.obtenerListaSuggestion();

				// Insertar promociones 02
				mongoSuggestionMgr.insertSuggestionBD(lstSuggestion);

				/***************************************************************************/

				// Obtener lista coleccion BD Mongo Suggestion
				List<ControlEjecucionCalculoDTO> lstCec = obtenerDatosPruebaMgr.obtenerDatosPrueba();

				// Insertar promociones 02
				mongoSuggestionMgr.insertControlEjecucionCalculoBD(lstCec);

				/***************************************************************************/

				// Obtener el grupo id promociones
				List<ControlEjecucionCalculoDTO> lstGrupoId= obtenerDatosPruebaMgr.obtenerGrupoIDPromocion();


				for (ControlEjecucionCalculoDTO controlEjecucionCalculoDTO : lstGrupoId)
				{

					List<ControlEjecucionCalculoDTO> lstSeg = obtenerDatosPruebaMgr.obtenerPromocionSegmento(controlEjecucionCalculoDTO.getId());
					mongoSuggestionMgr.insertControlEjecucionCalculoSegmentadoBD(lstSeg);
				}
				/***************************************************************************/

				lstCecsDTO = mongoSuggestionMgr.obtenerDatosEjecutar();

			}
		} 
		catch (Exception e) 
		{
			FactorySvc.Log(this.getClass()).Info("Error al obtener los datos de entrada para las pruebas");
		}


		return lstCecsDTO;
	}	
	
	public void browserFlujoPromocion (WebDriver driver,List<ControlEjecucionCalculoSegmentadoDTO> lstCecsDTO) throws Exception 
	{
		for (ControlEjecucionCalculoSegmentadoDTO itemDTO : lstCecsDTO) 
		{
			try
			{
				//String extentReportFile = System.getProperty("user.dir")+ "\\extentReportFile.html";
				//String extentReportImage = System.getProperty("user.dir")+ "\\extentReportImage.png";
				
				String extentReportFile = "C:"+ "\\extentReportFile.html";
				String extentReportImage = "C:"+ "\\extentReportImage.png";
				
				// Create object of extent report and specify the report file path.
				ExtentReports extent = new ExtentReports(extentReportFile, false);
		 
				// Start the test using the ExtentTest class object.
				ExtentTest extentTest = extent.startTest("Primera Prueba","Verify WebSite Title");
				
				int tiempo = 5000;

				//driver.get(baseUrl + "http://travelclub-hypertext-test.herokuapp.com/promociones/american");
				driver.get(baseUrl + "http://travelclub-hypertext-test.herokuapp.com/");
								
				Thread.sleep(5000);
				
				extentTest.log(LogStatus.INFO, "Browser Launched");
				
				// TRAVEL CLUB - Maximize Window
			    driver.manage().window().maximize();
			    
			    extentTest.log(LogStatus.INFO, "Navigated to www.travelclub.cl");
			    
			    JavascriptExecutor javascript = (JavascriptExecutor)driver;	
				//**************************************************************************************************************//

				WebElement option = driver.findElement(By.xpath(".//*[@id='select2-origin-container']"));
				option.click();

				Thread.sleep(tiempo);

				driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(itemDTO.getOrigin());
				Thread.sleep(tiempo);


				driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
				Thread.sleep(tiempo);

				WebElement option_1 = driver.findElement(By.xpath(".//*[@id='select2-destiny-container']"));
				option_1.click();
				Thread.sleep(tiempo);

				driver.findElement(By.xpath("html/body/span/span/span[1]/input")).clear();

				driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(itemDTO.getDestino());
				Thread.sleep(tiempo);

				driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
				Thread.sleep(tiempo);

				//**************************************************************************************************************//
				// IF LA FECHA ACTUAL SE ENCUENTRA EN EL RANGO DE LA FECHA DE COMPRA, ENTONCES ES OK SINO FALLIDO //

				// --------------------------------------------- FECHA PARTIDA ----------------------------------------------- //

				List<WebElement> inputs = driver.findElements(By.id("startDate"));

				for (WebElement input : inputs) 
				{
					((JavascriptExecutor) driver).executeScript ("arguments[0].removeAttribute('readonly','readonly')",input);
				}

				//		    WebElement option_3 = driver.findElement(By.id("startDate"));
				//		    option_3.click();

				// FALTARIA DAR FORMATO DE STRING TO DATE
				
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    Date travelDateFrom_1 = sdf.parse(itemDTO.getFecha_desde_1());
			    SimpleDateFormat fecha1 = new SimpleDateFormat("dd-MM-yyyy");
			    
			    SimpleDateFormat sdf_3 = new SimpleDateFormat("yyyy-MM-dd");
			    Date travelDateTo_1 = sdf_3.parse(itemDTO.getFecha_hasta_1());
			    SimpleDateFormat fecha2 = new SimpleDateFormat("dd-MM-yyyy");
			    
			    SimpleDateFormat intento2A = new SimpleDateFormat("yyyy-MM-dd");
			    Date travelDateFrom_2 = intento2A.parse(itemDTO.getFecha_desde_2());
			    SimpleDateFormat fecha3 = new SimpleDateFormat("dd-MM-yyyy");
			    
			    SimpleDateFormat intento2B = new SimpleDateFormat("yyyy-MM-dd");
			    Date travelDateTo_2 = intento2B.parse(itemDTO.getFecha_hasta_2());
			    SimpleDateFormat fecha4 = new SimpleDateFormat("dd-MM-yyyy");
			    
			    SimpleDateFormat intento3A = new SimpleDateFormat("yyyy-MM-dd");
			    Date travelDateFrom_3 = intento3A.parse(itemDTO.getFecha_desde_3());
			    SimpleDateFormat fecha5 = new SimpleDateFormat("dd-MM-yyyy");
			    
			    SimpleDateFormat intento3B = new SimpleDateFormat("yyyy-MM-dd");
			    Date travelDateTo_3 = intento3B.parse(itemDTO.getFecha_hasta_3());
			    SimpleDateFormat fecha6 = new SimpleDateFormat("dd-MM-yyyy");
				

				Calendar calen = Calendar.getInstance();
				calen.setTime(travelDateFrom_1);
				calen.add(Calendar.DATE, 5);
				Date travelDateTo_f1 = calen.getTime();


				if( travelDateTo_1.before(travelDateTo_f1))
				{
					travelDateTo_f1 = travelDateTo_1;	
				}

				System.out.println ("FECHA DESDE *************************************************************************: "+itemDTO.getFecha_desde_1());
			    System.out.println ("FECHA HASTA *************************************************************************: "+itemDTO.getFecha_hasta_1());
			    System.out.println (" ");
			    
				driver.findElement(By.id("startDate")).sendKeys(fecha1.format(travelDateFrom_1));
				Thread.sleep(tiempo);

				driver.findElement(By.id("startDate")).sendKeys(Keys.TAB);
				Thread.sleep(tiempo);
				
				// ------------------------------------- FECHA PARTIDA -------------------------------------- //

				List<WebElement> inputs_2 = driver.findElements(By.id("endDate"));

				for (WebElement input : inputs_2) 
				{
					((JavascriptExecutor) driver).executeScript ("arguments[0].removeAttribute('readonly','readonly')",input);
				}

				driver.findElement(By.id("endDate")).sendKeys(fecha1.format(travelDateTo_1));
				Thread.sleep(tiempo);

				driver.findElement(By.id("endDate")).sendKeys(Keys.TAB);
				Thread.sleep(tiempo);


				// ------------------------------------- FECHA REGRESO -------------------------------------- //

				driver.findElement(By.xpath("//*[@id='flightSearchForm']/div[9]/input")).click();
				Thread.sleep(tiempo);

				//**************************************************************************************************************//

				// IF SI ENCUENTRA EL MENSAJE, ENTONCES QUE CAMBIE LOS VALORES DEL MENU 3 VECES Y PINTE FALLIDO
			    
			    String result_id_error = driver.findElement(By.id("errorMessage")).getText();
			        
			    boolean activar = false;
			    
			    //*********************Programación del Segundo Reintento*******************************************************//
			    
			    if (!result_id_error.isEmpty())
			    {
			    	
			    	Calendar calen2 = Calendar.getInstance();
				    calen2.setTime(travelDateFrom_2);
				    calen2.add(Calendar.DATE, 5);
				    Date travelDateTo_f2 = calen2.getTime();
				   
				    
				    if(travelDateTo_2.before(travelDateTo_f2))
				    {
				    	travelDateTo_f2 = travelDateTo_2;	
				    }
				    
				    System.out.println ("FECHA DESDE 2*************************************************************************: "+itemDTO.getFecha_desde_2());
				    System.out.println ("FECHA HASTA 2*************************************************************************: "+itemDTO.getFecha_hasta_2());
				    System.out.println (" ");
				    
				    driver.findElement(By.id("startDate")).clear();
				    driver.findElement(By.id("startDate")).sendKeys(fecha3.format(travelDateFrom_2));
				    Thread.sleep(tiempo);
				    
				    driver.findElement(By.id("startDate")).sendKeys(Keys.TAB);
				    Thread.sleep(tiempo);
				    
				    
				    // ------------------------------------- FECHA PARTIDA -------------------------------------- //
				    		    
				    List<WebElement> inputs_3 = driver.findElements(By.id("endDate"));

				    for (WebElement input : inputs_3) 
				    {
				        ((JavascriptExecutor) driver).executeScript ("arguments[0].removeAttribute('readonly','readonly')",input);
				    }
				    
				    driver.findElement(By.id("endDate")).clear();
				    driver.findElement(By.id("endDate")).sendKeys(fecha4.format(travelDateTo_2));
				    Thread.sleep(tiempo);
				    
				    driver.findElement(By.id("endDate")).sendKeys(Keys.TAB);
				    Thread.sleep(tiempo);
				    
					// ------------------------------------- FECHA REGRESO -------------------------------------- //
				    								

				    driver.findElement(By.xpath("//*[@id='flightSearchForm']/div[9]/input")).click();
				    Thread.sleep(10000);
				    
				    //**************************************Programación del Tercer Reintento***********************************//
				    
				    if (!result_id_error.isEmpty())
				    {
				    	
				    	Calendar calen3 = Calendar.getInstance();
					    calen3.setTime(travelDateFrom_3);
					    calen3.add(Calendar.DATE, 5);
					    Date travelDateTo_f3 = calen3.getTime();
					   
					    
					    if( travelDateTo_3.before(travelDateTo_f3))
					    {
					    	travelDateTo_f3 = travelDateTo_3;	
					    }
					    
					    System.out.println ("FECHA DESDE 3*************************************************************************: "+itemDTO.getFecha_desde_3());
					    System.out.println ("FECHA HASTA 3*************************************************************************: "+itemDTO.getFecha_hasta_3());
					    System.out.println (" ");
					    
					    driver.findElement(By.id("startDate")).clear();
					    driver.findElement(By.id("startDate")).sendKeys(fecha5.format(travelDateFrom_3));
					    Thread.sleep(tiempo);
					    
					    driver.findElement(By.id("startDate")).sendKeys(Keys.TAB);
					    Thread.sleep(tiempo);
					    
					    //System.out.println ("FECHA FORMATEADA 1 ******************************************************************: "+travelDateFrom_3);
					    // ------------------------------------- FECHA PARTIDA -------------------------------------- //
					    		    
					    List<WebElement> inputs_4 = driver.findElements(By.id("endDate"));

					    for (WebElement input : inputs_4) 
					    {
					        ((JavascriptExecutor) driver).executeScript ("arguments[0].removeAttribute('readonly','readonly')",input);
					    }
					       
					    
					    driver.findElement(By.id("endDate")).clear();
					    driver.findElement(By.id("endDate")).sendKeys(fecha6.format(travelDateTo_3));
					    Thread.sleep(tiempo);
					    
					    driver.findElement(By.id("endDate")).sendKeys(Keys.TAB);
					    Thread.sleep(tiempo);

						// ------------------------------------- FECHA REGRESO -------------------------------------- //

					    driver.findElement(By.xpath("//*[@id='flightSearchForm']/div[9]/input")).click();
					    Thread.sleep(10000);

				    	
				    } 
				    activar = true;
			    }
			    	
			    	System.out.println("SIN ERROR");
			    	System.out.println(" ");
			    	Thread.sleep(tiempo);
			    	
					if(!driver.findElements(By.xpath(".//*[@id='bigImgPromoLi']/img")).isEmpty())
					{
						System.out.println("LA PROMOCIÓN SE ENCUENTRA CON EL RESPECTIVO BANNER");
						System.out.println(" ");
						
						
					}
					else
					{
						System.out.println("LA PROMOCIÓN NO SE ENCUENTRA CON EL RESPECTIVO BANNER");
						System.out.println(" ");
						
				
					}
			    	
			    	if (activar == false)
			    	{									
			    		
			    		//si consigue promoción
			    		if (driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[3]")).getText().isEmpty())
			    		{
			    			
			    				// SIMBOLO CURRENCY	
			    			  	String result_tipo_moneda = driver.findElement(By.xpath(".//*[@id='fligthsData']/div[2]/form/div[2]/span[4]/span[1]")).getText();
					    	    Thread.sleep(tiempo);
					    	    
					    	    System.out.println ("SELECCIÓN MONEDA  *******************: "+ result_tipo_moneda);
					    	    
					    	    String precioTachado = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[3]")).getText();
					    	
					    	    System.out.println("Precio Tachado***********************: " + precioTachado);
					    	    
					    	    String precioNormal = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[4]/span[3]")).getText();
					    	    Thread.sleep(tiempo);
					    	    System.out.println("Precio Descuento*********************: " + precioNormal);
					    	    
					    	    
					    	    String precioPasajero = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[1]/span[3]")).getText();
					    	    Thread.sleep(tiempo);
					    	    System.out.println("Precio Pasajero**********************: " + precioPasajero);
					    	    
					    	    
					    	    String tasaImpu = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[2]/span[3]")).getText();
					    	    Thread.sleep(tiempo);
					    	    System.out.println("Impuestos y Tasas********************: " + tasaImpu);
					    	    
					    	    String gestAgen = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[3]/span[3]")).getText();
					    	    Thread.sleep(tiempo);
					    	    System.out.println("Gestión de Agencias******************: " + gestAgen);
					    	    
					    	    											
					    	    String total1 = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[4]/span[3]")).getText();
					    	    Thread.sleep(tiempo);
					    	    System.out.println("Total********************************: " + total1);
					    	    System.out.println(" ");
					    	    
			    			
			    		}
			    		else
			    		{
			    			
			    			//sino consigue promociones
			    			String result_tipo_moneda = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[1]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println ("SELECCIÓN MONEDA  *******************: "+ result_tipo_moneda);
				    	    												  
				    	    String precioNormal = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[3]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println("Precio Descuento*********************: " + precioNormal);
			    			
				    	    String precioPasajero = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[1]/span[3]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println("Precio Pasajero**********************: " + precioPasajero);
			    			
				    	    String tasaImpu = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[2]/span[3]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println("Impuestos y Tasas********************: " + tasaImpu);
				    	    											
				    	  
			    	    	String gestAgen = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[3]/span[3]")).getText();
			    	    	Thread.sleep(tiempo);
				    	    System.out.println("Gestión de Agencias******************: " + gestAgen);
				    	 
				    	    
				    	    String total1 = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[4]/span[3]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println("Total********************************: " + total1);
				    	    System.out.println(" ");
				    	    
			    		}
				    	  //********************Inicio de recorrido de Filtros *******************//	
				    		
					    	   
				    	    javascript.executeScript("scroll(0, 300);");
				    		Thread.sleep(tiempo);
				    		
				    		driver.findElement(By.xpath("//*[@id='filterResultForm']/div[1]/div[1]/h3")).click();
				    		Thread.sleep(tiempo);
				    		
				    		// *******Filtro Moneda*****//
				    		
				    		driver.findElement(By.xpath("//*[@id='toUsd']/label/input")).click();
				    		Thread.sleep(tiempo);
				    		
				    		javascript.executeScript("scroll(0, -1200);");
				    		Thread.sleep(tiempo);
				    	    
				    	    
				    		// *******Filtro Moneda*****// 
				    		
			    			//Captura de datos de cantidades en dolares//
			    																		
		    				// SIMBOLO CURRENCY								
		    			  	String result_tipo_moneda_d = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[1]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println ("SELECCIÓN MONEDA  *******************: "+ result_tipo_moneda_d);
				    	    
				    	    													
				    	    String precioNormal_d = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[2]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println("Precio Descuento*********************: " + precioNormal_d);
				    	    
				    	    													
				    	    String precioPasajero_d = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[1]/span[2]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println("Precio Pasajero**********************: " + precioPasajero_d);
				    	    
				    	    												
				    	    String tasaImpu_d = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[2]/span[2]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println("Impuestos y Tasas********************: " + tasaImpu_d);
				    	                                    
				    	  
				    	    String gestAgen_d = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[3]/span[2]")).getText();
				    	    Thread.sleep(tiempo);
					    	System.out.println("Gestión de Agencias******************: " + gestAgen_d);
				    	    
				    	    											
				    	    String total_d = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[4]/span[2]")).getText();
				    	    Thread.sleep(tiempo);
				    	    System.out.println("Total********************************: " + total_d);
				    	    System.out.println(" ");
				    	    
				    	    javascript.executeScript("scroll(0, 300);");
				    		Thread.sleep(tiempo);
				    	    
				    		
				    		//************************ Filtro Escalas ***************//
				    		
				    		driver.findElement(By.xpath("//*[@id='filterResultForm']/div[2]/div[1]/h3/i")).click();
				    		Thread.sleep(tiempo);

				    		//***Captura de Valores**//
				    		
				    		//numero de todas las escalas
				    		driver.findElement(By.xpath("//*[@id='allStops']")).getText();
				    		System.out.println("Total de todas las Escalas: " + driver.findElement(By.xpath("//*[@id='allStops']")).getText());
				    		
				    		//numero de Directo
				    		String escalaDirecto = driver.findElement(By.xpath("//*[@id='directStop']")).getText();
				    		System.out.println("Total de Vuelos Directos: " + escalaDirecto);
				    		
				    		//numero de registros una escala
				    		String unaEscala = driver.findElement(By.xpath("//*[@id='oneStop']")).getText();
				    		System.out.println("Total de Vuelos con 1 Escala: " + unaEscala);
				    		
				    		
				    		//numero de registros de 2 o mas escalas
				    		String dosEscalas = driver.findElement(By.xpath("//*[@id='twoStop']")).getText();
				    		System.out.println("Total de Vuelos con 2 o mas Escalas: " + dosEscalas);
				    		System.out.println(" ");
				    		//***Fin Captura de Valores**//
				    		
				    		
				    		//Directo
				    		driver.findElement(By.xpath("//*[@id='filterResultForm']/div[2]/div[2]/div[2]/label/input")).click();
				    		Thread.sleep(tiempo);
				    		
				    		javascript.executeScript("scroll(0, -1200);");
				    		Thread.sleep(tiempo);
				    		
				    		int numDirecto = Integer.parseInt(escalaDirecto);
				    		
				    		if (numDirecto > 0)
				    		{
				    										
				    			String varDirecto = driver.findElement(By.xpath("//li[2]/ul/li[3]/a/span[1]")).getText();
				    			Thread.sleep(tiempo);
				    			String varDirecto2 = driver.findElement(By.xpath("//li[4]/ul/li[3]/a/span[1]")).getText();
				    			Thread.sleep(tiempo);
				    			
				    			if ( varDirecto.equals("Directo") && varDirecto2.equals("Directo"))
				    			{

				    				System.out.println("Los itinerarios Directos que se muestran, son Correctos");
				    				
				    			}
				    			else
				    			{
				    				System.out.println("Los itinerarios Directos que se muestran, son Incorrectos");
				    			}
				    		}
				    		else
				    		{
				    			String mensajeSinResult = driver.findElement(By.xpath("//*[@id='errorMessageBottom']/div")).getText();
				    			System.out.println("No se encontraron itinerarios Directos");
				    			System.out.println(" ");
				    			
				    		}
				    		
				    		javascript.executeScript("scroll(0, 300);");
				    		Thread.sleep(tiempo);
				    		
				    		//1 Escala
				    		driver.findElement(By.xpath("//*[@id='filterResultForm']/div[2]/div[2]/div[3]/label/input")).click();
				    		Thread.sleep(tiempo);
				    		
				    		javascript.executeScript("scroll(0, -1200);");
				    		Thread.sleep(tiempo);
				    		
				    		
				    		int numUnaEscala = Integer.parseInt(unaEscala);
				    		
				    		if (numUnaEscala > 0)
				    		{
				    			
				    			String varUnaEscala = driver.findElement(By.xpath("//li[2]/ul/li[3]/a/span[1]")).getText();
				    			Thread.sleep(tiempo);
				    			String varUnaEscala2 = driver.findElement(By.xpath("//li[4]/ul/li[3]/a/span[1]")).getText();
				    			Thread.sleep(tiempo);
				    			
				    			if ( varUnaEscala.equals("1 escala")  &&  varUnaEscala2.equals("1 escala") )
				    			{
				    				
				    				System.out.println("Los itinerarios de 1 escala que se muestran, son Correctos");
				    				System.out.println(" ");
				    				
				    			}
				    			else
				    			{
				    			
				    				System.out.println("Los itinerarios de 1 escala que se muestran, no son Correctos");
				    				System.out.println(" ");
				    			}
				    		}
				    		else
				    		{
				    			
				    		String mensajeSinResult = driver.findElement(By.xpath("//*[@id='errorMessageBottom']/div")).getText();
				    		System.out.println("No se encontraron itinerarios con 1 escala");
				    		System.out.println(" ");
				    		
				    		}
				    		
				    		
				    		javascript.executeScript("scroll(0, 300);");
				    		Thread.sleep(tiempo);
				    		
				    		
				    		//2 o mas Escalas		     
				    		driver.findElement(By.xpath("//*[@id='filterResultForm']/div[2]/div[2]/div[4]/label/input")).click();
				    		Thread.sleep(tiempo);
				    		
				    		
				    		javascript.executeScript("scroll(0, -1200);");
				    		Thread.sleep(tiempo);
				    		

				    		int numDosOMasEscala = Integer.parseInt(dosEscalas);
				    		
				    		if (numDosOMasEscala > 0){
				    			
				    			
				    			String varDosEscala = driver.findElement(By.xpath("//li[2]/ul/li[3]/a/span[1]")).getText();
				    			Thread.sleep(tiempo);
				    			                  
				    			String varDosEscala2 = driver.findElement(By.xpath("//li[4]/ul/li[3]/a/span[1]")).getText();
				    			Thread.sleep(tiempo);
				    			
				    			if ( varDosEscala.equals("") && varDosEscala2.equals("") )
				    			{
				    				
				    				System.out.println("Los itinerarios de 2 o más escalas que se muestran, son Correctos");
				    				System.out.println(" ");
				    				
				    			}
				    			else
				    			{
				    				System.out.println("Los itinerarios de 2 escala que se muestran, no son Correctos");
				    				System.out.println(" ");
				    			}
				    		}
				    		else
				    		{
				    			String mensajeSinResult = driver.findElement(By.xpath("//*[@id='errorMessageBottom']/div")).getText();
					    		System.out.println("No se encontraron itinerarios con 2 escala");
					    		System.out.println(" ");
				    		}
				    		
				    		javascript.executeScript("scroll(0, 300);");
				    		Thread.sleep(tiempo);
				    		
				    		
				    		//Todas las Escalas
				    		driver.findElement(By.xpath("//*[@id='clearStopsFilter']")).click();
				    		Thread.sleep(tiempo);

				    		
				    		//Fin Clic Escala
				    		driver.findElement(By.xpath("//*[@id='filterResultForm']/div[2]/div[1]/h3/i")).click();
				    		Thread.sleep(tiempo);
				    	
				    		//************************ Fin Filtro Escalas ***************//
				    		
				    		
				    		//************************ Filtro Aerolíneas ***************//
				    		
				    		//Clic Aerolíneas 				
				    		driver.findElement(By.xpath("//*[@id='carriersFilter']/div[1]/h3/i")).click();
				    		Thread.sleep(tiempo);
				    		
				    		//Obtengo el nombre de la Aerolínea
				    		String nombreAerol = driver.findElement(By.xpath("//*[@id='filterResultForm']/div[3]/div[2]/div[2]/label/span")).getText();
				    		
				    		String nameAerol = driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[1]/ul/li[2]/ul/li[1]/label")).getText();
				    											   
				    		if (nombreAerol.equals("American Airlines") && itemDTO.getAirline().equals("AA")){
				    			
				    			System.out.println("Nombre de la Aerolínea Itinerario: " + nombreAerol + "Promoción: " + itemDTO.getAirline());
				    			System.out.println(" ");
				    			System.out.println("La Aerolínea corresponde con la establecida en la regla comercial");
				    			
				    		}
				    		else
				    		{
				    			if (nameAerol.equals("Delta") && itemDTO.getAirline().equals("DL"))
				    			{
					    			
					    			System.out.println("Nombre de la Aerolínea Itinerario: " + nombreAerol + "Promoción: " + itemDTO.getAirline());
					    			System.out.println(" ");
					    			System.out.println("La Aerolínea corresponde con la establecida en la regla comercial");
				    			}
				    			else
				    			{
				    				
				    				if (nombreAerol.equals("Latam") && itemDTO.getAirline().equals("LA"))
				    				{
						    			System.out.println("Nombre de la Aerolínea Itinerario: " + nombreAerol + "Promoción: " + itemDTO.getAirline());
						    			System.out.println("La Aerolínea corresponde con la establecida en la regla comercial");
						    			
				    				}
				    			}
				    		}
				    				    		
				    		javascript.executeScript("scroll(0, -1200);");
				    		Thread.sleep(tiempo);	
				    		
				    		javascript.executeScript("scroll(0, 300);");
				    		Thread.sleep(tiempo);
				    		
		 		
				    		//Clic cerrar pestaña Aerolínea
				    		driver.findElement(By.xpath("//*[@id='carriersFilter']/div[1]/h3/i")).click();
				    		Thread.sleep(tiempo);
				    		
				    		
				    	//*************************Fin de recorrido Filtros ************************//		
				    		
				    		
				    	//**********************Identificación del Pasajero************************//
				    		
				    		javascript.executeScript("scroll(0, -1200);");
				    		Thread.sleep(tiempo);
				    		
				    		driver.findElement(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/div/input[3]")).click();
				    		Thread.sleep(tiempo);
				    		
				    		javascript.executeScript("scroll(0, 300);");
						    Thread.sleep(tiempo);
						    
						    javascript.executeScript("scroll(0, 600);");
						    Thread.sleep(tiempo);
						    
						    javascript.executeScript("scroll(0, 1200);");
						    Thread.sleep(tiempo);
						     
						    driver.findElement(By.xpath("//label/input")).click();
						    Thread.sleep(tiempo);
						    
						    driver.findElement(By.xpath("//div[3]/div/div/input")).clear();
						    Thread.sleep(tiempo);

						    driver.findElement(By.xpath("//div[3]/div/div/input")).sendKeys("Armando");
						    Thread.sleep(tiempo);
						    
						    driver.findElement(By.xpath("//div[2]/input")).clear();
						    Thread.sleep(tiempo);

						    driver.findElement(By.xpath("//div[2]/input")).sendKeys("Figueredo");
						    Thread.sleep(tiempo);
			  
						    driver.findElement(By.id("selectDoc")).click();
						    Thread.sleep(tiempo);
						    
						    //driver.findElement(By.id("//*[@id='selectDoc']/option[1]")).click();
						    //Thread.sleep(tiempo);
						    
						    driver.findElement(By.id("rutNum0")).clear();
							
						    driver.findElement(By.id("rutNum0")).sendKeys("401134814");
						    Thread.sleep(tiempo);
						    
						    driver.findElement(By.xpath("//div[5]/div/input")).clear();

						    driver.findElement(By.xpath("//div[5]/div/input")).click();
						    
						    driver.findElement(By.xpath("//div[5]/div/input")).sendKeys("07/05/1983");
						    Thread.sleep(tiempo);
						    
						    driver.findElement(By.xpath("//div[2]/div/input")).clear();
						
						    driver.findElement(By.xpath("//div[2]/div/input")).sendKeys("contacto@valorunico.cl");
						    Thread.sleep(tiempo);
							
						    driver.findElement(By.xpath("//div[2]/div[2]/input")).clear();
							
						    driver.findElement(By.xpath("//div[2]/div[2]/input")).sendKeys("995894653");
						    Thread.sleep(tiempo);
							
						    javascript.executeScript("scroll(0, 1200);");
						    Thread.sleep(tiempo);
						    
						    driver.findElement(By.id("inlineCheckbox1")).click();
						    Thread.sleep(tiempo);
					
						    driver.findElement(By.id("btnContinue")).click();
						    Thread.sleep(tiempo);
					
				    		
				    	//**********************Fin Identificación del Pasajero************************//
				    		
						//****************************Inicio del Login ********************************//

						    driver.findElement(By.xpath("//*[@id='rutemp2222']")).sendKeys("1-9");
				    		//driver.findElement(By.id("rutemp2222")).sendKeys("1-9");
				    		Thread.sleep(tiempo);
				    		
				    		driver.findElement(By.xpath("//*[@id='pin222']")).sendKeys("123456");
				    		//driver.findElement(By.id("pin222")).sendKeys("123456");
				    		Thread.sleep(tiempo);
				    		
				    		driver.findElement(By.id("idIngresar")).click();
				    		Thread.sleep(tiempo);
				    		
				    	//****************************Inicio del Login ********************************//

				    		Thread.sleep(10000);
				    		
				    	
				    	//*******************Tus productos pendientes de pago ************************//
				    	
				    		javascript.executeScript("scroll(0, 600);");
				    		Thread.sleep(tiempo);
				    		
				    		driver.findElement(By.xpath("//*[@id='acceptTC']")).click();
				    		Thread.sleep(tiempo);
				    		
				    		driver.findElement(By.id("btnPagar")).click();
				    		Thread.sleep(tiempo);
				    		
			
				    		
				    	//******************* Ingresa los Dólares-Premio a canjear ************************//	
				    		
				    		
				    		
				    		
				    		
				    	//******************* Fin Ingresa los Dólares-Premio a canjear ************************//	
				    		
				    		
			    		 		
				    	//****************Fin Tus productos pendientes de pago ***********************//	
			    
			    	}else
				    {
				    	System.out.println("NO SE ENCONTRARON ITINERARIOS DISPONIBLES PARA LOS TRES (3) INTENTOS");
			
				    }
					System.out.println("SALI FLUJO - TRAVEL CLUB");
					Thread.sleep(tiempo);
				}
			
			catch (Exception e) 
			{
				FactorySvc.Log(this.getClass()).Info("Error al obtener los datos de entrada para las pruebas");
			}
		}// CIERRA FOR

		System.out.println("************************************Close**********************************************");
		driver.close();
	}
}
	

