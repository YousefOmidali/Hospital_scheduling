package Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Hospital hospital;
//    @OneToMany(mappedBy = "clinic")
//    private List<Doctor> doctors;

    public Clinic(Long id) {
        this.id = id;
    }
}
