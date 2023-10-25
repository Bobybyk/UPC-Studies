# Cours 10

## Entrées / Sorties

**Processeur ---> *(protocoles/interface)* ---> Périphériques :**
- GPU
- Disques durs, SSD
- Cartes réseay
- Clavier, souris
- Moteur

**Interfaces :**
- PCI Express
- SPI
- SATA, IDE
- USB

**LC-3 :** 
- 3 périphériques : 
    - écran 
    - clavier
    - minuteur (timer)

On accède au périphériques :
- via des instructions dédiées :
    - Zilog Z80 (processeur 8 bits, 1974)
    - (Amstrad CPC...)
    - IN port, REG
    - OUT port, REG
- via l'espace mémoire

Adresse | Registre E/S | Fonction
--------|--------------|---------
xFE00 | Keyboard status KBSR | Bit de poids fort indique qu'un nouveau caractère peut être lu
xFE02 | Keyboard data (KBDR) | Huit bits de poids faible, le dernier caractère tapé

```asm
getc:
    LDI R0, KBSR_A
    BRzp getc
    LDI R0, KBDR_A
    RET
KBSR_A: 
    .FILL xFE00
KBDR_A: 
    .FILL xFE02
    ...
    JSR getc
    JSR get2

putc: 
    STI R0, DDR_A
putc0: 
    LDI R0, DSR_A
    BRzp putc0
    RET
DDR_A: 
    .FILL xFE06
DSR_A: 
    .FILL xFE04

loop: 
    JSR getc
    JSR putc
    BR loop
```
Pour gérer des événements (périphériques...) :
- Attente active par scrutation ou "polling" (boucle infinie de check)
- Interruptions : déroute de flot de contrôler vers une routine d'interruption
    - chaque interruption a une priorité fixée qui est un entier sur bits
    - une routine d'interruption peut à son tour être interrompue, mais seulement par une interruption  de priorité strictement supérieure

Adresses | Contenu
---------|---------
x0100 | Table des appels système
x01FF | Table des interruptions
... | ...
xFE00 | ...

---

## Mémoires cache

**Localité :**
- spaciale
- temporelle

**Un mécanisme transparent pour le programme :**
- Cache HIT (succès de cache) : le processeur demande la donnée à une adresse l, le cache a cette donnée.
- Cache MISS (défaut de cache) : 
    - le cache n'a pas la donnée, il doit la demander à la mémoire principale.
    - trop de données dans le cache
    - pas assez de place dans le cache

**Classification des caches :**
- associativité
- politique d'éviction
    - LRU : la plus ancienne à avoir été utilisé
    - LFU : la plus récente
    - pseudo LRU : toutes sauf la plus récente à avoir été utilisé
- taille de la ligne
- politique d'écriture
    - write-through
    - write-back

Cache direct (1-associatif)

Exemple : 980AB2A

Index | Dirty | Tag | Data (hex)
------|-------|-----|------
00
01
02
...
B1
B2 | ? | 980A | Data[A]
B3
...
FF

Index : 8 bits

Data : 0123456789ABCDEF