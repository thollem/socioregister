# socioregister
A Springboot REST application to register and add Socios

1) General Info About the Socio-Micro-Service-Demo

2) Specific Info Concerning Each Single Application



General Info =====================================

The Socio Micro Services Project will consist of about 10 small (backend) Springboot applications, deployed in a Docker Container/ Linux Oracle Virtual Box. SocioRegister is the principal part of a series of four applications called: starter, mock, jpa, socioregister. Together they show a stepwise buildup to a Springboot REST application, which contains use-cases for registering and adding Socios (similar to Facebook). This line of applications goes from an almost empty Springboot shell (starter: one controller method only) to a small but full-fledged REST application: SocioRegister which will be used as a component of our micro-services.

Next you`ll find four other serving applications. The simple SocioWeather, provides a weather-report by city by consulting an external REST-service called Open Weather. SocioBank, permits money transaction between Socios alse consulting an external service for exchange rates. The SocioSecurity, a Cookie/ Token based SpringSecurity (OAUTH2), still has to be written. Finally the SocioDbBatch application is interesting because it will update, on a daily bases, the databases of SocioRegister (socio_db) and SocioBank (soicio_bank_db). The DBs run on MySQL or Postgres.

From SocioRegister-jpa one finds backend-Validation (javax) and REST-Exception Handeling of Spring (RestControllerAdvice).

Testing, in general, will have an important focus and since we are dealing with Spring(boot) there will systematically testing based on five mayor strategies:

	-@ExtendWith(MockitoExtension.class)

	-@ExtendWith(SpringExtension.class) standalone setup (two ways)

	-@ExtendWith(SpringExtension.class) server tests (@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

	-@DataJpaTest wich is database testing on H2

	-Spring Batch testing

Testing is still "work in progress"



Specific Info SocioRegister =====================================

The previous three applications (starter, mock, jpa) form the prelude for the final SocioRegister application to be deployed at a Docker Container. Maybe it is a good idea to start first with a small recap?

	1) SocioRegister-starter shows how to begin a Micro-Service setup beginning with an empty shell of a Springboot (WEB/REST) application. Stressing the importance of the Maven pom (or Gradle file) and the Springboot Starter Dependencies.
	
	2) SocioRegister-mock is already more personalized, adding several Socio-layers to the mix: first the principal layers, the controller, service (interface and implemention), and the Repository; second, from DTO via Modelmapper convertion to Model (Entity) and visa versa; third, the use of the ResponseEntity class at the controllers' returns.
	
	3) SocioRegister-jpa adds the Spring-data layer in the form of JPA-persistence (Postgres DB). It further goes into the implementation of validation messages. And finally one finds an implementaion of a Spring Global Exception Handeling (amung others the ResourceNotFoundException) based on the @RestControllerAdvice and the ResponseEntityExceptionHandler.

Like I said before at this part I would like to elaborate on testing and DB initialization

Testing has a mayor importance at Micro-Service and Springboot applications, so lets dive into it!




The use-cases of socioregister are more extend since there are 8 tables present now:

	-http://localhost:8081/socio

	-http://localhost:8081/socio/2

	-http://localhost:8081/socio/username/js

And by using Postman:

	-post http://localhost:8081/socio  
		{
			"username": "rs",
			"password": "secret",
			"firstName": "Richard",
			"lastName": "Strauss",
			"email": "strauss@gmail.com",
			"active": true,
			"socioLanguages": [
                {"id": "2"},
                {"id": "3"}
			] 
		} 
  
	-post http://localhost:8081/address
		{ 
			"street":"Bachstraat 24",
			"city":"Haarlem",
			"province":"Noord Holland",
			"postalcode":"4365KL",
			"description":"some comment",
			"country": {"id": 5},
			"socioId":3,
			"addressType": "HOME"
		}
	
	-put http://localhost:8081/socio
		{
			"id": 7, <- note the id of the existing socio!
			"username": "rsxxx",
			"password": "secret",
			"firstName": "Richard",
			"lastName": "Strauss",
			"email": "strauss@gmail.com",
			"active": true,
			"socioLanguages": [
                {"id": "2"},
                {"id": "3"}
			] 
		} 
	etc.
	
    -add an associated socio: post http://localhost:8081/associatedSocio/1/2    (/{socioId}/{associatedSocioId})
	-change state: put http://localhost:8081/associatedSocio/1/2/true    (/{socioId}/{associatedSocioId}/{boolean})
	-delete an associated socio: delete http://localhost:8081/associatedSocio/1/2    (/{socioId}/{associatedSocioId})
	