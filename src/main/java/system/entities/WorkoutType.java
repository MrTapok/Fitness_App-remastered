package system.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workouttype", schema = "sql7241038")
public class WorkoutType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
