Gruppe 1

Start: 04. April 2019

Treffen: 10. April 2019

# **RSA_SEI_multithreaded**

## Dokumentation zum RSA-Verfahren

## Inhaltsverzeichnis
* GitHub Repository nach IntelliJ importieren
* Wie funktioniert der RSA Algorithmus?
* Wichtige Berechnungsformeln

## Wie importiere ich ein GitHub Repository in IntelliJ?
* URL des GitHub Repositories kopieren
* IntelliJ öffnen
* Oben links auf den Button "File"
* -> New...
* -> Object from Version Control
* -> URL einfügen & Speicherort auswählen

## Wie funktioniert der RSA Algorithmus?
* Der Benutzer muss zu Beginn zwei Primzahlen eingeben (p,q)
* Die Eingaben werden in der Methode "isPrime" getestet
* Danach muss der Benutzer ein passendes E eingeben
* Die Eingabe wird in der Methode "testE" getestet, E muss die Bedingung erfüllen, dass das ggT(Φ,E) = 1 ist
* Danach wird das inverse Element von Φ und E berechnet
* Das inverse Element wird dem Integer "d" zugeordnet
* Benutzer muss die zu verschlüsselnde Nachricht "M" eingeben
* Die Nachricht "M" wird durch fünf geteilt und einzeln mittels des Kpub(n,E) verschlüsselt
* Die verschlüsselten Worte werden mittels des Kpriv(d,n) entschlüsselt
* Die Zahlenfolgen werden in Zeichenfolgen umgewandelt
* Die Nachricht "M" ist nun wieder lesbar

## Wichtige Berechnungsformeln
Phi = (p-1) * (q-1)
N = p * q
Verschlüsseln:
Entschlüsseln:
