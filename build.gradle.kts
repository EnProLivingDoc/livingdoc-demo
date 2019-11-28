plugins {
	idea
	kotlin("jvm") version "1.3.50"
}

group = "org.livingdoc"
version = "2.0-SNAPSHOT"

repositories {
	mavenCentral()
	jcenter()
	mavenLocal()
	maven {
		url = uri("https://packages.atlassian.com/mvn/maven-external")
	}
	maven {
		// This is only used if not found in mavenLocal
		url = uri("https://gilbert.informatik.uni-stuttgart.de/api/v4/projects/328/packages/maven")
		credentials(HttpHeaderCredentials::class.java) {
			name = "Job-Token"
			value = System.getenv("CI_JOB_TOKEN")
		}
		authentication {
			this.create("header", HttpHeaderAuthentication::class.java)
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))

	testRuntime("org.livingdoc:livingdoc-junit-engine:${project.version}")
	testRuntime("org.livingdoc:livingdoc-repository-file:${project.version}")
	testRuntime("org.livingdoc:livingdoc-repository-rest:${project.version}")
	testRuntime("org.livingdoc:livingdoc-repository-confluence:${project.version}")

	testImplementation("org.livingdoc:livingdoc-api:${project.version}")
	testImplementation("ch.qos.logback:logback-classic:1.2.3")
	testImplementation("org.assertj:assertj-core:3.11.1")

	testImplementation ("org.junit.jupiter:junit-jupiter-api:5.5.1")
	testImplementation ("org.junit.jupiter:junit-jupiter-params:5.5.1")
	testImplementation("io.mockk:mockk:1.9.3")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.1")
}
