/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author USER
 */
public class universidad extends Conexion{
    
    private int codigo_universidad;
    private String nombre;
    
    
    public static ArrayList<universidad> listaUniversidad = new ArrayList<universidad>();

    public int getCodigo_universidad() {
        return codigo_universidad;
    }

    public void setCodigo_universidad(int codigo_universidad) {
        this.codigo_universidad = codigo_universidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    private void cargarLista() throws Exception {
        String sql = "select codigo_universidad,nombre from universidad order by 2";
        ResultSet resultado = this.ejecutarSQLSelect(sql);

        listaUniversidad.clear();

        while (resultado.next()) {
            universidad obj = new universidad();
            obj.setCodigo_universidad(resultado.getInt("codigo_universidad"));
            obj.setNombre(resultado.getString("nombre"));
            listaUniversidad.add(obj);
        }
    }
    
    public void cargarCombo(JComboBox objCombo) throws Exception {
        cargarLista();
        objCombo.removeAllItems();
        
        for (universidad uni : listaUniversidad) {
            objCombo.addItem(uni.getNombre());
        }
    }
    
}
