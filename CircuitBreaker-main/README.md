## Servicios con Circuit Breaker

Un Circuit Breaker es un patrón de diseño utilizado en sistemas distribuidos para mejorar la resiliencia y la estabilidad de la aplicación. Su propósito principal es manejar fallos temporales de forma más eficiente y evitar que fallos en un componente se propaguen y causen fallos en cadena en todo el sistema.
Un Circuit Breaker actúa como un interruptor entre el cliente y el servicio que está siendo llamado. Este interruptor puede estar en uno de los tres estados:

Closed: Las solicitudes pasan libremente al servicio objetivo. Si el servicio responde con éxito, el estado permanece cerrado. Sin embargo, si se detectan un número configurable de fallos consecutivos, el interruptor cambia al estado abierto.

Open: Las solicitudes son bloqueadas y fallan inmediatamente sin intentar comunicarse con el servicio objetivo. Este estado se mantiene durante un periodo de tiempo determinado (timeout).

Half-Open: Después del periodo de timeout, el Circuit Breaker permite pasar un número limitado de solicitudes para probar si el servicio se ha recuperado. Si las solicitudes tienen éxito, el interruptor vuelve al estado cerrado. Si fallan, el interruptor vuelve al estado abierto.