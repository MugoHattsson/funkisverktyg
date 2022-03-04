# Funkisverktyg

Detta är ett program utvecklat för att underlätta arbetet för funktionärsansvarig under en valprocess. 
Programmet tar en mapp med porträtt-bilder och genererar en pdf med 3 bilder per sida med namnet på filen skrivet under respektive bild. 

Kör programmet med: ``` java -jar funkisverktyg-1.1.jar```

Vid körning varnar programmet att den inte hittar ett loggnings-biblotek. Detta kan ignoreras. 

För att bygga projektet till en jar med alla beroenden:

```mvn clean assembly:assembly -f "path/to/workspace/funkis/pom.xml"```