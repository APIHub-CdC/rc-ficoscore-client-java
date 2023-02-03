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
    
    private String keystoreFile = "/your_path/keystore.jks";
	private String cdcCertFile = "/your_path/cdc_cert.pem";
	private String keystorePassword = "your_password";
	private String keyAlias = "your_alias";
	private String keyPassword = "your_key_password";
	
	
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
        
        
        
        Respuesta response = api.getReporte(xApiKey, username, password, persona);
        
        Assert.assertTrue(response.getFolioConsulta() != null);
        
        logger.info(response.toString());
        
        

    }
    
 
}
