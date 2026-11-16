# #322 Mini laohaldusrakendus

Java käsurearakendus kuvab kolm näidistoodet, lisab tooteid, muudab kogust,
otsib osalise nime järgi ja arvutab laoväärtust. JDK 17+ on piisav.

```sh
./run.sh
./test.sh
```

Hind sisesta punktiga, näiteks 1.25; kuupäev vormis AAAA-KK-PP. Andmed on ainult
mälus. Negatiivne laoseis, duplikaatnimi ja liiga suur kogus lükatakse tagasi.
Raha jaoks kasutatakse BigDecimal-i, et vältida ujukoma ümardusvigu.

## OOP osad ja andmestruktuur

Product on abstraktne klass. FoodProduct ja ElectronicProduct pärivad sellest.
Mõlemad rakendavad details() eri viisil: toit näitab kuupäeva, elektroonika
garantiid. Inventory kasutab Product tüüpi viiteid ja saab polümorfse tulemuse.
Nimi, hind ja kogus on private väljad; kogust muudetakse valideeriva meetodi kaudu.
Need on õppimiseks mõeldud näited. Õppija enda selgitus tuleb veel kirjutada.

LinkedHashMap säilitab lisamise järjekorra ja võimaldab täpse nime järgi kiiresti
leida. Võtmed normaliseeritakse väiketähtedeks. Osalise nime otsing käib kõik tooted
läbi ja tagastab muutmatu tulemuste nimekirja; tooteobjektid on kontrollitud API-ga.

## Tegelik tööjärjekord ja kontroll

1. 5fbcd8f: tooteklassid ja sisendi kontroll.
2. 700e463: laohaldus, otsing ja täpne koguväärtus.
3. bc56201: konsoolimenüü ja kolm näidistoodet.
4. Hilisem kontroll: 13 mudelikontrolli, menüü lokaalsete muutujate refaktor ja juhend.

Autor on Codex. Isiklikku 3–5-lauselist tööpäevikut, õppija enda OOP selgitust
ega individuaalset esitlusmuudatust pole sinu eest välja mõeldud.
Ekraanipilt või lühike salvestus peab näitama lisamist, otsingut ja koguväärtust.
Kontrollitud näidiskäivitus algab laoväärtusega 34.00 EUR.
