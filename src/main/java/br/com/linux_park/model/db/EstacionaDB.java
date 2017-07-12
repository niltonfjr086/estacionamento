/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linux_park.model.db;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class EstacionaDB {

    private Long id;
    private Long id_veiculo;
    private Long id_cor;
    private Long id_tipo_vaga;
    private Date data_entrada;
    private Date data_saida;
    private Float preco_total;

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

    public Long getId_cor() {
        return id_cor;
    }

    public void setId_cor(Long id_cor) {
        this.id_cor = id_cor;
    }

    public Long getId_tipo_vaga() {
        return id_tipo_vaga;
    }

    public void setId_tipo_vaga(Long id_tipo_vaga) {
        this.id_tipo_vaga = id_tipo_vaga;
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

    public Float getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(Float preco_total) {
        this.preco_total = preco_total;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.id_veiculo);
        hash = 59 * hash + Objects.hashCode(this.id_cor);
        hash = 59 * hash + Objects.hashCode(this.id_tipo_vaga);
        hash = 59 * hash + Objects.hashCode(this.data_entrada);
        hash = 59 * hash + Objects.hashCode(this.data_saida);
        hash = 59 * hash + Objects.hashCode(this.preco_total);
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
        if (!Objects.equals(this.id_cor, other.id_cor)) {
            return false;
        }
        if (!Objects.equals(this.id_tipo_vaga, other.id_tipo_vaga)) {
            return false;
        }
        if (!Objects.equals(this.data_entrada, other.data_entrada)) {
            return false;
        }
        if (!Objects.equals(this.data_saida, other.data_saida)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstacionaDB{" + "id=" + id + ", id_veiculo=" + id_veiculo + ", id_cor=" + id_cor + ", id_tipo_vaga=" + id_tipo_vaga + ", data_entrada=" + data_entrada + ", data_saida=" + data_saida + ", preco_total=" + preco_total + '}';
    }
    
    

}
