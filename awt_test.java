// package edu.javacourse.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class awt_test
{
    public static void createGUI()
    {
        JFrame frame = new JFrame("Фрейм по-русски");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel("А по-русски шаришь?");
        frame.getContentPane().add(label);
        
        frame.setPreferredSize(new Dimension(300, 300));
        

        frame.pack();
        frame.setVisible(true);          
   }
   
   public static void main(String[] args)
   {
      JFrame.setDefaultLookAndFeelDecorated(true);
//      javax.swing.SwingUtilities.invokeLater(new Runnable() {
//           public void run() {
               SimpleDb m = new SimpleDb();
               m.testDatabase();
               createGUI();
//           }
//       });
   }
}


class SimpleDb 
{
 //   public static void main(String[] args) {
 //       SimpleDb m = new SimpleDb();
 //       m.testDatabase();
 //   }
 
    public void testDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
//            DriverManager.register(new org.postgresql.Driver());
            String url = "jdbc:postgresql://localhost:5432/dom";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users order by id;");
                while (rs.next()) {
//                  String str = rs.getString("id") + ":" + rs.getString(2);
                  String str = rs.getString("id") + ":" + rs.getString("name") + " : " + rs.getString("appart") + " : " + rs.getString("sip_user");
                    System.out.println("Contact:" + str);
                }
                rs.close();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

