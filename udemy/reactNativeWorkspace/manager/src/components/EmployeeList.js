import React, { Component } from 'react'
import { View, Text } from 'react-native'
import { connect } from 'react-redux'
import { sceneStyle } from '../styles'
import { employeesFetch } from '../actions' // gets it from the index.js file, which exports all from all the actions in the actions folder

class EmployeeList extends Component {

    componentWillMount() {
        this.props.employeesFetch()
    }

    render() {
        return (
            <View style={sceneStyle}>
                <Text>asdf</Text>
                <Text>asdf</Text>
                <Text>asdf</Text>
                <Text>asdf</Text>
                <Text>asdf</Text>
                <Text>asdf</Text>
                <Text>asdf</Text>
                <Text>asdf</Text>
            </View>
        )
    }
}

export default connect(null, { employeesFetch })(EmployeeList)
