package modelo;

import java.util.Objects;

public class Bebida {
    private Integer id;
    private String nombre;
    private String proveedor;
    private Double precio;

    public Bebida(Integer id, String nombre, String proveedor, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.precio = precio;
    }

    public Bebida() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.proveedor);
        hash = 23 * hash + Objects.hashCode(this.precio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bebida other = (Bebida) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.proveedor, other.proveedor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Liquido{" + "id=" + id + ", nombre=" + nombre + ", proveedor=" + proveedor + ", precio=" + precio + '}';
    }
    
}