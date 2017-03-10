/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DBMapper.DBMapper;
import com.google.gson.Gson;
import entity.Country;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk
 */
@Path("world") //Test this with url : localhost:port/<application>/api/test
public class TestResource {

    private Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;
    private DBMapper db = new DBMapper();

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    /**
     * Simple example
     * Shows how to request a resource with the class/root resource path
     */
    @GET
    public String getText() {
        return "This is the first and most simple test";
    }
    
    //Getting a subresource - by using the @Path annotation on the method:
    @GET
    @Path("/countries")
    public String getAllCountry(){
        String ss = gson.toJson(db.getAllCountries(), List.class);
        return ss;
    }
    
    @GET
    @Path("/country/{population}")
    public String getCountry(@PathParam("population") int population){
        String ss = gson.toJson(db.getCountryGreaterThanPopulation(population), List.class);
        return ss;
    }
    
    
    //Using the Response object to return status and message:
    
    //Returning response and using gson to format as json
    
    //Send input parameter to the get request test with: /api/json/someparameter
    //if the parameter equals the name of another methods path this will overwrite the parameter (like using  /api/json/getresponse from the previous method
    @GET
    @Path("/json/{input}")
    public Response getFromInput(@PathParam("input") String input){
        return Response.status(Response.Status.OK).entity("The input is: "+input).build();
    }
    
    // Using 2 or more inputs
    @GET
    @Path("/json/mul/{n1}/{n2}")
    public Response getFromMultipleInput(@PathParam("n1") int n1, @PathParam("n2") int n2){
        return Response.status(200).entity("Result when multiplying: "+ (n1*n2)).build();
    }

    // Using Post json object formatted data to the server
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public String post(MultivaluedMap<String, String> formParams) {
        return formParams.getFirst("name");
    }

    //In this example I am using jettison to get parameters out of a json object (See the pom.xml)
    @DELETE //This method must have a String input parameter. 
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteSomething(String input) throws JSONException {
        JSONObject job = new JSONObject(input);
        String name = job.optString("name");
        String age = job.optString("age");
        return name + " of " + age;
    }
}
