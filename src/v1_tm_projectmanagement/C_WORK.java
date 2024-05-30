/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package v1_tm_projectmanagement;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.derby.drda.NetworkServerControl;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.TableColumnModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
/**
 *
 * @author Joshua i3
 */
public class C_WORK extends javax.swing.JFrame {

//    JTextField f_get = new JTextField("localhost");
    
//    Database_file df = new Database_file();
//    String get = df.path.getText();
    
//    String get = "C:\\Users\\Joshua i3\\Desktop\\Task_DB";
//    String sql9 = "SELECT * FROM JOSH.ACCOUNT";
    private static int getColumnIndexByName(JTable table, String columnName) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(columnName)) {
                return i; // Return the index if column name matches
            }
        }
        return -1; // Return -1 if column name is not found
    }
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    // IP ADDRESS
//   private final String url = "jdbc:derby://localhost:1527/TM_DB";
//    String url = "jdbc:derby://192.168.1.6:1527/\\\\DESKTOP-7CU9UG7\\Task_DB\\TM_DB";
//    private final String url = "jdbc:derby://localhost:1527/TM_DB";
    String url = "";
    private final String user = "josh";
    private final String password = "1234";
    
   Login rl = new Login();
   
   String name2 = rl.location.getText();
   String ip = rl.ip_add.getText();
//    String name = "\\\\DESKTOP-7CU9UG7\\Task_DB2\\TM_DB";
//    String ip = "192.168.1.6";
    
    public C_WORK() {
        initComponents();
        setIconImage();
        open_ip();
        
        tables();
        tables2();
        renderTable1();
        swingleft();

    }
    
    private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files (x86)\\Pms_f\\Documentation\\edit.png"));
    }
    
    public void open_ip(){
        NetworkServerControl obj;
        try {
            obj = new NetworkServerControl(InetAddress.getByName(ip),1527);
            obj.start(null);
            obj.ping();
//            System.out.println("Connection success");
            url = "jdbc:derby://"+ip+":1527/"+name2;
//            JOptionPane.showMessageDialog(this, "Derby server started successfully!!!.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Additional code for server startup actions
            } catch (UnknownHostException ex) {
                System.out.println("Connection failed");
            }catch (Exception ex) {
                System.out.println("Connection failed 2");
            }
        
    }
    
    public void renderTable1(){
        
        int num = getColumnIndexByName(jTable1, "#");
        int num2 = getColumnIndexByName(jTable2, "#");
        
        // Hide column (Istorbo)
        TableColumnModel columnModel = jTable1.getColumnModel();
        TableColumnModel columnModel2 = jTable2.getColumnModel();
        
        TableColumn column = columnModel.getColumn(num);
        column.setMinWidth(0);
        column.setMaxWidth(100);
        column.setPreferredWidth(60);
        column.setResizable(false);
        
        TableColumn column2 = columnModel2.getColumn(num2);
        column2.setMinWidth(0);
        column2.setMaxWidth(100);
        column2.setPreferredWidth(60);
        column2.setResizable(false);
    }
    
    public void swingleft(){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    }
    
    public void tables(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); 
        model.setRowCount(0);
//        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
//        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
//        jTable1.getColumnModel().getColumn(1).setPreferredWidth(180);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.setDefaultRenderer(String.class, centerRenderer);
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT USER_NAME FROM JOSH.USERS";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int rows = jTable1.getRowCount();
                String Value = resultSet.getString("USER_NAME");
                model.addRow(new Object[]{rows + 1,Value}); 
                // Use the lastValue variable as needed
            }
            jTable1.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e){
                        int selectedRow = jTable1.getSelectedRow();
                        if (selectedRow != -1) {
                            edit_btn_1.setEnabled(true);
                            Object rowData = jTable1.getValueAt(selectedRow, 1);
                            System.out.println(rowData);
                        }
                    }});
            
            // Close the resources
            resultSet.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void tables2(){
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel(); 
        model.setRowCount(0);
//        jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
//        jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
//        jTable2.getColumnModel().getColumn(1).setPreferredWidth(180);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable2.setDefaultRenderer(String.class, centerRenderer);
        jTable2.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT COMPANY FROM JOSH.COMPANY";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int rows = jTable2.getRowCount();
                String Value = resultSet.getString("COMPANY");
                model.addRow(new Object[]{rows + 1,Value}); 
                // Use the lastValue variable as needed
            }
            jTable2.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e){
                        int selectedRow = jTable2.getSelectedRow();
                        if (selectedRow != -1) {
                            edit_btn_2.setEnabled(true);
                            Object rowData = jTable2.getValueAt(selectedRow, 1);
                            System.out.println(rowData);
                        }
                    }});
            
            // Close the resources
            resultSet.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // EXCEL UPLOAD
    private void readExcelData() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                Workbook workbook = new XSSFWorkbook(fileChooser.getSelectedFile());
                Sheet sheet = workbook.getSheetAt(1); // assuming first sheet

                int userColumnIndex = -1, companyColumnIndex = -1, statusColumnIndex = -1,
                        priorityColumnIndex = -1, typeColumnIndex = -1, projNoColumnIndex = -1;

                // Find column indexes based on column headers
                Row headerRow = sheet.getRow(0);
                for (Cell cell : headerRow) {
                    String cellValue = cell.getStringCellValue();
                    if (cellValue.equalsIgnoreCase("User")) {
                        companyColumnIndex = cell.getColumnIndex();
                    } 
                }

                // Read data based on column indexes
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    String company = row.getCell(companyColumnIndex).getStringCellValue();
                    try {
                    //Class.forName("org.postgresql.Driver");
                    Connection conn = DriverManager.getConnection(url, user, password);

                //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
                    String sql = "INSERT INTO JOSH.USERS(U_NO,U_ID,USER_TYPE, USER_NAME, PASSWORD)VALUES (?, ?, ?, ?, ?)";
                    String sql2 = "SELECT * FROM JOSH.USERS WHERE U_NO = (SELECT MAX(U_NO) FROM JOSH.USERS)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);

                    ResultSet resultSet = stmt2.executeQuery();
                    int num = 0;
                    if (resultSet.next()) {
                        int lastValue = resultSet.getInt("U_NO");
                        num = lastValue;
                        System.out.println("Last value of U_NO column: " + lastValue);
                        // Use the lastValue variable as needed
                    }

                    // Set parameters for the PreparedStatement
                    stmt.setInt(1, num + 1);
                    stmt.setString(2, "U_"+String.valueOf(num + 1));
                    stmt.setString(3, String.valueOf("User"));
                    stmt.setString(4, String.valueOf(company));
                    stmt.setString(5, String.valueOf("123"));

                    // Execute the INSERT statement
                    int rowsAffected = stmt.executeUpdate();



                    if (rowsAffected > 0) {
                        System.out.println("Data inserted successfully!");
                    }

                    // Close the resources
                    resultSet.close();
                    stmt2.close();
                    stmt.close();
                    conn.close();

                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                    System.out.println("Company: " + company );
                }

                workbook.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Add_new_user = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        Edit_User = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        newPanel1 = new v1_tm_projectmanagement.NewPanel();
        username_edit = new javax.swing.JTextField();
        password_edit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        usertype_edit = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        user_id = new javax.swing.JLabel();
        Add_company = new javax.swing.JFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        Edit_Company = new javax.swing.JFrame();
        newPanel2 = new v1_tm_projectmanagement.NewPanel();
        company_id = new javax.swing.JTextField();
        company_name = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        delete_btn_1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        edit_btn_1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        edit_btn_2 = new javax.swing.JButton();
        delete_btn_2 = new javax.swing.JButton();

        Add_new_user.setIconImage(null);
        Add_new_user.setLocation(new java.awt.Point(290, 150));
        Add_new_user.setResizable(false);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "USERTYPE", "USERNAME", "PASSWORD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.setShowGrid(true);
        jScrollPane3.setViewportView(jTable3);

        jButton3.setText("Add row");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Ok");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Cancel");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setText("Add New Users");

        jButton7.setText("Remove");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Add_new_userLayout = new javax.swing.GroupLayout(Add_new_user.getContentPane());
        Add_new_user.getContentPane().setLayout(Add_new_userLayout);
        Add_new_userLayout.setHorizontalGroup(
            Add_new_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
            .addGroup(Add_new_userLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Add_new_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Add_new_userLayout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addGap(16, 16, 16))
                    .addGroup(Add_new_userLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        Add_new_userLayout.setVerticalGroup(
            Add_new_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_new_userLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Add_new_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        Add_new_user.setIconImage(null);

        Edit_User.setLocation(new java.awt.Point(290, 150));
        Edit_User.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        newPanel1.setBackground(new java.awt.Color(230, 226, 226));

        jLabel3.setText("Usertype");

        jLabel4.setText("Username");

        jLabel5.setText("Password");

        usertype_edit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));

        javax.swing.GroupLayout newPanel1Layout = new javax.swing.GroupLayout(newPanel1);
        newPanel1.setLayout(newPanel1Layout);
        newPanel1Layout.setHorizontalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usertype_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(username_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(password_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        newPanel1Layout.setVerticalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usertype_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton8.setText("Cancel");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel6.setText("Edit user:");

        user_id.setText("ID");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(user_id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addComponent(jLabel6)
                    .addComponent(newPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton8)
                    .addComponent(user_id))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Edit_UserLayout = new javax.swing.GroupLayout(Edit_User.getContentPane());
        Edit_User.getContentPane().setLayout(Edit_UserLayout);
        Edit_UserLayout.setHorizontalGroup(
            Edit_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edit_UserLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Edit_UserLayout.setVerticalGroup(
            Edit_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Add_company.setLocation(new java.awt.Point(290, 150));
        Add_company.setResizable(false);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Company"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.setShowGrid(true);
        jScrollPane4.setViewportView(jTable4);

        jButton9.setText("Add row");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Ok");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Cancel");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel7.setText("Add Company");

        jButton12.setText("Remove");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Add_companyLayout = new javax.swing.GroupLayout(Add_company.getContentPane());
        Add_company.getContentPane().setLayout(Add_companyLayout);
        Add_companyLayout.setHorizontalGroup(
            Add_companyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
            .addGroup(Add_companyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Add_companyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Add_companyLayout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11)
                        .addGap(16, 16, 16))
                    .addGroup(Add_companyLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        Add_companyLayout.setVerticalGroup(
            Add_companyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_companyLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Add_companyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Edit_Company.setLocation(new java.awt.Point(290, 150));
        Edit_Company.setResizable(false);

        newPanel2.setBackground(new java.awt.Color(234, 229, 229));

        company_id.setEnabled(false);

        jLabel9.setText("ID:");

        jLabel10.setText("Company name:");

        javax.swing.GroupLayout newPanel2Layout = new javax.swing.GroupLayout(newPanel2);
        newPanel2.setLayout(newPanel2Layout);
        newPanel2Layout.setHorizontalGroup(
            newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(company_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(company_name))
                .addGap(21, 21, 21))
        );
        newPanel2Layout.setVerticalGroup(
            newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(company_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(company_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton13.setText("Save");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Cancel");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel11.setText("Edit company name:");

        javax.swing.GroupLayout Edit_CompanyLayout = new javax.swing.GroupLayout(Edit_Company.getContentPane());
        Edit_Company.getContentPane().setLayout(Edit_CompanyLayout);
        Edit_CompanyLayout.setHorizontalGroup(
            Edit_CompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edit_CompanyLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addContainerGap(622, Short.MAX_VALUE))
            .addGroup(Edit_CompanyLayout.createSequentialGroup()
                .addGroup(Edit_CompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Edit_CompanyLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14))
                    .addGroup(Edit_CompanyLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(newPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Edit_CompanyLayout.setVerticalGroup(
            Edit_CompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edit_CompanyLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Edit_CompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Masters Unit");
        setFocusTraversalPolicyProvider(true);
        setLocation(new java.awt.Point(290, 90));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Users"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        delete_btn_1.setBackground(new java.awt.Color(255, 51, 51));
        delete_btn_1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete_btn_1.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn_1.setText("Delete");
        delete_btn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btn_1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 204, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        edit_btn_1.setBackground(new java.awt.Color(0, 153, 204));
        edit_btn_1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_btn_1.setForeground(new java.awt.Color(255, 255, 255));
        edit_btn_1.setText("Edit");
        edit_btn_1.setEnabled(false);
        edit_btn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btn_1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(edit_btn_1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(delete_btn_1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(edit_btn_1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_btn_1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Users", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Companies"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setShowGrid(true);
        jScrollPane2.setViewportView(jTable2);

        jButton2.setBackground(new java.awt.Color(0, 204, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        edit_btn_2.setBackground(new java.awt.Color(0, 153, 204));
        edit_btn_2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_btn_2.setForeground(new java.awt.Color(255, 255, 255));
        edit_btn_2.setText("Edit");
        edit_btn_2.setEnabled(false);
        edit_btn_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btn_2ActionPerformed(evt);
            }
        });

        delete_btn_2.setBackground(new java.awt.Color(255, 51, 51));
        delete_btn_2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete_btn_2.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn_2.setText("Delete");
        delete_btn_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btn_2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(edit_btn_2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addComponent(delete_btn_2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(edit_btn_2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_btn_2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Companies", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Assuming 'table' is your JTable
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        int columnToRetrieve = 1; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve2 = 2; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve3 = 3; // Column index (0-based) for the column you want to retrieve

        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, columnToRetrieve);
            Object value2 = model.getValueAt(i, columnToRetrieve2);
            Object value3 = model.getValueAt(i, columnToRetrieve3);
            System.out.println("Value at row " + i + ",specific column : " + value);
            // Perform your logic with 'value' here
        

        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

        //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
            String sql = "INSERT INTO JOSH.USERS(U_NO,U_ID,USER_TYPE, USER_NAME, PASSWORD)VALUES (?, ?, ?, ?, ?)";
            String sql2 = "SELECT * FROM JOSH.USERS WHERE U_NO = (SELECT MAX(U_NO) FROM JOSH.USERS)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            
            ResultSet resultSet = stmt2.executeQuery();
            int num = 0;
            if (resultSet.next()) {
                int lastValue = resultSet.getInt("U_NO");
                num = lastValue;
                System.out.println("Last value of U_NO column: " + lastValue);
                // Use the lastValue variable as needed
            }
            
            // Set parameters for the PreparedStatement
            stmt.setInt(1, num + 1);
            stmt.setString(2, "U_"+String.valueOf(num + 1));
            stmt.setString(3, String.valueOf(value));
            stmt.setString(4, String.valueOf(value2));
            stmt.setString(5, String.valueOf(value3));

            // Execute the INSERT statement
            int rowsAffected = stmt.executeUpdate();


    
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            }

            // Close the resources
            resultSet.close();
            stmt2.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        }
        tables();
//        try{
//                String host = "jdbc:derby://"+f_get.getText()+":1527/"+get+"\\Ben";
//            Connection con = DriverManager.getConnection(host, "josh", "1234");
//            PreparedStatement stmt_a = con.prepareStatement(sql9, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs_a = stmt_a.executeQuery();
//
////                int aa = rs_a.getRow();
////                rs_a.absolute(aa);
//                while(rs_a.next()){
//                    int a = rs_a.getRow();
//                    String userid = rs_a.getString("USER_ID");
//                        rs_a.absolute(a);
//                        
//                        String a1 = rs_a.getString("FULL_NAME");
//                        String b = rs_a.getString("INITIALS");
//                        String c = rs_a.getString("USER_NAME");
//                        String d = rs_a.getString("PASSWORD");
//                        String e = rs_a.getString("POSITION");
//                        
//                        
//                        rs_a.updateString("FULL_NAME", fullname.getText().toUpperCase());
//                        rs_a.updateString("INITIALS", initials.getText());
//                        rs_a.updateString("USER_NAME", username.getText().toUpperCase());
//                        rs_a.updateString("PASSWORD", password.getText());
//                        rs_a.updateString("POSITION", (String) position.getSelectedItem());
//                        rs_a.updateRow();
//                        //                        Refresh_RS_STMT_a();
//                        
//                    
//                }
//
//                rs_a.close();
//                stmt_a.close();
//                con.close();
//            }catch(SQLException er){}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int rows = jTable3.getRowCount();
        // Table model alignment
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel(); 
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(10);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable3.setDefaultRenderer(String.class, centerRenderer);
        jTable3.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        model.addRow(new Object[]{rows + 1, "None", " ? "," ? "});
        
        TableColumn sportColumn = jTable3.getColumnModel().getColumn(1);
        JComboBox comboBox = new JComboBox();
//        comboBox.addItem("None");
        comboBox.addItem("User");
        comboBox.addItem("Admin");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Add_new_user.pack();
        Add_new_user.setVisible(true);
        //        Add_company.setIconImage(null);
        ImageIcon icon = new ImageIcon(getClass().getResource("C:\\Program Files (x86)\\Pms_f\\Documentation\\edit.png"));
        Add_company.setIconImage(icon.getImage());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel(); 
        if(jTable3.getSelectedRow() != -1) {
               // remove selected row from the model
               model.removeRow(jTable3.getSelectedRow());
//               JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    Add_new_user.dispose();
// Assuming you have established a connection to your database and have a PreparedStatement object
//String sql = "SELECT * FROM JOSH.USERS WHERE U_NO = (SELECT MAX(U_NO) FROM JOSH.USERS)";
//
//
//try {
//    Connection conn = DriverManager.getConnection(url, user, password);
//    PreparedStatement statement = conn.prepareStatement(sql);
//    ResultSet resultSet = statement.executeQuery();
//
//    if (resultSet.next()) {
//        int lastValue = resultSet.getInt("U_NO");
//        System.out.println("Last value of U_NO column: " + lastValue);
//        // Use the lastValue variable as needed
//    }
//
//    resultSet.close();
//    statement.close();
//} catch (SQLException e) {
//    e.printStackTrace();
//}

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Example condition for the row to update based on U_ID
            //int userIdToUpdate = 123; // Replace with the specific user ID

            String sql = "UPDATE JOSH.USERS SET USER_NAME = ?, PASSWORD = ?, USER_TYPE = ? WHERE U_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            // Set parameters for the update statement
            stmt.setString(1, username_edit.getText());
            stmt.setString(2, password_edit.getText());
            stmt.setString(3, String.valueOf(usertype_edit.getSelectedItem()));
            stmt.setString(4, user_id.getText());
//            stmt.setInt(4, userIdToUpdate);

            // Execute the update statement
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                //System.out.println("Update successful for user with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "Update successful!");
            } else {
                //System.out.println("No user found with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "No user found with \"user_name\" ");
            }

            // Close the resources
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        tables();
        Edit_User.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void edit_btn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btn_1ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_1.setEnabled(true);
            Object rowData = jTable1.getValueAt(selectedRow, 1);
            Edit_User.pack();
            Edit_User.setVisible(true);
            
            try {
                Connection conn = DriverManager.getConnection(url, user, password);

                // Example condition for the row to update based on U_ID
                int userIdToUpdate = 123; // Replace with the specific user ID

                String sql = "SELECT * FROM JOSH.USERS WHERE USER_NAME = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, String.valueOf(rowData));
            
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    String Value = resultSet.getString("USER_TYPE");
                    String Value2 = resultSet.getString("USER_NAME");
                    String Value3 = resultSet.getString("PASSWORD");
                    String Value4 = resultSet.getString("U_ID");
                    
                    usertype_edit.setSelectedItem(Value);
                    username_edit.setText(Value2);
                    password_edit.setText(Value3);
                    user_id.setText(Value4);
                    // Use the lastValue variable as needed
                }

                // Close the resources
                resultSet.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_edit_btn_1ActionPerformed

    private void delete_btn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btn_1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); 
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_1.setEnabled(true);
            Object rowData = jTable1.getValueAt(selectedRow, 1);
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the user named \"" + rowData + "\" ?",
                    "Confirmation Message", JOptionPane.YES_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection(url, user, password);

                        //String usernameToDelete = "desiredUsername"; // Replace with the specific username to delete

                        String sql = "DELETE FROM JOSH.USERS WHERE USER_NAME = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, String.valueOf(rowData)); // Set the parameter for the username to delete

                        int rowsDeleted = stmt.executeUpdate();

                        if (rowsDeleted > 0) {
                            System.out.println("User with username '" + rowData + "' deleted successfully.");
                        } else {
                            System.out.println("No user found with username '" + rowData + "'.");
                        }

                        // Close the resources
                        stmt.close();
                        conn.close();

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
        }
        tables();
    }//GEN-LAST:event_delete_btn_1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int rows = jTable4.getRowCount();
        // Table model alignment
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel(); 
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(10);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable4.setDefaultRenderer(String.class, centerRenderer);
        jTable4.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        
        model.addRow(new Object[]{rows + 1, " ? "});
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
         // Assuming 'table' is your JTable
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        int columnToRetrieve = 1; // Column index (0-based) for the column you want to retrieve
//        int columnToRetrieve2 = 2; // Column index (0-based) for the column you want to retrieve
//        int columnToRetrieve3 = 3; // Column index (0-based) for the column you want to retrieve

        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, columnToRetrieve);
//            Object value2 = model.getValueAt(i, columnToRetrieve2);
//            Object value3 = model.getValueAt(i, columnToRetrieve3);
            System.out.println("Value at row " + i + ",specific column : " + value);
            // Perform your logic with 'value' here
        

        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

        //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
            String sql = "INSERT INTO JOSH.COMPANY(C_NO,C_ID,COMPANY)VALUES (?, ?, ?)";
            String sql2 = "SELECT * FROM JOSH.COMPANY WHERE C_NO = (SELECT MAX(C_NO) FROM JOSH.COMPANY)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            
            ResultSet resultSet = stmt2.executeQuery();
            int num = 0;
            if (resultSet.next()) {
                int lastValue = resultSet.getInt("C_NO");
                num = lastValue;
                System.out.println("Last value of C_NO column: " + lastValue);
                // Use the lastValue variable as needed
            }
            
            // Set parameters for the PreparedStatement
            stmt.setInt(1, num + 1);
            stmt.setString(2, "C_"+String.valueOf(num + 1));
            stmt.setString(3, String.valueOf(value));

            // Execute the INSERT statement
            int rowsAffected = stmt.executeUpdate();


    
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            }

            // Close the resources
            resultSet.close();
            stmt2.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        }
        tables2();
        Add_company.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Add_company.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel(); 
        if(jTable4.getSelectedRow() != -1) {
               // remove selected row from the model
               model.removeRow(jTable4.getSelectedRow());
//               JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Add_company.pack();
        Add_company.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void delete_btn_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btn_2ActionPerformed
        
        
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel(); 
            int selectedRow = jTable2.getSelectedRow();
            if (selectedRow != -1) {
                edit_btn_2.setEnabled(true);
                Object rowData = jTable2.getValueAt(selectedRow, 1);
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete a company \"" + rowData + "\" ?",
                    "Confirmation Message", JOptionPane.YES_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection(url, user, password);

                        //String usernameToDelete = "desiredUsername"; // Replace with the specific username to delete

                        String sql = "DELETE FROM JOSH.COMPANY WHERE COMPANY = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, String.valueOf(rowData)); // Set the parameter for the username to delete

                        int rowsDeleted = stmt.executeUpdate();

                        if (rowsDeleted > 0) {
                            System.out.println("User with username '" + rowData + "' deleted successfully.");
                        } else {
                            System.out.println("No user found with username '" + rowData + "'.");
                        }

                        // Close the resources
                        stmt.close();
                        conn.close();

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
            tables2();
        
    }//GEN-LAST:event_delete_btn_2ActionPerformed

    private void edit_btn_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btn_2ActionPerformed
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_2.setEnabled(true);
            Object rowData = jTable2.getValueAt(selectedRow, 1);
            Edit_Company.pack();
            Edit_Company.setVisible(true);
            
            try {
                Connection conn = DriverManager.getConnection(url, user, password);

                // Example condition for the row to update based on U_ID
                int userIdToUpdate = 123; // Replace with the specific user ID

                String sql = "SELECT C_ID, COMPANY FROM JOSH.COMPANY WHERE COMPANY = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, String.valueOf(rowData));
            
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    String Value = resultSet.getString("C_ID");
                    String Value2 = resultSet.getString("COMPANY");
                    
                    company_id.setText(Value);
                    company_name.setText(Value2);
                    // Use the lastValue variable as needed
                }

                // Close the resources
                resultSet.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_edit_btn_2ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Example condition for the row to update based on U_ID
            //int userIdToUpdate = 123; // Replace with the specific user ID

            String sql = "UPDATE JOSH.COMPANY SET COMPANY = ? WHERE C_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            // Set parameters for the update statement
            stmt.setString(1, company_name.getText());
            stmt.setString(2, company_id.getText());
//            stmt.setInt(4, userIdToUpdate);

            // Execute the update statement
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                //System.out.println("Update successful for user with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "Update successful!");
            } else {
                //System.out.println("No user found with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "No user found with \"user_name\" ");
            }

            // Close the resources
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        tables2();
        Edit_Company.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Edit_User.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Add_company.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try{
//            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        FlatRobotoFont.install();
        
        FlatMacLightLaf.setup();
//            FlatLightLaf.setup();
//            FlatDarkLaf.setup();
        }catch (Exception e){
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new C_WORK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Add_company;
    private javax.swing.JFrame Add_new_user;
    private javax.swing.JFrame Edit_Company;
    private javax.swing.JFrame Edit_User;
    private javax.swing.JTextField company_id;
    private javax.swing.JTextField company_name;
    private javax.swing.JButton delete_btn_1;
    private javax.swing.JButton delete_btn_2;
    private javax.swing.JButton edit_btn_1;
    private javax.swing.JButton edit_btn_2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private v1_tm_projectmanagement.NewPanel newPanel1;
    private v1_tm_projectmanagement.NewPanel newPanel2;
    private javax.swing.JTextField password_edit;
    private javax.swing.JLabel user_id;
    private javax.swing.JTextField username_edit;
    private javax.swing.JComboBox<String> usertype_edit;
    // End of variables declaration//GEN-END:variables
}
