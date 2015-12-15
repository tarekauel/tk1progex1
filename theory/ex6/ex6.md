# Theory Exercise 6

<p align="right">Tarek Auel, Tiange Hu, Benedikt Christoph Naumann, Markus Schanz</p>

## Task 1: Routing with Advertisements

### Task 1.1: Publisher P sends an advertisement to router 1
* Step 1
  * (Advertisment of P is stored internally in router 1)
  * Router 1 forwards the advertisement to all neighbors
    * Forward advertisement to Router 3
* Step 2
  * Router 3 adds the advertisement to its T<sub>A</sub> table.
  * Router 3 forwards the advertisement to all neighbors,except the origin
    * Forward advertisement to Router 2
    * Forward advertisement to Router 4
* Step 3
  * Router 2 adds the advertisement to its T<sub>A</sub> table.
  * Router 2 forwards the advertisement to all neighbors, except the origin
    * No forwarding
* Step 4
  * Router 4 adds the advertisement to its T<sub>A</sub> table.
  * Router 4 forwards the advertisement to all neighbors, except the origin
    * Forward advertisement to Router 5
    * Forward advertisement to Router 6
* Step 5
  * Router 5 adds the advertisement to its T<sub>A</sub> table.
  * Router 5 forwards the advertisement to all neighbors, except the origin
    * No forwarding
* Step 6
  * Router 6 adds the advertisement to its T<sub>A</sub> table.
  * Router 6 forwards the advertisement to all neighbors, except the origin
    * No forwarding

Steps **3 and 4**, as well as steps **5 and 6** may occur in arbitrary order.

Flow of advertisement forwardings:
![Advertisement forwarding][advertisement_flow]

T<sub>A</sub> tables of routers after advertisement was forwarded through the network:
<table>
    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>A</sub> (Router 1)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>A</sub> (Router 2)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>3</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>A</sub> (Router 3)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>A</sub> (Router 4)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>3</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>A</sub> (Router 5)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>4</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>A</sub> (Router 6)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>4</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
    </tr>
</table>

### Task 1.2: Subscriptions
#### Subscriber S1 sends a subscription to router 5.
* Step 1.1:
  * (Subscription of S1 is stored internally in router 5)
  * Lookup matching (D, F) pairs in T<sub>A</sub>: Found pair (4, F)
    * Forward subscription to 4.
* Step 1.2:
  * Router 4 adds the subscription to its T<sub>S</sub> table.
  * Lookup matching (D, F) pairs in T<sub>A</sub>: Found pair (3, F)
    * Forward subscription to 3.
* Step 1.3:
  * Router 3 adds the subscription to its T<sub>S</sub> table.
  * Lookup matching (D, F) pairs in T<sub>A</sub>: Found pair (1, F)
    * Forward subscription to 1.
* Step 1.4:
  * Router 1 adds the subscription to its T<sub>S</sub> table.
  * Lookup matching (D, F) pairs in T<sub>A</sub>: None found
    * No further forwarding.

Flow of S1 subscription forwardings:
![Subscription S1 forwarding][subscription_flow1]

T<sub>S</sub> tables of routers after subscription of S1 was forwarded through the network:
<table>
    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 1)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>3</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 2)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 3)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>4</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 4)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>5</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 5)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 6)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </table>
        </td>
    </tr>
</table>

#### S2 sends a subscription to router 6
* Step 2.1:
  * (Subscription of S2 is stored internally in router 6)
  * Lookup (D, F) pairs in T<sub>A</sub>: Found pair (4, F)
    * Forward subscription to 4.
* Step 2.2:
  * Router 4 adds the subscription to its T<sub>S</sub> table.
  * Lookup (D, F) pairs in T<sub>A</sub>: Found pair (3, F)
    * Forward subscription to 3.
* Step 2.3:
  * Router 3 already has the subscription in its T<sub>S</sub> table
    * No forwarding required

Flow of S2 subscription forwardings:
![Subscription S2 forwarding][subscription_flow2]

T<sub>S</sub> tables of routers after subscription of S1 was forwarded through the network:
<table>
    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 1)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>3</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 2)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 3)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>4</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 4)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>5</td>
                    <td>F</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>F</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 5)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <th colspan="2">T<sub>S</sub> (Router 6)</th>
                </tr>
                <tr>
                    <th>Destination</th>
                    <th>Filter</th>
                </tr>
                <tr>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </table>
        </td>
    </tr>
</table>

### Task 1.3: Publisher P sends a notification to router 1
* Step 1
  * Router 1: Lookup matching (D, F) pairs in T<sub>S</sub> table: Found pair (3, F)
    * Forward notification to router 3
* Step 2
  * Router 3: Lookup matching (D, F) pairs in T<sub>S</sub> table: Found pair (4, F)
    * Forward notification to router 4
* Step 3
  * Router 4: Lookup matching (D, F) pairs in T<sub>S</sub> table: Found pairs (5, F), (6, F)
    * Forward notification to router 5
    * Forward notification to router 6
* Step 4
  * Router 5: Lookup matching (D, F) pairs in T<sub>S</sub> table: Found none
    * No further forward to brokers
    * Forward notification to client S1
* Step 5
  * Router 6: Lookup matching (D, F) pairs in T<sub>S</sub> table: Found none
    * No further forward to brokers
    * Forward notification to client S2

Steps **4 and 5** may occur in arbitrary order.

Flow of notification forwardings:
![Notification flow][notification_flow]

[advertisement_flow]: graphs/advertisement.png "Advertisement flow"
[subscription_flow1]: graphs/subscriptionS1.png "S1 subscription flow"
[subscription_flow2]: graphs/subscriptionS2.png "S2 subscription flow"
[notification_flow]: graphs/notification.png "Notification flow"

## Task 2: Routing in Publish/Subscribe Systems
### Task 2.1
<table>
    <tr>
        <th>&nbsp;</th>
        <th>Pros</th>
        <th>Cons</th>
    </tr>
    <tr>
        <td><strong>Routing with Subscriptions</strong></td>
        <td>
            <ul>
                <li>Brokers must not maintain a seperate table for advertisements</li>
                <li>Publisher can send arbitary notifications</li>
            </ul>
        </td>
        <td>
            <ul>
                <li>Subscriptions are forwarded througout the whole network (Flooding)</li>
                <li>Routing tabes for subscriptions are complex to manage as they are used for potentially a lot of subscriptions.</li>
            </ul>
        </td>
    </tr>
    <tr>
        <td><strong>Routing with Advertisements</strong></td>
        <td>
            <ul>
                <li>More efficient routing of subscriptions. They are only forwarded to destinations where those type of notifications are produced.</li>
            </ul>
        </td>
        <td>
            <ul>
                <li>Publishers should know in advance what type of notifications they are producing</li>
            </ul>
        </td>
    </tr>
</table>

### Task 2.2
In general, "Routing with Advertisements" is more suiteable in applications where the producers know the content/type of the notifications they are producing in advance. This mechanism makes sense in appliations that have a wide variety of message types.

In turn "Routing with Subscriptions" is more suiteable in networks where publishers, in general, dont know the type of messages that they are producing in advance. Another scenario where this type of routing makes sense is when pubishers producing a lot of distinct messages, i.e. the messages are usally covered by a distinct filters/subscriptions.
