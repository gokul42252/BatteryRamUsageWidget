# BatteryRamUsageWidget
Custom widget to show ram and battery usage in a circular progress


![Alt Text](https://github.com/gokul42252/BatteryRamUsageWidget/blob/master/usage.gif)


## How to use in your project

### Step 1. Add the JitPack repository to your build file

gradle:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
maven
```maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
### Step 2. Add the dependency

gradle
```gradle
dependencies {
	        implementation 'com.github.gokul42252:BatteryRamUsageWidget:1.0.0'
	}
```
maven
```maven
<dependency>
	    <groupId>com.github.gokul42252</groupId>
	    <artifactId>BatteryRamUsageWidget</artifactId>
	    <version>1.0.0</version>
	</dependency>
```

### Step 2. Add to your Activity.xml
```java
  <com.ct.respurceusageview.WavesView
            style="@style/Widget.WaveView"
            android:layerType="software"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    <com.ct.respurceusageview.CircularLoadProgressView
            android:id="@+id/circle_progress_bar"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:lcv_base_arc_color="@color/colorPrimaryVeryLight"
            app:lcv_label_fontName="SansationRegular.ttf"
            app:lcv_arc_width="30dp"
            app:lcv_progress_arc_color="@color/colorGraphRed"
            app:lcv_progress_label_str="50%"
            app:lcv_progress_label_text_color="@color/colorPrimaryVeryLight"
            app:lcv_progress_label_text_size="40sp"
            app:lcv_progress_value_text_color="@color/colorWhite"
            app:lcv_progress_value_text_size="100sp"
            tools:ignore="MissingConstraints"/>
```

### Step 2. Add to your Activity.java

```java
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var i = 0
        val cicView = findViewById<CircularLoadProgressView>(R.id.circle_progress_bar)
        val ha = Handler()
        ha.postDelayed(object : Runnable {
            override fun run() {
                if (i <= 95) {
                    i += 5
                } else if (i >= 1) {
                    i -= 5
                }
                val progress = calculateScaledValue(
                    0,
                    99,
                    i.toLong(),
                    0,
                    360
                )
                cicView.updateProWithProText("$i%", progress)
                ha.postDelayed(this, 1000)

            }
        }, 1000)

    }


    /**
     * Method to calculate the scaled value
     */
    fun calculateScaledValue(
        minValue: Long,
        maxValue: Long,
        currentValue: Long,
        minScaledValue: Int,
        maxScaledValue: Int
    ): Float {
        return ((currentValue - minValue) *
                (maxScaledValue - minScaledValue).toLong() /
                (maxValue - minValue) + 0).toFloat()
    }
```
![Alt Text](https://github.com/gokul42252/BatteryRamUsageWidget/blob/master/usage.gif)
