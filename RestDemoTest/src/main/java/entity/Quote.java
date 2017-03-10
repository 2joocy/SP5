
package entity;

public class Quote
{
    private String quote;
    private int id;

    public Quote(int id, String quote)
    {
        this.quote = quote;
        this.id = id;
    }

    public Quote()
    {
    }

    public Quote(String quote)
    {
        this.quote = quote;
    }
    
    public String getQuote()
    {
        return quote;
    }

    public void setQuote(String quote)
    {
        this.quote = quote;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    
}
