package com.oldd6;

import com.formdev.flatlaf.FlatClientProperties;
import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class Show extends JFrame implements ActionListener{
    public static final String path = System.getProperty("user.home")+"/Downloads/";
    JLabel l;
    JButton b, b2, b3, b4;
    JFreeChart jchart;

    Show(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(960, 720);
        this.setLayout(new GridLayout(0,1));
        this.getContentPane().setBackground(Color.WHITE);
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());

        l = new JLabel("Інформація про погоду");
        l.setFont(new Font("Roboto", Font.BOLD, 48));
        l.setForeground(Color.BLACK);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.TOP);
        this.add(l);

        b = new JButton("Показати темп. графік погоди");
        b.addActionListener(this);
        b.setFont(new Font("Roboto", Font.BOLD, 48));
        b.setForeground(Color.LIGHT_GRAY);
        b.setBackground(Color.BLACK);
        this.add(b);

        b2 = new JButton("Показати всі дані");
        b2.addActionListener(this);
        b2.setFont(new Font("Roboto", Font.BOLD, 48));
        b2.setForeground(Color.LIGHT_GRAY);
        b2.setBackground(Color.BLACK);
        this.add(b2);

        b3 = new JButton("Зберегти темп. графік погоди");
        b3.addActionListener(this);
        b3.setFont(new Font("Roboto", Font.BOLD, 48));
        b3.setForeground(Color.LIGHT_GRAY);
        b3.setBackground(Color.BLACK);
        this.add(b3);

        b4 = new JButton("Зберегти всі дані");
        b4.addActionListener(this);
        b4.setFont(new Font("Roboto", Font.BOLD, 48));
        b4.setForeground(Color.LIGHT_GRAY);
        b4.setBackground(Color.BLACK);
        this.add(b4);

        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b){
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
            ChartPanel cp = new ChartPanel(jchart);
            JOptionPane.showMessageDialog(null, cp, "Лінійний графік погоди", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getSource()==b2){
            DefaultTableModel tm = new DefaultTableModel(new Object[]{
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
            JTable table = new JTable(tm);
            table.putClientProperty(FlatClientProperties.STYLE, "rowHeight: 35");

            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Таблиця погоди",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getSource()==b3){
            new SaveChart();
        }
        else if(e.getSource()==b4){
            new SaveInfo();
        }
    }
}