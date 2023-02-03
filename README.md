# rc-ficoscore-client-java

Esta API reporta el historial crediticio, el cumplimiento de pago de los compromisos que la persona ha adquirido con entidades financieras, no financieras e instituciones comerciales que dan crédito o participan en actividades afines al crédito. En esta versión se retornan los campos del Crédito Asociado a Nomina (CAN) en el nodo de créditos.

## Requisitos

1. Java >= 1.7
2. Maven >= 3.3

## Instalación

Para la instalación de las dependencias se deberá ejecutar el siguiente comando:

```shell
mvn install -Dmaven.test.skip=true
```

> **NOTA:** Este fragmento del comando *-Dmaven.test.skip=true* evitará que se lance la prueba unitaria.


## Guía de inicio

### Paso 1. Agregar el producto a la aplicación

Al iniciar sesión seguir los siguientes pasos:

 1. Dar clic en la sección "**Mis aplicaciones**".
 2. Seleccionar la aplicación.
 3. Ir a la pestaña de "**Editar '@tuApp**' ".
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/edit_applications.jpg" width="900">
    </p>
 4. Al abrirse la ventana emergente, seleccionar el producto.
 5. Dar clic en el botón "**Guardar App**":
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/selected_product.jpg" width="400">
    </p>

### Paso 2. Capturar los datos de la petición

Los siguientes datos a modificar se encuentran en ***src/test/java/com/cdc/apihub/mx/RC/FicoScore/api/ReporteDeCreditoConFicoScoreApiTest.java***

Es importante contar con el setUp() que se encargará de inicializar la url. Modificar la URL ***('the_url')*** de la petición del objeto ***$config***, como se muestra en el siguiente fragmento de código:

```java
 private final ReporteDeCreditoConFicoScoreApi api = new ReporteDeCreditoConFicoScoreApi();

@Before()
    public void setUp() {
    	ApiClient apiClient = api.getApiClient();
		apiClient.setBasePath("the_url");
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder().readTimeout(30, TimeUnit.SECONDS)
				.addInterceptor(new SignerInterceptor(keystoreFile, cdcCertFile, keystorePassword, keyAlias, keyPassword)).build();
		apiClient.setHttpClient(okHttpClient);
    }

```

En el archivo **DireccionesApiTest**, que se encuentra en ***src/test/java/com/cdc/apihub/mx/RC/FicoScore/api*** se deberá modificar el siguiente fragmento de código con los datos correspondientes:

```java
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
```

### Paso 3. Ejecutar la prueba unitaria

Teniendo los pasos anteriores ya solo falta ejecutar la prueba unitaria, con el siguiente comando:

```shell
mvn test -Dmaven.install.skip=true
```

---
[TERMINOS Y CONDICIONES](https://github.com/APIHub-CdC/licencias-cdc)