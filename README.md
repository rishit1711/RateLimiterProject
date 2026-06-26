# 🚦 Redis-Powered Rate Limiter

A production-inspired **Rate Limiter** built using **Spring Boot**, **Redis**, and **Docker** to protect APIs from excessive traffic, abuse, and sudden spikes in requests.

This project implements two popular rate-limiting algorithms:

* 🪟 Fixed Window Algorithm
* 🪣 Token Bucket Algorithm

---

## ✨ Features

* 🔑 API Key-based request tracking
* 🪟 Fixed Window Rate Limiting
* 🪣 Token Bucket Rate Limiting
* ⚡ Redis atomic counters using `INCR`
* ⏳ TTL-based automatic expiration
* 🚫 HTTP `429 Too Many Requests` responses
* 🐳 Dockerized Redis setup
* 🏗️ Clean and extensible architecture

---

## 🏗️ Architecture

```text
Client
   │
   ▼
Spring Boot API
   │
   ▼
Rate Limiter
   │
   ▼
Redis
   ├── Request Counters
   ├── Token Bucket State
   └── Expiration (TTL)
```

---

## 🤔 Why Rate Limiting?

Think about:

* 🎫 Booking concert tickets
* 💳 Online payments
* 🛒 Flash sales
* 🌐 Public APIs
* 💻 Coding platforms and online judges

Without rate limiting, a few users—or even a buggy script—could flood the backend with requests and degrade the experience for everyone.

Rate limiting helps:

✅ Prevent abuse
✅ Protect backend resources
✅ Improve reliability
✅ Handle traffic spikes gracefully

---

# 🪟 Fixed Window Algorithm

### How it works

Suppose the limit is:

```text
10 requests per minute
```

A counter is maintained for each API key.

```text
rate_limit:user123 -> 7
```

For every request:

1. Increment the counter in Redis.
2. Set a TTL if this is the first request.
3. Reject requests once the limit is exceeded.

### Redis Operations

```redis
INCR rate_limit:user123
EXPIRE rate_limit:user123 60
```

### ⚠️ Problem with Fixed Window

If the limit is:

```text
10 requests/minute
```

A client can send:

```text
10 requests at 12:00:59
10 requests at 12:01:01
```

Which means:

```text
20 requests in just 2 seconds
```

This is known as the **Boundary Problem**.

---

# 🪣 Token Bucket Algorithm

### How it works

A bucket contains tokens.

```text
Capacity    = 10 tokens
Refill Rate = 1 token/sec
```

Every request consumes one token.

```text
tokens > 0  → Request Allowed
tokens = 0  → HTTP 429
```

### ✅ Why Token Bucket?

* Allows short bursts of traffic
* Maintains a steady request rate
* Prevents sudden spikes
* Provides a better user experience
* Handles real-world traffic patterns more gracefully

---

## 🟥 Redis Data Model

### Fixed Window

```text
Key:
rate_limit:{apiKey}

Value:
15
```

### Token Bucket

```json
{
  "tokens": 7,
  "lastRefillTime": 1718680000
}
```

---

## 🛠️ Tech Stack

| Technology     | Purpose              |
| -------------- | -------------------- |
| ☕ Java         | Programming Language |
| 🌱 Spring Boot | Backend Framework    |
| 🟥 Redis       | In-memory Data Store |
| 🐳 Docker      | Containerization     |
| 📬 Postman     | API Testing          |

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/rate-limiter.git
cd rate-limiter
```

### 2. Start Redis using Docker

```bash
docker run -d \
--name redis-rate-limiter \
-p 6379:6379 \
redis
```

Verify:

```bash
docker ps
```

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

## 🧪 Stress Testing

Send multiple requests using:

* 📬 Postman Runner
* ⚡ JMeter
* 💻 Curl Scripts

Example:

```bash
for i in {1..100}
do
curl http://localhost:8080/api/test
done
```

Expected Output:

```text
200 OK
200 OK
200 OK
429 Too Many Requests
429 Too Many Requests
...
```

---

## 📊 Sample Response

```json
{
  "message": "Rate limit exceeded. Please try again later."
}
```

---

## 📚 Concepts Learned

* 🚦 Rate Limiting
* 🪟 Fixed Window Algorithm
* 🪣 Token Bucket Algorithm
* 🟥 Redis TTL & Expiration
* ⚡ Atomic Operations
* 📈 Traffic Management
* 🔒 API Protection
* 🏗️ System Design Fundamentals
* 🐳 Containerization with Docker

---

## 🔮 Future Improvements

* 📅 Sliding Window Algorithm
* 🌍 Distributed Rate Limiting
* 🚪 API Gateway Integration
* 📈 Metrics & Monitoring
* 👤 Per-user and Per-endpoint limits
* ⚙️ Dynamic Configuration
* ☁️ Microservices Integration

---

## 💡 Key Takeaway

The best backend systems are often invisible.

When thousands or millions of requests hit an application, mechanisms like **Rate Limiting** are what keep systems fast, stable, and available.

This project was built to understand how real-world systems handle traffic spikes and protect backend services from abuse.

---

<div align="center">

⭐ If you found this project interesting, consider giving it a star!

</div>
