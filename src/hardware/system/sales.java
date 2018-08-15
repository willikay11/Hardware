/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hardware.system;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author kamau
 */
public class sales extends javax.swing.JFrame {
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;
int get;
int gett;
public static String Code;
public static String Name;
public static String manufacture;
public static String max;
public static String Quantity;
public static String Units;
public static String Amount;
public static String Receipt;
public static String Total;
public static String Customer;
public static String BuyingPrice;
String payment;
int total;
int q = 0;
int it;
    /**
     * Creates new form sales
     */
    public sales() {
        initComponents();
    }
public void debtor(){
  String sql = "INSERT INTO `debtoracc`(`Debtor_Account`, `Name`, `items`, `Total_Amount`, `Receipt_Number`, `Date`, `User`) VALUES ('"+creditQuery.CreditCode+"','"+creditQuery.CreditName+"','"+it+"','"+txtTotal.getText()+"','"+lbReceipt.getText()+"','1','1')";
      try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
}
public void credit(){
    for(int i =1;i<purchased.getRowCount();i++){
    int it = i;
    System.out.println(it);
    }
  String sql = "INSERT INTO `transaction`(`name`, `receipt`, `items`, `Total`, `Amount`, `Change`, `payments`) VALUES ('"+sales.Customer+"','"+sales.Receipt+"','"+it+"','"+txtTotal.getText()+"','0','0','Credit')";
      try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
}
public void query(){
String sql = "SELECT `code`FROM `sales` WHERE `receipt` = '"+lbReceipt.getText()+"' and `code` = '"+Code+"'";
try{
   pst = conn.prepareStatement(sql);
   rs = pst.executeQuery();
   while (rs.next()) {
   q=1;
   }
   if(q==1){JOptionPane.showMessageDialog(null, "Item has already been added to customers cart");
   }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
public void update(){
String sql ="UPDATE `codes` SET `code`= '"+txtmax1.getText()+"' WHERE `index` = 3 ";
try{
pst = conn.prepareStatement(sql);
pst.execute(sql);
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
public void open(){
if(Integer.parseInt(txtTotal.getText())==0){
buttons();
txtTotal.setText("0");
}else{
cash.setEnabled(true);
cheque.setEnabled(true);
credit.setEnabled(true);
mpesa.setEnabled(true);
}
}
public void buttons(){
cash.setEnabled(false);
cheque.setEnabled(false);
credit.setEnabled(false);
mpesa.setEnabled(false);
cmdSale.setEnabled(false);
}
public void reduce(){
    Object x,y,z;
    int t;
    for(int i =0;i<purchased.getRowCount();i++){
        x=purchased.getValueAt(i, 1);
        y=purchased.getValueAt(i, 4);
        z=purchased.getValueAt(i, 5);
        txtQ.setText(purchased.getModel().getValueAt(i, 4).toString());
        txtQ1.setText(purchased.getModel().getValueAt(i, 5).toString());
        txtQ.setText(Integer.toString(Integer.parseInt(txtQ.getText()) - Integer.parseInt(txtQ1.getText()))); 
      String sql = "UPDATE `inventory` SET `Quantity`='"+txtQ.getText()+"' WHERE `Code` = '"+x+"'";
      try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
     }catch(Exception e){
JOptionPane.showMessageDialog(null, e);
 }
    }
}
public void savecredit(){
    String sql = "UPDATE `sales` SET `payment`='"+payment+"',`creditcode`='"+creditQuery.CreditCode+"'WHERE `receipt` = '"+lbReceipt.getText()+"'";
      try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
          JOptionPane.showMessageDialog(null, "Items Sold");
     }catch(Exception e){
JOptionPane.showMessageDialog(null, e);
 }
}
public void save(){
    String sql = "UPDATE `sales` SET `payment`='"+payment+"' WHERE `receipt` = '"+lbReceipt.getText()+"'";
      try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
           JOptionPane.showMessageDialog(null, "Items Sold");
     }catch(Exception e){
JOptionPane.showMessageDialog(null, e);
 }
}
public void total(){
if(purchased.getRowCount()>=1){
String sql = "SELECT SUM(`TotalAmount`) FROM `sales` WHERE `receipt` = '"+lbReceipt.getText()+"'";
try{
   pst = conn.prepareStatement(sql);
   rs = pst.executeQuery();
   while (rs.next()) {
   txtTotal.setText(rs.getString("SUM(`TotalAmount`)"));}
   Total = txtTotal.getText();
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
public void del(){   
int p = JOptionPane.showConfirmDialog(null, "Removing the following item "+txtdel.getText()+" from "+txtname.getText()+"'s Cart","Delete",JOptionPane.YES_NO_OPTION);
        if (p==0){
      String sql = "DELETE FROM `sales` WHERE `code` = '"+txtdel.getText()+"'";
try{
    pst = conn.prepareStatement(sql);
    pst.execute(sql);
    JOptionPane.showMessageDialog(null, "Delete Successful");
    purchased();
    txtdel.setText("");
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
        }
}
public void quantity(){
Quantity open = new Quantity();
open.setVisible(true);
open.setLocationRelativeTo(null);
}
public void purchased(){
String sql = "SELECT `receipt`,`code`,`item`, `manufacturer`,`StolkQuantity`, `quantity`, `units`, `amount`, `TotalAmount` FROM `sales` WHERE `receipt` = '"+lbReceipt.getText()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    purchased.setModel(DbUtils.resultSetToTableModel(rs));    
    total();
}catch(Exception e){
//JOptionPane.showMessageDialog(null, "Receipt Number '"+lbReceipt.getText()+"'");
}
}
public void receipt(){
String sql = "SELECT `code` FROM `codes` WHERE `index` = 3";
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
    lbReceipt.setText(rs.getString("code"));
    Receipt = lbReceipt.getText();
    txtmax1.setText(Integer.toString(Integer.parseInt(lbReceipt.getText())+1));  
 }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
public void saveitems(){
int p = JOptionPane.showConfirmDialog(null, "Adding the following item "+txtname1.getText()+" to "+txtname.getText()+"'s Cart","Accept",JOptionPane.YES_NO_OPTION);
        if (p==0){
      quantity();
        }
}
public void name(){
String sql = "SELECT `Code`, `Name`, `Initials`, `Manufacturer`, `Quantity`,`buyingPrice`,`SellingPrice`, `Units` FROM `inventory` WHERE `Name` like '%"+txtSearch.getText()+"%'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    items.setModel(DbUtils.resultSetToTableModel(rs));    
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
public void Code(){
String sql = "SELECT `Code`, `Name`, `Initials`, `Manufacturer`, `Quantity`,`buyingPrice`,`SellingPrice`, `Units` FROM `inventory` WHERE `Code` like '%"+txtSearch.getText()+"%'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    items.setModel(DbUtils.resultSetToTableModel(rs));    
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
public void manufacturer(){
String sql = "SELECT `Code`, `Name`, `Initials`, `Manufacturer`, `Quantity`, `buyingPrice`,`SellingPrice`, `Units` FROM `inventory` WHERE `Manufacturer` like '%"+txtSearch.getText()+"%'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    items.setModel(DbUtils.resultSetToTableModel(rs));    
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbReceipt = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        manufacturer = new javax.swing.JRadioButton();
        code = new javax.swing.JRadioButton();
        name = new javax.swing.JRadioButton();
        txtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtcode = new javax.swing.JTextField();
        txtman = new javax.swing.JTextField();
        txtmax = new javax.swing.JTextField();
        txtA = new javax.swing.JTextField();
        txtU = new javax.swing.JTextField();
        txtname1 = new javax.swing.JTextField();
        txtmax1 = new javax.swing.JTextField();
        txtdel = new javax.swing.JTextField();
        txtT = new javax.swing.JTextField();
        txtQ = new javax.swing.JTextField();
        txtQ1 = new javax.swing.JTextField();
        txtB = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        items = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        purchased = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        credit = new javax.swing.JRadioButton();
        cash = new javax.swing.JRadioButton();
        cheque = new javax.swing.JRadioButton();
        mpesa = new javax.swing.JRadioButton();
        cmdSale = new javax.swing.JButton();
        cmdClear = new javax.swing.JButton();
        cmdClose = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(101, 87, 15));
        jLabel1.setText("Sales");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(101, 87, 15));
        jLabel5.setText("RECEIPT NO:");

        lbReceipt.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lbReceipt.setForeground(new java.awt.Color(101, 87, 15));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel2.setText("Name");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Criteria"));

        buttonGroup1.add(manufacturer);
        manufacturer.setText("Manufacturer");
        manufacturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manufacturerActionPerformed(evt);
            }
        });

        buttonGroup1.add(code);
        code.setText("Code");
        code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeActionPerformed(evt);
            }
        });

        buttonGroup1.add(name);
        name.setText("Name");
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        jButton1.setText("Search");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(name)
                        .addGap(18, 18, 18)
                        .addComponent(code)
                        .addGap(18, 18, 18)
                        .addComponent(manufacturer)
                        .addGap(16, 16, 16)
                        .addComponent(txtQ, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtman, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmax, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtU, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtname1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQ1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtmax1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtdel)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name)
                    .addComponent(code)
                    .addComponent(manufacturer)
                    .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmax1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        items.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        items.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(items);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Items Purchased"));

        purchased.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        purchased.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchasedMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(purchased);

        jPanel5.setBackground(new java.awt.Color(255, 255, 0));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 2, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("<< CLICK TO REMOVE ITEM FROM CART>>");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Payments Via"));

        buttonGroup2.add(credit);
        credit.setText("Credit");
        credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditActionPerformed(evt);
            }
        });

        buttonGroup2.add(cash);
        cash.setText("Cash");
        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });

        buttonGroup2.add(cheque);
        cheque.setText("Cheque");
        cheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chequeActionPerformed(evt);
            }
        });

        buttonGroup2.add(mpesa);
        mpesa.setText("M-Pesa");
        mpesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mpesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cheque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(credit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mpesa)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cash)
                    .addComponent(cheque)
                    .addComponent(credit)
                    .addComponent(mpesa))
                .addContainerGap())
        );

        cmdSale.setText("Sale");
        cmdSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaleActionPerformed(evt);
            }
        });

        cmdClear.setText("Clear");

        cmdClose.setText("Close");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 0));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 2, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<< CLICK TO ADD ITEM FOR PURCHASE>>");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3)
        );

        jLabel4.setText("Total Amount");

        txtTotal.setEditable(false);
        txtTotal.setText("0");
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdSale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdClear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdClose))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdSale)
                            .addComponent(cmdClear)
                            .addComponent(cmdClose))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manufacturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manufacturerActionPerformed
        // TODO add your handling code here:
        if(manufacturer.isSelected()){
            txtSearch.setEnabled(true);
            get=3;
        }
    }//GEN-LAST:event_manufacturerActionPerformed

    private void codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeActionPerformed
        // TODO add your handling code here:
        if(code.isSelected()){
            txtSearch.setEnabled(true);
            get=2;
        }
    }//GEN-LAST:event_codeActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
        if(name.isSelected()){
            txtSearch.setEnabled(true);
            get=1;
        }
    }//GEN-LAST:event_nameActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        // TODO add your handling code here:
        if(get==1){
            name();
        }if(get==2){
            Code();
        }if(get==3){
            manufacturer();
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        conn = connectDB.ConnectDB();
        txtSearch.setEnabled(false);
        txtQ.setVisible(false);
        txtQ1.setVisible(false);
        txtmax.setVisible(false);
        txtman.setVisible(false);
        txtcode.setVisible(false);
        txtname1.setVisible(false);
        txtA.setVisible(false);
        txtU.setVisible(false);
        txtT.setVisible(false);
        txtmax1.setVisible(false);
        txtdel.setVisible(false);
        buttons();
        receipt();
    }//GEN-LAST:event_formWindowOpened

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void itemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsMouseClicked
        // TODO add your handling code here:
        try{
         int row = items.getSelectedRow();
        txtcode.setText(items.getModel().getValueAt(row, 0).toString());
        Code=txtcode.getText();
        query();
        if(q==0){
        txtname1.setText(items.getModel().getValueAt(row, 1).toString());
        Name=txtname1.getText();
        txtman.setText(items.getModel().getValueAt(row, 3).toString());
        manufacture=txtman.getText();
        txtmax.setText(items.getModel().getValueAt(row, 4).toString());
        Quantity=txtmax.getText();
        txtA.setText(items.getModel().getValueAt(row, 6).toString());
        Amount=txtA.getText();
        txtU.setText(items.getModel().getValueAt(row, 7).toString());
        Units=txtU.getText();
        Customer = txtname.getText();
        txtB.setText(items.getModel().getValueAt(row, 5).toString());
        BuyingPrice = txtB.getText();
        saveitems();
        }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "You have selected nothing");
        }     
    }//GEN-LAST:event_itemsMouseClicked

    private void purchasedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchasedMouseClicked
        // TODO add your handling code here:
        try{
        int row = purchased.getSelectedRow();
        txtdel.setText(purchased.getModel().getValueAt(row, 1).toString());
        del();
        if(purchased.getRowCount()==0){
        txtTotal.setText("");
        buttons();
        }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "You have Selected nothing");
        }
    }//GEN-LAST:event_purchasedMouseClicked

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        try{
        if(items.getRowCount()>=1){
        purchased();
        open();
        }
        }catch(Exception e){
        //JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowStateChanged

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
        // TODO add your handling code here:
        if(cash.isSelected()){
            gett = 1;
        cmdSale.setEnabled(true);
        payment="Cash";
        }
    }//GEN-LAST:event_cashActionPerformed

    private void cmdSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaleActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to sale this goods","Accept",JOptionPane.YES_NO_OPTION);
        if (p==0){
        if(gett==1){save();
        purchased.disable();
        reduce();
        update();
        sale open = new sale();
        open.setVisible(true);
        open.setLocationRelativeTo(null);
        }
        if(gett==2){
        
        }
        if(gett==3){
        if(creditQuery.CreditCode==null){
        JOptionPane.showMessageDialog(null, "Debtor has not been selected For credit issue");
        cmdSale.setEnabled(false);
        }else{savecredit();credit();debtor();reduce();update();}
        }
        if(gett==4){}
        }
    }//GEN-LAST:event_cmdSaleActionPerformed

    private void chequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chequeActionPerformed
        // TODO add your handling code here:
        if(cheque.isSelected()){
            gett =2;
        cmdSale.setEnabled(true);
        payment="Cheque";
        }
    }//GEN-LAST:event_chequeActionPerformed

    private void creditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditActionPerformed
        // TODO add your handling code here:
        if(credit.isSelected()){
       creditQuery open = new creditQuery();
       open.setVisible(true);
       open.setLocationRelativeTo(null);
        cmdSale.setEnabled(true);
          gett = 3;
        payment="Credit";
        }
    }//GEN-LAST:event_creditActionPerformed

    private void mpesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mpesaActionPerformed
        // TODO add your handling code here:
        if(mpesa.isSelected()){
            gett = 4;
        cmdSale.setEnabled(true);
        payment="M-Pesa";
        }
    }//GEN-LAST:event_mpesaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton cash;
    private javax.swing.JRadioButton cheque;
    private javax.swing.JButton cmdClear;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdSale;
    private javax.swing.JRadioButton code;
    private javax.swing.JRadioButton credit;
    private javax.swing.JTable items;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbReceipt;
    private javax.swing.JRadioButton manufacturer;
    private javax.swing.JRadioButton mpesa;
    private javax.swing.JRadioButton name;
    private javax.swing.JTable purchased;
    private javax.swing.JTextField txtA;
    private javax.swing.JTextField txtB;
    private javax.swing.JTextField txtQ;
    private javax.swing.JTextField txtQ1;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtT;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtU;
    private javax.swing.JTextField txtcode;
    private javax.swing.JTextField txtdel;
    private javax.swing.JTextField txtman;
    private javax.swing.JTextField txtmax;
    private javax.swing.JTextField txtmax1;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtname1;
    // End of variables declaration//GEN-END:variables
}
