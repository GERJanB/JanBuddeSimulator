# Mühle Referee

Dies ist das klassische Brettspiel Mühle.

# Wie wird gespielt?
1. Das Spiel wird von 2 Spielern gespielt.
2. Zu Beginn des Spiels befinden sich keine Steine auf dem Spielbrett.
3. Jeder Spieler hat zu Beginn des Spiels 9 Steine.
4. Ein Spieler fängt an und legt einen Stein auf einen der gekennzeichneten Punkte.
5. Die Spieler legen ihre Steine abwechselnd auf das Spielfeld.
6. Wenn ein Spieler 3 Steine in einer Reihe hat, darf dieser einen beliebigen Stein des Gegners vom Feld nehmen.
Die einzige Einschränkung hierbei ist, dass der Stein sich nicht in einer geschlossenen Mühle befinden darf.
Also nicht in einer Reihe von 3 Steinen.
7. Sobald alle Steine gelegt sind, dürfen die Steine immer um ein Feld verschoben werden
8. Es darf immer nur ein Stein auf einem Feld sein
9. Sobald ein Spieler nur noch 3 Steine übrig hat, darf dieser mit seinen Steinen Springen.
Das heißt, dass er sich nicht mehr an die Regel halten muss, welche besagt, dass ein Stein immer nur
um ein Feld verschoben werden darf. Er kann pro Zug einen Stein auf ein beliebiges Feld setzten.
10. Sobald ein Spieler nur noch 2 Steine hat, hat dieser verloren, da es mit zwei Steinen unmöglich ist das Spiel zu 
gewinnen.

# Was funktioniert?
1. Mensch gegen Mensch
2. Ausgaben, welcher Spieler aktuell dran ist werden angezeigt
3. Ein Spieler wird benachrichtigt, sobald dieser nur noch drei Steine hat und auf jedes freie Feld springen darf
4. Es können mehrere Instanzen des Spiels geöffnet werden, welche unabhängig von einander ablaufen. Es werden alle
Instanzen geschlossen, wenn in einer der offenen Instanzen das Spiel beendet wird
5. Sobald ein Spiel gewonnen wurde, wird automatisch das UI resetet und es kann ein neues Spiel gestartet werden
6. Accelatoren und Mnemonics funktionieren
7. Wenn das Fenster zu klein für das Spielfeld wird, erscheinen Scrollbars mit denen über das Spielfeld gescrollt werden kann
8. Beim anklicken einer Spielfigur werden alle mögliche Spielzüge angezeigt, Das Ausgangsfeld wird blau makiert
9. Wenn ein falscher Spielzug ausgeführt wird, wird die Figur automatisch auf das Ausgangsfeld zurückgesetzt. 
Dies passiert solange bis der Spieler einen gültigen Spielzug ausführt
