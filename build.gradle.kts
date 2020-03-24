import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.8.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
}

group = "ch.excape"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven {
		url = uri("${projectDir}/repo")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("ch.excape:swagger-ynab-client:0.0.1-SNAPSHOT")
	implementation("com.opencsv:opencsv:4.6")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation(project(":frontend"))

	runtimeOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

tasks.test {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
