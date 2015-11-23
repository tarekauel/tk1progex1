# Theory Exercise 2

<p style="text-align: right">Tarek Auel (2917599), Tiange Hu, Benedikt Christoph Naumann, Markus Schanz</p>

## Task 1.1: Threading
Ein Monitor ist ein Konzept zur Synchronisierung, das einen gegenseitigen Ausschluss erlaubt. Ein
Monitor erlaubt, dass genau ein oder kein Thread sich zur gleichen Zeit im kritischen Codebereich
befindet. Befindet sich ein Thread im vom Montior geschützten Bereich, müssen alle anderen, die 
ebenfalls einen durch den Monitor geschützen Bereich betreten wollen, warten, bis der erste Prozess
den kritischen Bereich verlassen hat. Ein Monitor besteht aus Variablen und Prozeduren, die die 
Synchronization ermöglichen. Zu schützende Variablen werden nur durch den Monitor geschütze Methoden
 verändert. Dies macht ein Auftreten von raice-conditions unmöglich.

## Task 1.2: Transparency in Java RMI
* :white_check_mark: Access Transparency: is gegeben. Lokale und entferne Objekte, die in der
Registery angemeldet wurden, werden über diese auf die gleiche Art und Weise angesprochen.
* :white_check_mark: Location Transparency ist gegeben. Objekte werden in der Registery mit einem
beliebigen Namen angemeldet. Dieser kann rein funktional sein.
* :negative_squared_cross_mark: Concurrency Transparency ist nicht von Java RMI gegeben. Die
Ressourcen müssen dies selbst implementieren.
* :negative_squared_cross_mark: Replication Transparency ist nicht gegeben. Jedem Namen (logisch
eine Resource) kann genau ein Objekt zugeordnet werden.
* :negative_squared_cross_mark: Failure Transparency ist nicht gegeben. Die Registry ist
beispielsweise ein Single-Point-Of-Failure. Es sind auch keine anderen Methoden vorhanden, die
Ausfälle kompensieren können
* :negative_squared_cross_mark: Mobility transparency ist nicht gegeben. Zwar können Namen in der
Registry neu vergeben werden, allerdings bleiben bestehende Verbindungen davon unberührt.
* :negative_squared_cross_mark: Performance transparency ist nicht gegeben. Der Zugriff auf eine
lokale Ressource kann deutlich schneller sein. Außerdem sind keine Mechanismen implementiert, die 
eine Rekonfiguration zur Performancesteigerung erlauben.
* :white_check_mark: Scaling transparency ist gegeben, allerdings nur für vertikale
Skalierung, nicht für horizontales skalieren.

## Task 1.3: RMI - single-threaded vs multi-threaded
a)
One request:
<p style="font-family: Consolas">
| -------- Client --------|--- N ---|----------------- Server ------------------|---- N ----|------ Client -----<br />
[4ms + 1ms + 0.3ms] + [4ms] + [0.3ms + 1ms + 9ms + 1ms + 0.3ms] + [4ms] + [0.3ms + 1ms]
</p>
Steps:
- calculate arguments
- marshalling
- send
- network transfer
- receive
- demarshalling
- server processing
- marshalling
- send
- network
- receive
- marshalling

Grouped: 
- Client until send (incl.): 5.3ms
- Network 4ms
- Server processing time: 11.6ms
- Network 4ms 
- Client receiving result: 1.3ms


Second requests starts at t=5.3ms
- at server at: 5.3ms + 5.3ms + 4ms = 14.6
- server busy until 5.3ms + 4ms + 11.6ms = 20.9ms
- second request has to wait 6.3ms
- 1. request finished at: 5.3ms + 4ms + 11.6ms + 4ms + 1.3ms = 26.2
- 2. request finished at: 5.3ms + 6.3ms + 26.2 = 37.8

b)
- both requests sent at 5.3ms
- both requests received at 9.3ms
- server processes first request until 20.9ms
- network 1st request + client gets first result
- server processes second request until 32.4ms
- network 2nd request 4ms, client 1.3ms --> 32.4ms + 4ms + 1.3ms = 37.7
