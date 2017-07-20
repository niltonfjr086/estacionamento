/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linux_park.model.bean;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class Estaciona {
//    private Long id;
//    private Long id_veiculo;
//    private Float valor_un;
//    private Float preco_total;
//    private Date data_entrada;
//    private Date data_saida;

    private Long id;
    private Veiculo veiculo;
    private Float valorHora;
    private Integer tolerancia;
    private Float valorTotal;
    private Date dataEntrada;
    private Date dataSaida;

    public Estaciona() {

    }

    public Estaciona(Veiculo veiculo, Integer tolerancia) {
        this.veiculo = veiculo;
        this.valorHora = veiculo.getModelo().getTipoVeiculo().getTipoVaga().getPreco_unitario();
        this.tolerancia = tolerancia;

    }

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
        this.valorHora = veiculo.getModelo().getTipoVeiculo().getTipoVaga().getPreco_unitario();
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Float getValorHora() {
        return valorHora;
    }

    public void setValorHora(Float valorHora) {
        this.valorHora = valorHora;
    }

    public Integer getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(Integer tolerancia) {
        this.tolerancia = tolerancia;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.veiculo);
        hash = 89 * hash + Objects.hashCode(this.dataEntrada);
        hash = 89 * hash + Objects.hashCode(this.valorHora);
        hash = 89 * hash + Objects.hashCode(this.dataSaida);
        hash = 89 * hash + Objects.hashCode(this.valorTotal);
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
        if (!Objects.equals(this.dataEntrada, other.dataEntrada)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "Estaciona{" + "id=" + id + ", veiculo=" + veiculo + ", valorHora=" + valorHora + ", tolerancia=" + tolerancia + ", valorTotal=" + valorTotal + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + '}';
        return veiculo.toString() +"\n"
                + "Valor Total :";
    }

}
