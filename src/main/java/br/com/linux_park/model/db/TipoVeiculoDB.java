package br.com.linux_park.model.db;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author main
 */
public class TipoVeiculoDB {

    private Long id;
    private String descricao;
    private Long id_tipo_vaga;
    private Date data_inclusao;

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

    public Long getId_tipo_vaga() {
        return id_tipo_vaga;
    }

    public void setId_tipo_vaga(Long id_tipo_vaga) {
        this.id_tipo_vaga = id_tipo_vaga;
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
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.descricao);
        hash = 83 * hash + Objects.hashCode(this.id_tipo_vaga);
        hash = 83 * hash + Objects.hashCode(this.data_inclusao);
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
        final TipoVeiculoDB other = (TipoVeiculoDB) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoVeiculoDB{" + "id=" + id + ", descricao=" + descricao + ", id_tipo_vaga=" + id_tipo_vaga + ", data_inclusao=" + data_inclusao + '}';
    }

}
