init:
	git init
	touch .git/info/exclude

#	maven-wrapper
#	mvn -N io.takari:maven:wrapper -Dmaven=3.6.2
#	rm mvnw.cmd
#	chmod +x ./mvnw
#	echo "\n/.mvn\n/mvnw*\n" >> .git/info/exclude

#	jenv
#	jenv local `cat pom.xml | xml sel -N pom=http://maven.apache.org/POM/4.0.0 -t -v /pom:project/pom:properties/pom:java.version`.0
#	echo "\n/.java-version\n" >> .git/info/exclude

#	checkstyler
	curl -O https://raw.githubusercontent.com/checkstyle/checkstyle/master/src/main/resources/google_checks.xml
	echo "\n/google_checks.xml\n" >> .git/info/exclude

uninit:
	rm -rf .mvn mvnw* google_checks.xml .git/info/exclude

reboot: clear uninit init

uninit-full: clear uninit
	rm -rf .idea spring-intro.iml .git

reboot-full: uninit-full init
	echo "\n/.idea/\n/spring-intro.iml\n/out/\n/classes/\n" >> .git/info/exclude
	git add src .editorconfig .gitignore Makefile pom.xml README.md
	idea pom.xml

jshell:
	jshell --enable-preview --start PRINTING --start JAVASE --class-path `mvn dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1`

build:
	./mvnw verify
	chmod +x ./target/spring-intro-0.0.1-SNAPSHOT.jar

run:
	./mvnw spring-boot:start -Dspring.profiles.active=local
#	./target/spring-intro-0.0.1-SNAPSHOT.jar
#	java -jar --enable-preview ./target/spring-intro-0.0.1-SNAPSHOT.jar

clear:
	./mvnw clean

test: clear
	./mvnw test

update:
	./mvnw versions:update-parent versions:update-properties versions:display-plugin-updates

delombok: clear
	./mvnw lombok:delombok
#	mkdir -p ./target/generated-sources/delombok
#	java -cp `./mvnw dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1` \
#		lombok.launch.Main delombok src/main/java \
#		-d target/generated-sources/delombok

test-delombok: delombok
	./mvnw lombok:testDelombok
#	mkdir -p ./target/generated-test-sources/delombok
#	java -cp `./mvnw dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1`:$(A)/target/generated-sources/delombok \
#		lombok.launch.Main delombok src/test/java \
#		-d target/generated-test-sources/delombok

.DEFAULT_GOAL := build-run
build-run: update build run
