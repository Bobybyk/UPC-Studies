plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":config"))
    testImplementation("junit:junit:4.+")
    implementation(group= "com.github.xmlet", name="htmlflow", version="3.5")
}


