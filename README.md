Redis-Powered Rate Limiter 🚦

A production-inspired Rate Limiter built using Spring Boot, Redis, and Docker to protect APIs from excessive traffic and abuse.

This project implements two popular rate-limiting algorithms:

Fixed Window Algorithm
Token Bucket Algorithm

The goal is to understand how modern applications prevent a single client from overwhelming backend services.

✨ Features
API Key-based request tracking
Fixed Window Rate Limiting
Token Bucket Rate Limiting
Redis atomic counters (INCR)
TTL-based automatic expiration
HTTP 429 (Too Many Requests) responses
Dockerized Redis setup
Clean and extensible architecture
🏗️ System Design
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
   │
   ├── Request Counters
   ├── Token Bucket State
   └── Expiration (TTL)
Why Rate Limiting?

Think about:

Booking concert tickets 🎫
Online payments 💳
Flash sales 🛒
Public APIs 🌐

Without rate limiting, a few users—or even a buggy script—could flood the backend with requests and degrade the experience for everyone.

Rate limiting helps:

✅ Prevent abuse
✅ Protect backend resources
✅ Improve reliability
✅ Handle traffic spikes gracefully

Algorithms Implemented
1️⃣ Fixed Window Algorithm
How it works

Suppose the limit is:

10 requests per minute

A counter is maintained for each API key.

rate_limit:user123 -> 7

Every request:

Increment counter in Redis.
Set TTL if this is the first request.
Reject requests once the limit is exceeded.
Redis Operations
INCR rate_limit:user123
EXPIRE rate_limit:user123 60
Problem with Fixed Window

If the limit is:

10 requests/minute

A client can send:

10 requests at 12:00:59
10 requests at 12:01:01

Total:

20 requests in 2 seconds

This is known as the Boundary Problem.

2️⃣ Token Bucket Algorithm
How it works

A bucket contains tokens.

Capacity = 10 tokens
Refill Rate = 1 token/sec

Every request consumes one token.

If:

tokens > 0

✅ Request Allowed

Otherwise:

❌ HTTP 429

Why Token Bucket?
Allows short traffic bursts
Maintains a steady request rate
Prevents sudden traffic spikes
Provides a better user experience
Redis Data Structure
Fixed Window
Key:
rate_limit:{apiKey}

Value:
15
Token Bucket
{
  "tokens": 7,
  "lastRefillTime": 1718680000
}
Tech Stack
Java
Spring Boot
Redis
Docker
Postman
Running the Project
Clone Repository
git clone https://github.com/your-username/rate-limiter.git
cd rate-limiter
Start Redis using Docker
docker run -d \
--name redis-rate-limiter \
-p 6379:6379 \
redis

Verify:

docker ps
Run Spring Boot Application
./mvnw spring-boot:run

or

mvn spring-boot:run
Testing

Send multiple requests using:

Postman Runner
JMeter
Curl scripts

Example:

for i in {1..100}
do
curl http://localhost:8080/api/test
done

Expected Result:

200 OK
200 OK
200 OK
429 Too Many Requests
429 Too Many Requests
...
Sample Response
{
  "message": "Rate limit exceeded. Please try again later."
}
Concepts Learned
Rate Limiting
Redis TTL
Atomic Operations
System Design Fundamentals
Traffic Management
Containerization with Docker
Distributed Systems Basics
Future Improvements
Sliding Window Algorithm
Distributed Rate Limiting
API Gateway Integration
Metrics and Monitoring
Per-user and Per-endpoint limits
Dynamic configuration
Microservices integration
