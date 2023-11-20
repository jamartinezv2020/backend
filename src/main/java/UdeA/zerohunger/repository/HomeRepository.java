package UdeA.zerohunger.repository;

import UdeA.zerohunger.model.Home;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HomeRepository {
    List<Home> findAllHomes();

    Optional<Home> getHome (String nombre);

    Home addHome(Home newHome);

    void updateHome (String familyName, Home updatedFamily);

    void deleteHome (String familyName);
}
