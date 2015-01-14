package br.com.mandado.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Helen
 */
@Entity
@Table(name = "tbl_bairros")
@NamedQueries({
    @NamedQuery(name = "Bairro.listar", query = "SELECT bairro FROM Bairro bairro"),
    @NamedQuery(name = "Bairro.buscarPorCodigo", query = "SELECT bairro FROM Bairro bairro WHERE bairro.codigo = :codigo")})
public class Bairro implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Bairro.class.getName());
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bai_codigo")
    private Long codigo;
    
    @Column(name = "bai_descricao")
    private String descricao;

    public Bairro() {
    }

    public Bairro(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return "Bairro{" + "codigo=" + codigo + ", descricao=" + descricao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.codigo);
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
        final Bairro other = (Bairro) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

}
