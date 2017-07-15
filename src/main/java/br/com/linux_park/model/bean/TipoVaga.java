package br.com.linux_park.model.bean;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class TipoVaga {

    private Long id;
    private String descricao;
    private Float preco_unitario;
    private Integer quantidade;
    private Date data_inclusao;

    public TipoVaga() {
    }

    public TipoVaga(String descricao, Float preco_unitario, Integer quantidade) {
        this.descricao = descricao;
        this.preco_unitario = preco_unitario;
        this.quantidade = quantidade;
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

    public Float getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(Float preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.descricao);
        hash = 61 * hash + Objects.hashCode(this.quantidade);
        hash = 61 * hash + Objects.hashCode(this.data_inclusao);
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
        final TipoVaga other = (TipoVaga) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "TipoVaga{" + "id=" + id + ", descricao=" + descricao + ", quantidade=" + quantidade + ", data_inclusao=" + data_inclusao + '}';
        return descricao;
    }

}
