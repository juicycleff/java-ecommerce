# Spring Boot starter for Java 11
This is a starter including:
* Spring Boot Web starter
* Spring Boot Data JPA starter
    * Using MySQL
* Spring Boot Devtools and Annotation Processor
* A sample REST controller

## Run Service
```shell script
docker-compose up -d
```

## REST API DOCUMENTATION
### Authentication
We use basic authentication for every request, and a user must have any of the following roles;
- CUSTOMER
- ADMIN

We have come seed users for testing, namely;
- username: `admin`     password: `password`
- username: `customer`  password: `password`

### Accounts

Contains all user accounts related interfaces

* register: `POST /api/v1/account/register/`

### Cart

Contains all cart related interfaces

* create: `POST /api/v1/cart/`
* update: `PUT /api/v1/cart/`
* get_cart: `GET /api/v1/cart/{cartId}`

### Product

Contains all product related interfaces

* create: `POST /api/v1/products/`
* update: `PUT /api/v1/products/{productId}`
* delete: `PUT /api/v1/products/{productId}`
* list: `GET /api/v1/products`
* get: `GET /api/v1/products/{productId}`
