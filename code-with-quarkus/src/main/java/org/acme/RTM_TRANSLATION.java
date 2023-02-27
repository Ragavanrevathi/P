package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class RTM_TRANSLATION extends PanacheEntityBase {
    @Id
    String LANG;
    String RTM_ID;

    String TEXT;

    String STATUS;





    @Override
    public String toString() {
        return "RTM_TRANSLATION{" +
                "LANG='" + LANG + '\'' +
                ", RTM_ID='" + RTM_ID + '\'' +
                ", TEXT='" + TEXT + '\'' +
                ", STATUS='" + STATUS + '\'' +
                '}';
    }
}
