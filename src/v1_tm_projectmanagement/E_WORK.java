/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package v1_tm_projectmanagement;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.derby.drda.NetworkServerControl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Joshua i3
 */
public class E_WORK extends javax.swing.JFrame {

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    // CellRenderer
    DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component rendererComponent = super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
            
            // Set background color for "water" to blue and "land" to green
            String[] parts = value.toString().split(",");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            int c = Integer.parseInt(parts[2]);
            if (value != null) {
                rendererComponent.setBackground(new Color(a,b,c));
            }
            else {
                rendererComponent.setBackground(Color.WHITE); // Default color
            }
            
            return rendererComponent;
        }
            
    };
    
    private static int getColumnIndexByName(JTable table, String columnName) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(columnName)) {
                return i; // Return the index if column name matches
            }
        }
        return -1; // Return -1 if column name is not found
    }
    
    class AlternatingRowColorRenderer extends DefaultTableCellRenderer {
    // Define the colors for the alternating rows
    private final Color lightGrayColor = new Color(240, 240, 240);
    private final Color whiteColor = Color.WHITE;
    private final Color selectedColor = new Color(0,153,0);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        // Let the default cell renderer handle the rest
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Set the background color based on the row index
        if (isSelected) {
            component.setBackground(selectedColor);
        } else if (row % 2 == 0) {
            component.setBackground(lightGrayColor);
        } else {
            component.setBackground(whiteColor);
        }

        return component;
    }
}
    
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
    
    public E_WORK() {
        initComponents();
        setIconImage();
        open_ip();
        priority();
        Location();
        Agency();
        Status();
        TypeList();
        renderTable1();
        swingleft();
        jTable1.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());
    }
    
    private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files (x86)\\Pms_f\\Documentation\\edit.png"));
    }
    
    public void renderTable1(){
        
        
        int id = getColumnIndexByName(jTable1, "Id");
        int id2 = getColumnIndexByName(jTable2, "Id");
        int id3 = getColumnIndexByName(jTable3, "Id");
        int id4 = getColumnIndexByName(jTable4, "Id");
        
        int num = getColumnIndexByName(jTable1, "#");
        int num2 = getColumnIndexByName(jTable2, "#");
        int num3 = getColumnIndexByName(jTable3, "#");
        int num4 = getColumnIndexByName(jTable4, "#");
        int num9 = getColumnIndexByName(jTable9, "#");
        
        // Hide column (Istorbo)
        TableColumnModel columnModel = jTable1.getColumnModel();
        TableColumnModel columnModel2 = jTable2.getColumnModel();
        TableColumnModel columnModel3 = jTable3.getColumnModel();
        TableColumnModel columnModel4 = jTable4.getColumnModel();
        
        TableColumnModel columnModelnum = jTable1.getColumnModel();
        TableColumnModel columnModelnum2 = jTable2.getColumnModel();
        TableColumnModel columnModelnum3 = jTable3.getColumnModel();
        TableColumnModel columnModelnum4 = jTable4.getColumnModel();
        TableColumnModel columnModelnum9 = jTable9.getColumnModel();
        
        TableColumn column = columnModel.getColumn(id);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);
        
        TableColumn column2 = columnModel2.getColumn(id2);
        column2.setMinWidth(0);
        column2.setMaxWidth(0);
        column2.setPreferredWidth(0);
        column2.setResizable(false);
        
        TableColumn column3 = columnModel3.getColumn(id3);
        column3.setMinWidth(0);
        column3.setMaxWidth(0);
        column3.setPreferredWidth(0);
        column3.setResizable(false);
        
        TableColumn column4 = columnModel4.getColumn(id4);
        column4.setMinWidth(0);
        column4.setMaxWidth(0);
        column4.setPreferredWidth(0);
        column4.setResizable(false);
        
        TableColumn columnnum = columnModelnum.getColumn(num);
        columnnum.setMinWidth(0);
        columnnum.setMaxWidth(60);
        columnnum.setPreferredWidth(40);
        columnnum.setResizable(false);
        TableColumn columnnum2 = columnModelnum2.getColumn(num2);
        columnnum2.setMinWidth(0);
        columnnum2.setMaxWidth(60);
        columnnum2.setPreferredWidth(40);
        columnnum2.setResizable(false);
        TableColumn columnnum3 = columnModelnum3.getColumn(num3);
        columnnum3.setMinWidth(0);
        columnnum3.setMaxWidth(60);
        columnnum3.setPreferredWidth(40);
        columnnum3.setResizable(false);
        TableColumn columnnum4 = columnModelnum4.getColumn(num4);
        columnnum4.setMinWidth(0);
        columnnum4.setMaxWidth(60);
        columnnum4.setPreferredWidth(40);
        columnnum4.setResizable(false);
        TableColumn columnnum9 = columnModelnum9.getColumn(num9);
        columnnum9.setMinWidth(0);
        columnnum9.setMaxWidth(60);
        columnnum9.setPreferredWidth(40);
        columnnum9.setResizable(false);
    }
    
    public void swingleft(){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        jTable3.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        jTable4.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
    }
    
    public void open_ip(){
        NetworkServerControl obj;
        try {
            obj = new NetworkServerControl(InetAddress.getByName(ip),1527);
            obj.start(null);
            obj.ping();
//            System.out.println("Connection success");
            url = "jdbc:derby://"+ip+":1527/"+name2;
//                JOptionPane.showMessageDialog(this, "Derby server started successfully!!!.", "Success", JOptionPane.INFORMATION_MESSAGE);
// Additional code for server startup actions
            } catch (UnknownHostException ex) {
                System.out.println("Connection failed");
                
        }catch (Exception ex) {
            System.out.println("Connection failed 2");
        }
        
    }
    
    public void colorex(){

        DefaultTableModel model = (DefaultTableModel) jTable5.getModel(); 

        // Set background color for a specific cell (row 2, column 1)
        int rowToColor = 1; // Index 1 (2nd row)
        int columnToColor = 2; // Index 0 (1st column)
        Color cellBackgroundColor = Color.PINK; // Set your desired background color
        // Create a custom renderer for the specific cell
        TableCellRenderer cellRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value != null ? value.toString() : "");
                try {
                    //Class.forName("org.postgresql.Driver");
                    Connection conn = DriverManager.getConnection(url, user, password);

                    String sql = "SELECT P_COLOR, PRIORITY FROM JOSH.D_1";
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    ResultSet resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        int rows = resultSet.getRow();
                        String Values = resultSet.getString("P_COLOR");
                        String Values2 = resultSet.getString("PRIORITY");
                        String[] parts = Values.toString().split(",");
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                        // Set the background color for the specified cell
                        if (row == rows - 1 && column == columnToColor) {
                            cell.setBackground(new Color(a,b,c));
                            cell.setOpaque(true);
                        }
                }
                    // Close the resources
                    resultSet.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return cell;
            }
        };
        // Set the custom renderer for the specific cell
        jTable5.getColumnModel().getColumn(columnToColor).setCellRenderer(cellRenderer);
        // Repaint the cell with the updated background color
    }
    
    public void priority(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); 
        model.setRowCount(0);
//        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(180);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.setDefaultRenderer(String.class, centerRenderer);
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Create a custom renderer for the specific cell
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT D_1_ID, PRIORITY, P_COLOR FROM JOSH.D_1";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int rows = jTable1.getRowCount();
                String Value = resultSet.getString("D_1_ID");
                String Value2 = resultSet.getString("PRIORITY");
                String Value3 = resultSet.getString("P_COLOR");
                model.addRow(new Object[]{rows + 1,Value, Value2, ""}); 
                // Use the lastValue variable as needed
                // Set the background color for the specified cell
            }
            jTable1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) {
                        edit_btn_1.setEnabled(true);
                        Object rowData = jTable1.getValueAt(selectedRow, 2);
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
        int columnToColor = 3;
        TableCellRenderer cellRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value != null ? value.toString() : "");
                try {
                    //Class.forName("org.postgresql.Driver");
                    Connection conn = DriverManager.getConnection(url, user, password);

                    String sql = "SELECT P_COLOR FROM JOSH.D_1";
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    ResultSet resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        int rows = resultSet.getRow();
                        String Values = resultSet.getString("P_COLOR");
                        String[] parts = Values.toString().split(",");
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                        // Set the background color for the specified cell
                        if (row == rows - 1 && column == columnToColor) {
                            cell.setBackground(new Color(a,b,c));
                            cell.setOpaque(true);
//                            cell.setText(String.valueOf(a));
//                            if(cell.getText().equals("51")){cell.setText("BINGO");}
                        }
                }
                    // Close the resources
                    resultSet.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return cell;
            }
        };
        jTable1.getColumnModel().getColumn(columnToColor).setCellRenderer(cellRenderer);
        
    }
    
    public void Location(){
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel(); 
        model.setRowCount(0);
//        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(180);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable2.setDefaultRenderer(String.class, centerRenderer);
        jTable2.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Create a custom renderer for the specific cell
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT D_2_ID, LOCATION, L_COLOR FROM JOSH.D_2";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int rows = jTable2.getRowCount();
                String Value = resultSet.getString("D_2_ID");
                String Value2 = resultSet.getString("LOCATION");
                String Value3 = resultSet.getString("L_COLOR");
                model.addRow(new Object[]{rows + 1,Value, Value2, ""}); 
                // Use the lastValue variable as needed
                // Set the background color for the specified cell
            }
            jTable2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int selectedRow = jTable2.getSelectedRow();
                    if (selectedRow != -1) {
                        edit_btn_2.setEnabled(true);
                        Object rowData = jTable2.getValueAt(selectedRow, 2);
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
        int columnToColor = 3;
        TableCellRenderer cellRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value != null ? value.toString() : "");
                try {
                    //Class.forName("org.postgresql.Driver");
                    Connection conn = DriverManager.getConnection(url, user, password);

                    String sql = "SELECT L_COLOR FROM JOSH.D_2";
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    ResultSet resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        int rows = resultSet.getRow();
                        String Values = resultSet.getString("L_COLOR");
                        String[] parts = Values.toString().split(",");
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                        // Set the background color for the specified cell
                        if (row == rows - 1 && column == columnToColor) {
                            cell.setBackground(new Color(a,b,c));
                            cell.setOpaque(true);
                        }
                }
                    // Close the resources
                    resultSet.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return cell;
            }
        };
        jTable2.getColumnModel().getColumn(columnToColor).setCellRenderer(cellRenderer);
        
    }
    
    public void Agency(){
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel(); 
        model.setRowCount(0);
//        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable3.getColumnModel().getColumn(2).setPreferredWidth(180);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable3.setDefaultRenderer(String.class, centerRenderer);
        jTable3.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Create a custom renderer for the specific cell
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT D_3_ID, AGENCY, A_COLOR FROM JOSH.D_3";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int rows = jTable3.getRowCount();
                String Value = resultSet.getString("D_3_ID");
                String Value2 = resultSet.getString("AGENCY");
                String Value3 = resultSet.getString("A_COLOR");
                model.addRow(new Object[]{rows + 1,Value, Value2, ""}); 
                // Use the lastValue variable as needed
                // Set the background color for the specified cell
            }
            jTable3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int selectedRow = jTable3.getSelectedRow();
                    if (selectedRow != -1) {
                        edit_btn_3.setEnabled(true);
                        Object rowData = jTable3.getValueAt(selectedRow, 2);
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
        int columnToColor = 3;
        TableCellRenderer cellRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value != null ? value.toString() : "");
                try {
                    //Class.forName("org.postgresql.Driver");
                    Connection conn = DriverManager.getConnection(url, user, password);

                    String sql = "SELECT A_COLOR FROM JOSH.D_3";
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    ResultSet resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        int rows = resultSet.getRow();
                        String Values = resultSet.getString("A_COLOR");
                        String[] parts = Values.toString().split(",");
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                        // Set the background color for the specified cell
                        if (row == rows - 1 && column == columnToColor) {
                            cell.setBackground(new Color(a,b,c));
                            cell.setOpaque(true);
                        }
                }
                    // Close the resources
                    resultSet.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return cell;
            }
        };
        jTable3.getColumnModel().getColumn(columnToColor).setCellRenderer(cellRenderer);
        
    }
    
    public void Status(){
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel(); 
        model.setRowCount(0);
//        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(180);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable4.setDefaultRenderer(String.class, centerRenderer);
        jTable4.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Create a custom renderer for the specific cell
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT D_4_ID, STATUS, S_COLOR FROM JOSH.D_4";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int rows = jTable4.getRowCount();
                String Value = resultSet.getString("D_4_ID");
                String Value2 = resultSet.getString("STATUS");
                String Value3 = resultSet.getString("S_COLOR");
                model.addRow(new Object[]{rows + 1,Value, Value2, ""}); 
                // Use the lastValue variable as needed
                // Set the background color for the specified cell
            }
            jTable4.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int selectedRow = jTable4.getSelectedRow();
                    if (selectedRow != -1) {
                        edit_btn_4.setEnabled(true);
                        Object rowData = jTable4.getValueAt(selectedRow, 2);
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
        int columnToColor = 3;
        TableCellRenderer cellRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value != null ? value.toString() : "");
                try {
                    //Class.forName("org.postgresql.Driver");
                    Connection conn = DriverManager.getConnection(url, user, password);

                    String sql = "SELECT S_COLOR FROM JOSH.D_4";
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    ResultSet resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        int rows = resultSet.getRow();
                        String Values = resultSet.getString("S_COLOR");
                        String[] parts = Values.toString().split(",");
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                        // Set the background color for the specified cell
                        if (row == rows - 1 && column == columnToColor) {
                            cell.setBackground(new Color(a,b,c));
                            cell.setOpaque(true);
                        }
                }
                    // Close the resources
                    resultSet.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return cell;
            }
        };
        jTable4.getColumnModel().getColumn(columnToColor).setCellRenderer(cellRenderer);
        
    }
    
    public void TypeList(){
        DefaultTableModel model = (DefaultTableModel) jTable9.getModel(); 
        model.setRowCount(0);
//        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        jTable9.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable9.getColumnModel().getColumn(1).setPreferredWidth(180);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable9.setDefaultRenderer(String.class, centerRenderer);
        jTable9.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Create a custom renderer for the specific cell
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT D_5_ID, LIST FROM JOSH.D_5";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int rows = jTable9.getRowCount();
                String Value = resultSet.getString("D_5_ID");
                String Value2 = resultSet.getString("LIST");
                model.addRow(new Object[]{rows + 1, Value2}); 
                // Use the lastValue variable as needed
                // Set the background color for the specified cell
            }
            jTable9.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int selectedRow = jTable9.getSelectedRow();
                    if (selectedRow != -1) {
                        edit_btn_5.setEnabled(true);
                        Object rowData = jTable9.getValueAt(selectedRow, 1);
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

    // EXCEL 
    private void readExcelData() {
//        JFileChooser fileChooser = new JFileChooser();
//        int returnValue = fileChooser.showOpenDialog(null);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            try {
//                Workbook workbook = new XSSFWorkbook(fileChooser.getSelectedFile());
//                Sheet sheet = workbook.getSheetAt(1); // assuming first sheet
//
//                int userColumnIndex = -1, companyColumnIndex = -1, statusColumnIndex = -1,
//                        priorityColumnIndex = -1, typeColumnIndex = -1, projNoColumnIndex = -1;
//
//                // Find column indexes based on column headers
//                Row headerRow = sheet.getRow(0);
//                for (Cell cell : headerRow) {
//                    String cellValue = cell.getStringCellValue();
//                    if (cellValue.equalsIgnoreCase("Agency")) {
//                        companyColumnIndex = cell.getColumnIndex();
//                    } 
//                }
//
//                // Read data based on column indexes
//                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                    Row row = sheet.getRow(i);
//                    String company = row.getCell(companyColumnIndex).getStringCellValue();
//                    
//                    try {
//                //Class.forName("org.postgresql.Driver");
//                Connection conn = DriverManager.getConnection(url, user, password);
//
//                //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
//                String sql = "INSERT INTO JOSH.D_5(D_5_NO, D_5_ID, LIST)VALUES (?, ?, ?)";
//                String sql2 = "SELECT * FROM JOSH.D_5 WHERE D_5_NO = (SELECT MAX(D_5_NO) FROM JOSH.D_5)";
//                String sql3 = "SELECT COUNT(*) AS count FROM JOSH.PRJNO WHERE D_5_ID = '" + String.valueOf(value) + "'";
//                String sql4 = "INSERT INTO JOSH.PRJNO(NUMBER, SLOT, D_5_ID)VALUES (?, ?, ?)";
//                
//                PreparedStatement stmt = conn.prepareStatement(sql);
//                PreparedStatement stmt2 = conn.prepareStatement(sql2);
//                PreparedStatement stmt3 = conn.prepareStatement(sql3); 
//                PreparedStatement stmt4 = conn.prepareStatement(sql4); 
//                
//                ResultSet resultSet = stmt2.executeQuery();
//                int num = 0;
//                if (resultSet.next()) {
//                    int lastValue = resultSet.getInt("D_5_NO");
//                    num = lastValue;
//                    System.out.println("Last value of D_5_NO column: " + lastValue);
//                    // Use the lastValue variable as needed
//                }
//                
//                ResultSet resultSetD_5 = stmt3.executeQuery();
//                boolean ch = true;
//                resultSetD_5.next();
//                int count = resultSetD_5.getInt("count");
//                if (count > 0) {
//                    System.out.println("There is an existing data");
//                } else {
//                    System.out.println("There's none");
//                    stmt4.setInt(1, 1);
//                    stmt4.setInt(2, 0);
//                    stmt4.setString(3, "D_5_ID_"+String.valueOf(num + 1));
//                    // Execute the INSERT statement
//                    int rowsAffected = stmt4.executeUpdate();
//                     if (rowsAffected > 0) {
//                        System.out.println("Data inserted successfully!");
//                    }
//
//                }
//
//                // Set parameters for the PreparedStatement
//                stmt.setInt(1, num + 1);
//                stmt.setString(2, "D_5_ID_"+String.valueOf(num + 1));
//                stmt.setString(3, String.valueOf(value));
//
//                // Execute the INSERT statement
//                int rowsAffected = stmt.executeUpdate();
//
//                if (rowsAffected > 0) {
//                    System.out.println("Data inserted successfully!");
//                }
//
//                // Close the resources
//                resultSet.close();
//                resultSetD_5.close();
//                stmt2.close();
//                stmt.close();
//                stmt3.close();
//                stmt4.close();
//                conn.close();
//
//            } catch (SQLException e) {
//                System.out.println("Error: " + e.getMessage());
//            }
//
//                    System.out.println("Company: " + company );
//                }
//
//                workbook.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
    }
    
    class jPanelGradient extends JPanel {
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            
            Color color1 = new Color(153,255,153);
            Color color2 = new Color(204,255,204);
            GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
            
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

        P_add_new = new javax.swing.JFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        L_add_new = new javax.swing.JFrame();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        P_edit = new javax.swing.JFrame();
        newPanel1 = new v1_tm_projectmanagement.NewPanel();
        priority_edit = new javax.swing.JTextField();
        p_color_edit = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        p_id = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        L_edit = new javax.swing.JFrame();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        newPanel2 = new v1_tm_projectmanagement.NewPanel();
        location_edit = new javax.swing.JTextField();
        l_color_edit = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        l_id = new javax.swing.JTextField();
        A_add_new = new javax.swing.JFrame();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        A_edit = new javax.swing.JFrame();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        newPanel3 = new v1_tm_projectmanagement.NewPanel();
        agency_edit = new javax.swing.JTextField();
        a_color_edit = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        a_id = new javax.swing.JTextField();
        S_add_new = new javax.swing.JFrame();
        jButton23 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        S_edit = new javax.swing.JFrame();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        newPanel4 = new v1_tm_projectmanagement.NewPanel();
        status_edit = new javax.swing.JTextField();
        s_color_edit = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        s_id = new javax.swing.JTextField();
        T_add_new = new javax.swing.JFrame();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jButton33 = new javax.swing.JButton();
        T_edit = new javax.swing.JFrame();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        newPanel5 = new v1_tm_projectmanagement.NewPanel();
        type_edit = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        t_id = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new jPanelGradient();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        delete_btn_1 = new javax.swing.JButton();
        edit_btn_1 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jPanel2 = new jPanelGradient();
        edit_btn_2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        delete_btn_2 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel3 = new jPanelGradient();
        edit_btn_3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        delete_btn_4 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel4 = new jPanelGradient();
        jButton3 = new javax.swing.JButton();
        delete_btn_3 = new javax.swing.JButton();
        edit_btn_4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jPanel5 = new jPanelGradient();
        edit_btn_5 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jButton29 = new javax.swing.JButton();
        delete_btn_5 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();

        P_add_new.setLocation(new java.awt.Point(290, 150));
        P_add_new.setResizable(false);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Priority", "Color"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable5.setGridColor(new java.awt.Color(153, 153, 153));
        jTable5.setShowHorizontalLines(true);
        jTable5.setShowVerticalLines(true);
        jScrollPane5.setViewportView(jTable5);

        jButton5.setText("Add row");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Ok");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Cancel");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel2.setText("Add new priority");

        jButton8.setText("Remove");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout P_add_newLayout = new javax.swing.GroupLayout(P_add_new.getContentPane());
        P_add_new.getContentPane().setLayout(P_add_newLayout);
        P_add_newLayout.setHorizontalGroup(
            P_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
            .addGroup(P_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_add_newLayout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7)
                        .addGap(16, 16, 16))
                    .addGroup(P_add_newLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        P_add_newLayout.setVerticalGroup(
            P_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        L_add_new.setLocation(new java.awt.Point(290, 150));
        L_add_new.setResizable(false);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Priority", "Color"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable6.setGridColor(new java.awt.Color(153, 153, 153));
        jTable6.setShowHorizontalLines(true);
        jTable6.setShowVerticalLines(true);
        jScrollPane6.setViewportView(jTable6);

        jButton9.setText("Add row");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton12.setText("Ok");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Cancel");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel7.setText("Add new location");

        jButton14.setText("Remove");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout L_add_newLayout = new javax.swing.GroupLayout(L_add_new.getContentPane());
        L_add_new.getContentPane().setLayout(L_add_newLayout);
        L_add_newLayout.setHorizontalGroup(
            L_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(L_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(L_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, L_add_newLayout.createSequentialGroup()
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13)
                        .addGap(16, 16, 16))
                    .addGroup(L_add_newLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        L_add_newLayout.setVerticalGroup(
            L_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(L_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(L_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton12)
                    .addComponent(jButton13)
                    .addComponent(jButton14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P_edit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        P_edit.setLocation(new java.awt.Point(290, 150));
        P_edit.setResizable(false);

        newPanel1.setBackground(new java.awt.Color(255, 255, 255));

        p_color_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        p_color_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p_color_editMouseClicked(evt);
            }
        });

        jLabel4.setText("Priority:");

        jLabel5.setText("Color:");

        jLabel3.setText("Id");

        p_id.setEnabled(false);

        javax.swing.GroupLayout newPanel1Layout = new javax.swing.GroupLayout(newPanel1);
        newPanel1.setLayout(newPanel1Layout);
        newPanel1Layout.setHorizontalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(p_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(priority_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(p_color_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        newPanel1Layout.setVerticalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priority_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p_color_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton10.setText("Save");
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

        jLabel6.setText("Edit priority");

        javax.swing.GroupLayout P_editLayout = new javax.swing.GroupLayout(P_edit.getContentPane());
        P_edit.getContentPane().setLayout(P_editLayout);
        P_editLayout.setHorizontalGroup(
            P_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_editLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(P_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(P_editLayout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addGap(3, 3, 3)
                        .addComponent(jButton11))
                    .addGroup(P_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(newPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        P_editLayout.setVerticalGroup(
            P_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_editLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        L_edit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        L_edit.setLocation(new java.awt.Point(290, 150));
        L_edit.setResizable(false);

        jButton15.setText("Save");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Cancel");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel8.setText("Edit location");

        newPanel2.setBackground(new java.awt.Color(230, 228, 228));

        l_color_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        l_color_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l_color_editMouseClicked(evt);
            }
        });

        jLabel9.setText("Location:");

        jLabel10.setText("Color:");

        jLabel11.setText("Id");

        l_id.setEnabled(false);

        javax.swing.GroupLayout newPanel2Layout = new javax.swing.GroupLayout(newPanel2);
        newPanel2.setLayout(newPanel2Layout);
        newPanel2Layout.setHorizontalGroup(
            newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(l_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(location_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(l_color_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        newPanel2Layout.setVerticalGroup(
            newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(location_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_color_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout L_editLayout = new javax.swing.GroupLayout(L_edit.getContentPane());
        L_edit.getContentPane().setLayout(L_editLayout);
        L_editLayout.setHorizontalGroup(
            L_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(L_editLayout.createSequentialGroup()
                .addGroup(L_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(L_editLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton15)
                        .addGap(3, 3, 3)
                        .addComponent(jButton16))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, L_editLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(L_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addComponent(newPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        L_editLayout.setVerticalGroup(
            L_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(L_editLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(L_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        A_add_new.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        A_add_new.setLocation(new java.awt.Point(290, 150));
        A_add_new.setResizable(false);

        jButton17.setText("Add row");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Ok");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Cancel");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel12.setText("Add new agency");

        jButton20.setText("Remove");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Priority", "Color"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable7.setGridColor(new java.awt.Color(153, 153, 153));
        jTable7.setShowGrid(true);
        jScrollPane7.setViewportView(jTable7);

        javax.swing.GroupLayout A_add_newLayout = new javax.swing.GroupLayout(A_add_new.getContentPane());
        A_add_new.getContentPane().setLayout(A_add_newLayout);
        A_add_newLayout.setHorizontalGroup(
            A_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(A_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(A_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A_add_newLayout.createSequentialGroup()
                        .addComponent(jButton20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17)
                        .addGap(18, 18, 18)
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton19)
                        .addGap(16, 16, 16))
                    .addGroup(A_add_newLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        A_add_newLayout.setVerticalGroup(
            A_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(A_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(jButton18)
                    .addComponent(jButton19)
                    .addComponent(jButton20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        A_edit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        A_edit.setLocation(new java.awt.Point(290, 150));
        A_edit.setResizable(false);

        jButton21.setText("Save");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("Cancel");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel13.setText("Edit agency");

        newPanel3.setBackground(new java.awt.Color(255, 255, 255));

        a_color_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        a_color_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                a_color_editMouseClicked(evt);
            }
        });

        jLabel14.setText("Agency:");

        jLabel15.setText("Color:");

        jLabel16.setText("Id");

        a_id.setEnabled(false);

        javax.swing.GroupLayout newPanel3Layout = new javax.swing.GroupLayout(newPanel3);
        newPanel3.setLayout(newPanel3Layout);
        newPanel3Layout.setHorizontalGroup(
            newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(a_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(agency_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(a_color_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        newPanel3Layout.setVerticalGroup(
            newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agency_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_color_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout A_editLayout = new javax.swing.GroupLayout(A_edit.getContentPane());
        A_edit.getContentPane().setLayout(A_editLayout);
        A_editLayout.setHorizontalGroup(
            A_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A_editLayout.createSequentialGroup()
                .addGroup(A_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(A_editLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton21)
                        .addGap(3, 3, 3)
                        .addComponent(jButton22))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, A_editLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(A_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13)
                            .addComponent(newPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        A_editLayout.setVerticalGroup(
            A_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A_editLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(A_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21)
                    .addComponent(jButton22))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        S_add_new.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        S_add_new.setLocation(new java.awt.Point(290, 150));
        S_add_new.setResizable(false);

        jButton23.setText("Remove");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Priority", "Color"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable8.setGridColor(new java.awt.Color(153, 153, 153));
        jTable8.setShowGrid(true);
        jScrollPane8.setViewportView(jTable8);

        jButton24.setText("Add row");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setText("Ok");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setText("Cancel");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jLabel17.setText("Add new status");

        javax.swing.GroupLayout S_add_newLayout = new javax.swing.GroupLayout(S_add_new.getContentPane());
        S_add_new.getContentPane().setLayout(S_add_newLayout);
        S_add_newLayout.setHorizontalGroup(
            S_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(S_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(S_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, S_add_newLayout.createSequentialGroup()
                        .addComponent(jButton23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton24)
                        .addGap(18, 18, 18)
                        .addComponent(jButton25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton26)
                        .addGap(16, 16, 16))
                    .addGroup(S_add_newLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        S_add_newLayout.setVerticalGroup(
            S_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(S_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(S_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton24)
                    .addComponent(jButton25)
                    .addComponent(jButton26)
                    .addComponent(jButton23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        S_edit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        S_edit.setLocation(new java.awt.Point(290, 150));
        S_edit.setResizable(false);

        jButton27.setText("Save");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setText("Cancel");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel18.setText("Edit status");

        newPanel4.setBackground(new java.awt.Color(255, 255, 255));

        s_color_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s_color_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                s_color_editMouseClicked(evt);
            }
        });

        jLabel19.setText("Status:");

        jLabel20.setText("Color:");

        jLabel21.setText("Id");

        s_id.setEnabled(false);

        javax.swing.GroupLayout newPanel4Layout = new javax.swing.GroupLayout(newPanel4);
        newPanel4.setLayout(newPanel4Layout);
        newPanel4Layout.setHorizontalGroup(
            newPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(newPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(s_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(newPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(status_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(s_color_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        newPanel4Layout.setVerticalGroup(
            newPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(newPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s_color_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout S_editLayout = new javax.swing.GroupLayout(S_edit.getContentPane());
        S_edit.getContentPane().setLayout(S_editLayout);
        S_editLayout.setHorizontalGroup(
            S_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, S_editLayout.createSequentialGroup()
                .addContainerGap(328, Short.MAX_VALUE)
                .addComponent(jButton27)
                .addGap(3, 3, 3)
                .addComponent(jButton28))
            .addGroup(S_editLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(S_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(S_editLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(356, 356, 356))
                    .addGroup(S_editLayout.createSequentialGroup()
                        .addComponent(newPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        S_editLayout.setVerticalGroup(
            S_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(S_editLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(S_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27)
                    .addComponent(jButton28))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        T_add_new.setLocation(new java.awt.Point(290, 150));
        T_add_new.setResizable(false);

        jButton30.setText("Ok");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setText("Cancel");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jLabel22.setText("Add new type of list");

        jButton32.setText("Remove");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable10.setGridColor(new java.awt.Color(153, 153, 153));
        jTable10.setShowGrid(true);
        jScrollPane10.setViewportView(jTable10);

        jButton33.setText("Add row");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout T_add_newLayout = new javax.swing.GroupLayout(T_add_new.getContentPane());
        T_add_new.getContentPane().setLayout(T_add_newLayout);
        T_add_newLayout.setHorizontalGroup(
            T_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(T_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(T_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, T_add_newLayout.createSequentialGroup()
                        .addComponent(jButton32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton33)
                        .addGap(18, 18, 18)
                        .addComponent(jButton30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton31)
                        .addGap(16, 16, 16))
                    .addGroup(T_add_newLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        T_add_newLayout.setVerticalGroup(
            T_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(T_add_newLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(T_add_newLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton33)
                    .addComponent(jButton30)
                    .addComponent(jButton31)
                    .addComponent(jButton32))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        T_edit.setLocation(new java.awt.Point(290, 150));
        T_edit.setResizable(false);

        jButton34.setText("Save");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setText("Cancel");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jLabel23.setText("Edit type");

        newPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setText("Type:");

        jLabel26.setText("Id");

        t_id.setEnabled(false);

        javax.swing.GroupLayout newPanel5Layout = new javax.swing.GroupLayout(newPanel5);
        newPanel5.setLayout(newPanel5Layout);
        newPanel5Layout.setHorizontalGroup(
            newPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(newPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(t_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(newPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(type_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        newPanel5Layout.setVerticalGroup(
            newPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(newPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout T_editLayout = new javax.swing.GroupLayout(T_edit.getContentPane());
        T_edit.getContentPane().setLayout(T_editLayout);
        T_editLayout.setHorizontalGroup(
            T_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(T_editLayout.createSequentialGroup()
                .addGroup(T_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(T_editLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton34)
                        .addGap(3, 3, 3)
                        .addComponent(jButton35))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, T_editLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(T_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23)
                            .addComponent(newPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        T_editLayout.setVerticalGroup(
            T_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(T_editLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(T_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton34)
                    .addComponent(jButton35))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Masters List");
        setLocation(new java.awt.Point(290, 90));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 204));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Id", "Priority", "Colors"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(0, 204, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        delete_btn_1.setBackground(new java.awt.Color(255, 51, 51));
        delete_btn_1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete_btn_1.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn_1.setText("Delete");
        delete_btn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btn_1ActionPerformed(evt);
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

        jLabel25.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Priority");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(delete_btn_1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edit_btn_1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(edit_btn_1))
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(delete_btn_1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Priority", jPanel1);

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));

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

        jScrollPane2.setPreferredSize(new java.awt.Dimension(468, 406));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Id", "Location", "Colors"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(204, 204, 204));
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

        delete_btn_2.setBackground(new java.awt.Color(255, 51, 51));
        delete_btn_2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete_btn_2.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn_2.setText("Delete");
        delete_btn_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btn_2ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("Location");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(delete_btn_2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edit_btn_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(edit_btn_2))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_btn_2)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Location", jPanel2);

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));

        edit_btn_3.setBackground(new java.awt.Color(0, 153, 204));
        edit_btn_3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_btn_3.setForeground(new java.awt.Color(255, 255, 255));
        edit_btn_3.setText("Edit");
        edit_btn_3.setEnabled(false);
        edit_btn_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btn_3ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Id", "Agency", "Colors"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.setShowHorizontalLines(true);
        jTable3.setShowVerticalLines(true);
        jScrollPane3.setViewportView(jTable3);

        jButton4.setBackground(new java.awt.Color(0, 204, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        delete_btn_4.setBackground(new java.awt.Color(255, 51, 51));
        delete_btn_4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete_btn_4.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn_4.setText("Delete");
        delete_btn_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btn_4ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Agency");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete_btn_4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edit_btn_3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)))))
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(edit_btn_3))
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_btn_4)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Agency", jPanel3);

        jPanel4.setBackground(new java.awt.Color(240, 240, 240));

        jButton3.setBackground(new java.awt.Color(0, 204, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        delete_btn_3.setBackground(new java.awt.Color(255, 51, 51));
        delete_btn_3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete_btn_3.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn_3.setText("Delete");
        delete_btn_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btn_3ActionPerformed(evt);
            }
        });

        edit_btn_4.setBackground(new java.awt.Color(0, 153, 204));
        edit_btn_4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_btn_4.setForeground(new java.awt.Color(255, 255, 255));
        edit_btn_4.setText("Edit");
        edit_btn_4.setEnabled(false);
        edit_btn_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btn_4ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Id", "Status", "Colors"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.setShowHorizontalLines(true);
        jTable4.setShowVerticalLines(true);
        jScrollPane4.setViewportView(jTable4);

        jLabel29.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Status");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edit_btn_4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(delete_btn_3))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE))
                        .addGap(19, 19, 19))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(edit_btn_4))
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_btn_3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Status", jPanel4);

        jPanel5.setBackground(new java.awt.Color(240, 240, 240));

        edit_btn_5.setBackground(new java.awt.Color(0, 153, 204));
        edit_btn_5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_btn_5.setForeground(new java.awt.Color(255, 255, 255));
        edit_btn_5.setText("Edit");
        edit_btn_5.setEnabled(false);
        edit_btn_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btn_5ActionPerformed(evt);
            }
        });

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Type list"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable9.setShowHorizontalLines(true);
        jTable9.setShowVerticalLines(true);
        jScrollPane9.setViewportView(jTable9);

        jButton29.setBackground(new java.awt.Color(0, 204, 0));
        jButton29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("Add");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        delete_btn_5.setBackground(new java.awt.Color(255, 51, 51));
        delete_btn_5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete_btn_5.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn_5.setText("Delete");
        delete_btn_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btn_5ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setText("Type list");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(delete_btn_5))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edit_btn_5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton29))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton29)
                        .addComponent(edit_btn_5))
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_btn_5)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Typelist", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edit_btn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btn_1ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_1.setEnabled(true);
            Object rowData = jTable1.getValueAt(selectedRow, 2);
            P_edit.pack();
            P_edit.setVisible(true);

            try {
                Connection conn = DriverManager.getConnection(url, user, password);

                // Example condition for the row to update based on U_ID

                String sql = "SELECT D_1_ID, PRIORITY, P_COLOR FROM JOSH.D_1 WHERE PRIORITY = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, String.valueOf(rowData));

                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    String Value = resultSet.getString("D_1_ID");
                    String Value2 = resultSet.getString("PRIORITY");
                    String Value3 = resultSet.getString("P_COLOR");

                    p_id.setText(Value);
                    priority_edit.setText(Value2);
                    p_color_edit.setText(Value3);
                    String[] parts = Value3.toString().split(",");
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                    p_color_edit.setBackground(new Color(a,b,c));
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

    private void edit_btn_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btn_2ActionPerformed
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_2.setEnabled(true);
            Object rowData = jTable2.getValueAt(selectedRow, 2);
            L_edit.pack();
            L_edit.setVisible(true);

            try {
                Connection conn = DriverManager.getConnection(url, user, password);

                // Example condition for the row to update based on U_ID

                String sql = "SELECT D_2_ID, LOCATION, L_COLOR FROM JOSH.D_2 WHERE LOCATION = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, String.valueOf(rowData));

                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    String Value = resultSet.getString("D_2_ID");
                    String Value2 = resultSet.getString("LOCATION");
                    String Value3 = resultSet.getString("L_COLOR");

                    l_id.setText(Value);
                    location_edit.setText(Value2);
                    l_color_edit.setText(Value3);
                    String[] parts = Value3.toString().split(",");
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                    l_color_edit.setBackground(new Color(a,b,c));
                    System.out.println(rowData);
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        P_add_new.pack();
        P_add_new.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void delete_btn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btn_1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); 
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_1.setEnabled(true);
            Object rowData = jTable1.getValueAt(selectedRow, 1);
            Object rowData2 = jTable1.getValueAt(selectedRow, 2);
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the priority of \"" + rowData2 + "\" ?",
                    "Confirmation Message", JOptionPane.YES_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection(url, user, password);

                        //String usernameToDelete = "desiredUsername"; // Replace with the specific username to delete

                        String sql = "DELETE FROM JOSH.D_1 WHERE D_1_ID = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, String.valueOf(rowData)); // Set the parameter for the username to delete

                        int rowsDeleted = stmt.executeUpdate();

                        if (rowsDeleted > 0) {
                            System.out.println("User with priority '" + rowData + "' deleted successfully.");
                        } else {
                            System.out.println("No user found with priority '" + rowData + "'.");
                        }

                        // Close the resources
                        stmt.close();
                        conn.close();

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
        }
        priority();
    }//GEN-LAST:event_delete_btn_1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        L_add_new.pack();
        L_add_new.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void delete_btn_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btn_2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel(); 
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_2.setEnabled(true);
            Object rowData = jTable2.getValueAt(selectedRow, 1);
            Object rowData2 = jTable2.getValueAt(selectedRow, 2);
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the location of \"" + rowData2 + "\" ?",
                    "Confirmation Message", JOptionPane.YES_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection(url, user, password);

                        //String usernameToDelete = "desiredUsername"; // Replace with the specific username to delete

                        String sql = "DELETE FROM JOSH.D_2 WHERE D_2_ID = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, String.valueOf(rowData)); // Set the parameter for the username to delete

                        int rowsDeleted = stmt.executeUpdate();

                        if (rowsDeleted > 0) {
                            System.out.println("User with location '" + rowData + "' deleted successfully.");
                        } else {
                            System.out.println("No user found with location '" + rowData + "'.");
                        }

                        // Close the resources
                        stmt.close();
                        conn.close();

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
        }
        Location();
    }//GEN-LAST:event_delete_btn_2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        S_add_new.pack();
        S_add_new.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void delete_btn_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btn_3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel(); 
        int selectedRow = jTable4.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_4.setEnabled(true);
            Object rowData = jTable4.getValueAt(selectedRow, 1);
            Object rowData2 = jTable4.getValueAt(selectedRow, 2);
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the status of \"" + rowData2 + "\" ?",
                    "Confirmation Message", JOptionPane.YES_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection(url, user, password);

                        //String usernameToDelete = "desiredUsername"; // Replace with the specific username to delete

                        String sql = "DELETE FROM JOSH.D_4 WHERE D_4_ID = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, String.valueOf(rowData)); // Set the parameter for the username to delete

                        int rowsDeleted = stmt.executeUpdate();

                        if (rowsDeleted > 0) {
                            System.out.println("User with status '" + rowData + "' deleted successfully.");
                        } else {
                            System.out.println("No user found with status '" + rowData + "'.");
                        }

                        // Close the resources
                        stmt.close();
                        conn.close();

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
        }
        Status();
    }//GEN-LAST:event_delete_btn_3ActionPerformed

    private void edit_btn_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btn_3ActionPerformed
        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_3.setEnabled(true);
            Object rowData = jTable3.getValueAt(selectedRow, 2);
            A_edit.pack();
            A_edit.setVisible(true);

            try {
                Connection conn = DriverManager.getConnection(url, user, password);

                // Example condition for the row to update based on U_ID

                String sql = "SELECT D_3_ID, AGENCY, A_COLOR FROM JOSH.D_3 WHERE AGENCY = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, String.valueOf(rowData));

                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    String Value = resultSet.getString("D_3_ID");
                    String Value2 = resultSet.getString("AGENCY");
                    String Value3 = resultSet.getString("A_COLOR");

                    a_id.setText(Value);
                    agency_edit.setText(Value2);
                    a_color_edit.setText(Value3);
                    String[] parts = Value3.toString().split(",");
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                    a_color_edit.setBackground(new Color(a,b,c));
                    System.out.println(rowData);
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
    }//GEN-LAST:event_edit_btn_3ActionPerformed

    private void edit_btn_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btn_4ActionPerformed
        int selectedRow = jTable4.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_4.setEnabled(true);
            Object rowData = jTable4.getValueAt(selectedRow, 2);
            S_edit.pack();
            S_edit.setVisible(true);

            try {
                Connection conn = DriverManager.getConnection(url, user, password);

                // Example condition for the row to update based on U_ID

                String sql = "SELECT D_4_ID, STATUS, S_COLOR FROM JOSH.D_4 WHERE STATUS = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, String.valueOf(rowData));

                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    String Value = resultSet.getString("D_4_ID");
                    String Value2 = resultSet.getString("STATUS");
                    String Value3 = resultSet.getString("S_COLOR");

                    s_id.setText(Value);
                    status_edit.setText(Value2);
                    s_color_edit.setText(Value3);
                    String[] parts = Value3.toString().split(",");
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                    s_color_edit.setBackground(new Color(a,b,c));
                    System.out.println(rowData);
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
    }//GEN-LAST:event_edit_btn_4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        A_add_new.pack();
        A_add_new.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void delete_btn_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btn_4ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel(); 
        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_3.setEnabled(true);
            Object rowData = jTable3.getValueAt(selectedRow, 1);
            Object rowData2 = jTable3.getValueAt(selectedRow, 2);
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the agency of \"" + rowData2 + "\" ?",
                    "Confirmation Message", JOptionPane.YES_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection(url, user, password);

                        //String usernameToDelete = "desiredUsername"; // Replace with the specific username to delete

                        String sql = "DELETE FROM JOSH.D_3 WHERE D_3_ID = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, String.valueOf(rowData)); // Set the parameter for the username to delete

                        int rowsDeleted = stmt.executeUpdate();

                        if (rowsDeleted > 0) {
                            System.out.println("User with agency '" + rowData + "' deleted successfully.");
                        } else {
                            System.out.println("No user found with agency '" + rowData + "'.");
                        }

                        // Close the resources
                        stmt.close();
                        conn.close();

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
        }
        Agency();
    }//GEN-LAST:event_delete_btn_4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int rows = jTable5.getRowCount();
        // Table model alignment
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(10);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable5.setDefaultRenderer(String.class, centerRenderer);
        jTable5.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        model.addRow(new Object[]{rows + 1, " ? ", "255,255,255"});

        jTable5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = jTable5.columnAtPoint(e.getPoint());
                int row = jTable5.rowAtPoint(e.getPoint());

                // Check if the clicked cell is in column 3 and row 2
                if (column == 2 && row == rows) {
                        // Perform specific action for column 3, row 2 (or any desired cell)
                        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                        if (chosenColor != null) {
                            int red = chosenColor.getRed();
                            int green = chosenColor.getGreen();
                            int blue = chosenColor.getBlue();

                            // Displaying the RGB components as three-digit values
                            String msg = red+","+green+","+blue;
                            String message = String.format("Red: %03d, Green: %03d, Blue: %03d", red, green, blue);
                            jTable5.getModel().setValueAt(msg, row, column);
                            
                            
                        }
                }
                jTable5.getColumnModel().getColumn(2).setCellRenderer(colorRenderer);
                
                
            }
        });
//        sportColumn.setCellEditor(new DefaultCellEditor(button));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Assuming 'table' is your JTable
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        int columnToRetrieve = 1; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve2 = 2; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve3 = 3; // Column index (0-based) for the column you want to retrieve

        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, columnToRetrieve);
            Object value2 = model.getValueAt(i, columnToRetrieve2);
            System.out.println("Value at row " + i + ",specific column : " + value);
            // Perform your logic with 'value' here

            try {
                //Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);

                //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
                String sql = "INSERT INTO JOSH.D_1(D_1_NO, D_1_ID, PRIORITY, P_COLOR)VALUES (?, ?, ?, ?)";
                String sql2 = "SELECT * FROM JOSH.D_1 WHERE D_1_NO = (SELECT MAX(D_1_NO) FROM JOSH.D_1)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2);

                ResultSet resultSet = stmt2.executeQuery();
                int num = 0;
                if (resultSet.next()) {
                    int lastValue = resultSet.getInt("D_1_NO");
                    num = lastValue;
                    System.out.println("Last value of D_1_NO column: " + lastValue);
                    // Use the lastValue variable as needed
                }

                // Set parameters for the PreparedStatement
                stmt.setInt(1, num + 1);
                stmt.setString(2, "D_1_ID_"+String.valueOf(num + 1));
                stmt.setString(3, String.valueOf(value));
                stmt.setString(4, String.valueOf(value2));

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
        priority();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    P_add_new.dispose();        
// Assuming you have established a connection to your database and have a PreparedStatement object
//        String sql = "SELECT * FROM JOSH.USERS WHERE U_NO = (SELECT MAX(U_NO) FROM JOSH.USERS)";
//
//        try {
//            Connection conn = DriverManager.getConnection(url, user, password);
//            PreparedStatement statement = conn.prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                int lastValue = resultSet.getInt("U_NO");
//                System.out.println("Last value of U_NO column: " + lastValue);
//                // Use the lastValue variable as needed
//            }
//
//            resultSet.close();
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        if(jTable5.getSelectedRow() != -1) {
            // remove selected row from the model
            model.removeRow(jTable5.getSelectedRow());
            //               JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Example condition for the row to update based on U_ID
            //int userIdToUpdate = 123; // Replace with the specific user ID

            String sql = "UPDATE JOSH.D_1 SET PRIORITY = ?, P_COLOR = ? WHERE D_1_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameters for the update statement
            stmt.setString(1, priority_edit.getText());
            stmt.setString(2, p_color_edit.getText());
            stmt.setString(3, p_id.getText());
            //            stmt.setInt(4, userIdToUpdate);

            // Execute the update statement
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                //System.out.println("Update successful for user with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "Update successful!");
            } else {
                //System.out.println("No user found with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "No user found with \"priority\" ");
            }

            // Close the resources
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        priority();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void p_color_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_color_editMouseClicked
        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
        if (chosenColor != null) {
            int red = chosenColor.getRed();
            int green = chosenColor.getGreen();
            int blue = chosenColor.getBlue();

            // Displaying the RGB components as three-digit values
            String msg = red+","+green+","+blue;
            String message = String.format("Red: %03d, Green: %03d, Blue: %03d", red, green, blue);
            p_color_edit.setBackground(new Color (red,green,blue));
            p_color_edit.setText(msg);
            
        }
    }//GEN-LAST:event_p_color_editMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int rows = jTable6.getRowCount();
        // Table model alignment
        DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
        jTable6.getColumnModel().getColumn(0).setPreferredWidth(10);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable6.setDefaultRenderer(String.class, centerRenderer);
        jTable6.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        model.addRow(new Object[]{rows + 1, " ? ", "255,255,255"});

        jTable6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = jTable6.columnAtPoint(e.getPoint());
                int row = jTable6.rowAtPoint(e.getPoint());

                // Check if the clicked cell is in column 3 and row 2
                if (column == 2 && row == rows) {
                        // Perform specific action for column 3, row 2 (or any desired cell)
                        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                        if (chosenColor != null) {
                            int red = chosenColor.getRed();
                            int green = chosenColor.getGreen();
                            int blue = chosenColor.getBlue();

                            // Displaying the RGB components as three-digit values
                            String msg = red+","+green+","+blue;
                            String message = String.format("Red: %03d, Green: %03d, Blue: %03d", red, green, blue);
                            jTable6.getModel().setValueAt(msg, row, column);
                            
                            
                        }
                }
                jTable6.getColumnModel().getColumn(2).setCellRenderer(colorRenderer);
                
                
            }
        });
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // Assuming 'table' is your JTable
        DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
        int columnToRetrieve = 1; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve2 = 2; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve3 = 3; // Column index (0-based) for the column you want to retrieve

        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, columnToRetrieve);
            Object value2 = model.getValueAt(i, columnToRetrieve2);
            System.out.println("Value at row " + i + ",specific column : " + value);
            // Perform your logic with 'value' here

            try {
                //Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);

                //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
                String sql = "INSERT INTO JOSH.D_2(D_2_NO, D_2_ID, LOCATION, L_COLOR)VALUES (?, ?, ?, ?)";
                String sql2 = "SELECT * FROM JOSH.D_2 WHERE D_2_NO = (SELECT MAX(D_2_NO) FROM JOSH.D_2)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2);

                ResultSet resultSet = stmt2.executeQuery();
                int num = 0;
                if (resultSet.next()) {
                    int lastValue = resultSet.getInt("D_2_NO");
                    num = lastValue;
                    System.out.println("Last value of D_2_NO column: " + lastValue);
                    // Use the lastValue variable as needed
                }

                // Set parameters for the PreparedStatement
                stmt.setInt(1, num + 1);
                stmt.setString(2, "D_2_ID_"+String.valueOf(num + 1));
                stmt.setString(3, String.valueOf(value));
                stmt.setString(4, String.valueOf(value2));

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
        Location();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        L_add_new.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
        if(jTable6.getSelectedRow() != -1) {
            // remove selected row from the model
            model.removeRow(jTable6.getSelectedRow());
            //               JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Example condition for the row to update based on U_ID
            //int userIdToUpdate = 123; // Replace with the specific user ID

            String sql = "UPDATE JOSH.D_2 SET LOCATION = ?, L_COLOR = ? WHERE D_2_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameters for the update statement
            stmt.setString(1, location_edit.getText());
            stmt.setString(2, l_color_edit.getText());
            stmt.setString(3, l_id.getText());
            //            stmt.setInt(4, userIdToUpdate);

            // Execute the update statement
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                //System.out.println("Update successful for user with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "Update successful!");
            } else {
                //System.out.println("No user found with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "No user found with \"priority\" ");
            }

            // Close the resources
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        Location();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void l_color_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l_color_editMouseClicked
        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
        if (chosenColor != null) {
            int red = chosenColor.getRed();
            int green = chosenColor.getGreen();
            int blue = chosenColor.getBlue();

            // Displaying the RGB components as three-digit values
            String msg = red+","+green+","+blue;
            String message = String.format("Red: %03d, Green: %03d, Blue: %03d", red, green, blue);
            l_color_edit.setBackground(new Color (red,green,blue));
            l_color_edit.setText(msg);
            
        }
    }//GEN-LAST:event_l_color_editMouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        int rows = jTable7.getRowCount();
        // Table model alignment
        DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
        jTable7.getColumnModel().getColumn(0).setPreferredWidth(10);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable7.setDefaultRenderer(String.class, centerRenderer);
        jTable7.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        model.addRow(new Object[]{rows + 1, " ? ", "255,255,255"});

        jTable7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = jTable7.columnAtPoint(e.getPoint());
                int row = jTable7.rowAtPoint(e.getPoint());

                // Check if the clicked cell is in column 3 and row 2
                if (column == 2 && row == rows) {
                        // Perform specific action for column 3, row 2 (or any desired cell)
                        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                        if (chosenColor != null) {
                            int red = chosenColor.getRed();
                            int green = chosenColor.getGreen();
                            int blue = chosenColor.getBlue();

                            // Displaying the RGB components as three-digit values
                            String msg = red+","+green+","+blue;
                            String message = String.format("Red: %03d, Green: %03d, Blue: %03d", red, green, blue);
                            jTable7.getModel().setValueAt(msg, row, column);
                        }
                }
                jTable7.getColumnModel().getColumn(2).setCellRenderer(colorRenderer);
            }
        });
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // Assuming 'table' is your JTable
        DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
        int columnToRetrieve = 1; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve2 = 2; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve3 = 3; // Column index (0-based) for the column you want to retrieve

        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, columnToRetrieve);
            Object value2 = model.getValueAt(i, columnToRetrieve2);
            System.out.println("Value at row " + i + ",specific column : " + value);
            // Perform your logic with 'value' here

            try {
                //Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);

                //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
                String sql = "INSERT INTO JOSH.D_3(D_3_NO, D_3_ID, AGENCY, A_COLOR)VALUES (?, ?, ?, ?)";
                String sql2 = "SELECT * FROM JOSH.D_3 WHERE D_3_NO = (SELECT MAX(D_3_NO) FROM JOSH.D_3)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2);

                ResultSet resultSet = stmt2.executeQuery();
                int num = 0;
                if (resultSet.next()) {
                    int lastValue = resultSet.getInt("D_3_NO");
                    num = lastValue;
                    System.out.println("Last value of D_3_NO column: " + lastValue);
                    // Use the lastValue variable as needed
                }

                // Set parameters for the PreparedStatement
                stmt.setInt(1, num + 1);
                stmt.setString(2, "D_3_ID_"+String.valueOf(num + 1));
                stmt.setString(3, String.valueOf(value));
                stmt.setString(4, String.valueOf(value2));

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
        Agency();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        A_add_new.dispose();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
        if(jTable7.getSelectedRow() != -1) {
            // remove selected row from the model
            model.removeRow(jTable7.getSelectedRow());
            //               JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Example condition for the row to update based on U_ID
            //int userIdToUpdate = 123; // Replace with the specific user ID

            String sql = "UPDATE JOSH.D_3 SET AGENCY = ?, A_COLOR = ? WHERE D_3_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameters for the update statement
            stmt.setString(1, agency_edit.getText());
            stmt.setString(2, a_color_edit.getText());
            stmt.setString(3, a_id.getText());

            // Execute the update statement
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                //System.out.println("Update successful for user with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "Update successful!");
            } else {
                //System.out.println("No user found with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "No user found with \"priority\" ");
            }

            // Close the resources
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        Agency();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void a_color_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_a_color_editMouseClicked
        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
        if (chosenColor != null) {
            int red = chosenColor.getRed();
            int green = chosenColor.getGreen();
            int blue = chosenColor.getBlue();

            // Displaying the RGB components as three-digit values
            String msg = red+","+green+","+blue;
            String message = String.format("Red: %03d, Green: %03d, Blue: %03d", red, green, blue);
            a_color_edit.setBackground(new Color (red,green,blue));
            a_color_edit.setText(msg);
            
        }
    }//GEN-LAST:event_a_color_editMouseClicked

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
        if(jTable8.getSelectedRow() != -1) {
            // remove selected row from the model
            model.removeRow(jTable8.getSelectedRow());
            //               JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        int rows = jTable8.getRowCount();
        // Table model alignment
        DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
        jTable8.getColumnModel().getColumn(0).setPreferredWidth(10);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable8.setDefaultRenderer(String.class, centerRenderer);
        jTable8.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        model.addRow(new Object[]{rows + 1, " ? ", "255,255,255"});

        jTable8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = jTable8.columnAtPoint(e.getPoint());
                int row = jTable8.rowAtPoint(e.getPoint());

                // Check if the clicked cell is in column 3 and row 2
                if (column == 2 && row == rows) {
                        // Perform specific action for column 3, row 2 (or any desired cell)
                        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                        if (chosenColor != null) {
                            int red = chosenColor.getRed();
                            int green = chosenColor.getGreen();
                            int blue = chosenColor.getBlue();

                            // Displaying the RGB components as three-digit values
                            String msg = red+","+green+","+blue;
                            String message = String.format("Red: %03d, Green: %03d, Blue: %03d", red, green, blue);
                            jTable8.getModel().setValueAt(msg, row, column);
                        }
                }
                jTable8.getColumnModel().getColumn(2).setCellRenderer(colorRenderer);
            }
        });
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // Assuming 'table' is your JTable
        DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
        int columnToRetrieve = 1; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve2 = 2; // Column index (0-based) for the column you want to retrieve
        int columnToRetrieve3 = 3; // Column index (0-based) for the column you want to retrieve

        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, columnToRetrieve);
            Object value2 = model.getValueAt(i, columnToRetrieve2);
            System.out.println("Value at row " + i + ",specific column : " + value);
            // Perform your logic with 'value' here

            try {
                //Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);

                //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
                String sql = "INSERT INTO JOSH.D_4(D_4_NO, D_4_ID, STATUS, S_COLOR)VALUES (?, ?, ?, ?)";
                String sql2 = "SELECT * FROM JOSH.D_4 WHERE D_4_NO = (SELECT MAX(D_4_NO) FROM JOSH.D_4)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2);

                ResultSet resultSet = stmt2.executeQuery();
                int num = 0;
                if (resultSet.next()) {
                    int lastValue = resultSet.getInt("D_4_NO");
                    num = lastValue;
                    System.out.println("Last value of D_4_NO column: " + lastValue);
                    // Use the lastValue variable as needed
                }

                // Set parameters for the PreparedStatement
                stmt.setInt(1, num + 1);
                stmt.setString(2, "D_4_ID_"+String.valueOf(num + 1));
                stmt.setString(3, String.valueOf(value));
                stmt.setString(4, String.valueOf(value2));

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
        Status();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        S_add_new.dispose();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Example condition for the row to update based on U_ID
            //int userIdToUpdate = 123; // Replace with the specific user ID

            String sql = "UPDATE JOSH.D_4 SET STATUS = ?, S_COLOR = ? WHERE D_4_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameters for the update statement
            stmt.setString(1, status_edit.getText());
            stmt.setString(2, s_color_edit.getText());
            stmt.setString(3, s_id.getText());

            // Execute the update statement
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                //System.out.println("Update successful for user with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "Update successful!");
            } else {
                //System.out.println("No user found with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "No user found with \"priority\" ");
            }

            // Close the resources
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        Status();
    }//GEN-LAST:event_jButton27ActionPerformed

    private void s_color_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s_color_editMouseClicked
        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
        if (chosenColor != null) {
            int red = chosenColor.getRed();
            int green = chosenColor.getGreen();
            int blue = chosenColor.getBlue();

            // Displaying the RGB components as three-digit values
            String msg = red+","+green+","+blue;
            String message = String.format("Red: %03d, Green: %03d, Blue: %03d", red, green, blue);
            s_color_edit.setBackground(new Color (red,green,blue));
            s_color_edit.setText(msg);
            
        }
    }//GEN-LAST:event_s_color_editMouseClicked

    private void edit_btn_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btn_5ActionPerformed
        int selectedRow = jTable9.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_5.setEnabled(true);
            Object rowData = jTable9.getValueAt(selectedRow, 1);
            T_edit.pack();
            T_edit.setVisible(true);

            try {
                Connection conn = DriverManager.getConnection(url, user, password);

                // Example condition for the row to update based on U_ID

                String sql = "SELECT D_5_ID, LIST FROM JOSH.D_5 WHERE LIST = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, String.valueOf(rowData));

                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    String Value = resultSet.getString("D_5_ID");
                    String Value2 = resultSet.getString("LIST");

                    t_id.setText(Value);
                    type_edit.setText(Value2);
                    System.out.println(rowData);
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
    }//GEN-LAST:event_edit_btn_5ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        T_add_new.pack();
        T_add_new.setVisible(true);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void delete_btn_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btn_5ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable9.getModel(); 
        int selectedRow = jTable9.getSelectedRow();
        if (selectedRow != -1) {
            edit_btn_5.setEnabled(true);
            Object rowData = jTable9.getValueAt(selectedRow, 1);
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the status of \"" + rowData + "\" ?",
                    "Confirmation Message", JOptionPane.YES_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection(url, user, password);

                        //String usernameToDelete = "desiredUsername"; // Replace with the specific username to delete

                        String sql = "DELETE FROM JOSH.D_5 WHERE LIST = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, String.valueOf(rowData)); // Set the parameter for the username to delete

                        int rowsDeleted = stmt.executeUpdate();

                        if (rowsDeleted > 0) {
                            System.out.println("User with typelist '" + rowData + "' deleted successfully.");
                        } else {
                            System.out.println("No user found with typelist '" + rowData + "'.");
                        }

                        // Close the resources
                        stmt.close();
                        conn.close();

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
        }
        TypeList();
    }//GEN-LAST:event_delete_btn_5ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed

// Assuming 'table' is your JTable
        DefaultTableModel model = (DefaultTableModel) jTable10.getModel();
        int columnToRetrieve = 1; // Column index (0-based) for the column you want to retrieve
//        int columnToRetrieve2 = 2; // Column index (0-based) for the column you want to retrieve
//        int columnToRetrieve3 = 3; // Column index (0-based) for the column you want to retrieve

        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, columnToRetrieve);
//            Object value2 = model.getValueAt(i, columnToRetrieve2);
            System.out.println("Value at row " + i + ",specific column : " + value);
            // Perform your logic with 'value' here

            try {
                //Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);

                //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
                String sql = "INSERT INTO JOSH.D_5(D_5_NO, D_5_ID, LIST)VALUES (?, ?, ?)";
                String sql2 = "SELECT * FROM JOSH.D_5 WHERE D_5_NO = (SELECT MAX(D_5_NO) FROM JOSH.D_5)";
                String sql3 = "SELECT COUNT(*) AS count FROM JOSH.PRJNO WHERE D_5_ID = '" + String.valueOf(value) + "'";
                String sql4 = "INSERT INTO JOSH.PRJNO(NUMBER, SLOT, D_5_ID)VALUES (?, ?, ?)";
                
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                PreparedStatement stmt3 = conn.prepareStatement(sql3); 
                PreparedStatement stmt4 = conn.prepareStatement(sql4); 
                
                ResultSet resultSet = stmt2.executeQuery();
                int num = 0;
                if (resultSet.next()) {
                    int lastValue = resultSet.getInt("D_5_NO");
                    num = lastValue;
                    System.out.println("Last value of D_5_NO column: " + lastValue);
                    // Use the lastValue variable as needed
                }
                
                ResultSet resultSetD_5 = stmt3.executeQuery();
                boolean ch = true;
                resultSetD_5.next();
                int count = resultSetD_5.getInt("count");
                if (count > 0) {
                    System.out.println("There is an existing data");
                } else {
                    System.out.println("There's none");
                    stmt4.setInt(1, 1);
                    stmt4.setInt(2, 0);
                    stmt4.setString(3, "D_5_ID_"+String.valueOf(num + 1));
                    // Execute the INSERT statement
                    int rowsAffected = stmt4.executeUpdate();
                     if (rowsAffected > 0) {
                        System.out.println("Data inserted successfully!");
                    }

                }

                // Set parameters for the PreparedStatement
                stmt.setInt(1, num + 1);
                stmt.setString(2, "D_5_ID_"+String.valueOf(num + 1));
                stmt.setString(3, String.valueOf(value));

                // Execute the INSERT statement
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully!");
                }

                // Close the resources
                resultSet.close();
                resultSetD_5.close();
                stmt2.close();
                stmt.close();
                stmt3.close();
                stmt4.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        TypeList();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        T_add_new.dispose();
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable10.getModel();
        if(jTable10.getSelectedRow() != -1) {
            // remove selected row from the model
            model.removeRow(jTable10.getSelectedRow());
            //               JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        int rows = jTable10.getRowCount();
        // Table model alignment
        DefaultTableModel model = (DefaultTableModel) jTable10.getModel();
        jTable10.getColumnModel().getColumn(0).setPreferredWidth(10);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable10.setDefaultRenderer(String.class, centerRenderer);
        jTable10.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        model.addRow(new Object[]{rows + 1, " ? "});

//        jTable10.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int column = jTable10.columnAtPoint(e.getPoint());
//                int row = jTable10.rowAtPoint(e.getPoint());
//
//                // Check if the clicked cell is in column 3 and row 2
//                if (column == 2 && row == rows) {
//                        // Perform specific action for column 3, row 2 (or any desired cell)
//                        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
//                        if (chosenColor != null) {
//                            int red = chosenColor.getRed();
//                            int green = chosenColor.getGreen();
//                            int blue = chosenColor.getBlue();
//
//                        }
//                }
//            }
//        });
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Example condition for the row to update based on U_ID
            //int userIdToUpdate = 123; // Replace with the specific user ID

            String sql = "UPDATE JOSH.D_5 SET LIST = ? WHERE D_5_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameters for the update statement
            stmt.setString(1, type_edit.getText());
            stmt.setString(2, t_id.getText());

            // Execute the update statement
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                //System.out.println("Update successful for user with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "Update successful!");
            } else {
                //System.out.println("No user found with ID: " + userIdToUpdate);
                JOptionPane.showMessageDialog(null, "No user found with \"typelist\" ");
            }

            // Close the resources
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        TypeList();
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        T_edit.dispose();
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        S_edit.dispose();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        A_edit.dispose();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        L_edit.dispose();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        P_edit.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

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
                new E_WORK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame A_add_new;
    private javax.swing.JFrame A_edit;
    private javax.swing.JFrame L_add_new;
    private javax.swing.JFrame L_edit;
    private javax.swing.JFrame P_add_new;
    private javax.swing.JFrame P_edit;
    private javax.swing.JFrame S_add_new;
    private javax.swing.JFrame S_edit;
    private javax.swing.JFrame T_add_new;
    private javax.swing.JFrame T_edit;
    private javax.swing.JTextField a_color_edit;
    private javax.swing.JTextField a_id;
    private javax.swing.JTextField agency_edit;
    private javax.swing.JButton delete_btn_1;
    private javax.swing.JButton delete_btn_2;
    private javax.swing.JButton delete_btn_3;
    private javax.swing.JButton delete_btn_4;
    private javax.swing.JButton delete_btn_5;
    private javax.swing.JButton edit_btn_1;
    private javax.swing.JButton edit_btn_2;
    private javax.swing.JButton edit_btn_3;
    private javax.swing.JButton edit_btn_4;
    private javax.swing.JButton edit_btn_5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField l_color_edit;
    private javax.swing.JTextField l_id;
    private javax.swing.JTextField location_edit;
    private v1_tm_projectmanagement.NewPanel newPanel1;
    private v1_tm_projectmanagement.NewPanel newPanel2;
    private v1_tm_projectmanagement.NewPanel newPanel3;
    private v1_tm_projectmanagement.NewPanel newPanel4;
    private v1_tm_projectmanagement.NewPanel newPanel5;
    private javax.swing.JTextField p_color_edit;
    private javax.swing.JTextField p_id;
    private javax.swing.JTextField priority_edit;
    private javax.swing.JTextField s_color_edit;
    private javax.swing.JTextField s_id;
    private javax.swing.JTextField status_edit;
    private javax.swing.JTextField t_id;
    private javax.swing.JTextField type_edit;
    // End of variables declaration//GEN-END:variables
}
