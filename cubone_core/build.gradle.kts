import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
	kotlin("plugin.jpa") version "1.6.0"
}

group = "io.cubone"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val	springBootVersion = "2.6.1" // keep in sync with boot plugin

repositories {
	mavenCentral()
}

dependencies {
	implementation(project("modules:a:a-impl"))
	implementation(project("modules:b:b-impl"))
	implementation(project("modules:c:c-impl"))

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

subprojects {
	// this sets the group and version the same as the root
	group = rootProject.group
	version = rootProject.version

	apply(plugin = "java")
	apply(plugin = "io.spring.dependency-management")

	repositories {
		mavenCentral()
	}

	dependencyManagement {
		imports {
			// use the same spring boot version as in the root
			mavenBom("org.springframework.boot:spring-boot-dependencies:${springBootVersion}")
		}
	}

	// define dependencies every module will inherit
	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")

		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
