# BatteryRamUsageWidget
Custom widget to show ram and battery usage in a circular progress


![Alt Text](https://github.com/gokul42252/BatteryRamUsageWidget/blob/master/2tdp1t.gif)


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

![Alt Text](https://github.com/gokul42252/BatteryRamUsageWidget/blob/master/2tdp1t.gif)
