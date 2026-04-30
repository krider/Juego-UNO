# 🎮 UNO Refactorizado – Documentación de Mejoras y Pruebas

**Versión:** 4.0  
**Fecha de entrega:** 29 de abril de 2026  

---

## 1. Refactorización aplicada

Se reorganizó el proyecto en **11 clases** con responsabilidades claramente definidas, eliminando la "clase Dios" que concentraba toda la lógica.

| Clase | Responsabilidad |
|-------|----------------|
| `Main` | Punto de entrada. Crea la UI y el juego. |
| `Game` | Orquestador de alto nivel. Coordina el flujo del juego sin depender de la consola. |
| `GameUI` | Maneja **toda** la entrada/salida (`Scanner`, mensajes, colores, pausas). |
| `Player` | Representa un jugador (humano o IA). Contiene la lógica de decisión automática. |
| `Hand` | Gestiona las cartas de un jugador, búsqueda de jugadas válidas y priorización de especiales. |
| `TurnManager` | Controla el orden de turnos, dirección, saltos, y omite jugadores sin cartas. |
| `RuleEngine` | Aplica los efectos de las cartas especiales (Reversa, Salto, +2, Comodín, +4). |
| `Deck` | Mazo de 108 cartas. Permite robar, barajar y reciclar. |
| `DiscardPile` | Pila de descarte. Mantiene la carta en mesa y permite reconstruir el mazo. |
| `Card` | Modelo de una carta UNO (color, tipo, número) con validación de jugadas. |

### Separación de capas

- **Lógica de negocio:** `Game`, `Player`, `Hand`, `TurnManager`, `RuleEngine`, `Deck`, `DiscardPile`.
- **Lógica de presentación:** `GameUI`.
- **Reglas del juego:** `Card.esJugableSobre()`, `RuleEngine.aplicarEfecto()`.

**Principio aplicado:** Inversión de dependencias. `Game` no conoce `Scanner` ni `System.out`; se comunica con el mundo exterior a través de `GameUI`. Esto permitiría migrar a una interfaz gráfica sin modificar la lógica.

---

## 2. Plan de pruebas unitarias

### 2.1 Estrategia

| Técnica | Descripción | Clases evaluadas |
|---------|-------------|------------------|
| **Caja negra** | Partición equivalente, valores límite, tabla de decisión. | `Card`, `TurnManager` |
| **Caja blanca** | Cobertura de sentencia, decisión y condición. | `RuleEngine`, `Hand` |
| **Integración** | Flujo completo simulado. | `Game` con `Scanner` virtual |

### 2.2 Casos de prueba ejecutados

#### `CardTest`
- Construcción de carta numérica / especial / comodín.
- Excepciones por color inválido, número fuera de rango, comodín con color.
- `esJugableSobre()`: mismo color, mismo número, mismo tipo especial, comodín siempre válido, distinto color y número, mesa `null`.
- `toString()` con y sin color, comodines, cartas especiales.
- `setColor()` y `resetColor()` en comodines; excepción en carta normal.

#### `RuleEngineTest` (con stubs)
- Efecto `SALTO`: siguiente jugador pierde turno.
- Efecto `REVERSA`: cambia dirección; con 2 jugadores también salta.
- Efecto `ROBA2`: siguiente roba 2 cartas y pierde turno.
- Efecto `COMODÍN`: elige color, no roba cartas.
- Efecto `ROBA4`: elige color, siguiente roba 4 cartas y pierde turno.

#### `TurnManagerTest`
- Jugador inicial.
- Avance normal en horario y antihorario.
- Salto de turno por efecto de carta.
- Omisión de jugador con mano vacía.
- Cambio de dirección simple y doble.
- `siguienteJugador()` en ambas direcciones y ciclos.

#### `GameIntegrationTest`
- Simulación de partida completa con entrada predefinida (victoria del jugador humano).

### 2.3 Herramientas utilizadas
- **JUnit 5 (Jupiter)** con `@Test`, `@BeforeEach`, `@DisplayName`, `assertAll`.
- **Stubs** manuales para aislar dependencias en `RuleEngineTest`.

---

## 3. Lista de bugs encontrados y correcciones realizadas

| # | Bug | Estado | Corrección |
|---|-----|--------|------------|
| 1 | `esJugableSobre()` permitía cualquier carta numérica sobre otra numérica (sin coincidir color ni número). | Corregido | Se reescribió la condición: solo mismo color, mismo número entre numéricas, o mismo tipo entre especiales. |
| 2 | La carta robada no se jugaba automáticamente para el humano si era válida. | Corregido | En `robarYJugar()` se juega automáticamente si es válida. |
| 3 | Al quedar con una carta, se preguntaba si decir UNO (podía olvidarse y no haber penalización). | Corregido | Se anuncia UNO automáticamente con pausa de 5 segundos. |
| 4 | Jugadores con mano vacía seguían tomando turnos. | Corregido | `TurnManager.avanzarTurno()` salta automáticamente a los jugadores sin cartas. |
| 5 | Mensaje "IA pensando..." no se mostraba. | Corregido | Se agregó mensaje con el color del jugador IA. |
| 6 | Doble código de color ANSI en la impresión de cartas. | Corregido | Se eliminó `ColorConsola.colorCarta()` redundante dentro de `mostrarCartaMesa()` y `mostrarMano()`, dejando que `Card.toString()` aplique el color. |
| 7 | Faltaba mensaje de "roba 2 cartas" al aplicar efecto +2. | Corregido | Se añadió mensaje en `RuleEngine`. |
| 8 | El comodín +4 (`ROBA4`) y el comodín normal (`COMODIN`) estaban unificados como un solo tipo "CAMBIO_COLOR". | Corregido | Se separaron en dos tipos distintos (`COMODIN` sin robo, `ROBA4` con robo de 4). |
| 9 | Excepción `TurnManager cannot be resolved` por archivo mal nombrado o contenido incorrecto. | Corregido | Se verificó que `TurnManager.java` contenga exclusivamente la clase `TurnManager`. |
| 10 | Error `invalid source release: 26` por configuración de compilador. | Corregido | Se ajustó el *compiler compliance level* a la versión del JDK instalado (ej. 21). |
| 11 | `ClassNotFoundException: org.junit.platform.engine.ConfigurationParameters` al ejecutar tests. | Corregido | Se añadió JUnit 5 a las bibliotecas del proyecto (Add Library → JUnit → JUnit 5). |
| 12 | Pruebas antiguas usaban constructores incorrectos (`new Card("rojo", 5)` sin tipo). | Corregido | Se actualizaron todas las pruebas al nuevo constructor `Card(String color, Tipo tipo, int numero)`. |

---

## 4. Conclusiones

La refactorización logró un diseño modular, testeable y extensible. Las pruebas unitarias cubren los casos críticos de reglas y turnos, y los stubs permitieron validar el motor de reglas sin instanciar el juego completo. Los defectos encontrados fueron corregidos en su totalidad, mejorando la robustez y la experiencia de usuario en consola.
