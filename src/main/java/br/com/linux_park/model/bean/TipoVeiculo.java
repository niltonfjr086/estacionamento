package br.com.linux_park.model.bean;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author main
 */
public class TipoVeiculo {

    private Long id;
    private String descricao;
    private TipoVaga tipoVaga;
    private Date data_inclusao;

    public TipoVeiculo() {
    }

    public TipoVeiculo(String descricao, TipoVaga tipoVaga) {
        this.descricao = descricao;
        this.tipoVaga = tipoVaga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoVaga getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(TipoVaga vaga) {
        this.tipoVaga = vaga;
    }

    public Date getData_inclusao() {
        return data_inclusao;
    }

    public void setData_inclusao(Date data_inclusao) {
        this.data_inclusao = data_inclusao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.descricao);
        hash = 29 * hash + Objects.hashCode(this.tipoVaga);
        hash = 29 * hash + Objects.hashCode(this.data_inclusao);
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
        final TipoVeiculo other = (TipoVeiculo) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "TipoVeiculo{" + "id=" + id + ", descricao=" + descricao + ", tipoVaga=" + tipoVaga + ", data_inclusao=" + data_inclusao + '}';
        return descricao;
    }

}
