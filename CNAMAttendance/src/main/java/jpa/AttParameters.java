/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mazen
 */
@Entity
@Table(name = "ATT_PARAMETERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AttParameters.findAll", query = "SELECT a FROM AttParameters a")
    , @NamedQuery(name = "AttParameters.findByParametersId", query = "SELECT a FROM AttParameters a WHERE a.parametersId = :parametersId")
    , @NamedQuery(name = "AttParameters.findByIssaeLatitude", query = "SELECT a FROM AttParameters a WHERE a.issaeLatitude = :issaeLatitude")
    , @NamedQuery(name = "AttParameters.findByIssaeLongitude", query = "SELECT a FROM AttParameters a WHERE a.issaeLongitude = :issaeLongitude")
    , @NamedQuery(name = "AttParameters.findByGoogleMapsApi", query = "SELECT a FROM AttParameters a WHERE a.googleMapsApi = :googleMapsApi")
    , @NamedQuery(name = "AttParameters.findByActiveFlg", query = "SELECT a FROM AttParameters a WHERE a.activeFlg = :activeFlg")})
public class AttParameters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARAMETERS_ID")
    private Integer parametersId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISSAE_LATITUDE")
    private BigDecimal issaeLatitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISSAE_LONGITUDE")
    private BigDecimal issaeLongitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "GOOGLE_MAPS_API")
    private String googleMapsApi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVE_FLG")
    private int activeFlg;

    public AttParameters() {
    }

    public AttParameters(Integer parametersId) {
        this.parametersId = parametersId;
    }

    public AttParameters(Integer parametersId, BigDecimal issaeLatitude, BigDecimal issaeLongitude, String googleMapsApi, int activeFlg) {
        this.parametersId = parametersId;
        this.issaeLatitude = issaeLatitude;
        this.issaeLongitude = issaeLongitude;
        this.googleMapsApi = googleMapsApi;
        this.activeFlg = activeFlg;
    }

    public Integer getParametersId() {
        return parametersId;
    }

    public void setParametersId(Integer parametersId) {
        this.parametersId = parametersId;
    }

    public BigDecimal getIssaeLatitude() {
        return issaeLatitude;
    }

    public void setIssaeLatitude(BigDecimal issaeLatitude) {
        this.issaeLatitude = issaeLatitude;
    }

    public BigDecimal getIssaeLongitude() {
        return issaeLongitude;
    }

    public void setIssaeLongitude(BigDecimal issaeLongitude) {
        this.issaeLongitude = issaeLongitude;
    }

    public String getGoogleMapsApi() {
        return googleMapsApi;
    }

    public void setGoogleMapsApi(String googleMapsApi) {
        this.googleMapsApi = googleMapsApi;
    }

    public int getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(int activeFlg) {
        this.activeFlg = activeFlg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametersId != null ? parametersId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttParameters)) {
            return false;
        }
        AttParameters other = (AttParameters) object;
        if ((this.parametersId == null && other.parametersId != null) || (this.parametersId != null && !this.parametersId.equals(other.parametersId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.AttParameters[ parametersId=" + parametersId + " ]";
    }
    
}
