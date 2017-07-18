
package br.com.linux_park.model.db;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Nilton
 */
public class EstacionaDB {
//id bigint primary key auto_increment,
//id_veiculo bigint not null,
//valor_un float not null;
//preco_total float null,
//data_entrada datetime not null,
//data_saida datetime null,
    private Long id;
    private Long id_veiculo;
    private Float valor_un;
    private Float preco_total;
    private Date data_entrada;
    private Date data_saida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Long id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public Float getValor_un() {
        return valor_un;
    }

    public void setValor_un(Float valor_un) {
        this.valor_un = valor_un;
    }

    public Float getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(Float preco_total) {
        this.preco_total = preco_total;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Date getData_saida() {
        return data_saida;
    }

    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.id_veiculo);
        hash = 59 * hash + Objects.hashCode(this.valor_un);
        hash = 59 * hash + Objects.hashCode(this.preco_total);
        hash = 59 * hash + Objects.hashCode(this.data_entrada);
        hash = 59 * hash + Objects.hashCode(this.data_saida);
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
        final EstacionaDB other = (EstacionaDB) obj;
        if (!Objects.equals(this.id_veiculo, other.id_veiculo)) {
            return false;
        }
        if (!Objects.equals(this.data_entrada, other.data_entrada)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstacionaDB{" + "id=" + id + ", id_veiculo=" + id_veiculo + ", valor_un=" + valor_un + ", preco_total=" + preco_total + ", data_entrada=" + data_entrada + ", data_saida=" + data_saida + '}';
    }

}
