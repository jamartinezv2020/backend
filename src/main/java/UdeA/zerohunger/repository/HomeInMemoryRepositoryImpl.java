package UdeA.zerohunger.repository;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import UdeA.zerohunger.model.Home;

public class HomeInMemoryRepositoryImpl implements HomeRepository {

    private static final Logger logger = LoggerFactory.getLogger( HomeInMemoryRepositoryImpl.class);

    private List<Home> homeList;

    public HomeInMemoryRepositoryImpl() {
        this.homeList = new ArrayList<>(loadHomes());//Al momento de construir el Repository se cargan los datos "quemados" en la clase
    }
    private List<Home> loadHomes(){
        logger.info( "Cargando los datos predefinidos " );
        return List.of(
                new Home (LocalDate.of(2023, Month.APRIL, 12), "JOSE ALFREDO MARTINEZ VALDES", "11", "Padre y Madre", 800000.0D, 3, 2, 2),
                new Home (LocalDate.of(2022, Month.OCTOBER, 11), "CARMEN CECILIA IJAJÍ CADAVID", "10", "Padre y Madre", 1600000.0D, 7, 5, 4),
                new Home (LocalDate.of(2023, Month.AUGUST, 1), "JORGE LUIS PINTO", "10", "Padre", 1130000.0D, 2, 5, 3),
                new Home (LocalDate.of(2023, Month.FEBRUARY, 21), "VICKY DAVILA", "11", "Padre", 850000.0D, 2, 8, 3),
                new Home (LocalDate.of(2021, Month.OCTOBER, 17), "JOSE ALFREDO MARTINEZ VALDES", "10", "Padre y Madre", 5800000.0D, 2, 4, 2),
                new Home (LocalDate.of(2023, Month.SEPTEMBER, 19), "JOSE ALFREDO MARTINEZ VALDES", "8", "Madre", 820000.0D, 2, 7, 1),
                new Home (LocalDate.of(2023, Month.JULY, 12), "JOSE ALFREDO MARTINEZ VALDES", "8", "Otros", 1856000.0D, 2, 4, 5),
                new Home (LocalDate.of(2023, Month.JUNE, 5), "JOSE ALFREDO MARTINEZ VALDES", "11", "Abuelos", 1250000.0D, 2, 2, 30000000));


    }


    @Override
    public List<Home> findAllHomes () {

        return homeList;
    }

       @Override
    public Optional<Home> getHome (String nombre) {
        return this.homeList.stream().filter( p->p.nombre ().equals( nombre ) ).findAny();
    }

    @Override
    public Home addHome (Home newHome) {
        this.homeList.add( newHome ) ;
    return this.homeList.stream()
                .filter( isTheHomeOfTheName( newHome ) )//Busca la nota en la lista que corresponda al proyecto de la nota recien creada
                .findAny()
                .orElse( null );//Si no la encuentra devuelve nulo
    }

    @Override
    public void updateHome (String familyName, Home updatedFamily) {

    }

    @Override
    public void deleteHome (String familyName) {

    }


    private Predicate<Home> isTheHomeOfTheName(Home newHome) {
        return p -> p.nombre ().equals( newHome.nombre ());
    }


}
