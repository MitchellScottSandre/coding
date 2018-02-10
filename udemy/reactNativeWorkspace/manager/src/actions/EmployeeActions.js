// email, phone, shift
import { EMPLOYEE_UPDATE, EMPLOYEE_CREATED, EMPLOYEES_FETCH_SUCCESS } from './types' 
import firebase from 'firebase'
import { Actions } from 'react-native-router-flux'

export const employeeUpdate = ({ prop, value }) => {
   
    return {
        type: EMPLOYEE_UPDATE,
        payload: { prop, value } // prop will be name, phone, or shift
    }
}

export const employeeCreate = ( { name, phone, shift }) => {
    // get currently authenticated user
    const { currentUser } = firebase.auth()
    
    return (dispatch) => { 
        firebase.database().ref(`/users/${currentUser.uid}/employees`)
        .push({name, phone, shift})
        .then(() => {
            dispatch({type: EMPLOYEE_CREATED})
            Actions.pop()
        }) // go back to previous screen, the employeeList screen
    }
    // Notes
    // uses backTicks, not single quotes
    // don't need to return an action, just wanted to create that employee
}

export const employeesFetch = () => {
    const { currentUser } = firebase.auth()

    return (dispatch) => {
        firebase.database().ref(`/users/${currentUser.uid}/employees`)
        .on('value', snapshot => {
            console.log(' ... calling employees fetch success')
            dispatch({ type: EMPLOYEES_FETCH_SUCCESS, payload: snapshot.val() })
        })
    }
}