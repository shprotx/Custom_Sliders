# Custom Sliders for Jetpack Compose 🎚️ 

A collection of customizable sliders for Jetpack Compose, including a default slider, a two-value slider, and a color selection slider.

# Features

- Fully customizable styles and properties
- Supports gradient backgrounds and color selection
- Includes value indicators (optional)
- Works with any range of values
- API level 26+

# Installation

Add Jitpack repository to your project (settings.gradle.kts)

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

Add library dependency

```kotlin
dependencies {
    implementation("com.github.shprotx:Custom_Sliders:v1.0-alpha")
}
```

# Usage

Default slider

```kotlin
var defSliderCurrentValue by remember { mutableFloatStateOf(40f) }
val scaleItems = listOf("10", "20", "30", "40", "50", "60", "70", "80", "90") // optional

DefaultSlider(
    modifier = Modifier,
    currentValue = defSliderCurrentValue,
    minValue = 0f,
    maxValue = 100f,
    horizontalPaddingDp = horizontalPadding,
    scaleItems = scaleItems,                               // optional
    colors = CustomSliderDefaults.sliderColors(),          // optional
    properties = CustomSliderDefaults.sliderProperties(),  // optional
    withIndicator = true,                                  // optional
    isSliderEnabled = true,                                // optional
    onValueChange = { newValue -> defSliderCurrentValue = newValue },
    onDragEnd = {
        // todo your api request or something else
    },
)
```

Two-values slider

```kotlin
var currentValue by remember { mutableFloatStateOf(60f) }
val gradient = Brush.horizontalGradient(                   // optional
        listOf(
            Color(0xFF86E5F5),
            Color(0xFFFFC000),
            Color(0xFFFF9E58),
            Color(0xFFFF634E),
        )
    )

TwoValuesSlider(
    modifier = Modifier,
    currentValue = currentValue,
    baseValue = baseValue,
    minValue = 0f,
    maxValue = 100f,
    horizontalPaddingDp = horizontalPadding,
    brush = gradient,                                      // optional
    colors = CustomSliderDefaults.sliderColors(),          // optional
    properties = CustomSliderDefaults.sliderProperties(),  // optional
    withIndicator = true,                                  // optional
    isSliderEnabled = true,                                // optional
    onValueChange = { newValue -> currentValue = newValue },
    onDragEnd = {
        // todo your api request or something else
    },
)
```

Color selection slider

```kotlin
var currentColor: Color? by remember { mutableStateOf(null) }
val rgbGradientColors = remember {
        listOf(
            Color(0xFFFF0000),
            Color(0xFFFF8A00),
            Color(0xFFFFE500),
            Color(0xFF05FF00),
            Color(0xFF00FFF0),
            Color(0xFF0500FF),
            Color(0xFFFF00D6),
        )
    }

ColorSelectionSlider(
    modifier = Modifier,
    gradientList = rgbGradientColors,
    horizontalPaddingDp = horizontalPadding,
    colors = CustomSliderDefaults.sliderColors(),         // optional
    properties = CustomSliderDefaults.sliderProperties(), // optional
    onSliderPositionChanged = { newColor -> currentColor = newColor},
    onDragEnd = {
        // todo your api request or something else
    },
)
```

### Customization

Colors [CustomSliderDefaults.sliderColors()]

```kotlin
class CustomSliderColors(
    val trackColor: Color,
    val sliderColor: Color,
    val knobColor: Color,
    val indicatorColor: Color,
    val moreThanBaseColor: Color,
    val lessThanBaseColor: Color,
)
```

Properties [CustomSliderDefaults.sliderProperties()]

```kotlin
class CustomSliderProperties(
    val sliderHeight: Dp,
    val sliderCornerRadius: Dp,
    val knobWidth: Dp,
    val knobHorizontalPadding: Dp,
    val knobVerticalPadding: Dp,
    val indicatorSize: Dp,
    val stepBetweenStripes: Dp,
)
```

See example app for more details.
