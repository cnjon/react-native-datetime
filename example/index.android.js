/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */

import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View
} from 'react-native';

import ExampleComponent from './ExampleComponent';

class example extends Component {
    render() {
        return (
            <ExampleComponent />
        );
    }
}

AppRegistry.registerComponent('example', () => example);
