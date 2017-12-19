import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FirewallTest {

    Firewall fw;

    @Before
    public void setUp() throws Exception {
        fw = new Firewall("./firewall_rules.csv");
    }

    @After
    public void tearDown() throws Exception {
        fw = null;
    }

    @Test
    public void acceptPacketTest() {
        boolean test1 = fw.accept_packet("inbound", "tcp", 80, "192.168.1.2");
        boolean test2 = fw.accept_packet("inbound", "udp", 53, "192.168.2.1");
        boolean test3 = fw.accept_packet("outbound", "tcp", 10234, "192.168.10.11");
        boolean test4 = fw.accept_packet("inbound", "tcp", 81, "192.168.1.2");
        boolean test5 = fw.accept_packet("inbound", "udp", 24, "52.12.48.92");

        assertEquals(true, test1);
        assertEquals(true, test2);
        assertEquals(true, test3);
        assertEquals(false, test4);
        assertEquals(false, test5);

    }
}