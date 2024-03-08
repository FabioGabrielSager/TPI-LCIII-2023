package ar.edu.utn.frc.tup.lciii.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name =  "partidas")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class EntidadPartida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jugadorBlanco_id")
    private EntidadJugador jugadorBlanco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jugadorNegro_id")
    private EntidadJugador jugadorNegro;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "partida_fichas",
            joinColumns = @JoinColumn(name = "partida_id"),
            inverseJoinColumns = @JoinColumn(name = "ficha_id")
    )
    private List<EntidadFicha> fichas = new ArrayList<>();

    @Column(name = "turno")
    private String turno;

    @Column(name = "creada_en")
    private LocalDateTime creadaEn;

    @Column(name = "terminada")
    private boolean terminada;
}