package com.oldd6;
import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaveChart extends JFrame implements ActionListener {
    JLabel l;
    JButton b, b2;
    static JFreeChart jchart;


    SaveChart(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(890, 360);
        this.setLayout(new GridLayout(0,1));
        this.getContentPane().setBackground(Color.BLACK);
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());

        l = new JLabel("Зберегти графік погоди у форматі");
        l.setFont(new Font("Roboto", Font.BOLD, 48));
        l.setForeground(Color.LIGHT_GRAY);
        l.setVerticalAlignment(JLabel.TOP);
        l.setHorizontalAlignment(JLabel.CENTER);
        this.add(l);

        b = new JButton("PNG");
        b.addActionListener(this);
        b.setFont(new Font("Roboto", Font.BOLD, 48));
        b.setForeground(Color.LIGHT_GRAY);
        b.setBackground(Color.BLACK);
        this.add(b);

        b2 = new JButton("JPEG");
        b2.addActionListener(this);
        b2.setFont(new Font("Roboto", Font.BOLD, 48));
        b2.setForeground(Color.LIGHT_GRAY);
        b2.setBackground(Color.BLACK);
        this.add(b2);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b){
            createChart(".png");
        }
        else if(e.getSource()==b2){
            createChart(".jpeg");
        }
    }

    public static void createChart(String f){
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        Session s = Main.sf.openSession();
        List<Weather> wths = s.createQuery("from Weather", Weather.class).list();
        for(Weather wth : wths) data.addValue(wth.getTemp(), "Температура", wth.getDate());
        jchart = ChartFactory.createLineChart(
                "Температура",
                "Дані",
                "ПОГОДА",
                data
        );
        try{
            ChartUtils.saveChartAsJPEG(new File(Show.path+"Темп_графік"+f), jchart, 1920, 1080);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
