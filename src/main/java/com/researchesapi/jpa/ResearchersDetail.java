package com.researchesapi.jpa;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "researchers_information")
@NamedQueries({
        @NamedQuery(name = "ResearchersDetail.findAll", query = "SELECT r FROM ResearchersDetail r")})
public class ResearchersDetail implements Serializable {

    private static final long serialVersionUID = 2466746444860530376L;

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;

    @Basic(optional = false)
    @Column(name = "url", length=128)
    private String url;

    @Basic(optional = false)
    @Column(name = "eid", length=32)
    private String eid;

    @Basic(optional = false)
    @Column(name = "total_results")
    private Integer totalResults;

    @Basic(optional = false)
    @Column(name = "affiliation_name")
    private String affiliationName;

    @Basic(optional = true)
    @Column(name = "affiliation_city")
    private String affiliationCity;

    @Basic(optional = true)
    @Column(name = "affiliation_country")
    private String affiliationCountry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public String getAffiliationName() {
        return affiliationName;
    }

    public void setAffiliationName(String affiliationName) {
        this.affiliationName = affiliationName;
    }

    public String getAffiliationCity() {
        return affiliationCity;
    }

    public void setAffiliationCity(String affiliationCity) {
        this.affiliationCity = affiliationCity;
    }
}
