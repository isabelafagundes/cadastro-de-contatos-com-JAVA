package agendaform.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FabricaDeConexao {
    
    static public Connection obterConexao() throws ClassNotFoundException, SQLException {
        Logger logger = Logger.getLogger("FabricaDeConexao");
        try {
            String user = "root";
            String senha = "root";
            String url = "jdbc:mysql://localhost:3306/AGENDA_ISABELA?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            Connection conexao = DriverManager.getConnection(url, user, senha);
            return conexao;
        } catch(ClassNotFoundException erro) {
             logger.log(Level.SEVERE, "Driver MySQL não encontrado!",erro);
        } catch (SQLException erro) {
            logger.log(Level.SEVERE, "Erro ao efetuar a conexão com a base de dados! ",erro);
        }
        return null;
    }
    
    static public void desconectar(Connection conexao) {
        Logger logger = Logger.getLogger("FabricaDeConexao");
        try {
            conexao.close();
        } catch (Exception erro) {
            logger.log(Level.SEVERE, "Erro ao fechar a conexão com a base de dados! ",erro);
        }
    }
    
}
