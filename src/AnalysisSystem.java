
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.*;

/**
 *
 * @author Mohamed Almukhtar
 */
public class AnalysisSystem extends javax.swing.JFrame {

    int errorToken = -1;

    /**
     * Creates new form AnalysisSystem
     */
    public AnalysisSystem() {
        
        super("Syntax Analyzer");
        
        initComponents();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        start.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                output.setText(null);
                terminalSet.setText(null);
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AnalysisSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(input.getText());
                
                try{
                    syntaxAnalyzer.ifStatement();

                    appendToPane(output,"\n\nNo Errors found in statement,\nstatement was parsed successfully.\n\n",new Color(0,153,0));

                    while(syntaxAnalyzer.getLookAhead()<syntaxAnalyzer.getSize()){

                        appendToPane(output,"\n\n-------------------------Next-Statement-----------------------\n\n",Color.black);

                        syntaxAnalyzer.ifStatement();

                        appendToPane(output,"\n\nNo Errors found in statement,\nstatement was parsed successfully.\n\n",new Color(0,153,0));

                    }
                }catch(ErrorException error){
                    
                    
                    try {
                        
                        appendToPane(output,error.toString(),new Color(255, 51, 51));
                        errorToken = error.errorToken;
                        
                    } catch (BadLocationException ex) {
                        Logger.getLogger(AnalysisSystem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                    
                    
                } catch (BadLocationException ex) {
                    Logger.getLogger(AnalysisSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                int count = 0;
                
                for(int i=0;i<syntaxAnalyzer.tokens.size();i++){
                    
                    
                    try {
                        
                        
                        if(i==errorToken)
                            appendToPane(terminalSet,++count + "- " + syntaxAnalyzer.tokens.get(i), new Color(255, 51, 51));
                        else
                            appendToPane(terminalSet,++count + "- " + syntaxAnalyzer.tokens.get(i),Color.black);
                        
                        appendToPane(terminalSet,"\n",Color.black);
                        
                        
                        
                    } catch (BadLocationException ex) {
                        Logger.getLogger(AnalysisSystem.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                
                errorToken = -1;
                
            }
 
        });
        
        clear.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                input.setText(null);
                
            }
            
        });
    }
    
    public static void appendToPane(JTextPane tp, String msg, Color c) throws BadLocationException
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
        tp.getDocument().insertString(len, msg, aset);
    }

    
//    public static void appendToPane(JTextPane pane,String str,Color color) throws BadLocationException {
//        StyledDocument document = (StyledDocument) pane.getDocument();
//        document.insertString(document.getLength(), str, (AttributeSet) color);
//
//    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        input = new javax.swing.JTextArea();
        clear = new javax.swing.JButton();
        start = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        terminalSet = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1780, 850));
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        input.setColumns(20);
        input.setFont(new java.awt.Font("Monospaced", 0, 20)); // NOI18N
        input.setRows(5);
        input.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 20))); // NOI18N
        jScrollPane1.setViewportView(input);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 440));

        clear.setBackground(java.awt.SystemColor.inactiveCaption);
        clear.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        clear.setText("Clear");
        clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 690, 240, 70));

        start.setBackground(new java.awt.Color(166, 190, 217));
        start.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        start.setText("Analyze");
        start.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 570, 240, 70));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel1.setText("Example : if ( id <= num ) then statement ; else statement ;");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel2.setText("Please Input terminals of an If statement separated by white space.");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        output.setEditable(false);
        output.setBackground(java.awt.SystemColor.inactiveCaption);
        output.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OUTPUT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 20))); // NOI18N
        output.setFont(new java.awt.Font("Monospaced", 0, 20)); // NOI18N
        jScrollPane4.setViewportView(output);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 790, 850));

        terminalSet.setEditable(false);
        terminalSet.setBackground(java.awt.SystemColor.inactiveCaption);
        terminalSet.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Terminal set", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        terminalSet.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jScrollPane3.setViewportView(terminalSet);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 260, 850));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AnalysisSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnalysisSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnalysisSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnalysisSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnalysisSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JTextArea input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    static javax.swing.JTextPane output;
    private javax.swing.JButton start;
    private javax.swing.JTextPane terminalSet;
    // End of variables declaration//GEN-END:variables
}
