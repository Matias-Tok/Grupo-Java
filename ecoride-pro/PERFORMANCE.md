# Anexo Tecnico de Rendimiento - EcoRide Pro

# 1. Por que la nueva estructura de busqueda es mas rapida que la lineal anterior

En la version anterior, la busqueda de un vehiculo por patente recorria una lista
secuencialmente elemento por elemento hasta encontrarlo o llegar al final.
En el peor caso (vehiculo al final o inexistente), se inspeccionaban todos los N elementos.
Complejidad: O(n).

En EcoRide Pro, los vehiculos se almacenan en un `HashMap<String, Vehiculo>` donde la clave es
la patente en mayusculas. La operacion `vehiculosPorPatente.get(patente)` calcula el hash de
la clave y accede directamente a la posicion de memoria correspondiente.
Complejidad: O(1) promedio.

# 2. Como el algoritmo de deduplicacion de alertas GPS evita los bucles anidados

El enfoque ingenuo para eliminar duplicados compara cada elemento contra todos los demas:
dos bucles for anidados dan una complejidad de O(n^2). Con miles de coordenadas GPS,
esto bloquea la CPU del servidor.

EcoRide Pro resuelve el problema con una sola pasada usando un `HashSet<String>`:
- Se recorre la lista una unica vez (un solo for).
- Por cada alerta, `set.add(alerta)` devuelve true si es nueva (se agrega al resultado)
  o false si ya existe (se descarta).
- La operacion `add` en un HashSet es O(1) promedio.
- Resultado final: O(n) en tiempo y O(n) en espacio.

# 3. Como se resolvio el ordenamiento natural sin romper el ordenamiento por tarifa

`Vehiculo` implementa `Comparable<Vehiculo>` definiendo el orden natural por porcentaje
de bateria ascendente (los de menor energia primero). Este orden intriseco se usa con
`Collections.sort(lista)` sin argumentos.

Para el ordenamiento alternativo por tarifa descendente, se creo la clase
`ComparadorTarifaDescendente` que implementa `Comparator<Vehiculo>`. Este componente
externo no modifica la clase `Vehiculo` ni interfiere con su orden natural.
Se usa con `Collections.sort(lista, new ComparadorTarifaDescendente())`.

Ambos ordenamientos coexisten en memoria sin conflicto, respetando el principio
de responsabilidad unica: el criterio natural vive en el vehiculo,
el criterio alternativo vive en su propio comparador externo.
