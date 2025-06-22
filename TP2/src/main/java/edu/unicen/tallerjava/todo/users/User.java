package edu.unicen.tallerjava.todo.users;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private Integer id;

    private String name;

    public User() {
        this("", 0);
    }

    public User(String name) {
        this(name, 0);
    }

    public User(String name, Integer id) {
        super();
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        //System.out.println("en hashcode. name = " + this.name + " id = " + this.id);
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        //System.out.println("en equals. this.name = " + this.name + " this.id = " + this.id);
        //System.out.println("en equals. obj.name = " + ((User) obj).getName() + " obj.id = " + ((User) obj).getId());
        return (this.name.equals(((User) obj).getName()));
    }

    public String toString()
    {
        return this.name;
    }
}
