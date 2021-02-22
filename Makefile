docker_build:
	docker-compose build --no-cache
docker_run:
	docker-compose up
web:
	cd pet-store-web && npm run start