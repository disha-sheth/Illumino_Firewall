import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Firewall {

    static HashMap<Long, Boolean> map = new HashMap<Long, Boolean>();

    public Firewall(String file_path) {
        try {
            //read file containing firewall rules
            FileReader fileReader = new FileReader(file_path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] rule = line.split(",");
                if (rule[2].contains("-")) {
                    String[] portRanges = rule[2].split("-");
                    int portMin = Integer.parseInt(portRanges[0]);
                    int portMax = Integer.parseInt(portRanges[1]);
                    int portRange = portMax - portMin;
                    if (rule[3].contains("-")) {
                        //rule contains range of ports and range of IPs
                        String[] ipAddressRanges = rule[3].split("-");
                        long ipAddressMin = Long.parseLong(ipAddressRanges[0].replaceAll("\\.", ""));
                        long ipAddressMax = Long.parseLong(ipAddressRanges[1].replaceAll("\\.", ""));
                        long ipRange = ipAddressMax - ipAddressMin;

                        for (int i = 0; i <= portRange; i++) {
                            for (int j = 0; j <= ipRange; j++) {
                                FirewallRule currRule = new FirewallRule(rule[0], rule[1], portMin + i, ipAddressMin + j);
                                map.put(currRule.hashCode, Boolean.TRUE);
                            }
                        }
                    } else {
                        //rule contains range of ports and fixed IP
                        for (int i = 0; i <= portRange; i++) {
                            FirewallRule currRule = new FirewallRule(rule[0], rule[1], portMin + i, Long.parseLong(rule[3].replaceAll("\\.", "")));
                            map.put(currRule.hashCode, Boolean.TRUE);
                        }
                    }
                } else {
                    if (rule[3].contains("-")) {
                        //rule contains fixed port and range of IPs
                        String[] ipAddressRanges = rule[3].split("-");
                        long ipAddressMin = Long.parseLong(ipAddressRanges[0].replaceAll("\\.", ""));
                        long ipAddressMax = Long.parseLong(ipAddressRanges[1].replaceAll("\\.", ""));
                        long ipRange = ipAddressMax - ipAddressMin;

                        for (int i = 0; i <= ipRange; i++) {
                            FirewallRule currRule = new FirewallRule(rule[0], rule[1], Integer.parseInt(rule[2]), ipAddressMin + i);
                            map.put(currRule.hashCode, Boolean.TRUE);
                        }
                    } else {
                        //rule contains fixed port and fixed IP
                        FirewallRule currRule = new FirewallRule(rule[0], rule[1], Integer.parseInt(rule[2]), Long.parseLong(rule[3].replaceAll("\\.", "")));
                        map.put(currRule.hashCode, Boolean.TRUE);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred \n" + e.getMessage());
        }
    }

    boolean accept_packet(String direction, String protocol, int port, String ipAddress) {
        FirewallRule rule = new FirewallRule(direction, protocol, port, Long.parseLong(ipAddress.replaceAll("\\.", "")));
        return map.containsKey(rule.hashCode) ? true : false;
    }

}
