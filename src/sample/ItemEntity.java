package sample;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import javafx.beans.property.SimpleBooleanProperty;


/**
 * Created by sandi on 2/13/2016.
 */
public class ItemEntity extends GenericJson {


    @Key("_id")
    private String id;
    @Key
    private String unit;
    @Key
    private String walmartHyvee;
    @Key
    private String usFoods;
    @Key
    private String roma;
    @Key
    private String count;
    @Key
    private String selectedPrice;



    public ItemEntity(){}  //GenericJson classes must have a public empty constructor

    public ItemEntity(String name, String unit, String walmartHyvee, String usfoods, String roma, String count, String selectedPrice) {
        this.id = name;
        this.unit = unit;
        this.walmartHyvee = walmartHyvee;
        this.usFoods = usfoods;
        this.roma = roma;
        this.count = count;
        this.selectedPrice= selectedPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUnit(){
        return unit;
    }

    public void setUnit(String unit){
        this.unit=unit;
    }

    public String getWalmartHyvee() {
        return walmartHyvee;
    }

    public void setWalmartHyvee(String walmartHyvee) {
        this.walmartHyvee = walmartHyvee;
    }

    public String getUsFoods() {
        return usFoods;
    }

    public void setUsFoods(String usFoods) {
        this.usFoods = usFoods;
    }

    public String getRoma() {
        return roma;
    }

    public void setRoma(String roma) {
        this.roma = roma;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

//    public String getTestTest() {
//        return testTest;
//    }
//
//    public void setTestTest(String testTest) {
//        this.testTest = testTest;
//    }


    private SimpleBooleanProperty checked = new SimpleBooleanProperty(false);
    // other columns here

    public SimpleBooleanProperty checkedProperty() {
        return this.checked;
    }

    public java.lang.Boolean getChecked() {
        return this.checkedProperty().get();
    }

    public void setChecked(final java.lang.Boolean checked) {
        this.checkedProperty().set(checked);
    }

    public String getSelectedPrice() {
        return selectedPrice;
    }

    public void setSelectedPrice(String selectedPrice) {
        this.selectedPrice = selectedPrice;
    }
}