
MovieCruiseApplication

This project was MovieCruiseServerApplication

First clone this project by following commands git clone https://gitlab-cts.stackroute.in/Pawan.Jaiswal/moviecuriserserverapplication.git

1-FrontEnd(Angular)

	Development server

	run npm install then Run ng serve for a dev server. Navigate to http://localhost:4200/. The app will automatically reload if you change any of the source files.


	Build

	Run ng build to build the project. The build artifacts will be stored in the dist/ directory. Use the --prod flag for a production build.


	Running unit tests

	Run ng test to execute the unit tests via Karma.


	Running end-to-end tests

	Run ng e2e to execute the end-to-end tests via Protractor.

2-BackEnd(Spring)

	Build

	Run source ./env-variable.sh to assign environment variable After that run mvn clean package to for testing and building the jar in target folder.

	Development server

	Run mvn spring-boot:run to run the application on the given port Repeat the above step for both the spring service


3-Docker
  
  	For Sql
	service mysql stop
	docker run -d -p 3306:3306 --network=host -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=moviedb -e MYSQL_USER=root -e MYSQL_PASSWORD=root mysql:5.5

	For Spring
	docker build -t test-spring .

	For Angular
	docker build -t angular-app .

	To Push in Docker Hub

	docker login --username=<usernamae>
	docker tag <imageid> <docker repository name>:<imagename>
	docker push <docker repository name>

	To Run

	docker-compose up This will run the application.
