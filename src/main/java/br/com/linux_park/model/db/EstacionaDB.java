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
    private Integer tolerancia;
    private Float preco_total;
    private Date data_entrada;
    private Date data_saida;
    
//        private Long id;
//    private Veiculo veiculo;
//    private Float valorHora;
//    private Integer tolerancia;
//    private Float valorTotal;
//    private Date dataEntrada;
//    private Date dataSaida;

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

    public Integer getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(Integer tolerancia) {
        this.tolerancia = tolerancia;
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
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.id_veiculo);
        hash = 43 * hash + Objects.hashCode(this.valor_un);
        hash = 43 * hash + Objects.hashCode(this.preco_total);
        hash = 43 * hash + Objects.hashCode(this.tolerancia);
        hash = 43 * hash + Objects.hashCode(this.data_entrada);
        hash = 43 * hash + Objects.hashCode(this.data_saida);
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
        if (!Objects.equals(this.valor_un, other.valor_un)) {
            return false;
        }
        if (!Objects.equals(this.preco_total, other.preco_total)) {
            return false;
        }
        if (!Objects.equals(this.tolerancia, other.tolerancia)) {
            return false;
        }
        if (!Objects.equals(this.data_entrada, other.data_entrada)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstacionaDB{" + "id=" + id + ", id_veiculo=" + id_veiculo + ", valor_un=" + valor_un + ", preco_total=" + preco_total + ", tolerancia=" + tolerancia + ", data_entrada=" + data_entrada + ", data_saida=" + data_saida + '}';
    }

}
