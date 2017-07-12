/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linux_park.model.bean;

import br.com.linux_park.model.bean.Cor;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class Estaciona {

    private Long id;
    private Veiculo veiculo;
    private Cor cor;
    private TipoVaga tipo_vaga;
    private Date data_entrada;
    private Date data_saida;
    private Float preco_total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public TipoVaga getTipo_vaga() {
        return tipo_vaga;
    }

    public void setTipo_vaga(TipoVaga tipo_vaga) {
        this.tipo_vaga = tipo_vaga;
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
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.veiculo);
        hash = 47 * hash + Objects.hashCode(this.cor);
        hash = 47 * hash + Objects.hashCode(this.tipo_vaga);
        hash = 47 * hash + Objects.hashCode(this.data_entrada);
        hash = 47 * hash + Objects.hashCode(this.data_saida);
        hash = 47 * hash + Objects.hashCode(this.preco_total);
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
        final Estaciona other = (Estaciona) obj;
        if (!Objects.equals(this.veiculo, other.veiculo)) {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        if (!Objects.equals(this.tipo_vaga, other.tipo_vaga)) {
            return false;
        }
        if (!Objects.equals(this.data_entrada, other.data_entrada)) {
            return false;
        }
        if (!Objects.equals(this.data_saida, other.data_saida)) {
            return false;
        }
        if (!Objects.equals(this.preco_total, other.preco_total)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estaciona{" + "id=" + id + ", veiculo=" + veiculo + ", cor=" + cor + ", tipo_vaga=" + tipo_vaga + ", data_entrada=" + data_entrada + ", data_saida=" + data_saida + ", preco_total=" + preco_total + '}';
    }
    
    



}
