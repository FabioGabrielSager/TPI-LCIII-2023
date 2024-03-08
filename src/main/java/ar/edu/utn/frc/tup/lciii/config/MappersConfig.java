package ar.edu.utn.frc.tup.lciii.config;

import ar.edu.utn.frc.tup.lciii.entidades.EntidadFicha;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadPosicion;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.ColorFicha;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.Ficha;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.FichaFactory;
import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MappersConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new AbstractConverter<EntidadFicha, Ficha>() {
            @Override
            // Realizo conversión personalizada para convertir la fichas correctamente fichas.
            protected Ficha convert(EntidadFicha source) {
                String tipoFicha = source.getTipoFicha();
                Ficha f = FichaFactory.crearFicha(tipoFicha);
                f.setId(source.getId());
                f.setColor(ColorFicha.valueOf(source.getColor()));
                f.setPosicion(new Posicion(source.getPosicion().getId(), source.getPosicion().getX(), source.getPosicion().getY()));
                f.setTipoFicha(source.getTipoFicha());
                f.setViva(source.isViva());
                return f;
            }
        });

        Converter<List<String>, String> movimientosToJsonConverter = new AbstractConverter<>() {
            @Override
            protected String convert(List<String> movimientos) {
                Gson gson = new Gson();
                return gson.toJson(movimientos);
            }
        };

        Converter<String, List<String>> jsonToMovimientosConverter = new AbstractConverter<>() {
            @Override
            protected List<String> convert(String json) {
                Gson gson = new Gson();
                return gson.fromJson(json, new TypeToken<List<String>>() {
                }.getType());
            }
        };


        Converter<EntidadPosicion, Posicion> entidadPosicionToPosicionConverter = new AbstractConverter<>() {
            protected Posicion convert(EntidadPosicion source) {
                Posicion p = new Posicion();
                p.setId(source.getId());
                p.setX(source.getX());
                p.setY(source.getY());
                return p;
            }
        };

        Converter<Posicion, EntidadPosicion> posicionToEntidadPosicionConverter = new AbstractConverter<>() {
            protected EntidadPosicion convert(Posicion source) {
                EntidadPosicion ep = new EntidadPosicion();
                ep.setId(source.getId());
                ep.setX(source.getX());
                ep.setY(source.getY());
                return ep;
            }
        };

        modelMapper.addConverter(posicionToEntidadPosicionConverter);


        modelMapper.addConverter(entidadPosicionToPosicionConverter);
        modelMapper.addConverter(movimientosToJsonConverter);
        modelMapper.addConverter(jsonToMovimientosConverter);

        return modelMapper;
    }

    @Bean("mergerMapper")
    public ModelMapper mergerMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}