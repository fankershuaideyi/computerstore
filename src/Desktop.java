public class Desktop extends computer{
    public void setMsg(String category,String type,String Id,String brand,String cpu,String MS,String SSD,String price){
     setCategory(category);
     setBrand(brand);
     setType(type);
     setCpu(cpu);
     setID(Id);
     setPrice(price);
     setSSD(SSD);
     setMemorySize(MS);

    }
    public void adddata(Object[] tabledata){
        tabledata[0]=getCategory();
        tabledata[1]=getType();
        tabledata[2]=getID();
        tabledata[3]=getBrand();
        tabledata[4]=getCpu();
        tabledata[5]=getPrice();
    }
}
