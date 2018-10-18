package negocio;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author USER
 */
public class estudiante extends Conexion {

    private int codigo_estudiante;
    private String apellidos;
    private String nombres;
    private String dni;
    private String sexo;
    private String direccion;
    private String telefono_fijo;
    private String telefono_movil;
    private String email;
    private int codigo_escuela_profesional;
    private String modalidad;
    private String tiene_beca;
    private String codigo_departamento;
    private String codigo_provincia;
    private String codigo_distrito;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono_fijo() {
        return telefono_fijo;
    }

    public void setTelefono_fijo(String telefono_fijo) {
        this.telefono_fijo = telefono_fijo;
    }

    public String getTelefono_movil() {
        return telefono_movil;
    }

    public void setTelefono_movil(String telefono_movil) {
        this.telefono_movil = telefono_movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo_departamento() {
        return codigo_departamento;
    }

    public void setCodigo_departamento(String codigo_departamento) {
        this.codigo_departamento = codigo_departamento;
    }

    public String getCodigo_provincia() {
        return codigo_provincia;
    }

    public void setCodigo_provincia(String codigo_provincia) {
        this.codigo_provincia = codigo_provincia;
    }

    public String getCodigo_distrito() {
        return codigo_distrito;
    }

    public void setCodigo_distrito(String codigo_distrito) {
        this.codigo_distrito = codigo_distrito;
    }

    public int getCodigo_estudiante() {
        return codigo_estudiante;
    }

    public void setCodigo_estudiante(int codigo_estudiante) {
        this.codigo_estudiante = codigo_estudiante;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCodigo_escuela_profesional() {
        return codigo_escuela_profesional;
    }

    public void setCodigo_escuela_profesional(int codigo_escuela_profesional) {
        this.codigo_escuela_profesional = codigo_escuela_profesional;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getTiene_beca() {
        return tiene_beca;
    }

    public void setTiene_beca(String tiene_beca) {
        this.tiene_beca = tiene_beca;
    }

    public ResultSet listar() throws Exception {
        String sql = "SELECT   estudiante_universitario.codigo_estudiante,   estudiante_universitario.apellidos,   estudiante_universitario.nombres,   estudiante_universitario.dni,   estudiante_universitario.sexo,   estudiante_universitario.direccion,   estudiante_universitario.telefono_fijo,   estudiante_universitario.telefono_movil,   estudiante_universitario.email,   escuela_profesional.nombre as escuela_profesional,   facultad.nombre as facultad,   universidad.nombre as universidad,   estudiante_universitario.modalidad,   estudiante_universitario.tiene_beca,   distrito.nombre as distrito,   provincia.nombre as provincia,   departamento.nombre as departamento FROM   public.estudiante_universitario,   public.escuela_profesional,   public.facultad,   public.universidad,   public.distrito,   public.provincia,   public.departamento WHERE   estudiante_universitario.codigo_provincia = distrito.codigo_provincia AND  estudiante_universitario.codigo_distrito = distrito.codigo_distrito AND  escuela_profesional.codigo_escuela_profesional = estudiante_universitario.codigo_escuela_profesional AND  facultad.codigo_facultad = escuela_profesional.codigo_facultad AND  universidad.codigo_universidad = facultad.codigo_universidad AND  distrito.codigo_departamento = provincia.codigo_departamento AND  distrito.codigo_departamento = estudiante_universitario.codigo_departamento AND  provincia.codigo_departamento = departamento.codigo_departamento AND  provincia.codigo_provincia = distrito.codigo_provincia;";
        PreparedStatement sentencia = abrirConexion().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultado = ejecutarSQLSelectSP(sentencia);
        return resultado;
    }

    public String[] obtenerCamposBusqueda() {
        String campos[] = {"codigo_estudiante", "apellidos", "nombres", "dni", "sexo", "direccion", "telefono_fijo", "telefono_movil", "email", "escuela_profesional", "facultad", "universidad", "modalidad", "tiene_beca", "distrito", "provincia", "departamento"};
        return campos;
    }

    public boolean agregar() throws Exception {
        String sql = "select * from f_generar_correlativo('estudianteu') as numero";
        ResultSet resultado = ejecutarSQLSelect(sql);

        if (resultado.next()) {
            int nuevoCodigo = resultado.getInt("numero");
            setCodigo_estudiante(nuevoCodigo);

            Connection transaccion = abrirConexion();
            transaccion.setAutoCommit(false);

            sql = "INSERT INTO estudiante_universitario(codigo_estudiante, apellidos, nombres, dni, sexo, direccion, telefono_fijo, telefono_movil, email, codigo_escuela_profesional, modalidad, tiene_beca, codigo_departamento, codigo_provincia, codigo_distrito)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?);";
            PreparedStatement sentencia1 = transaccion.prepareStatement(sql);
            sentencia1.setInt(1, this.getCodigo_estudiante());
            sentencia1.setString(2, this.getApellidos());
            sentencia1.setString(3, this.getNombres());
            sentencia1.setString(4, this.getDni());
            sentencia1.setString(5, this.getSexo());
            sentencia1.setString(6, this.getDireccion());
            sentencia1.setString(7, this.getTelefono_fijo());
            sentencia1.setString(8, this.getTelefono_movil());
            sentencia1.setString(9, this.getEmail());
            sentencia1.setInt(10, this.getCodigo_escuela_profesional());
            sentencia1.setString(11, this.getModalidad());
            sentencia1.setString(12, this.getTiene_beca());
            sentencia1.setString(13, this.getCodigo_departamento());
            sentencia1.setString(14, this.getCodigo_provincia());
            sentencia1.setString(15, this.getCodigo_distrito());
            this.ejecutarSQL(sentencia1, transaccion);

            sql = "UPDATE correlativo SET numero=numero+1 WHERE tabla=?";
            PreparedStatement sentencia2 = transaccion.prepareStatement(sql);
            sentencia2.setString(1, "estudianteu");
            this.ejecutarSQL(sentencia2, transaccion);

            transaccion.commit();
            transaccion.close();
        } else {
            throw new Exception("No existe un correlativo registrado para la tabla estudiante");
        }
        return true;
    }

    
    public boolean editar() throws Exception {
        Connection transaccion = abrirConexion();
        transaccion.setAutoCommit(false);

        String sql = "UPDATE estudiante_universitario   SET apellidos=?, nombres=?, dni=?, sexo=?, direccion=?, telefono_fijo=?, telefono_movil=?, email=?, codigo_escuela_profesional=?, modalidad=?, tiene_beca=?, codigo_departamento=?, codigo_provincia=?, codigo_distrito=? WHERE codigo_estudiante=?;";

        PreparedStatement sentencia = transaccion.prepareStatement(sql);

        sentencia.setString(1, this.getApellidos());
        sentencia.setString(2, this.getNombres());
        sentencia.setString(3, this.getDni());
        sentencia.setString(4, this.getSexo());
        sentencia.setString(5, this.getDireccion());
        sentencia.setString(6, this.getTelefono_fijo());
        sentencia.setString(7, this.getTelefono_movil());
        sentencia.setString(8, this.getEmail());        
        sentencia.setInt(9, this.getCodigo_escuela_profesional());
        sentencia.setString(10, this.getModalidad());
        sentencia.setString(11, this.getTiene_beca());
        sentencia.setString(12, this.getCodigo_departamento());
        sentencia.setString(13, this.getCodigo_provincia());
        sentencia.setString(14, this.getCodigo_distrito());
        sentencia.setInt(15, this.getCodigo_estudiante());

        this.ejecutarSQL(sentencia, transaccion);
        transaccion.commit();
        transaccion.close();

        return true;
    }

    public boolean eliminar() throws Exception {
        Connection transaccion = abrirConexion();
        transaccion.setAutoCommit(false);

        String sql = "delete from estudiante_universitario where codigo_estudiante=?";

        PreparedStatement sentencia = transaccion.prepareStatement(sql);
        sentencia.setInt(1, getCodigo_estudiante());

        this.ejecutarSQL(sentencia, transaccion);
        transaccion.commit();
        transaccion.close();

        return true;
    }

    public ResultSet leerDatos(int cod_cli) throws Exception {
        String sql = "SELECT   estudiante_universitario.codigo_estudiante,   estudiante_universitario.apellidos,   estudiante_universitario.nombres,   estudiante_universitario.dni,   estudiante_universitario.sexo,   estudiante_universitario.direccion,   estudiante_universitario.telefono_fijo,   estudiante_universitario.telefono_movil,   estudiante_universitario.email,   escuela_profesional.nombre as escuela_profesional,   facultad.nombre as facultad,   universidad.nombre as universidad,   estudiante_universitario.modalidad,   estudiante_universitario.tiene_beca,   distrito.nombre as distrito,   provincia.nombre as provincia,   departamento.nombre as departamento FROM   public.estudiante_universitario,   public.escuela_profesional,   public.facultad,   public.universidad,   public.distrito,   public.provincia,   public.departamento WHERE   estudiante_universitario.codigo_provincia = distrito.codigo_provincia AND  estudiante_universitario.codigo_distrito = distrito.codigo_distrito AND  escuela_profesional.codigo_escuela_profesional = estudiante_universitario.codigo_escuela_profesional AND  facultad.codigo_facultad = escuela_profesional.codigo_facultad AND  universidad.codigo_universidad = facultad.codigo_universidad AND  distrito.codigo_departamento = provincia.codigo_departamento AND  distrito.codigo_departamento = estudiante_universitario.codigo_departamento AND  provincia.codigo_departamento = departamento.codigo_departamento AND  provincia.codigo_provincia = distrito.codigo_provincia and codigo_estudiante=?";
        PreparedStatement sentencia = abrirConexion().prepareStatement(sql);
        sentencia.setInt(1, cod_cli);
        return ejecutarSQLSelectSP(sentencia);
    }
    
}
