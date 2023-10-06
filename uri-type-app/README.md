# uri basics

there are 4 types of uris

## 1. resource uri
- resource uri points to any files in our projects res folder.
```kotlin
val resourceUri = Uri.parse("android.resource://$packageName/drawable/maruchan_the_cat")
```
    
## 2. file uri
- file uir points to any files in internal storage of the app.
```kotlin
val file = File(filesDir, "maruChanTheCat.jpg")
val fileUri = file.toUri()
```

## 3. content uri
- content uri temporarily points to any files user has picked.
- it those not require to have storage permissions
```kotlin
val pickImage = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent(),
    onResult = {contentUri ->
        // code..
    }
)
```

## 4. data uri
- data uri has data within the uri
```kotlin
val dataUri = Uri.parse("data:text/plain;charset=UTF-8,Hello%20World")
```
