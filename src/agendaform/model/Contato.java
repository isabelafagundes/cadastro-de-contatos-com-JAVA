
package agendaform.model;

/**
 * @since 28 de out de 2023
 * @author Isabela Fagundes
 */
public class Contato {
    
    private int id;
    private String nome;
    private String telefone;
    private String email;
    
    public Contato(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    
}
