package br.com.linux_park.model.db;

import java.util.Date;
import java.util.Objects;

public class VeiculoDB {

    private Long id;
    private String placa;
    private Long id_modelo;
    private Long id_cor;
    private Date data_inclusao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getId_cor() {
        return id_cor;
    }

    public void setId_cor(Long id_cor) {
        this.id_cor = id_cor;
    }

    public Long getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(Long id_modelo) {
        this.id_modelo = id_modelo;
    }

    public Date getData_inclusao() {
        return data_inclusao;
    }

    public void setData_inclusao(Date data_inclusao) {
        this.data_inclusao = data_inclusao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.placa);
        hash = 59 * hash + Objects.hashCode(this.id_cor);
        hash = 59 * hash + Objects.hashCode(this.id_modelo);
        hash = 59 * hash + Objects.hashCode(this.data_inclusao);
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
        final VeiculoDB other = (VeiculoDB) obj;
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.id_cor, other.id_cor)) {
            return false;
        }
        if (!Objects.equals(this.id_modelo, other.id_modelo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VeiculoDB{" + "id=" + id + ", placa=" + placa + ", id_cor=" + id_cor + ", id_modelo=" + id_modelo + ", data_inclusao=" + data_inclusao + '}';
    }

}
