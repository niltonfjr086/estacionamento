package br.com.linux_park.model.bean;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author main
 */
public class Modelo {

    private Long id;
    private TipoVeiculo tipoVeiculo;
    private Marca marca;
    private String descricao;
    private Date data_inclusao;

    public Modelo() {
    }

    public Modelo(TipoVeiculo tipoVeiculo, Marca marca, String descricao) {
        this.tipoVeiculo = tipoVeiculo;
        this.marca = marca;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
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
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.tipoVeiculo);
        hash = 67 * hash + Objects.hashCode(this.marca);
        hash = 67 * hash + Objects.hashCode(this.descricao);
        hash = 67 * hash + Objects.hashCode(this.data_inclusao);
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
        final Modelo other = (Modelo) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.tipoVeiculo, other.tipoVeiculo)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "Modelo{" + "id=" + id + ", tipoVeiculo=" + tipoVeiculo + ", marca=" + marca + ", descricao=" + descricao + ", data_inclusao=" + data_inclusao + '}';
        return descricao;
    }

}
