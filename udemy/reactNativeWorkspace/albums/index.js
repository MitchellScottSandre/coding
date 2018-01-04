// Import a libary to help create a component
import React from 'react';
import { Text, AppRegistry } from 'react-native';

// Create a component
const App = () => {
    return (
        <Text>Some Text</Text>
    );
};

// Render that component to the device
AppRegistry.registerComponent('albums', () => App); // this string must match up with project name