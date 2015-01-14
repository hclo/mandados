package br.com.mandado.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Helen
 */
@Entity
@Table(name = "tbl_logradouros")
@NamedQueries({
    @NamedQuery(name = "Logradouro.listar", query = "SELECT logradouro FROM Logradouro logradouro"),
    @NamedQuery(name = "Logradouro.buscarPorCodigo", query = "SELECT logradouro FROM Logradouro logradouro WHERE logradouro.codigo = :codigo"),
    @NamedQuery(name = "Logradouro.listarPorBairro", query = "SELECT logradouro FROM Logradouro logradouro WHERE logradouro.bairro = :bairro")})
public class Logradouro implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Logradouro.class.getName());
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "log_codigo")
    private Long codigo;
    
    @Column(name = "log_descricao")
    private String descricao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_bairros_bai_codigo", referencedColumnName = "bai_codigo", nullable = false)
    private Bairro bairro;

    public Logradouro() {
    }

    public Logradouro(Long codigo, String descricao, Bairro bairro) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.bairro = bairro;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Logradouro{" + "codigo=" + codigo + ", descricao=" + descricao + ", bairro=" + bairro + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Logradouro other = (Logradouro) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
}
