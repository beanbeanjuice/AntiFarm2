import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.io.ByteArrayOutputStream

plugins {
    id("com.github.johnrengelman.shadow") version("8.1.1")
    id("java")
}

group = "com.beanbeanjuice"
version = "1.7.18"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()

    maven {
        name = "maven-central-2"
        url = uri("https://oss.sonatype.org/content/groups/public")
    }

    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
}

dependencies {
    // Spigot
    compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")

    implementation("com.tchristofferson:ConfigUpdater:2.1-SNAPSHOT")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

configure<ProcessResources>("processResources") {
    filesMatching("plugin.yml") {
        expand(project.properties)
    }
}

inline fun <reified C> Project.configure(name: String, configuration: C.() -> Unit) {
    (this.tasks.getByName(name) as C).configuration()
}

tasks.withType<ShadowJar> {
    minimize()
    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
    archiveVersion.set(version as String)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
