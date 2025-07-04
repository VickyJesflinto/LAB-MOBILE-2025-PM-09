plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.belajarnetworking"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.belajarnetworking"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0") // Instal retrofit2
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // Instal retrofit2-converter

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
    
    // OkHttp
    implementation ("com.squareup.okhttp3:okhttp:4.9.1") // Instal okhttp (caching, logging, dll)
    implementation ("com.squareup.picasso:picasso:2.71828") // Instal picasso (Menampilkan gambar)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}