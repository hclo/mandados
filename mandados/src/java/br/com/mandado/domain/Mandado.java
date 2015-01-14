package br.com.mandado.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Helen
 */
@Entity
@Table(name = "tbl_mandados")
@NamedQueries({
    @NamedQuery(name = "Mandado.listar", query = "SELECT mandado FROM Mandado mandado"),
    @NamedQuery(name = "Mandado.buscarPorCodigo", query = "SELECT mandado FROM Mandado mandado WHERE mandado.codigo = :codigo")})
public class Mandado implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Mandado.class.getName());
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "man_codigo")
    private Long codigo;
    
    @Column(name = "man_numero")
    private String numero;
    
    @Column(name = "man_autos")
    private String autos;
    
    @Column(name = "man_cartorio")
    private String cartorio;
    
    @Column(name = "man_nome")
    private String nome;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_logradouros_log_codigo", referencedColumnName = "log_codigo", nullable = false)
    @Cascade(CascadeType.ALL)
    private Logradouro logradouro;
    
    @Column(name = "man_complemento")
    private String complemento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_bairros_bai_codigo", referencedColumnName = "bai_codigo", nullable = false)
    @Cascade(CascadeType.ALL)
    private Bairro bairro;
    
    @Column(name = "man_reg")
    private int reg;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "man_audi")
    private Date audi;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "man_carga")
    private Date carga;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "man_prazo")
    private Date prazo;
    
    @Column(name = "man_jgpg")
    private String jgpg;
    
    @ElementCollection(targetClass = Status.class)
    @CollectionTable(name = "tbl_status", joinColumns = @JoinColumn(name = "sta_codigo"))
    @Column(name = "man_status", nullable = false)
    private Set<Status> _status;
    
    public Mandado() {
    }

    public Mandado(Long codigo, String numero, String autos, String cartorio, String nome, Logradouro logradouro, String complemento, Bairro bairro, int reg, Date audi, Date carga, Date prazo, String jgpg, Set<Status> _status) {
        this.codigo = codigo;
        this.numero = numero;
        this.autos = autos;
        this.cartorio = cartorio;
        this.nome = nome;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.reg = reg;
        this.audi = audi;
        this.carga = carga;
        this.prazo = prazo;
        this.jgpg = jgpg;
        this._status = _status;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAutos() {
        return autos;
    }

    public void setAutos(String autos) {
        this.autos = autos;
    }

    public String getCartorio() {
        return cartorio;
    }

    public void setCartorio(String cartorio) {
        this.cartorio = cartorio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public Date getAudi() {
        return audi;
    }

    public void setAudi(Date audi) {
        this.audi = audi;
    }

    public Date getCarga() {
        return carga;
    }

    public void setCarga(Date carga) {
        this.carga = carga;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public String getJgpg() {
        return jgpg;
    }

    public void setJgpg(String jgpg) {
        this.jgpg = jgpg;
    }

    public Set<Status> getStatus() {
        return _status;
    }

    public void setStatus(Set<Status> _status) {
        this._status = _status;
    }

    @Override
    public String toString() {
        return "Mandado{" + "codigo=" + codigo + ", numero=" + numero + ", autos=" + autos + ", cartorio=" + cartorio + ", nome=" + nome + ", logradouro=" + logradouro + ", complemento=" + complemento + ", bairro=" + bairro + ", reg=" + reg + ", audi=" + audi + ", carga=" + carga + ", prazo=" + prazo + ", jgpg=" + jgpg + ", _status=" + _status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.codigo);
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
        final Mandado other = (Mandado) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

}
