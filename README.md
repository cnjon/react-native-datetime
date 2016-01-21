# React Native DateTime
A datetime-picker for react-native support for android and ios(base on @remobile/react-native-datetime-picker)

## Installation
```sh
npm install react-native-datetime --save
```

### Installation (iOS)
* not need install, on ios use js write

### Installation (Android)
```gradle
...
include ':react-native-datetime'
project(':react-native-datetime').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-datetime/android')
```

* In `android/app/build.gradle`

```gradle
...
dependencies {
    ...
    compile project(':react-native-datetime')
}
```

* register module (in MainActivity.java)

```java
import com.keyee.datetime.*;  // <--- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ......
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new RCTDateTimePickerPackage())              // <------ add here
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "ExampleRN", null);

    setContentView(mReactRootView);
  }

  ......
}
```

### Screencasts
* ios
<br>
![image](https://github.com/remobile/react-native-datetime-picker/blob/master/screencasts/ios/1.png)
![image](https://github.com/remobile/react-native-datetime-picker/blob/master/screencasts/ios/2.png)
![image](https://github.com/remobile/react-native-datetime-picker/blob/master/screencasts/ios/3.png)
<br>
* android
<br>
![image](https://github.com/remobile/react-native-datetime-picker/blob/master/screencasts/android/1.png)
![image](https://github.com/remobile/react-native-datetime-picker/blob/master/screencasts/android/2.png)


## Usage
use as follows:
```js
<DateTimePicker ref={(picker)=>{this.picker=picker}}/>
...
this.picker.showDatePicker(...)
this.picker.showTimePicker(...)
```
* on ios, make sure <DateTimePicker> must on topest view

### Example
```js
'use strict';

var React = require('react-native');
var {
    StyleSheet,
    TouchableOpacity,
    View,
    Text,
} = React;

var DateTimePicker = require('@remobile/react-native-datetime-picker');
var Button = require('@remobile/react-native-simple-button');

module.exports = React.createClass({
    getInitialState() {
        return {
            date: new Date(),
        }
    },
    showDatePicker() {
        var date = this.state.date;
        this.picker.showDatePicker(date, (d)=>{
            this.setState({date:d});
        });
    },
    showTimePicker() {
        var date = this.state.date;
        this.picker.showTimePicker(date, (d)=>{
            this.setState({date:d});
        });
    },
    render() {
        return (
            <View style={styles.container}>
                <Text style={{textAlign: 'center'}}>
                    {this.state.date.toString()}
                </Text>
                <View style={{height:40}} />
                <Button onPress={this.showDatePicker}>showDatePicker</Button>
                <View style={{height:40}} />
                <Button onPress={this.showTimePicker}>showTimePicker</Button>
                <DateTimePicker ref={(picker)=>{this.picker=picker}}/>
            </View>
        );
    },
});

var styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        paddingTop:20,
    },
});
```

### Methods

* showDatePicker(date, callback(date))
* showTimePicker(date, callback(date))

### Caution
* don't need set any props for <DateTimePicker>
