import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class computer {
    private String Category;
    private String Type;
    private String ID;
    private String Brand;
    private String cpu;
    private String price;
    private String MemorySize;
    private String SSD;
    private String ScreenSize;

    public void setType(String type) {
        Type = type;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public String getID() {
        return ID;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return Brand;
    }

    public String getCategory() {
        return Category;
    }

    public String getCpu() {
        return cpu;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getMemorySize() {
        return MemorySize;
    }

    public String getScreenSize() {
        return ScreenSize;
    }

    public String getSSD() {
        return SSD;
    }

    public void setMemorySize(String memorySize) {
        MemorySize = memorySize;
    }

    public void setScreenSize(String screenSize) {
        ScreenSize = screenSize;
    }

    public void setSSD(String SSD) {
        this.SSD = SSD;
    }
    public computer someone(HashMap<Integer,computers> computers, String value){
        computer computer=new computer();
        for(Map.Entry<Integer,computers>item:computers.entrySet()){
            computers val=item.getValue();

        if(val.desktop.getCategory()!=null){
            if(Objects.equals(val.desktop.getID(),value)){
                computer=val.desktop;
            }
        }
        if(val.laptop.getCategory()!=null){
            if(Objects.equals(val.laptop.getID(),value)){
                computer=val.laptop;
            }
        }
        if(val.tablet.getCategory()!=null){
            if(Objects.equals(val.tablet.getID(),value)){
                computer=val.tablet;
            }
        }}
        return computer;
    }
}
