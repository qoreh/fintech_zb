docker run --name fintech-redis \
-p 6379:6379 \
--network fintech-net \
-d redis:latest
