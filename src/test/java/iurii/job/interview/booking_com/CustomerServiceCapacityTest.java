package iurii.job.interview.booking_com;

import iurii.job.interview.utils.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by iurii.dziuban on 06/06/2017.
 */
public class CustomerServiceCapacityTest {

    private CustomerServiceCapacity serviceCapacity = new CustomerServiceCapacity();

    @Test
    public void testSimple() {
        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(1481122000, 1481122020));
        pairs.add(new Pair(1481122000, 1481122040));
        pairs.add(new Pair(1481122030, 1481122035));

        assertThat(serviceCapacity.findNeededNumberOfEmployers(1,pairs)).isEqualTo(1);
    }

    @Test
    public void testOne() {
        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(1481122000, 1481122020));
        pairs.add(new Pair(1481122020, 1481122040));
        pairs.add(new Pair(1481122040, 1481122050));

        assertThat(serviceCapacity.findNeededNumberOfEmployers(1,pairs)).isEqualTo(0);
    }

    @Test
    public void testNegative() {
        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(1481122000, 1481122025));
        pairs.add(new Pair(1481122030, 1481122045));
        pairs.add(new Pair(1481122050, 1481122070));

        assertThat(serviceCapacity.findNeededNumberOfEmployers(2,pairs)).isEqualTo(0);
    }
}
