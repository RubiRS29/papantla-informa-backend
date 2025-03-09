docker run --name mysql-municipio-db -e MYSQL_ROOT_PASSWORD=Papantla123 -e MYSQL_DATABASE=municipio_db -e MYSQL_USER=Papantla -e MYSQL_PASSWORD=Papantla123 -p 3306:3306 -d mysql:latest


mysql -h papantla-informa-municipio-db.c434mwwygtna.us-east-1.rds.amazonaws.com  -P 3306 -u papantla -p