// Import a libary to help create a component
import React from 'react';
import { Text, AppRegistry } from 'react-native';
import Header from './src/components/header';
// Create a component
const App = () => {
    return (
        <Header/>
    );
};

// Render that component to the device
AppRegistry.registerComponent('albums', () => App); // this string must match up with project name