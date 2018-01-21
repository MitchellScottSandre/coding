import React, { Component } from 'react'
import { View, Text } from 'react-native'
import { Card, CardSection, InputField, Button, Spinner } from './common'
import { emailChanged, passwordChanged, loginUser } from '../actions' // from the index.js file inside of there
import { connect } from 'react-redux'
import { sceneStyle } from '../styles'

class LoginForm extends Component {
    onEmailChange(text){
        this.props.emailChanged(text) // this.props.emailChanged comes from redux connect
    }

    onPasswordChange(text){
        this.props.passwordChanged(text)
    }

    onButtonPress() {
        const { email, password } = this.props
        this.props.loginUser({ email, password})
    }

    renderError() {
        if (this.props.error){
            return (
                <View style={{backgroundColor: 'white'}}>
                    <Text style={styles.errorTextStyle}>{this.props.error}</Text>
                </View>
            )
        }
    }

    renderButton() {
        if (this.props.loading){
            return <Spinner size="large" />
        } else {
            return <Button onPress={this.onButtonPress.bind(this)}>Login</Button>
        }
    }

    renderLoadingSpinner() {
        if (this.props.loading){
            return <Spinner />
        }
    }

    render() {
        return (
            <View style={sceneStyle}>
                <Card>
                    <CardSection>
                        <InputField label="Email" placeholder="email@gmail.com" 
                                    onChangeText={this.onEmailChange.bind(this)} value={this.props.email}/>
                    </CardSection>

                    <CardSection>
                        <InputField secureTextEntry label="Password" placeholder="password" 
                                    onChangeText={this.onPasswordChange.bind(this)} value={this.props.password}/>
                    </CardSection>
                    {this.renderError()}
                    <CardSection>
                        {this.renderButton()}
                    </CardSection>
                </Card>
            </View>
        )
    }
}

const styles = {
    errorTextStyle: {
        textAlign: 'center',
        justifyContent: 'center',
        color: 'red',
        fontSize: 20,
        alignSelf: 'center'
    }
}

const mapStateToProps = state => {
    return {
        email: state.auth.email, // auth is value we assigned reducer to, reducer provides the .email value,
        password: state.auth.password,
        error: state.auth.error,
        loading: state.auth.loading
    }
}

export default connect(mapStateToProps, { emailChanged, passwordChanged, loginUser })(LoginForm);