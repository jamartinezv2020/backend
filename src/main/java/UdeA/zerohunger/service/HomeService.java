package UdeA.zerohunger.service;
import java.time.LocalDate;
import java.util.List;
import UdeA.zerohunger.exception.HomeNotFoundException;
import UdeA.zerohunger.model.Home;


public interface HomeService {

    Double calculateAverage();

    Integer sumNumberOfHomes();



    Home getHome (String nombre) throws HomeNotFoundException;

    List<Home> listAllHomes();

    Home addHome(Home newHome);
}
