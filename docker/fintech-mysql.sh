docker run -d \
--name fintech-mysql \
--network=fintech-net \
-e MYSQL_ROOT_PASSWORD="fintech" \
-e MYSQL_USER="fintech" \
-e MYSQL_PASSWORD="fintech" \
-e MYSQL_DATABASE="fintech" \
-p 3306:3306 \
-d \
mysql:latest