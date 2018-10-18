
package negocio;

import datos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JComboBox;
public class facultad extends Conexion{

    private int codigo_universidad;
    private int codigo_facultad;
    private String nombre;
    
    public static ArrayList<facultad> listaFacultad= new ArrayList<facultad>();

    public int getCodigo_universidad() {
        return codigo_universidad;
    }

    public void setCodigo_universidad(int codigo_universidad) {
        this.codigo_universidad = codigo_universidad;
    }

    public int getCodigo_facultad() {
        return codigo_facultad;
    }

    public void setCodigo_facultad(int codigo_facultad) {
        this.codigo_facultad = codigo_facultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    private void cargarLista(int codigoUniversidad) throws Exception {
        String sql = "select * from facultad where codigo_universidad=? order by 2";
        PreparedStatement sentencia = this.abrirConexion().prepareStatement(sql);
        sentencia.setInt(1, codigoUniversidad);
        
        ResultSet resultado = this.ejecutarSQLSelectSP(sentencia);
        
        listaFacultad.clear();
        
        while (resultado.next()) {
            facultad obj = new facultad();
            obj.setCodigo_universidad(resultado.getInt("codigo_universidad"));
            obj.setCodigo_facultad(resultado.getInt("codigo_facultad"));
            obj.setNombre(resultado.getString("nombre"));
            
            listaFacultad.add(obj);
        }        
    }
    
    public void cargarCombo(JComboBox objCombo,int codigoFacultad) throws Exception{
        cargarLista(codigoFacultad);
        objCombo.removeAllItems();
        
        for (facultad facul : listaFacultad) {
            objCombo.addItem(facul.getNombre());
        }
    }
    
}
