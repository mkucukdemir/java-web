package com.smallfe.rolemanagementwsc.service;

import com.smallfe.rolemanagementwsc.model.Role;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Write short comment on created class
 * Created on 04.Nis.2016
 * @author mkucukdemir
 */
public class RoleManagementService {
    
    public List<Role> getUserRoles(String username) {
        String urlGETList = "http://localhost:8084/RoleManagementWS/"+username;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Role>> responseEntity = restTemplate.exchange(urlGETList,HttpMethod.GET,null,new ParameterizedTypeReference<List<Role>>(){});
        List<Role> roles = responseEntity.getBody();
        MediaType contentType = responseEntity.getHeaders().getContentType();
        HttpStatus statusCode = responseEntity.getStatusCode();
        if(statusCode.equals(HttpStatus.OK))
            return roles;
        else
            return null;
        
    }

}
