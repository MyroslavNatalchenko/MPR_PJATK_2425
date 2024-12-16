package pl.edu.pjatk.MPR_Spring_PRJ.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //za pomoca JPA stworze tabele SQL School (1)
public class School {
    @Id //main key
    @GeneratedValue(strategy = GenerationType.AUTO) //AutoGeneration
    private Long id;
    private String name;
    private int number;
    private Long indetyfikator;

    public School() {}

    public School(String name, int number) {
        this.name = name;
        this.number = number;
        this.setIndetyfikator(countIndenticator());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /*
    Indetyfikator core
     */
    public Long countIndenticator(){
        long indetyfikator= 0L;
        for (char c : this.name.toCharArray()) {
            indetyfikator+=(long) c;
        }
        int n=this.number;
        while (n!=0){
            indetyfikator+=(long) (n%10);
            n/=10;
        }
        return indetyfikator;
    }
    public Long getIndetyfikator() {
        return indetyfikator;
    }
    public void setIndetyfikator(Long indetyfikator) {
        this.indetyfikator = indetyfikator;
    }
}
