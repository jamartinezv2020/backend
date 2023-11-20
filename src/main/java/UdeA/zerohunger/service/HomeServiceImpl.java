package UdeA.zerohunger.service;

import java.util.List;
import java.util.Optional;
import UdeA.zerohunger.exception.HomeHogarNotFoundException;
import UdeA.zerohunger.model.Home;

import UdeA.zerohunger.repository.HomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    private final HomeRepository homeRepository;
    private static final Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    public HomeServiceImpl(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public Double calculateAverage() {
        logger.info("Calculating average salary");
        List<Home> homeList = this.homeRepository.findAllHomes();
        return homeList.stream().mapToDouble(Home::salario).average().orElse(0.0);
    }

    @Override
    public Integer sumNumberOfHomes() {
        logger.info("Summing the number of homes");
        List<Home> homeList = this.homeRepository.findAllHomes();
        return homeList.size();
    }

    @Override
    public Home getHome(String nombre) {
        return this.homeRepository.getHome(nombre)
                .orElseThrow(() -> new HomeHogarNotFoundException(nombre));
    }

    @Override
    public List<Home> listAllHomes() {
        return this.homeRepository.findAllHomes();
    }

    @Override
    public Home addHome(Home newHome) {
        return this.homeRepository.addHome(newHome);
    }
}

