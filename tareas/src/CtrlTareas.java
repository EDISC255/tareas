
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author eduardo
 */
public class CtrlTareas {

    private Conexion con;
    private DefaultListModel dlm;

    public void insertar(JTextField txtTitulo, JTextArea txtDescripcion, JDateChooser txtFecha, JList lstTareas) {
        String fecha = txtFecha.getDate().toString(),
                titulo = txtTitulo.getText(),
                descripcion = txtDescripcion.getText();
        int status = 0;
        try {
            con.setPs(con.getCo(), "insert into tarea(titulo, descripcion, fecha, status) values(?,?,?,?)");
            con.getPs().setString(1, titulo);
            con.getPs().setString(2, descripcion);
            con.getPs().setString(3, fecha);
            con.getPs().setInt(4, status);
            int i = con.getPs().executeUpdate();
            if (i != 0) {
                javax.swing.JOptionPane.showInternalMessageDialog(null, "Tarea creada con exito");
                listarTareas(lstTareas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void listarTareas(JList lstTareas) {
        dlm = new DefaultListModel();
        lstTareas.setModel(dlm);

        try {
            con.setRS(con.getCo(), "select id, titulo from tarea");
            while (con.getRs().next()) {
                dlm.addElement(con.getRs().getString("id") + "--" + con.getRs().getString("titulo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filtrarPorFecha(JDateChooser txtFecha) {
        dlm.clear();
        try {
            con.setRS(con.getCo(), "select id, titulo from tarea where fecha = '" + txtFecha.getDate().toString() + "'");
            while (con.getRs().next()) {
                dlm.addElement(con.getRs().getString("id") + "--" + con.getRs().getString("titulo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void datosTarea(String id, JLabel lblId, JLabel lblTitulo, JLabel lblDescripcion, JButton btnStatus, int status) {
        String titulo = "", descripcion = "", descStatus;
        status = 0;
        try {
            con.setRS(con.getCo(), "select * from tarea where id=" + id);
            titulo = con.getRs().getString("titulo");
            descripcion = con.getRs().getString("descripcion");
            status = con.getRs().getInt("status");
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblId.setText("ID: " + id);
        lblTitulo.setText("TITULO: " + titulo);
        lblDescripcion.setText("DESCRIPCION: " + descripcion);
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
    public void actualizarStatus(int status,JLabel lblId, JButton btnStatus){
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
    }
}
