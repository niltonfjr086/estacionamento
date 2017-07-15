package br.com.linux_park.model.db;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author main
 */
public class ModeloDB {
    
    private Long id;
    private Long id_tipoveiculo;
    private Long id_marca;
    private String descricao;
    private Date data_inclusao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_tipoveiculo() {
        return id_tipoveiculo;
    }

    public void setId_tipoveiculo(Long id_tipoveiculo) {
        this.id_tipoveiculo = id_tipoveiculo;
    }

    public Long getId_marca() {
        return id_marca;
    }

    public void setId_marca(Long id_marca) {
        this.id_marca = id_marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.id_tipoveiculo);
        hash = 47 * hash + Objects.hashCode(this.id_marca);
        hash = 47 * hash + Objects.hashCode(this.descricao);
        hash = 47 * hash + Objects.hashCode(this.data_inclusao);
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
        final ModeloDB other = (ModeloDB) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.id_tipoveiculo, other.id_tipoveiculo)) {
            return false;
        }
        if (!Objects.equals(this.id_marca, other.id_marca)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ModeloDB{" + "id=" + id + ", id_tipoveiculo=" + id_tipoveiculo + ", id_marca=" + id_marca + ", descricao=" + descricao + ", data_inclusao=" + data_inclusao + '}';
    }

}
