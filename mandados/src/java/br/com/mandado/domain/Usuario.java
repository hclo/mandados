package br.com.mandado.domain;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Helen
 */
@Entity
@Table(name = "tbl_usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuario.listar", query = "SELECT usuario FROM Usuario usuario"),
    @NamedQuery(name = "Usuario.buscarPorCodigo", query = "SELECT usuario FROM Usuario usuario WHERE usuario.codigo = :codigo"),
    @NamedQuery(name = "Usuario.autenticar", query = "SELECT usuario FROM Usuario usuario WHERE usuario.cpf = :cpf AND usuario.senha = :senha")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Usuario.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usr_codigo")
    private Long codigo;

    @NotEmpty(message = "O campo nome é obrigatório.")
    @Size(min = 5, max = 50, message = "Tamanho inválido para o campo nome (5 a 50).")
    @Column(name = "usr_nome", length = 50, nullable = false)
    private String nome;

    @CPF(message = "O CPF informado é inválido.")
    @Column(name = "usr_cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @NotEmpty(message = "O campo senha é obrigatório.")
    @Size(min = 6, message = "Tamanho inválido para o campo senha (6 a 8).")
    @Column(name = "usr_senha", length = 32, nullable = false)
    private String senha;

    @NotEmpty(message = "O campo função é obrigatório.")
    @Column(name = "usr_funcao", length = 50, nullable = false)
    private String funcao;

    /**
     * @return
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * @param funcao
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Usuario [codigo=" + codigo + ", nome=" + nome + ", cpf="
                + cpf + ", senha=" + senha + ", funcao=" + funcao + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        Usuario other = (Usuario) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

}
