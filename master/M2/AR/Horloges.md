# Horloges

## Horloges physiques

- Cadancé par un mécanisme physique
- Notations :
    - Un noeud qui lit lʼheure au temps obtiendra la valeur Hi(t)
    - Si l'horloge est parfaite Hi(t) = t

## Horloges systèmes réparties

- Chaque noeud i a sa propre horloge Hi
- Lorsque le noeud i veut synchroniser son horloge sur lʼhorloge du noeud j, il envoie un message <HORLOGE> à j.
- Lorsque j reçoit ce message, il renvoie le message <HORLOGE, Hj(t)> à i
- Lorsque i reçoit ce message, i donne à son horloge à l’heure suivante
Hi(t)=Hj(t)+(max+min)/2

Ici, min et max correspondent respectivement à une borne inférieure et supérieure. 
Un ordre causal est un ordre partiel sur les événements. (max+min/2) est une approximation du temps d'envoie du message à i.

## Algorithme de Lamport

- Chaque processus du systyème possède sa propre horloge logique (elle mesure l'avancement dans la séquence d'événements)
- Chaque événement est noté par un couple (date, identifiant du processus), à chaque fois qu'un événement est déclenché, l'horloge logique est incrémentée.
- Lorsqu'un processus reçoit un message, il met à jour son horloge logique en prenant le maximum entre sa propre horloge et celle du message.

Si on note L(e) lʼétiquette dʼun événement e alors:  
 - e -> e'L(e) < L(e')

si e est un événement interne alors faire
- hi := hi + 1 et L(e) = hi

Si e est lʼenvoie dʼun message M alors faire
- hi := hi + 1 et L(e) = hi

On envoie le message M avec lʼestampille L(e)
- Envoyer(<M,hi>)

Si e est la réception dʼun message Recevoir(<M,h>) alors faire 
- hi := max(h,hi) + 1 et L(e) = hi