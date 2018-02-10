
import React, { Component } from 'react';
import { connect } from 'react-redux';
import { employeeUpdate, employeeSave } from '../actions';
import { Card, CardSection, Button, Confirm } from './common';
import EmployeeForm from './EmployeeForm'
import _ from 'lodash'
import Communications from 'react-native-communications'

class EmployeeEdit extends Component {

    state = {
        showModal: false
    }

    // look at this.props.employee, and for all props, forward them on to employeeUpdate
    componentWillMount() {
        // iterate over every property and update reducer for each
        _.each(this.props.employee, (value, prop) => {
            this.props.employeeUpdate( { prop, value} )
        })
    }


    saveChanges() {
        const { name, phone, shift } = this.props
        this.props.employeeSave({ name, phone, shift, uid: this.props.employee.uid })
        // action creator that will save stuff to firebase
    }

    deleteChanges() {
        this.setState({showModal: !this.state.showModal})
        // this.props.employeeDelete({ uid: this.props.employee.uid }) //
    }

    textPerson() {
        const { phone, shift } = this.props
        let text = "Your upcoming shift is on " + shift
        Communications.text(phone, text)
    }

    render() {

        return (
        <Card>
            <EmployeeForm {...this.props} />
            <CardSection>
                <Button onPress={this.saveChanges.bind(this)}>Save Changes</Button>
            </CardSection>
            <CardSection>
                <Button onPress={this.textPerson.bind(this)}>Text Schedule</Button>
            </CardSection>
            <CardSection>
                <Button onPress={this.deleteChanges.bind(this)}>Fire Employee</Button>
            </CardSection>

            <Confirm visible={this.state.showModal}>
                Are you sure you want to fire them?
            </Confirm>
        </Card>
        );
    }
} 

const mapStateToProps = (state) => {
  const { name, phone, shift } = state.employeeForm;

  return { name, phone, shift };
};

export default connect(mapStateToProps, { employeeUpdate, employeeSave })(EmployeeEdit);