package ar.edu.utn.frc.tup.lciii.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "posiciones")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntidadPosicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "posicion_x")
    private int x;

    @Column(name = "posicion_y")
    private int y;
}