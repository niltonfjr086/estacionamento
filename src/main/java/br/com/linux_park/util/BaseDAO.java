package br.com.linux_park.util;

import java.util.List;

public interface BaseDAO<O extends Object, Z extends Object, I extends Object, T extends Object> {

    public final String[] BANCO = {"linuxpark"};

    public abstract I inserir(Z objeto);

    public abstract Boolean alterar(Z objeto);

    public abstract Boolean excluir(I id);

    public abstract List<Z> listarTodos();

    public abstract List<Z> listarAoNomeParcial(T... key);

    public abstract Z get(T... key);

    public abstract I getId(T... key);

    public abstract Z getPorId(I id);
    
    public abstract Boolean existente(T... key);
    
    public abstract O toDB(Z objeto);
    
    public abstract Z fromDB(O objeto);

}
