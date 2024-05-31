/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package v1_tm_projectmanagement;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import org.apache.derby.drda.NetworkServerControl;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Joshua i3
 */
public class Login extends Connect_ip {

//    private final String url = "jdbc:derby://localhost:1527/TM_DB";
//    private String location = "";
    private String url = "";
    private final String user = "josh";
    private final String password = "1234";
    
//    ImageIcon icon = new ImageIcon("C:\\Users\\Joshua i3\\Desktop\\PMS.png"); // Provide the absolute path to the image
    
        
    
    public Login() {
        initComponents();
        // Check this out
//        Image image = icon.getImage();
//        setIconImage(image);
//        JOptionPane.showMessageDialog(null, "Loading");
        setIconImage();

        Location_of_TaskDB();
        Add_new_ip();
        Timer_check();
        remove_empt();
        int leftPadding = 10;
        Border paddingBorder = BorderFactory.createEmptyBorder(0, leftPadding, 0, 0);
        Border compoundBorder = BorderFactory.createCompoundBorder(username.getBorder(), paddingBorder);
        Border compoundBorder2 = BorderFactory.createCompoundBorder(pass.getBorder(), paddingBorder);
        username.setBorder(compoundBorder);
        pass.setBorder(compoundBorder2);
    }

    public void open_ip(){
        String url_ip = "jdbc:derby://"+ip_add.getText()+":1527/"+location.getText();
//        String url_ip = "jdbc:derby://192.168.1.19:1527/"+location.getText();
        url = url_ip;
        NetworkServerControl obj;
        try {
            obj = new NetworkServerControl(InetAddress.getByName(ip_add.getText()),1527);
            obj.start(null);
            obj.ping();
            url = url_ip;
            System.out.println("Connection success");
//                JOptionPane.showMessageDialog(this, "Derby server started successfully!!!.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Additional code for server startup actions
            } catch (UnknownHostException ex) {
                System.out.println("Connection failed");
                System.out.println(url_ip);
                next_ip();
        }catch (Exception ex) {
            System.out.println("Connection failed 2"); next_ip();
        }
    }
    
    public void Timer_check(){
        int delay = 2000; // delay in milliseconds
          Timer timer = new Timer(delay, e -> {
              read_ip();
              open_ip();
            ((Timer) e.getSource()).restart();
         });
         timer.start();
    }

    private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files (x86)\\Pms_f\\Documentation\\PMS.png"));
    }
    
    class jPanelGradient extends JPanel {
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            
            Color color1 = new Color(204,204,204);
            Color color2 = new Color(255,255,255);
            GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
            
        }
    }
    
 
    
    // Can be use for getting location
    
//    public void read_ip(){
////      Database_file df = new Database_file();
////      String get = df.path.getText();
//        ////// Location of IP
////        String filePath2 = "IP_Config\\IP_V19G.txt";
//        String filePath2 = "C:\\Users\\Joshua i3\\Desktop\\Task_DB\\TM_DB\\log\\logfolder\\address.txt";
//        String fPath = filePath2.replace("\\", "\\\\");
////        String[] pathParts = get2.split("\\\\");
//        StringBuilder parentPathBuilder = new StringBuilder();
//        // Append the first two parts (up to the second folder level)
////        for (int i = 0; i < 4; i++) {
////            parentPathBuilder.append(pathParts[i]);
////            parentPathBuilder.append("\\");
////        }
//        String parentPath = parentPathBuilder.toString();
//        String filepath = parentPath + filePath2;
//        try {
//                BufferedReader reader = new BufferedReader(new FileReader(filepath));
//                    String line1 = reader.readLine();
//                        reader.close();
//            ip_add.setText(line1);
//            } catch (IOException e) {
//            }
//    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frame = new javax.swing.JFrame();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 =  new javax.swing.JPanel(){

            public void paintComponent(Graphics g){

                ImageIcon im = new ImageIcon(getClass().getResource("/IMG/designeducation.jpg"));
                Image i = im.getImage();

                g.drawImage(i, 0 ,0, this.getSize().width, this.getSize().height, this);
            }

        };
        newPanel5 = new v1_tm_projectmanagement.NewPanel();
        newPanel1 = new v1_tm_projectmanagement.NewPanel();
        jLabel2 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        newPanel4 = new v1_tm_projectmanagement.NewPanel();

        jButton2.setText("set ip");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Database");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(location)
                        .addComponent(ip_add, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ip_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ProjectMS - v1.0.0.0");
        setLocation(new java.awt.Point(220, 40));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(235, 235, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newPanel5.setBackground(new java.awt.Color(153, 204, 255));
        newPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/tyc_160.png"))); // NOI18N

        pass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pass.setForeground(new java.awt.Color(102, 102, 102));
        pass.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pass.setMinimumSize(new java.awt.Dimension(64, 12));
        pass.setPreferredSize(new java.awt.Dimension(64, 37));
        pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passFocusLost(evt);
            }
        });
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 153, 204));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        username.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        username.setForeground(new java.awt.Color(102, 102, 102));
        username.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        username.setMargin(new java.awt.Insets(22, 26, 22, 26));
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFocusLost(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Username");

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Password");

        jSeparator1.setForeground(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout newPanel1Layout = new javax.swing.GroupLayout(newPanel1);
        newPanel1.setLayout(newPanel1Layout);
        newPanel1Layout.setHorizontalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(pass, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newPanel1Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(90, 90, 90))
        );
        newPanel1Layout.setVerticalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        //jLabel2.setVisible(false);
        username.setMargin(new Insets(2,2,2,2));

        newPanel5.add(newPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(newPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 360, 460));

        newPanel4.setBackground(new java.awt.Color(251, 168, 36));
        newPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(newPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 360, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        open_ip();
        try {
            String usern = username.getText().toUpperCase();
            String passw = String.valueOf(pass.getPassword());
            boolean incorrect = true;
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT USER_NAME, PASSWORD, USER_TYPE, U_ID FROM JOSH.USERS";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String Value = resultSet.getString("USER_NAME");
                String Value2 = resultSet.getString("PASSWORD");
                String Value3 = resultSet.getString("USER_TYPE");
                String Value4 = resultSet.getString("U_ID");
                if(usern.equals(Value.toUpperCase()) && passw.equals(Value2)){
                    incorrect = false;
                    A_W a = new A_W();
                    a.setVisible(true);
                    a.nameer.setText(Value);
                    a.utype.setText(Value3);
                    a.admin_user.setText(Value4);
                    //
                    
                    //
                    Login.this.dispose();
                    log = false;
                }
                // Use the lastValue variable as needed
            }
            if(incorrect == true){
                username.setText(null);
                pass.setText(null);
                JOptionPane.showMessageDialog(null, "Wrong username or password");
            }
            
            // Close the resources
            resultSet.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       location.setText(location.getText());
       ip_add.setText(ip_add.getText());
        open_ip();
//       TEMPLATE rl = new TEMPLATE();
//       rl.loca.setText(url);
//       rl.p_name.setText(n);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void locationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_locationActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(null);
        File c = fc.getSelectedFile();
        String filename = c.getPath();
        String file = c.getName();  
        String fPath = filename.replace("\\", "\\\\");
        
        location.setText(filename);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void passFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFocusGained
       
    }//GEN-LAST:event_passFocusGained

    private void passFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFocusLost
    
    }//GEN-LAST:event_passFocusLost

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost

//        if(username.getText().equals(""))
//        {
//            username.setText("Username");
//            username.setForeground(new Color(153,153,153));
//        }
    }//GEN-LAST:event_usernameFocusLost

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained
//        if(username.getText().equals("Username"))
//        {
//            username.setText("");
//            username.setForeground(new Color(153,153,153));
//        }
    }//GEN-LAST:event_usernameFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try{
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        FlatRobotoFont.install();
        
        FlatMacLightLaf.setup();
//            FlatLightLaf.setup();
//            FlatDarkLaf.setup();
        }catch (Exception e){
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame frame;
    public static final javax.swing.JTextField ip_add = new javax.swing.JTextField();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    public static final javax.swing.JTextField location = new javax.swing.JTextField();
    private v1_tm_projectmanagement.NewPanel newPanel1;
    private v1_tm_projectmanagement.NewPanel newPanel4;
    private v1_tm_projectmanagement.NewPanel newPanel5;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
