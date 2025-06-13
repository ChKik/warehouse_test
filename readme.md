# 🏬 Warehouse Inventory Management API

This is a RESTful API built with Spring Boot to manage warehouses, products, and inventory. It provides endpoints for CRUD operations and querying data based on specific filters.

---

## 🚀 Base URL

```
http://localhost:8080/api
```

---

## 📦 Warehouse Endpoints (`/warehouses`)

| Method | Endpoint                         | Description                          |
|--------|----------------------------------|--------------------------------------|
| GET    | `/warehouses`                    | Get all warehouses                   |
| GET    | `/warehouses/{id}`               | Get a warehouse by its ID           |
| POST   | `/warehouses`                    | Create a new warehouse               |
| PUT    | `/warehouses/{id}`               | Update an existing warehouse         |
| DELETE | `/warehouses/{id}`               | Delete a warehouse by its ID         |
| GET    | `/warehouses/search?name=Main`   | Search warehouses by name            |

📌 Required JSON for creating a warehouse:
```json
{
  "warehouseName": "Main Warehouse",
  "address": "123 Storage St",
  "managerName": "John Doe",
  "capacity": 10000
}
```

---

## 🛒 Product Endpoints (`/product`)

| Method | Endpoint                 | Description                          |
|--------|--------------------------|--------------------------------------|
| GET    | `/product`               | Get all products                     |
| GET    | `/product/{id}`          | Get a product by ID                  |
| POST   | `/product`               | Create a new product                 |
| PUT    | `/product/{id}`          | Update a product by ID               |
| DELETE | `/product/{id}`          | Delete a product by ID               |
| GET    | `/product/{category}`    | Get products by category             |
| GET    | `/product/{productName}` | Get product by name (first match)    |

📌 Sample JSON to create a product:
```json
{
  "productName": "Gaming Mouse",
  "category": "Electronics",
  "weight": 0.3
}
```

---

## 📊 Inventory Endpoints (`/inventory`)

| Method | Endpoint                                         | Description                                 |
|--------|--------------------------------------------------|---------------------------------------------|
| GET    | `/inventory/{id}`                                | Get all inventory (misnamed: ID unused)     |
| POST   | `/inventory`                                     | Create inventory entry                      |
| PUT    | `/inventory/{id}`                                | Update inventory by ID                      |
| DELETE | `/inventory/{id}`                                | Delete inventory by ID                      |
| GET    | `/inventory/product/{productId}/quantity/{qty}`  | Get inventory entries by product + quantity |
| GET    | `/inventory/warehouse/{warehouseId}`             | Get all inventory for a warehouse           |

📌 Example JSON to create inventory:
```json
{
  "warehouse": { "id": 1 },
  "product": { "id": 1 },
  "quantity": 100,
  "minimumStock": 20,
  "maximumStock": 2000
}
```

---

## ✅ Notes

- Be sure to create `Product` and `Warehouse` entries before creating `Inventory`.
- All endpoints return standard HTTP codes (200 OK, 201 Created, 204 No Content, 404 Not Found).
- This API supports Cross-Origin Requests (`@CrossOrigin(origins="*")`).

---

## 🔪 Testing with cURL

You can use `curl` commands (see example above) or tools like Postman/Insomnia to test the API.

---

