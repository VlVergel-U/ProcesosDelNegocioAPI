//package com.procesos.parcial;
//
//import org.mockito.junit.MockitoJUnitRunner;
//
//
//@RunWith(MockitoJUnitRunner.class)
//
//public class ApiServiceTest {
//
//        @Mock
//        private ApiService apiService;
//
//        @InjectMocks
//        private ApiController apiController;
//
//        @Test
//        public void testProcessRequest() {
//            // Simulando el comportamiento del servicio
//            when(apiService.processRequest(anyString())).thenReturn("Mocked response");
//
//            // Ejecutando la prueba
//            GenericRequest<String> request = new GenericRequest<>();
//            request.setData("Test data");
//            ResponseEntity<GenericResponse<String>> responseEntity = apiController.handleRequest(request);
//
//            // Verificando el resultado
//            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//            assertThat(responseEntity.getBody().getData()).isEqualTo("Mocked response");
//        }
//    }
//
//}
