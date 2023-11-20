package UdeA.zerohunger.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record Home(

        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate submissionDate,
        String nombre,
        String gradoEscolaridad,
        String jefeFamilia,
        Double salario,
        int miembros,
        int habitaciones,
        int comidas
) {


}