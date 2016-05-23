/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */

import React, { Component } from 'react';
import {
    AppRegistry,
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
