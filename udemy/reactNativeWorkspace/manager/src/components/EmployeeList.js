import React, { Component } from 'react'
import { View, ListView } from 'react-native'
import { connect } from 'react-redux'
import { sceneStyle } from '../styles'
import { employeesFetch } from '../actions' // gets it from the index.js file, which exports all from all the actions in the actions folder
import _ from 'lodash'
import EmployeeListItem from './EmployeeListItem'
// import { sceneStyle } from '../styles'

class EmployeeList extends Component {

    componentWillMount() {
        this.props.employeesFetch()

        this.createDataSource(this.props)
    }

    componentWillReceiveProps(nextProps) {
        this.createDataSource(nextProps)
    }

    createDataSource(props) {
        const { employees } = props

        const ds = new ListView.DataSource({
            rowHasChanged: (r1, r2) => r1 !== r2
        })

        this.dataSource = ds.cloneWithRows(employees)
    }

    renderRow(employee) {
        return <EmployeeListItem employee={employee}/>
    }

    render() {
        return (
            <View style={{ marginTop: 65 }}>
                <ListView
                    enableEmptySections
                    dataSource={this.dataSource}
                    renderRow={this.renderRow}
                />
            </View>
        )
    }
}

const mapStateToProps = state => {
    // need to convert from object to array
    // state.employees is an object. for each key, value pair, run this method
    // create a new object, push in all the values and the key
    // map puts all the objects into an array
    const employees = _.map(state.employees, (val, uid) => {
        return {...val, uid}
    })

    return { employees } // don't  forget to have the curly brackets 
}

export default connect(mapStateToProps, { employeesFetch })(EmployeeList)
