package ar.edu.utn.frc.tup.lciii.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name =  "jugadores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntidadJugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;
}