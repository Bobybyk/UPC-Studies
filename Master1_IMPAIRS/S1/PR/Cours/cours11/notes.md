# Cours 11

## TLS (ssl)

Bien dans 99% des cas mais belhek quand même si c'est pour transmettre des trucs top secret. Les autorités de certification c'est sombre en sah.

 Protocoles | Numéro couche
------------|--------------
  HTTP | 7
  TLS |
  TCP | 4

**Garanties :**
- confidentialité
- abscence de rejets
- authentification serveur auprès du client

HTTPS = HTTP au dessus de TLS

**Limitations :**
- client non authentifié (nécessite username/pw)
- seul TCP
- serveur authentifié par certificat

## DNS

 Etape | Numéro couche
---------|-------------
nom d'hôte (de domaine) | 7
DNS |
adresse de socket | 4
IP |
adresse IP | 3
ARP/ND | 
adresse MAC | 2

