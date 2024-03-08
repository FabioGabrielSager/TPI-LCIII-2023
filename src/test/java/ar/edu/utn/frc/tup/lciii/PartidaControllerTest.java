package ar.edu.utn.frc.tup.lciii;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PartidaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1mostrarPartidas() throws Exception {
        this.mockMvc.perform(get("/partida/mostrar_partidas")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void test1getPartida() throws Exception {
        this.mockMvc.perform(get("/partida/mostrar_partida/500")).andDo(print()).andExpect(status().isNotFound());
    }
    @Test
    public void test1crearPartida() throws Exception {
        mockMvc.perform(post("/partida/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreJugadorBlanco\": \"\",\"nombreJugadorNegro\": \"pepo\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test1moverFicha() throws Exception {
        mockMvc.perform(put("/partida/mover_ficha").param("id", "500").param("mov", "Pa2 a3"))
                .andExpect(status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Partida no encontrada"));
    }
}