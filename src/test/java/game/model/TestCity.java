package game.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestCity {

    @Test
    public void test1() {
        City test = new City("Киев");
        Assert.assertEquals("К", test.getFirstSymbol());
        Assert.assertEquals("в", test.getLastSymbol());
        System.out.println(test);

        City two = new City("Амсамбль");
        Assert.assertEquals("А", two.getFirstSymbol());
        Assert.assertEquals("л", two.getLastSymbol());
        System.out.println(two);
    }

}
