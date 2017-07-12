package br.com.linux_park.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoDB {

    static private ConexaoDB instanciaUnica;

    private Connection conexao;
    private String url;
    private String login;
    private String senha;

    private ConexaoDB() {
        dadosConexao();
    }

    private void dadosConexao() {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream("./src/main/java/br/com/linux_park/config.properties"));
            String tipo = (String) config.get("tipodb");
            if (tipo.equals("mysql")) {
                this.url = "jdbc:mysql://" + config.getProperty("servidordb") + ":"
                        + config.get("portadb") + "/estacionamento?serverTimezone=GMT-03:00";
            }

            this.login = (String) config.get("user");
            this.senha = (String) config.get("password");

        } catch (IOException ex) {
            Logger.getLogger(ConexaoDB.class.getName()).log(Level.SEVERE, null, ex);
//            this.url = "jdbc:mysql://localhost:3306/projeto?serverTimezone=GMT-03:00";
//            this.login = "root";
//            this.senha = "";
        } finally {

        }
    }

    public static boolean conexaoHabil() {
        ConexaoDB conTemp = new ConexaoDB();

        try {
            conTemp.conexao = DriverManager.getConnection(conTemp.url, conTemp.login, conTemp.senha);
            if (conTemp.conexao != null) {
                conTemp.conexao.close();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("error : " + e.getErrorCode() + " : " + e.getMessage());
        } finally {

        }
        return false;
    }

    public static Connection getConnection() {

        //Class.forName("com.mysql.jdbc.Driver"); //Não precisa mais desse comando
        try /*(Connection con = DriverManager.getConnection(url, login, senha))*/ { //autocloseable
            if (instanciaUnica == null || instanciaUnica.conexao == null || instanciaUnica.conexao.isClosed()) {
                synchronized (ConexaoDB.class) {
                    instanciaUnica = new ConexaoDB();
                }
            } else {
                return instanciaUnica.conexao;
            }

            instanciaUnica.conexao = DriverManager.getConnection(instanciaUnica.url, instanciaUnica.login, instanciaUnica.senha);

            return instanciaUnica.conexao;

        } catch (SQLException e) {
            String leituraRetorno = "error : " + e.getErrorCode() + " : " + e.getMessage();
            System.out.println(leituraRetorno);

            throw new RuntimeException(leituraRetorno, e);

//            JOptionPane.showMessageDialog(null, leituraRetorno);
//            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void closeConnection() {
        try {
            if (instanciaUnica.conexao != null && !instanciaUnica.conexao.isClosed()) {
                instanciaUnica.conexao.close();
            }

        } catch (SQLException e) {
            String leituraRetorno = "error : " + e.getErrorCode() + " : " + e.getMessage();
            System.out.println(leituraRetorno);
            throw new RuntimeException(leituraRetorno);
        } finally {

        }
    }
}
