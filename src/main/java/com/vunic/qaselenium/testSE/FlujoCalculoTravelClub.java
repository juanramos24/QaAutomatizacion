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
				//		    Thread.sleep(5000);

				// FALTARIA DAR FORMATO DE STRING TO DATE
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date travelDateFrom_1 = sdf.parse(itemDTO.getFecha_desde_1());
				SimpleDateFormat fecha1 = new SimpleDateFormat("dd-MM-yyyy");

				SimpleDateFormat sdf_3 = new SimpleDateFormat("yyyy-MM-dd");
				Date travelDateTo_1 = sdf_3.parse(itemDTO.getFecha_hasta_1());
				SimpleDateFormat sdf4 = new SimpleDateFormat("dd-MM-yyyy");


				Calendar calen = Calendar.getInstance();
				calen.setTime(travelDateFrom_1);
				calen.add(Calendar.DATE, 5);
				Date travelDateTo_f1 = calen.getTime();


				if( travelDateTo_1.before(travelDateTo_f1))
				{
					travelDateTo_f1 = travelDateTo_1;	
				}

				System.out.println ("FECHA HASTA *************************************************************************: "+travelDateTo_f1);

				driver.findElement(By.id("startDate")).sendKeys(fecha1.format(travelDateFrom_1));
				Thread.sleep(tiempo);

				driver.findElement(By.id("startDate")).sendKeys(Keys.TAB);
				Thread.sleep(tiempo);

				System.out.println ("FECHA FORMATEADA 1 ******************************************************************: "+travelDateFrom_1);
				// ------------------------------------- FECHA PARTIDA -------------------------------------- //

				List<WebElement> inputs_2 = driver.findElements(By.id("endDate"));

				for (WebElement input : inputs_2) 
				{
					((JavascriptExecutor) driver).executeScript ("arguments[0].removeAttribute('readonly','readonly')",input);
				}

				driver.findElement(By.id("endDate")).sendKeys(sdf4.format(travelDateTo_1));
				Thread.sleep(tiempo);

				driver.findElement(By.id("endDate")).sendKeys(Keys.TAB);
				Thread.sleep(tiempo);

				System.out.println ("FECHA FORMATEADA 2 ******************************************************************: "+travelDateFrom_1);

				// ------------------------------------- FECHA REGRESO -------------------------------------- //

				//driver.findElement(By.xpath(".//*[@id='flightSearchForm']/div[8]/input")).click();
				driver.findElement(By.xpath("//*[@id='flightSearchForm']/div[9]/input")).click();
				Thread.sleep(tiempo);

				//**************************************************************************************************************//

				// IF SI ENCUENTRA EL MENSAJE, ENTONCES QUE CAMBIE LOS VALORES DEL MENU 3 VECES Y PINTE FALLIDO
				String result_id_error = driver.findElement(By.id("errorMessage")).getText();

				System.out.println("ERROR: ******************************: "+result_id_error);

				boolean activar = false;

				if (result_id_error.isEmpty())
				{

					System.out.println("SIN ERROR");
					Thread.sleep(tiempo);

					if(!driver.findElements(By.xpath(".//*[@id='bigImgPromoLi']/img")).isEmpty())
					{
						System.out.println("LA PROMOCI�N SE ENCUENTRA CON EL RESPECTIVO BANNER");
						System.out.println(" ");
						activar = true;
					}
					else
					{
						System.out.println("LA PROMOCI�N NO SE ENCUENTRA CON EL RESPECTIVO BANNER");
						System.out.println(" ");

					}


					if (activar == true)
					{
						//System.out.println("ORIGEN: " +  origin   +  "  DESTINO :" + id_iataCityCode);	

						// SIMBOLO CURRENCY
						String result_tipo_moneda = driver.findElement(By.xpath(".//*[@id='fligthsData']/div[2]/form/div[2]/span[4]/span[1]")).getText();
						System.out.println ("SELECCI�N MONEDA  ********************************* : "+ result_tipo_moneda);

						// PRECIO ORIGINAL TACHADO
						String result_precio_original = driver.findElement(By.xpath(".//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[3]")).getText();
						//String result_precio_original = driver.findElement(By.xpath(".//*[@id='fligthsData']/div[2]/form/div[2]/span[3]/span[2]")).getText();

						System.out.println ("VALOR CAPTURADO 1 ********************************* : "+result_precio_original);

						// PRECIO CON DESCUENTO
						String result_precio_descuento = driver.findElement(By.xpath(".//*[@id='fligthsData']/div[2]/form/div[2]/span[4]/span[3]")).getText();
						//String result_precio_descuento = driver.findElement(By.xpath(".//*[@id='fligthsData']/div[2]/form/div[2]/span[4]/span[2]")).getText();

						System.out.println ("VALOR CAPTURADO 2 ********************************* : "+result_precio_descuento);

						driver.findElement(By.xpath(".//*[@id='fligthsData']/div[2]/form/div[2]/div/input[3]")).click();
						Thread.sleep(tiempo);

						// SCROLL DOWN - 1 PARTE
						JavascriptExecutor jse_A1 = (JavascriptExecutor)driver;
						jse_A1.executeScript("scroll(0, 300);");

						/*   IdentificacionPasajeros identify = new IdentificacionPasajeros();
					    identify.identificacionPasajero();
					    System.out.println("Pas� por la identificaci�n del pasajero");*/

						String result_precio_original_1 = driver.findElement(By.xpath(".//*[@id='checkoutForm']/article/div[1]/div[2]/div[2]/div[2]/ul[1]/li[2]/span[2]")).getText();
						Thread.sleep(tiempo);
						System.out.println ("VALOR CAPTURADO 3 ********************************* : "+result_precio_original_1);

						String result_precio_descuento_1 = driver.findElement(By.xpath(".//*[@id='checkoutForm']/article/div[1]/div[2]/div[2]/div[2]/ul[2]/li[2]/span[2]")).getText();
						Thread.sleep(tiempo);
						System.out.println ("VALOR CAPTURADO 4 ********************************* : "+result_precio_descuento_1);

						String valor1 = result_precio_original.replace(".",""); // remplazando puntos y comas
						String valor2 = result_precio_descuento.replace(".",""); // remplazando puntos y comas

						int result_precio_original_A1 = Double.valueOf(valor1).intValue();
						int result_precio_descuento_A1 = Double.valueOf(valor2).intValue();

						//		    	int result_precio_original_B1 = Integer.parseInt(result_precio_original_1);
						//		    	int result_precio_descuento_B1 = Integer.parseInt(result_precio_descuento_1);

						String valor3 = result_precio_original_1.replace(".",""); // remplazando puntos y comas
						String valor4 = result_precio_descuento_1.replace(".",""); // remplazando puntos y comas

						int result1 = Double.valueOf(valor1).intValue();
						int result2 = Double.valueOf(valor2).intValue();

						int result_precio_original_B1 = Double.valueOf(valor3).intValue();
						int result_precio_descuento_B1 = Double.valueOf(valor4).intValue();

						double var_dif_1 = (result_precio_original_A1 - result_precio_original_B1);

						double var_dif_2 = (result_precio_original_A1 - result_precio_descuento_A1);
						System.out.println(" ");
						System.out.println("VALOR DIFERENCIA 1 ********************************* : " + var_dif_1);
						System.out.println("VALOR DIFERENCIA 2 ********************************* : " + var_dif_2);
						System.out.println(" ");

						if (var_dif_1 == var_dif_2)
						{
							System.out.println("PROMOCION CORRECTA *********************************** : ");
						}
						else
						{
							System.out.println("PROMOCION INCORRECTA ********************************* : ");
						}

					}
					else
					{
						System.out.println("CON ERROR");
						//Intentar 3 Veces
					}
					System.out.println("SALI FLUJO - TRAVEL CLUB");
					Thread.sleep(tiempo);
				}
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
	

