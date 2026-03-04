# Juego UNO - Descripción del Proyecto

## 📋 Descripción del Juego

Este es un juego de UNO implementado en Java que permite jugar contra la computadora. El juego sigue las reglas básicas del UNO tradicional donde el objetivo es ser el primero en quedarse sin cartas.

### 🎮 Cómo Jugar

1. **Inicio del Juego**: 
   - Se reparten 7 cartas a cada jugador (humano y computadora)
   - Se coloca una carta inicial en la mesa
   - El jugador humano comienza el juego

2. **Mecánica del Juego**:
   - En tu turno, debes jugar una carta que coincida en color o número con la carta en la mesa
   - Si no tienes una carta válida, puedes robar una carta del mazo presionando "R"
   - La computadora juega automáticamente cuando es su turno
   - Gana el primero que se quede sin cartas

3. **Cartas Disponibles**:
   - 4 colores: Rojo, Azul, Verde y Amarillo
   - Números del 0 al 9
   - Cada combinación color-número tiene 2 cartas

### 🎯 Objetivo

Ser el primer jugador en descartar todas tus cartas antes que la computadora.

## 🚀 Mejoras Propuestas

### Corto Plazo
1. **Cartas Especiales**:
   - Implementar cartas de "Salta" (Skip)
   - Implementar cartas de "Reversa" (Reverse)
   - Implementar cartas de "Toma 2" (Draw 2)
   - Implementar cartas "Comodín" (Wild) y "Toma 4" (Wild Draw 4)

2. **Validaciones**:
   - Agregar validación cuando el mazo se agota
   - Implementar regla de "UNO" cuando queda una carta
   - Validar que no queden cartas repetidas en la mesa

### Mediano Plazo
3. **Interfaz Gráfica**:
   - Desarrollar una interfaz gráfica con JavaFX o Swing
   - Mostrar las cartas visualmente en lugar de texto
   - Animaciones al jugar cartas

4. **Múltiples Jugadores**:
   - Permitir de 2 a 4 jugadores humanos
   - Modo multijugador local
   - Sistema de turnos más complejo

### Largo Plazo
5. **Características Avanzadas**:
   - Sistema de puntuación y estadísticas
   - Guardar partidas
   - Diferentes niveles de dificultad para la IA
   - Modo online para jugar contra otros jugadores

6. **Personalización**:
   - Seleccionar número de jugadores
   - Configurar reglas de la casa
   - Temas y colores personalizables

## 📝 Reglas Actuales Implementadas

- ✅ Coincidencia por color o número
- ✅ Robar carta cuando no hay movimiento válido
- ✅ Turnos alternados entre humano y computadora
- ✅ Detección de ganador
- ✅ Barajado aleatorio del mazo

## 🛠️ Tecnologías Utilizadas

- Java (JDK 8 o superior)
- Programación Orientada a Objetos
- Colecciones de Java (ArrayList)
- Scanner para entrada de usuario
