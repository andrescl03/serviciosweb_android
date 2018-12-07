package r.andres.serviciosweb_android.entidades;

public class encuestaBean {

    private int id;
    private String identificador;
    private String des;
    private usuarioBean objUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public usuarioBean getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(usuarioBean objUsuario) {
        this.objUsuario = objUsuario;
    }

}
