# PR - CC, correction (perso)

## A quoi correspond l'adresse IP 127.0.0.1

Cela représente le réseau local (localhost) sur la machine courante

## Ecrivez 355 sur deux octets en big endian (chaque octet sera représenté par un nombre entre 0 et 255)

0000 0001 | 0110 0011

## Quelle est la valeur de l'entier renvoyé par htons(1) (la machine utilise un codage little endian)

0000 0000 | 0000 0001

## Si en C on crée une socket de la forme int sock = socket(PF_INET, SOCK_STREAM, 0), quel est le mode de communication qu'on utilise
SOCK_STREAM (TCP), SOCK_DGRAM (UDP)

Donc on utilise une communication TCP

## Dans quel fichier trouve-t-on les numéros de port associés aux services classiques ?

Ici : /etc/services

## Comment faire, en java, pour que deux méthodes d'un même objet ne puisse pas être executés en parallèle ?

On fait un synchronized sur l'une des 2 méthodes 

## Quelle est la valeur de l'entier renvoyé par la fonction fork ?

Un **pid_t**, soit un entier signé, en gros un **int**

## Quel est le problème principal de ce morceau de code suivant ? (8)

On a pas donné l'IP PTDRRRRRRR

## Quel est le problème principal de ce morceau de code suivant ? (9)

