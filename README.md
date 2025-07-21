# Expense Tracker API

## Endpoints

---

To interact with any other endpoint, you need an Authorization token.

To get an Authorization token, you must log in to a registered user account.

### 1] Registration:

----
**Endpoint**: `POST` `api/auth/register`

**Request Body**: 
```json
{
  "name": "USER NAME",
  "email": "VALID EMAIL ADDRESS",
  "password": "PASSWORD"
}
```

--

**Response**:

**[STATUS CODE: 200]**
```json
{
  "name": "USER NAME",
  "email": "EMAIL ADDRESS"
}
```
----

### 2] Login:

----
**Endpoint**: `POST` `api/auth/login`

**Request Body**:
```json
{
  "email": "REGISTERED EMAIL ADDRESS",
  "password": "PASSWORD"
}
```

--

**Response**:

**[STATUS CODE: 200]**

```json
{
  "token": "AUTHORIZATION TOKEN"
}
```
----

### 3] Check token validity:

----
**Endpoint**: `GET` `api/auth/health`

**Headers**:
```json
{
  "Authorization": "Bearer <TOKEN>"
}
```
**NO Request Body**

--

**Response if valid**:

**[STATUS CODE: 200]**

```json
{
  "name": "USER NAME",
  "email": "EMAIL ADDRESS"
}
```

--

**Response if _not_ valid**:

**[STATUS CODE: 401]**

**NO Response Body**

----

### 4] Transactions:

> [!IMPORTANT] 
> 
> For **ALL** requests mentioned below, an Authorization token must be provided for a request to process.
> 
> Include this header in any request:
> **Headers**:
> ```json
> {
>     "Authorization": "Bearer <TOKEN>"
> }
> ```
> 
> Otherwise an `Unauthorized` response will be given (of status code **401**)

----
**Endpoint**: `GET` `api/transactions`


**NO Request Body**

--

**Response if valid**:

**[STATUS CODE: 200]**

**Response Body**:
```json
[
  {
    "id": "UUID",
    "name": "TRANSACTION NAME",
    "description": "DESCRIPTION",
    "amount": 999.99
  }
]
```

----

----
**Endpoint**: `POST` `api/transactions`

**Request Body**:
```json
{
  "name": "TRANSACTION NAME",
  "description": "DESCRIPTION",
  "amount": 999.99
}
```

--

**Response if valid**:

**[STATUS CODE: 201]**

----

----
**Endpoint**: `GET` `api/transactions/{id}`


**NO Request Body**

--

**Response if valid**:

**[STATUS CODE: 200]**

**Response Body**:
```json
[
  {
    "id": "UUID",
    "name": "TRANSACTION NAME",
    "description": "DESCRIPTION",
    "amount": 999.99
  }
]
```

----

----
**Endpoint**: `PATCH` `api/transactions/{id}`

**Request Body**:
> `name?` means that you may or may not include it

```json
[
  {
    "name?": "TRANSACTION NAME",
    "description?": "DESCRIPTION",
    "amount?": 999.99
  }
]
```

--

**Response if valid**:

**[STATUS CODE: 204]**

**NO Response Body**

----

---

## Setup

### Encryption Keys

---

To begin downloading the correct OpenSSL needed, you need to head over to
[this GitHub page](https://github.com/openssl/openssl/wiki/Binaries).

Follow the instructions there to properly download it.

For Windows (the way I used):

1. Head over to this [website](https://slproweb.com/products/Win32OpenSSL.html). 
    (Available in the GitHub page as well)

2. Download the `EXE` for `Win64 OpenSSL Light` with their latest version.
3. After installation, run the application `Win64 OpenSSL Command Prompt`
4. Within the command prompt, make sure you are within the same directory of `src/main/resources/jwt`:
    ```bash
    cd <PROJECT-FOLDER>/api/src/main/resources/jwt
   ```
5. Run this command to generate a private key:
    ```bash
    openssl genpkey -algorithm RSA -out app.key -outform PEM
   ```
6. Run this command to generate a public key:
    ```bash
    openssl rsa -pubout -in app.key -out app.pub
    ```


#### Concerning Security Considerations

As of now, the idea of this project was to host it locally 
therefore no considerable considerations to safe-keep the encryption keys were carried out.

However, one such method will be implemented and documented as of then.

If you already have an idea, then the 2 keys generated above will need to be safe-guarded **and** 
you need to consider `application.yml` configuration as well.

---

### Database Setup

---

Follow the installation instructions in this [website](https://www.postgresql.org/download/),
and set-up a database.

The default one is named `postgres` on port `5432`

Make sure to include the database credentials in the `application.yml`
by following the steps in this [section](#applicationyml-configuration).

---

### application.yml Configuration

---

To properly run this API, you must configure your `application.yml` file, which is located in `src/main/resources/`.

Here are a few settings you must make sure are configured correctly:

```yml
  datasource:
    # This must be uncommented

    # url: jdbc:postgresql://<HOST>:<PORT>/<DATABASE>          < Here add the URL to access the database (Self-hosted / Docker Compose)
    # username: <USERNAME>                                     < Here add your database username 
    # password: <PASSWORD>                                     < Here add your database password        

    # This should remain if you continue to use PostgreSQL
    driver-class-name: org.postgresql.Driver
```

After following the [Encryption Keys](#encryption-keys) section, 
you should have **2** files: `app.key` and `app.pub`.

Adding their classpath here is **important**, but if there are already
located at `src/main/resources/jwt` then there is no need to change this.

```yml
jwt:
  private-key: classpath:jwt/app.key
  public-key: classpath:jwt/app.pub
  ttl: 15m
```

Leaving `ddl-auto` to be `create` will cause the database to drop tables at
every server start, therefore this setting should be unchanged if you are **still developing your schema**.

Otherwise change it to `update` to retain the rows in your tables.

```yml
    hibernate:
      ddl-auto: create
```

If port *443* is already preoccupied or you prefer another, change it here:

```yml
server:
  port: 443
```

---

## Project Structure
```plaintext
  src/
   ├───main/
   │   ├───java/
   │   │   └───com/acskii/api
   │   │            ├───config/           # Main configuration beans
   │   │            ├───jwt/              # JWT service beans  
   │   │            ├───transactions/       
   │   │            │   ├───controller/   # Transaction API endpoints  
   │   │            │   ├───data/         # Transaction Models  
   │   │            │   │   ├───dto/      # Transaction Response DTOs  
   │   │            │   │   └───enums/    # Enums for Transaction Model
   │   │            │   ├───exception/    # Custom exceptions
   │   │            │   ├───mapper/       # Mappers to DTOs
   │   │            │   ├───repo/         # Transaction JPA repository
   │   │            │   └───service/      # Service layer
   │   │            └───users/
   │   │                ├───controller/   # User API endpoints  
   │   │                ├───data/         # User Models 
   │   │                │   ├───dto/      # User Response DTOs
   │   │                │   └───enums/    # Enums for User Model
   │   │                ├───exception/    # Custom exceptions
   │   │                ├───mapper/       # Mappers to DTOs
   │   │                ├───repo/         # User JPA repository
   │   │                └───service/      # Service layer
   │   └───resources/       
   │        ├── application.yaml          # Main application configuration
   │        └───jwt/                      # Contains OpenSSL keys
   └───test/                              # Will contain test units (TBD)
```

---

## How to run

---

### Database

### Steps

1. Clone Repository
    ```bash
    git clone git@github.com:acskii/expense-tracker-api.git
    cd api
    ```
   

2. Run the API

   - Compile application and download dependencies using Maven:
    ```bash
    ./mvnw clean package
    ```

    - Run the Spring boot application:
        ```bash
        java -jar  .\target\api-0.0.1-SNAPSHOT.jar
        ```
        **or**
        ```bash
        ./mvnw spring-boot:run
        ```


---
