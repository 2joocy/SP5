/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DBMapper.DBMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;
    private static List<Person> persons = new ArrayList();
    
    private final DBMapper db = new DBMapper();
    private final JSONConverter conv = new JSONConverter();
    
    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
        
    }
    //private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
//
//    //Example1: simple (no parameters and media type: text)
//    //This method runs when server recieves a get request like:
//    //http://localhost:8084/restdemo/api/person
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
//        JsonObject job = new JsonObject();
//        job.addProperty("quote", quote);
        return "Hello From REST";
    }
    

    //Ex2: using @Path and @PathParam to provide an input to the method
    //Request like: http://localhost:8084/restdemo/api/person/<some name>
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addPerson(String jsonPerson) {
        Person p = conv.getPersonFromJson(jsonPerson);
        p.setId(persons.size());
        db.addPerson(p);
        return Response
                .status(200)
                .entity("Person has been successfully added!")
                .build();
    }
//    @GET
//    @Path("{id}")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getPerson(@PathParam("id") int id){
//        return persons.get(1).getName();
//    }
//
//    //Ex3: Using the Response object
//    //Test with: http://localhost:8084/restdemo/api/person/resp
    @GET
    @Path("/getPersons")
    public Response getPersons() {
        String json = conv.getJSONFromPerson(persons);
        Response res = Response.status(200).entity(json).build();
        return res;
    }
//
//    //Ex4: Using key value (username={username})
//    //test with http://localhost:8084/restdemo/api/person/username/tha123
    @GET
    @Path("/username/{username}")
    public Response getPersonByName(@PathParam("username") String username) {
        return Response.status(200).entity("getUserByUserName is called, username : " + username).build();
    }
//
//    //Ex5: Using regexp to validate input
//    //Using gson to create JSON from java object (Type: Person)
//    //test with: http://localhost:8084/restdemo/api/person/id/3
}
