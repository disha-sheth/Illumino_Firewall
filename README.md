# Firewall Implementation
A piece of Hardware tool or Software program to prevent unauthorised access of the system from hackers or intruders. It also prevents incoming viruses and worms from the internet. The file *Firewall.java* implements basic firewall that checks all the incoming traffic with the *firewall_rules.csv* and returns true only if the **port** and **ip address** of incoming traffic matches the rules in file.

## Tools and Technologies
1. IDE: Intellij
2. Runtime: Java 8
3. Unit Testing: JUnit

## Implementation
All the valid rules are loaded in the HashMap by reading lines from from *firewall_rules.csv*. HashMap is used because of it's quick lookup capability **(O(1))** in average case and **O(log n)** for worst case since Java 8 uses **Tree** to resolve collision in HashMap. To optimize the space complexity of the program, I created a class **Firewall** which stored all the required details of the Network Rule from the **security_rule.csv** in the form of FirewallWall object. This digests the data in the object of FirewallRule into a single hash value. 

## Testing
JUnit is used to test the implementation. Input rule is given and tested if the function returns true or false. If the rule matches the rules defined in Map, the packet is accepted.

## Possible Refinements
Given more time, I would have tested the code more rigorously. I could have further optimised the performance by trying various other data structures that could have better performed in terms of space complexity. Also, if the number of entries increases beyond certain order, it could have filled the buffer. Hence, disk store options like SQLite or caching could have been used. 

## Team
With the information I have for each team, I would love to work for platform team beacause it involves backend and API related domains. I would even like to work with other teams.  
