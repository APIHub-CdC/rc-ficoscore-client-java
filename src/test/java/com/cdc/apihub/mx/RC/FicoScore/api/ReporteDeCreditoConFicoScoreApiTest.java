package com.cdc.apihub.mx.RC.FicoScore.api;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdc.apihub.mx.RC.FicoScore.api.ReporteDeCreditoConFicoScoreApi;
import com.cdc.apihub.mx.RC.FicoScore.client.ApiClient;
import com.cdc.apihub.mx.RC.FicoScore.client.ApiException;
import com.cdc.apihub.mx.RC.FicoScore.model.CatalogoEstados;
import com.cdc.apihub.mx.RC.FicoScore.model.CatalogoTipoAsentamiento;
import com.cdc.apihub.mx.RC.FicoScore.model.CatalogoTipoDomicilio;
import com.cdc.apihub.mx.RC.FicoScore.model.Consultas;
import com.cdc.apihub.mx.RC.FicoScore.model.Creditos;
import com.cdc.apihub.mx.RC.FicoScore.model.DomicilioPeticion;
import com.cdc.apihub.mx.RC.FicoScore.model.DomiciliosRespuesta;
import com.cdc.apihub.mx.RC.FicoScore.model.Empleos;
import com.cdc.apihub.mx.RC.FicoScore.model.Mensajes;
import com.cdc.apihub.mx.RC.FicoScore.model.PersonaPeticion;
import com.cdc.apihub.mx.RC.FicoScore.model.Respuesta;
import com.cdc.apihub.mx.RC.FicoScore.model.Scores;
import com.cdc.apihub.signer.manager.interceptor.SignerInterceptor;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;


public class ReporteDeCreditoConFicoScoreApiTest {
	
    private final ReporteDeCreditoConFicoScoreApi api = new ReporteDeCreditoConFicoScoreApi();
    private Logger logger = LoggerFactory.getLogger(ReporteDeCreditoConFicoScoreApi.class.getName());
    
    private String keystoreFile = "/Users/globatos/Documents/LLAVES_APIGEE/qa/keystore.jks";
	private String cdcCertFile = "/Users/globatos/Documents/LLAVES_APIGEE/qa/cdc_cert.pem";
	private String keystorePassword = "p@stgres";
	private String keyAlias = "cdc";
	private String keyPassword = "p@stgres";
	
	
	@Before()
    public void setUp() {
    	
    	ApiClient apiClient = api.getApiClient();
		apiClient.setBasePath("the_url");
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder().readTimeout(30, TimeUnit.SECONDS)
				.addInterceptor(new SignerInterceptor(keystoreFile, cdcCertFile, keystorePassword, keyAlias, keyPassword)).build();
		apiClient.setHttpClient(okHttpClient);
    }
    
    @Test
    public void getReporteTest() throws ApiException {
        
        String xApiKey = "your_api_key";
        String username = "username";
        String password = "password";
        String xFullReport = null;
    	
        PersonaPeticion persona = new PersonaPeticion();
        DomicilioPeticion domicilio = new DomicilioPeticion();
        
        persona.setPrimerNombre("XX");
        persona.setApellidoPaterno("XX");
        persona.setApellidoMaterno("XX");
        persona.setFechaNacimiento("XX");
        persona.setRFC("XX");
        persona.setNacionalidad("MX");
        
        domicilio.setDireccion("XX");
        domicilio.setColoniaPoblacion("XX");
        domicilio.setDelegacionMunicipio("XX");
        domicilio.setCiudad("XX");
        domicilio.setEstado(CatalogoEstados.MEX);
        domicilio.setCP("XX");
        domicilio.setFechaResidencia("XX");
        domicilio.setNumeroTelefono("XX");
        domicilio.setTipoDomicilio(CatalogoTipoDomicilio.C);
        domicilio.setTipoAsentamiento(CatalogoTipoAsentamiento._0);
        
        persona.setDomicilio(domicilio);
        
        
        
        Respuesta response = api.getReporte(xApiKey, username, password, persona, xFullReport);
        
        Assert.assertTrue(response.getFolioConsulta() != null);
        
        logger.info(response.toString());
        
        
        if (response.getFolioConsulta() != null && (xFullReport == null ||  xFullReport.equals("false") || xFullReport.equals("FALSE"))) {
        	
			String folioConsulta = response.getFolioConsulta();

			Consultas consultas = api.getConsultas(folioConsulta, xApiKey, username, password);
			logger.info(consultas.toString());
			Assert.assertTrue(consultas.getConsultas() != null);

			Creditos creditos = api.getCreditos(folioConsulta, xApiKey, username, password);
			logger.info(creditos.toString());
			Assert.assertTrue(creditos.getCreditos() != null);

			DomiciliosRespuesta domicilios = api.getDomicilios(folioConsulta, xApiKey, username, password);
			logger.info(domicilios.toString());
			Assert.assertTrue(domicilios.getDomicilios() != null);

			Empleos empleos = api.getEmpleos(folioConsulta, xApiKey, username, password);
			logger.info(empleos.toString());
			Assert.assertTrue(empleos.getEmpleos() != null);

			Scores scores = api.getScores(folioConsulta, xApiKey, username, password);
			logger.info(scores.toString());
			Assert.assertTrue(scores.getScores() != null);
			
			Mensajes mensajes = api.getMensajes(folioConsulta, xApiKey, username, password);
			logger.info(mensajes.toString());
			Assert.assertTrue(mensajes.getMensajes() != null);
		}

    }
    
 
}
