package ibf2022.batch3.assessment.csf.orderbackend.models;

import java.io.StringReader;
import java.util.Date;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class PizzaOrderRedis {
    
    private String orderId = "not set";
	private Date date;
    private Float total;
	private String name;
	private String email;

    public PizzaOrderRedis(String orderId, Date date, Float total, String name, String email) {
        this.orderId = orderId;
        this.date = date;
        this.total = total;
        this.name = name;
        this.email = email;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PizzaOrderRedis [orderId=" + orderId + ", date=" + date + ", total=" + total + ", name=" + name
                + ", email=" + email + "]";
    }


    public static JsonObject toJSON(String json){
        JsonReader r = Json.createReader(new StringReader(json));
        return r.readObject();
    }
    
    public JsonObject toJSON(){

        return Json.createObjectBuilder()
                .add("orderId", this.getOrderId())
                .add("date", this.getDate().toString())
                .add("total", this.getTotal())
                .add("name", this.getName())
                .build();
    }

    

    

}
