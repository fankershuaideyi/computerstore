import java.util.HashMap;
import java.util.Objects;

public class computers {
    public Desktop desktop=new Desktop();
    public Laptop laptop=new Laptop();
    public Tablet tablet=new Tablet();
public int a;
    public Desktop getDesktop() {
        return desktop;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setDesktop(Desktop desktop) {
        this.desktop = desktop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Tablet getTablet() {
        return tablet;
    }

    public void setTablet(Tablet tablet) {
        this.tablet = tablet;
    }
    public computers add(String category,String type,String Id,String brand,String cpu,String MS,String SSD,String SS,String price){
           computers computers=new computers();
        if(Objects.equals(category,"Desktop PC")){
               computers.desktop.setMsg(category, type,Id, brand, cpu, MS, SSD,price);
        }
        if(Objects.equals(category,"Laptop")){
         computers.laptop.setMsg(category,type,Id,brand,cpu,MS,SSD,SS,price);
        }
        if(Objects.equals(category,"Tablet")){
          computers.tablet.setMsg(category,type,Id,brand,cpu,SS,price);
        }
            return computers;
    }

}
