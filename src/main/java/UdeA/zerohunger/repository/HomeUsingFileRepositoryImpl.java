package UdeA.zerohunger.repository;

import UdeA.zerohunger.model.Home;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HomeUsingFileRepositoryImpl implements HomeRepository {

    public static final Logger logger = LoggerFactory.getLogger(HomeUsingFileRepositoryImpl.class);
    private List<Home> homeList;

    public HomeUsingFileRepositoryImpl() {
        //this.houseHoldIncomeList = new ArrayList<>(loadHouseHoldIncomeFromFile());
        this.homeList = new ArrayList<>(loadHomes ());
    }

    private List<Home> loadHomes(){
        logger.info( "Cargando los datos desde archivo" );
        List<String> plainTextHomeList =  readFileWithHomes();
        List<Home> homeList = plainTextHomeList.stream().map( this::buildHome ).toList();
        return homeList;
    }

    private List<Home> loadHomesFromFile() {
        logger.info("Loading data from file");
        List<String> plainTextHomeList = readFileWithHomes();
        return plainTextHomeList.stream()
                .map(this::createHomeFromPlainText)
                .filter(Objects::nonNull)
                .toList();
    }

    private List<String> readFileWithHomes() {
        Path path = Paths.get("./src/main/resources/families.txt");
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            logger.error("The file 'families.txt' does not exist or is not a regular file.");
            return Collections.emptyList();
        }

        try (Stream<String> stream = Files.lines(path)) {
            return stream.toList();
        } catch (IOException e) {
            logger.error("Error reading file 'families.txt'", e);
            throw new RuntimeException("Error reading file 'families.txt'", e);
        }
    }



    private Home buildHome(String plainTextHome) {
    /*Este metodo toma una linea del archivo para generar un vector
   y con dicho vector generar una Nota
     */
        String[] questionArray = plainTextHome.split (",");//En el archivo las notas vienen separadas por comas por ejemplo: UNIDAD 1,4.5D,2023-08-01

        Home home = new Home (LocalDate.parse (
                questionArray[0].trim (), DateTimeFormatter.ISO_DATE),
                questionArray[1].trim (),
                questionArray[2].trim (),
                questionArray[3].trim (),
                Double.parseDouble (questionArray[4].trim ()),
                Integer.parseInt (questionArray[5].trim ()),
                Integer.parseInt (questionArray[6].trim ()),
                Integer.parseInt (questionArray[7].trim ()));

        return home;
    }

    @Override
    public List<Home> findAllHomes() {

        return homeList;
    }
    public Home createHomeFromPlainText (String plainTextHome) {
        logger.info("Loading data from file");
        String[] homeArray = plainTextHome.split(",");

        try {
            if (homeArray.length != 8) {
                logger.error("Incorrect number of fields in home income data. Expected 8 fields, but got {}: {}", homeArray.length, plainTextHome);
                return null;
            }


            LocalDate submissionDate = LocalDate.parse(homeArray[0].trim(), DateTimeFormatter.ISO_DATE);
            String nombre = homeArray[1].trim();
            String gradoEscolaridad = homeArray[2].trim();
            String jefeFamilia = homeArray[3].trim();

            double salario = Double.parseDouble(homeArray[4].trim());
            int miembros = Integer.parseInt(homeArray[5].trim());
            int habitaciones = Integer.parseInt(homeArray[6].trim());
            int comidas = Integer.parseInt(homeArray[7].trim());

            return new Home(submissionDate, nombre, gradoEscolaridad, jefeFamilia, salario, miembros, habitaciones, comidas);
        } catch (NumberFormatException | DateTimeParseException e) {
            logger.error("Error parsing home data: " + plainTextHome, e);
            return null;
        }
    }

  /*
  public HouseHoldIncome createHouseHoldIncomeFromPlainText(String plainTextHouseHoldIncome) {
    logger.info("Loading data from the file");
    String[] houseHoldIncomeArray = plainTextHouseHoldIncome.split(",");
    try {
        if (houseHoldIncomeArray.length != 8) {
            logger.error("Incorrect number of fields in household income data. Expected 8 fields, but got {}: {}", houseHoldIncomeArray.length, plainTextHouseHoldIncome);
            return null;
        }

        // Resto del código...

    } catch (NumberFormatException | DateTimeParseException e) {
        logger.error("Error parsing household income data: " + plainTextHouseHoldIncome, e);
        return null;
    }
}

   */


    @Override
    public Optional<Home> getHome (String nombre) {

        //return Optional.empty ();
        return this.homeList.stream().filter( p->p.nombre ().equals( nombre ) ).findAny();
    }

    @Override
    public Home addHome(Home newHome) {
        this.homeList.add(newHome);
        return newHome;
    }


    private Predicate<Home> isTheHomeOfTheName(Home newHome) {
        //return home -> home.nombre().equals(familyName);
        return p -> p.nombre ().equals( newHome.nombre ());
    }


    @Override
    public void updateHome (String familyName, Home updatedFamily) {
        // Implementar la lógica para actualizar el hogar en el archivo o en la base de datos
        // ...
    }

    @Override
    public void deleteHome (String familyName) {
        // Implementar la lógica para eliminar el hogar en el archivo o en la base de datos
        // ...
    }
;
}

