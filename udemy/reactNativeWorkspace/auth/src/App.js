import React, { Component } from 'react';
import { View, Text } from 'react-native';
import * as firebase from 'firebase';
import { Header, Button, Spinner } from './components/common'; // don't need to specify the index file, by DEFAULT it will therefore go to index.js
import LoginForm from './components/LoginForm';

class App extends Component {
    state = {
        loggedIn: null
    }

    componentWillMount(){
        firebase.initializeApp({
            apiKey: "AIzaSyBl1V8XiIuwjxMq4Puyj2q1QNw6jktOzn0",
            authDomain: "authentication-f2cc1.firebaseapp.com",
            databaseURL: "https://authentication-f2cc1.firebaseio.com",
            projectId: "authentication-f2cc1",
            storageBucket: "authentication-f2cc1.appspot.com",
            messagingSenderId: "654945123544"
          });

        firebase.auth().onAuthStateChanged((user) => { //sign in, user... sign out, null
            if (user) {
                this.setState({loggedIn: true});
            } else {
                this.setState({loggedIn: false});
            }
        });
    }

    renderContent() {
        switch(this.state.loggedIn){
            case true:
                return (
                    <Button onPress={() => firebase.auth().signOut()} >
                        Log Out
                    </Button>
                );
            case false:
                return <LoginForm />;
            default:
                return (
                    <View style={styles.mainSpinnerStyle}>
                        <Spinner size="large" />
                    </View>
                );
        }
        
    }

    render(){
        return (
            <View>
                <Header headerText="Authentication" />
                {this.renderContent()}
            </View>
        );
    }
}

const styles = {
    mainSpinnerStyle: {
        marginTop: 20,
        height: 20,
        alignItems: 'center',
        justifyContent: 'center'
    }
};  

export default App;