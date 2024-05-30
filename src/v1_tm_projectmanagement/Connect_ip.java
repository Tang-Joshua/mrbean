/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package v1_tm_projectmanagement;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.apache.derby.drda.NetworkServerControl;
import static v1_tm_projectmanagement.Login.location;
import static v1_tm_projectmanagement.Login.ip_add;
import static v1_tm_projectmanagement.A_W.nameer;

/**
 *
 * @author Joshua i3
 */
public class Connect_ip  extends javax.swing.JFrame{
    
    public static boolean one_pin = false;
    
    String full_name = "";
    
    Boolean log = false;
    
//    public void open_ip(){
//        
//        NetworkServerControl obj;
//        try {
//            obj = new NetworkServerControl(InetAddress.getByName(""),1527);
//            obj.start(null);
//            obj.ping();
////                JOptionPane.showMessageDialog(this, "Derby server started successfully!!!.", "Success", JOptionPane.INFORMATION_MESSAGE);
//// Additional code for server startup actions
//            } catch (UnknownHostException ex) {
//        }catch (Exception ex) {
//        }
//    }
    // EXIT FUNCTION
    public Connect_ip() {
        
        // Set up your frame properties
        
        // Add a WindowListener to handle window closing event
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            
                ////// Location of IP
               String filePath2 = location.getText()+"\\log\\logfolder\\address.txt";
                String fPath = filePath2.replace("\\", "\\\\");
        //        String[] pathParts = get2.split("\\\\");
                StringBuilder parentPathBuilder = new StringBuilder();
                String parentPath = parentPathBuilder.toString();
                String filepath = parentPath + filePath2;
                
                
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    String localIpAddress = inetAddress.getHostAddress();
                    
                    BufferedReader reader = new BufferedReader(new FileReader(filepath));
                    StringBuilder content = new StringBuilder();
                    
                    
                    System.out.println(filepath);

                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.contains(localIpAddress)) { // Exclude the second line
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
                
                clear();
                // For example, display a message before exiting
                System.out.println("Window closed ");
                
                // Terminate the application
//                System.exit(0);
            }
        });
    }
    
    // IP ADDRESS
    
    public void Location_of_TaskDB(){
        // Description: This "Location_of_TaskDB()" will locate the file(Task_Location.txt) for locating derby database.
        // NOTE: The user will physically add the location text in (Task_Location.txt).
         
        String filePath2 = "C:\\Program Files (x86)\\Pms_f\\dist\\Task_Location.txt";
        String fPath = filePath2.replace("\\", "\\\\");
        StringBuilder parentPathBuilder = new StringBuilder();

        String parentPath = parentPathBuilder.toString();
        String filepath = parentPath + filePath2;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
                String line1 = reader.readLine();
                    reader.close();
//            location_path = line1;
            location.setText(line1);
            } catch (IOException e) {
            }
    }
    
    public void Add_new_ip(){
            // Description : This "Add_new_ip()" will add new ip address to the last line of the text
            
            if(!one_pin){
             // Get IP
            try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String localIpAddress = inetAddress.getHostAddress();
            // "192.168.1.25"

             ////// Location of IP
            String filePath2 = location.getText()+"\\log\\logfolder\\address.txt";
            String fPath = filePath2.replace("\\", "\\\\");
    //        String[] pathParts = get2.split("\\\\");
            StringBuilder parentPathBuilder = new StringBuilder();
            String parentPath = parentPathBuilder.toString();
            String filepath = parentPath + filePath2;
//              JOptionPane.showMessageDialog(null, filepath);
            ////// Add IP into IP_Config (Folder)
            try {
                // Read the file into a list of strings
                List<String> lines = Files.readAllLines(Paths.get(filepath));
                // Append the text to the last element of the list
                lines.add(localIpAddress);
                // Write the updated list back to the file
                Files.write(Paths.get(filepath), lines, StandardOpenOption.WRITE);
                System.out.println("Text added to the last line.");
            } catch (IOException e) {
                e.printStackTrace();
            }


                System.out.println("Local IP address: " + localIpAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            one_pin = true;
            }
     }
    
    public void read_ip(){
        // Description : This "read_ip()" will read the first line of file(address.txt) and get its ip address to 
        // run its server
        
        String filePath2 = location.getText()+"\\log\\logfolder\\address.txt";
        String fPath = filePath2.replace("\\", "\\\\");
//        String[] pathParts = get2.split("\\\\");
        StringBuilder parentPathBuilder = new StringBuilder();
        String parentPath = parentPathBuilder.toString();
        String filepath = parentPath + filePath2;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            // Read the first line
             String line1 = reader.readLine();
                reader.close();
            ip_add.setText(line1);
            } catch (IOException e) {
//                e.printStackTrace();
//            remove_empt();
            }
    }
    
    public void next_ip(){
        // Description : This "next_ip()" will iterate the file(address.txt) to set the inactive ip address to the last line, so the 
        // next line of ip address will check if the server is active. 
        
        ////// Location of IP
        String filePath2 = location.getText()+"\\log\\logfolder\\address.txt";

        StringBuilder parentPathBuilder = new StringBuilder();
        String parentPath = parentPathBuilder.toString();
        String filepath = parentPath + filePath2;

            try {

                BufferedReader reader = new BufferedReader(new FileReader(filepath));
                StringBuilder content = new StringBuilder();


                System.out.println(filepath);

                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.contains(ip_add.getText())) { // Exclude the second line
                        content.append(line).append(System.lineSeparator());
                    }
                }

                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
                writer.write(content.toString());
                writer.close();

                List<String> lines = Files.readAllLines(Paths.get(filepath));
                // Append the text to the last element of the list
                lines.add(ip_add.getText());
                // Write the updated list back to the file
                Files.write(Paths.get(filepath), lines, StandardOpenOption.WRITE);
                read_ip();
            } catch (IOException er) {
                er.printStackTrace();
                    remove_empt();
            }catch (Exception ex) {
            }
                if(log == false){
                    JOptionPane.showMessageDialog(null, "Server has changed.");}
                    read_ip();
    }
    
    public void remove_empt(){
        // Description : This "remove_empt()" will iterate the file(address) to remove all null text in each line
        try {
            String filePath2 = location.getText()+"\\log\\logfolder\\address.txt";

        StringBuilder parentPathBuilder = new StringBuilder();
        String parentPath = parentPathBuilder.toString();
        String filepath = parentPath + filePath2;
            // Read the file and filter out empty lines
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
            reader.close();

            // Write the filtered content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            for (String filteredLine : lines) {
                writer.write(filteredLine);
                writer.newLine();
            }
            writer.close();

//            System.out.println("Empty lines removed from the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // END OF LINE (IP ADDRESS)
//    String txt = "["+ddd+"]" + "["+O_time.getText()+ "]" + "["+p_name.getText()+"]" +"["+PN+"]" +"["+Sub_D_No+"]"+"["+ firstLine +"]"+"["+ " - " +"]"+"["+ " - " +"]"+"["+ " - " +"]"+"["+ "Clicked \"Prepared\" " +"]"+"["+ setchildID +"]";
//    add_His(txt);
    public void add_His(String data){
        ////// Location of IP
        String filePath2 = location.getText()+"\\log\\logfolder\\refresh_a_work.txt";

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
            Logger.getLogger(Connect_ip.class.getName()).log(Level.SEVERE, null, ex);
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
        String filePath2 = location.getText()+"\\log\\logfolder\\refresh_a_work.txt";

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
    
    public void clear(){
        String filePath2 = location.getText()+"\\log\\logfolder\\refresh_a_work.txt";

        StringBuilder parentPathBuilder = new StringBuilder();
        String parentPath = parentPathBuilder.toString();
        String filepath = parentPath + filePath2;

        try {
            BufferedWriter  reader = new BufferedWriter (new FileWriter(filepath));
            StringBuilder content = new StringBuilder();
            reader.write("");

        } catch (IOException er) {
            er.printStackTrace();
        }catch (Exception ex) {
        }

//        read_ip();
    }
    
    // Add chat function
    public void add_userhist(String data){
        String filePath2 = location.getText()+"\\log\\logfolder\\Ccall.txt";

        StringBuilder parentPathBuilder = new StringBuilder();
        String parentPath = parentPathBuilder.toString();
        String filepath = parentPath + filePath2;
        ////// Add IP into IP_Config (Folder)
        try {
        // Read the file into a list of strings
        List<String> lines = Files.readAllLines(Paths.get(filepath));
        // Append the text to the last element of the list
        // Encrypt the original text
        lines.add(data);
        // Write the updated list back to the file
        Files.write(Paths.get(filepath), lines, StandardOpenOption.WRITE);
        System.out.println("Text added to the last line.");
        } catch (IOException e3) {e3.printStackTrace();} catch (Exception ex) { 
            Logger.getLogger(Connect_ip.class.getName()).log(Level.SEVERE, null, ex);
        } 
        // Refresh 
    }
    
    public void del_userhist(String txt){
        String filePath2 = location.getText()+"\\log\\logfolder\\Ccall.txt";

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

    }
}
