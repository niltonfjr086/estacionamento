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
    protected String inicio = "data_inclusao";
    protected String fim = "data_remocao";

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
            Class c = o.getClass();
            Field[] f = c.getDeclaredFields();
            id = f[0].getName();
        } else {
            id = CAMPOS[0];
        }

        String sql = "UPDATE " + this.banco + "." + tabela
                + " SET " + this.fim + " = NOW() "
                + " WHERE " + this.banco + "." + this.tabela + "." + this.fim + " IS NULL"
                + " AND " + this.banco + "." + this.tabela + "." + id + " = ?";

        return sql;
    }

    public String sqlDelete(Field... f) {
        String sql = "UPDATE " + this.banco + "." + tabela
                + " SET " + this.fim + " = NOW() "
                + " WHERE " + this.banco + "." + this.tabela + "." + this.fim + " IS NULL"
                + " AND " + this.banco + "." + this.tabela + "." + f[0] + " = ?";

        return sql;
    }

    public String sqlSelectTodos() {
        Field[] fs = o.getClass().getDeclaredFields();

        if (!fs[fs.length - 1].getName().equals(this.inicio)) {
            return "SELECT * FROM " + this.banco + "." + this.tabela + " ";

        } else {
            return "SELECT * FROM " + this.banco + "." + this.tabela
                    + " WHERE " + this.banco + "." + this.tabela + "." + this.fim + " IS NULL ";
        }
    }

    public String sqlSelectTodos(Long fk) {
        return this.sqlSelectTodos()
                + " AND "
                + this.banco + "." + this.tabela + "." + this.keyF + " = " + fk + " ";

    }

    public Field[] getSelectedFields(Object objeto) {
        Field[] fs = objeto.getClass().getDeclaredFields();

        if (!fs[fs.length - 1].getName().equals(this.inicio)) {

            Field[] someFields = new Field[fs.length - 1];
            for (int i = 0; i < someFields.length; i++) {
                someFields[i] = fs[i];
            }
            return someFields;
        }

        return fs;
    }

    @Override
    public Long inserir(Z objeto) {

        O ob = toDB(objeto);
        Field[] campos = getSelectedFields(ob);

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
        Field[] campos = getSelectedFields(ob);

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

//        String sql = "SELECT * FROM " + this.banco + "." + this.tabela
//                + " WHERE " + this.banco + "." + this.tabela + "." + this.fim + " IS NULL";
        try (PreparedStatement stmt = con.prepareStatement(GenericDAO.this.sqlSelectTodos())) {

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

    public List<Z> listarTodos(Long fk) {

        O ob = o;

        Class c = ob.getClass();
        Field[] atributos = c.getDeclaredFields();

        String sql;
        if (this.keyF == null) {
            sql = GenericDAO.this.sqlSelectTodos();
        } else {
            sql = "SELECT * FROM " + this.banco + "." + this.tabela
                    + " WHERE " + this.banco + "." + this.tabela + "." + this.fim + " IS NULL "
                    + " AND "
                    + this.banco + "." + this.tabela + "." + this.keyF + " = " + fk;
        }

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

    public List<Z> listarTodos(Long fk, Long fk2) {

        O ob = o;

        Class c = ob.getClass();
        Field[] atributos = c.getDeclaredFields();

        String sql;
        if (this.keyF == null) {
            sql = GenericDAO.this.sqlSelectTodos();
        } else {
            sql = "SELECT * FROM " + this.banco + "." + this.tabela
                    + " WHERE " + this.banco + "." + this.tabela + "." + this.fim + " IS NULL "
                    + " AND "
                    + this.banco + "." + this.tabela + "." + this.keyF + " = " + fk + " "
                    + " AND "
                    + this.banco + "." + this.tabela + "." + this.key2 + " = " + fk2 + " ";
        }

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

    public List<Z> listarTodos(Boolean estado) {

        O ob = o;
        Class c = ob.getClass();
        Field[] atributos = c.getDeclaredFields();

        String sql = "SELECT * FROM " + this.banco + "." + this.tabela
                + " WHERE " + this.banco + "." + this.tabela + "." + this.fim + " ";

        if (estado) {
            sql += " IS NULL";
        } else {

            sql += " IS NOT NULL";
        }

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

//    public List<UtilizacaoDB> listarTodos(Date de, Date ate) {
//
//        String sql = "SELECT * FROM " + this.banco + "." + this.tabela
//                + " WHERE " + this.banco + "." + this.tabela + "." + "dtEntrada" + " BETWEEN ? AND ?";
//
//        try (PreparedStatement stmt = con.prepareStatement(sql)) {
//
//            if (stmt.execute()) {
//
//                ResultSet rs = stmt.getResultSet();
//                List<UtilizacaoDB> lista = new LinkedList<>();
//
//                while (rs.next()) {
//                    lista.add(new UtilizacaoDB(rs.getLong(1), rs.getLong(2), rs.getTimestamp(3),
//                            rs.getTimestamp(4), rs.getDouble(5)));
//                }
//                return lista;
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//        } finally {
//
//        }
//        return null;
//    }
//
//    public List<UtilizacaoDB> listarTodos(Date de, Date ate, Boolean estado) {
//        String sql = "SELECT * FROM " + this.banco + "." + this.tabela
//                + " WHERE "
//                + this.banco + "." + this.tabela + "." + "dtEntrada" + " BETWEEN ? AND ?"
//                + " AND "
//                + this.banco + "." + this.tabela + ".dtSaida";
//
//        if (estado) {
//            sql += " IS NOT NULL";
//        } else {
//            sql += " IS NULL";
//
//        }
//        try (PreparedStatement stmt = con.prepareStatement(sql)) {
//
//            stmt.setObject(1, de.toString());
//            stmt.setObject(2, ate.toString());
//
//            if (stmt.execute()) {
//
//                ResultSet rs = stmt.getResultSet();
//                List<UtilizacaoDB> lista = new LinkedList<>();
//
//                while (rs.next()) {
//                    lista.add(new UtilizacaoDB(rs.getLong(1), rs.getLong(2), rs.getTimestamp(3),
//                            rs.getTimestamp(4), rs.getDouble(5)));
//                }
//                return lista;
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//        } finally {
//
//        }
//        return null;
//    }
    @Override
    public List<Z> listarAoNomeParcial(String... key) {
        Class c = o.getClass();
        Field[] atributos = c.getDeclaredFields();

        String sql = "SELECT * FROM " + this.banco + "." + this.tabela
                + " WHERE " + this.banco + "." + this.tabela + "." + this.fim + " IS NULL"
                + " AND LOWER(" + this.banco + "." + this.tabela + "." + this.key + ") LIKE ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, "%" + key[0].toLowerCase() + "%");

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
    public Z get(String... key) {
        O ob = toDB();
        Field[] atributos = new Field[]{};
        try {
            ob = (O) ob.getClass().getConstructor().newInstance();
            Class c = ob.getClass();
            atributos = c.getDeclaredFields();

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
        }

        String sql = this.sqlSelectTodos()
                + " AND LOWER(" + this.banco + "." + this.tabela + "." + this.key + ") = ? ";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, key[0].toLowerCase());

            if (stmt.execute()) {

                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {

//                    O ob = (O) o.getClass().getConstructor().newInstance();
                    for (int i = 0; i < atributos.length; i++) {
                        atributos[i].setAccessible(true);
                        atributos[i].set(ob, rs.getObject(i + 1));
                    }
                    return (Z) fromDB(ob);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return null;
    }

    @Override
    public Long getId(String... key) {

        String sql = "SELECT " + this.banco + "." + this.tabela + "." + "id"
                + " FROM " + this.banco + "." + this.tabela + " WHERE ";

        Field[] fs = o.getClass().getDeclaredFields();
        if (!fs[fs.length - 1].getName().equals(this.inicio)) {

            sql += this.banco + "." + this.tabela + "." + this.fim + " IS NULL" + " AND ";
        }

        sql += " LOWER(" + this.banco + "." + this.tabela + "." + this.key + ") = ? ";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, key[0].toLowerCase());

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

        String sql = this.sqlSelectTodos()
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
    public Boolean existente(String... key) {

        String sql = "SELECT "
                + "IF(("
                + "SELECT " + "MIN(" + this.banco + "." + this.tabela + ".id" + ") "
                + "FROM " + this.banco + "." + this.tabela + " WHERE "
                + " LOWER(" + this.banco + "." + this.tabela + "." + this.key + ") = ?) "
                + "IS NOT NULL , TRUE , FALSE)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, key[0].toLowerCase());

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

    public Boolean utilizando(String... keys) {

        String sql = "SELECT "
                + "IF(("
                + "SELECT " + "MIN(" + this.banco + "." + this.tabela + ".id " + ") "
                + "FROM " + this.banco + "." + this.tabela + " "
                + "WHERE " + this.banco + "." + this.tabela + "." + this.key + " = ? "
                + " AND " + this.banco + "." + this.tabela + "." + this.fim + " IS NULL " + " ) "
                + "IS NOT NULL , TRUE , FALSE)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
//                                for (int i = 0; i < atributos.length; i++) {
//                        atributos[i].setAccessible(true);
//                        atributos[i].set(o, rs.getObject(i + 1));
//                    }
//                    return fromDB(o);
            for (int i = 0; i < keys.length; i++) {
                stmt.setObject(i + 1, keys[i]);
            }

            if (stmt.execute()) {

                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    return rs.getInt(1) == 1;
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

        Class c = objeto.getClass();
        Field[] ff = c.getDeclaredFields();

        O ob = toDB();
        Class c2 = ob.getClass();
        Field[] atributos = c2.getDeclaredFields();

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
        Class c = objeto.getClass();
        Field[] ff = c.getDeclaredFields();

        z = fromDB();
        Class c2 = z.getClass();
        Field[] atributos = c2.getDeclaredFields();

        try {

            for (int i = 0; i < atributos.length; i++) {
                ff[i].setAccessible(true);
                atributos[i].setAccessible(true);
                atributos[i].set(z, ff[i].get(objeto));
            }
            return z;

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
