# Theory Exercise 3 

<p style="text-align : right">Tarek Auel (2917599), Tiange Hu(1595750), Benedikt Christoph Naumann, Markus Schanz</p>

## Task 1.1: Threading

Monitor ist ein Module, der aktuelle Prozedure und Variablen überwachtet. Jeder Objekt in Java hat einen eigenen Monitor.
In der Nebenläufigkeit darf nur ein Thread gleichzeitig den Monitor behalten. Alle andere Thread 
müssen warten, wenn sie momentan keinen Monitor haben. 

## Task 1.2: Transparency in Java RMI
- **Access transparency**: <br />
yes, rmi ist url : rmi://host:port/
- **Location transparency**: <br />
yes, stub und skeleton sind dafür geeignet.
- **Concurrency transparency**: <br />
yes, UnicastRemoteObject.exportObject(server, port) ist wie Endpoint, Benutzer können direkt es verwenden.
- **Replication transparency**: <br />
yes, only Endpoint URL rmi://host:port wurde benutzt.
- **Failure transparency**: <br />
yes, da Replication transparency hilft. 
- **Mobility transparency**:<br />
yes, URL hat sich nicht verändert.
- **Performance transparency**:<br />
yes, im rmi://host:port, nur url ist sichitbar.
- **Scaling transparency**:<br />
yes, Stub und Skeleton Struktur bleibt ohne Veränderung.<br />

## Task 1.3: RMI - single-threaded vs. multi-threaded
- **it is single thread**: <br />
2 * (4 + 0.3 + 4  + 0.3 + 9 + 1 + 4 + 0.3 + 1) = 47.8ms
- **it has two threads**: <br />
4 + 0.3 + 4 + 2 * (0.3 + 9 + 1 + 4) + 1 + 0.3  = 38.2ms
