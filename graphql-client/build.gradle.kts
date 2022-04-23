import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("com.netflix.dgs.codegen") version "5.1.17"
}

group = "com.netlight.berlin.edgey"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("io.github.microutils:kotlin-logging-jvm:2.1.20")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.1")


	implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:4.9.25"))
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")

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

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
	schemaPaths = mutableListOf("${projectDir}/src/main/resources/schema") // List of directories containing schema files
	packageName = "com.example.packagename" // The package name to use to generate sources
	generateClient = true // Enable generating the type safe query API

}
