# Use Cases
1. Inserimento frase iniziale
2. 


### Use Case 1
<table>
  <tr>
    <td><b>Use Case Name</b>
    <td>Inserimento frase iniziale</td>
  </tr>
  <tr>
    <td><b>Attori</b></td>
    <td>User</td>
  </tr>
  <tr>
    <td><b>Descrizione</b></td>
    <td>Viene inserita la frase da parte dell’utente che poi il sistema dividerà sintatticamente.</td>
  </tr>
  <tr>
    <td><b>Precondizioni</b></td>
    <td>-</td>
  </tr>
  <tr>
    <td><b>Scenario principale</b></td>
    <td>L’utente digita la frase in una casella di testo.</td>
  </tr>
  <tr>
    <td><b>Scenario alternativo</b></td>
    <td>Il sistema genera un messaggio di errore quando la casella di testo per la frase input non viene compilata.</td>
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
skinparam actorStyle awesome
skinparam usecase {
  BackgroundColor #fdf6e3
  BorderColor black
  ArrowColor black
}

' Attori
actor Software as S
actor User as U

' Sistema
rectangle "NONSENSE Generator" {

  ' Input
  usecase UC1 as "Enter Initial Sentence"
  usecase UC2 as "Selection Number of Output Sentences"

  ' Elaborazione
  usecase UC3 as "View Generated Sentences"
  usecase UC4 as "Toxicity Check"

  ' Azioni post-output
  usecase UC5 as "Reset the Generator"
  usecase UC6 as "Copy the Sentences"
  usecase UC7 as "Save Output Sentences to Text File"

  ' Automatico
  usecase UC8 as "Save Words to Software Dictionary"
}

' === Associazioni User ===
U --> UC1
U --> UC2
U --> UC3
U --> UC5
U --> UC6
U --> UC7

' === Associazioni Software ===
S --> UC4
S --> UC8

' === Relazioni tra use cases ===
UC1 -[dashed]-> UC8 : <<includes>>
UC3 -[dashed]-> UC4 : <<includes>>
UC3 <-[dashed]- UC6 : <<extends>>
UC3 <-[dashed]- UC5 : <<extends>>
UC1 <-[dashed]- UC7 : <<extends>>
@enduml
```
