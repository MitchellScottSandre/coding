import React, { Component } from 'react'
import { View, Text } from 'react-native'
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux'
import reducers from './reducers'
import firebase from 'firebase'
import ReduxThunk from 'redux-thunk' //middle ware, need to import from redux!!
import LoginForm from './components/LoginForm'

class App extends Component {

    componentWillMount() {
        const config = {
            apiKey: "AIzaSyDwQdNC7lALbRK-eSwZ9iZvwtnH6nw7jag",
            authDomain: "manager-1ccf7.firebaseapp.com",
            databaseURL: "https://manager-1ccf7.firebaseio.com",
            projectId: "manager-1ccf7",
            storageBucket: "manager-1ccf7.appspot.com",
            messagingSenderId: "110185549378"
          };
          firebase.initializeApp(config);
    }
    render() {
        const store = createStore(reducers, {}, applyMiddleware(ReduxThunk))
        // applyMiddleware is a store enhancer
        return (
            <Provider store={store}>
                <LoginForm />
            </Provider>
        )
    }
}

export default App;