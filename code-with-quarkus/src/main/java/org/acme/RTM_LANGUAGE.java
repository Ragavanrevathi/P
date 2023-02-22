package org.acme;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class RTM_LANGUAGE extends PanacheEntity {
     public static String CODE;
     public String NAME;
     public String EMAIL;

     // return name as uppercase in the model
     public String getName(){
          return NAME.toUpperCase();
     }

     // store all names in lowercase in the DB
     public void setName(String name){
          this.NAME = name.toLowerCase();
     }

     public static RTM_LANGUAGE findByName(String name){
          return find("NAME", name).firstResult();
     }

     public static List<RTM_LANGUAGE> findAlive(){
          return list("CODE", CODE);
     }

}