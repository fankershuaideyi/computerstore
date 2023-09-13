//Yifan Qiu :21012688
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Random r = new Random();
        for (int i = 0; i<10;i++){
            System.out.println(r.nextInt(9000));
        }

    }
    public static void login( ArrayList<ISmanager> iSmanagers,  HashMap<Integer,computers>computers){
        JFrame jf=new JFrame("Computer products Management System");
        jf.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button=new JButton(new ImageIcon("login.png"));
        jf.setBounds(400,300,550,300);

        jf.add(button);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jd=new JDialog(jf,"Sales Person login");
                jd.setLayout(new FlowLayout(FlowLayout.LEFT));
                jd.setBounds(450,350,400,200);
                JLabel label1=new JLabel("Username:");
                JTextField username=new JTextField("",30);
                JLabel label2=new JLabel("Password :");
                JPasswordField pwd=new JPasswordField("",30);
                JButton button1=new JButton("Login");
                JButton button2=new JButton("Cancel");
                Box b1=Box.createHorizontalBox();


                jd.add(label1);
                jd.add(username);
                jd.add(label2);
                jd.add(pwd);
                jd.add(b1);
                b1.add(Box.createHorizontalStrut(80));
                b1.add(button1);
                b1.add(Box.createHorizontalStrut(70));
                b1.add(button2);
                b1.add(Box.createVerticalGlue());


                jd.setResizable(false);
                button1.setSize(40,20);
                button2.setSize(40,20);
                button1.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Username=username.getText();
                        String password=pwd.getText();
                        for (int i=0;i<iSmanagers.size();i++){
                            if(Objects.equals(Username, iSmanagers.get(i).getUsername())&&Objects.equals(password, iSmanagers.get(i).getPassword())){
                                jf.dispose();
                                addjfream(computers,iSmanagers.get(i).isIsmanager(),iSmanagers);
                            }
                        }
                    }
                });
                button2.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jd.dispose();
                    }
                });
                jd.setVisible(true);
                jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });

        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void read_file(HashMap<Integer,computers>computers,int count) {
        try {
            InputStream inputStream = new FileInputStream("computers.txt");
            try {
                Scanner scanner=new Scanner(inputStream, StandardCharsets.UTF_8);
                while (scanner.hasNext()){
                    computers com=new computers();
                    String line=scanner.nextLine();
                    String[] tokens=line.split(",");
                    if(Objects.equals(tokens[0],"Desktop PC")){
                        com.desktop.setMsg(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7]);
                        computers.put(count,com);
                        count++;
                    }
                    if(Objects.equals(tokens[0],"Laptop")){
                        com.laptop.setMsg(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8]);
                        computers.put(count,com);
                        count++;
                    }
                    if(Objects.equals(tokens[0],"Tablet")){
                        com.tablet.setMsg(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6]);
                        computers.put(count,com);
                        count++;
                    }
                } }finally {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addjfream( HashMap<Integer,computers>computers,boolean ismanager, ArrayList<ISmanager> iSmanagers){
        boolean both1,both2;
         JFrame jf2=new JFrame("Computer products Management System:");
         jf2.setLayout(new BorderLayout());
         jf2.setBounds(400,300,600,600);
         JMenuBar bar=new JMenuBar();
         JMenu menu=new JMenu("Browse Products");
         GridLayout layout=new GridLayout(2,2);
         JPanel panel=new JPanel(new FlowLayout(FlowLayout.LEFT));
         bar.setLayout(new BorderLayout());
         JLabel label=new JLabel("Computer Category:");
         JLabel label1=new JLabel("Computer Type: " );
         label.setVisible(true);
         label1.setVisible(true);
         label.setEnabled(false);
         label1.setEnabled(false);
         bar.add(menu);
         JComboBox box=new JComboBox();
         box.addItem("All");
         box.addItem("Desktop PC");
         box.addItem("Tablet");
         box.addItem("Laptop");
         box.setEnabled(true);
         JComboBox TypeBox=new JComboBox();
         TypeBox.addItem(" ");
         TypeBox.addItem("Gaming");
         TypeBox.addItem("Home & Study");
         TypeBox.addItem("Business");
         TypeBox.addItem("Compact");
         TypeBox.addItem("Thin & Light");
         TypeBox.addItem("Android");
         TypeBox.addItem("Apple");
         TypeBox.addItem("Windows");
         String[][] tabledata=new String[computers.size()][6];
        tabledata=cHangeTable(computers);
         String[] name={"Category","Type","ID","Brand","CPU Family","Price($)"};
         JTable table=new JTable();
         DefaultTableModel tableModel=new DefaultTableModel(tabledata,name){
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false;
             }
         };
         table.setModel(tableModel);
         JScrollPane pane=new JScrollPane(table);

         JButton exit1=new JButton(new ImageIcon("exit.png"));

        exit1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf2.dispose();
                login(iSmanagers,computers);
            }
        });
        panel.add(bar);
        panel.add(label);
        panel.add(box);
        panel.add(label1);
        panel.add(TypeBox);
         jf2.add(panel,BorderLayout.NORTH);
         jf2.add(pane);
         jf2.add(exit1,BorderLayout.SOUTH);

        box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int exixt=0;
                int index=box.getSelectedIndex();
                String content=box.getSelectedItem().toString();
                String content1=TypeBox.getSelectedItem().toString();
                String[][] Object = new String[computers.size()][6];
                String[][] tabledata1=new String[computers.size()][6];
                tabledata1=cHangeTable(computers);
                if(e.getStateChange() == ItemEvent.SELECTED){
                if(index!=0){
                    for(int i=0;i<computers.size();i++){
                        if(Objects.equals(tabledata1[i][0],content)){
                            if(Objects.equals(tabledata1[i][1],content1) || Objects.equals(content1," "))
                            {
                            for(int j=0;j<6;j++){
                                Object[exixt][j]=tabledata1[i][j];
                            }
                            exixt++;
                            }
                        }
                    }
                    String[][] current=new String[exixt][6];

                    for(int i=0;i<exixt;i++){
                        for (int j=0;j<6;j++){
                            current[i][j]=Object[i][j];
                        }
                    }
                    TableModel tableModel=new DefaultTableModel(current,name);
                    table.setModel(tableModel);
                }
            }
                else {
                    TableModel tableModel=new DefaultTableModel(tabledata1,name);
                    table.setModel(tableModel);
                }
            }
        });
        TypeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index=TypeBox.getSelectedIndex();
                String[][] Object = new String[computers.size()][6];
                int exixt=0;
                String[][] tabledata1=new String[computers.size()][6];
                tabledata1=cHangeTable(computers);
                String content=TypeBox.getSelectedItem().toString();
                String content1=box.getSelectedItem().toString();
                if(e.getStateChange() == ItemEvent.SELECTED){
                    String content2=TypeBox.getSelectedItem().toString();
                        for(int i=0;i<computers.size();i++){
                            if(Objects.equals(tabledata1[i][1],content)){
                                if(Objects.equals(tabledata1[i][0],content1)||Objects.equals(content1,"All")){
                                for(int j=0;j<6;j++){
                                    Object[exixt][j]=tabledata1[i][j];
                                }
                                exixt++;}
                            }
                        }
                        String[][] current=new String[exixt][6];

                        for(int i=0;i<exixt;i++){
                            for (int j=0;j<6;j++){
                                current[i][j]=Object[i][j];
                            }
                        }
                        TableModel tableModel=new DefaultTableModel(current,name);
                        table.setModel(tableModel);


            }
            }
        });

        //监听表格
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int r= table.getSelectedRow();
                //得到选中的单元格的值，表格中都是字符串

                String value= table.getValueAt(r, 2).toString();
               addnewjd(jf2,computers,value,ismanager,table);

            }
        });


        jf2.setVisible(true);
         jf2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void addnewjd(JFrame jf2,HashMap<Integer,computers>computers,String value,boolean isManger,JTable table){
        JDialog jd=new JDialog(jf2,"Computer Products Management System:");
        jd.setBounds(450,150,360,600);     //别改360，剩下的随意
        jd.setLayout(new BorderLayout());
        GridLayout gridLayout1=new GridLayout(18,2);
        JPanel panel1=new JPanel(gridLayout1);
        JLabel label=new JLabel("Model ID:");
        JTextField ID=new JTextField("",50);
        JLabel label1=new JLabel("Category:");
        JComboBox box=new JComboBox();
        box.addItem("Desktop PC");
        box.addItem("Tablet");
        box.addItem("Laptop");
        JLabel label2=new JLabel("Type: ");
        JComboBox TypeBox=new JComboBox();
        TypeBox.addItem("Gaming");
        TypeBox.addItem("Home & Study");
        TypeBox.addItem("Business");
        TypeBox.addItem("Compact");
        TypeBox.addItem("Thin&Light");
        TypeBox.addItem("Android");
        TypeBox.addItem("Apple");
        TypeBox.addItem("Windows");
        JLabel label3=new JLabel("Brand:");
        JTextField brand=new JTextField("",10);
        JLabel label4=new JLabel("CPU Family:");
        JTextField Cpu=new JTextField("",10);
        JLabel label5=new JLabel("Memory Size:");
        JTextField MS=new JTextField("",10);
        JLabel label6=new JLabel("SSD Capacity");
        JTextField SSD=new JTextField("",10);
        JLabel label7=new JLabel("Screen Size:");
        JTextField SS=new JTextField("",10);
        JLabel label8=new JLabel("Price:");
        JTextField price=new JTextField("",10);
        computer computer=new computer();
        computer=computer.someone(computers,value);
        String existId=computer.getID();
        ID.setText(computer.getID());
        brand.setText(computer.getBrand());
        Cpu.setText(computer.getCpu());
        MS.setText(computer.getMemorySize());
        SSD.setText(" ");
        SSD.setText(computer.getSSD());
        SS.setText(computer.getScreenSize());
        price.setText(computer.getPrice());
        box.setSelectedItem(computer.getCategory());
        TypeBox.setSelectedItem(computer.getType());
        box.setEnabled(isManger);
        TypeBox.setEnabled(isManger);
        label.setEnabled(false);label1.setEnabled(false);
        label2.setEnabled(isManger);label3.setEnabled(false);
        label4.setEnabled(false);label5.setEnabled(false);
        label6.setEnabled(false);label7.setEnabled(false);
        label8.setEnabled(false);
        ID.setEnabled(isManger);box.setEnabled(isManger);TypeBox.setEnabled(isManger);brand.setEnabled(isManger);Cpu.setEnabled(isManger);Cpu.setEnabled(isManger);MS.setEnabled(isManger);SSD.setEnabled(isManger);SS.setEnabled(isManger);SSD.setEnabled(isManger);price.setEnabled(isManger);

        panel1.add(label);
        panel1.add(ID);
        panel1.add(label1);
        panel1.add(box);
        panel1.add(label2);
        panel1.add(TypeBox);
        panel1.add(label3);
        panel1.add(brand);
        panel1.add(label4);
        panel1.add(Cpu);
       panel1.add(label5);
       panel1.add(MS);
        panel1.add(label6);
        panel1.add(SSD);
       panel1.add(label7);
       panel1.add(SS);
       panel1.add(label8);
       panel1.add(price);
        jd.add(panel1,BorderLayout.CENTER);
//        jd.add(label);
//        jd.add(ID);
//        jd.add(label1);
//        jd.add(box);
//        jd.add(label2);
//        jd.add(TypeBox);
//        jd.add(label3);
//        jd.add(brand);
//        jd.add(label4);
//        jd.add(Cpu);
//        jd.add(label5);
//        jd.add(MS);
//        jd.add(label6);
//        jd.add(SSD);
//        jd.add(label7);
//        jd.add(SS);
//        jd.add(label8);
//        jd.add(price);
        JButton add=new JButton("ADD");add.setEnabled(isManger);
        JButton update=new JButton("Update");update.setEnabled(isManger);
        JButton delete=new JButton("Delete");delete.setEnabled(isManger);
        JButton Clear=new JButton("Clear");Clear.setEnabled(isManger);
        JButton exit=new JButton(new ImageIcon("exit.png"));
        add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a=ID.getText().toString();
                computers computer1=new computers();

                if(Objects.equals(existId,a)){
                    javax.swing.JOptionPane.showMessageDialog(null,"Can't add an existing ID");
                }
                else {
                     computer1=computer1.add(box.getSelectedItem().toString(),TypeBox.getSelectedItem().toString(),a,brand.getText().toString(),Cpu.getText().toString(),MS.getText().toString(),SSD.getText().toString(),SS.getText().toString(),price.getText().toString());
                     computers.put(computers.size(),computer1);
                    String[][] tabledata=new String[computers.size()][6];
                    tabledata= cHangeTable(computers);
                    String[] name1={"Category","Type","ID","Brand","CPU Family","Price($)"};
                    TableModel tableModel=new DefaultTableModel(tabledata,name1);
                    table.setModel(tableModel);

                    javax.swing.JOptionPane.showMessageDialog(null,"The record for the computer add successfully");
                    jd.dispose();
                }
            }
        });
        update.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a=ID.getText().toString();
                computers computer1=new computers();
                if(!Objects.equals(existId,a)){
                    javax.swing.JOptionPane.showMessageDialog(null,"Can't update an Unexisting ID");
                }
                else {
                    int r=0;
                    for (Map.Entry<Integer,computers>item:computers.entrySet()){
                        Integer key=item.getKey();
                        computers val=item.getValue();
                        if(val.desktop.getCategory()!=null){
                            if(Objects.equals(val.desktop.getID(),ID)){
                                r=key;
                            }
                        }
                        if(val.laptop.getCategory()!=null){
                            if(Objects.equals(val.laptop.getCategory(),ID)){
                                r=key;
                            }
                        }
                        if(val.tablet.getCategory()!=null){
                            if(Objects.equals(val.tablet.getCategory(),ID)){
                                r=key;
                            }
                        }
                    }
                    computer1=computer1.add(box.getSelectedItem().toString(),TypeBox.getSelectedItem().toString(),a,brand.getText().toString(),Cpu.getText().toString(),MS.getText().toString(),SSD.getText().toString(),SS.getText().toString(),price.getText().toString());
                    computers.put(r,computer1);
                    String[][] tabledata=new String[computers.size()][6];
                    tabledata= cHangeTable(computers);
                    String[] name={"Category","Type","ID","Brand","CPU Family","Price($)"};
                    TableModel tableModel=new DefaultTableModel(tabledata,name);
                    table.setModel(tableModel);
                    javax.swing.JOptionPane.showMessageDialog(null,"The record for the computer update successfully");
                    jd.dispose();
                }
            }
        });
        delete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=ID.getText().toString();
                int r=0;
                for (Map.Entry<Integer,computers>item:computers.entrySet()){
                    Integer key=item.getKey();
                    computers val=item.getValue();
                    if(val.desktop.getCategory()!=null){
                        if(Objects.equals(val.desktop.getID(),ID)){
                            r=key;
                        }
                    }
                    if(val.laptop.getCategory()!=null){
                        if(Objects.equals(val.laptop.getCategory(),ID)){
                            r=key;
                        }
                    }
                    if(val.tablet.getCategory()!=null){
                        if(Objects.equals(val.tablet.getCategory(),ID)){
                            r=key;
                        }
                    }
                }
                javax.swing.JOptionPane.showMessageDialog(null,"It has been deleted");
               for (Iterator<Map.Entry<Integer,computers>>it=computers.entrySet().iterator();it.hasNext();){
                   Map.Entry<Integer,computers>item=it.next();
                   Integer key=item.getKey();
                   if(key==r){
                       it.remove();
                   }
               }
                String[][] tabledata=new String[computers.size()][6];
                tabledata= cHangeTable(computers);
                String[] name1={"Category","Type","ID","Brand","CPU Family","Price($)"};
                TableModel tableModel=new DefaultTableModel(tabledata,name1);
                table.setModel(tableModel);
                jd.dispose();
            }
        });
        Clear.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SS.setText("                ");
                SSD.setText("               ");
                MS.setText("                ");
                Cpu.setText("               ");
                ID.setText("                ");
                brand.setText("             ");
                price.setText("             ");
            }
        });
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jd.dispose();
            }
        });

  GridLayout gridLayout=new GridLayout(2,2);
  JPanel panel=new JPanel(gridLayout);
  panel.add(add);
  panel.add(update);
  panel.add(delete);
  panel.add(Clear);
  jd.add(panel,BorderLayout.WEST);
        jd.add(exit,BorderLayout.SOUTH);
        jd.setVisible(true);
        jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public static String[][] cHangeTable(HashMap<Integer,computers>computers){
        String[][] tabledata=new String[computers.size()][6];

        int i=0;
        for(Map.Entry<Integer,computers>item:computers.entrySet()){
           computers val=item.getValue();
            if(val.desktop.getCategory()!=null){
                Desktop com=new Desktop();
                com=val.desktop;
                com.adddata(tabledata[i]);
                i++;
            }
            if(val.tablet.getCategory()!=null){
                Tablet com=new Tablet();
                com=val.tablet;
                com.adddata(tabledata[i]);
                i++;
            }
            if(val.laptop.getCategory()!=null){
                Laptop com=new Laptop();
                com=val.laptop;
                com.adddata(tabledata[i]);
                i++;
            }
        }
        return tabledata;
    }

}
