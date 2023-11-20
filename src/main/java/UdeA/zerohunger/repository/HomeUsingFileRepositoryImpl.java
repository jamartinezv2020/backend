package UdeA.zerohunger.repository;

import UdeA.zerohunger.model.Home;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Stream;

public class HomeUsingFileRepositoryImpl implements HomeRepository {

    private static final Logger logger = LoggerFactory.getLogger(HomeUsingFileRepositoryImpl.class);
    private List<Home> homeList;

    public HomeUsingFileRepositoryImpl() {
        this.homeList = new ArrayList<> (loadHomesFromFile());
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

    private Home createHomeFromPlainText(String plainTextHome) {
        logger.info("Creating home from plain text");
        String[] homeArray = plainTextHome.split(",");
        try {
            if (homeArray.length != 8) {
                logger.error("Incorrect number of fields in home data. Expected 8 fields, but got {}: {}", homeArray.length, plainTextHome);
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

    @Override
    public List<Home> findAllHomes() {
        return homeList;
    }

    @Override
    public Optional<Home> getHome(String nombre) {
        return this.homeList.stream().filter(p -> p.nombre().equals(nombre)).findAny();
    }

    @Override
    public Home addHome(Home newHome) {
        this.homeList.add(newHome);
        return newHome;
    }

    @Override
    public void updateHome(String familyName, Home updatedFamily) {
        // Implement the logic to update the home in the file or database
        // ...
    }

    @Override
    public void deleteHome(String familyName) {
        // Implement the logic to delete the home from the file or database
        // ...
    }
}
