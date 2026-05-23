# EcoRide - Sistema de Movilidad Urbana

**Examen Parcial Práctico - Programación III**

## Estructura del Proyecto

```
ecoride/
├── pom.xml
└── src/main/java/com/ecoride/
    ├── EcoRideApplication.java
    ├── model/
    │   ├── Vehiculo.java              (abstracta)
    │   ├── Monopatin.java             (extiende Vehiculo)
    │   ├── BicicletaElectrica.java    (extiende Vehiculo)
    │   ├── Usuario.java               (base)
    │   ├── UsuarioPremium.java        (extiende Usuario)
    │   ├── EstacionAnclaje.java       (contiene List<Vehiculo>)
    │   ├── ProcesadorPago.java        (interfaz)
    │   ├── TarjetaCredito.java        (implementa ProcesadorPago)
    │   └── BilleteraVirtual.java      (implementa ProcesadorPago)
    ├── factory/
    │   └── ProcesadorPagoFactory.java
    ├── service/
    │   ├── AlquilerService.java
    │   ├── VehiculoNoEncontradoException.java
    │   └── BateriaInsuficienteException.java
    └── controller/
        ├── AlquilerController.java
        └── DesbloqueoRequest.java
```

## Diagrama de Clases UML (texto)

```
<<abstract>>
Vehiculo
─────────────────────────────
- patente: String
- porcentajeBateria: int
- tarifaBase: double
─────────────────────────────
+ getPatente(): String
+ getPorcentajeBateria(): int
+ getTarifaBase(): double
        △
        |
   ─────┴─────
   |           |
Monopatin   BicicletaElectrica
──────────  ──────────────────
- tieneAmortiguacionReforzada: boolean
            - capacidadCanasto: int

Usuario
─────────────────────────────
- id: String
- nombreCompleto: String
─────────────────────────────
+ calcularImporte(tarifa): double
        △
        |
UsuarioPremium
─────────────────────────────
- porcentajeDescuento: double
─────────────────────────────
+ calcularImporte(tarifa): double  [OVERRIDE]

EstacionAnclaje
─────────────────────────────
- nombre: String
- vehiculosDisponibles: List<Vehiculo>
─────────────────────────────
+ agregarVehiculo(v): void
+ buscarVehiculoPorPatente(p): Vehiculo

<<interface>>
ProcesadorPago
─────────────────────────────
+ procesarPago(monto): void
        △
   ─────┴──────
   |            |
TarjetaCredito  BilleteraVirtual

ProcesadorPagoFactory
─────────────────────────────
+ obtenerProcesador(metodo): ProcesadorPago   [static]
```

## Cómo ejecutar

### Requisitos
- Java 21
- Maven

### Compilar y correr
```bash
cd ecoride
mvn spring-boot:run
```

El servidor arranca en: `http://localhost:8080`

## Endpoint REST

**GET** `/api/alquileres/desbloquear`

**Body JSON:**
```json
{
  "idUsuario": "U001",
  "patente": "ABC123",
  "metodoPago": "TARJETA"
}
```

### Casos de prueba con Postman / curl

#### ✅ Caso exitoso - Usuario Regular
```bash
curl -X GET http://localhost:8080/api/alquileres/desbloquear \
  -H "Content-Type: application/json" \
  -d '{"idUsuario":"U001","patente":"ABC123","metodoPago":"TARJETA"}'
```
Respuesta:
```json
{
  "estado": "EXITOSO",
  "mensaje": "Desbloqueo exitoso. Vehículo: ABC123 | Usuario: Juan Pérez (Regular) | Monto cobrado: $500.00 | Método: TARJETA"
}
```

#### ✅ Caso exitoso - Usuario Premium (15% descuento)
```bash
curl -X GET http://localhost:8080/api/alquileres/desbloquear \
  -H "Content-Type: application/json" \
  -d '{"idUsuario":"U003","patente":"GHI789","metodoPago":"BILLETERA"}'
```
Respuesta: Monto = $600 - 15% = $510.00

#### ❌ Vehículo no encontrado
```bash
curl -X GET http://localhost:8080/api/alquileres/desbloquear \
  -H "Content-Type: application/json" \
  -d '{"idUsuario":"U001","patente":"XXXXXX","metodoPago":"TARJETA"}'
```
Respuesta 404: `"Vehículo No Encontrado: No existe un vehículo con patente 'XXXXXX' en la estación."`

#### ❌ Batería insuficiente
```bash
curl -X GET http://localhost:8080/api/alquileres/desbloquear \
  -H "Content-Type: application/json" \
  -d '{"idUsuario":"U001","patente":"DEF456","metodoPago":"TARJETA"}'
```
Respuesta 400: `"Batería Insuficiente: El vehículo con patente 'DEF456' tiene solo 10% de batería. Se requiere mínimo 15%."`

## Datos de prueba cargados en memoria

| ID    | Nombre          | Tipo    | Descuento |
|-------|-----------------|---------|-----------|
| U001  | Juan Pérez      | Regular | —         |
| U002  | María González  | Regular | —         |
| U003  | Carlos López    | Premium | 15%       |
| U004  | Ana Martínez    | Premium | 10%       |

| Patente | Tipo              | Batería | Tarifa  |
|---------|-------------------|---------|---------|
| ABC123  | Monopatín         | 80%     | $500.00 |
| DEF456  | Monopatín         | 10% ⚠️  | $450.00 |
| GHI789  | Bicicleta Eléctrica | 95%   | $600.00 |
| JKL012  | Bicicleta Eléctrica | 60%   | $550.00 |
