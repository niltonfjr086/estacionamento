package br.com.linux_park.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nilton **classe valendo 10hrs?
 * @param <O>
 * @param <Z>
 */
public abstract class GenericDAO<O extends Object, Z extends Object> implements BaseDAO<O, Z, Long, String> {

    protected O o;
    protected Z z;
    protected String banco;
    protected String tabela;
    protected String key;
    protected String keyF;
    protected String key2;
    protected String[] CAMPOS;

    protected Connection con;

    public GenericDAO() {
        this.banco();
    }

    public GenericDAO(String banco, String tabela, String key, O o, Z z) {
        this.banco();

        this.banco = banco;
        this.tabela = tabela;
        this.key = key;
        this.o = o;
        this.z = z;
    }

    public GenericDAO(String banco, String tabela, String key, O o, Z z, String... campos) {
        this.banco();

        this.banco = banco;
        this.tabela = tabela;
        this.key = key;
        this.o = o;
        this.z = z;
        this.CAMPOS = campos;
    }

    public GenericDAO(String banco, String tabela, String key, String keyF, O o, Z z) {
        this.banco();

        this.banco = banco;
        this.tabela = tabela;
        this.key = key;
        this.keyF = keyF;
        this.o = o;
        this.z = z;
    }

    public GenericDAO(String banco, String tabela, String key, String keyF, O o, Z z, String... campos) {
        this.banco();

        this.banco = banco;
        this.tabela = tabela;
        this.key = key;
        this.keyF = keyF;
        this.o = o;
        this.z = z;
        this.CAMPOS = campos;
    }

    public GenericDAO(String banco, String tabela, String key, String keyF, String key2, O o, Z z) {
        this.banco();

        this.banco = banco;
        this.tabela = tabela;
        this.key = key;
        this.keyF = keyF;
        this.key2 = key2;
        this.o = o;
        this.z = z;
    }

    public GenericDAO(String banco, String tabela, String key, String keyF, String key2, O o, Z z, String... campos) {
        this.banco();

        this.banco = banco;
        this.tabela = tabela;
        this.key = key;
        this.keyF = keyF;
        this.key2 = key2;
        this.o = o;
        this.z = z;
        this.CAMPOS = campos;
    }

    private void banco() {
        if (con == null) {
            con = ConexaoDB.getConnection();
        }
    }

    protected void startTransaction() {
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

        }
    }

    protected void backTransaction() {
        try {
            con.rollback();
        } catch (SQLException ex1) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex1);
        }
    }

    protected void validateTransaction() {
        try {
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

        }
    }

    protected void finishTransaction() {
        try {
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

        }
    }

    public String sqlInsert() {

        StringBuilder sql = new StringBuilder("INSERT INTO " + this.banco + "." + this.tabela + " (");
        StringBuffer campos = new StringBuffer();
        StringBuffer parenteses = new StringBuffer("VALUES(");
        String virgula;

        for (int i = 1, p = 1; i < CAMPOS.length; i++) {
            virgula = (++p < CAMPOS.length) ? ", " : "";
            campos.append(CAMPOS[i]).append(virgula);

            if (virgula.equals(", ")) {
                parenteses.append("?").append(virgula);
            } else {
                parenteses.append("NOW()");
            }

        }
        campos.append(") ");
        parenteses.append(") ");

        return sql.append(campos).append(parenteses).toString();
    }

    public String sqlInsert(Field... f) {

        StringBuilder sql = new StringBuilder("INSERT INTO " + this.banco + "." + this.tabela + " (");
        StringBuffer campos = new StringBuffer();
        StringBuffer parenteses = new StringBuffer("VALUES(");
        String virgula;

        for (int i = 1, p = 1; i < f.length; i++) {
            virgula = (++p < f.length) ? ", " : "";
            campos.append(f[i].getName()).append(virgula);

            if (virgula.equals(", ")) {
                parenteses.append("?").append(virgula);
            } else {
                parenteses.append("NOW()");
            }

        }
        campos.append(") ");
        parenteses.append(") ");

        return sql.append(campos).append(parenteses).toString();
    }

    public String sqlUpdate() {

        StringBuilder sql = new StringBuilder("UPDATE " + this.banco + "." + this.tabela + " SET ");
        StringBuffer campos = new StringBuffer();
        String where = "";

        boolean virgula = false;
        for (int i = 0, p = 1; i < CAMPOS.length - 1; i++) {
            if (p == 1) {
                where = " WHERE " + CAMPOS[i] + "=? ";
            } else {
                campos.append(CAMPOS[i]).append("=?").append(virgula ? ", " : "");
            }
            virgula = (++p < CAMPOS.length - 1);
        }
        return sql.append(campos).append(where).toString();
    }

    public String sqlUpdate(Field... f) {

        StringBuilder sql = new StringBuilder("UPDATE " + this.banco + "." + this.tabela + " SET ");
        StringBuffer campos = new StringBuffer();
        String where = "";

        boolean virgula = false;
        for (int i = 0, p = 1; i < f.length - 1; i++) {
            if (p == 1) {
                where = " WHERE " + f[i].getName() + "=? ";
            } else {
                campos.append(f[i].getName()).append("=?").append(virgula ? ", " : "");
            }
            virgula = (++p < f.length - 1);
        }
        return sql.append(campos).append(where).toString();
    }

    public String sqlDelete() {

        String id;
        if (CAMPOS == null) {
            Field[] f = getDeclaredFields(o);
            id = f[0].getName();
        } else {
            id = CAMPOS[0];
        }

        String sql = "UPDATE " + this.banco + "." + tabela
                + " SET dtExclusao = NOW() "
                + " WHERE " + this.banco + "." + this.tabela + "." + "data_remocao IS NULL"
                + " AND " + this.banco + "." + this.tabela + "." + id + " = ?";

        return sql;
    }

    public String sqlDelete(Field... f) {
        String sql = "UPDATE " + this.banco + "." + tabela
                + " SET dtExclusao = NOW() "
                + " WHERE " + this.banco + "." + this.tabela + "." + "data_remocao IS NULL"
                + " AND " + this.banco + "." + this.tabela + "." + f[0] + " = ?";

        return sql;
    }

    public Field[] getDeclaredFields(Object objeto) {

        return objeto.getClass().getDeclaredFields();
    }

    @Override
    public Long inserir(Z objeto) {

        O ob = toDB(objeto);
        Field[] campos = getDeclaredFields(ob);

        String sql;
        if (CAMPOS == null) {
            sql = sqlInsert(campos);
        } else {
            sql = sqlInsert();
        }

        try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            for (int i = 1; i < campos.length - 1; i++) {
                campos[i].setAccessible(true);
                Object valor = campos[i].get(ob);
                stmt.setObject(i, valor);
            }

            if (stmt.executeUpdate() == 1) {

                ResultSet getPK = stmt.getGeneratedKeys();
                while (getPK.next()) {
                    return getPK.getLong(1);
                }
            }

        } catch (IllegalArgumentException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    @Override
    public Boolean alterar(Z objeto) {

        O ob = toDB(objeto);
        Field[] campos = getDeclaredFields(ob);

        String sql;
        if (CAMPOS == null) {
            sql = sqlUpdate(campos);
        } else {
            sql = sqlUpdate();
        }

        for (Field campo : campos) {
            if (campo.getName().equals("id")) {
                campo.setAccessible(true);
                try {
                    if (campo.get(ob) == null) {
                        return false;
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            Integer ultimoSet = campos.length - 1;
            for (int i = 1; i < ultimoSet; i++) {
                campos[i].setAccessible(true);
                Object valor = campos[i].get(ob);
                stmt.setObject(i, valor);
            }
            campos[0].setAccessible(true);
            stmt.setObject(ultimoSet, campos[0].get(ob));

            if (stmt.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

        return false;
    }

    @Override
    public Boolean excluir(Long id) {
        if (id == null) {
            return false;
        }

        try (PreparedStatement stmt = con.prepareStatement(sqlDelete())) {
            stmt.setObject(1, id);

            if (stmt.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

        return false;
    }

    @Override
    public List<Z> listarTodos() {

        O ob = o;

        Class c = ob.getClass();
        Field[] atributos = c.getDeclaredFields();

        String sql = "SELECT * FROM " + this.banco + "." + this.tabela
                + " WHERE " + this.banco + "." + this.tabela + "." + "data_remocao IS NULL";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            if (stmt.execute()) {

                ResultSet rs = stmt.getResultSet();
                List<Z> lista = new LinkedList<>();

                while (rs.next()) {
                    ob = (O) ob.getClass().getConstructor().newInstance();
                    for (int i = 0; i < atributos.length; i++) {
                        atributos[i].setAccessible(true);
                        atributos[i].set(ob, rs.getObject(i + 1));
                    }
                    Z zz = fromDB(ob);
                    lista.add(zz);
                }
                return lista;
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    @Override
    public List<Z> listarAoNomeParcial(String key) {
        Class c = o.getClass();
        Field[] atributos = c.getDeclaredFields();

        String sql = "SELECT * FROM " + this.banco + "." + this.tabela
                + " WHERE " + this.banco + "." + this.tabela + "." + "data_remocao IS NULL"
                + " AND LOWER(" + this.banco + "." + this.tabela + "." + this.key + ") LIKE ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, "%" + key.toLowerCase() + "%");

            if (stmt.execute()) {

                ResultSet rs = stmt.getResultSet();
                List<Z> lista = new LinkedList<>();

                while (rs.next()) {
                    o = (O) o.getClass().getConstructor().newInstance();
                    for (int i = 0; i < atributos.length; i++) {
                        atributos[i].setAccessible(true);
                        atributos[i].set(o, rs.getObject(i + 1));
                    }
                    lista.add(fromDB(o));
                }
                return lista;
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

        return null;
    }

    @Override
    public Z get(String key) {
        Class c = o.getClass();
        Field[] atributos = c.getDeclaredFields();

        String sql = "SELECT * FROM " + this.banco + "." + this.tabela
                + " WHERE " + this.banco + "." + this.tabela + "." + "data_remocao IS NULL"
                + " AND LOWER(" + this.banco + "." + this.tabela + "." + this.key + ") = ? ";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, key.toLowerCase());

            if (stmt.execute()) {

                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {

                    o = (O) o.getClass().getConstructor().newInstance();
                    for (int i = 0; i < atributos.length; i++) {
                        atributos[i].setAccessible(true);
                        atributos[i].set(o, rs.getObject(i + 1));
                    }
                    return fromDB(o);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    @Override
    public Long getId(String key) {

        String sql = "SELECT " + this.banco + "." + this.tabela + "." + "id"
                + " FROM " + this.banco + "." + this.tabela
                + " WHERE " + this.banco + "." + this.tabela + "." + "data_remocao IS NULL"
                + " AND LOWER(" + this.banco + "." + this.tabela + "." + this.key + ") = ? ";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, key.toLowerCase());

            if (stmt.execute()) {

                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    @Override
    public Z getPorId(Long id) {
        Class c = o.getClass();
        Field[] atributos = c.getDeclaredFields();

        String sql = "SELECT * FROM " + this.banco + "." + this.tabela
                + " WHERE " + this.banco + "." + this.tabela + "." + "data_remocao IS NULL"
                + " AND " + this.banco + "." + this.tabela + "." + "id" + " = ? ";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, id);

            if (stmt.execute()) {

                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    o = (O) o.getClass().getConstructor().newInstance();
                    for (int i = 0; i < atributos.length; i++) {
                        atributos[i].setAccessible(true);
                        atributos[i].set(o, rs.getObject(i + 1));
                    }
                    return fromDB(o);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    @Override
    public Boolean existente(String key) {
        String sql = "SELECT "
                + "IF(("
                + "SELECT " + "MIN(" + this.banco + "." + this.tabela + ".id " + ") "
                + "FROM " + this.banco + "." + this.tabela + " "
                + "WHERE " + this.banco + "." + this.tabela + "." + "data_remocao IS NULL "
                + "AND LOWER(" + this.banco + "." + this.tabela + "." + this.key + ") = ?) "
                + "IS NOT NULL , TRUE , FALSE)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, key.toLowerCase());

            if (stmt.execute()) {

                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return false;
    }

    protected O toDB() {

        try {
            O ob = (O) o.getClass().getConstructor().newInstance();

            return ob;
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    @Override
    public O toDB(Z objeto) {
        Field[] ff = getDeclaredFields(objeto);

        O ob = toDB();
        Field[] atributos = getDeclaredFields(ob);

        try {

            for (int i = 0; i < atributos.length; i++) {
                ff[i].setAccessible(true);
                atributos[i].setAccessible(true);
                atributos[i].set(ob, ff[i].get(objeto));
            }
            return ob;

        } catch (SecurityException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    protected Z fromDB() {

        try {
            Z ob = (Z) z.getClass().getConstructor().newInstance();

            return ob;
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    @Override
    public Z fromDB(O objeto) {
        Field[] ff = getDeclaredFields(objeto);

        Z ob = fromDB();
        Field[] atributos = getDeclaredFields(ob);

        try {

            for (int i = 0; i < atributos.length; i++) {
                ff[i].setAccessible(true);
                atributos[i].setAccessible(true);
                atributos[i].set(ob, ff[i].get(objeto));
            }
            return ob;

        } catch (SecurityException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }
}
