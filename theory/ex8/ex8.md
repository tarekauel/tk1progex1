# Theory Exercise 8

<p align="right">Tarek Auel, Markus Schanz</p>

## Task 1: Correctness in distributed Systems

## Task 2: Time synchronization using the algorithm by Christian
a) Which entry in the depicted table should C select to set its local clock?  
In order to get the best accuracy, C should choose the second entry, with a round-trip time of 18ms.

b) How accurate is the time estimate of C in relation to S?  
The time estimate of C (see next lines) has an accuracy of `±RTT/2 = ±18/2 = ±9ms`,

c) Which time should C set to its local clock?  
To the time it received from S, increased by half of the duration of the RTT: `newtime = 15:38:36.580 + 0.009 = 15:38:36.589`

d) The transmission of a message between C and S takes at least 4ms (for each direction). How does this change the answers for a-c?  
A minimum transmission delay of 4ms implies...:

- ... no change of the answer in a)
- ... a smaller error (=> higher accuracy): `accuracy = ±0.5 * (RTT - 2 * min_trans_delay) = ±0.5 * (18 - 8) = ±5ms`
- ... no change of the answer in c)

## Task 3: Time synchronization using NTP
