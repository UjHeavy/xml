
package xml;

public class data {
    private static int count=0;
    private Integer id;
    private String name;
    private Integer price;
    private Boolean inStorage;

    public data() {
        count++;
    }

    public data(String name, Integer price, Boolean inStorage)
    {
        count++;
        this.id = count;
        this.name = name;
        this.price = price;
        this.inStorage = inStorage;
    }
    public data(Integer id, String name, Integer price, Boolean inStorage)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStorage = inStorage;
    }

    public static int getCount() 
    {
        return count;
    }

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Integer getPrice() 
    {
        return price;
    }

    public Boolean getInStorage() 
    {
        return inStorage;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setPrice(Integer price) 
    {
        this.price = price;
    }

    public void setInStorage(Boolean inStorage) 
    {
        this.inStorage = inStorage;
    }
    
}
