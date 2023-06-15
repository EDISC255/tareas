
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

/**
 *
 * @author eduardo
 */
public class DialogTarea extends javax.swing.JDialog {

    /**
     * Creates new form DialogTarea
     */
    private int status;
    public DialogTarea(java.awt.Frame parent, boolean modal, String id) {
        super(parent, modal);
        initComponents();
        String titulo="", descripcion="",descStatus;
        con = new Conexion();
        setLocationRelativeTo(null);
        try {
            con.setRS(con.getCo(), "select * from tarea where id="+id);
            titulo=con.getRs().getString("titulo");
            descripcion=con.getRs().getString("descripcion");
            status=con.getRs().getInt("status");
        } catch (Exception e) {
        }
        lblId.setText("ID: "+id);
        lblTitulo.setText("TITULO: "+titulo);
        lblDescripcion.setText("DESCRIPCION: "+descripcion);
        switch (status) {
            case 0:
                btnStatus.setText("sin iniciar");
            break;
            case 1:
                btnStatus.setText("en proceso");
            break;
            case 2:
                btnStatus.setEnabled(false);
                btnStatus.setText("terminado");
            break;
            //default:
            //    throw new AssertionError();
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

        lblId = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        btnStatus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblId.setText("ID");

        lblTitulo.setText("TITULO");

        lblDescripcion.setText("DESCRIPCION");

        btnStatus.setText("STATUS");
        btnStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblId)
                            .addComponent(lblTitulo)
                            .addComponent(lblDescripcion))
                        .addGap(0, 309, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusActionPerformed
        String sql="";
        try{
            switch (status) {
                case 0:
                    sql="UPDATE tarea SET  status=1 WHERE id ="+lblId.getText();
                break;
                case 1:
                    sql="UPDATE tarea SET  status=2 WHERE id ="+lblId.getText();
                    btnStatus.setEnabled(false);
                break;
            }
        con.setPs(con.getCo(), sql);
        con.getPs().executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        };
    }//GEN-LAST:event_btnStatusActionPerformed

    private Conexion con;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStatus;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}