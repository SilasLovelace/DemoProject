# Webshop Authentification & Authorization

Dieses Projekt beinhaltet die grundlegende Login-Funktionalität, die notwendig für den Webshop ist. 
Konkret kann man sich damit registrieren und einloggen und abhängig davon und der eigenen Benutzerrolle (Kunde/Admin) unterschiedliche Bereiche der Webseite ansehen.

Zum Background: der Login per se läuft über einen Post-Request auf /api/auth/login ab, bei dem einmalig die E-Mail-Adresse und das Passwort im Request-Body mitgeschickt werden. 
Damit man sich nicht nach jedem Seitenreload neu anmelden muss und gleichzeitg aber das Passwort nirgends im Klartext gespeichert werden muss, kommt ein Login mittels Json Web Tokens (JWT) zum einsatz.

Hierbei generiert der Server einen verschlüsselten Token, der beim Login als Response zurückkommt. Dieser kann zwar entschlüsselt werden, aber ohne einen geheimen Schlüssel
nicht manipuliert und wieder verschlüsselt werden, sodass er als ausreichende und sichere Identifikation dienen kann. 
Dieser Token wird anschließend bei jedem weiteren Request an das Backend übermittelt (siehe `main.js`), welches dadurch weiß, wer ihr seid.
Hier ein kurzes Video, das den Login-Prozess mittels JWT nochmal veranschaulicht: https://www.youtube.com/watch?v=7ozQLeFJpqs

Damit dann auch der Token nicht nach jedem Seitenreload verloren geht, wird dieser im Frontend im [LocalStorage](https://developer.mozilla.org/en-US/docs/Web/API/Window/localStorage) gespeichert.
Das ist ein primitiver Key-Value-Speicher, der unter einem bestimmten Key (bestimmt man selbst) einen String auch über längere Zeiten speichern kann. 
In diesem wird der JWT nach einem erfolgreichen Login abgelegt (siehe `store/authStore.js`).

Damit bleibt der JWT zwar über mehrere Seitenreloads erhalten, aber die Benutzerdaten, die notwendig sind um bspw. Buttons anhand der Benutzerrolle anzuzeigen, gehen dennoch verloren. 
Aus diesem Grund muss dennoch bei jedem Seitenreload ein Request an das Backend geschickt werden, damit diese Daten wieder geladen werden können.
Dies geschieht hier in `router/index.js` im "beforeEach"-Guard. Siehe hierzu die Kommentare an den jeweiligen Stellen.

## Projekt aufsetzen

- Einmal `npm install` ausführen 
- Anschließend mit `npm run dev` starten
- Unter `api/index.js` bei der Variable `API_URL` die Domain für eine eigene Webshop-Instanz eingeben.
