package game.ai;

import game.model.City;
import game.model.base.PersonStrategy;
import game.model.base.QualityStrategy;

public class AiTackUtilities {

    public City aiChoseCity(PersonStrategy strategy) {
       return aiChoseCity(strategy, null);
    }

    public City aiChoseCity(PersonStrategy strategy, QualityStrategy quality) {
        return new City();
    }

}
