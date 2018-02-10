import { EMPLOYEE_UPDATE, EMPLOYEE_CREATED} from '../actions/types'

const INITIAL_STATE = {
    name: '',
    phone: '',
    shift: ''
}

export default (state = INITIAL_STATE, action) => {
    switch(action.type){
        case EMPLOYEE_UPDATE:
            return { ...state, [action.payload.prop]: action.payload.value}
            // [action.payload.prop] --> key interpolation
            // if action.payload.prop == 'name', then it turns into name: 
        case EMPLOYEE_CREATED:
            return INITIAL_STATE
        default:
            return state
    }
}