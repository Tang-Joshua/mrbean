/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package v1_tm_projectmanagement;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.derby.drda.NetworkServerControl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PROJECT_LIST extends javax.swing.JFrame {

//    private final String url = "jdbc:derby://localhost:1527/TM_DB";
//    String url = "jdbc:derby://192.168.1.6:1527/\\\\DESKTOP-7CU9UG7\\Task_DB\\TM_DB";
    String url = "";
    private final String user = "josh";
    private final String password = "1234";
    
    private DefaultTableModel model = new DefaultTableModel();
    private int clickedColumn = -1;
    private TableRowSorter<DefaultTableModel> sorter;
    
    Login rl = new Login();
    
    
    Date petsa = new Date();
    SimpleDateFormat sdfa = new SimpleDateFormat("yyyy.MM.dd");
    String date = sdfa.format(petsa);
    SimpleDateFormat sdfa2 = new SimpleDateFormat("MM/dd/yyyy");
    String datecreated = sdfa2.format(petsa);
    SimpleDateFormat sdfa3 = new SimpleDateFormat("MMM dd.yyyy");
   String datechat = sdfa3.format(petsa);
    
    List<String[]> rows1 = new ArrayList<>();
    List<String[]> rows2 = new ArrayList<>();
    List<String[]> rows3 = new ArrayList<>();
    List<String[]> rows4 = new ArrayList<>();
    List<String[]> rows5 = new ArrayList<>();
    List<String[]> rowsacti = new ArrayList<>();
    
    List<String[]> rowscompany = new ArrayList<>();
    List<String[]> rowsuser = new ArrayList<>();
    
 
    AcceptRejectRenderer renderer = new AcceptRejectRenderer();
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    private Map<Integer, String> lastValidTextMap = new HashMap<>(); // Map to store last valid text per row
    
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
    
    private static int getColumnIndexByName(JTable table, String columnName) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(columnName)) {
                return i; // Return the index if column name matches
            }
        }
        return -1; // Return -1 if column name is not found
    }
    
    String O_time = "";
    
    String name = rl.location.getText();
    String ip = rl.ip_add.getText();
//    String name = "\\\\DESKTOP-7CU9UG7\\Task_DB2\\TM_DB";
//    String ip = "192.168.1.6";
   
    public PROJECT_LIST() {
        initComponents();
        System.out.println(name+" and "+ip);
        setIconImage();
        open_ip();
        add_db();
        times();
        Template();
        listnercolumn();
        renderTable1();
        Ref();
        
    }
    
  
    
    private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files (x86)\\Pms_f\\Documentation\\PMS.png"));
    }
    
    Timer t;
    SimpleDateFormat st;
    public void times(){
    t = new Timer(0, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
                //throw new UnsupportedOperationException("Not supported yet.")
        Date dt = new Date();
        st = new SimpleDateFormat("hh:mm a");
            
        String tt = st.format(dt);
//        O_time.setText(tt);
        O_time = tt;
            }
        });
    t.start();
    }
    
    public void open_ip(){
       name = rl.location.getText();
    ip = rl.ip_add.getText();
        NetworkServerControl obj;
        try {
            obj = new NetworkServerControl(InetAddress.getByName(ip),1527);
            obj.start(null);
            obj.ping();
//            System.out.println("Connection success");
            url = "jdbc:derby://"+ip+":1527/"+name;
//                JOptionPane.showMessageDialog(this, "Derby server started successfully!!!.", "Success", JOptionPane.INFORMATION_MESSAGE);
// Additional code for server startup actions
            } catch (UnknownHostException ex) {
                System.out.println("Connection failed");
                
        }catch (Exception ex) {
            System.out.println("Connection failed 2");
        }
        
    }
    
    public void renderTable1(){
        int id = getColumnIndexByName(jTable1, "ID");
        int id2 = getColumnIndexByName(jTable2, "No");
        
        // Hide column (Istorbo)
        TableColumnModel columnModel = jTable1.getColumnModel();
        TableColumnModel columnModel2 = jTable2.getColumnModel();
        
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
        
        jTable1.setRowHeight(42);
        jTable1.getColumnModel().getColumn(17).setCellRenderer(renderer);
        jTable1.getColumnModel().getColumn(17).setCellEditor(new AcceptRejectEditor());
        jTable1.setRowSelectionAllowed(true);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(13).setPreferredWidth(35);
        
        jTable2.setRowHeight(42);
        jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable2.setRowSelectionAllowed(false);
        jTable2.setColumnSelectionAllowed(false);
//        jTable2.setDefaultEditor(Object.class, null);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable2.getTableHeader().setReorderingAllowed(false);
        int ty = getColumnIndexByName(jTable1, "Type");
        int nn = getColumnIndexByName(jTable1, "#");
        int age = getColumnIndexByName(jTable1, "Agency");
        int com = getColumnIndexByName(jTable1, "Company");
        int prio = getColumnIndexByName(jTable1, "Priority");
        int user = getColumnIndexByName(jTable1, "User");
        jTable1.getColumnModel().getColumn(ty).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(nn).setPreferredWidth(25);
        jTable1.getColumnModel().getColumn(age).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(com).setPreferredWidth(105);
        jTable1.getColumnModel().getColumn(prio).setPreferredWidth(43);
        jTable1.getColumnModel().getColumn(user).setPreferredWidth(47);
        customizeTableHeader(jTable1);
        
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                refresh.doClick();
                searchTable();
            }
        };

        search_text.addActionListener( action );
        
    }
    
    public void listnercolumn(){
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRows = jTable1.getSelectedRow();
                
//                jLabel17.setText((String) rowDatas);
//        
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int column = jTable1.columnAtPoint(e.getPoint());
                    int row = jTable1.rowAtPoint(e.getPoint());
                    int selectedRow = jTable1.getSelectedRow();
                    
                    int approval = getColumnIndexByName(jTable1, "Approval");
                    int prj = getColumnIndexByName(jTable1, "Project Description");
                    int desc = getColumnIndexByName(jTable1, "Remarks");
                    int LasCo = getColumnIndexByName(jTable1, "Last Comment");

                    if (column == approval && row != -1) { 
                        if(utype.getText().equals("Admin")){
                            // Perform the desired action when a cell in column 13 is clicked
                            int id = getColumnIndexByName(jTable1, "ID");
                            int proj = getColumnIndexByName(jTable1, "Proj #");
                            Object rowData = jTable1.getValueAt(selectedRow, proj);
                            Object rowData2 = jTable1.getValueAt(selectedRow, id);
                            Object rowData3 = jTable1.getValueAt(selectedRow, approval);
                            try {
                                Connection conn = DriverManager.getConnection(url, user, password);

                                String sql = "UPDATE JOSH.TEMPLATE SET APPROVAL = ? WHERE T_ID = ?";

                                String sql_template = "SELECT * FROM JOSH.TEMPLATE";
                                String sqlslot = "UPDATE JOSH.PRJNO SET SLOT = ? WHERE NUMBER = ?";

                                PreparedStatement stmt = conn.prepareStatement(sql);
                                PreparedStatement stmt_template = conn.prepareStatement(sql_template);
                                PreparedStatement stmtslot = conn.prepareStatement(sqlslot);

                                ResultSet resultSet = stmt_template.executeQuery();
                                while (resultSet.next()) {
                                    String V = resultSet.getString("T_ID");
                                    String V2 = resultSet.getString("PRJNO");
                                    int originalNumber = Integer.parseInt(V2);
                                    if(V.equals(rowData2)){
                                        if(rowData3.equals("Pending")){
                                            stmtslot.setInt(1, 2);
                                            stmtslot.setInt(2, originalNumber); int rowsUpdated = stmtslot.executeUpdate();
                                        }
                                        else{
                                            stmtslot.setInt(1, 1);
                                            stmtslot.setInt(2, originalNumber);int rowsUpdated = stmtslot.executeUpdate();
                                        }

                                    }
                                    // Use the lastValue variable as needed
                                }
                                // Set parameters for the update statement
                                if(rowData3.equals("Pending")){
                                    stmt.setBoolean(1, true);
                                    stmt.setString(2, String.valueOf(rowData2));
                                }
                                else{
                                    stmt.setBoolean(1, false);
                                    stmt.setString(2, String.valueOf(rowData2));
                                }
                                //            stmt.setInt(4, userIdToUpdate);

                                // Execute the update statement
                                int rowsUpdated = stmt.executeUpdate();

                                if (rowsUpdated > 0) {
                                    //System.out.println("Update successful for user with ID: " + userIdToUpdate);
    //                                JOptionPane.showMessageDialog(null, "Update successful!");
                                } else {
                                    //System.out.println("No user found with ID: " + userIdToUpdate);
    //                                JOptionPane.showMessageDialog(null, "No user found with \"priority\" ");
                                }

                                // Auto refresh
                                String txt = "[Change2]"+"["+ supername.getText() +"]";
                                add_His(txt);
                                // Close the resources
                                resultSet.close();
                                stmt.close();
                                stmtslot.close();
                                conn.close();

                            } catch (SQLException er) {
                                System.out.println("Error: " + er.getMessage());
                            }
                             Template();
                        }else{JOptionPane.showMessageDialog(null, "Only Admin has access");}
                    }
                    else if (column == prj && row != -1) { // Check if clicked cell is in column 13 and not the header
                        // Perform the desired action when a cell in column 13 is clicked
                        Object rowData = jTable1.getValueAt(row, column);
                        JOptionPane.showMessageDialog(null, rowData);
                    }
                    else if (column == desc && row != -1) { // Check if clicked cell is in column 13 and not the header
                        // Perform the desired action when a cell in column 13 is clicked
                        Object rowData = jTable1.getValueAt(row, column);
                        JOptionPane.showMessageDialog(null, rowData);
                    }
                    else if (column == LasCo && row != -1) { // Check if clicked cell is in column 13 and not the header
                        // Perform the desired action when a cell in column 13 is clicked
                        Object rowDatas = jTable1.getValueAt(selectedRows, LasCo);
                        JOptionPane.showMessageDialog(null, rowDatas);
                    }
                   
                }
            }
        });
        
        jTable1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int column = jTable1.columnAtPoint(e.getPoint());
                int row = jTable1.rowAtPoint(e.getPoint());
                int approval = getColumnIndexByName(jTable1, "Approval");
                int prj = getColumnIndexByName(jTable1, "Project Description");
                int desc = getColumnIndexByName(jTable1, "Remarks");
                
                if (column == approval) { // Change cursor if mouse enters column 13
                    if (row >= 0) { // Ensure row is valid
                        jTable1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    } else {
                        jTable1.setCursor(Cursor.getDefaultCursor());
                    }
                } 
                else if (column == prj || column == desc) { // Change cursor if mouse enters column 13
                    if (row >= 0) { // Ensure row is valid
                        jTable1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    } else {
                        jTable1.setCursor(Cursor.getDefaultCursor());
                    }
                } 
                else {
                    jTable1.setCursor(Cursor.getDefaultCursor());
                }
            }
        });
        
        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int column = jTable2.columnAtPoint(e.getPoint());
                    int row = jTable2.rowAtPoint(e.getPoint());
                    int selectedRow = jTable2.getSelectedRow();
                    
                    int prjdesc = getColumnIndexByName(jTable2, "Project Description");
                    int desc = getColumnIndexByName(jTable2, "Remarks");
                    int dd = getColumnIndexByName(jTable2, "Due Date");
                    int save = getColumnIndexByName(jTable2, "Save");

                    if (column == prjdesc && row != -1) {
                        Object rowData = jTable2.getValueAt(selectedRow, prjdesc);
                        JTextPane textPane = new JTextPane(); textPane.setPreferredSize(new Dimension(270,90));
//                        textPane.setContentType("text/html"); // Set content type to HTML for formatted text
                        textPane.setText((String)rowData);

                        // Create a JScrollPane and add the JTextPane to it
                        JScrollPane scrollPane = new JScrollPane(textPane);
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                        // Show the JOptionPane with the JScrollPane containing the JTextPane
                        JOptionPane.showMessageDialog(null, scrollPane, "Long Paragraph with Scrollbar", JOptionPane.INFORMATION_MESSAGE);
                        
                        // Show the JOptionPane with the JScrollPane containing the JTextPane
                        int option = JOptionPane.showConfirmDialog(null, scrollPane, "Review Text", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        // If OK is selected in the JOptionPane
                        if (option == JOptionPane.OK_OPTION) {
                            // Retrieve the text from the JTextPane
                            String newText = textPane.getText();
                            // Update the specific cell in the JTable with the retrieved text
                            // Replace "jTable" with your actual JTable object name
                            jTable2.setValueAt(newText, row, column);
                        }
                    }
                    else if (column == desc && row != -1) {
                        Object rowData = jTable2.getValueAt(selectedRow, desc);
                        JTextPane textPane = new JTextPane(); textPane.setPreferredSize(new Dimension(270,90));
//                        textPane.setContentType("text/html"); // Set content type to HTML for formatted text
                        textPane.setText((String)rowData);

                        // Create a JScrollPane and add the JTextPane to it
                        JScrollPane scrollPane = new JScrollPane(textPane);
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                        // Show the JOptionPane with the JScrollPane containing the JTextPane
                        JOptionPane.showMessageDialog(null, scrollPane, "Set Text", JOptionPane.INFORMATION_MESSAGE);
                        
                        // Show the JOptionPane with the JScrollPane containing the JTextPane
                        int option = JOptionPane.showConfirmDialog(null, scrollPane, "Review Text", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        // If OK is selected in the JOptionPane
                        if (option == JOptionPane.OK_OPTION) {
                            // Retrieve the text from the JTextPane
                            String newText = textPane.getText();
                            // Update the specific cell in the JTable with the retrieved text
                            // Replace "jTable" with your actual JTable object name
                            jTable2.setValueAt(newText, row, column);
                        }
                    }
                    else if (column == dd && row != -1) {
                        JDateChooser df = new JDateChooser();
                        // Display the JDateChooser within a JOptionPane dialog
                        int option = JOptionPane.showConfirmDialog(null, df, "Set Date", JOptionPane.OK_CANCEL_OPTION);

                        if (option == JOptionPane.OK_OPTION) {
                            Date date = df.getDate();
                            String dateText = ""; // Initialize dateText variable

                            // Check if the date is not null before formatting it
                            if (date != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                                dateText = sdf.format(date);
                            }

                            // Set the formatted date into a specific cell in jTable2 (assuming row and column are defined)
                            jTable2.setValueAt(dateText, row, column);
                        }
                    }
                    else if (column == save && row != -1) {
                        Object rowData = jTable2.getValueAt(selectedRow, save);
                        if(rowData.equals("SAVE UPDATE")){
                            int a = getColumnIndexByName(jTable2, "Company");
                            int b = getColumnIndexByName(jTable2, "Project Description");
                            int f = getColumnIndexByName(jTable2, "Remarks");
                            int g = getColumnIndexByName(jTable2, "Due Date");
                            int ii = getColumnIndexByName(jTable2, "Types");
                            int j = getColumnIndexByName(jTable2, "No");
                            Object cellCompany = jTable2.getValueAt(selectedRow, a);
                            Object cellDescription = jTable2.getValueAt(selectedRow, b);
                            Object cellRemarks = jTable2.getValueAt(selectedRow, f);
                            Object cellDue = jTable2.getValueAt(selectedRow, g);
                            Object cellTypelist = jTable2.getValueAt(selectedRow, ii);
                            Object cellId = jTable2.getValueAt(selectedRow, j);
                            Object cellprio = jTable2.getValueAt(selectedRow, 4);
                            Object cellloc = jTable2.getValueAt(selectedRow, 10);
                            Object cellage = jTable2.getValueAt(selectedRow, 9);
                            Object cellsta = jTable2.getValueAt(selectedRow, 3);
                            Object celluse = jTable2.getValueAt(selectedRow, 1);

                            String use = "";
                            String com = "";
                            String prio = "";
                            String loc = "";
                            String age = "";
                            String sta = "";
                            String typ = "";
                            String number_prj = "";

                            for(int i = 0; i < rowsuser.size(); i++){
                                String[] rowa = rowsuser.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                if(celluse.equals(value2)){use = value1;}
                            }
                            for(int i = 0; i < rowscompany.size(); i++){
                                String[] rowa = rowscompany.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                if(cellCompany.equals(value2)){com = value1;}
                            }
                            for(int i = 0; i < rows1.size(); i++){
                                String[] rowa = rows1.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                if(cellprio.equals(value2)){prio = value1;}
                            }
                            for(int i = 0; i < rows2.size(); i++){
                                String[] rowa = rows2.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                if(cellloc.equals(value2)){loc = value1;}
                            }
                            for(int i = 0; i < rows3.size(); i++){
                                String[] rowa = rows3.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                if(cellage.equals(value2)){age = value1;}
                            }
                            for(int i = 0; i < rows4.size(); i++){
                                String[] rowa = rows4.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                if(cellsta.equals(value2)){sta = value1;}
                            }
                            for(int i = 0; i < rows5.size(); i++){
                                String[] rowa = rows5.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                if(cellTypelist.equals(value2)){typ = value1;}
                            }
                            if(com.equals("") || 
                                       prio.equals("") || 
                                       loc.equals("") || 
                                       age.equals("") || 
                                       sta.equals("") || 
                                       typ.equals("")|| 
                                       cellDescription.equals("")|| 
                                       cellRemarks.equals("")|| 
                                       use.equals("")){JOptionPane.showMessageDialog(null, "Please enter more details");}
                                else{
                                    try {
                                        //Class.forName("org.postgresql.Driver");
                                        Connection conn = DriverManager.getConnection(url, user, password);

                                        String sql = "UPDATE JOSH.TEMPLATE SET  C_ID = ?, PD = ?, D_1_ID = ?, D_2_ID = ?, D_3_ID = ?,  DESCRIPTION = ?, DD = ?, D_4_ID = ?, D_5_ID = ? , PRJNO = ?, U_ID = ? WHERE T_ID = ?";
        //                                String sql_prjno4 = "SELECT * FROM JOSH.PRJNO WHERE NUMBER = ? AND D_5_ID = ?";

                                        PreparedStatement stmt = conn.prepareStatement(sql);
        //                                PreparedStatement stmt_prjno = conn.prepareStatement(sql_prjno4);
                                        // If the (new_change) is true
                                        String sql_prjno = "SELECT MAX(NUMBER) AS max_number FROM JOSH.PRJNO WHERE D_5_ID = '" + typ + "'";
                                        String sql_prjno2 = "SELECT * FROM JOSH.PRJNO";
                                        String sql_prjno3 = "INSERT INTO JOSH.PRJNO(NUMBER, SLOT, D_5_ID)VALUES (?, ?, ?)";
                                        String sql_prjno5 = "UPDATE JOSH.PRJNO SET SLOT = ? WHERE NUMBER = ? AND D_5_ID = ?";
                                        String sql_prjno6 = "UPDATE JOSH.PRJNO SET SLOT = ? WHERE NUMBER = ? AND D_5_ID = ?";

                                        PreparedStatement stmt3 = conn.prepareStatement(sql_prjno);
                                        PreparedStatement stmt4 = conn.prepareStatement(sql_prjno2);
                                        PreparedStatement stmt5 = conn.prepareStatement(sql_prjno3);
                                        PreparedStatement stmt6 = conn.prepareStatement(sql_prjno5);  
                                        PreparedStatement stmt7 = conn.prepareStatement(sql_prjno6);  

                                        String formattedNumber = String.format("%03d", Integer.parseInt(show_n.getText()));

        //                                stmt_prjno.setInt(1, Integer.parseInt(formattedNumber));
        //                                stmt_prjno.setString(2, typ);

                                        boolean new_change = false;
                                        // All ResultSet
        //                                ResultSet resultSet = stmt_prjno.executeQuery();
                                        ResultSet resultSet2 = stmt3.executeQuery();
                                        ResultSet resultSet3 = stmt4.executeQuery();
                                        // Check if there are results
                                        if(show_prj.getText().equals(typ)){
                                            new_change = false; number_prj = show_n.getText();
                                        }
                                        else{
                                            new_change = true; number_prj = show_n.getText();
                                            stmt7.setInt(1, 0);
                                            stmt7.setInt(2, Integer.parseInt(show_n.getText()));
                                            stmt7.setString(3, show_prj.getText());
        //                                        System.out.println("D_5_ID: " + Value1);
                                            // Execute the update statement
                                            int rowsUpdated = stmt7.executeUpdate();
                                        }

                                        if(new_change == true){

                                            int number = 0;
                                            if (resultSet2.next()) {
                                                int maxNumber = resultSet2.getInt("max_number");
        //                                        System.out.println("Maximum Number for typelist " + typelist + ": " + maxNumber);
                                                 number = maxNumber;
                                            } else {
                                                System.out.println("No maximum number found for typelist " + typ);
                                            }

        //                                    String available_slot = "";

                                            boolean empty_slot = true;
                                            while (resultSet3.next()) {
                                                int Value1 = resultSet3.getInt("NUMBER");
                                                int Value2 = resultSet3.getInt("SLOT");
                                                String Value3 = resultSet3.getString("D_5_ID");
                                                if(Value2 == 0 && Value3.equals(typ)){
                                                    String formattedNumber1 = String.format("%03d", Value1);
                                                    number_prj = formattedNumber1;
                                                    // Set parameters for the update statement
                                                    stmt6.setInt(1, 1);
                                                    stmt6.setInt(2, Value1);
                                                    stmt6.setString(3, typ);
            //                                        System.out.println("D_5_ID: " + Value1);
                                                    // Execute the update statement
                                                    int rowsUpdated = stmt6.executeUpdate();

                                                    if (rowsUpdated > 0) {
                                                        //System.out.println("Update successful for user with ID: " + userIdToUpdate);
                            //                            JOptionPane.showMessageDialog(null, "Update successful!");
                                                    } else {
                                                        //System.out.println("No user found with ID: " + userIdToUpdate);
                            //                            JOptionPane.showMessageDialog(null, "No user found with \"priority\" ");
                                                    }
                                                    empty_slot = false;
                                                    break;
                                                }

                                            }
                                            if(empty_slot == true){
                                                stmt5.setInt(1, number + 1);
                                                stmt5.setInt(2, 1);
                                                stmt5.setString(3, typ);
                                                String formattedNumber1 = String.format("%03d", number + 1);
                                                number_prj = String.valueOf(formattedNumber1);
                                                // Execute the INSERT statement
                                                int rowsAffected = stmt5.executeUpdate();

                                                if (rowsAffected > 0) {
                                                    System.out.println("SLOT inserted successfully!");
                                                }
                                            }
                                        }

                                        // Set parameters for the PreparedStatement
                                        stmt.setString(1, (String) com);
                                        stmt.setString(2, (String) cellDescription);
                                        stmt.setString(3, (String) prio);
                                        stmt.setString(4, (String) loc);
                                        stmt.setString(5, (String) age);
                                        stmt.setString(6, (String) cellRemarks);
                                        stmt.setString(7, (String) cellDue);
                                        stmt.setString(8, (String) sta);
                                        stmt.setString(9, (String) typ);
                                        stmt.setString(10, number_prj);
                                        stmt.setString(11, use);
                                        stmt.setString(12, String.valueOf(cellId));

                                        functionAdd(use, String.valueOf(cellId));
                                        // Execute the INSERT statement
                                        int rowsAffected = stmt.executeUpdate();

                                        if (rowsAffected > 0) {
                                            System.out.println("Data updated successfully!");
                                        }
                                        // Auto refresh
                                        String txt = "[Change2]"+"["+ supername.getText() +"]";
                                        add_His(txt);
                                        // Close the resources
                                        resultSet2.close();
                                        resultSet3.close();
                                        stmt.close();
                                        stmt3.close();
                                        stmt4.close();
                                        stmt5.close();
                                        stmt6.close();
                                        stmt7.close();
                                        conn.close();

                                    } catch (SQLException ef) {
                                        System.out.println("Error: " + ef.getMessage());
                                    }
                            }
                            Add_Edit.setVisible(false);
                            Template();
                        }
                        
                        else{
                                int a = getColumnIndexByName(jTable2, "Company");
                                int b = getColumnIndexByName(jTable2, "Project Description");
                                int c = getColumnIndexByName(jTable2, "Priority");
                                int d = getColumnIndexByName(jTable2, "Location");
                                int ee = getColumnIndexByName(jTable2, "Agency");
                                int f = getColumnIndexByName(jTable2, "Remarks");
                                int g = getColumnIndexByName(jTable2, "Due Date");
                                int h = getColumnIndexByName(jTable2, "Status");
                                int ii = getColumnIndexByName(jTable2, "Types");
                                Object cellCompany = jTable2.getValueAt(selectedRow, a);
                                Object cellDescription = jTable2.getValueAt(selectedRow, b);
                                Object cellPriority = jTable2.getValueAt(selectedRow, c);
                                Object cellLocation = jTable2.getValueAt(selectedRow, d);
                                Object cellAgency = jTable2.getValueAt(selectedRow, ee);
                                Object cellRemarks = jTable2.getValueAt(selectedRow, f);
                                Object cellDue = jTable2.getValueAt(selectedRow, g);
                                Object cellStatus = jTable2.getValueAt(selectedRow, h);
                                Object cellTypelist = jTable2.getValueAt(selectedRow, ii);
                                String com = "";
                                String pri = "";
                                String loc = "";
                                String age = "";
                                String sta = "";
                                String typelist = "";

                                for(int i = 0; i < rowscompany.size(); i++){
                                    String[] rowa = rowscompany.get(i);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(cellCompany.equals(value2)){com = value1;}
                                }
                                for(int i = 0; i < rows1.size(); i++){
                                    String[] rowa = rows1.get(i);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(cellPriority.equals(value2)){pri = value1;}
                                }
                                for(int i = 0; i < rows2.size(); i++){
                                    String[] rowa = rows2.get(i);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(cellLocation.equals(value2)){loc = value1;}
                                }
                                for(int i = 0; i < rows3.size(); i++){
                                    String[] rowa = rows3.get(i);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(cellAgency.equals(value2)){age = value1;}
                                }
                                for(int i = 0; i < rows4.size(); i++){
                                    String[] rowa = rows4.get(i);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(cellStatus.equals(value2)){sta = value1;}
                                }
                                for(int i = 0; i < rows5.size(); i++){
                                    String[] rowa = rows5.get(i);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(cellTypelist.equals(value2)){typelist = value1;}
                                }
                                if(com.equals("") || 
                                       pri.equals("") || 
                                       loc.equals("") || 
                                       age.equals("") || 
                                       sta.equals("") || 
                                       typelist.equals("")|| 
                                       cellDescription.equals("")|| 
                                       cellRemarks.equals("")|| 
                                       cellDue.equals(" DATE ")){JOptionPane.showMessageDialog(null, "Please enter more details");}
                                else{
                                try {
                                    
                                    //Class.forName("org.postgresql.Driver");
                                    Connection conn = DriverManager.getConnection(url, user, password);

                                    //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
                                    String sql = "INSERT INTO JOSH.TEMPLATE(T_NO, T_ID, U_ID, C_ID, PD, DATE, PRJNO, D_1_ID, D_2_ID, D_3_ID, DESCRIPTION, DCREATED, DD, DU, D_4_ID, APPROVAL, D_5_ID)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                    String sql2 = "SELECT * FROM JOSH.TEMPLATE WHERE T_NO = (SELECT MAX(T_NO) FROM JOSH.TEMPLATE)";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                                    // SELECT MAX(NUMBER) FROM JOSH.PRJNO WHERE D_5_ID = 'someValue'; (SELECT MAX NO. WHERE EQUAL TO D_5_ID)
                                    // SELECT * FROM JOSH.PRJNO ORDER BY NUMBER ASC; (SORTED NUMBER)
    //                                String sql_prjno = "SELECT * FROM JOSH.PRJNO WHERE NUMBER = (SELECT MAX(NUMBER) FROM JOSH.PRJNO)";
    //                                String sql_prjno2 = "SELECT * FROM JOSH.PRJNO ORDER BY NUMBER ASC";
    //                                String sql_prjno4 = "UPDATE JOSH.PRJNO SET SLOT = ? WHERE NUMBER = ?";
                                    String sql_prjno = "SELECT MAX(NUMBER) AS max_number FROM JOSH.PRJNO WHERE D_5_ID = '" + typelist + "'";
                                    String sql_prjno2 = "SELECT * FROM JOSH.PRJNO";
                                    String sql_prjno3 = "INSERT INTO JOSH.PRJNO(NUMBER, SLOT, D_5_ID)VALUES (?, ?, ?)";
                                    String sql_prjno4 = "UPDATE JOSH.PRJNO SET SLOT = ? WHERE NUMBER = ? AND D_5_ID = ?";

                                    PreparedStatement stmt3 = conn.prepareStatement(sql_prjno);
                                    PreparedStatement stmt4 = conn.prepareStatement(sql_prjno2);
                                    PreparedStatement stmt5 = conn.prepareStatement(sql_prjno3);
                                    PreparedStatement stmt6 = conn.prepareStatement(sql_prjno4);    


                                    ResultSet resultSetCheck = stmt2.executeQuery();
                                    int num = 0;
                                    if (resultSetCheck.next()) {
                                        int lastValue = resultSetCheck.getInt("T_NO");
                                        num = lastValue;
                                        System.out.println("Last value of T_NO column: " + lastValue);
                                        // Use the lastValue variable as needed
                                    }

                                    ResultSet resultSet2 = stmt3.executeQuery();
                                    int number = 0;
                                    if (resultSet2.next()) {
                                        int maxNumber = resultSet2.getInt("max_number");
                                        System.out.println("Maximum Number for typelist " + typelist + ": " + maxNumber);
                                         number = maxNumber;
                                    } else {
                                        System.out.println("No maximum number found for typelist " + typelist);
                                    }

                                    String available_slot = "";

                                    ResultSet resultSet3 = stmt4.executeQuery();
                                    boolean empty_slot = true;
                                    while (resultSet3.next()) {
                                        int Value1 = resultSet3.getInt("NUMBER");
                                        int Value2 = resultSet3.getInt("SLOT");
                                        String Value3 = resultSet3.getString("D_5_ID");
                                        if(Value2 == 0 && Value3.equals(typelist)){
                                            String formattedNumber = String.format("%03d", Value1);
                                            available_slot = formattedNumber;
                                            // Set parameters for the update statement
                                            stmt6.setInt(1, 1);
                                            stmt6.setInt(2, Value1);
                                            stmt6.setString(3, typelist);
    //                                        System.out.println("D_5_ID: " + Value1);
                                            // Execute the update statement
                                            int rowsUpdated = stmt6.executeUpdate();

                                            if (rowsUpdated > 0) {
                                                //System.out.println("Update successful for user with ID: " + userIdToUpdate);
                    //                            JOptionPane.showMessageDialog(null, "Update successful!");
                                            } else {
                                                //System.out.println("No user found with ID: " + userIdToUpdate);
                    //                            JOptionPane.showMessageDialog(null, "No user found with \"priority\" ");
                                            }
                                            empty_slot = false;
                                            break;
                                        }

                                    }
                                    if(empty_slot == true){
                                        stmt5.setInt(1, number + 1);
                                        stmt5.setInt(2, 1);
                                        stmt5.setString(3, typelist);
                                        String formattedNumber = String.format("%03d", number + 1);
                                        available_slot = String.valueOf(formattedNumber);
                                        // Execute the INSERT statement
                                        int rowsAffected = stmt5.executeUpdate();

                                        if (rowsAffected > 0) {
                                            System.out.println("SLOT inserted successfully!");
                                        }
                                    }


                    //                // Set parameters for the PreparedStatement
                                    stmt.setInt(1, num + 1);
                                    stmt.setString(2, "T_"+String.valueOf(num + 1));
                                    stmt.setString(3, String.valueOf(users.getText())); // user (DONE)
                                    stmt.setString(4, String.valueOf(com)); // company  (DONE)
                                    stmt.setString(5, String.valueOf(cellDescription)); // project description (DONE)
                                    stmt.setString(6, String.valueOf(date)); // date for proj#
                                    stmt.setString(7, String.valueOf(available_slot)); // proj#
                                    stmt.setString(8, String.valueOf(pri)); // priority (DONE)
                                    stmt.setString(9, String.valueOf(loc)); // location (DONE)
                                    stmt.setString(10, String.valueOf(age)); // agency (DONE)
                                    stmt.setString(11, String.valueOf(cellRemarks)); // remarks (DONE)
                                    stmt.setString(12, String.valueOf(datecreated)); // date created 
                                    stmt.setString(13, String.valueOf(cellDue));  // due date (DONE)
                                    stmt.setString(14, String.valueOf("None")); // date updated
                                    stmt.setString(15, String.valueOf(sta)); // status (DONE)
                                    stmt.setBoolean(16, false); 
                                    stmt.setString(17, String.valueOf(typelist)); // D_5_ID (DONE)

                                    // Execute the INSERT statement
                                    int rowsAffected = stmt.executeUpdate();

                                    if (rowsAffected > 0) {
                                        System.out.println("Data inserted successfully!");
                                    }

                                    // Auto refresh
                                    String txt = "[Change2]"+"["+ supername.getText() +"]";
                                    add_His(txt);
                                    // Close the resources
                                    resultSet2.close();
                                    resultSet3.close();
                                    resultSetCheck.close();
                                    stmt2.close();
                                    stmt3.close();
                                    stmt4.close();
                                    stmt5.close();
                                    stmt6.close();
                                    stmt.close();
                                    conn.close();
                                    DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

                                    if (selectedRow != -1) { // Check if any row is selected
                                        model.removeRow(selectedRow); // Remove the selected row
                                    } else {
                                        // Handle case when no row is selected
                                        JOptionPane.showMessageDialog(null, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
                                    }
                                    Template();
                                    int rowCount = model.getRowCount();
                                    SwingUtilities.invokeLater(() -> {
                                    JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
                                    verticalScrollBar.setValue(verticalScrollBar.getMaximum());

                                    // Set selection/highlight on the newly added row
                                    if (rowCount >= 0) { // Check if there was at least one row before adding
                                        int lastRow = jTable1.getRowCount() - 1; // Get the index of the last row
                                        jTable1.setRowSelectionInterval(lastRow, lastRow); // Select the last row
                                            }
                                    });
                                    
                                } catch (SQLException de) {
                                    System.out.println("Error: " + de.getMessage());
                                }
                                }
                                
                                
                    }
                        

                    
                    }
                }
                
            }
        });
    }
    
    public void functionAdd(String username, String iD){
        try {
                //Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);
                String sqlact = "INSERT INTO JOSH.ACTIVITIES(A_NO, A_ID, T_ID, NAME, DATE_TIME, COMMENT, COMMENT2)VALUES (?, ?, ?, ?, ?, ?, ?)";
                String sql2act = "SELECT * FROM JOSH.ACTIVITIES WHERE A_NO = (SELECT MAX(A_NO) FROM JOSH.TEMPLATE)";
                PreparedStatement stmtact = conn.prepareStatement(sqlact);
                PreparedStatement stmt2act = conn.prepareStatement(sql2act);

                ResultSet resultSetact = stmt2act.executeQuery();
                int num = 0;
                if (resultSetact.next()) {
                    int lastValue = resultSetact.getInt("A_NO");
                    num = lastValue;
                    System.out.println("Last value of A_NO column: " + lastValue);
                    // Use the lastValue variable as needed
                }
                String get_user = "";
                for(int b = 0; b < rowsuser.size(); b++){
                    String[] rowa3 = rowsuser.get(b);
                    String v1 = rowa3[0];
                    String v2 = rowa3[1];
                    if(v1.equals(username)){get_user = v2;}
                }
                stmtact.setInt(1, num + 1);
                stmtact.setString(2, "A_"+String.valueOf(num + 1));
                stmtact.setString(3, String.valueOf(iD));
                stmtact.setString(4, String.valueOf("Change User"));
                stmtact.setString(5, String.valueOf(datechat+" - "+O_time));
                stmtact.setString(6, String.valueOf(show_name.getText()));
                stmtact.setString(7, String.valueOf(get_user));
                int rowsAffected = stmtact.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data updated successfully!");
                }
                
                // Close the resources
                resultSetact.close();
                stmt2act.close();
                stmtact.close();
                conn.close();

            } catch (SQLException oe) {
                System.out.println("Error: " + oe.getMessage());
            }
    }
    // Buttons for edit/delete
    public class AcceptRejectPane extends JPanel {

    private JButton Delete;
    private JButton Edit;
    private String state;

    public AcceptRejectPane() {
        setLayout(new GridBagLayout());
        ImageIcon printIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/IMG/delete.png"))); // Replace "print_icon.png" with your icon file path
        ImageIcon printIcon2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/IMG/pen.png"))); // Replace "print_icon.png" with your icon file path
        Delete = new JButton("");
        Delete.setActionCommand("Delete");
        Delete.setSize(200, 100);
        Delete.setIcon(printIcon);
        Edit = new JButton("");
        Edit.setIcon(printIcon2);
        Edit.setSize(200, 100);
        Edit.setActionCommand("Edit");

        add(Delete);
        add(Edit);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = e.getActionCommand();

                if(state.equals("Delete")){
                    if(jLabel2.isVisible()){JOptionPane.showMessageDialog(null, "Refresh Needed");}
                    else{
                    int id = getColumnIndexByName(jTable1, "ID");
                    int usersname = getColumnIndexByName(jTable1, "User");
                    int prjno = getColumnIndexByName(jTable1, "Proj #");
                    int approval = getColumnIndexByName(jTable1, "Approval");
                    int prj = getColumnIndexByName(jTable1, "Project Description");
                    int desc = getColumnIndexByName(jTable1, "Remarks");
                    int row = jTable1.convertRowIndexToModel(jTable1.getEditingRow());
                    Object o = jTable1.getModel().getValueAt(row, id);
                    Object ouser = jTable1.getModel().getValueAt(row, usersname);
                    Object p = jTable1.getModel().getValueAt(row, prjno);
                    Object a = jTable1.getModel().getValueAt(row, approval);
                    if (a.equals("Approved") && utype.getText().equals("User")) {
                        JOptionPane.showMessageDialog(null, "Already been approved!");
                    } else if (!supername.getText().equals(ouser) && utype.getText().equals("User")) {
                        JOptionPane.showMessageDialog(null, "This isn't your project list");
                    }
                    else{
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record of "+p+" ?",
                            "Confirmation Message", JOptionPane.YES_OPTION);
                    if(result == JOptionPane.YES_OPTION) {
                        try {
                                Connection conn = DriverManager.getConnection(url, user, password);

                                String sql = "UPDATE JOSH.TEMPLATE SET APPROVAL = ? WHERE T_ID = ?";

                                String sql_template = "SELECT * FROM JOSH.TEMPLATE";
//                                String sqlslot = "UPDATE JOSH.PRJNO SET SLOT = ? WHERE NUMBER = ?";
                                String sqlslot = "UPDATE JOSH.PRJNO SET SLOT = ? WHERE NUMBER = ? AND D_5_ID = ?";
                                String sql_delete = "DELETE FROM JOSH.TEMPLATE WHERE T_ID = ?";

                                PreparedStatement stmt = conn.prepareStatement(sql);
                                PreparedStatement stmt_template = conn.prepareStatement(sql_template);
                                PreparedStatement stmtslot = conn.prepareStatement(sqlslot);
                                PreparedStatement stmtdel = conn.prepareStatement(sql_delete);

                                ResultSet resultSet = stmt_template.executeQuery();
                                while (resultSet.next()) {
                                    String V = resultSet.getString("T_ID");
                                    String V2 = resultSet.getString("PRJNO");
                                    String V3 = resultSet.getString("D_5_ID");
                                    int originalNumber = Integer.parseInt(V2);
                                    if(V.equals(o)){
                                        stmtslot.setInt(1, 0);
                                        stmtslot.setInt(2, originalNumber); 
                                        stmtslot.setString(3, V3); 
                                        
                                        int rowsUpdated = stmtslot.executeUpdate();

                                        stmtdel.setString(1, String.valueOf(o)); 
                                        int rowsUpdated2 = stmtdel.executeUpdate();
                                    }
                                    // Use the lastValue variable as needed
                                }
                                // Auto refresh
                                String txt = "[Change2]"+"["+ supername.getText() +"]";
                                add_His(txt);
                                // Close the resources
                                resultSet.close();
                                stmt.close();
                                stmtslot.close();
                                stmtdel.close();
                                conn.close();

                            } catch (SQLException er) {
                                System.out.println("Error: " + er.getMessage());
                            }
                        Template();
                        }
                    }
                }
                }
                
                else if(state.equals("Edit")){
                    if(jLabel2.isVisible()){JOptionPane.showMessageDialog(null, "Refresh Needed");}
                    else{
                    int id = getColumnIndexByName(jTable1, "ID");
                    int usersname = getColumnIndexByName(jTable1, "User");
                    int comp = getColumnIndexByName(jTable1, "Company");
                    int prio = getColumnIndexByName(jTable1, "Priority");
                    int loc = getColumnIndexByName(jTable1, "Location");
                    int ag = getColumnIndexByName(jTable1, "Agency");
                    int stat = getColumnIndexByName(jTable1, "Status");
                    int prj = getColumnIndexByName(jTable1, "Project Description");
                    int desc = getColumnIndexByName(jTable1, "Remarks");
                    int approval = getColumnIndexByName(jTable1, "Approval");
                    int type = getColumnIndexByName(jTable1, "Type");
                    int dd = getColumnIndexByName(jTable1, "Due Date");
                    int no = getColumnIndexByName(jTable1, "#");
                    int prjno = getColumnIndexByName(jTable1, "Proj #");
                    
                    int row = jTable1.convertRowIndexToModel(jTable1.getEditingRow());
                    Object o = jTable1.getModel().getValueAt(row, id);
                    Object ouser = jTable1.getModel().getValueAt(row, usersname);
                    Object company = jTable1.getModel().getValueAt(row, comp);
                    Object priority = jTable1.getModel().getValueAt(row, prio);
                    Object location = jTable1.getModel().getValueAt(row, loc);
                    Object agency = jTable1.getModel().getValueAt(row, ag);
                    Object status = jTable1.getModel().getValueAt(row, stat);
                    Object prjd = jTable1.getModel().getValueAt(row, prj);
                    Object description = jTable1.getModel().getValueAt(row, desc);
                    Object a = jTable1.getModel().getValueAt(row, approval);
                    Object typ = jTable1.getModel().getValueAt(row, type);
                    Object ddate = jTable1.getModel().getValueAt(row, dd);
                    Object num = jTable1.getModel().getValueAt(row, no);
                    Object prno = jTable1.getModel().getValueAt(row, prjno);
                    
                    if (a.equals("Approved") && utype.getText().equals("User")) {
                        JOptionPane.showMessageDialog(null, "Already been approved!");
                    } else if (!supername.getText().equals(ouser) && utype.getText().equals("User")) {
                        JOptionPane.showMessageDialog(null, "This isn't your project list");
                    } else {
                        addrow.setVisible(false);
                        removerow.setVisible(false);
                        Add_Edit.setVisible(true);
                        Add_Edit.setBackground(new Color(51,153,255));
                        type_level.setForeground(new Color(255,255,255));
                        type_level.setText("Edit project");
                        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                        model.setRowCount(0);
                        int rows = jTable2.getRowCount();
                        // Table model alignment
                        jTable2.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());
                        jTable2.getColumnModel().getColumn(0).setPreferredWidth(10);
                        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                        jTable2.setDefaultRenderer(String.class, centerRenderer);
                        jTable2.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
                        String name = "";
                        String typelist = "";
                        for(int i = 0; i < rowsuser.size(); i++){
                            String[] rowa = rowsuser.get(i);
                            String value1 = rowa[0];
                            String value2 = rowa[1];
                            if(users.getText().equals(value1)){name = value2;}
                        }
                        model.addRow(new Object[]{o, ouser, company, status , priority  , typ, num, prno, prjd, agency, location, description ,ddate,"SAVE UPDATE"});
                        show_n.setText((String)num);
                        show_name.setText((String)ouser);
                        
                        TableColumn typColumn = jTable2.getColumnModel().getColumn(5);
                        
                        List<String> secondColumnValues = extractSecondColumn(rowsuser);
                        jTable2.getColumnModel().getColumn(1).setCellEditor(new FilteredInputCellEditor(secondColumnValues));
                        
                        List<String> secondColumnValues1 = extractSecondColumn(rowscompany);
                        jTable2.getColumnModel().getColumn(2).setCellEditor(new FilteredInputCellEditor(secondColumnValues1));

                        List<String> secondColumnValues2 = extractSecondColumn(rows1);
                        jTable2.getColumnModel().getColumn(4).setCellEditor(new FilteredInputCellEditor(secondColumnValues2));

                        List<String> secondColumnValues3 = extractSecondColumn(rows2);
                        jTable2.getColumnModel().getColumn(10).setCellEditor(new FilteredInputCellEditor(secondColumnValues3));

                        List<String> secondColumnValues4 = extractSecondColumn(rows3);
                        jTable2.getColumnModel().getColumn(9).setCellEditor(new FilteredInputCellEditor(secondColumnValues4));

                        List<String> secondColumnValues5 = extractSecondColumn(rows4);
                        jTable2.getColumnModel().getColumn(3).setCellEditor(new FilteredInputCellEditor(secondColumnValues5));

                        jTable2.getColumnModel().getColumn(13).setCellEditor(nonEditableEditor); 
                        if(utype.getText().equals("User")){jTable2.getColumnModel().getColumn(1).setCellEditor(nonEditableEditor); }
                        
                        jTable2.getColumnModel().getColumn(6).setCellEditor(nonEditableEditor); 
                        jTable2.getColumnModel().getColumn(7).setCellEditor(nonEditableEditor); 
                        jTable2.getColumnModel().getColumn(8).setCellEditor(nonEditableEditor); 
                        jTable2.getColumnModel().getColumn(11).setCellEditor(nonEditableEditor); 
                        jTable2.getColumnModel().getColumn(12).setCellEditor(nonEditableEditor); 
                        jTable2.getColumnModel().getColumn(13).setCellEditor(nonEditableEditor); 

                        JComboBox comboBoxTyp = new JComboBox();
                        for(int i = 0; i < rows5.size(); i++){
                            String[] rowa = rows5.get(i);
                            String value1 = rowa[0];
                            String value2 = rowa[1];
                            comboBoxTyp.addItem(value2);
                            if(typ.equals(value2)){show_prj.setText((String)value1);}
                        }
                        
                        typColumn.setCellEditor(new DefaultCellEditor(comboBoxTyp));
//                       
                        
                        int columnToColor = 4; // prio
                        int columnToColor2 = 10; // location
                        int columnToColor3 = 9; // agency
                        int columnToColor4 = 3; // status
                        int columnToColor5 = 13; // Save update
                        TableCellRenderer cellRenderer = new TableCellRenderer() {
                            @Override
                            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                                JLabel cell = new JLabel(value != null ? value.toString() : "");
                                        // Set the background color for the specified cell
                                        if (column == columnToColor) {
                                            cell.setOpaque(true);

                                            for(int i = 0; i < rows1.size(); i++){
                                                String[] rowa = rows1.get(i);
                                                String value1 = rowa[0];
                                                String value2 = rowa[1];
                                                String value3 = rowa[2];

                                                String[] parts = value3.toString().split(",");
                                                int a = Integer.parseInt(parts[0]);
                                                int b = Integer.parseInt(parts[1]);
                                                int c = Integer.parseInt(parts[2]);
                                                Border blackline = BorderFactory.createLineBorder(new Color(153,153,153));
                                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
                                                else if(cell.getText().equals(value1)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
                                            }

                                        }
                                        else if (column == columnToColor2) {
                //                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}

                                            cell.setOpaque(true);
                                            for(int i = 0; i < rows2.size(); i++){
                                                String[] rowa = rows2.get(i);
                                                String value1 = rowa[0];
                                                String value2 = rowa[1];
                                                String value3 = rowa[2];

                                                String[] parts = value3.toString().split(",");
                                                int a = Integer.parseInt(parts[0]);
                                                int b = Integer.parseInt(parts[1]);
                                                int c = Integer.parseInt(parts[2]);
                                                Border blackline = BorderFactory.createLineBorder(new Color(153,153,153));
                                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
                                                else if(cell.getText().equals(value1)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
                                            }
                                        }
                                        else if (column == columnToColor3) {
                //                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}

                                            cell.setOpaque(true);
                                            for(int i = 0; i < rows3.size(); i++){
                                                String[] rowa = rows3.get(i);
                                                String value1 = rowa[0];
                                                String value2 = rowa[1];
                                                String value3 = rowa[2];

                                                String[] parts = value3.toString().split(",");
                                                int a = Integer.parseInt(parts[0]);
                                                int b = Integer.parseInt(parts[1]);
                                                int c = Integer.parseInt(parts[2]);
                                                Border blackline = BorderFactory.createLineBorder(new Color(153,153,153));
                                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
                                                else if(cell.getText().equals(value1)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
                                            }
                                        }
                                        else if (column == columnToColor4) {
                //                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}

                                            cell.setOpaque(true);
                                            for(int i = 0; i < rows4.size(); i++){
                                                String[] rowa = rows4.get(i);
                                                String value1 = rowa[0];
                                                String value2 = rowa[1];
                                                String value3 = rowa[2];

                                                String[] parts = value3.toString().split(",");
                                                int a = Integer.parseInt(parts[0]);
                                                int b = Integer.parseInt(parts[1]);
                                                int c = Integer.parseInt(parts[2]);
                                                Border blackline = BorderFactory.createLineBorder(new Color(153,153,153));
                                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
                                                else if(cell.getText().equals(value1)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
                                            }
                                        }
                                        else if (column == columnToColor5) {
                                            Border blackline = BorderFactory.createLineBorder(new Color(255,255,255));
                                            cell.setBorder(blackline);
                                            cell.setOpaque(true);
                                            cell.setHorizontalAlignment(SwingConstants.CENTER);
                                            cell.setText("SAVE UPDATE"); 
                                            cell.setBackground(new Color(0, 153, 255));
                                            cell.setForeground(Color.WHITE);
                                        }

                                return cell;
                            }
                        };
                        jTable2.getColumnModel().getColumn(columnToColor).setCellRenderer(cellRenderer);
                        jTable2.getColumnModel().getColumn(columnToColor2).setCellRenderer(cellRenderer);
                        jTable2.getColumnModel().getColumn(columnToColor3).setCellRenderer(cellRenderer);
                        jTable2.getColumnModel().getColumn(columnToColor4).setCellRenderer(cellRenderer);
                        jTable2.getColumnModel().getColumn(columnToColor5).setCellRenderer(cellRenderer);
                        
                    }
                }
                }

                
            }
        };

        Delete.addActionListener(listener);
        Edit.addActionListener(listener);
    }

    public void addActionListener(ActionListener listener) {
        Delete.addActionListener(listener);
        Edit.addActionListener(listener);
    }

    public String getState() {
        return state;
    }
    }

    public class AcceptRejectRenderer extends DefaultTableCellRenderer {

        private AcceptRejectPane acceptRejectPane;

        public AcceptRejectRenderer() {
            acceptRejectPane = new AcceptRejectPane();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                acceptRejectPane.setBackground(table.getSelectionBackground());
            } else {
                acceptRejectPane.setBackground(table.getBackground());
            }
            return acceptRejectPane;
        }
    }

    public class AcceptRejectEditor extends AbstractCellEditor implements TableCellEditor {

        private AcceptRejectPane acceptRejectPane;

        public AcceptRejectEditor() {
            acceptRejectPane = new AcceptRejectPane();
            acceptRejectPane.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            stopCellEditing();
                        }
                    });
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return acceptRejectPane.getState();
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                acceptRejectPane.setBackground(table.getSelectionBackground());
            } else {
                acceptRejectPane.setBackground(table.getBackground());
            }
            return acceptRejectPane;
        }
    }
    // End
    
    public void add_db(){
         
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM JOSH.D_1";
            String sql2 = "SELECT * FROM JOSH.D_2";
            String sql3 = "SELECT * FROM JOSH.D_3";
            String sql4 = "SELECT * FROM JOSH.D_4";
            String sql5 = "SELECT * FROM JOSH.D_5";
            String sqlcom = "SELECT * FROM JOSH.COMPANY";
            String sqluser = "SELECT * FROM JOSH.USERS";
            String sqlacti = "SELECT * FROM JOSH.ACTIVITIES";
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            PreparedStatement stmt3 = conn.prepareStatement(sql3);
            PreparedStatement stmt4 = conn.prepareStatement(sql4);
            PreparedStatement stmt6 = conn.prepareStatement(sql5);
            PreparedStatement stmtCom = conn.prepareStatement(sqlcom);
            PreparedStatement stmt5 = conn.prepareStatement(sqluser);
            PreparedStatement stmtacti = conn.prepareStatement(sqlacti);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String values = resultSet.getString("D_1_ID");
                String values2 = resultSet.getString("PRIORITY");
                String values3 = resultSet.getString("P_COLOR");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3};
                rows1.add(newRow);
            }
            ResultSet resultSet2 = stmt2.executeQuery();
            while (resultSet2.next()) {
                String values = resultSet2.getString("D_2_ID");
                String values2 = resultSet2.getString("LOCATION");
                String values3 = resultSet2.getString("L_COLOR");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3};
                rows2.add(newRow);
            }
            ResultSet resultSet3 = stmt3.executeQuery();
            while (resultSet3.next()) {
                String values = resultSet3.getString("D_3_ID");
                String values2 = resultSet3.getString("AGENCY");
                String values3 = resultSet3.getString("A_COLOR");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3};
                rows3.add(newRow);
            }
            ResultSet resultSet4 = stmt4.executeQuery();
            while (resultSet4.next()) {
                String values = resultSet4.getString("D_4_ID");
                String values2 = resultSet4.getString("STATUS");
                String values3 = resultSet4.getString("S_COLOR");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3};
                rows4.add(newRow);
            }
            ResultSet resultSetCom = stmtCom.executeQuery();
            while (resultSetCom.next()) {
                String values = resultSetCom.getString("C_ID");
                String values2 = resultSetCom.getString("COMPANY");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2};
                rowscompany.add(newRow);
            }
            ResultSet resultSet5 = stmt5.executeQuery();
            while (resultSet5.next()) {
                String values = resultSet5.getString("U_ID");
                String values2 = resultSet5.getString("USER_NAME");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2};
                rowsuser.add(newRow);
            }
            ResultSet resultSet6 = stmt6.executeQuery();
            while (resultSet6.next()) {
                String values = resultSet6.getString("D_5_ID");
                String values2 = resultSet6.getString("LIST");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2};
                rows5.add(newRow);
            }
            ResultSet resultSetActi = stmtacti.executeQuery();
            while (resultSetActi.next()) {
                String values = resultSetActi.getString("T_ID");
                String values2 = resultSetActi.getString("NAME");
                String values3 = resultSetActi.getString("COMMENT");
                String values4 = resultSetActi.getString("COMMENT2");
                String values5 = resultSetActi.getString("DATE_TIME");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3, values4, values5};
                rowsacti.add(newRow);
            }
            // Close resources
            resultSet.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            resultSet5.close();
            resultSet6.close();
            stmt.close();
            stmt2.close();
            stmt3.close();
            stmt4.close();
            stmt5.close();
            stmt6.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void Template(){
        
        System.out.println(utype.getText());
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.setDefaultRenderer(String.class, centerRenderer);
//        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
//        jTable1.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); {
        model.setRowCount(0);
        // Create a custom renderer for the specific cell
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM JOSH.TEMPLATE";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int rows = jTable1.getRowCount();
                String V = resultSet.getString("T_NO");
                String V2 = resultSet.getString("T_ID");
                String V3 = resultSet.getString("U_ID");
                String V4 = resultSet.getString("C_ID");
                String V5 = resultSet.getString("PD");
                String V6 = resultSet.getString("DATE");
                String V7 = resultSet.getString("PRJNO");
                String V8 = resultSet.getString("D_1_ID");
                String V9 = resultSet.getString("D_2_ID");
                String V10 = resultSet.getString("D_3_ID");
                String V11 = resultSet.getString("DESCRIPTION");
                String V12 = resultSet.getString("DCREATED");
                String V13 = resultSet.getString("DD");
                String V14 = resultSet.getString("DU");
                String V15 = resultSet.getString("D_4_ID");
                String V16 = resultSet.getString("APPROVAL");
                String V17 = resultSet.getString("D_5_ID");
                String company = "";
                String prior = "";
                String loca = "";
                String age = "";
                String stat = "";
                String typelist = "";
                String userlist = "";
                String appro = "";
                for(int i = 0; i < rowscompany.size(); i++){
                    String[] rowa = rowscompany.get(i);
                    String value1 = rowa[0];
                    String value2 = rowa[1];
                    if(V4.equals(value1)){company = value2;}
                }
                for(int i = 0; i < rows1.size(); i++){
                    String[] rowa = rows1.get(i);
                    String value1 = rowa[0];
                    String value2 = rowa[1];
                    if(V8.equals(value1)){prior = value2;}
                }
                for(int i = 0; i < rows2.size(); i++){
                    String[] rowa = rows2.get(i);
                    String value1 = rowa[0];
                    String value2 = rowa[1];
                    if(V9.equals(value1)){loca = value2;}
                }
                for(int i = 0; i < rows3.size(); i++){
                    String[] rowa = rows3.get(i);
                    String value1 = rowa[0];
                    String value2 = rowa[1];
                    if(V10.equals(value1)){age = value2;}
                }
                for(int i = 0; i < rows4.size(); i++){
                    String[] rowa = rows4.get(i);
                    String value1 = rowa[0];
                    String value2 = rowa[1];
                    if(V15.equals(value1)){stat = value2;}
                }
                for(int i = 0; i < rows5.size(); i++){
                    String[] rowa = rows5.get(i);
                    String value1 = rowa[0];
                    String value2 = rowa[1];
                    if(V17.equals(value1)){typelist = value2;}
                }
                if(V16.equals("true")){appro = "Approved";}else{appro = "Pending";}
                
                for(int i = 0; i < rowsuser.size(); i++){
                    String[] rowa = rowsuser.get(i);
                    String value1 = rowa[0];
                    String value2 = rowa[1];
                    if(V3.equals(value1)){userlist = value2;}
                }
                String activity = "";
                for(int i = 0; i < rowsacti.size(); i++){
                    String[] rowa = rowsacti.get(i);
                    String value1 = rowa[0];
                    String value2 = rowa[1];
                    String value3 = rowa[2];
                    String value4 = rowa[3];
                    String value5 = rowa[4];
                    if(value4.equals("asd123") && V2.equals(value1)){activity = value2 + "\t\t     " + value5 + "\n\n" + value3;}
                }
                model.addRow(new Object[]{V2, userlist, company, stat, prior ,typelist , V7 ,V6 , V5, age, loca, V14, V11, V12, V13, activity,appro}); 
                // Use the lastValue variable as needed
                // Set the background color for the specified cell
            }
            jTable1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int selectedRow = jTable1.getSelectedRow();
//                    if (selectedRow != -1) {
//                        edit_btn_1.setEnabled(true);
//                        Object rowData = jTable1.getValueAt(selectedRow, 2);
//                        System.out.println(rowData);
//                    }
                }});

            // Close the resources
            resultSet.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        int priority = getColumnIndexByName(jTable1, "Priority");
        int location = getColumnIndexByName(jTable1, "Location");
        int agency = getColumnIndexByName(jTable1, "Agency");
        int status = getColumnIndexByName(jTable1, "Status");
        int company = getColumnIndexByName(jTable1, "Company");
//        int user = getColumnIndexByName(jTable1, "User");
        int approval = getColumnIndexByName(jTable1, "Approval");
        int columnToColor = priority;
        int columnToColor2 = location;
        int columnToColor3 = agency;
        int columnToColor4 = status;
        int columnToColor5 = company;
//        int columnToColor6 = user;
        int columnToColor7 = approval;
        TableCellRenderer cellRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value != null ? value.toString() : "");
                        // Set the background color for the specified cell
                        if (column == columnToColor) {
                            cell.setOpaque(true);
                            cell.setFont(new Font("Arial", Font.PLAIN, 11));
                            for(int i = 0; i < rows1.size(); i++){
                                String[] rowa = rows1.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(230,232,230));
                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
//                                System.out.println("Values: " + value);
                            }
                           
                        }
                        else if (column == columnToColor2) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            cell.setFont(new Font("Arial", Font.PLAIN, 11));
                            for(int i = 0; i < rows2.size(); i++){
                                String[] rowa = rows2.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(230,232,230));
                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
//                                System.out.println("Values: " + value);
                            }
                        }
                        else if (column == columnToColor3) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            cell.setFont(new Font("Arial", Font.PLAIN, 11));
                            for(int i = 0; i < rows3.size(); i++){
                                String[] rowa = rows3.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(230,232,230));
                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
//                                System.out.println("Values: " + value);
                            }
                        }
                        else if (column == columnToColor4) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            cell.setFont(new Font("Arial", Font.PLAIN, 11));
                            for(int i = 0; i < rows4.size(); i++){
                                String[] rowa = rows4.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(230,232,230));
                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
//                                System.out.println("Values: " + value);
                            }
                        }
                        else if (column == columnToColor5) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            cell.setFont(new Font("Arial", Font.PLAIN, 11));
                            for(int i = 0; i < rowscompany.size(); i++){
                                String[] rowa = rowscompany.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                
                                Border blackline = BorderFactory.createLineBorder(new Color(204,204,204));
                                if(cell.getText().equals(value1)){cell.setText(value2); cell.setBorder(blackline); cell.setHorizontalAlignment(SwingConstants.CENTER);}
//                                System.out.println("Values: " + value);
                            }
                        }
//                        else if (column == columnToColor6) {
////                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
//                            
//                            cell.setOpaque(true);
//                            for(int i = 0; i < rowsuser.size(); i++){
//                                String[] rowa = rowsuser.get(i);
//                                String value1 = rowa[0];
//                                String value2 = rowa[1];
//                                
//                                Border blackline = BorderFactory.createLineBorder(new Color(204,204,204));
//                                if(cell.getText().equals(value1)){cell.setText(value2); cell.setBorder(blackline); cell.setHorizontalAlignment(SwingConstants.CENTER);}
////                                System.out.println("Values: " + value);
//                            }
//                        }
                        else if (column == columnToColor7) {
                            Border blackline = BorderFactory.createLineBorder(new Color(255,255,255, 250));
                            cell.setBorder(blackline);
                            cell.setOpaque(true);
                            cell.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.setFont(new Font("Arial", Font.PLAIN, 11));
                            if(cell.getText().equals("Pending")){
                                cell.setText("Pending"); 
                                cell.setBackground(Color.red);
                                cell.setForeground(Color.WHITE);
                            }
                            else if(cell.getText().equals("Approved")){
                                cell.setText("Approved"); 
                                cell.setBackground(Color.green);
                            }
                        }
                    
                return cell;
            }
        };
        jTable1.getColumnModel().getColumn(columnToColor).setCellRenderer(cellRenderer);
        jTable1.getColumnModel().getColumn(columnToColor2).setCellRenderer(cellRenderer);
        jTable1.getColumnModel().getColumn(columnToColor3).setCellRenderer(cellRenderer);
        jTable1.getColumnModel().getColumn(columnToColor4).setCellRenderer(cellRenderer);
//        jTable1.getColumnModel().getColumn(columnToColor5).setCellRenderer(cellRenderer);
//        jTable1.getColumnModel().getColumn(columnToColor6).setCellRenderer(cellRenderer);
        jTable1.getColumnModel().getColumn(columnToColor7).setCellRenderer(cellRenderer);
    }
    }
    
    private void searchTable() {
    String searchText = search_text.getText().toLowerCase();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    
//    int lastCommentColumnIndex = model.findColumn("Last Comment");
//    int projectDescriptionColumnIndex = model.findColumn("Project Description");
//    int remarkColumnIndex = model.findColumn("Remarks");

    for (int i = model.getRowCount() - 1; i >= 0; i--) {
        boolean rowContainsText = false;
        for (int j = 0; j < model.getColumnCount(); j++) {
//            if (j != lastCommentColumnIndex && j != projectDescriptionColumnIndex && j != remarkColumnIndex) {
                Object value = model.getValueAt(i, j);
                if ((value != null && value.toString().toLowerCase().contains(searchText))) {
                    rowContainsText = true;
                    jTable1.repaint();
                    jTable1.revalidate();
                    break;
                }
//            }
        }
        if (!rowContainsText) {
            model.removeRow(i);
        }
    }
}
    
    //Filter Right Click

    private void showDropDownFilterMenu(int x, int y) {
        if (clickedColumn != -1) {
            Set<String> uniqueValues = getUniqueValues(clickedColumn);
            JPopupMenu popupMenu = new JPopupMenu(); 
            for (String value : uniqueValues) {
                JMenuItem menuItem = new JMenuItem(value);
                
                menuItem.addActionListener(e -> filterTable(clickedColumn, value));
                popupMenu.add(menuItem);
            }
            JMenuItem clearFilterItem = new JMenuItem("Clear Filter");
            clearFilterItem.addActionListener(e -> clearFilter());
            popupMenu.add(clearFilterItem);

            popupMenu.show(jTable1, x, y);
        }
    }

    private Set<String> getUniqueValues(int columnIndex) {
        Set<String> uniqueValues = new HashSet<>();
        for (int row = 0; row < jTable1.getRowCount(); row++) {
            Object value = jTable1.getValueAt(row, columnIndex);
            if (value != null) {
                uniqueValues.add(value.toString());
            }
        }
        return uniqueValues;
    }

//    private void filterTable(int columnIndex, String filterValue) {
//        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); 
//        if (sorter == null) {
//            sorter = new TableRowSorter<>(model);
//            jTable1.setRowSorter(sorter);
//        }
//
//        // Convert the view column index to the model column index
//        int modelColumnIndex = jTable1.convertColumnIndexToModel(columnIndex);
//
//        RowFilter<Object, Object> rowFilter = RowFilter.regexFilter(filterValue, modelColumnIndex);
//        sorter.setRowFilter(rowFilter);
//        filtertext.setText("Filtered: " + filterValue);
////        System.out.println(filterValue);
//
//    }
//    private void filterTable(int columnIndex, String filterValue) {
//        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); 
//        if (sorter == null) {
//            sorter = new TableRowSorter<>(model);
//            jTable1.setRowSorter(sorter);
//        }
//
//        // Convert the view column index to the model column index
//        int modelColumnIndex = jTable1.convertColumnIndexToModel(columnIndex);
//
//        // Get the existing filter
//        RowFilter<Object, Object> existingFilter = (RowFilter<Object, Object>) sorter.getRowFilter();
//
//        // Create a new filter for the current column
//        RowFilter<Object, Object> currentFilter = RowFilter.regexFilter(filterValue, modelColumnIndex);
//
//        // Combine filters using AND logic
//        RowFilter<Object, Object> combinedFilter;
//        if (existingFilter != null) {
//            combinedFilter = RowFilter.andFilter(Arrays.asList(existingFilter, currentFilter));
//        } else {
//            combinedFilter = currentFilter;
//        }
//
//        sorter.setRowFilter(combinedFilter);
//        filtertext.setText("Filtered: " + filterValue);
//    }
    
    private Map<Integer, String> filteredValues = new HashMap<>();

    private void filterTable(int columnIndex, String filterValue) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); 
        if (sorter == null) {
            sorter = new TableRowSorter<>(model);
            jTable1.setRowSorter(sorter);
        }

        // Convert the view column index to the model column index
        int modelColumnIndex = jTable1.convertColumnIndexToModel(columnIndex);

        // Create a RowFilter that filters based on multiple columns
        RowFilter<Object, Object> currentFilter = RowFilter.regexFilter(filterValue, modelColumnIndex);
        RowFilter<Object, Object> combinedFilter = (RowFilter<Object, Object>) sorter.getRowFilter();

        if (combinedFilter != null) {
            // Combine the current filter with the existing filters using AND logic
            combinedFilter = RowFilter.andFilter(Arrays.asList(combinedFilter, currentFilter));
        } else {
            combinedFilter = currentFilter;
        }

        sorter.setRowFilter(combinedFilter);

        // Store the filtered value for the current column
        filteredValues.put(modelColumnIndex, filterValue);

        // Update the filtertext to show the filtered values for all columns
        StringBuilder filterTextBuilder = new StringBuilder("Filtered: ");
        for (Map.Entry<Integer, String> entry : filteredValues.entrySet()) {
            filterTextBuilder.append(entry.getValue()).append(", ");
        }
        filtertext.setText(filterTextBuilder.toString());
    }


    private void clearFilter() {
        if (sorter != null) {
            sorter.setRowFilter(null);
            jTable1.repaint();
            jTable1.revalidate();
            filtertext.setText("");
            filteredValues.clear();

        }
    }
    
    // This will iterate for Company
    class FilteredInputCellEditor extends DefaultCellEditor {
        private JTextField textField;
        private JPopupMenu popupMenu; 
        private JList<String> list; 
        private List<String> secondColumnValues;
        private int currentRow; // Track the current row being edited

        public FilteredInputCellEditor(List<String> values) {
            super(new JTextField());
            secondColumnValues = values;
            textField = (JTextField) editorComponent;

            list = new JList<>(secondColumnValues.toArray(new String[0]));
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        int index = list.locationToIndex(e.getPoint());
                        if (index != -1) {
                            textField.setText(list.getSelectedValue());
                            stopCellEditing();
                        }
                    }
                }
            });

            popupMenu = new JPopupMenu();
            JScrollPane scrollPane = new JScrollPane(list);
            scrollPane.setFocusable(false); // Set scroll pane not focusable
            popupMenu.add(scrollPane);
            popupMenu.setFocusable(false); // Set popup menu not focusable

            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        handleEnterKeyPress();
                    } else {
                        filterAndShowPopup();
                    }
                }
            });
        }

        private void handleEnterKeyPress() {
            String enteredText = textField.getText().trim(); // Trim leading/trailing spaces
            boolean isInList = secondColumnValues.stream()
                    .anyMatch(value -> value.equalsIgnoreCase(enteredText));

            if (!isInList) {
                JOptionPane.showMessageDialog(null, "The text you entered is not in the list", "Error", JOptionPane.ERROR_MESSAGE);
                stopCellEditing();
                textField.setText("");
                lastValidTextMap.clear();
                jTable2.getModel().setValueAt(null, currentRow, jTable2.getSelectedColumn());
            } else {
                lastValidTextMap.put(currentRow, enteredText); // Store the last valid text for the current row
                stopCellEditing();
                textField.setText("");
                lastValidTextMap.clear();
                
                String matchingText = secondColumnValues.stream()
                .filter(value -> value.equalsIgnoreCase(enteredText))
                .findFirst().orElse(null);
                
                jTable2.getModel().setValueAt(matchingText, currentRow, jTable2.getSelectedColumn());
                
            }
        }

        private void filterAndShowPopup() {
            String text = textField.getText().toLowerCase();
            java.util.List<String> filteredList = secondColumnValues.stream()
                    .filter(value -> value.toLowerCase().contains(text))
                    .collect(Collectors.toList());

            if (!filteredList.isEmpty()) {
                list.setListData(filteredList.toArray(new String[0]));
                popupMenu.show(textField, 0, -(textField.getHeight()+110));
            } else {
                textField.setText(""); // Clear the text field if input is not in the list
                popupMenu.setVisible(false);
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            textField.setText(lastValidTextMap.getOrDefault(row, value != null ? value.toString() : ""));
            currentRow = row; // Set the current row being edited
            return textField;
        }

        @Override
        public boolean stopCellEditing() {
            if (!lastValidTextMap.containsKey(currentRow) && !secondColumnValues.contains(textField.getText().toLowerCase())) {
                return false; // Prevent cell editing if the text is not in the list and no valid text stored for the current row
            }
            popupMenu.setVisible(false);
            return super.stopCellEditing();
        }
        // ... rest of your code
    }
    
    private static List<String> extractSecondColumn(List<String[]> rows) {
        List<String> secondColumnValues = new ArrayList<>();

        for (String[] row : rows) {
            if (row.length > 1) {
                secondColumnValues.add(row[1]);
            }
        }

        return secondColumnValues;
    }
    
    TableCellEditor nonEditableEditor = new TableCellEditor() {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                return null; // Return null to make the cell non-editable
            }

            @Override
            public Object getCellEditorValue() {
                return null;
            }

            @Override
            public boolean isCellEditable(EventObject anEvent) {
                return false;
            }

            @Override
            public boolean shouldSelectCell(EventObject anEvent) {
                return false;
            }

            @Override
            public boolean stopCellEditing() {
                return false;
            }

            @Override
            public void cancelCellEditing() {
                // Implementation not needed for non-editable cells
            }

            @Override
            public void addCellEditorListener(CellEditorListener l) {
                // Implementation not needed for non-editable cells
            }

            @Override
            public void removeCellEditorListener(CellEditorListener l) {
                // Implementation not needed for non-editable cells
            }
        };
    
    
    
    private void readExcelData() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                Workbook workbook = new XSSFWorkbook(fileChooser.getSelectedFile());
                Sheet sheet = workbook.getSheetAt(0); // assuming first sheet

                int userColumnIndex = -1, companyColumnIndex = -1, statusColumnIndex = -1,
                        priorityColumnIndex = -1, typeColumnIndex = -1, projNoColumnIndex = -1,
                        prodateColumnIndex = -1, proDeColumnIndex = -1, AgencyColumnIndex = -1,
                        locationColumnIndex = -1, dateUpdatedColumnIndex = -1, remarksColumnIndex = -1,
                        dateCreatedColumnIndex = -1, dueDateColumnIndex = -1;

                // Find column indexes based on column headers
                Row headerRow = sheet.getRow(0);
                for (Cell cell : headerRow) {
                    String cellValue = cell.getStringCellValue();
                    if (cellValue.equalsIgnoreCase("User")) {
                        userColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Company")) {
                        companyColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Status")) {
                        statusColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Priority")) {
                        priorityColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Type")) {
                        typeColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Proj no.")) {
                        projNoColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Proj date")) {
                        prodateColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Project Description")) {
                        proDeColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Agency")) {
                        AgencyColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Location")) {
                        locationColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Date Updated")) {
                        dateUpdatedColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Remarks")) {
                        remarksColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Date Created")) {
                        dateCreatedColumnIndex = cell.getColumnIndex();
                    } else if (cellValue.equalsIgnoreCase("Due Date")) {
                        dueDateColumnIndex = cell.getColumnIndex();
                    }
                }
                DataFormatter dataFormatter = new DataFormatter();
                // Read data based on column indexes
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    String users = row.getCell(userColumnIndex).getStringCellValue();
                    String company = row.getCell(companyColumnIndex).getStringCellValue();
                    String status = row.getCell(statusColumnIndex).getStringCellValue();
                    String priority = row.getCell(priorityColumnIndex).getStringCellValue();
                    String type = row.getCell(typeColumnIndex).getStringCellValue();
                    String projNo = row.getCell(projNoColumnIndex).getStringCellValue();
                    String projDate = row.getCell(prodateColumnIndex).getStringCellValue();
                    String projDescr = row.getCell(proDeColumnIndex).getStringCellValue();
                    String agency = row.getCell(AgencyColumnIndex).getStringCellValue();
                    String location = row.getCell(locationColumnIndex).getStringCellValue();
                    String dateupdated = "";
                    String remarks = row.getCell(remarksColumnIndex).getStringCellValue();
                    String datecreated = "";
                    String duedate = "";
                    
    //                    Cell dateUpdatedCell = row.getCell(dateUpdatedColumnIndex);
//                    if (dateUpdatedCell != null) {
//                        dateupdated = dataFormatter.formatCellValue(dateUpdatedCell);
//                    }
//                    Cell datecreatedCell = row.getCell(dateUpdatedColumnIndex);
//                    if (datecreatedCell != null) {
//                        datecreated = dataFormatter.formatCellValue(datecreatedCell);
//                    }                    
//                    Cell DueDateCell = row.getCell(dateUpdatedColumnIndex);
//                    if (DueDateCell != null) {
//                        duedate = dataFormatter.formatCellValue(DueDateCell);
//                    }
                    Cell dateUpdatedCell = row.getCell(dateUpdatedColumnIndex);
                    if (dateUpdatedCell != null) {
//                        Locale locale = new Locale("en", "US");
//                        DataFormatter dataFormatter2 = new DataFormatter(locale);
//                        String dateUpdated = dataFormatter2.formatCellValue(dateUpdatedCell);
//                        dateupdated = dateUpdated;
                        
                        Date date;
                        date = dateUpdatedCell.getDateCellValue();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = dateFormat.format(date);
                        dateupdated = formattedDate;
                    }
                    Cell datecreatedCell = row.getCell(dateUpdatedColumnIndex);
                    if (dateUpdatedCell != null) {
//                        Locale locale = new Locale("en", "US");
//                        DataFormatter dataFormatter2 = new DataFormatter(locale);
//                        String dateUpdated = dataFormatter2.formatCellValue(datecreatedCell);
//                        datecreated = dateUpdated;
                        
                        Date date;
                        date = datecreatedCell.getDateCellValue();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = dateFormat.format(date);
                        datecreated = formattedDate;
                    }
                    Cell DueDateCell = row.getCell(dateUpdatedColumnIndex);
                    if (dateUpdatedCell != null) {
//                        Locale locale = new Locale("en", "US");
//                        DataFormatter dataFormatter2 = new DataFormatter(locale);
//                        String dateUpdated = dataFormatter2.formatCellValue(DueDateCell);
//                        duedate = dateUpdated;

                        Date date;
                        date = DueDateCell.getDateCellValue();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = dateFormat.format(date);
                        duedate = formattedDate;
                    }
                    
                    
                    
                    
                    
                    

                                String use = "";
                                String com = "";
                                String pri = "";
                                String loc = "";
                                String age = "";
                                String sta = "";
                                String typelist = "";

                                for(int is = 0; is < rowsuser.size(); is++){
                                    String[] rowa = rowsuser.get(is);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(users.equals(value2)){use = value1;}
                                }
                                for(int is = 0; is < rowscompany.size(); is++){
                                    String[] rowa = rowscompany.get(is);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(company.equals(value2)){com = value1;}
                                }
                                for(int is = 0; is < rows1.size(); is++){
                                    String[] rowa = rows1.get(is);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(priority.equals(value2)){pri = value1;}
                                }
                                for(int is = 0; is < rows2.size(); is++){
                                    String[] rowa = rows2.get(is);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(location.equals(value2)){loc = value1;}
                                }
                                for(int is = 0; is < rows3.size(); is++){
                                    String[] rowa = rows3.get(is);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(agency.equals(value2)){age = value1;}
                                }
                                for(int a = 0; a < rows4.size(); a++){
                                    String[] rowa = rows4.get(a);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(status.equals(value2)){sta = value1;}
                                }
                                for(int is = 0; is < rows5.size(); is++){
                                    String[] rowa = rows5.get(is);
                                    String value1 = rowa[0];
                                    String value2 = rowa[1];
                                    if(type.equals(value2)){typelist = value1;}
                                }
                                
//                                System.out.println("User: " + use + ", Company: " + com + ", Status: " + sta
//                            + ", Priority: " + pri+ ", Type: " + type + ", Proj no.: " + projNo + projDate
//                    + projDescr + age + loc + dateupdated + remarks + datecreated + duedate);
                                try {
                                    
                                    //Class.forName("org.postgresql.Driver");
                                    Connection conn = DriverManager.getConnection(url, user, password);

                                    //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
                                    String sql = "INSERT INTO JOSH.TEMPLATE(T_NO, T_ID, U_ID, C_ID, PD, DATE, PRJNO, D_1_ID, D_2_ID, D_3_ID, DESCRIPTION, DCREATED, DD, DU, D_4_ID, APPROVAL, D_5_ID)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                    String sql2 = "SELECT * FROM JOSH.TEMPLATE WHERE T_NO = (SELECT MAX(T_NO) FROM JOSH.TEMPLATE)";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    PreparedStatement stmt2 = conn.prepareStatement(sql2);



                                    ResultSet resultSetCheck = stmt2.executeQuery();
                                    int num = 0;
                                    if (resultSetCheck.next()) {
                                        int lastValue = resultSetCheck.getInt("T_NO");
                                        num = lastValue;
                                        System.out.println("Last value of T_NO column: " + lastValue);
                                        // Use the lastValue variable as needed
                                    }



                    //                // Set parameters for the PreparedStatement
                                    stmt.setInt(1, num + 1);
                                    stmt.setString(2, "T_"+String.valueOf(num + 1));
                                    stmt.setString(3, String.valueOf(use)); // user (DONE)
                                    stmt.setString(4, String.valueOf(com)); // company  (DONE)
                                    stmt.setString(5, String.valueOf(projDescr)); // project description (DONE)
                                    stmt.setString(6, String.valueOf(projNo)); // date for proj#
                                    stmt.setString(7, String.valueOf("0")); // proj#
                                    stmt.setString(8, String.valueOf(pri)); // priority (DONE)
                                    stmt.setString(9, String.valueOf(loc)); // location (DONE)
                                    stmt.setString(10, String.valueOf(age)); // agency (DONE)
                                    stmt.setString(11, String.valueOf(remarks)); // remarks (DONE)
                                    stmt.setString(12, String.valueOf(datecreated)); // date created 
                                    stmt.setString(13, String.valueOf(duedate));  // due date (DONE)
                                    stmt.setString(14, String.valueOf(dateupdated)); // date updated
                                    stmt.setString(15, String.valueOf(sta)); // status (DONE)
                                    stmt.setBoolean(16, true); 
                                    stmt.setString(17, String.valueOf(typelist)); // D_5_ID (DONE)

                                    // Execute the INSERT statement
                                    int rowsAffected = stmt.executeUpdate();

                                    if (rowsAffected > 0) {
                                        System.out.println("Data inserted successfully!");
                                    }

                                    // Close the resources
                                    resultSetCheck.close();
                                    stmt2.close();
                                    stmt.close();
                                    conn.close();

                                    
//                                    Template();
                                    
                                } catch (SQLException de) {
                                    System.out.println("Error: " + de.getMessage());
                                }
                                
                                
                    
                }

                workbook.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void exportTableToExcel() {
        try {
            // Choose a file to save the exported data
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                String filePath = fileChooser.getSelectedFile().getPath() + ".xlsx";

                // Create a new Excel workbook and sheet
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Table Data");

                TableModel model = jTable1.getModel();

                // Write column headers
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < model.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(model.getColumnName(i));
                }

                // Write data
                for (int row = 0; row < model.getRowCount(); row++) {
                    Row dataRow = sheet.createRow(row + 1);
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        Cell cell = dataRow.createCell(col);

                        Object cellValue = model.getValueAt(row, col);
                        if (cellValue != null) {
                            cell.setCellValue(cellValue.toString());
                        } else {
                            // Handle null values, for example, write an empty string
                            cell.setCellValue("");
                        }
                    }
                }

                // Write the workbook to the file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(this, "Data exported to Excel successfully!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Excel(){
      
            // Choose a file to save the exported data
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                String filePath = fileChooser.getSelectedFile().getPath() + ".xlsx";
        
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Custom cell style with background color for the columns
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(248, 248, 255), new DefaultIndexedColorMap()));
        
        // Custom cell style with background color for the first column
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle1.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 153, 204), new DefaultIndexedColorMap()));

        // Custom cell style with background color for the second column
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle2.setFillForegroundColor(new XSSFColor(new java.awt.Color(155, 194, 230), new DefaultIndexedColorMap()));

        // Custom cell style with background color for the third column
        CellStyle cellStyle3 = workbook.createCellStyle();
        cellStyle3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle3.setFillForegroundColor(new XSSFColor(new java.awt.Color(221, 235, 247), new DefaultIndexedColorMap()));
        
        // Custom cell style with background color for the third column
//        CellStyle cellStyle4 = workbook.createCellStyle();
//        cellStyle4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        cellStyle4.setFillForegroundColor(new XSSFColor(new java.awt.Color(249, 208, 23), new DefaultIndexedColorMap()));
        
        // Custom cell style with background color for the third column
//        CellStyle cellStyle5 = workbook.createCellStyle();
//        cellStyle5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        cellStyle5.setFillForegroundColor(new XSSFColor(new java.awt.Color(217,217,217), new DefaultIndexedColorMap()));
        
        // Custom cell style with background color for the third column
        CellStyle cellStyle6 = workbook.createCellStyle();
        cellStyle6.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle6.setFillForegroundColor(new XSSFColor(new java.awt.Color(242,242,242), new DefaultIndexedColorMap()));

        int id = getColumnIndexByName(jTable1, "ID");
        int del_ed = getColumnIndexByName(jTable1, "Delete/Edit");
        int[] excludedColumns = {id, del_ed};
        // Loop through columns 0 to 2 (all columns)
        for (int colIdx = 0; colIdx < jTable1.getColumnCount(); colIdx++) {
            // Get the column text
            boolean excludeColumn = false;
            for (int excludedColumn : excludedColumns) {
                if (colIdx == excludedColumn) {
                    excludeColumn = true;
                    break;
                }
            }

            if (excludeColumn) {
                continue;
            }
            String columnText = jTable1.getColumnName(colIdx);

            // Create a new row in the Excel sheet to store the column text
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                headerRow = sheet.createRow(0);
            }
             // Set the width for column 1 (example: 2000)
//            if (colIdx == 1) {
//                int columnWidthInUnits = 15; // The width is specified in 1/256th of a character width units
//                sheet.setColumnWidth(colIdx, columnWidthInUnits * 256);
//            }

                  
            // Create a new cell in the header row and set its value to the column text
            Cell headerCell = headerRow.createCell(colIdx);
            headerCell.setCellValue(columnText);

            // Set the background color for the header cell based on the column index
           if (colIdx == 1) {
                headerCell.setCellStyle(cellStyle);
            } else if (colIdx == 2) {
                headerCell.setCellStyle(cellStyle);
            }
            else if (colIdx == 3) {
                headerCell.setCellStyle(cellStyle);
            }

            // Iterate through the JTable and export its content to the Excel file for the current column
            for (int rowIdx = 0; rowIdx < jTable1.getRowCount(); rowIdx++) {
                Object value = jTable1.getValueAt(rowIdx, colIdx);
                Row row = sheet.getRow(rowIdx + 1);
                if (row == null) {
                    row = sheet.createRow(rowIdx + 1);
                }
                Cell cell = row.createCell(colIdx);
//                cell.setCellValue(value.toString());
                int priority = getColumnIndexByName(jTable1, "Priority");
                int location = getColumnIndexByName(jTable1, "Location");
                int agency = getColumnIndexByName(jTable1, "Agency");
                int status = getColumnIndexByName(jTable1, "Status");
                int company = getColumnIndexByName(jTable1, "Company");
                int user = getColumnIndexByName(jTable1, "User");
                int approval = getColumnIndexByName(jTable1, "Approval");
                
//                if (colIdx == 1) {
//                    CellStyle wrappedCellStyle = getWrappedCellStyle(workbook);
//                    cell.setCellStyle(wrappedCellStyle);
//                    sheet.setColumnWidth(colIdx, 50 * 256); // Set width to 80 units (256 units per character)
//                } 
//                else if (colIdx == 5) {
//                    CellStyle wrappedCellStyle = getWrappedCellStyle(workbook);
//                    cell.setCellStyle(wrappedCellStyle);
//                    sheet.setColumnWidth(colIdx, 50 * 256); // Set width to 80 units (256 units per character)
//                } 

                // Replace "\n" with line break character (CHAR(10))
                String cellValue = value == null ? "" : value.toString();
                cellValue = cellValue.replace("\n", String.valueOf((char) 10));

                // Set the cell value with line breaks using RichTextString
                RichTextString richText = workbook.getCreationHelper().createRichTextString(cellValue);
                cell.setCellValue(richText);
        
                // Set the background color for the cell based on the value and column index
                
                if (colIdx == status) {
                    if (!value.equals("")) {
                        for(int i = 0; i < rows4.size(); i++){
                                String[] rowa = rows4.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                
                                if (value.equals(value2)) {
                                    CellStyle cellStyle4 = workbook.createCellStyle();
                                    cellStyle4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                    cellStyle4.setFillForegroundColor(new XSSFColor(new java.awt.Color(a,b,c), new DefaultIndexedColorMap())); // Red background
                                    cell.setCellStyle(cellStyle4);
                                }
                        }
                    }
                }
                else if (colIdx == priority) {
                    if (!value.equals("")) {
                        for(int i = 0; i < rows1.size(); i++){
                                String[] rowa = rows1.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                
                                if (value.equals(value2)) {
                                    CellStyle cellStyle5 = workbook.createCellStyle();
                                    cellStyle5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                    cellStyle5.setFillForegroundColor(new XSSFColor(new java.awt.Color(a,b,c), new DefaultIndexedColorMap())); // Red background
                                    cell.setCellStyle(cellStyle5);
                                }
                        }
                    }
                }
                else if (colIdx == agency) {
                    if (!value.equals("")) {
                        for(int i = 0; i < rows3.size(); i++){
                                String[] rowa = rows3.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                
                                if (value.equals(value2)) {
                                    CellStyle cellStyle5 = workbook.createCellStyle();
                                    cellStyle5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                    cellStyle5.setFillForegroundColor(new XSSFColor(new java.awt.Color(a,b,c), new DefaultIndexedColorMap())); // Red background
                                    cell.setCellStyle(cellStyle5);
                                }
                        }
                        
                    }
                }
                else if (colIdx == location) {
                    if (!value.equals("")) {
                        for(int i = 0; i < rows2.size(); i++){
                                String[] rowa = rows2.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                
                                if (value.equals(value2)) {
                                    CellStyle cellStyle5 = workbook.createCellStyle();
                                    cellStyle5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                    cellStyle5.setFillForegroundColor(new XSSFColor(new java.awt.Color(a,b,c), new DefaultIndexedColorMap())); // Red background
                                    cell.setCellStyle(cellStyle5);
                                }
                        }
                        
                    }
                }
                else if (colIdx == approval) {
                    if (!value.equals("")) {
                        if (value.equals("Approved")) {
                            CellStyle cellStyle5 = workbook.createCellStyle();
                            cellStyle5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellStyle5.setFillForegroundColor(new XSSFColor(Color.green, new DefaultIndexedColorMap())); // Red background
                            cell.setCellStyle(cellStyle5);
                        }
                        else{
                            CellStyle cellStyle5 = workbook.createCellStyle();
                            cellStyle5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellStyle5.setFillForegroundColor(new XSSFColor(Color.RED, new DefaultIndexedColorMap())); // Red background
                            cell.setCellStyle(cellStyle5);
                        }
//                        cell.setCellStyle(cellStyle3);
                    }
                }
                
            }
        }

        // Auto-size columns
        for (int colIdx = 0; colIdx < jTable1.getColumnCount(); colIdx++) {
            sheet.autoSizeColumn(colIdx);
        }

        // Save the workbook to a file
//        String userHome = System.getProperty("user.home");
//        String downloadFolder = userHome + File.separator + "Downloads";
//        String fileName = "table_export.xlsx";
//        String filePath = downloadFolder + File.separator + fileName;

//        System.out.println("File path: " + filePath);
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Download complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
            }
        
    }
    
    private static CellStyle getWrappedCellStyle(Workbook workbook) {
    CellStyle style = workbook.createCellStyle();
    style.setWrapText(true);
    return style;
}
    
    
    private static void customizeTableHeader(JTable table) {
        JTableHeader header = table.getTableHeader();

        // Set height of column header
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        // Set font size for column header
        Font headerFont = new Font("Leelawadee UI", Font.BOLD, 12); // Adjust font settings as needed
        header.setFont(headerFont);
        header.setForeground(Color.DARK_GRAY);
        // Set background color for column header
        header.setBackground(new Color(255,255,255));

        // Set default cell renderer for header
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER); // Optional: Center-align text in headers
    }
    
    // AUTO REFRESH
    public void add_His(String data){
        ////// Location of IP
        String filePath2 = name+"\\log\\logfolder\\refresh_a_work.txt";

        StringBuilder parentPathBuilder = new StringBuilder();
        String parentPath = parentPathBuilder.toString();
        String filepath = parentPath + filePath2;
        ////// Add IP into IP_Config (Folder)
        try {
        // Read the file into a list of strings
        List<String> lines2 = Files.readAllLines(Paths.get(filepath));
        // Append the text to the last element of the list
        // Encrypt the original text
        
        lines2.add(data);
        // Write the updated list back to the file
        Files.write(Paths.get(filepath), lines2, StandardOpenOption.WRITE);
        System.out.println("Text added to the last line.");
        } catch (IOException e3) {e3.printStackTrace();} catch (Exception ex) { 
//            Logger.getLogger(Connect_ip.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Refresh 
        // Refresh 
        int delay = 6000; // delay in milliseconds
        Timer timers = new Timer(delay, ee -> {
            // update the label text
                del_added(data);
            ((Timer) ee.getSource()).stop();
        });
        timers.start();
        
    }
    
    public void del_added(String txt){
        String filePath2 = name+"\\log\\logfolder\\refresh_a_work.txt";

        StringBuilder parentPathBuilder = new StringBuilder();
        String parentPath = parentPathBuilder.toString();
        String filepath = parentPath + filePath2;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            StringBuilder content = new StringBuilder();

            System.out.println(filepath);

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(txt)) { // Exclude the second line
                    content.append(line).append(System.lineSeparator());
                }
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(content.toString());
            writer.close();

        } catch (IOException er) {
            er.printStackTrace();
        }catch (Exception ex) {
        }

//        read_ip();
    }
    
    public void Ref(){
        String filePath = name+"\\log\\logfolder\\refresh_a_work.txt";
        String fPath = filePath.replace("\\", "\\\\");
//        String[] pathParts = get2.split("\\\\");
        StringBuilder parentPathBuilder = new StringBuilder();
        String parentPath = parentPathBuilder.toString();
        String filepath = parentPath + filePath;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String date = extractValue(line, "\\[(.*?)\\]");
                String[] parts = line.split("\\s*\\]\\s*\\[\\s*");
                if (parts.length >= 1) {
                    String id = parts[1].replaceAll("\\]", ""); // Remove trailing brackets from ID
//                    jLabel1.setText(date + " = " + id);
//                        System.out.println(date + " and id: "+id);
                   if(!supername.getText().equals(id) && date.equals("Change2")){jLabel2.setVisible(true);}
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int delay = 1000; // delay in milliseconds
            Timer timer = new Timer(delay, e -> {
                // update the label text
                        Ref();
                    ((Timer) e.getSource()).stop();
            });
            timer.start();
    }
    
    private static String extractValue(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
    
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        refresh = new javax.swing.JButton();
        Add_Edit = new v1_tm_projectmanagement.NewPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        type_level = new javax.swing.JLabel();
        addrow = new javax.swing.JButton();
        removerow = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        show_n = new javax.swing.JLabel();
        show_prj = new javax.swing.JLabel();
        show_name = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        newPanel1 = new v1_tm_projectmanagement.NewPanel();
        search_text = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filtertext = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        users.setText("U_5");

        utype.setText("User");

        supername.setText("jLabel1");

        jButton2.setText("Upload Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(supername, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(users, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(utype, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 124, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(users, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(utype, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supername, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton2))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Project List");
        setLocation(new java.awt.Point(38, 20));
        setMinimumSize(new java.awt.Dimension(1295, 634));

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

        jTable1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "User", "Company", "Status", "Priority", "Type", "#", "Proj #", "Project Description", "Agency", "Location", "Date Updated", "Remarks", "Date Created", "Due Date", "Last Comment", "Approval", "Delete/Edit"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setSelectionBackground(new java.awt.Color(0, 153, 255));
        jTable1.setShowGrid(false);
        jTable1.setShowHorizontalLines(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleName("");

        refresh.setForeground(new java.awt.Color(102, 102, 102));
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/refresh-20.png"))); // NOI18N
        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        Add_Edit.setBackground(new java.awt.Color(0, 102, 0));
        Add_Edit.setMaximumSize(new java.awt.Dimension(32767, 160));

        jScrollPane6.setMaximumSize(new java.awt.Dimension(32767, 190));
        jScrollPane6.setMinimumSize(new java.awt.Dimension(456, 106));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(456, 106));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "User", "Company", "Status", "Priority", "Types", "#", "Proj #", "Project Description", "Agency", "Location", "Remarks", "Due Date", "Save"
            }
        ));
        jTable2.setGridColor(new java.awt.Color(153, 153, 153));
        jTable2.setShowGrid(true);
        jScrollPane6.setViewportView(jTable2);

        type_level.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        type_level.setForeground(new java.awt.Color(255, 255, 255));
        type_level.setText("Add new project list");

        addrow.setBackground(new java.awt.Color(51, 255, 0));
        addrow.setForeground(new java.awt.Color(255, 255, 255));
        addrow.setText("Add row");
        addrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addrowActionPerformed(evt);
            }
        });

        removerow.setBackground(new java.awt.Color(255, 51, 51));
        removerow.setForeground(new java.awt.Color(255, 255, 255));
        removerow.setText("Remove");
        removerow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerowActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 51, 51));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("X");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        show_n.setText("M");

        show_prj.setText("P");

        show_name.setText("NAME");

        javax.swing.GroupLayout Add_EditLayout = new javax.swing.GroupLayout(Add_Edit);
        Add_Edit.setLayout(Add_EditLayout);
        Add_EditLayout.setHorizontalGroup(
            Add_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Add_EditLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(Add_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Add_EditLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(type_level)
                        .addGap(18, 18, 18)
                        .addComponent(show_n)
                        .addGap(34, 34, 34)
                        .addComponent(show_prj)
                        .addGap(27, 27, 27)
                        .addComponent(show_name)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Add_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Add_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Add_EditLayout.createSequentialGroup()
                            .addComponent(removerow, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7))
                        .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(addrow)))
        );
        Add_EditLayout.setVerticalGroup(
            Add_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_EditLayout.createSequentialGroup()
                .addGroup(Add_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Add_EditLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(Add_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(type_level)
                            .addComponent(show_n)
                            .addComponent(show_prj)
                            .addComponent(show_name)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Add_EditLayout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(8, 8, 8)
                .addGroup(Add_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Add_EditLayout.createSequentialGroup()
                        .addComponent(addrow)
                        .addGap(5, 5, 5)
                        .addComponent(removerow))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        show_n.setVisible(false);
        show_prj.setVisible(false);
        show_name.setVisible(false);

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 204, 51));
        jButton11.setText("+ Create new project");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        newPanel1.setBackground(new java.awt.Color(255, 255, 255));

        search_text.setBorder(null);
        search_text.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                search_textAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        search_text.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                search_textFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                search_textFocusLost(evt);
            }
        });
        search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_textActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search-19.png"))); // NOI18N

        javax.swing.GroupLayout newPanel1Layout = new javax.swing.GroupLayout(newPanel1);
        newPanel1.setLayout(newPanel1Layout);
        newPanel1Layout.setHorizontalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_text, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );
        newPanel1Layout.setVerticalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(search_text, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("[REFRESH NEEDED]");

        filtertext.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filtertext.setForeground(new java.awt.Color(0, 102, 204));

        jMenu1.setText("Export");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/excel-22.png"))); // NOI18N
        jMenuItem1.setText("Export");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Import");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/excel-22.png"))); // NOI18N
        jMenuItem2.setText("Import from Excel");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Add_Edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(newPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filtertext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refresh)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(newPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filtertext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(Add_Edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Add_Edit.setVisible(false);
        jLabel2.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents
int cc = 0;
    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        open_ip();
        Template();
        clearFilter();
        cc++;
        jLabel2.setVisible(false);
        filtertext.setText("");
        filteredValues.clear();

    }//GEN-LAST:event_refreshActionPerformed

    private void addrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addrowActionPerformed
        int rows = jTable2.getRowCount();
        // Table model alignment
//        jTable2.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel(); 
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(10);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable2.setDefaultRenderer(String.class, centerRenderer);
        jTable2.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable2.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        String name = "";
        for(int i = 0; i < rowsuser.size(); i++){
            String[] rowa = rowsuser.get(i);
            String value1 = rowa[0];
            String value2 = rowa[1];
            if(users.getText().equals(value1)){name = value2;}
        }
        model.addRow(new Object[]{rows + 1, name, "" ,"",""," Choose type "," AUTO GENERATED "," AUTO GENERATED","","","","" ," DATE "," SAVE "});
        
        List<String> secondColumnValues = extractSecondColumn(rowscompany);
        jTable2.getColumnModel().getColumn(2).setCellEditor(new FilteredInputCellEditor(secondColumnValues));
        
        List<String> secondColumnValues2 = extractSecondColumn(rows1);
        jTable2.getColumnModel().getColumn(4).setCellEditor(new FilteredInputCellEditor(secondColumnValues2));
        
        List<String> secondColumnValues3 = extractSecondColumn(rows2);
        jTable2.getColumnModel().getColumn(10).setCellEditor(new FilteredInputCellEditor(secondColumnValues3));
        
        List<String> secondColumnValues4 = extractSecondColumn(rows3);
        jTable2.getColumnModel().getColumn(9).setCellEditor(new FilteredInputCellEditor(secondColumnValues4));
        
        List<String> secondColumnValues5 = extractSecondColumn(rows4);
        jTable2.getColumnModel().getColumn(3).setCellEditor(new FilteredInputCellEditor(secondColumnValues5));
        
        jTable2.getColumnModel().getColumn(0).setCellEditor(nonEditableEditor); 
        jTable2.getColumnModel().getColumn(1).setCellEditor(nonEditableEditor); 
        jTable2.getColumnModel().getColumn(6).setCellEditor(nonEditableEditor); 
        jTable2.getColumnModel().getColumn(7).setCellEditor(nonEditableEditor); 
        jTable2.getColumnModel().getColumn(8).setCellEditor(nonEditableEditor); 
        jTable2.getColumnModel().getColumn(11).setCellEditor(nonEditableEditor); 
        jTable2.getColumnModel().getColumn(12).setCellEditor(nonEditableEditor); 
        jTable2.getColumnModel().getColumn(13).setCellEditor(nonEditableEditor); 

        
        TableColumn typColumn = jTable2.getColumnModel().getColumn(5);

        JComboBox comboBoxTyp = new JComboBox();
        for(int i = 0; i < rows5.size(); i++){
            String[] rowa = rows5.get(i);
            String value1 = rowa[0];
            String value2 = rowa[1];
            comboBoxTyp.addItem(value2);
        }
        
        typColumn.setCellEditor(new DefaultCellEditor(comboBoxTyp));
        
        int columnToColor = 4; // prio
        int columnToColor2 = 10; // location
        int columnToColor3 = 9; // agency
        int columnToColor4 = 3; // status
//        int columnToColor5 = 2; // company
        int columnToColor6 = 13; // save
        TableCellRenderer cellRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value != null ? value.toString() : "");
                        // Set the background color for the specified cell
                        if (column == columnToColor) {
                            cell.setOpaque(true);
                            
                            for(int i = 0; i < rows1.size(); i++){
                                String[] rowa = rows1.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(153,153,153));
                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
//                                System.out.println("Values: " + value);
                            }
                           
                        }
                        else if (column == columnToColor2) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            for(int i = 0; i < rows2.size(); i++){
                                String[] rowa = rows2.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(153,153,153));
                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
//                                System.out.println("Values: " + value);
                            }
                        }
                        else if (column == columnToColor3) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            for(int i = 0; i < rows3.size(); i++){
                                String[] rowa = rows3.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(153,153,153));
                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
//                                System.out.println("Values: " + value);
                            }
                        }
                        else if (column == columnToColor4) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            for(int i = 0; i < rows4.size(); i++){
                                String[] rowa = rows4.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(153,153,153));
                                if(cell.getText().equals(value2)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);}
//                                System.out.println("Values: " + value);
                            }
                        }
//                        else if (column == columnToColor5) {
////                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
//                            
//                            cell.setOpaque(true);
//                            for(int i = 0; i < rowscompany.size(); i++){
//                                String[] rowa = rowscompany.get(i);
//                                String value1 = rowa[0];
//                                String value2 = rowa[1];
//                                
//                                Border blackline = BorderFactory.createLineBorder(new Color(204,204,204));
//                                if(cell.getText().equals(value2)){cell.setText(value2); cell.setBorder(blackline); cell.setHorizontalAlignment(SwingConstants.CENTER);}
////                                System.out.println("Values: " + value);
//                            }
//                        }
                        else if (column == columnToColor6) {
                            Border blackline = BorderFactory.createLineBorder(new Color(255,255,255));
                            cell.setBorder(blackline);
                            cell.setOpaque(true);
                            cell.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.setText("SAVE"); 
                            cell.setBackground(new Color(0, 204, 0));
                            cell.setForeground(Color.WHITE);
                        }
                    
                return cell;
            }
        };
        jTable2.getColumnModel().getColumn(columnToColor).setCellRenderer(cellRenderer);
        jTable2.getColumnModel().getColumn(columnToColor2).setCellRenderer(cellRenderer);
        jTable2.getColumnModel().getColumn(columnToColor3).setCellRenderer(cellRenderer);
        jTable2.getColumnModel().getColumn(columnToColor4).setCellRenderer(cellRenderer);
        jTable2.getColumnModel().getColumn(columnToColor6).setCellRenderer(cellRenderer);
//        jTable1.getColumnModel().getColumn(columnToColor5).setCellRenderer(cellRenderer);
    }//GEN-LAST:event_addrowActionPerformed

    private void removerowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerowActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int selectedRow = jTable2.getSelectedRow();

        if (selectedRow != -1) { // Check if any row is selected
            model.removeRow(selectedRow); // Remove the selected row
        } else {
            // Handle case when no row is selected
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_removerowActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        addrow.setVisible(false);
        removerow.setVisible(false);
        Add_Edit.setVisible(true);
//        Add_Edit.setPreferredSize(new Dimension(446,106)); // 456, 406
        Add_Edit.setBackground(new Color(153,255,153));
        type_level.setText("Add new project list");
        type_level.setForeground(new Color(0,102,0));
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel(); {
            model.setRowCount(0);
        }
        addrow.doClick();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Add_Edit.setVisible(false);
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel(); {
            model.setRowCount(0);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void search_textFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_textFocusGained
        if(search_text.getText().equals("Search"))
        {
            search_text.setText("");
            search_text.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_search_textFocusGained

    private void search_textFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_textFocusLost
                                 
        if(search_text.getText().equals(""))
        {
            search_text.setText("Search");
            search_text.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_search_textFocusLost

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        if ("User".equals(utype.getText())) {
            jTable1.getTableHeader().setReorderingAllowed(false);
        } else if ("Admin".equals(utype.getText())) {
            jTable1.getTableHeader().setReorderingAllowed(true);
        } else {
            // Handle other user types or unexpected input
            // For example, set a default behavior
            jTable1.getTableHeader().setReorderingAllowed(false);
        }
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            int col = jTable1.columnAtPoint(evt.getPoint());
            if (col != -1) {
                clickedColumn = col;
                showDropDownFilterMenu(evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        readExcelData();
        Template();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_textActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //        exportTableToExcel();
        Excel();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void search_textAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_search_textAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_search_textAncestorAdded

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if ("Admin".equals(utype.getText())) {
            readExcelData();
        } 
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
                new PROJECT_LIST().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private v1_tm_projectmanagement.NewPanel Add_Edit;
    private javax.swing.JButton addrow;
    private javax.swing.JLabel filtertext;
    private javax.swing.JFrame frame;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private v1_tm_projectmanagement.NewPanel newPanel1;
    private javax.swing.JButton refresh;
    private javax.swing.JButton removerow;
    private javax.swing.JTextField search_text;
    private javax.swing.JLabel show_n;
    private javax.swing.JLabel show_name;
    private javax.swing.JLabel show_prj;
    public static final javax.swing.JLabel supername = new javax.swing.JLabel();
    private javax.swing.JLabel type_level;
    public static final javax.swing.JLabel users = new javax.swing.JLabel();
    public static final javax.swing.JLabel utype = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
