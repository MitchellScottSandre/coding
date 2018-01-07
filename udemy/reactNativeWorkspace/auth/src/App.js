import React, { Component } from 'react';
import { View, Text } from 'react-native';
import * as firebase from 'firebase';
import { Header } from './components/common'; // don't need to specify the index file, by DEFAULT it will therefore go to index.js
import LoginForm from './components/LoginForm';

class App extends Component {
    componentWillMount(){
        firebase.initializeApp({
            apiKey: "AIzaSyBl1V8XiIuwjxMq4Puyj2q1QNw6jktOzn0",
            authDomain: "authentication-f2cc1.firebaseapp.com",
            databaseURL: "https://authentication-f2cc1.firebaseio.com",
            projectId: "authentication-f2cc1",
            storageBucket: "authentication-f2cc1.appspot.com",
            messagingSenderId: "654945123544"
          });
    }

    render(){
        return (
            <View>
                <Header headerText="Authentication" />
                <LoginForm />
            </View>
        );
    }
}

export default App;