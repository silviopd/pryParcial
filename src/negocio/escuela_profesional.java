
package negocio;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JComboBox;
public class escuela_profesional extends Conexion{

    private int codigo_facultad;
    private int codigo_escuela_profesional;
    private String nombre;
    
    public static ArrayList<escuela_profesional> listaEscuelaProfesional= new ArrayList<escuela_profesional>();

    public int getCodigo_facultad() {
        return codigo_facultad;
    }

    public void setCodigo_facultad(int codigo_facultad) {
        this.codigo_facultad = codigo_facultad;
    }

    public int getCodigo_escuela_profesional() {
        return codigo_escuela_profesional;
    }

    public void setCodigo_escuela_profesional(int codigo_escuela_profesional) {
        this.codigo_escuela_profesional = codigo_escuela_profesional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    private void cargarLista(int codigoFacultad) throws Exception {
        String sql = "select * from escuela_profesional where codigo_facultad=? order by 2";
        PreparedStatement sentencia = this.abrirConexion().prepareStatement(sql);
        sentencia.setInt(1, codigoFacultad);
        
        ResultSet resultado = this.ejecutarSQLSelectSP(sentencia);
        
        listaEscuelaProfesional.clear();
        
        while (resultado.next()) {
            escuela_profesional obj = new escuela_profesional();
            obj.setCodigo_facultad(resultado.getInt("codigo_facultad"));
            obj.setCodigo_escuela_profesional(resultado.getInt("codigo_escuela_profesional"));
            obj.setNombre(resultado.getString("nombre"));
            
            listaEscuelaProfesional.add(obj);
        }        
    }
    
    public void cargarCombo(JComboBox objCombo,int codigoFacultad) throws Exception{
        cargarLista(codigoFacultad);
        objCombo.removeAllItems();
        
        for (escuela_profesional escProf : listaEscuelaProfesional) {
            objCombo.addItem(escProf.getNombre());
        }
    }
    
}
