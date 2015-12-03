# Theory Exercise 2

<p style="text-align: right">Tarek Auel (2917599), Tiange Hu, Benedikt Christoph Naumann, Markus Schanz</p>

## Task 1.1: RPC Failure Semantics
- Remote Procedure Calls brauchen eine eindeutige ID:  
Diese ID wird vom Client für einen Methodenaufruf eindeutig vergeben und bei jeder Anfrage an den Server mitgesendet. Im
Idealfall findet nur einmalig eine Anfrage statt. Im Falle eines Fehlers jedoch (z.B. Server antwortet nicht innerhalb
von definierter Zeitspanne) muss eine erneute Anfrage an den Server erfolgen. Dieser kann jetzt anhand der ID erkennen
ob er die Prozedur bereits schon aufgeführt hat oder nicht. Insbesondere kann der Client doppelte Antworten verwerfen.

- Serverseitiger Puffer zur Zwischenspeicherung von Prozedurergebnissen:  
Für den Fall das der Client erneut eine Anfrage für den selben Prozeduraufruf an den Server schickt (z.B. weil die
letzte Antwort nicht rechtzeitig ankam), muss der Server die Möglichkeit haben das Ergebnis des Prozeduraufrufes an den
Client zu schicken ohne die Methode noch einmal auszuführen.

- Client sollte Empfang von Antworten bestätigen (entweder implizit über darunterliegende Protokolle, oder explizit):  
Damit der Server die Zwischenergebnisse sicher löschen kann muss er sichergehen dass der Client die Antwort empfangen
hat. Alternativ könnten die Zwischenergebnisse auch nach einer vorgegebenen Zeitspanne gelöscht werden, wenn davon
ausgegangen werden kann dass es zu keiner erneuten Anfrage mehr kommt.

## Task 1.2: Marshalling
**Aufgabenteil a)**:

Marshalling beschreibt eine Darstellung von Datenstrukturen (z.B. Instanz einer Klasse) die unabhängig vom
Betriebssystem oder der Computerplatform ist. Das ist notwendig, um diese Datenstrukturen (z.B. über ein Netzwerk) an
andere Systeme zu senden, welche dann die Datenstrukturen nutzen können um das ursprüngliche Objekt (-> Instanz der Klasse)
wieder herzustellen. Beim Marshalling werden neben den Werten der einzelnen Felder auch strukturinformationen gespeichert.

**Aufgabenteil b)**:

Bei der CORBA Common Data Representation (CDR) kommt eine "Interface Definition Language" (IDL) zum Einsatz. In
diesem Format können Interfaces (wie z.B. Methodensignaturen) und komplexe Datenstrukturen (wie z.B. Klassen)
beschrieben werden.  
***Vorteile:***  
Sofern dem Empfänger eines serialisierten Datensatzes also klar ist um welches Interface bzw. um welche Datenstruktur es
sich handelt, muss der Datensatz selber keine weiteren Informationen darüber mitführen (-> Schnellere Übertragung über
Netzwerke, da keien Strukturinformationen übertragen werden; Geringerer Aufwand des Parsens beim Empfang, da die Struktur
nicht analysiert werden muss und zum Beispiel direkt in den Speicher der Zielstruktur kopiert werden kann). Zudem ist
der Empfänger selbst dafür zuständig die Daten in eine Darstellung zu überführen, die mit der Architektur des Systems in 
Einklang steht, z.B. Darstellung im Big Endian Format.  
***Nachteile:***  
Der Empfänger benötigt die Schnittstellenbeschreibungen in Form eines IDL-Formates. Hat er diese nicht, so kann er die
empfangenen Daten nicht sinnvoll verwenden. Eine Versionierung der Datenstrukturen muss selbst gelöst werden, da keine 
Strukturinformationen vorhanden sind. Gleiches gilt, wenn man Nachrichten archivieren möchte - denn dann muss die
entsprechende IDL-Struktur vorgehalten werden.

## Task 1.3: Request-Reply Protocol
**Aufgabenteil a)**:  
* Wie bereits in Aufgabe 1.1 verdeutlicht, kann der Server bei dem Empfang eines ACK ggf. "Aufräumarbeiten" ausführen
(-> Wegwerfen von zwischengespeicherten Ergebnissen). Ohne ACK müssen die Zwischenergebnisse ggf. unnötig lange
vorgehalten werden, ehe sie gelöscht werden können, was zu einem erhöhten Speicherverbrauch führt und der Server deshalb
ggf. schneller ausgelastet ist.

* Ein Nachteil ist, dass eine zusätzliche Nachricht gesendet werden muss, die möglicherweise durch darunter liegende
Technologien (z.B. TCP) eine Antwort beinhaltet. Sollte das Netzwerk sehr instabil sein und Speicher beim Server keine
knappe Ressource, kann es daher durchaus von Vorteil sein RR zu verwenden.

**Aufgabenteil b)**:  
Bleibt ein ACK aus, bedeutet das nicht zwangsläufig, dass die letzte Antwort des Servers nicht beim Client angekommen ist.
Genausogut kann es sein, dass das ACK zwar losgeschickt wurde, jedoch nie ankam. Bleibt ein ACK also aus, weil der Client
die letzte Antwort nicht bekommen hat, so wird er ohnehin in Kürze eine neue Anfrage an den Server senden. Bleibt das
ACK aus, weil es den Server nicht erreicht hat, braucht die Antwort auch nicht noch einmal verschickt zu werden. Unter
diesem Gesichtspunkt macht es also durchaus Sinn auf der erneute versenden einer Antwort zu verzichten. In diesem Fall
muss das gespeicherte Zwischenergebnis (im Falle einer At-Most-Once Semantik) nach einem Timeout gelöscht werden. Ein weiterer
Vorteil liegt darin, dass der Client sich sonst möglicherweise ebenfalls etwas merken muss, um auf das ACK entsprechend 
reagieren zu können. Sonst kann er nicht unterscheiden zwischen: Er hat die Anfrage nicht gestellt und er hat sie verarbeitet,
kann sich aber nicht erinnern. Erster Fall könnte auftreten, wenn ein Client crasht, die IP neu vergeben wird und die 
Kommunikation beispielsweise auf UDP basiert.
