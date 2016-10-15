# java-web
Servlets, Web Services
will soon be available... (Repositories will be reorganized)
## Web Application Template
Servlet written with Spring MVC + Security, Hibernate, JSTL, JQuery, Bootstrap dependencies. Find details in project folder.
## UserAuthenticationWS
SOAP Web Service
## UserAuthenticationWSC
Client application for UserAuthenticationWS
## RoleManagementWS
* Spring MVC Restful Web Service with Hibernate integration
* Returns list of roles for a given username
* Clint application has to import RoleManagementWSClient

Dependencies
```sh
<springframework.version>4.2.2.RELEASE</springframework.version>
<spring.ws.version>2.0.0.RELEASE</spring.ws.version>
<hibernate.version>4.3.6.Final</hibernate.version>
<oracle.version>12.1.0.2.0</oracle.version>
```

Integration of the service
```sh
@Autowired
private RoleManagementService roleManagementService;
...
List<Role> userRoles = roleManagementService.getUserRoles(username);
```
## RoleManagementWSC
Client application for RoleManagementWS
