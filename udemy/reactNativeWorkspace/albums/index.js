// Import a libary to help create a component
import React from 'react';
import { Text, AppRegistry, View} from 'react-native';
import Header from './src/components/Header';
import AlbumList from './src/components/AlbumList'; // don't need to add .js

// Create a component
const App = () => {
    return (
        <View>
            <Header headerText={'Albums!'}/>
            <AlbumList/>
        </View>
    );
};

// Render that component to the device
AppRegistry.registerComponent('albums', () => App); // this string must match up with project name