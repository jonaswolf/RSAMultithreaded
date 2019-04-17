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
* Oben links auf den Button "File" klicken
* -> New...
* -> Object from Version Control
* -> URL einfügen & Speicherort auswählen

## Wie benutze ich Git Bash richtig, um das Projekt zu synchronisieren?
**Voraussetzungen**: Git Bash muss installiert und eingerichtet sein
* Lokalen Speicherort aussuchen, an dem das Projekt gespeichert werden soll
* Rechtsklick innerhalb des Ordners
* Klicken auf: "Git Bash Here"
* **Bestehendes Repository an den lokalen Speicherort kopieren**:
* `git clone URL` -> ("URL" wird durch die URL des Repositories ersetzt)
* **Aktualisierungen auf Git hochladen**:
* `git add FOLDER_NAME` -> ("FOLDER_NAME" wird durch den Namen des Ordners ersetzt, welcher die Aktualisierungen enthält)
* `git commit -m "COMMIT_NAME"` -> ("COMMIT_NAME" wird ersetzt durch den Namen der Aktualisierung, bspw. "Fixed Bugs")
* `git push origin master`
* **Neueste Version des Repositories runterladen**:
* `git pull`

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
* Phi = (p-1) * (q-1)
* N = p * q
* d = Inverses Element(Φ,E) + Phi
* Verschlüsseln: M^e mod(n)
* Public Key: Kpub (e,n)
* Entschlüsseln: M^d mod(n)
* Private Key: Kpriv (d,n)

## ToDo`s
* [x] Benutzereingaben
* [x] Primzahlentest
* [x] Phi, N berechnen
* [x] E, N testen
* [x] ggT und Inverses Element berechnen
* [x] Verschlüsseln
* [ ] Entschlüsseln
* [x] Parallelisieren
* [x] Randomizer Methode
* [ ] User Interface
* [ ] Verschlüsselte Nachricht so abspeichern (.txt), dass man sie auf einem anderen PC entschlüsseln kann
* [ ] dazu: Verschlüsselten Code als .txt abspeichern (mit d, N)
* [ ] dazu: Import und Entschlüsselung der Daten aus der .txt Datei
* [ ] Neben Verschlüsselungstextfeld: "Copy to Clipboard" - Button
* [ ] Zusammenführung aller Teilaufgaben

**Hinweis**: Alle Berechnungen o. Ä. funktional, nicht objektorientiert
