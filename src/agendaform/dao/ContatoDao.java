package agendaform.dao;

import agendaform.model.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @since 28 de out de 2023
 * @author Isabela Fagundes
 */
public class ContatoDao {
    
    private final String INSERT = "INSERT INTO contato (nome, telefone, email) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE contato SET nome = ?, email = ?, telefone = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM contato WHERE id = ?";
    private final String SELECT = "SELECT * FROM contato";
    Logger logger = Logger.getLogger("ContatoDao");


    public List<Contato> obterContatos() {
        try {
            Connection conexao = FabricaDeConexao.obterConexao();
            Statement stmt = conexao.createStatement();
            ResultSet resultSet = stmt.executeQuery(SELECT);
            List<Contato> contatos = new ArrayList<>();
            while (resultSet.next()) {
                Contato contato = new Contato(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("telefone"));
                contatos.add(contato);
            }
            
            logger.info("Sucesso na obtenção dos contatos!");
            
            return contatos;

        } catch (Exception erro) {
            logger.log(Level.SEVERE, "Erro ao efetuar a busca de contatos! ", erro);
        }
        return null;
    }

    public void inserir(String nome, String email, String telefone) {
        try {
            Connection conexao = FabricaDeConexao.obterConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, nome);
            pstmt.setString(2, telefone);
            pstmt.setString(3, email);
            pstmt.execute();
            logger.info("Sucesso ao efetuar a inserção de contato!");
        } catch (Exception erro) {
            logger.log(Level.SEVERE, "Erro ao efetuar a inserção de contato! ", erro);
        }
    }
    
    public void atualizar(int id, String nome, String email, String telefone) {
        try {
            Connection conexao = FabricaDeConexao.obterConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, telefone);
            pstmt.setInt(4, id);
            pstmt.execute();
            logger.info("Sucesso ao efetuar a atualização de contato!");
        } catch (Exception erro) {
            System.out.println("Erro ao efetuar a inserção de contato - " + erro.getMessage());
            logger.log(Level.SEVERE, "Erro ao efetuar a atualização deste contato! ", erro);
        }
    }
    
    public void deletar(int id) {
        try {
            Connection conexao = FabricaDeConexao.obterConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setInt(1, id);
            pstmt.execute();
            logger.info("Sucesso ao deletar o contato!");
        } catch (Exception erro) {
            logger.log(Level.SEVERE, "Erro ao deletar este contato! ", erro);
        }
    }
}
