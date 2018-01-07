import React, { Component } from 'react';
import firebase from 'firebase';
import { Text } from 'react-native';
import { Button, Card, CardSection, InputField, Spinner } from './common'; // does common/index.js automatically since no file is specified

class LoginForm extends Component {

    state = {
        email: '',
        password: '',
        error: '',
        loading: false
    };

    onButtonPress() {
        const { email, password } = this.state;
        this.setState({error: ''});
        if (password.length < 6){
            this.setState({error: 'Password too short'});
        } else {
            this.setState({loading: true});
            firebase.auth().signInWithEmailAndPassword(email, password)
            .then(this.onLoginSuccess.bind(this)) // executed if success
            .catch(() => {                        // executed if fails
                console.log('sign in with email and password failed');
                firebase.auth().createUserWithEmailAndPassword(email, password)
                    .then(this.onLoginSuccess.bind(this))
                    .catch(this.onLoginFail.bind(this));
            });
        }
    }

    onLoginFail() {
        console.log('create user with email and password failed');
        this.setState({
            error: 'Authentication Failed.',
            loading: false
        });
    }

    onLoginSuccess() {
        this.setState({
            email: '',
            password: '',
            loading: false,
            error: ''
        });
    }

    renderButton(){
        if(this.state.loading) {
            return <Spinner size="small" />;
        } else {
            return (
                <Button onPress={this.onButtonPress.bind(this)}>
                        Log In
                </Button>
            );
        }
    }

    render(){
        return (
            <Card>
                <CardSection>
                    <InputField
                        placeholder="user@gmail.com"
                        label="Email" 
                        value={this.state.email}
                        onChangeText={newText => this.setState({email: newText})}
                    />
                </CardSection>    

                <CardSection>
                    <InputField 
                        label="Password"
                        placeholder="password"
                        secureTextEntry
                        value={this.state.password}
                        onChangeText={password => this.setState({ password })}
                    />
                </CardSection> 

                <Text style={styles.errorTextStyles}>{this.state.error}</Text>

                <CardSection>
                    {this.renderButton()}
                </CardSection> 
            </Card>
        );
    }
}

const styles = {
    errorTextStyles: {
        fontSize: 20,
        alignSelf: 'center',
        color: 'red'
    }
};

export default LoginForm;
