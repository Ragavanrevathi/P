package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RTM_LANGUAGE extends PanacheEntityBase {
    @Id
    String CODE;




    @Override
    public String toString() {
        return "RTM_LANGUAGE{" +
                "CODE='" + CODE ;

    }
}
