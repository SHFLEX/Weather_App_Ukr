package com.oldd6;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveInfo extends JFrame implements ActionListener{
    JLabel l;
    JButton b, b2, b3, b4, b5;
    static DefaultTableModel tm;

    SaveInfo(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1280, 920);
        this.setLayout(new GridLayout(0,1));
        this.getContentPane().setBackground(Color.WHITE);
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());

        l = new JLabel("Зберегти всю інформацію про погоду у форматі");
        l.setFont(new Font("Roboto", Font.BOLD, 48));
        l.setForeground(Color.LIGHT_GRAY);
        l.setVerticalAlignment(JLabel.TOP);
        l.setHorizontalAlignment(JLabel.CENTER);
        this.add(l);

        b = new JButton("XLSX");
        b.addActionListener(this);
        b.setFont(new Font("Roboto", Font.BOLD, 48));
        b.setForeground(Color.LIGHT_GRAY);
        b.setBackground(Color.BLACK);
        this.add(b);

        b2 = new JButton("CSV");
        b2.addActionListener(this);
        b2.setFont(new Font("Roboto", Font.BOLD, 48));
        b2.setForeground(Color.LIGHT_GRAY);
        b2.setBackground(Color.BLACK);
        this.add(b2);

        b3 = new JButton("JSON");
        b3.addActionListener(this);
        b3.setFont(new Font("Roboto", Font.BOLD, 48));
        b3.setForeground(Color.LIGHT_GRAY);
        b3.setBackground(Color.BLACK);
        this.add(b3);

        b4 = new JButton("TXT");
        b4.addActionListener(this);
        b4.setFont(new Font("Roboto", Font.BOLD, 48));
        b4.setForeground(Color.LIGHT_GRAY);
        b4.setBackground(Color.BLACK);
        this.add(b4);

        b5 = new JButton("PDF");
        b5.addActionListener(this);
        b5.setFont(new Font("Roboto", Font.BOLD, 48));
        b5.setForeground(Color.LIGHT_GRAY);
        b5.setBackground(Color.BLACK);
        this.add(b5);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b){
            try {
                createDTM();
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("data");

                XSSFRow headerSheet = sheet.createRow(0);
                for (int i = 0; i < tm.getColumnCount(); i++) {
                    headerSheet.createCell(i).setCellValue(tm.getColumnName(i));
                }

                for (int r = 0; r < tm.getRowCount(); r++) {
                    XSSFRow row = sheet.createRow(r + 1);
                    for (int c = 0; c < tm.getColumnCount(); c++) {
                        row.createCell(c).setCellValue(String.valueOf(tm.getValueAt(r, c)));
                    }
                }

                wb.write(new FileOutputStream(Show.path+"Погода_Інфо.xlsx"));
                wb.close();
            }catch(IOException ex){ex.printStackTrace();}
        }else if(e.getSource()==b2){
            createDTM();
            export(tm, "Погода_Інфо.csv", "csv");
        }else if(e.getSource()==b3){
            createDTM();
            export(tm, "Погода_Інфо.json", "json");
        }else if(e.getSource()==b4){
            createDTM();
            export(tm, "Погода_Інфо.txt", "txt");
        }else if(e.getSource()==b5){
            try{
                createDTM();
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(Show.path+"Погода_Інфо.pdf"));
                doc.open();

                PdfPTable pt = new PdfPTable(tm.getColumnCount());

                for(int i = 0; i<tm.getColumnCount(); i++){
                    pt.addCell(tm.getColumnName(i));
                }

                for(int r = 0; r<tm.getRowCount(); r++){
                    for(int c = 0; c<tm.getColumnCount(); c++){
                        pt.addCell(String.valueOf(tm.getValueAt(r,c)));
                    }
                }

                doc.add(pt);
                doc.close();
            }catch(FileNotFoundException ex){ex.printStackTrace();}
        }
    }
    public static void createDTM(){
        tm = new DefaultTableModel(new Object[]{
                "Дата",
                "Стан повітря",
                "Температура",
                "Шв. вітру"
        },0);
        Session s = Main.sf.openSession();
        List<Weather> wths = s.createQuery("from Weather", Weather.class).list();
        for(Weather wth : wths){
            tm.addRow(new Object[]{wth.getDate(), wth.getAir(), wth.getTemp(), wth.getWind()});
        }
    }
    public static void export(DefaultTableModel tm, String nf, String type){
        try(PrintWriter prw = new PrintWriter(Show.path+nf)){
            if(type.equals("json")) prw.println("[");
            for(int i = 0; i<tm.getRowCount(); i++){
                if(type.equals("json")) prw.print("{");
                for(int j = 0; j<tm.getColumnCount(); j++){
                    Object val = tm.getValueAt(i,j);
                    if(type.equals("csv")) prw.print(val+(j<tm.getColumnCount()-1? "," : ""));
                    else if(type.equals("txt")){
                        prw.print(val+"\t");
                    }else if(type.equals("json")) {
                        prw.print("\"" + tm.getColumnName(j) + "\":\"" + val + "\"");
                        if (j < tm.getColumnCount() - 1) prw.print(",");
                    }
                }
                if(type.equals("json")){
                    prw.print("}");
                    if(i<tm.getRowCount()-1) prw.print(",\n");
                }else prw.println();
            }
            if(type.equals("json")) prw.println("\n]");
        }catch(FileNotFoundException ex){ex.printStackTrace();}
    }
}