docker_build:
	mvn clean install && docker-compose build --no-cache
docker_run:
	docker-compose rm -f -s -v && docker-compose up -d && watch "docker ps -a"
web:
	cd pet-store-web && npm i && npm run start

proxy:
	npm i express http-proxy-middleware && node proxy.js
