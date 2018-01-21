// action creator
import firebase from 'firebase'
import { EMAIL_CHANGED, PASSWORD_CHANGED, LOGIN_USER_SUCCESS, LOGIN_USER_FAIL, LOGIN_USER_START} from './types'
import { Actions } from 'react-native-router-flux'
export const emailChanged = (text) => {
    return {
        type: EMAIL_CHANGED,
        payload: text
    };
};

export const passwordChanged = (text) => {
    return {
        type: PASSWORD_CHANGED,
        payload: text
    }
}

// this is asynchronous!
export const loginUser = ( { email, password } ) => {
    return (dispatch) => {
        dispatch({type: LOGIN_USER_START})

        firebase.auth().signInWithEmailAndPassword(email, password)
            .then(user => loginUserSuccess(dispatch, user))                     // sign in
            .catch(() => {
                firebase.auth().createUserWithEmailAndPassword(email, password)
                    .then(user => loginUserSuccess(dispatch, user))             // create new account
                    .catch(() => loginUserFail(dispatch))
            })
    }      
}

const loginUserSuccess = (dispatch, user) => {
    dispatch({
        type: LOGIN_USER_SUCCESS,
        payload: user
    })

    Actions.main()
}

const loginUserFail = (dispatch) => {
    dispatch({type: LOGIN_USER_FAIL})
}