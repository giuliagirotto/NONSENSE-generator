# Use Cases
1. Enter Initial Sentence
2. Selection Number of Output Sentences
3. 


### Use Case 1
<table>
  <tr>
    <td><b>Use Case Name</b>
    <td>Enter Initial Sentence</td>
  </tr>
  <tr>
    <td><b>Actors</b></td>
    <td>User</td>
  </tr>
  <tr>
    <td><b>Description</b></td>
    <td>Input of a sentence which will be syntactically analyzed by the system.</td>
  </tr>
  <tr>
    <td><b>Preconditions</b></td>
    <td>-</td>
  </tr>
  <tr>
    <td><b>Main Scenario</b></td>
    <td>The user enters a sentence in the text box.</td>
  </tr>
  <tr>
    <td><b>Alternative Scenario</b></td>
    <td>The user does not input a sentence: the system shows an error message.</td>
  </tr>
  <tr>
    <td><b>Post-Conditions</b></td>
    <td>The sentence is stored and parsed for further processing.</td>
  </tr>
    <tr>
    <td><b>Notes</b></td>
    <td>-</td>
  </tr>
</table>

### Use Case 2
<table>
  <tr>
    <td><b>Use Case Name</b>
    <td>Enter Initial Sentence</td>
  </tr>
  <tr>
    <td><b>Actors</b></td>
    <td>User</td>
  </tr>
  <tr>
    <td><b>Description</b></td>
    <td>Input of a sentence which will be syntactically analyzed by the system.</td>
  </tr>
  <tr>
    <td><b>Preconditions</b></td>
    <td>-</td>
  </tr>
  <tr>
    <td><b>Main Scenario</b></td>
    <td>The user enters a sentence in the text box.</td>
  </tr>
  <tr>
    <td><b>Alternative Scenario</b></td>
    <td>The user does not input a sentence: the system shows an error message.</td>
  </tr>
  <tr>
    <td><b>Post-Conditions</b></td>
    <td>The sentence is stored and parsed for further processing.</td>
  </tr>
    <tr>
    <td><b>Notes</b></td>
    <td>-</td>
  </tr>
</table>




# Grafo Use Cases

![UseCasesDiagram.png](img/diagrams/UseCasesDiagram.png)

```plantuml
@startuml

left to right direction

actor Giocatore
actor NBM_Script

rectangle "Klotski" {
  usecase "Visualizzare configurazione corrente" as current_conf
  usecase "Scegliere configurazione iniziale" as init_conf
  usecase "Visualizzare counter delle mosse effettuate" as counter
  usecase "Ripristinare la partita salvata" as restore
  usecase "Salvare la partita" as update_log
  usecase "Muovere i pezzi nelle posizioni consentite" as move
  usecase "Vincere" as win
  usecase "Utilizzare funzione di reset" as reset
  usecase "Utilizzare funzione di undo" as undo
  usecase "Fornire «next best move»" as make_nbm
  usecase "Visualizzare «next best move»" as nbm
}

Giocatore -- current_conf
Giocatore -- init_conf
init_conf -down[dashed]-> update_log: "<<includes>>"
Giocatore -- move
move -[dashed]-> update_log: "<<includes>>"
move <-[dashed]right- win: "<<extends>>"
Giocatore -- counter
Giocatore -- reset
reset -[dashed]-> update_log: "<<includes>>"
Giocatore -- undo
undo -[dashed]-> update_log: "<<includes>>"
Giocatore -- nbm
Giocatore -- restore
restore -left[dashed]-> current_conf: "<<includes>>"
NBM_Script -up- make_nbm
nbm -[dashed]-> make_nbm: "<<includes>>"
nbm -[dashed]-> update_log: "<<includes>>"

@enduml
```
