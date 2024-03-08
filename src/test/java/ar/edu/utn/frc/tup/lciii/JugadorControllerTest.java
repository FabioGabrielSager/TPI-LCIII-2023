package ar.edu.utn.frc.tup.lciii;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

public class JugadorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mostrarJugadores() throws Exception {
        this.mockMvc.perform(get("/jugador/mostrar_jugadores")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void crearJugador() throws Exception {
        this.mockMvc.perform(post("/jugador/crear").param("nombre", ""))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void guardarJugador() throws Exception {
        this.mockMvc.perform(put("/jugador/guardar").param("id", "600").param("nombre", "faav"))
                .andDo(print()).andExpect(status().isBadRequest());
    }

}





