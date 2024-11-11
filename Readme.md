# Compose Preferences

Preference functionality for compose projects. Preference values are stored in a DataStores, managed by a DataStoreManager.

Currently supported Preferences:

    - TextPreference: A Preference to display text without storing any data.
    - SwitchPreference: Stores boolean data.


## Usage:
---

1. Initialize the DataStoreManager with a DataStore<Preferences> object
2. Collect the preferences
3. Create a composable and place the Preferences inside.

```kotlin

val Context.datastore: DataStore<Preferences> by preferencesDataStore("settings")

@Composable
fun PreferenceScreen() {

    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context.datastore) }

    val preferences by dataStoreManager.preferenceFlow.collectAsState(null)

    Column(modifier = modifier.fillMaxSize()) {

        Preference(
            data = PreferenceData.SwitchPreference(
                commons = PreferenceData.Commons(
                    title = "Preference Title",
                    summary = "Some Text ...",
                    enabled = true,
                    iconSpaceReserved = true
                ),
                key = "KEY_ONE",
                default = true,
            ),
            preferences = preferences,
            dataStoreManager = dataStoreManager
        )
    }
}


```

## PreferenceGroup

Use the PreferenceGroup composable to categorize preferences


```kotlin

PreferenceGroup(
    title = "Group title",
    icon = Icons.Default.Settings,
    preferences = preferences,
    dataStoreManager = DataStoreManager(context.datastore),
    preferenceData = listOf(
        PreferenceData.SwitchPreference(
            commons = PreferenceData.Commons(
                title = "Preference A",
                summary = "Preference A Summary",
                iconSpaceReserved = true),
            key = "keyA",
            default = true
        ),
        PreferenceData.TextPreference(
            commons = PreferenceData.Commons(
                title = "Preference B",
                summary = "Preference B Summary",
                icon = Icons.Default.Settings),
        )

    )
)

```

## Read data from DataStore
---

Get all Preferences as flow from the DataStoreManager

```kotlin

val flow : Flow<Preferences> = dataStoreManager.preferenceFlow


```

Read a single preference by using the getPreference() and getPreferenceFlow() methods.

```kotlin
//asynchronously
val flow = dataStoreManager.getPreferenceFlow(key = stringPreferenceKey("myKey"), default = "default value")

//synchronously (suspending)
val value = dataStoreManager.getPreferencew(key = stringPreferenceKey("myKey"), default = "default value")

```

## Edit Data
---

```kotlin
//suspending
dataStoreManager.edit(key = stringPreferenceKey("myKey"), value = "new value")
```
