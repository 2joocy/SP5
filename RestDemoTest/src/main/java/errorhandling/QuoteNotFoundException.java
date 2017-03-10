
package errorhandling;


public class QuoteNotFoundException extends Exception
{

    public QuoteNotFoundException()
    {
        super();
    }
    
    public QuoteNotFoundException(String quote_not_found)
    {
        super(quote_not_found);
    }
    
}
