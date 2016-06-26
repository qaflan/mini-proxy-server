
import java.util.Vector;
import java.util.Hashtable;
import java.io.*;
import javax.swing.ListModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GUI.java
 *
 * Created on Jun 9, 2010, 12:29:51 PM
 */

/**
 *
 * @author Cavad
 */
public class GUI extends javax.swing.JFrame {


    /** Creates new form GUI */
    public GUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Status = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPortNumber = new javax.swing.JTextField();
        btnStartStop = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstWords = new javax.swing.JList();
        btnClrWords = new javax.swing.JButton();
        btnRmvWords = new javax.swing.JButton();
        txtAddWord = new javax.swing.JTextField();
        btnAddWords = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstHosts = new javax.swing.JList();
        btnRmvSelHost = new javax.swing.JButton();
        btnClrHost = new javax.swing.JButton();
        btnAddHost = new javax.swing.JButton();
        txtAddHost = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Server Status"));

        Status.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Status.setText("Stopped");

        jLabel1.setText("Port Number:");

        btnStartStop.setText("Start Server");
        btnStartStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Status)
                .addGap(108, 108, 108)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStartStop, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Status)
                    .addComponent(jLabel1)
                    .addComponent(txtPortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStartStop))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Banned Words"));

        jScrollPane2.setViewportView(lstWords);

        btnClrWords.setText("Clear");
        btnClrWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClrWordsActionPerformed(evt);
            }
        });

        btnRmvWords.setText("Remove Selected");
        btnRmvWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRmvWordsActionPerformed(evt);
            }
        });

        txtAddWord.setText("");

        btnAddWords.setText("Add to list");
        btnAddWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWordsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnRmvWords, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClrWords, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAddWord, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddWords)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRmvWords)
                    .addComponent(btnClrWords))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddWords)
                    .addComponent(txtAddWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Host Black List"));

        jScrollPane1.setViewportView(lstHosts);

        btnRmvSelHost.setText("Remove Selected");
        btnRmvSelHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRmvSelHostActionPerformed(evt);
            }
        });

        btnClrHost.setText("Clear");
        btnClrHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClrHostActionPerformed(evt);
            }
        });

        btnAddHost.setText("Add to list");
        btnAddHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHostActionPerformed(evt);
            }
        });

        txtAddHost.setText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRmvSelHost, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClrHost, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtAddHost, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddHost)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRmvSelHost)
                    .addComponent(btnClrHost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddHost)
                    .addComponent(txtAddHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void btnStartStopActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(ps!=null){
            ps.stopProxy();
            ps.stop();
        }
        int port=Integer.parseInt(txtPortNumber.getText());
        ps=new Main(port);
        ps.start();
        Status.setText("Running");
    }
    
    private void btnAddHostActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(!"".equals(txtAddHost.getText().trim())){
            blHosts.add(txtAddHost.getText().toLowerCase());
            updateHostList();
        }
    }

    private void btnAddWordsActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(!"".equals(txtAddWord.getText().trim())){
            blWords.add(txtAddWord.getText().toLowerCase());
            updateWordList();
        }
    }

    private void btnRmvSelHostActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int[] selIs=lstHosts.getSelectedIndices();
        int n=0;
        for(int i:selIs){
            blHosts.removeElementAt(i-n);
            n++;
        }
        updateHostList();
    }

    private void btnClrHostActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        blHosts.clear();
        updateHostList();
    }

    private void btnRmvWordsActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int[] selIs=lstWords.getSelectedIndices();
        int n=0;
        for(int i:selIs){
            blWords.removeElementAt(i-n);
            n++;
        }
        updateWordList();
    }

    private void btnClrWordsActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        blWords.clear();
        updateWordList();
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel Status;
    private javax.swing.JButton btnAddHost;
    private javax.swing.JButton btnAddWords;
    private javax.swing.JButton btnClrHost;
    private javax.swing.JButton btnClrWords;
    private javax.swing.JButton btnRmvSelHost;
    private javax.swing.JButton btnRmvWords;
    private javax.swing.JButton btnStartStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstHosts;
    private javax.swing.JList lstWords;
    private javax.swing.JTextField txtAddHost;
    private javax.swing.JTextField txtAddWord;
    private javax.swing.JTextField txtPortNumber;
    // End of variables declaration

    private Vector<String> blHosts=new Vector<String>(100);
    private Vector<String> blWords=new Vector<String>(100);
    private Main ps;

    private void updateHostList() {
        lstHosts.setListData(blHosts);
        MyThread.blackList.clear();
        for(String s: blHosts){
            MyThread.blackList.put(s,"");
        }
        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("blh.htb"));
            oos.writeObject(MyThread.blackList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void updateWordList() {
        lstWords.setListData(blWords);
        MyThread.bannedWords.clear();
        for(String s: blWords){
            MyThread.bannedWords.put(s,"");
        }
        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("blw.htb"));
            oos.writeObject(MyThread.bannedWords);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
