package com.springsecurity.mysql.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="domain_id")
    private int domain_id;
    @Column(name="domain_provider")
    private String domain_provider;
    @Column(name="domain_name")
    private String domain_name;
    @Column(name="created_at")
    private String created_at;
    @Column(name="expires")
    private String expires;
    @Column(name="renew_auto")
    private String renew_auto;
    @Column(name="status")
    private String status;


    public Order() {
        super();
    }

    public Order(int domainId, String domainProvider, String domainName, String createdAt, String expires, String autoRenew, String status) {
        this.domain_id = domainId;
        this.domain_provider = domainProvider;
        this.domain_name = domainName;
        this.created_at = createdAt;
        this.expires = expires;
        this.renew_auto = autoRenew;
        this.status = status;

    }

    public int getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(int domain_id) {
        this.domain_id = domain_id;
    }

    public String getDomain_provider() {
        return domain_provider;
    }

    public void setDomain_provider(String domain_provider) {
        this.domain_provider = domain_provider;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getRenew_auto() {
        return renew_auto;
    }

    public void setRenew_auto(String renew_auto) {
        this.renew_auto = renew_auto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Order{" +
                "domain_id=" + domain_id +
                ", domain_provider='" + domain_provider + '\'' +
                ", domain_name='" + domain_name + '\'' +
                ", created_at='" + created_at + '\'' +
                ", expires='" + expires + '\'' +
                ", renew_auto='" + renew_auto + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if(domain_id == ((Order)(o)).getDomain_id() && domain_name.equals(((Order)(o)).getDomain_name())
                && status.equals(((Order)(o)).getStatus()) && expires.equals(((Order)(o)).getExpires())
                && renew_auto.equals(((Order)(o)).getRenew_auto()) && created_at.equals(((Order)(o)).getCreated_at())
                && domain_provider.equals(((Order)(o)).getDomain_provider()))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + domain_name.hashCode();
        result = 31 * result + domain_id;
        result = 31 * result + domain_provider.hashCode();
        return result;
    }


}