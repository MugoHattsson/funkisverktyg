# Funkisverktyg

## Information
Utvecklat för att underlätta arbetet för funktionärsansvarig under en valprocess.

Programmet tar en mapp med bilder och genererar en pdf med 3 stycken bilder per A4-sida. Filnamnet för varje bild skrivs automatiskt under varje respektive bild. Bäst resultat fås med bilder i "porträttformat", dvs högre än dom är breda. Ju närmre en kvadrat bildernas format är desto mer utrymme per sida lämnas tomt under bilden. Mer utrymme är trevligt för att kunna anteckna information tillhörande bilden.
Filtyper som stöds är: png, jpg och jpeg. Filer av annan typ i den valda mappen kommer att ignoreras. 

## Innan användning
Med tanke på mängden bilder som ska behandlas kan det vara bra att innan intervjuprocessen startas ta lite olika bilder och lista ut vad som ger ett najs slutresultat. Detta för att slippa justera alla potentiellt hundratals bilder i efterhand. 

## Körning
Kör programmet från en terminal med kommandot: ```java -jar funkisverktyg-1.X.jar```

Den PDF som skapas sparas i samma mapp som bilderna ligger i. Den har namnet ```output.pdf```.

Vid körning varnar programmet att den inte hittar ett loggnings-bibliotek. Detta kan ignoreras. 

## Kompatibilitet
Java behöver vara installerat på datorn.
- V 1.2 kräver Java 17
- V 1.0-1.1 kräver Java 11(kanske funkar med 17 också) 

## Vidareutveckling
För att bygga projektet till en jar med alla beroenden:

```mvn clean assembly:assembly -f "path/to/workspace/funkis/pom.xml"```