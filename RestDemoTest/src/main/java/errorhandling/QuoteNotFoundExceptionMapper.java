
package errorhandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class QuoteNotFoundExceptionMapper implements ExceptionMapper<QuoteNotFoundException>
{
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(QuoteNotFoundException exception)
    {
        String msg = exception.getMessage();
        ErrorMessage err = new ErrorMessage(msg,404);
        Response res = Response.status(404).entity(gson.toJson(err)).type(MediaType.APPLICATION_JSON).build();
        return res;
    }
    
}
