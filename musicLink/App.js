/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});


export default class App extends Component<{}> {
  func() {
    fetch("https://api.music.apple.com/v1/catalog/us/artists/36954", {
  headers: {
    Authorization: "Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjMyRzk4TUNTMzMifQ.eyJpc3MiOiI5ODc2NTQzMjEwIiwiaWF0IjoxNTE2NzYzMDk1LCJleHAiOjE1MTY4MDYyOTV9.SX4AgdmD-Uins7FmLu9FdY99oH7FbuoiIXQ3OMq820poI-5dAWVh-UdJQ324wucggjtaNyT18vYkLP08b_ywIQ"
  }
}).then((response) => console.log(response))
  }

  render() {
    this.func()

    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit App.js
        </Text>
        <Text style={styles.instructions}>
          {instructions}
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
