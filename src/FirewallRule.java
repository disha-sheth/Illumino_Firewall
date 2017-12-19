public class FirewallRule {
    private String direction;
    private String protocol;
    private int port;
    private long ipAddress;
    long hashCode;

    public FirewallRule(String direction, String protocol, int port, long ipAddress) {
        this.direction = direction;
        this.protocol = protocol;
        this.port = port;
        this.ipAddress = ipAddress;
        this.hashCode = hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FirewallRule)) return false;
        FirewallRule firewallRule = (FirewallRule) o;
        return direction.equals(firewallRule.direction) && protocol.equals(firewallRule.protocol) && port == firewallRule.port && ipAddress == firewallRule.ipAddress;
    }

    @Override
    public String toString() {
        return this.direction + ", " + this.protocol + ", " + Integer.toString(this.port) + ", " + Long.toString(this.ipAddress);
    }


    public int hashCode() {
        long hash = (this.ipAddress + this.port + this.direction.hashCode() + this.protocol.hashCode()); //get unique key from all the components
        return Long.valueOf(hash).hashCode();
    }

}