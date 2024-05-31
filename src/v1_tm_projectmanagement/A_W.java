/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package v1_tm_projectmanagement;

// Testing 1
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import org.apache.derby.drda.NetworkServerControl;


public class A_W extends Connect_ip {
    
    private static int getColumnIndexByName(JTable table, String columnName) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(columnName)) {
                return i; // Return the index if column name matches
            }
        }
        return -1; // Return -1 if column name is not found
    }
    
    List<String[]> rows = new ArrayList<>();
    
    List<String[]> rowprio = new ArrayList<>();
    List<String[]> rowloca = new ArrayList<>();
    List<String[]> rowagen = new ArrayList<>();
    List<String[]> rowstat = new ArrayList<>();
    List<String[]> rowstype = new ArrayList<>();
    List<String[]> rowscompany = new ArrayList<>();
    List<String[]> rowsuser = new ArrayList<>();
    List<String[]> rowsactivities = new ArrayList<>();
    
    List<String[]> rows_user = new ArrayList<>();
    List<String[]> rows_company = new ArrayList<>();
    List<String[]> rows_table = new ArrayList<>();
        
   ButtonGroup group1 = new ButtonGroup();
   ButtonGroup group2 = new ButtonGroup();
   String O_time = "";
   
   Date petsa = new Date();
   SimpleDateFormat sdfa = new SimpleDateFormat("MMM dd.yyyy");
   String date = sdfa.format(petsa);
   SimpleDateFormat sdfa2 = new SimpleDateFormat("MM/dd/yyyy");
   String dateupdated = sdfa2.format(petsa);
   
   String status_check = "";
   int activities_length = 100;
   String name_btn_click = "";
   private JPopupMenu popupMenu;
   
   String one_btn = "";
   String two_btn = "";
   String three_btn = "";
   boolean ready_action = false;
   // IP ADDRESS
//   private final String url = "jdbc:derby://localhost:1527/TM_DB";
//    String url = "jdbc:derby://192.168.1.6:1527/\\\\DESKTOP-7CU9UG7\\Task_DB\\TM_DB";
    String url = "";
    private final String user = "josh";
    private final String password = "1234";
    
   Login rl = new Login();
   
   String name2 = rl.location.getText();
   String ip = rl.ip_add.getText();
//    String name = "\\\\DESKTOP-7CU9UG7\\Task_DB2\\TM_DB";
//    String ip = "192.168.1.6";
   
   
    public A_W() {
        initComponents();
        
        setIconImage();
        open_ip();
        add_db_template();
        add_db();
        times();
//        show_one();
        listnercolumn();
        maptext();
        Ref();
        renderTable1();
        int delay = 100; // delay in milliseconds
        Timer timer = new Timer(delay, e -> {
            // update the label text
                    show_table();
                    this.setTitle(nameer.getText() + " - " + utype.getText());
            ((Timer) e.getSource()).stop();
        });
        timer.start();
        jTable1.setRowHeight(42);
        jTable1.setRowSelectionAllowed(true);
        jTable1.setColumnSelectionAllowed(false);
        jTable1.setDefaultEditor(Object.class, null);
        jScrollPane4.getVerticalScrollBar().setUnitIncrement(13);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(13);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(13);
//        jTable1.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());
        
    }
   
  
    private void setIconImage() {
       setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files (x86)\\Pms_f\\Documentation\\PMS.png"));
    }
    
    public void renderTable1(){
        int id = getColumnIndexByName(jTable1, "ID");
        int priority = getColumnIndexByName(jTable1, "Priority");
        int status = getColumnIndexByName(jTable1, "Status");
        int agency = getColumnIndexByName(jTable1, "Agency");
        int location = getColumnIndexByName(jTable1, "Location");
        int type = getColumnIndexByName(jTable1, "Type");
        int no = getColumnIndexByName(jTable1, "#");
        int prjno = getColumnIndexByName(jTable1, "Proj #");
        jTable1.getColumnModel().getColumn(id).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(status).setPreferredWidth(145);
        jTable1.getColumnModel().getColumn(priority).setPreferredWidth(55);
        jTable1.getColumnModel().getColumn(agency).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(location).setPreferredWidth(65);
        jTable1.getColumnModel().getColumn(type).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(no).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(prjno).setPreferredWidth(125);
        
        // Hide column (Istorbo)
        TableColumnModel columnModel = jTable1.getColumnModel();
        TableColumn column = columnModel.getColumn(id);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);
        
        customizeTableHeader(jTable1);
    }
    
    public void open_ip(){
        name2 = rl.location.getText();
        ip = rl.ip_add.getText();
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
    
    // KEYWORD @
    public void maptext(){
        Comment.setEditorKit(new StyledEditorKit());
        Comment.addKeyListener(new MentionKeyListener());
        popupMenu = new JPopupMenu();
        // Add your names to the popup menu
        for(int b = 0; b < rowsuser.size(); b++){
            String[] rowa3 = rowsuser.get(b);
            String v1 = rowa3[0];
            String v2 = rowa3[1];
            popupMenu.add(createMenuItem(v2));
        }
        
//        popupMenu.add(createMenuItem("Alice"));
//        popupMenu.add(createMenuItem("Bob"));
    }
    
    private JMenuItem createMenuItem(String name) {
        JMenuItem menuItem = new JMenuItem("@" + name);
        menuItem.addActionListener(e -> insertMention(name));
        return menuItem;
    }

    private void insertMention(String name) {
        try {
            StyledDocument doc = (StyledDocument) Comment.getDocument();
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setForeground(attributes, Color.BLUE);
            
            SimpleAttributeSet blackAttributes = new SimpleAttributeSet();
        StyleConstants.setForeground(blackAttributes, Color.BLACK);

            int caretPosition = Comment.getCaretPosition();

            // Remove the "@" character that was typed
            doc.remove(caretPosition - 1, 1);

            // Insert the mention with blue color
            doc.insertString(caretPosition - 1,"@" + name , attributes);
            // Insert a space with black color after the mention
        doc.insertString(doc.getLength(), " ", blackAttributes);

        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private class MentionKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == '@') {
                // Show popup menu
                popupMenu.show(Comment, Comment.getCaret().getMagicCaretPosition().x,
                        Comment.getCaret().getMagicCaretPosition().y + Comment.getFontMetrics(Comment.getFont()).getHeight());
            } else if (e.getKeyChar() == ' ') {
                // Hide popup menu if space is pressed
                popupMenu.setVisible(false);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                handleBackspace();
            }
        }

        private void handleBackspace() {
    try {
        StyledDocument doc = (StyledDocument) Comment.getDocument();
        int caretPosition = Comment.getCaretPosition();

        // Check if the character before the caret is "@"
        if (caretPosition > 0 && doc.getText(caretPosition - 1, 1).equals("@")) {
            // Find the start position of the mention (including "@")
            int mentionStart = caretPosition - 1;
            while (mentionStart > 0 && !Character.isWhitespace(doc.getText(mentionStart - 1, 1).charAt(0))) {
                mentionStart--;
            }

            // Ensure that mentionStart is less than or equal to caretPosition
            if (mentionStart <= caretPosition) {
                // Remove the entire mention (including "@")
                doc.remove(mentionStart, caretPosition - mentionStart + 1);
            }
        }
    } catch (BadLocationException ex) {
        ex.printStackTrace();
    }
}



    }
    // END KEYWORD @
    
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
    
    public void listnercolumn(){

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                activities_length = 100;
                activity_panel.removeAll();
                activity_panel.repaint();
                activity_panel.revalidate();
                int pro = getColumnIndexByName(jTable1, "Proj #");
                int type = getColumnIndexByName(jTable1, "Type");
                int hash = getColumnIndexByName(jTable1, "#");
                int sta = getColumnIndexByName(jTable1, "Status");
                int selectedRow = jTable1.getSelectedRow();
                
                Object rowData = jTable1.getValueAt(selectedRow, 0);
                Object rowDataPro = jTable1.getValueAt(selectedRow, pro);
                Object rowDataSta = jTable1.getValueAt(selectedRow, sta);
                Object rowDataType = jTable1.getValueAt(selectedRow, type);
                Object rowDataHash = jTable1.getValueAt(selectedRow, hash);
                three_btn = String.valueOf(rowData);
                proj.setText((String)rowDataType+" - "+(String)rowDataPro+"."+(String)rowDataHash);
                status_check = (String)rowDataSta;
                for(int b = 0; b < rowsactivities.size(); b++){
                    String[] rowa3 = rowsactivities.get(b);
                    String v1 = rowa3[0];  //A_NO
                    String v2 = rowa3[1];  //A_ID
                    String v3 = rowa3[2];  //T_ID
                    String v4 = rowa3[3];  //NAME
                    String v5 = rowa3[4];  //DATE_TIME
                    String v6 = rowa3[5];  //COMMENT
                    String v7 = rowa3[6];  //COMMENT 2
                    
                    if(rowData.equals(v3)){
                        if(ready_action == true){del_userhist((String)rowData);}
                        
                        if(v7.equals("asd123")){
                            
                            NewPanel A_display = new NewPanel(); A_display.setBackground(Color.WHITE);
                            JLabel name = new JLabel(v4); JLabel date_time = new JLabel(v5); date_time.setFont(new Font("Nirmala UI", Font.PLAIN, 10)); name.setFont(new Font("segoe UI", Font.BOLD, 12));
                            JEditorPane left_txt = new JEditorPane(); left_txt.setText(v6); left_txt.setEditable(false); left_txt.setBackground(new Color(153, 204, 255)); left_txt.setForeground(Color.BLACK); //left_txt.setBounds(5, 2, 200, left_txt.getPreferredSize().height + 50);
                            NewPanel cover_t = new NewPanel(); cover_t.add(left_txt); cover_t.setBackground(new Color(153, 204, 255));cover_t.setLayout(null);
                            
                            GroupLayout TEXT_gl = new GroupLayout(cover_t);cover_t.setLayout(TEXT_gl);

                            TEXT_gl.setHorizontalGroup(TEXT_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                      .addGroup(GroupLayout.Alignment.TRAILING, TEXT_gl.createSequentialGroup()
                                          .addGap(5)
                                          .addComponent(left_txt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                              .addGap(0,0,0))
                                      );

                              TEXT_gl.setVerticalGroup(TEXT_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                  .addGroup(TEXT_gl.createSequentialGroup() 
                                          .addGap(0)
                                          .addGroup(TEXT_gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                              .addComponent(left_txt, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE)
                                          .addGap(3)
                                          )
                                  )
                              );
                            
                            GroupLayout A_gl = new GroupLayout(A_display);A_display.setLayout(A_gl);

                            A_gl.setHorizontalGroup(A_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                      .addGroup(GroupLayout.Alignment.TRAILING, A_gl.createSequentialGroup()
                                          .addGap(15)
                                          .addComponent(name, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                              .addGap(20,20,20)
                                              .addComponent(date_time, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                          .addGap(30,30,30))
                                    .addGroup(GroupLayout.Alignment.LEADING, A_gl.createSequentialGroup()
                                          .addGap(15)
                                          .addComponent(cover_t, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
                                      );

                              A_gl.setVerticalGroup(A_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                  .addGroup(A_gl.createSequentialGroup() 
                                          .addGap(0)
                                          .addGroup(A_gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                              .addComponent(name, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE)
                                          .addComponent(date_time, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE))
                                          .addContainerGap(0, Short.MAX_VALUE)
                                          .addComponent(cover_t, GroupLayout.DEFAULT_SIZE, left_txt.getPreferredSize().height + 10, GroupLayout.DEFAULT_SIZE)
                                  .addContainerGap(0, Short.MAX_VALUE))
                              );

                            activities_length += A_display.getPreferredSize().height + 10;
                            
                            activity_panel.add(A_display);
                            activity_panel.revalidate();
                            activity_panel.repaint();
                            activity_panel.setPreferredSize(new Dimension(0, activities_length));
                        }
                        else{
                            int red = 0;
                            int green = 0;
                            int blue = 0;
                            int red1 = 0;
                            int green1 = 0;
                            int blue1 = 0;
                            for(int i = 0; i < rowstat.size(); i++){
                                String[] rowa = rowstat.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int aa = Integer.parseInt(parts[0]);
                                int bb = Integer.parseInt(parts[1]);
                                int cc = Integer.parseInt(parts[2]);
                                if(v6.equals(value2)){if(!value3.equals("255,255,255")){red = aa; green = bb; blue = cc;}}
                                else if(v7.equals(value2)){if(!value3.equals("255,255,255")){red1 = aa; green1 = bb; blue1 = cc;}}
                            }
                            
                            NewPanel A_display = new NewPanel(); A_display.setBackground(new Color(244,207,95));
                            JLabel name = new JLabel(v4); JLabel date_time = new JLabel(v5); date_time.setFont(new Font("Nirmala UI", Font.PLAIN, 10)); name.setFont(new Font("segoe UI", Font.BOLD, 12));
                            JEditorPane left_txt = new JEditorPane();  left_txt.setEditable(false); left_txt.setBackground(new Color(255, 255, 255)); left_txt.setForeground(Color.BLACK);left_txt.setFont(new Font("segoe UI", Font.BOLD, 11));
                            String htmlText = "<html><font color='rgb(" + red + "," + green + "," + blue + ")'>" +
                            v6.toUpperCase() + "</font> ➡ <br><font color='rgb(" + red1 + "," + green1 + "," + blue1 + ")'>" + v7.toUpperCase() + "</font></html>";
        
                            left_txt.setContentType("text/html");
                            left_txt.setText(htmlText);
                            NewPanel cover_t = new NewPanel(); cover_t.add(left_txt); cover_t.setBackground(new Color(255, 255, 255));cover_t.setLayout(null);

                            GroupLayout TEXT_gl = new GroupLayout(cover_t);cover_t.setLayout(TEXT_gl);

                            TEXT_gl.setHorizontalGroup(TEXT_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                      .addGroup(GroupLayout.Alignment.TRAILING, TEXT_gl.createSequentialGroup()
                                          .addGap(5)
                                          .addComponent(left_txt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                              .addGap(0,0,0))
                                      );

                              TEXT_gl.setVerticalGroup(TEXT_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                  .addGroup(TEXT_gl.createSequentialGroup() 
                                          .addGap(0)
                                          .addGroup(TEXT_gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                              .addComponent(left_txt, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE)
                                          .addGap(3)
                                          )
                                  )
                              );
                            
                            
                            GroupLayout A_gl = new GroupLayout(A_display);A_display.setLayout(A_gl);

                            A_gl.setHorizontalGroup(A_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                      .addGroup(GroupLayout.Alignment.TRAILING, A_gl.createSequentialGroup()
                                          .addGap(15)
                                          .addComponent(name, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                              .addGap(20,20,20)
                                              .addComponent(date_time, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                          .addGap(30,30,30))
                                    .addGroup(GroupLayout.Alignment.LEADING, A_gl.createSequentialGroup()
                                          .addGap(15)
                                          .addComponent(cover_t, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
                                      );

                              A_gl.setVerticalGroup(A_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                  .addGroup(A_gl.createSequentialGroup() 
                                          .addGap(0)
                                          .addGroup(A_gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                              .addComponent(name, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE)
                                          .addComponent(date_time, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE))
                                          .addContainerGap(0, Short.MAX_VALUE)
                                          .addComponent(cover_t, GroupLayout.DEFAULT_SIZE, left_txt.getPreferredSize().height + 10, GroupLayout.DEFAULT_SIZE)
                                  .addContainerGap(0, Short.MAX_VALUE))
                              );
                            activities_length += A_display.getPreferredSize().height + 10;
                            
                            activity_panel.add(A_display);
                            activity_panel.revalidate();
                            activity_panel.repaint();
                            activity_panel.setPreferredSize(new Dimension(0, activities_length));
                        }
                    }
                }
                
//        
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int column = jTable1.columnAtPoint(e.getPoint());
                    int row = jTable1.rowAtPoint(e.getPoint());
                    int selectedRows = jTable1.getSelectedRow();

                    int PD = getColumnIndexByName(jTable1, "Project Description");
                    
                    if(column == PD && row != -1){
                        Object rowDatas = jTable1.getValueAt(row, column);
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
                int prj = getColumnIndexByName(jTable1, "Project Description");
                
                if (column == prj) { // Change cursor if mouse enters column 13
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
    }
    
    public void add_db_template(){
         rows.clear();
         rows_user.clear();
         rows_company.clear();
         rows_table.clear();
        try {
            //Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM JOSH.TEMPLATE";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String V = resultSet.getString("T_NO");//0
                String V2 = resultSet.getString("T_ID");//1
                String V3 = resultSet.getString("U_ID");//2
                String V4 = resultSet.getString("C_ID");//3
                String V5 = resultSet.getString("PD");//4
                String V6 = resultSet.getString("DATE");//5
                String V7 = resultSet.getString("PRJNO");//6
                String V8 = resultSet.getString("D_1_ID");//7
                String V9 = resultSet.getString("D_2_ID");//8
                String V10 = resultSet.getString("D_3_ID");//9
                String V11 = resultSet.getString("DESCRIPTION");//10
                String V12 = resultSet.getString("DCREATED");//11
                String V13 = resultSet.getString("DD");//12
                String V14 = resultSet.getString("DU");//13
                String V15 = resultSet.getString("D_4_ID");//14
                String V16 = resultSet.getString("APPROVAL");//15
                String V17 = resultSet.getString("D_5_ID");//15
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {V, V2, V3, V4, V5, V6, V7, V8, V9, V10, V11, V12, V13, V14, V15, V16, V17};
                rows.add(newRow);
                
                if(V16.equals("true")){
                    String[] newRow1 = {V2, V3};
                    rows_user.add(newRow1);
                    
                    String[] newRow2 = {V2, V3, V4};
                    rows_company.add(newRow2);
                    
//                    String[] newRow3 = {V2, V15, V8, V6+V7, V5, V10, V14, V3, V4};
                    String[] newRow3 = {V2, V15, V8, V7, V17, V6, V5, V10, V14, V3, V4, V9};
                    rows_table.add(newRow3);
                }
            }
            // Close resources
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void add_db(){
         rowprio.clear();
         rowloca.clear();
         rowagen.clear();
         rowstat.clear();
         rowscompany.clear();
         rowsuser.clear();
         rowsactivities.clear();
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
            String sqlact = "SELECT * FROM JOSH.ACTIVITIES";
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            PreparedStatement stmt3 = conn.prepareStatement(sql3);
            PreparedStatement stmt4 = conn.prepareStatement(sql4);
            PreparedStatement stmt7 = conn.prepareStatement(sql5);
            PreparedStatement stmtCom = conn.prepareStatement(sqlcom);
            PreparedStatement stmt5 = conn.prepareStatement(sqluser);
            PreparedStatement stmt6 = conn.prepareStatement(sqlact);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String values = resultSet.getString("D_1_ID");
                String values2 = resultSet.getString("PRIORITY");
                String values3 = resultSet.getString("P_COLOR");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3};
                rowprio.add(newRow);
            }
            ResultSet resultSet2 = stmt2.executeQuery();
            while (resultSet2.next()) {
                String values = resultSet2.getString("D_2_ID");
                String values2 = resultSet2.getString("LOCATION");
                String values3 = resultSet2.getString("L_COLOR");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3};
                rowloca.add(newRow);
            }
            ResultSet resultSet3 = stmt3.executeQuery();
            while (resultSet3.next()) {
                String values = resultSet3.getString("D_3_ID");
                String values2 = resultSet3.getString("AGENCY");
                String values3 = resultSet3.getString("A_COLOR");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3};
                rowagen.add(newRow);
            }
            ResultSet resultSet4 = stmt4.executeQuery();
            while (resultSet4.next()) {
                String values = resultSet4.getString("D_4_ID");
                String values2 = resultSet4.getString("STATUS");
                String values3 = resultSet4.getString("S_COLOR");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3};
                rowstat.add(newRow);
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
                String values = resultSet6.getString("A_NO");
                String values2 = resultSet6.getString("A_ID");
                String values3 = resultSet6.getString("T_ID");
                String values4 = resultSet6.getString("NAME");
                String values5 = resultSet6.getString("DATE_TIME");
                String values6 = resultSet6.getString("COMMENT");
                String values7 = resultSet6.getString("COMMENT2");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2, values3, values4, values5, values6, values7};
                rowsactivities.add(newRow);
            }
            ResultSet resultSet7 = stmt7.executeQuery();
            while (resultSet7.next()) {
                String values = resultSet7.getString("D_5_ID");
                String values2 = resultSet7.getString("LIST");
                // Add retrieved values to the ArrayList as String array
                String[] newRow = {values, values2};
                rowstype.add(newRow);
            }
            // Close resources
            resultSet.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            resultSet5.close();
            resultSet7.close();
            stmt.close();
            stmt2.close();
            stmt3.close();
            stmt4.close();
            stmt5.close();
            stmt7.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void show_one(){
        for(int i = 0; i < rows.size(); i++){
            String[] rowa = rows.get(i);
            String value1 = rowa[0];
            String value3 = rowa[2];
            String value16 = rowa[15];
            if(value16.equals("true")){
                for(int a = 0; a < rowsuser.size(); a++){
                    String[] rowa2 = rowsuser.get(a);
                    String v1 = rowa2[0];
                    String v2 = rowa2[1];
                    if(value3.equals(v1)){
                        String[] newRow = {value3};
                        rows_user.add(newRow);
                    }
                }
            }
        }
    }
    
    public void show_table(){
        String[] parts = nameer.getText().split("\\s+");
        String firstName = parts[0];
        
        int sbar = 0;
        adduser.removeAll();
        Set<String> uniqueValues = new LinkedHashSet<>();
        List<String[]> updatedList = new ArrayList<>();
        for (String[] array : rows_user) {
            String valueAtIndex1 = array[1];
            if (!uniqueValues.contains(valueAtIndex1)) {
                uniqueValues.add(valueAtIndex1);
                updatedList.add(array);
            }
        }
        
        for (String [] value : updatedList) {
            String id = value[0];
            String userid = value[1];
            for(int b = 0; b < rowsuser.size(); b++){
                    String[] rowa3 = rowsuser.get(b);
                    String v1 = rowa3[0];
                    String v2 = rowa3[1];
                    if(v1.equals(userid)){
                        sbar += 42;
                        JToggleButton parent = new JToggleButton(); parent.setPreferredSize(new Dimension(70,35)); parent.setBackground(new Color(17,77,112)); parent.setForeground(Color.WHITE); 
                        parent.setFont(new Font("Leelawadee UI", Font.BOLD, 11)); group1.add(parent);
                        Insets margins = new Insets(2, 2, 0, 13);
                        parent.setMargin(margins);
                        parent.setHorizontalAlignment(SwingConstants.LEFT);
//                        String[] parts = v2.split("\\s+");
//                        String firstName = parts[0];
//                        System.out.println(id);
                        
                            
                        parent.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent t) {
                                int tbar = 0;
                                one_btn = v2;
                                name_btn_click = v2;
                                DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); 
                                model.setRowCount(0);
                                activity_panel.removeAll();
                                activity_panel.repaint();
                                activity_panel.revalidate();
                                addcom.removeAll();
                                Set<String> uniquePairs = new LinkedHashSet<>();
                                List<String[]> updatedList = new ArrayList<>();

                                 for (String[] triplet : rows_company) {
                                    String pairString = triplet[1] + " , " + triplet[2];
                                    if (uniquePairs.add(pairString)) {
                                        updatedList.add(triplet);
                                    }
                                }
                                for(String [] array : updatedList){
                                    String idd = array[0];
                                    String idd2 = array[1];
                                    String name = array[2];
                                    
                                    for(int b = 0; b < rowscompany.size(); b++){
                                        String[] rowa3 = rowscompany.get(b);
                                        String vv1 = rowa3[0];
                                        String vv2 = rowa3[1];
                                        if(vv1.equals(name) && idd2.equals(userid)){
                                            tbar += 42;
                                            JToggleButton sub_parent = new JToggleButton(); sub_parent.setPreferredSize(new Dimension(200,35)); sub_parent.setBackground(new Color(255,255,255)); sub_parent.setForeground(new Color(17,77,112)); 
                                            sub_parent.setFont(new Font("Leelawadee UI", Font.BOLD, 11)); group2.add(sub_parent);
                                            Insets margins = new Insets(2, 2, 0, 13);
                                            sub_parent.setMargin(margins);
                                            sub_parent.setHorizontalAlignment(SwingConstants.LEFT);
                                            
                                            sub_parent.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent t) {
                                                    activity_panel.removeAll();
                                                    activity_panel.repaint();
                                                    activity_panel.revalidate();
                                                    two_btn = vv2;
                                                    show_jtable(idd, userid, name, sub_parent);
 
                                                }
                                            });
                                            int countrow = 0;
                                            for(int count = 0; count < rows_table.size(); count++){
                                                String[] rowacount = rows_table.get(count);
                                                String v10 = rowacount[9];
                                                String v11 = rowacount[10];
                                                if(userid.equals(v10) && name.equals(v11)){
                                                    countrow++;
                                                }
                                            }
                                            sub_parent.setText("  "+vv2);
                                            JLabel smallFontLabel = new JLabel(String.valueOf(countrow)); smallFontLabel.setForeground(Color.RED);
                                            smallFontLabel.setFont(smallFontLabel.getFont().deriveFont(Font.BOLD, 9)); // Set the smaller font size

                                            int spacing = -21; // Adjust the spacing as needed
                                            smallFontLabel.setBorder(new EmptyBorder(spacing, 179, 0, 0));

                                            // Set BorderLayout for the button
                                            sub_parent.setLayout(new BorderLayout());

                                            // Add the smaller font label to the top-left corner (WEST) of the button
                                            sub_parent.add(smallFontLabel, BorderLayout.WEST);
                                            // Check chat
                                            String filePath = name2+"\\log\\logfolder\\Ccall.txt";
                                            StringBuilder parentPathBuilder = new StringBuilder();
                                            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                                                String line;
                                                while ((line = br.readLine()) != null) {
                                                    String data = extractValue(line, "\\[(.*?)\\]");
                                                    String[] parts1 = line.split("\\s*\\]\\s*\\[\\s*");
                                                    if (parts1.length >= 4) {
                                                        String one = parts1[1];
                                                        String two = parts1[2];
                                                        String three = parts1[3].replaceAll("\\]", ""); // Remove trailing brackets from ID
                                                        if(v2.equals(one) && vv2.equals(two) && firstName.equals(data)){ sub_parent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/chat-20.png"))); ready_action = true; break;}
                                                        else{sub_parent.setIcon(null);}

                                                    }
                                                }
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            //
                                            addcom.add(sub_parent);
                                            addcom.revalidate();
                                            addcom.repaint();
                                        }
                                        addcom.setPreferredSize(new Dimension(60, tbar));
                                    }
                                }
                                        
                                    
                                
                            }
                        });
                        Set<String> uniquePairs = new LinkedHashSet<>();
                        List<String[]> updatedList12 = new ArrayList<>();

                         for (String[] triplet : rows_company) {
                            String pairString = triplet[1] + " , " + triplet[2];
                            if (uniquePairs.add(pairString)) {
                                updatedList12.add(triplet);
                            }
                        }
                        int countrow = 0;
                        for(String [] array : updatedList12){
                            String idd = array[0];
                            String idd2 = array[1];
                            String names = array[2];

                            for(int bcount = 0; bcount < rowscompany.size(); bcount++){
                                String[] rowabcount = rowscompany.get(bcount);
                                String vv1 = rowabcount[0];
                                String vv2 = rowabcount[1];
                                if(vv1.equals(names) && idd2.equals(userid)){
                                    countrow++;
                                }
                            }
                        }
//                        parent.setText("(" + String.valueOf(countrow) + ") " + v2);
                        parent.setText( "  "+v2);
                        JLabel smallFontLabel = new JLabel(String.valueOf(countrow)); smallFontLabel.setForeground(Color.WHITE);
                        smallFontLabel.setFont(smallFontLabel.getFont().deriveFont(Font.BOLD, 9)); // Set the smaller font size
//                        parent.add(Box.createRigidArea(new Dimension(-10, 5)));
//                        int spacing = -15; // Adjust the spacing as needed
//                        smallFontLabel.setBorder(new EmptyBorder(0, spacing, 4, 0));
//                        parent.add(smallFontLabel);
//                        parent.setText("<html><font size='6px'>("+countrow+")</font> "+v2+"</html>");
                        int spacing = -21; // Adjust the spacing as needed
                        smallFontLabel.setBorder(new EmptyBorder(spacing, 48, 0, 0));

                        // Set BorderLayout for the button
                        parent.setLayout(new BorderLayout());

                        // Add the smaller font label to the top-left corner (WEST) of the button
                        parent.add(smallFontLabel, BorderLayout.WEST);
                        
                        // Check chat
                        String filePath = name2+"\\log\\logfolder\\Ccall.txt";
                        StringBuilder parentPathBuilder = new StringBuilder();
                        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                String data = extractValue(line, "\\[(.*?)\\]");
                                String[] parts1 = line.split("\\s*\\]\\s*\\[\\s*");
                                if (parts1.length >= 4) {
                                    String one = parts1[1];
                                    String two = parts1[2];
                                    String three = parts1[3].replaceAll("\\]", ""); // Remove trailing brackets from ID
                                    if(v2.equals(one) && firstName.equals(data)){ parent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/chat-20.png")));break;}
                                    else{parent.setIcon(null);}
                                           
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //
                        adduser.add(parent);
                        adduser.revalidate();
                        adduser.repaint();
                    }
                    adduser.setPreferredSize(new Dimension(60, sbar));
                }
        }
    }
    
    public void show_jtable(String text, String text2, String text3, JToggleButton sub_parent){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); 
        model.setRowCount(0);
        for(int b = 0; b < rows_table.size(); b++){
            String[] rowa3 = rows_table.get(b);
            String v1 = rowa3[0];
            String v2 = rowa3[1];
            String v3 = rowa3[2];
            String v4 = rowa3[3]; 
            String v5 = rowa3[4];
            String v6 = rowa3[5];
            String v7 = rowa3[6];
            String v8 = rowa3[7];
            String v9 = rowa3[8];
            String v10 = rowa3[9];
            String v11 = rowa3[10];
            String v12 = rowa3[11];
            if(text2.equals(v10) && text3.equals(v11)){
                String name = "";
                String stats2 = "";
                for(int g = 0; g < rowstype.size(); g++){
                    String[] rowatype = rowstype.get(g);
                    String g1 = rowatype[0];
                    String g2 = rowatype[1];
                    if(v5.equals(g1)){name = g2;}
                }
                for(int bb = 0; bb < rowstat.size(); bb++){
                    String[] rows = rowstat.get(bb);
                    String value1 = rows[0];
                    String value2 = rows[1];
                    if(v2.equals(value1)){stats2 = value2;}
                }
                
                model.addRow(new Object[]{v1, v2, v3, name, v4, v6, v7, v8, v12, v9});
            }
        }
//        int rows = jTable1.getRowCount();
        
        
        int priority = getColumnIndexByName(jTable1, "Priority");
        int status = getColumnIndexByName(jTable1, "Status");
        int agency = getColumnIndexByName(jTable1, "Agency");
        int location = getColumnIndexByName(jTable1, "Location");
        
//        List<String> secondColumnValues5 = extractSecondColumn(rowstat);
//        jTable1.getColumnModel().getColumn(status).setCellEditor(new FilteredInputCellEditor(secondColumnValues5));
        
        int columnToColor = priority;
        int columnToColor2 = status;
        int columnToColor3 = agency;
        int columnToColor4 = location;
        TableCellRenderer cellRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value != null ? value.toString() : "");
                        // Set the background color for the specified cell
                        if (column == columnToColor) {
                            cell.setOpaque(true);
                            
                            for(int i = 0; i < rowprio.size(); i++){
                                String[] rowa = rowprio.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(230,232,230));
                                if(cell.getText().equals(value1)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER); cell.setFont(new Font("Leelawadee UI", Font.BOLD, 10));}
//                                System.out.println("Values: " + value);
                            }
                           
                        }
                        else if (column == columnToColor2) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            for(int i = 0; i < rowstat.size(); i++){
                                String[] rowa = rowstat.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(230,232,230));
                                if(cell.getText().equals(value1)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER); cell.setFont(new Font("Leelawadee UI", Font.BOLD, 10));}
//                                System.out.println("Values: " + value);
                            }
                        }
                        else if (column == columnToColor3) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            for(int i = 0; i < rowagen.size(); i++){
                                String[] rowa = rowagen.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(230,232,230));
                                if(cell.getText().equals(value1)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);cell.setFont(new Font("Leelawadee UI", Font.BOLD, 10));}
//                                System.out.println("Values: " + value);
                            }
                        }
                        else if (column == columnToColor4) {
//                            if(cell.getText().equals(Values)){cell.setText(Values2);cell.setBackground(new Color(a,b,c));}
                            
                            cell.setOpaque(true);
                            for(int i = 0; i < rowloca.size(); i++){
                                String[] rowa = rowloca.get(i);
                                String value1 = rowa[0];
                                String value2 = rowa[1];
                                String value3 = rowa[2];
                                
                                String[] parts = value3.toString().split(",");
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int c = Integer.parseInt(parts[2]);
                                Border blackline = BorderFactory.createLineBorder(new Color(230,232,230));
                                if(cell.getText().equals(value1)){cell.setText(value2);cell.setBackground(new Color(a,b,c));cell.setBorder(blackline);cell.setHorizontalAlignment(SwingConstants.CENTER);cell.setFont(new Font("Leelawadee UI", Font.BOLD, 10));}
//                                System.out.println("Values: " + value);
                            }
                        }
                    
                return cell;
            }
        };
        jTable1.getColumnModel().getColumn(columnToColor).setCellRenderer(cellRenderer);
        jTable1.getColumnModel().getColumn(columnToColor2).setCellRenderer(cellRenderer);
        jTable1.getColumnModel().getColumn(columnToColor3).setCellRenderer(cellRenderer);
        jTable1.getColumnModel().getColumn(columnToColor4).setCellRenderer(cellRenderer);
        
        TableColumn sportColumn = jTable1.getColumnModel().getColumn(1);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("[Select status]");
        for(int b = 0; b < rowstat.size(); b++){
            String[] rowa3 = rowstat.get(b);
            String v1 = rowa3[0];
            String v2 = rowa3[1];
            comboBox.addItem(v2);
        }
        comboBox.setSelectedItem("[Select status]");
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                Object rowData = jTable1.getValueAt(selectedRow, 0);
                System.out.println("The combo box has been used (selection changed). "+rowData);
                if(comboBox.getSelectedItem().equals("[Select status]")){}
                else{
                    
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the status to \"" + comboBox.getSelectedItem() + "\" ?",
                    "Confirmation Message", JOptionPane.YES_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    
                    if(nameer.getText().equals(name_btn_click) || utype.getText().equals("Admin")){

                        try {
                            //Class.forName("org.postgresql.Driver");
                            Connection conn = DriverManager.getConnection(url, user, password);
                            int sta = getColumnIndexByName(jTable1, "Status");
                            int selectedRowS = jTable1.getSelectedRow();
                            Object rowDataS = jTable1.getValueAt(selectedRow, sta);
                            //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
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
                            String get_stat = "";
//                            String get_stat2 = "";
                            for(int b = 0; b < rowstat.size(); b++){
                                String[] rowa3 = rowstat.get(b);
                                String v1 = rowa3[0];
                                String v2 = rowa3[1];
                                if(v1.equals(status_check)){get_stat = v2;}
//                                else if(v1.equals(rowDataS)){get_stat2 = v2;}
                            }
//                            JOptionPane.showMessageDialog(null, get_stat);
                            stmtact.setInt(1, num + 1);
                            stmtact.setString(2, "A_"+String.valueOf(num + 1));
                            stmtact.setString(3, String.valueOf(rowData));
                            stmtact.setString(4, String.valueOf("Change Status"));
                            stmtact.setString(5, String.valueOf(date+" - "+O_time));
                            stmtact.setString(6, String.valueOf(get_stat));
                            stmtact.setString(7, String.valueOf(comboBox.getSelectedItem()));
                            int rowsAffected = stmtact.executeUpdate();

                            if (rowsAffected > 0) {
                                System.out.println("Data updated successfully!");
                            }
                            /// NEXT SQL

                            String sql = "UPDATE JOSH.TEMPLATE SET  D_4_ID = ?, DU = ? WHERE T_ID = ?";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            String newstat = "";
                            for(int b = 0; b < rowstat.size(); b++){
                                String[] rowa3 = rowstat.get(b);
                                String v1 = rowa3[0];
                                String v2 = rowa3[1];
                                if(v2.equals(comboBox.getSelectedItem())){newstat = v1;}
                            }

            //                // Set parameters for the PreparedStatement
                            stmt.setString(1, String.valueOf(newstat));
                            stmt.setString(2, String.valueOf(dateupdated));
                            stmt.setString(3, String.valueOf(rowData));

                            // Execute the INSERT statement
                            int rowsAffected2 = stmt.executeUpdate();

                            if (rowsAffected2 > 0) {
                                System.out.println("Data updated successfully!");
                            }

                            // Close the resources
                            resultSetact.close();
                            stmt2act.close();
                            stmtact.close();
                            stmt.close();
                            conn.close();

                            NewPanel A_display = new NewPanel(); A_display.setBackground(new Color(244,207,95));
                            JLabel namestat = new JLabel("Change status"); JLabel date_time = new JLabel(date+" - "+O_time); date_time.setFont(new Font("Nirmala UI", Font.PLAIN, 10)); nameer.setFont(new Font("segoe UI", Font.BOLD, 12));
                            JEditorPane left_txt = new JEditorPane(); left_txt.setText(status_check.toUpperCase() + " ➡ \n"+rowDataS.toString().toUpperCase()); left_txt.setEditable(false); left_txt.setBackground(new Color(255, 255, 255)); left_txt.setForeground(Color.BLACK);left_txt.setBounds(5, 5, 200, left_txt.getPreferredSize().height);
                            NewPanel cover_t = new NewPanel(); cover_t.add(left_txt); cover_t.setBackground(new Color(255, 255, 255));cover_t.setLayout(null);
                            GroupLayout A_gl = new GroupLayout(A_display);A_display.setLayout(A_gl);

                            A_gl.setHorizontalGroup(A_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                      .addGroup(GroupLayout.Alignment.TRAILING, A_gl.createSequentialGroup()
                                          .addGap(15)
                                          .addComponent(namestat, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                              .addGap(20,20,20)
                                              .addComponent(date_time, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                          .addGap(30,30,30))
                                    .addGroup(GroupLayout.Alignment.LEADING, A_gl.createSequentialGroup()
                                          .addGap(15)
                                          .addComponent(cover_t, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
                                      );

                              A_gl.setVerticalGroup(A_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                  .addGroup(A_gl.createSequentialGroup() 
                                          .addGap(0)
                                          .addGroup(A_gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                              .addComponent(namestat, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE)
                                          .addComponent(date_time, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE))
                                          .addContainerGap(0, Short.MAX_VALUE)
                                          .addComponent(cover_t, GroupLayout.DEFAULT_SIZE, left_txt.getPreferredSize().height + 10, GroupLayout.DEFAULT_SIZE)
                                  .addContainerGap(0, Short.MAX_VALUE))
                              );

                            activities_length += A_display.getPreferredSize().height + 10;
                            activity_panel.add(A_display);
                            activity_panel.revalidate();
                            activity_panel.repaint();
                            activity_panel.setPreferredSize(new Dimension(0, activities_length));
                            // Auto refresh
                            String txt = "[Change1]"+"["+ nameer.getText() +"]";
                            add_His(txt);
                            // 
                        } catch (SQLException oe) {
                            System.out.println("Error: " + oe.getMessage());
                        }

                        }else{JOptionPane.showMessageDialog(null, "Sorry you cant change this status, it isn't yours.");}
                    }
                
                
                add_db_template();
                add_db();
                show_table();
                sub_parent.doClick();
                // Perform actions when the combo box is used
            }
            }
        });
        comboBox.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    // Action to perform when the JComboBox loses focus
                    sub_parent.doClick();
                }
            });
        
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        
        show_in_jtable1();
    }
    
    public void show_in_jtable1(){
        String[] parts = nameer.getText().split("\\s+");
        String firstName = parts[0];
        
        boolean contain = false;
        int rowCount = jTable1.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String nameInRow = jTable1.getValueAt(i, 0).toString();
            
            String filePath = name2+"\\log\\logfolder\\Ccall.txt";
            StringBuilder parentPathBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String data = extractValue(line, "\\[(.*?)\\]");
                    String[] parts1 = line.split("\\s*\\]\\s*\\[\\s*");
                    if (parts1.length >= 4) {
                        String one = parts1[1];
                        String two = parts1[2];
                        String three = parts1[3].replaceAll("\\]", ""); // Remove trailing brackets from ID
                        if(nameInRow.equals(three) && firstName.equals(data)){
                            contain = true;
                            break;
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if(contain == true){
                // Set the selection to the found row
                jTable1.getSelectionModel().setSelectionInterval(i, i);
                // Scroll to the selected row if necessary
                jTable1.scrollRectToVisible(jTable1.getCellRect(i, 0, true));
                break;  // Stop iterating once a match is found
            }
                
        }
    }
    
    public void Ref(){
        String filePath = name2+"\\log\\logfolder\\refresh_a_work.txt";
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
                  if(!nameer.getText().equals(id) && date.equals("Change")){jLabel1.setVisible(true);}
                    
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
    
    private static void customizeTableHeader(JTable table) {
        JTableHeader header = table.getTableHeader();

        // Set height of column header
        header.setPreferredSize(new Dimension(header.getWidth(), 30));

        // Set font size for column header
        Font headerFont = new Font("Leelawadee UI", Font.BOLD, 12); // Adjust font settings as needed
        header.setFont(headerFont);
        header.setForeground(new Color(102,102,102));
        
        // Set background color for column header
        header.setBackground(new Color(255,255,255));

        // Set default cell renderer for header
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER); // Optional: Center-align text in headers
    }

    class jPanelGradient extends JPanel {
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            
            Color color1 = new Color(204,255,204);
            Color color2 = new Color(153,255,153);
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

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        newPanel1 = new v1_tm_projectmanagement.NewPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        adduser = new javax.swing.JPanel();
        newPanel2 = new v1_tm_projectmanagement.NewPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        addcom = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        newPanel3 = new v1_tm_projectmanagement.NewPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        activity_panel = new jPanelGradient();
        Send = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        proj = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        newPanel4 = new v1_tm_projectmanagement.NewPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Comment = new javax.swing.JEditorPane();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmenu = new javax.swing.JMenu();
        m1 = new javax.swing.JMenuItem();
        m2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        utype.setText("jLabel4");

        admin_user.setText("jLabel3");

        nameer.setText("nam");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(utype)
                .addGap(18, 18, 18)
                .addComponent(admin_user)
                .addGap(18, 18, 18)
                .addComponent(nameer, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utype)
                    .addComponent(admin_user)
                    .addComponent(nameer, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(206, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        jLabel6.setText("SUPERMAN");

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel7.setText("User");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel7)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ProjectMS - v1.0.0.0");
        setLocation(new java.awt.Point(68, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("[REFRESH NEEDED]");

        newPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        adduser.setBackground(new java.awt.Color(204, 255, 204));
        adduser.setPreferredSize(new java.awt.Dimension(60, 450));
        adduser.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        jScrollPane1.setViewportView(adduser);

        javax.swing.GroupLayout newPanel1Layout = new javax.swing.GroupLayout(newPanel1);
        newPanel1.setLayout(newPanel1Layout);
        newPanel1Layout.setHorizontalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        newPanel1Layout.setVerticalGroup(
            newPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        newPanel2.setBackground(new java.awt.Color(238, 238, 238));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        addcom.setBackground(new java.awt.Color(238, 238, 238));
        addcom.setPreferredSize(new java.awt.Dimension(60, 514));
        addcom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        jScrollPane2.setViewportView(addcom);

        javax.swing.GroupLayout newPanel2Layout = new javax.swing.GroupLayout(newPanel2);
        newPanel2.setLayout(newPanel2Layout);
        newPanel2Layout.setHorizontalGroup(
            newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );
        newPanel2Layout.setVerticalGroup(
            newPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable1.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        jTable1.setForeground(new java.awt.Color(102, 102, 102));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Status", "Priority", "Type", "#", "Proj #", "Project Description", "Agency", "Location", "Date Updated"
            }
        ));
        jTable1.setSelectionBackground(new java.awt.Color(51, 153, 255));
        jTable1.setShowHorizontalLines(true);
        jScrollPane3.setViewportView(jTable1);

        activity_panel.setBackground(new java.awt.Color(153, 255, 153));
        activity_panel.setPreferredSize(new java.awt.Dimension(130, 540));
        jScrollPane4.setViewportView(activity_panel);

        Send.setBackground(new java.awt.Color(246, 246, 246));
        Send.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Send_24x24.png"))); // NOI18N
        Send.setBorder(null);
        Send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SendMouseEntered(evt);
            }
        });
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Activities");

        proj.setText("Proj #");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        newPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane5.setBorder(null);

        Comment.setBorder(null);
        Comment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Comment.setForeground(new java.awt.Color(51, 51, 51));
        Comment.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CommentFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CommentFocusLost(evt);
            }
        });
        jScrollPane5.setViewportView(Comment);

        javax.swing.GroupLayout newPanel4Layout = new javax.swing.GroupLayout(newPanel4);
        newPanel4.setLayout(newPanel4Layout);
        newPanel4Layout.setHorizontalGroup(
            newPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        newPanel4Layout.setVerticalGroup(
            newPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout newPanel3Layout = new javax.swing.GroupLayout(newPanel3);
        newPanel3.setLayout(newPanel3Layout);
        newPanel3Layout.setHorizontalGroup(
            newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newPanel3Layout.createSequentialGroup()
                        .addGroup(newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(newPanel3Layout.createSequentialGroup()
                                .addComponent(newPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(newPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(proj)
                        .addGap(30, 30, 30))))
        );
        newPanel3Layout.setVerticalGroup(
            newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(proj)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton5.setForeground(new java.awt.Color(102, 102, 102));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/refresh-20.png"))); // NOI18N
        jButton5.setText("Refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Users");

        jLabel4.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Companies");

        jLabel5.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Project lists");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(177, 177, 177)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(newPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setVisible(false);

        jmenu.setText("Home");

        m1.setText("Master's Unit");
        m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m1ActionPerformed(evt);
            }
        });
        jmenu.add(m1);

        m2.setText("Master's List");
        m2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m2ActionPerformed(evt);
            }
        });
        jmenu.add(m2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem3.setText("Project List");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jmenu.add(jMenuItem3);
        jmenu.add(jSeparator2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setText("Logout");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jmenu.add(jMenuItem4);

        jMenuBar1.add(jmenu);

        jMenu2.setText("Wrap");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem5.setText("Wrap");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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
    open_ip();
        add_db_template();
    add_db();
    show_table();
    jLabel1.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed
       
        boolean pass = true;
        int selectedRow = jTable1.getSelectedRow();
        Object rowData = jTable1.getValueAt(selectedRow, 0);
        try {
                //Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);

                //    String sql = "INSERT INTO id (user_name, user_age) VALUES (NULL, NULL)";
                String sql = "INSERT INTO JOSH.ACTIVITIES(A_NO, A_ID, T_ID, NAME, DATE_TIME, COMMENT, COMMENT2)VALUES (?, ?, ?, ?, ?, ?, ?)";
                String sql2 = "SELECT * FROM JOSH.ACTIVITIES WHERE A_NO = (SELECT MAX(A_NO) FROM JOSH.TEMPLATE)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                //

                ResultSet resultSet = stmt2.executeQuery();
                int num = 0;
                if (resultSet.next()) {
                    int lastValue = resultSet.getInt("A_NO");
                    num = lastValue;
                    System.out.println("Last value of A_NO column: " + lastValue);
                    // Use the lastValue variable as needed
                }
                
                
//                // Set parameters for the PreparedStatement
                stmt.setInt(1, num + 1);
                stmt.setString(2, "A_"+String.valueOf(num + 1));
                stmt.setString(3, String.valueOf(rowData));
                stmt.setString(4, String.valueOf(nameer.getText()));
                stmt.setString(5, String.valueOf(date+" - "+O_time));
                stmt.setString(6, String.valueOf(Comment.getText()));
                stmt.setString(7, String.valueOf("asd123"));

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
                JOptionPane.showMessageDialog(null, "Your message exceeds the maximum character limit of 200 characters and cannot be saved.");
                pass = false;
            }
        if(pass == true){
        
        NewPanel A_display = new NewPanel(); A_display.setBackground(Color.WHITE);
        JLabel namedisplay = new JLabel(nameer.getText()); JLabel date_time = new JLabel(date+" - "+O_time); date_time.setFont(new Font("Nirmala UI", Font.PLAIN, 10)); nameer.setFont(new Font("segoe UI", Font.BOLD, 12));
        JEditorPane left_txt = new JEditorPane(); left_txt.setText(Comment.getText()); left_txt.setEditable(false); left_txt.setBackground(new Color(153, 204, 255)); left_txt.setForeground(Color.BLACK); //left_txt.setBounds(5, 2, 200, left_txt.getPreferredSize().height + 50);
        NewPanel cover_t = new NewPanel(); cover_t.add(left_txt); cover_t.setBackground(new Color(153, 204, 255));cover_t.setLayout(null);

        GroupLayout TEXT_gl = new GroupLayout(cover_t);cover_t.setLayout(TEXT_gl);

        TEXT_gl.setHorizontalGroup(TEXT_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                  .addGroup(GroupLayout.Alignment.TRAILING, TEXT_gl.createSequentialGroup()
                      .addGap(5)
                      .addComponent(left_txt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                          .addGap(0,0,0))
                  );

          TEXT_gl.setVerticalGroup(TEXT_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
              .addGroup(TEXT_gl.createSequentialGroup() 
                      .addGap(0)
                      .addGroup(TEXT_gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                          .addComponent(left_txt, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE))
              )
          );
        
        GroupLayout A_gl = new GroupLayout(A_display);A_display.setLayout(A_gl);
        
        A_gl.setHorizontalGroup(A_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                  .addGroup(GroupLayout.Alignment.TRAILING, A_gl.createSequentialGroup()
                      .addGap(15)
                      .addComponent(namedisplay, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                          .addGap(20,20,20)
                          .addComponent(date_time, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                      .addGap(30,30,30))
                .addGroup(GroupLayout.Alignment.LEADING, A_gl.createSequentialGroup()
                      .addGap(15)
                      .addComponent(cover_t, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
                  );

          A_gl.setVerticalGroup(A_gl.createParallelGroup(GroupLayout.Alignment.LEADING)
              .addGroup(A_gl.createSequentialGroup() 
                      .addGap(0)
                      .addGroup(A_gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                          .addComponent(namedisplay, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE)
                      .addComponent(date_time, GroupLayout.DEFAULT_SIZE, 30, GroupLayout.DEFAULT_SIZE))
                      .addContainerGap(0, Short.MAX_VALUE)
                      .addComponent(cover_t, GroupLayout.DEFAULT_SIZE, left_txt.getPreferredSize().height + 10, GroupLayout.DEFAULT_SIZE)
              .addContainerGap(0, Short.MAX_VALUE))
          );
        
        activities_length += A_display.getPreferredSize().height + 10;
        activity_panel.add(A_display);
        activity_panel.revalidate();
        activity_panel.repaint();
        activity_panel.setPreferredSize(new Dimension(0, activities_length));
                            
        add_db_template();
        add_db();
        show_table();
        
        Pattern pattern = Pattern.compile("@(\\w+)");
        Matcher matcher = pattern.matcher(Comment.getText());

        // Iterate through the matches and extract the usernames
        while (matcher.find()) {
            String mention = matcher.group(); // Get the mention, e.g., "@Kevin"
            String username = matcher.group(1); // Get the username without "@", e.g., "Kevin"
            System.out.println("Mention: " + mention + ", Username: " + username);
            add_userhist("["+username+"]" + "[" + one_btn + "]" + "[" + two_btn + "]" + "[" + three_btn + "]" );
            
            // Auto refresh
            String txt = "[Change]"+"["+ nameer.getText() +"]";
            add_His(txt);
        }
        
        
        Comment.setText(null);
        
        }
        
    }//GEN-LAST:event_SendActionPerformed

    private void m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m1ActionPerformed
        if(utype.getText().equals("Admin")){
            C_WORK a = new C_WORK();
            a.setVisible(true);
        }
    }//GEN-LAST:event_m1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Login a = new Login();
        a.setVisible(true);
        A_W.this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void CommentFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CommentFocusGained
        if(Comment.getText().equals("Message"))
        {
            Comment.setText("");
            Comment.setForeground(new Color(153,153,153));
        }
    
    }//GEN-LAST:event_CommentFocusGained

    private void CommentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CommentFocusLost
                                 
        if(Comment.getText().equals(""))
        {
            Comment.setText("Message");
            Comment.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_CommentFocusLost

    private void m2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m2ActionPerformed
        if(utype.getText().equals("Admin")){
            E_WORK a = new E_WORK();
            a.setVisible(true);
        }
    }//GEN-LAST:event_m2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
            PROJECT_LIST a = new PROJECT_LIST();
            a.setVisible(true);
            a.users.setText(admin_user.getText());
            a.utype.setText(utype.getText());
            a.supername.setText(nameer.getText());
    }//GEN-LAST:event_jMenuItem3ActionPerformed
int setscroll = 0;
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
           // Calculate row heights
        for (int row = 0; row < jTable1.getRowCount(); row++) {
    int maxHeight = 0;
    for (int column = 0; column < jTable1.getColumnCount(); column++) {
        TableCellRenderer cellRenderer = jTable1.getCellRenderer(row, column);
        Component comp = jTable1.prepareRenderer(cellRenderer, row, column);
        
        // Use the preferred height of the renderer
        int cellHeight = comp.getPreferredSize().height;
        
        maxHeight = Math.max(maxHeight, cellHeight);
    }
    
    jTable1.setRowHeight(row, maxHeight + 15); // Add some padding to the row height
}
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void SendMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SendMouseEntered
        if(Comment.getText().equals("")){Send.setEnabled(false);}
        else{Send.setEnabled(true);}
    }//GEN-LAST:event_SendMouseEntered

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
                new A_W().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane Comment;
    private javax.swing.JButton Send;
    private javax.swing.JPanel activity_panel;
    private javax.swing.JPanel addcom;
    private javax.swing.JPanel adduser;
    public static final javax.swing.JLabel admin_user = new javax.swing.JLabel();
    private javax.swing.JButton jButton5;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenu jmenu;
    private javax.swing.JMenuItem m1;
    private javax.swing.JMenuItem m2;
    public static final javax.swing.JLabel nameer = new javax.swing.JLabel();
    private v1_tm_projectmanagement.NewPanel newPanel1;
    private v1_tm_projectmanagement.NewPanel newPanel2;
    private v1_tm_projectmanagement.NewPanel newPanel3;
    private v1_tm_projectmanagement.NewPanel newPanel4;
    private javax.swing.JLabel proj;
    public static final javax.swing.JLabel utype = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
