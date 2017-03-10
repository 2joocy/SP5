/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Person;
import java.util.List;

/**
 *
 * @author William Pfaffe
 */
public class JSONConverter {
    
    private  Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
    
    public Person getPersonFromJson(String js){
        Person p = gson.fromJson(js, Person.class);
        return p;
    }
    
    public String getJSONFromPerson(Person p){
        String JSONPerson = gson.toJson(p);
        return JSONPerson;
    }
    
    public String getJSONFromPerson(List<Person> persons){
        String js = gson.toJson(persons, List.class);
        return js;
    }
}
