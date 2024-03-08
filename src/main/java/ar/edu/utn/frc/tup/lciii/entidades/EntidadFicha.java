package ar.edu.utn.frc.tup.lciii.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fichas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntidadFicha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_ficha")
    private String tipoFicha;

    @ManyToMany(mappedBy = "fichas", cascade = CascadeType.ALL)
    private List<EntidadPartida> partidas = new ArrayList<>();

    @Column(name = "color")
    private String color;

    @Column(name = "viva")
    private boolean viva;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posicion_id")
    private EntidadPosicion posicion;
}