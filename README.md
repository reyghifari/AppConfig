[![](https://jitpack.io/v/reyghifari/app-config.svg)](https://jitpack.io/#reyghifari/app-config)

Android library for application configuration, including Base URL environment management (DEV, SIT, UAT) and Exception/Error logging.

## ✨ Features

- 🔄 **Environment Switcher** - Easily switch the Base URL between DEV, SIT, and UAT
- 📋 **Exception Logger** - View and track errors/exceptions in the application

## 📦 Installation

### Step 1: Add JitPack repository

Add the following to your `settings.gradle.kts` (root project):
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Or if using settings.gradle (Groovy):

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

In your build.gradle.kts :
```
dependencies {
    implementation("com.github.reyghifari:AppConfig:v1.1.0")
}
```

Or if using build.gradle (Groovy):

```
dependencies {
  implementation 'com.github.reyghifari:AppConfig:v1.1.0'
}
```

### Initialize the Library

```
class MyApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize App Config
        AppConfig.init(
            context = this,
            environments = listOf(
                "https://api-dev.example.com/",
                "https://api-sit.example.com/",
                "https://api-uat.example.com/",
            )
        )
        AppConfig.exceptionLog(this)
    }
}
```
### Get BASEURL after SWITCH

```
 AppConfig.baseURL(context, "YOUR_DEFAULT_URL")
```




