# Progetto-IS-GR19 - Sistema di Registro Elettronico

## Descrizione del Progetto

Il progetto è un Registro Elettronico. Consente la gestione di utenti (Studenti e Docenti), l'iscrizione autonoma alle classi virtuali, la registrazione delle lezioni giornaliere e l'assegnazione di compiti. 

## Tecnologie Utilizzate
- **Java & JSwing** : Linguaggio e GUI
- **Hibernate & Jakarta Persistence** : ORM Persistance
- **MySQL** : Database
- **JUnit 5** : Test automatici

## Come installare ed eseguire il progetto

**Prerequisiti**: 
   - [Java JDK](https://www.oracle.com/java/technologies/downloads/) (compatibile con la configurazione del POM) installato.
   - [Apache Maven](https://maven.apache.org/) per la gestione delle dipendenze di progetto.
   - Server [MySQL](https://www.mysql.com/) installato e attivo sulla macchina (porta di default `3306`).

**Configurazione Database**:
   Assicurarsi di creare uno schema di database compatibile in locale tramite MySQL Workbench e di aggiornare il file `persistence.xml` con l'username e la password corretti del proprio DB MySQL prima di effettuare il primo avvio.

**Installazione**:
   Per scaricare tutte le librerie necessarie (Jakarta, Hibernate, driver MySQL) ed effettuare la compilazione, eseguire questo comando dal terminale posizionandosi nella directory in cui si trova il `pom.xml`:  "mvn clean install"
   

**Avvio**:
   Avviare l'applicazione eseguendo la classe di startup `Main.java` tramite l'IDE.

## Come usare il progetto

All'avvio il metodo `inizializzaDatiDiProva()` interviene iniettando nel database alcune entità per esplorare da subito il gestionale senza inserimenti manuali.

Di seguito sono riportate le credenziali fittizie che si hanno a disposizione all'avvio. Tutti gli account possiedono come password base: `password123`.

**Docenti di prova**:
- **Mario Rossi**: `mario.rossi@docenti.unina.it` 
- **Giulia Bianchi**: `giulia.bianchi@docenti.unina.it` 

**Studenti di prova**:
- **Luigi Verdi**: `luigi.verdi@studenti.unina.it` 
- **Francesca Neri**: `francesca.neri@studenti.unina.it` 

Entrando come **Docente** è possibile simulare la registrazione di nuove lezioni e nuovi compiti sulle classi appartenenti a quel docente; entrando come **Studente**, invece, si avrà la visibilità delle materie, dei compiti assegnati e si potrà effettuare iscrizioni alle classi tramite codice univoco.

## Test

La qualità e stabilità del software sono supervisionate mediante test automatizzati JUnit5. All'interno di `src/test/java` sono presenti le classi relative alla suite di test che utilizzano l'approccio del **Black Box Testing** e del **White Box Testing** per la verifica di requisiti funzionali e non-funzionali e certificare la reazione dei Controller ad input specifici o la copertura del codice.

Per lanciare l'intera suite di test e verificarne il passaggio usa: "mvn test"



## 👥 Ringraziamenti / Riferimenti

Progetto concepito e realizzato per intero dal **Gruppo 19** per l'esame di Ingegneria del Software, presso l'Università degli Studi di Napoli Federico II.

Un ringraziamento ai membri del team di sviluppo:
- [Francesco Cocozza]
- [Giovanni Cautiero]
- [Francesco Costagliola]

