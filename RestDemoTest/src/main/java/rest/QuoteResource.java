
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Quote;
import errorhandling.QuoteNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("quote")
public class QuoteResource
{

    @Context
    private UriInfo context;
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Map<Integer,String> quotes = new HashMap()
    {{
        put(0, "Læsefidusen");
        put(1, "Ja den er god");
        put(2, "jajaja");
        put(3, "sådan");
    }};

    /**
     * Creates a new instance of PersonResource
     */
    public QuoteResource()
    {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpecificQuote(@PathParam("id") int id) throws QuoteNotFoundException
    {
        if(quotes.size() == 0)
        {
            throw new QuoteNotFoundException("No Quotes Created Yet");
        }
        if(id >= quotes.size())
        {
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
        Quote q = new Quote(id, quotes.get(id));
        String result = gson.toJson(q);
        return result;
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomQuote() throws QuoteNotFoundException
    {
        if(quotes.size() == 0)
        {
            throw new QuoteNotFoundException("No Quotes Created Yet");
        }
        //int bingo = 3 / 0; For general 500 error!
        int id = (new Random().nextInt(quotes.size()));
        Quote q = new Quote(id, quotes.get(id));
        String result = gson.toJson(q);
        return result;
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postData(String content)
    {
        Quote q = gson.fromJson(content, Quote.class);
        int newId = quotes.size();
        q.setId(newId);
        quotes.put(newId, q.getQuote());
        return gson.toJson(q);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putData(String content, @PathParam("id") int id) throws QuoteNotFoundException
    {
        if(quotes.size() == 0)
        {
            throw new QuoteNotFoundException("No Quotes Created Yet");
        }
        if(id >= quotes.size())
        {
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
        Quote newQuote = gson.fromJson(content, Quote.class);
        newQuote.setId(id);
        quotes.put(id, newQuote.getQuote());
        return gson.toJson(newQuote);
    }
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteData(String content, @PathParam("id") int id) throws QuoteNotFoundException
    {
        if(quotes.size() == 0)
        {
            throw new QuoteNotFoundException("No Quotes Created Yet");
        }
        if(id >= quotes.size())
        {
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
        Quote deleteQuote = gson.fromJson(content, Quote.class);
        Quote emptyQuote = new Quote(id, "DELETED");
        quotes.put(id, emptyQuote.getQuote());
        return gson.toJson(emptyQuote);
    }
}
