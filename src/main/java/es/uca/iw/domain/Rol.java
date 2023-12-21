package es.uca.iw.domain;

public enum Rol {
    VENTAS("Ventas",0),
    ATENCION("Atencion",1),
    FINANZAS("Finanzas",2);

    private final String puesto;
    private final int id;

    Rol(String puesto, int id) {
        this.puesto = puesto;
        this.id = id;
    }

    public String getPuesto(){
        return puesto;
    }

    public int getId(){
        return id;
    }
}
