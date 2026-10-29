# mk96-qa-technical-tests

Pruebas técnicas que he hecho para distintos procesos de selección. Cada carpeta es un proyecto Maven independiente.

> Son ejercicios de selección: pretenden demostrar a grandes rasgos los conocimientos que pedía cada puesto, no son código de producción. Cada README interno lista las mejoras que se harían con más tiempo.

## Los ejercicios

| Carpeta | Origen | Qué es | Tecnología | Puerto |
|---|---|---|---|---|
| [`idealista/code-review-challenge`](idealista/code-review-challenge) | Idealista | Código de partida de un reto de *code review*: un servicio que mide la calidad de los anuncios. El reto consiste en comentar el código, no en escribir nada | Spring Boot, Java 8 | 8080 por defecto |
| [`idealista/idealista-adds`](idealista/idealista-adds) | Idealista | Servicio de calidad de anuncios: puntúa anuncios y lista los relevantes y los irrelevantes | Spring Boot 3.1.5, Java 17 | 8080 por defecto |
| [`prices-db`](prices-db) | Neoris (proyecto de Inditex) | API de consulta de precios sobre una base de datos en memoria | Spring Boot 3.1.4, Java 17, H2, Swagger | 8090 |
| [`similar-products/similar-products-app`](similar-products/similar-products-app) | MCA IT Solutions | API que devuelve los productos similares a uno dado, a partir de su id | Spring Boot 2.7.14, Java 8 | 5000 |
| [`similar-products/similar-products-mock`](similar-products/similar-products-mock) | MCA IT Solutions | Servicio simulado (mock) del que depende la API anterior | Spring Boot 2.7.14, Java 8 | 3001 |

### Endpoints

| Proyecto | Endpoint |
|---|---|
| `code-review-challenge` | `GET /ads/quality`, `GET /ads/public`, `GET /ads/score` |
| `idealista-adds` | `GET /idealista/ads/relevant`, `GET /idealista/ads/irrelevant`, `PUT /idealista/ads/score` |
| `prices-db` | `POST /Neoris/Inditex/PricesDB/getOperatingProductRate`, con un cuerpo `{ "productId", "brandId", "applicationDate" }` |
| `similar-products-app` | `GET /product/{productId}/similar` |
| `similar-products-mock` | `GET /product/{productId}` y `GET /product/{productId}/similarids` |

`similar-products-app` llama al mock en `http://localhost:3001`, así que **hay que arrancar primero el mock**.

## Cómo arrancar un proyecto

Necesitas Maven y un JDK. Los proyectos de Java 8 (Spring Boot 2.7) funcionan con un JDK entre 8 y 17: con JDK 21 falla la compilación por la versión de Lombok que usan. `idealista-adds` y `prices-db` necesitan Java 17 o superior.

```bash
cd prices-db                       # o la carpeta del proyecto que quieras
mvn clean install
java -jar target/prices-db-1.0.0.jar
```

El nombre del `.jar` depende del proyecto: `prices-db-1.0.0.jar`, `similar-products-1.0.0.jar`, `similar-products-mock-1.0.0.jar` y `ads-1.0.0.jar`. Los proyectos que tienen `.mvn/wrapper` (todos menos `code-review-challenge`) se pueden compilar también con `./mvnw`.

Para ejecutar solo los tests: `mvn test`.

### Servicios que ofrecen documentación o consola

`prices-db` usa una base de datos H2 **en memoria**, que se crea y se borra con cada arranque. Mientras está en marcha:

- Swagger: `http://localhost:8090/swagger-ui.html`
- Consola de H2: `http://localhost:8090/h2-console`. El usuario y la contraseña de esa base de datos local están en su README.

No hay claves, tokens ni datos de ningún servicio externo en este repositorio.

## Estado actual

| Proyecto | ¿Compila y pasa los tests? | Notas |
|---|---|---|
| `code-review-challenge` | Sí | Comprobado con JDK 21 |
| `similar-products-mock` | Sí | Comprobado con JDK 11. No tiene tests |
| `similar-products-app` | Sí | Comprobado con JDK 11 |
| `prices-db` | **Todavía no** | Depende de `com.markes96:common-code:1.0.0`, una versión local antigua de la librería que nunca se publicó |
| `idealista-adds` | **Todavía no** | Depende de `com.mk96:common-code:2.0.0-LocalVersion`, también una versión local que nunca se publicó |

Las clases que usan `prices-db` e `idealista-adds` (por ejemplo `com.markes96.dto.api.ApiResponseDTO` o `com.mk96.utils.MapperUtils`) no existen en la librería publicada, [`mk96-lib-java-common-code`](https://github.com/mk96-lib/mk96-lib-java-common-code), cuyas clases están en `org.mk96.common`. Hay que migrarlos a esa librería para que compilen.

## Probar las APIs con Postman

Las colecciones de Postman para `prices-db` y `similar-products` están en [`mk96-qa-postman-collections`](https://github.com/mk96-qa/mk96-qa-postman-collections).

## Estructura del repositorio

```
.
├── README.md
├── .gitignore
├── idealista/
│   ├── code-review-challenge/       Código del reto de code review (con su README y el enunciado)
│   └── idealista-adds/              Servicio de calidad de anuncios
├── prices-db/                       API de precios
└── similar-products/
    ├── similar-products-app/        API de productos similares
    └── similar-products-mock/       Servicio simulado del que depende
```

Cada proyecto es un proyecto Maven estándar: `pom.xml`, `src/main` (código y recursos) y `src/test` (tests). El único que no tiene tests es `similar-products-mock`.
