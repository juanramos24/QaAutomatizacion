package com.vunic.qaselenium.testSE;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.vunic.core.FactorySvc;
import com.vunic.qaselenium.dto.ControlEjecucionCalculoDTO;
import com.vunic.qaselenium.dto.ControlEjecucionCalculoSegmentadoDTO;
import com.vunic.qaselenium.dto.OverDTO;
import com.vunic.qaselenium.dto.SuggestionDTO;
import com.vunic.qaselenium.mgr.TravelClubMgr;


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

				WebDriverWait wait = new WebDriverWait(driver, 30); 
				WebElement esperarElemento;
				
				//driver.get(baseUrl + "http://travelclub-hypertext-test.herokuapp.com/");
				driver.get(baseUrl + "http://travelclub-hypertext-test.herokuapp.com/promociones/american");
								
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
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
			    
				
				driver.findElement(By.id("startDate")).clear();
			    
			    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
			    esperarElemento.sendKeys(fecha1.format(travelDateFrom_1));
			    
			    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
			    esperarElemento.sendKeys(Keys.TAB);
				
				
				
				// ------------------------------------- FECHA PARTIDA -------------------------------------- //

				List<WebElement> inputs_2 = driver.findElements(By.id("endDate"));

				for (WebElement input : inputs_2) 
				{
					((JavascriptExecutor) driver).executeScript ("arguments[0].removeAttribute('readonly','readonly')",input);
				}
				driver.findElement(By.id("endDate")).clear();
			    
			    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));
			    esperarElemento.sendKeys(fecha2.format(travelDateTo_1));
			    
			    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));
			    esperarElemento.sendKeys(Keys.TAB);


				// ------------------------------------- FECHA REGRESO -------------------------------------- //

			    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='flightSearchForm']/div[9]/input")));
			    esperarElemento.click();

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
				    
				    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
				    esperarElemento.sendKeys(fecha3.format(travelDateFrom_2));

				    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
				    esperarElemento.sendKeys(Keys.TAB);
				    
				    
				    // ------------------------------------- FECHA PARTIDA -------------------------------------- //
				    		    
				    List<WebElement> inputs_3 = driver.findElements(By.id("endDate"));

				    for (WebElement input : inputs_3) 
				    {
				        ((JavascriptExecutor) driver).executeScript ("arguments[0].removeAttribute('readonly','readonly')",input);
				    }
				    
				    driver.findElement(By.id("endDate")).clear();
				    
				    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));
				    esperarElemento.sendKeys(fecha4.format(travelDateTo_2));
				    
				    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));
				    esperarElemento.sendKeys(Keys.TAB);
				    
					// ------------------------------------- FECHA REGRESO -------------------------------------- //
				    								

				    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='flightSearchForm']/div[9]/input")));
				    esperarElemento.click();
				    
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
					    
					    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
					    esperarElemento.sendKeys(fecha5.format(travelDateFrom_3));				    
					    
					    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
					    esperarElemento.sendKeys(Keys.TAB);
					    
					    
					    // ------------------------------------- FECHA PARTIDA -------------------------------------- //
					    		    
					    List<WebElement> inputs_4 = driver.findElements(By.id("endDate"));

					    for (WebElement input : inputs_4) 
					    {
					        ((JavascriptExecutor) driver).executeScript ("arguments[0].removeAttribute('readonly','readonly')",input);
					    }
					       
					    
					    driver.findElement(By.id("endDate")).clear();
					    
					    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));
					    esperarElemento.sendKeys(fecha6.format(travelDateTo_3));
					    
					    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));
					    esperarElemento.sendKeys(Keys.TAB);

						// ------------------------------------- FECHA REGRESO -------------------------------------- //

					    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='flightSearchForm']/div[9]/input")));
					    esperarElemento.click();

				    	
				    } 
				    activar = true;
			    }
			    	
			    	System.out.println("SIN ERROR");
			    	System.out.println(" ");
			    	
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
			    		
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[3]")));
			    		
			    		//si consigue promoción
			    		if (esperarElemento.getText().isEmpty())
			    		{
			    			
			    				// SIMBOLO CURRENCY	
			    				esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='fligthsData']/div[2]/form/div[2]/span[4]/span[1]")));
			    			  	String result_tipo_moneda =  esperarElemento.getText();
			    			  	
					    	    System.out.println ("SELECCIÓN MONEDA  *******************: "+ result_tipo_moneda);
					    	    
					    	    
					    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[3]")));
					    	    String precioTachado = esperarElemento.getText();
					    	    
					    	    System.out.println("Precio Tachado***********************: " + precioTachado);
					    	    
					    	    
					    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[4]/span[3]")));
					    	    String precioNormal = esperarElemento.getText();
					    	    
					    	    System.out.println("Precio Descuento*********************: " + precioNormal);
					    	    
					    	    
					    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[1]/span[3]")));
					    	    String precioPasajero = esperarElemento.getText();
					    	 
					    	    System.out.println("Precio Pasajero**********************: " + precioPasajero);
					    	    
					    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[2]/span[3]")));
					    	    String tasaImpu = esperarElemento.getText();
					    	    
					    	    System.out.println("Impuestos y Tasas********************: " + tasaImpu);
					    	    
					    	    
					    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[3]/span[3]")));
					    	    String gestAgen = esperarElemento.getText();
					    	    
					    	    System.out.println("Gestión de Agencias******************: " + gestAgen);
					    	    
					    	    					
					    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[4]/span[3]")));
					    	    String total1 = esperarElemento.getText();
					    	    
					    	    System.out.println("Total********************************: " + total1);
					    	    System.out.println(" ");
					    	    
					    	    
					    		float precioP = Float.parseFloat(precioPasajero);
					    		float tasasI = Float.parseFloat(tasaImpu);
					    		float gestA = Float.parseFloat(gestAgen);
					    		float totalTotal = Float.parseFloat(total1);
					    		float precioN = Float.parseFloat(precioNormal);
					    		
					    		float precioCalculado = precioP + tasasI + gestA;
					    		
					  	      // compares the two specified float values
					    		float f1 = precioCalculado;
					    		float f2 = totalTotal;
					    		float f3 = precioN;
					    		int retval = Float.compare(f1, f2);
					    		int retval2 =Float.compare(f2, f3);
							
					    		if(retval > 0) {
					    			System.out.println("f1 es mayor a f2");
					    		} else if(retval < 0) {
					    			System.out.println("f1 es menor a f2");
					    		} else {
					    			System.out.println("f1 es igual a f2");
					    		}
					    	    
					    		
					    		if(retval2 > 0) {
					    			System.out.println("f2 es mayor a f3");
					    		} else if(retval2 < 0) {
					    			System.out.println("f2 es menor a f3");
					    		} else {
					    			System.out.println("f2 es igual a f3");
					    		}
			    			
			    		}
			    		else
			    		{
			    			
			    			//sino consigue promociones
			    			esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[1]")));
				    	    String result_tipo_moneda = esperarElemento.getText();
			    			
				    	    System.out.println ("SELECCIÓN MONEDA  *******************: "+ result_tipo_moneda);
				    	    								
				    	    
				    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[3]")));
				    	    String precioNormal = esperarElemento.getText();
				    	    
				    	    System.out.println("Precio Descuento*********************: " + precioNormal);
				    	    
				    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[1]/span[3]")));
				    	    String precioPasajero = esperarElemento.getText();
				    	    
				    	    System.out.println("Precio Pasajero**********************: " + precioPasajero);
			    			
				    	    
				    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[2]/span[3]")));
				    	    String tasaImpu = esperarElemento.getText();
				    	   
				    	    System.out.println("Impuestos y Tasas********************: " + tasaImpu);
				    	    						
				    	    
				    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[3]/span[3]")));
				    	    String gestAgen = esperarElemento.getText();
				    	    
				    	    System.out.println("Gestión de Agencias******************: " + gestAgen);
				    	 
				    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[4]/span[3]")));
				    	    String total1 = esperarElemento.getText();
				    	    
				    	    System.out.println("Total********************************: " + total1);
				    	    System.out.println(" ");
				    	    
				    	    float precioP = Float.parseFloat(precioPasajero);
				    		float tasasI = Float.parseFloat(tasaImpu);
				    		float gestA = Float.parseFloat(gestAgen);
				    		float totalTotal = Float.parseFloat(total1);
				    		float precioN = Float.parseFloat(precioNormal);
				    		
				    		float precioCalculado = precioP + tasasI + gestA;
				    		
				  	      // compares the two specified float values 
				    		float f1 = precioCalculado;
				    		float f2 = totalTotal;
				    		float f3 = precioN;
				    		int retval = Float.compare(f1, f2);
				    		int retval2 =Float.compare(f2, f3);
						
				    		if(retval > 0) {
				    			System.out.println("f1 es mayor a f2");
				    		} else if(retval < 0) {
				    			System.out.println("f1 es menor a f2");
				    		} else {
				    			System.out.println("f1 es igual a f2");
				    		}
				    	    
				    		if(retval2 > 0) {
				    			System.out.println("f2 es mayor a f3");
				    		} else if(retval2 < 0) {
				    			System.out.println("f2 es menor a f3");
				    		} else {
				    			System.out.println("f2 es igual a f3");
				    		}
				    	 
			    		}
				    	//********************Inicio de recorrido de Filtros *******************//	
			    		
				    	   
			    	    javascript.executeScript("scroll(0, 300);");
			    		
			    		
			    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filterResultForm']/div[1]/div[1]/h3")));
			    	    esperarElemento.click();
			    	    
			    		// *******Filtro Moneda*****//
			    		
			    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toUsd']/label/input")));
			    	    esperarElemento.click();
			    	    
			    		javascript.executeScript("scroll(0, -1200);");
			    	    
			    	    
			    		// *******Filtro Moneda*****// 
			    		
		    			//Captura de datos de cantidades en dolares//
		    																		
	    				// SIMBOLO CURRENCY
			    		
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[1]")));
			    	    String result_tipo_moneda = esperarElemento.getText();
			    		
			    	    System.out.println ("SELECCIÓN MONEDA  *******************: "+ result_tipo_moneda);
			    	    
			    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[2]")));
			    	    String precioNormal = esperarElemento.getText();								
			    	    
			    	    System.out.println("Precio Descuento*********************: " + precioNormal);
			    	    
			    	    				
			    	    
			    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[1]/span[2]")));
			    	    String precioPasajero = esperarElemento.getText();
			    	    
			    	    System.out.println("Precio Pasajero**********************: " + precioPasajero);
			    	    
			    	    
			    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[2]/span[2]")));
			    	    String tasaImpu = esperarElemento.getText();
			    	    
			    	    System.out.println("Impuestos y Tasas********************: " + tasaImpu);
			    	                                    
			    	    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[3]/span[2]")));
			    	    String gestAgen = esperarElemento.getText();
			    	    
				    	System.out.println("Gestión de Agencias******************: " + gestAgen);
			    	    
				    	esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/ul/li[4]/span[2]")));
			    	    String total1 = esperarElemento.getText();							
				    	
			    	    System.out.println("Total********************************: " + total1);
			    	    System.out.println(" ");
			    	    
			    	    javascript.executeScript("scroll(0, 300);");
			    		
			    	    float precioP = Float.parseFloat(precioPasajero);
			    		float tasasI = Float.parseFloat(tasaImpu);
			    		float gestA = Float.parseFloat(gestAgen);
			    		float totalTotal = Float.parseFloat(total1);
			    		float precioN = Float.parseFloat(precioNormal);
			    		
			    		float precioCalculado = precioP + tasasI + gestA;
			    		
			  	      // compares the two specified float values
			    		float f1 = precioCalculado;
			    		float f2 = totalTotal;
			    		float f3 = precioN;
			    		int retval = Float.compare(f1, f2);
			    		int retval2 =Float.compare(f2, f3);
					
			    		if(retval > 0) {
			    			System.out.println("f1 es mayor a f2");
			    		} else if(retval < 0) {
			    			System.out.println("f1 es menor a f2");
			    		} else {
			    			System.out.println("f1 es igual a f2");
			    		}
			    	    
			    		if(retval2 > 0) {
			    			System.out.println("f2 es mayor a f3");
			    		} else if(retval2 < 0) {
			    			System.out.println("f2 es menor a f3");
			    		} else {
			    			System.out.println("f2 es igual a f3");
			    		}
			    	 
			    		
		    		
			    		
			    		//cambia a tipo moneda pesos
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toWn']/label/input")));
			    	    esperarElemento.click();
			    		
			    		
			    		//************************ Filtro Escalas ***************//
			    		
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filterResultForm']/div[2]/div[1]/h3/i")));
			    	    esperarElemento.click();
			    		
			    		
			    		//***Captura de Valores**//
			    		
			    		//numero de todas las escalas
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='allStops']")));
			    		esperarElemento.getText();
			    		
			    		System.out.println("Total de todas las Escalas: " + esperarElemento.getText());
			    		
			    		//numero de Directo
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='directStop']")));
			    	    String escalaDirecto = esperarElemento.getText();
			    		
			    		System.out.println("Total de Vuelos Directos: " + escalaDirecto);
			    		
			    		//numero de registros una escala
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='oneStop']")));
			    	    String unaEscala = esperarElemento.getText();
			    	    
			    		System.out.println("Total de Vuelos con 1 Escala: " + unaEscala);
			    		
			    		
			    		//numero de registros de 2 o mas escalas
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='twoStop']")));
			    	    String dosEscalas = esperarElemento.getText();
			    		
			    		System.out.println("Total de Vuelos con 2 o mas Escalas: " + dosEscalas);
			    		System.out.println(" ");
			    		//***Fin Captura de Valores**//
			    		
			    		
			    		//Directo
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filterResultForm']/div[2]/div[2]/div[2]/label/input")));
			    		esperarElemento.click();
			    		
			    		javascript.executeScript("scroll(0, -1200);");
			    		
			    		int numDirecto = Integer.parseInt(escalaDirecto);
			    		
			    		if (numDirecto > 0)
			    		{
			    						
			    			esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[2]/ul/li[3]/a/span[1]")));
			    			String varDirecto = esperarElemento.getText();
			    			
			    			esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[4]/ul/li[3]/a/span[1]")));
			    			String varDirecto2 = esperarElemento.getText();
			    			
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
			    		
			    		//1 Escala
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filterResultForm']/div[2]/div[2]/div[3]/label/input")));
			    		esperarElemento.click();
			    		
			    		javascript.executeScript("scroll(0, -1200);");
			    	
			    		int numUnaEscala = Integer.parseInt(unaEscala);
			    		
			    		if (numUnaEscala > 0)
			    		{
			    		
			    			esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[2]/ul/li[3]/a/span[1]")));
			    			String varUnaEscala = esperarElemento.getText();
			    			
			    			esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[4]/ul/li[3]/a/span[1]")));
			    			String varUnaEscala2 = esperarElemento.getText();
			    			
			    			
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
			    		
			    		//2 o mas Escalas		     
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filterResultForm']/div[2]/div[2]/div[4]/label/input")));
		    			esperarElemento.click();
			    		
			    		
			    		javascript.executeScript("scroll(0, -1200);");
			    		

			    		int numDosOMasEscala = Integer.parseInt(dosEscalas);
			    		
			    		if (numDosOMasEscala > 0){
			    			
			    			
			    			esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[2]/ul/li[3]/a/span[1]")));
			    			String varDosEscala = esperarElemento.getText();
			    			
			    			esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[4]/ul/li[3]/a/span[1]")));
			    			String varDosEscala2 = esperarElemento.getText();
			    			
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
			    		
			    		
			    		//Todas las Escalas
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='clearStopsFilter']")));
		    			esperarElemento.click();
			    		
			    		//Fin Clic Escala
		    			esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filterResultForm']/div[2]/div[1]/h3/i")));
		    			esperarElemento.click();
		    			
			    		//************************ Fin Filtro Escalas ***************//
			    		
			    		
			    		//************************ Filtro Aerolíneas ***************//
			    		
			    		//Clic Aerolíneas 				
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='carriersFilter']/div[1]/h3/i")));
		    			esperarElemento.click();
			    		
			    		
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
			    		
			    		javascript.executeScript("scroll(0, 300);");
	 		
			    		//Clic cerrar pestaña Aerolínea
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='carriersFilter']/div[1]/h3/i")));
		    			esperarElemento.click();
			    		
			    		//******************* Fin de recorrido Filtros **********************//					    		
				    		
				    	//**********************Identificación del Pasajero************************//
				    		
			    		javascript.executeScript("scroll(0, -1200);");
			    		
			    		esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fligthsData']/div[2]/form/div[2]/div/input[3]")));
		    			esperarElemento.click();
			    		
			    		javascript.executeScript("scroll(0, 300);");
					    
					    javascript.executeScript("scroll(0, 600);");
					    
					    javascript.executeScript("scroll(0, 1200);");
					     
					    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label/input")));
		    			esperarElemento.click();
					    
					    driver.findElement(By.xpath("//div[3]/div/div/input")).clear();

					    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div/div/input")));
		    			esperarElemento.sendKeys("Armando");
					    
					    driver.findElement(By.xpath("//div[2]/input")).clear();

					    
					    esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/input")));
		    			esperarElemento.sendKeys("Figueredo");
		  
		    			esperarElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selectDoc")));
			    		esperarElemento.click();
			    								 
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
				    		
				    	//************************** Fin Inicio del Login ********************************//

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
	

